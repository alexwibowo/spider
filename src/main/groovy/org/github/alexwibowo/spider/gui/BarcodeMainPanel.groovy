package org.github.alexwibowo.spider.gui

import com.jgoodies.binding.adapter.Bindings
import groovy.io.FileType
import org.github.alexwibowo.spider.gui.model.BarcodeMainPanelPresentationModel
import org.github.alexwibowo.spider.gui.model.FileEntry
import org.github.alexwibowo.spider.gui.model.InputFilesPreProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
    private static final Logger LOGGER = LoggerFactory.getLogger(BarcodeMainPanel.class.getName());

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

        LOGGER.debug("Initializing open folder button event handling.");
        openFolderButton.action = new SelectFolderAction({ File selectedDirectory ->
            LOGGER.info("Directory ${selectedDirectory} was chosen as input directory");
            getPM().clearFiles()
            List<FileEntry> fileEntries = []
            selectedDirectory.eachFile(FileType.FILES) { File file ->
                fileEntries << new FileEntry(file)
            }
            def preProcessed = new InputFilesPreProcessor().preProcess(fileEntries)
            preProcessed.each { FileEntry fileEntry ->
                LOGGER.info("Adding ${fileEntry.file} as file to be processed");
                getPM().add(fileEntry)
            }
        })

        targetDirectoryBrowseButton.action = new SelectFolderAction({ File selectedDirectory ->
            LOGGER.info("Directory ${selectedDirectory} was chosen as output directory");
            getPM().outputLocation = selectedDirectory.absolutePath
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


