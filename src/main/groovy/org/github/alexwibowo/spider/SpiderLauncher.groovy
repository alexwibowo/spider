package org.github.alexwibowo.spider

import org.github.alexwibowo.spider.gui.BarcodeSpiderMainFrame

import javax.swing.*

/**
 * User: alexwibowo
 */
@Singleton
class SpiderLauncher {
    BarcodeSpiderMainFrame barcodeSpiderMainFrame

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SpiderLauncher.instance.run(args);
            }
        });
    }

    void run(String[] args) {
        barcodeSpiderMainFrame = BarcodeSpiderMainFrame.instance()
        barcodeSpiderMainFrame.pack()
        barcodeSpiderMainFrame.setVisible(true)
    }
}
