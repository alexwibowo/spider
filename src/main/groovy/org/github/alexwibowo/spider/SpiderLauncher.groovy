package org.github.alexwibowo.spider

import com.jgoodies.looks.plastic.PlasticLookAndFeel
import com.jgoodies.looks.plastic.theme.SkyBluer
import org.github.alexwibowo.spider.gui.BarcodeSpiderMainFrame

import javax.swing.SwingUtilities

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
        configureUI()

        barcodeSpiderMainFrame.show()
    }

    void configureUI() {
        PlasticLookAndFeel.setPlasticTheme(new SkyBluer());
        barcodeSpiderMainFrame = BarcodeSpiderMainFrame.instance()
    }

}
