package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.beans.Model
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils

/**
 * User: alexwibowo
 */
class FileEntry extends Model{
    File file

    Status status

    String itemName

    String barcode

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

    String getBarcode() {
        return barcode
    }

    void setBarcode(String newValue) {
        def oldValue = getBarcode()
        this.barcode = newValue
        this.firePropertyChange("barcode", oldValue, newValue)
    }

    void copyTo(File targetDirectory) {
        def sourceFileExtension = FilenameUtils.getExtension(file.getName())
        def targetFileExtension = sourceFileExtension ? ".${sourceFileExtension}" : ""
        String targetFileName  = "${targetDirectory.absolutePath}${File.separator}${itemName}${targetFileExtension}"
        FileUtils.copyFile(file, new File(targetFileName))
    }
}
