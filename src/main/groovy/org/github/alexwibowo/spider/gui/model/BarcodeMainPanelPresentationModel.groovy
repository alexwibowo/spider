package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.PresentationModel
import com.jgoodies.common.collect.ArrayListModel
import org.github.alexwibowo.spider.ApplicationContext

import javax.swing.SwingWorker

/**
 * User: alexwibowo
 */
class BarcodeMainPanelPresentationModel extends PresentationModel<BarcodeSpiderModel>{

    FileTableModel fileTableModel

    BarcodeMainPanelPresentationModel() {
        super(new BarcodeSpiderModel(
                barcodeReader: ApplicationContext.instance().barcodeReader,
                barcodeDictionary: ApplicationContext.instance().barcodeDictionary
        ))
        fileTableModel = new FileTableModel(getFiles())
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

    void setOutputLocation(String directory) {
        getBean().setOutputLocation(directory)
    }

    void processFiles() {
        getBean().processFiles() { index ->
            fileTableModel.fireTableRowsUpdated(index, index)
        }
    }

}
