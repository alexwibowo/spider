package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model
import com.jgoodies.common.collect.ArrayListModel

/**
 * User: alexwibowo
 */
class BarcodeSpiderModel extends Model{

    ArrayListModel<FileEntry> files = new ArrayListModel<>()

    String outputLocation

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
}
