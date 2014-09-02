package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model
import com.jgoodies.common.collect.ArrayListModel
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

    String productCatalogueFileLocation

    String outputLocation

    BarcodeReader barcodeReader

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

    String getOutputLocation() {
        return outputLocation
    }

    void setOutputLocation(String newValue) {
        String oldValue = getOutputLocation()
        this.outputLocation = newValue
        this.firePropertyChange("outputLocation", oldValue, newValue)
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
        SwingWorker<Integer,Integer> worker = new BarcodeProcessingTask(inputFiles: files,
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
