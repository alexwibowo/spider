package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model
import com.jgoodies.common.collect.ArrayListModel
import org.github.alexwibowo.spider.BarcodeSpiderException
import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.task.BarcodeProcessingTask
import org.github.alexwibowo.spider.gui.task.ExcelBasedProductCatalogueLoadingTask

import javax.swing.SwingWorker

/**
 * User: alexwibowo
 */
class BarcodeSpiderModel extends Model{

    ArrayListModel<FileEntry> files = new ArrayListModel<>()

    ProductCatalogue productCatalogue

    String productCatalogueFileLocation  //TODO: dont quite like this

    File outputLocation

    BarcodeReader barcodeReader

    String systemMessage = "Idle"

    String getSystemMessage() {
        return systemMessage
    }

    void setSystemMessage(String newValue) {
        String oldValue = getSystemMessage()
        this.systemMessage = newValue
        this.firePropertyChange("systemMessage", oldValue, newValue)
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
        this.firePropertyChange("productCatalogue", oldValue, newValue)
    }

    File getOutputLocation() {
        return outputLocation
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

    void setFiles(ArrayListModel<FileEntry> newValue) {
        ArrayListModel<FileEntry> oldValue = getFiles()
        this.files = newValue
        this.firePropertyChange("files", oldValue, newValue)
    }

    SwingWorker<Integer,Integer> processFiles(Closure closure) {
        SwingWorker<Integer,Integer> worker = new BarcodeProcessingTask(
                inputFiles: files,
                outputLocation: outputLocation,
                barcodeDictionary: productCatalogue,
                barcodeReader: barcodeReader,
                callback: closure
        )
        worker.execute()
        worker
    }

    SwingWorker<ProductCatalogue, Product> loadCatalogue(File sourceFile, Closure closure) {
        SwingWorker<ProductCatalogue, Product> worker = new ExcelBasedProductCatalogueLoadingTask(
                sourceFile:sourceFile,
                callback:closure
        )
        worker.execute()
        worker
    }
}
