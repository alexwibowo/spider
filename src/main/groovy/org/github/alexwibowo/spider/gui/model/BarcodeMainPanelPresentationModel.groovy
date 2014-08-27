package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.PresentationModel
import com.jgoodies.common.collect.ArrayListModel

/**
 * User: alexwibowo
 */
class BarcodeMainPanelPresentationModel extends PresentationModel<BarcodeSpiderModel>{

    BarcodeMainPanelPresentationModel() {
        super(new BarcodeSpiderModel())
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

    void setOutputLocation(String directory) {
        getBean().setOutputLocation(directory)

    }

}
