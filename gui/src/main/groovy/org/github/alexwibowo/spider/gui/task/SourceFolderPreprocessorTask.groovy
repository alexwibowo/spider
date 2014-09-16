package org.github.alexwibowo.spider.gui.task

import groovy.io.FileType
import org.github.alexwibowo.spider.gui.BarcodeSpiderMainFrame
import org.github.alexwibowo.spider.gui.model.FileEntry
import org.github.alexwibowo.spider.gui.model.InputFilesPreProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.JOptionPane
import javax.swing.SwingWorker

import static javax.swing.JOptionPane.showMessageDialog

/**
 * Worker that processes an input directory, and returning
 * a List of {@link FileEntry} for processing.
 * <p/>
 * User: alexwibowo
 */
class SourceFolderPreprocessorTask extends SwingWorker<List<FileEntry>, Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SourceFolderPreprocessorTask.class.getName());

    File inputDirectory

    @Override
    protected List<FileEntry> doInBackground() throws Exception {
        setProgress(0)
        List<FileEntry> fileEntries = []
        inputDirectory.eachFile(FileType.FILES) { File file ->
            fileEntries << new FileEntry(file)
        }
        setProgress(50)
        List<FileEntry> preProcessedFiles = new InputFilesPreProcessor().preProcess(fileEntries)
        setProgress(100)
        return preProcessedFiles
    }

    @Override
    protected void done() {
        try {
            get()
        } catch (Exception e) {
            LOGGER.error("An error has occurred while reading source directory [${inputDirectory}]", e)
            String msg = String.format("Unexpected problem: %s",
                    e.getCause().getMessage());
            showMessageDialog(BarcodeSpiderMainFrame.instance(), msg, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
