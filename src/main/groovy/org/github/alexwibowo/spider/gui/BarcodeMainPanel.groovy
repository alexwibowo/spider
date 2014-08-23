package org.github.alexwibowo.spider.gui

import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * User: alexwibowo
 */
class BarcodeMainPanel extends MainPanel {
    private DefaultListModel<String> listModel = new DefaultListModel<String>()

    BarcodeMainPanel() {
        initEventHandling()
        initModels()
    }

    protected void initModels() {
        fileList.model = listModel
    }

    protected void initEventHandling() {
        addButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                listModel.addElement("Hhahahah")
            }
        })

        clearButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                listModel.clear()
            }
        })

        openFolderButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.setLocation(50, 50);
                        if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
                            listModel.clear()
                            File selectedDirectory = chooser.getSelectedFile()
                            selectedDirectory.eachFile {
                                listModel.addElement(it.name)
                            }
//                            new DirectoryScanner(startingDirectory: chooser.getSelectedFile()).execute()
                        } else {
                            System.out.println("No Selection ");
                        }
                    }
        })
    }

/*
    private class DirectoryScanner extends SwingWorker<Void, File>{

        File startingDirectory

        @Override
        protected Void doInBackground() throws Exception {

            startingDirectory.eachFile {
                publish(it)
            }
            Void.newInstance()
        }

        @Override
        protected void process(List<File> chunks) {
            for (File file : chunks) {
                listModel.addElement(file.name)
            }
        }
    }
*/
}


