package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model
import com.jgoodies.common.collect.ArrayListModel
import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.dictionary.BarcodeDictionary

import javax.swing.SwingWorker

/**
 * User: alexwibowo
 */
class BarcodeSpiderModel extends Model{

    ArrayListModel<FileEntry> files = new ArrayListModel<>()

    BarcodeDictionary barcodeDictionary

    String outputLocation

    BarcodeReader barcodeReader

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

    SwingWorker processFiles(Closure closure) {
        SwingWorker worker = new BarcodeProcessingTask(inputFiles: files,
                barcodeDictionary: barcodeDictionary,
                barcodeReader: barcodeReader,
                callback: closure
        )
        worker.execute()
        worker
    }
}
