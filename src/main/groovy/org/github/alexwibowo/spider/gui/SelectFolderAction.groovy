package org.github.alexwibowo.spider.gui

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.AbstractAction
import javax.swing.JFileChooser
import java.awt.event.ActionEvent


class SelectFolderAction extends AbstractAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectFolderAction.class.getName());
    Closure closure

    SelectFolderAction(Closure closure) {
        super("Open")
        this.closure = closure
    }

    @Override
    void actionPerformed(ActionEvent e) {
        LOGGER.debug("Open source folder was clicked");
        JFileChooser chooser = new JFileChooser();
        chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        chooser.setLocation(50, 50);
        if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
            closure.call(chooser.selectedFile)
        } else {
            LOGGER.info("No selection");
        }
    }
}
