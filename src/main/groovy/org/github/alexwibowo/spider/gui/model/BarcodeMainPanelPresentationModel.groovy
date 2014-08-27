package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.PresentationModel
import com.jgoodies.common.collect.ArrayListModel

import javax.swing.ListModel

/**
 * User: alexwibowo
 */
class BarcodeMainPanelPresentationModel extends PresentationModel<BarcodeMainPanelModel>{

    BarcodeMainPanelPresentationModel() {
        super(new BarcodeMainPanelModel())
    }

    ArrayListModel<File> getFiles() {
        getBean().getFiles()
    }

    void clearFiles() {
        getFiles().clear()
    }

    void add(File file) {
        getFiles().add(file)
    }

}
