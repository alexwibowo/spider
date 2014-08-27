package org.github.alexwibowo.spider.gui

import com.jgoodies.common.collect.ArrayListModel
import groovy.io.FileType
import org.github.alexwibowo.spider.gui.model.FileTableModel

import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * User: alexwibowo
 */
class BarcodeMainPanel extends MainPanel {

    @Override
    protected BarcodeMainPanelPresentationModel buildModel() throws Exception {
        new BarcodeMainPanelPresentationModel()
    }

    @Override
    protected void bind() throws Exception {
        super.bind()
        this.fileTable.setModel(new FileTableModel(getPM().files))
    }

    protected void initEventHandling() {
        super.initEventHandling()
        openFolderButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setLocation(50, 50);
                if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
                    getPM().getBean().getFiles().clear()
                    File selectedDirectory = chooser.getSelectedFile()
                    selectedDirectory.eachFile(FileType.FILES) {
                        getPM().getBean().getFiles().add(it)
                    }
                } else {
                    System.out.println("No Selection ");
                }
            }
        })
    }
}


