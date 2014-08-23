package org.github.alexwibowo.spider.gui

import groovy.io.FileType

import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * User: alexwibowo
 */
class BarcodeMainPanel extends MainPanel {
    private DefaultListModel<File> listModel = new DefaultListModel<File>()

    BarcodeMainPanel() {
        initModels()
        initEventHandling()
    }

    protected void initModels() {
        fileList.model = listModel
    }

    protected void initEventHandling() {
        openFolderButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setLocation(50, 50);
                if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
                    listModel.clear()
                    File selectedDirectory = chooser.getSelectedFile()
                    selectedDirectory.eachFile(FileType.FILES) {
                        listModel.addElement(it)
                    }
                } else {
                    System.out.println("No Selection ");
                }
            }
        })
    }
}


