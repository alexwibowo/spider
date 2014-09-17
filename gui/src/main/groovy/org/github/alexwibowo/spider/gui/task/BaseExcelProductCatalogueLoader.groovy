package org.github.alexwibowo.spider.gui.task

import org.apache.commons.io.IOUtils
import org.apache.commons.lang.StringUtils
import org.apache.poi.poifs.filesystem.OfficeXmlFileException
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.github.alexwibowo.spider.catalogue.CatalogueLoadingException
import org.github.alexwibowo.spider.catalogue.InvalidProductException
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.BarcodeSpiderMainFrame
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.*
import java.util.concurrent.ExecutionException

import static javax.swing.JOptionPane.showMessageDialog

/**
 * User: alexwibowo
 */
abstract class BaseExcelProductCatalogueLoader extends SwingWorker<Void, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExcelProductCatalogueLoader.class.getName());

    public static final int BARCODE_COLUMN = 0

    public static final int PRODUCT_NAME_COLUMN = 1

    File sourceFile

    Closure callback

    ProductCatalogue catalogue

    @Override
    protected Void doInBackground() throws Exception {
        LOGGER.info("About to load catalogue from [${sourceFile.absolutePath}");
        setProgress(0)     // make sure we reach 100% at the end
        load(sourceFile) { int index ->
            publish(index)
        }
        setProgress(100)     // make sure we reach 100% at the end
        LOGGER.info("Finished reading catalogue from [${sourceFile.absolutePath}]");
        return null
    }

    void load(File file, Closure closure = null) {
        catalogue.clear()
        catalogue.name = file.getAbsolutePath()
        def is = file.newInputStream()
        try {
            Workbook wb = getWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);

            int numberOfRows = sheet.getPhysicalNumberOfRows();

            for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
                Row currentRow = sheet.getRow(rowIndex);
                if (currentRow != null) {
                    validateRow(currentRow)
                    String barcode = readBarcode(currentRow)
                    String productName = readProductName(currentRow)

                    def product = new Product(barcode: barcode, name: productName)
                    LOGGER.info("Product ${product} is loaded.");
                    catalogue.addProductToCatalogue(product)
                    if (closure) {
                        closure.call(rowIndex)
                    }
                }
            }
        } catch (IOException | OfficeXmlFileException ex) {
            LOGGER.error("Failed to read file", ex);
            throw new CatalogueLoadingException("Error encountered while reading [${file}].", ex)
        }finally{
            IOUtils.closeQuietly(is)
        }
    }

    protected abstract Workbook getWorkbook(InputStream is);


    private String readProductName(Row currentRow) {
        String productName = currentRow.getCell(PRODUCT_NAME_COLUMN)
        if (StringUtils.isBlank(productName)) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank product name.")
        }
        productName
    }

    private String readBarcode(Row currentRow) {
        if (currentRow.getCell(BARCODE_COLUMN).cellType != Cell.CELL_TYPE_STRING) {
            throw new CatalogueLoadingException("Row ${currentRow.rowNum + 1} doesnt have barcode is textual format.")
        }
        String barcode = currentRow.getCell(BARCODE_COLUMN).toString()
        if (StringUtils.isBlank(barcode)) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank barcode.")
        }
        barcode
    }

    private void validateRow(Row currentRow) {
        if (currentRow.getCell(BARCODE_COLUMN) == null) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank barcode.")
        }
        if (currentRow.getCell(PRODUCT_NAME_COLUMN) == null) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank product name.")
        }
    }

    @Override
    protected void process(List<Integer> chunks) {
        chunks.each {
            callback.call(it)
        }
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (ExecutionException e) {
            LOGGER.error("An error has occurred while loading catalogue",e)
            String msg = String.format("Unexpected problem: %s",
                    e.getCause().getMessage());
            showMessageDialog(BarcodeSpiderMainFrame.instance(), msg, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException e) {
            LOGGER.info("Work was interrupted");
            // Process e here
        }
    }
}
