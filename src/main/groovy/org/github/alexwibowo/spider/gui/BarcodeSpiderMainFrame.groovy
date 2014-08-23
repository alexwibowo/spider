package org.github.alexwibowo.spider.gui

import groovy.transform.Synchronized

import javax.swing.*
import java.awt.*

/**
 * User: alexwibowo
 */
class BarcodeSpiderMainFrame extends JFrame{

    private static BarcodeSpiderMainFrame INSTANCE;

    BarcodeSpiderMainFrame(String title) throws HeadlessException {
        super(title)
    }

    @Synchronized
    public static BarcodeSpiderMainFrame instance() {
        if (INSTANCE == null) {
            INSTANCE = new BarcodeSpiderMainFrame("Spider")
            INSTANCE.minimumSize = new Dimension(800,600)
            INSTANCE.preferredSize = new Dimension(1024,768)
            INSTANCE.maximumSize = new Dimension(1024,768)
            INSTANCE.defaultCloseOperation = EXIT_ON_CLOSE
        }
        INSTANCE.initializeContent()
        INSTANCE
    }

    private void initializeContent() {
        BarcodeMainPanel mainPanel = new BarcodeMainPanel()
        this.contentPane.add(mainPanel);
    }
}
