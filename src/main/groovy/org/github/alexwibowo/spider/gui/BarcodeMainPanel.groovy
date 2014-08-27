package org.github.alexwibowo.spider.gui

import groovy.io.FileType
import org.github.alexwibowo.spider.gui.model.BarcodeMainPanelPresentationModel
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

        def fileTableModel = new FileTableModel(getPM().files)
        this.fileTable.setModel(fileTableModel)
    }

    protected void initEventHandling() {
        super.initEventHandling()
        openFolderButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser();
                chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                chooser.setLocation(50, 50);
                if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
                    getPM().clearFiles()
                    chooser.selectedFile.eachFile(FileType.FILES) { File file ->
                        getPM().add(file)
                    }
                } else {
                    System.out.println("No Selection ");
                }
            }
        })
    }
}


