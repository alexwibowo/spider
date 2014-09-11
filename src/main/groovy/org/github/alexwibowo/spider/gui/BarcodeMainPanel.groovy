package org.github.alexwibowo.spider.gui

import com.jgoodies.binding.adapter.Bindings
import com.jgoodies.validation.view.ValidationComponentUtils
import org.github.alexwibowo.spider.BarcodeSpiderException
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.model.BarcodeMainPanelPresentationModel
import org.github.alexwibowo.spider.gui.model.FileEntry
import org.github.alexwibowo.spider.gui.model.FileTableModel
import org.github.alexwibowo.spider.gui.model.Status
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableColumnModel
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.util.List
import java.util.concurrent.CancellationException

import static javax.swing.JOptionPane.showMessageDialog

/**
 * User: alexwibowo
 */
class BarcodeMainPanel extends MainPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarcodeMainPanel.class.getName());

    ThreadLocal<SwingWorker> processTask = new ThreadLocal<>()

    @Override
    protected void initGUIState() throws Exception {
        super.initGUIState()
        getPM().validate()
    }

    @Override
    protected BarcodeMainPanelPresentationModel buildModel() throws Exception {
        new BarcodeMainPanelPresentationModel()
    }

    @Override
    protected void initComponents() {
        super.initComponents()
        ValidationComponentUtils.setMandatory(catalogueFileValueLabel, true);
        ValidationComponentUtils.setMandatory(targetDirectoryValueLabel, true);

        getPM().validationResultModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            void propertyChange(PropertyChangeEvent evt) {
                processButton.setEnabled(!getPM().validationResultModel.hasErrors())
            }
        })
    }

    public static class FileEntryTabelRowRenderer extends DefaultTableCellRenderer {
        FileTableModel fileTableModel

        FileEntryTabelRowRenderer() {
            setOpaque(true); //MUST do this for background to show up.
        }

        @Override
        Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            FileEntry fileEntry = fileTableModel.getRow(row)
            if (fileEntry.status == Status.Unprocessed) {
                c.setBackground(Color.WHITE);
            } else if (fileEntry.status == Status.Processing) {
                c.setBackground(Color.ORANGE);
            } else if (fileEntry.status == Status.Processed) {
                c.setBackground(Color.GREEN);
            }
            return c;
        }
    }

    @Override
    protected void bind() throws Exception {
        super.bind()

        this.fileTable.setModel(getPM().fileTableModel)

        TableColumnModel columnModel = this.fileTable.getColumnModel()
        columnModel.getColumn(0).setCellRenderer(new FileEntryTabelRowRenderer(fileTableModel: getPM().fileTableModel))
        columnModel.getColumn(1).setCellRenderer(new FileEntryTabelRowRenderer(fileTableModel: getPM().fileTableModel))
        columnModel.getColumn(2).setCellRenderer(new FileEntryTabelRowRenderer(fileTableModel: getPM().fileTableModel))
        columnModel.getColumn(3).setCellRenderer(new FileEntryTabelRowRenderer(fileTableModel: getPM().fileTableModel))
        columnModel.getColumn(4).setCellRenderer(new FileEntryTabelRowRenderer(fileTableModel: getPM().fileTableModel))

        Bindings.bind(this.catalogueFileValueLabel, getPM().getModel("productCatalogueFileLocation"))
        Bindings.bind(this.targetDirectoryValueLabel, getPM().getModel("outputLocation"))
        Bindings.bind(this.logTextArea, getPM().getModel("systemMessage"))
    }

    protected void initEventHandling() {
        super.initEventHandling()

        LOGGER.debug("Initializing open folder button event handling.");
        openFolderMenuItem.action = new SelectFolderAction("Open folder", { File selectedDirectory ->
            LOGGER.info("Directory ${selectedDirectory} was chosen as input directory");
            SwingWorker<List<FileEntry>, Void> worker = getPM().loadInputDirectory(selectedDirectory)
            worker.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                void propertyChange(PropertyChangeEvent event) {
                    switch (event.getPropertyName()) {
                        case "progress":
                            break;
                        case "state":
                            switch ((SwingWorker.StateValue) event.getNewValue()) {
                                case SwingWorker.StateValue.PENDING:
                                    break
                                case SwingWorker.StateValue.STARTED:
                                    getPM().clearFiles()
                                    break;
                                case SwingWorker.StateValue.DONE:
                                    List<FileEntry> preProcessed = worker.get()
                                    getPM().setFiles(preProcessed)
                                    getPM().appendSystemMessage("Queued ${preProcessed.size()} images for processing.")
                                    break;
                            }
                    }
                }
            });
        })

        controlFileBrowseButton.action = new SelectFileAction({ File selectedFile ->
            LOGGER.info("File ${selectedFile} was chosen as the catalogue file.");
            SwingWorker<ProductCatalogue, Product> worker = getPM().loadCatalogue(selectedFile)
            worker.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                void propertyChange(PropertyChangeEvent event) {
                    switch (event.getPropertyName()) {
                        case "progress":
                            break;
                        case "state":
                            switch ((SwingWorker.StateValue) event.getNewValue()) {
                                case SwingWorker.StateValue.PENDING:
                                    break
                                case SwingWorker.StateValue.STARTED:
                                    break;
                                case SwingWorker.StateValue.DONE:
                                    ProductCatalogue productCatalogue = worker.get();
                                    getPM().getBean().setProductCatalogue(productCatalogue)
                                    getPM().getBean().setProductCatalogueFileLocation(selectedFile.getAbsolutePath())
                                    getPM().appendSystemMessage("Loaded ${productCatalogue.size()} products.")
                                    showMessageDialog(BarcodeMainPanel.this.getParent(),
                                            "Successfully loaded ${productCatalogue.size()} products from catalogue ${selectedFile}.",
                                            "Catalogue loaded",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    break;
                            }
                    }
                }
            })
        })


        targetDirectoryBrowseButton.action = new SelectFolderAction("Select", { File selectedDirectory ->
            LOGGER.info("Directory ${selectedDirectory} was chosen as output directory");
            try {
                getPM().outputLocation = selectedDirectory.absolutePath
            } catch (BarcodeSpiderException e) {
                showMessageDialog(BarcodeSpiderMainFrame.instance(),
                        e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                                        getPM().resetInputFilesStatus()
                                        processButton.setEnabled(false)
                                        stopButton.setEnabled(true)
                                        break
                                    case SwingWorker.StateValue.DONE:
                                        processButton.setEnabled(true)
                                        stopButton.setEnabled(false)

                                        try {
                                            int totalProcessedFiles = worker.get();
                                            showMessageDialog(BarcodeMainPanel.this.getParent(),
                                                    "Successfully processed ${totalProcessedFiles} files",
                                                    "Process files",
                                                    JOptionPane.INFORMATION_MESSAGE
                                            );
                                        } catch (final CancellationException ex) {
                                            showMessageDialog(BarcodeMainPanel.this.getParent(),
                                                    "Process interrupted",
                                                    "Process files",
                                                    JOptionPane.WARNING_MESSAGE
                                            );
                                        } catch (final Exception ex) {
                                            showMessageDialog(BarcodeMainPanel.this.getParent(),
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


