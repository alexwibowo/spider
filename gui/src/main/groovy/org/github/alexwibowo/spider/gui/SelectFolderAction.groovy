package org.github.alexwibowo.spider.gui

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.AbstractAction
import javax.swing.ImageIcon
import javax.swing.JFileChooser
import java.awt.event.ActionEvent


class SelectFolderAction extends AbstractAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectFolderAction.class.getName());
    Closure closure

    SelectFolderAction(String actionName, ImageIcon icon, Closure closure) {
        super(actionName, icon)
        this.closure = closure
    }

    @Override
    void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        chooser.setLocation(50, 50);
        if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
            closure.call(chooser.selectedFile)
        } else {
            LOGGER.info("No folder was selected");
        }
    }
}
