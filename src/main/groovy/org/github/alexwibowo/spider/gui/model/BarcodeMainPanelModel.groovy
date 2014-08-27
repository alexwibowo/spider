package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model
import com.jgoodies.common.collect.ArrayListModel

/**
 * User: alexwibowo
 */
class BarcodeMainPanelModel extends Model{

    ArrayListModel<File> files = new ArrayListModel<>()


    ArrayListModel<File> getFiles() {
        return files
    }

    void setFiles(ArrayListModel<File> newValue) {
        ArrayListModel<File> oldValue = getFiles()
        this.files = newValue
        this.firePropertyChange("files", oldValue, newValue)
    }
}
