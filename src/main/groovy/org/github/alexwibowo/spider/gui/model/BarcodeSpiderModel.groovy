package org.github.alexwibowo.spider.gui.model

import com.google.common.base.Joiner
import com.jgoodies.binding.beans.Model
import com.jgoodies.common.collect.ArrayListModel
import com.jgoodies.validation.Validatable
import com.jgoodies.validation.ValidationResult
import org.github.alexwibowo.spider.BarcodeSpiderException
import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.task.BarcodeProcessingTask
import org.github.alexwibowo.spider.gui.task.ExcelBasedProductCatalogueLoadingTask
import org.github.alexwibowo.spider.gui.task.SourceFolderPreprocessorTask

import javax.swing.*

/**
 * User: alexwibowo
 */
class BarcodeSpiderModel extends Model implements Validatable {

    public static final String PROPERTYNAME_FILES = "files"
    ArrayListModel<FileEntry> files = new ArrayListModel<>()

    public static final String PROPERTYNAME_PRODUCT_CATALOGUE ="productCatalogue"
    ProductCatalogue productCatalogue

    String productCatalogueFileLocation  //TODO: dont quite like this

    public static final String PROPERTYNAME_OUTPUT_LOCATION ="outputLocation"
    File outputLocation

    BarcodeReader barcodeReader

    public static final String PROPERTYNAME_SYSTEM_MESSAGE ="systemMessage"
    String systemMessage = "Idle"

    public static final List<String> VALIDATEABLE_PROPERTIES = [PROPERTYNAME_PRODUCT_CATALOGUE, PROPERTYNAME_OUTPUT_LOCATION]

    String getSystemMessage() {
        return systemMessage
    }

    void setSystemMessage(String newValue) {
        String oldValue = getSystemMessage()
        this.systemMessage = newValue
        this.firePropertyChange(PROPERTYNAME_SYSTEM_MESSAGE, oldValue, newValue)
    }

    ProductCatalogue getProductCatalogue() {
        return productCatalogue
    }

    String getProductCatalogueFileLocation() {
        return productCatalogueFileLocation
    }

    void setProductCatalogueFileLocation(String newValue) {
        def oldValue = getProductCatalogueFileLocation()
        this.productCatalogueFileLocation = newValue
        this.firePropertyChange("productCatalogueFileLocation", oldValue, newValue)
    }

    void setProductCatalogue(ProductCatalogue newValue) {
        def oldValue = getProductCatalogue()
        this.productCatalogue = newValue
        this.firePropertyChange(PROPERTYNAME_PRODUCT_CATALOGUE, oldValue, newValue)
    }

    File getOutputLocation() {
        return outputLocation
    }

    void resetInputFilesStatus() {
        files*.resetStatus()
    }

    void setOutputLocation(String newValue) {
        String oldValue = getOutputLocation()
        File outputDirectory = new File(newValue)
        validateOutputDirectory(outputDirectory)
        this.outputLocation = outputDirectory
        this.firePropertyChange("outputLocation", oldValue, newValue)
    }

    // TODO: maybe do this using JGoodies validation...
    void validateOutputDirectory(File directory) {
        if (!directory.isDirectory()) {
            throw new BarcodeSpiderException("Target directory must be a directory")
        }
        if (!directory.exists()) {
            throw new BarcodeSpiderException("Target directory must exists")
        }
        if (!directory.canWrite()) {
            throw new BarcodeSpiderException("Target directory must be writeable")
        }
    }

    ArrayListModel<FileEntry> getFiles() {
        return files
    }

    void setFiles(List<FileEntry> newValue) {
        newValue.each { FileEntry fileEntry ->
            files.add(fileEntry)
        }
    }

    SwingWorker<Integer, Integer> processFiles(Closure closure) {
        SwingWorker<Integer, Integer> worker = new BarcodeProcessingTask(
                inputFiles: files,
                outputLocation: outputLocation,
                barcodeDictionary: productCatalogue,
                barcodeReader: barcodeReader,
                callback: closure
        )
        worker.execute()
        worker
    }

    SwingWorker<List<FileEntry>, Void> loadInputDirectory(File inputDirectory) {
        SwingWorker<List<FileEntry>, Void> worker = new SourceFolderPreprocessorTask(
                inputDirectory: inputDirectory
        )
        worker.execute()
        worker
    }

    SwingWorker<ProductCatalogue, Product> loadCatalogue(File sourceFile, Closure closure) {
        SwingWorker<ProductCatalogue, Product> worker = new ExcelBasedProductCatalogueLoadingTask(
                sourceFile: sourceFile,
                callback: closure
        )
        worker.execute()
        worker
    }

    @Override
    ValidationResult validate() {
        def validationResult = new ValidationResult()

        if (files.isEmpty()) {
            validationResult.addError("No files queued to be processed.")
        }
        if (outputLocation == null) {
            validationResult.addError("Output location is not provided.")
        } else {
            if (!outputLocation.exists()) {
                validationResult.addError("Output location does not exist.")
            }
            if (!outputLocation.canWrite()) {
                validationResult.addError("Output location is not writeable.")
            }
        }



        if (productCatalogue == null) {
            validationResult.addError("Product catalogue is not provided.")
        }else{
            def productCatalogueValidationResult = productCatalogue.validate()
            validationResult.addAllFrom(productCatalogueValidationResult)
        }



        return validationResult
    }

    void appendSystemMessage(String systemMessage) {
        def oldValue = getSystemMessage()
        String newSystemMessage = Joiner.on("\n").join(oldValue, systemMessage)
        setSystemMessage(newSystemMessage)
    }
}
