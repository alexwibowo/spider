package org.github.alexwibowo.spider.catalogue

import org.apache.commons.lang.StringUtils
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Cell

class ExcelBasedProductCatalogue implements ProductCatalogue {
    public static final int BARCODE_COLUMN = 0

    public static final int PRODUCT_NAME_COLUMN = 1

    private final Map<String, Product> catalogue

    ExcelBasedProductCatalogue() {
        catalogue = Collections.synchronizedMap([:])
    }

    void load(InputStream is) {
            clear()
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);

            int numberOfRows = sheet.getPhysicalNumberOfRows();

            for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
                HSSFRow currentRow = sheet.getRow(rowIndex);
                if (currentRow != null) {
                    validateRow(currentRow)
                    String barcode = readBarcode(currentRow)
                    String productName = readProductName(currentRow)
                    addProductToCatalogue(new Product(barcode: barcode, name: productName))
                }
            }
    }

    private String readProductName(HSSFRow currentRow) {
        String productName = currentRow.getCell(PRODUCT_NAME_COLUMN)
        if (StringUtils.isBlank(productName)) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank product name.")
        }
        productName
    }

    private String readBarcode(HSSFRow currentRow) {
        if (currentRow.getCell(BARCODE_COLUMN).cellType != Cell.CELL_TYPE_STRING) {
            throw new CatalogueLoadingException("One or more barcode is not in textual format.")
        }
        String barcode = currentRow.getCell(BARCODE_COLUMN).toString()
        if (StringUtils.isBlank(barcode)) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank barcode.")
        }
        barcode
    }

    private void validateRow(HSSFRow currentRow) {
        if (currentRow.getCell(BARCODE_COLUMN) == null) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank barcode.")
        }
        if (currentRow.getCell(PRODUCT_NAME_COLUMN) == null) {
            throw new InvalidProductException("Row ${currentRow.rowNum + 1} has blank product name.")
        }
    }

    void addProductToCatalogue(Product product) {
        if (catalogue.containsKey(product.barcode)) {
            throw new NonUniqueProductException(product.barcode)
        }
        catalogue[product.barcode] = product
    }

    void clear() {
        catalogue.clear()
    }

    int size() {
        catalogue.size()
    }

    @Override
    String getItemName(String barcode) {
        return catalogue.get(barcode)?.name
    }
}
