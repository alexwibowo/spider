package org.github.alexwibowo.spider.gui

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.AbstractAction
import javax.swing.JFileChooser
import javax.swing.UIManager
import java.awt.event.ActionEvent


class SelectFileAction extends AbstractAction{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectFileAction.class.getName());

    Closure closure

    SelectFileAction(String name, Closure closure) {
        super(name,UIManager.getIcon("Tree.openIcon"))
        this.closure = closure
    }

    @Override
    void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.fileSelectionMode = JFileChooser.FILES_ONLY
        chooser.setLocation(50, 50);
        if (chooser.showOpenDialog(BarcodeSpiderMainFrame.instance()) == JFileChooser.APPROVE_OPTION) {
            closure.call(chooser.selectedFile)
        } else {
            LOGGER.info("No file was selected");
        }

    }
}
