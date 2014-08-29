package org.github.alexwibowo.spider.gui

import com.jgoodies.binding.adapter.Bindings
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

        this.fileTable.setModel(getPM().fileTableModel)

        Bindings.bind(this.targetDirectoryTextField, getPM().getModel("outputLocation"))
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
        targetDirectoryBrowseButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser();
                chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                chooser.setLocation(50, 50);
                if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
                    getPM().setOutputLocation(chooser.selectedFile.absolutePath)
                } else {
                    System.out.println("No Selection ");
                }
            }
        })

        processButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                getPM().processFiles()
            }
        })
    }
}


