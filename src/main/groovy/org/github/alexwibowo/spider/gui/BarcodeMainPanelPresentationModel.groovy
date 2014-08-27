package org.github.alexwibowo.spider.gui

import com.jgoodies.binding.PresentationModel

import javax.swing.ListModel

/**
 * User: alexwibowo
 */
class BarcodeMainPanelPresentationModel extends PresentationModel<BarcodeMainPanelModel>{

    BarcodeMainPanelPresentationModel() {
        super(new BarcodeMainPanelModel())
    }

    ListModel<File> getFiles() {
        getBean().files
    }

}
