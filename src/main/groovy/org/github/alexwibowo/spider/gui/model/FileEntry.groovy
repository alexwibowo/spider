package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model

/**
 * User: alexwibowo
 */
class FileEntry extends Model{
    File file

    Status status

    String itemName

    FileEntry(File file) {
        this.file = file
        this.status = Status.Unprocessed
    }

    File getFile() {
        return file
    }

    void setFile(File newValue) {
        File oldValue = getFile()
        this.file = newValue
        this.firePropertyChange("file", oldValue, newValue)
    }

    Status getStatus() {
        return status
    }

    void setStatus(Status newValue) {
        Status oldValue = getStatus()
        this.status = newValue
        this.firePropertyChange("status", oldValue, newValue)
    }

    String getItemName() {
        return itemName
    }

    void setItemName(String newValue) {
        def oldValue = getItemName()
        this.itemName = newValue
        this.firePropertyChange("itemName", oldValue, newValue)
    }

    void renameFile() {
        
    }
}