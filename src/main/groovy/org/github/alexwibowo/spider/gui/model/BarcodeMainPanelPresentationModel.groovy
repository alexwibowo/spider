package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.PresentationModel
import com.jgoodies.common.collect.ArrayListModel
import com.jgoodies.validation.Validatable
import com.jgoodies.validation.ValidationResult
import com.jgoodies.validation.ValidationResultModel
import com.jgoodies.validation.util.DefaultValidationResultModel
import org.github.alexwibowo.spider.ApplicationContext
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue

import javax.swing.SwingWorker
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

/**
 * User: alexwibowo
 */
class BarcodeMainPanelPresentationModel extends PresentationModel<BarcodeSpiderModel> {

    FileTableModel fileTableModel

     ValidationResultModel validationResultModel;


    BarcodeMainPanelPresentationModel() {
        super(new BarcodeSpiderModel(
                barcodeReader: ApplicationContext.instance().barcodeReader,
                productCatalogue: ApplicationContext.instance().barcodeDictionary
        ))
        validationResultModel = new DefaultValidationResultModel();
        fileTableModel = new FileTableModel(getFiles())

        addBeanPropertyChangeListener(new PropertyChangeListener() {
            @Override
            void propertyChange(PropertyChangeEvent evt) {
                if (BarcodeSpiderModel.VALIDATEABLE_PROPERTIES.contains(evt.propertyName)) {
                    validate()
                }
            }
        });
    }

    void validate() {
        def validationResult = getBean().validate()
        validationResultModel.setResult(validationResult);
    }

    ArrayListModel<FileEntry> getFiles() {
        getBean().getFiles()
    }

    void clearFiles() {
        getFiles().clear()
    }

    void add(File file) {
        getFiles().add(new FileEntry(file))
    }

    void add(FileEntry fileEntry) {
        getFiles().add(fileEntry)
    }


    void setOutputLocation(String directory) {
        getBean().setOutputLocation(directory)
    }

    SwingWorker<Integer,Integer> processFiles() {
        getBean().processFiles() { index ->
            fileTableModel.fireTableRowsUpdated(index, index)
        }
    }

    SwingWorker<ProductCatalogue, Product> loadCatalogue(File file) {
        getBean().loadCatalogue(file) { Product loadedProduct ->
           getBean().setSystemMessage("Loaded product: '${loadedProduct.name}' with barcode '${loadedProduct.barcode}'")
        }
    }
}
