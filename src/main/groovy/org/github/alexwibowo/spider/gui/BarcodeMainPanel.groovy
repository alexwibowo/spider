package org.github.alexwibowo.spider.gui

import com.jgoodies.binding.adapter.Bindings
import groovy.io.FileType
import javafx.application.Application
import org.github.alexwibowo.spider.gui.model.BarcodeMainPanelPresentationModel
import org.github.alexwibowo.spider.gui.model.FileEntry
import org.github.alexwibowo.spider.gui.model.FileTableModel
import org.github.alexwibowo.spider.gui.model.InputFilesPreProcessor

import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.util.concurrent.CancellationException

/**
 * User: alexwibowo
 */
class BarcodeMainPanel extends MainPanel {

    ThreadLocal<SwingWorker> processTask = new ThreadLocal<>()

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

                    List<FileEntry> fileEntries = []
                    chooser.selectedFile.eachFile(FileType.FILES) { File file ->
                        fileEntries << new FileEntry(file)
                    }

                    def preProcessed = new InputFilesPreProcessor().preProcess(fileEntries)
                    preProcessed.each {
                        getPM().add(it)
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
                SwingWorker<Integer, Integer> worker = getPM().processFiles()
                worker.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    void propertyChange(PropertyChangeEvent event) {
                        switch (event.getPropertyName()) {
                            case "progress":
                                processProgressBar.setIndeterminate(false);
                                processProgressBar.setValue((Integer) event.getNewValue());
                                break;
                            case "state":
                                switch ((SwingWorker.StateValue) event.getNewValue()) {
                                    case SwingWorker.StateValue.PENDING:
                                        break
                                    case SwingWorker.StateValue.STARTED:
                                        processButton.setEnabled(false)
                                        stopButton.setEnabled(true)
                                        break
                                    case SwingWorker.StateValue.DONE:
                                        processButton.setEnabled(true)
                                        stopButton.setEnabled(false)

                                        try {
                                            int totalProcessedFiles = worker.get();
                                            JOptionPane.showMessageDialog(BarcodeMainPanel.this.getParent(),
                                                    "Successfully processed ${totalProcessedFiles} files",
                                                    "Process files",
                                                    JOptionPane.INFORMATION_MESSAGE
                                            );
                                        } catch (final CancellationException ex) {
                                            JOptionPane.showMessageDialog(BarcodeMainPanel.this.getParent(),
                                                    "Process interrupted",
                                                    "Process files",
                                                    JOptionPane.WARNING_MESSAGE
                                            );
                                        } catch (final Exception ex) {
                                            JOptionPane.showMessageDialog(BarcodeMainPanel.this.getParent(),
                                                    "An error has occurred", "Process files",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                        break
                                }
                                break;
                        }
                    }
                })
                processTask.set(worker)
            }
        })

        stopButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                processTask.get().cancel(true)

            }
        })
    }
}


