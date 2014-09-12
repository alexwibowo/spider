package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.PresentationModel
import com.jgoodies.common.collect.ArrayListModel
import com.jgoodies.validation.ValidationResultModel
import com.jgoodies.validation.util.DefaultValidationResultModel
import org.github.alexwibowo.spider.ApplicationContext
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue

import javax.swing.SwingWorker
import javax.swing.event.TableModelEvent
import javax.swing.event.TableModelListener
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

/**
 * User: alexwibowo
 */
class BarcodeMainPanelPresentationModel extends PresentationModel<BarcodeSpiderModel> {

    QueuedImageFilesTableModel fileTableModel

    CatalogueTableModel catalogueTableModel

    ValidationResultModel validationResultModel

    BarcodeMainPanelPresentationModel() {
        super(new BarcodeSpiderModel(
                barcodeReader: ApplicationContext.instance().barcodeReader,
                productCatalogue: ApplicationContext.instance().productCatalogue
        ))
        validationResultModel = new DefaultValidationResultModel()
        fileTableModel = new QueuedImageFilesTableModel(getFiles())
        catalogueTableModel = new CatalogueTableModel(getCatalogueProducts())

        addBeanPropertyChangeListener(new PropertyChangeListener() {
            @Override
            void propertyChange(PropertyChangeEvent evt) {
                if (BarcodeSpiderModel.VALIDATEABLE_PROPERTIES.contains(evt.propertyName)) {
                    validate()
                }
            }
        });

        fileTableModel.addTableModelListener(new TableModelListener() {
            @Override
            void tableChanged(TableModelEvent e) {
                validate()
            }
        })
    }

    void validate() {
        def validationResult = getBean().validate()
        validationResultModel.setResult(validationResult);
    }

    ArrayListModel<FileEntry> getFiles() {
        getBean().getFiles()
    }
    ArrayListModel<Product> getCatalogueProducts() {
        getBean().getCatalogueProducts()
    }

    void clearFiles() {
        getFiles().clear()
    }

    void appendSystemMessage(String message) {
        getBean().appendSystemMessage(message)
    }

    void clearSystemMessage() {
        getBean().setSystemMessage("")
    }


    void setFiles(List<FileEntry> fileEntries) {
        getBean().setFiles(fileEntries)
    }


    void setOutputLocation(String directory) {
        getBean().setOutputLocation(directory)
    }

    void resetInputFilesStatus() {
        getBean().resetInputFilesStatus()
        fileTableModel.fireTableDataChanged()
    }

    SwingWorker<Integer,Integer> processFiles() {
        getBean().processFiles() { index ->
            fileTableModel.fireTableRowsUpdated(index, index)
            FileEntry updatedFileEntry = getBean().getFileEntryAt(index)
            if (updatedFileEntry.status == Status.Processed) {
                appendSystemMessage("Processed file [${updatedFileEntry.file.name}] as product '${updatedFileEntry.itemName}'")
            }
        }
    }

    SwingWorker loadInputDirectory(File sourceDirectory) {
        getBean().loadInputDirectory(sourceDirectory)
    }

    SwingWorker<ProductCatalogue, Product> loadCatalogue(File file) {
        getBean().loadCatalogue(file) { index ->
            catalogueTableModel.fireTableRowsUpdated(index, index)
            Product loadedProduct = catalogueTableModel.getRow(index)
            appendSystemMessage("Loaded product: '${loadedProduct.name}' with barcode '${loadedProduct.barcode}'")
        }
    }
}
