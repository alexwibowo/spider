package org.github.alexwibowo.spider

import com.jgoodies.looks.LookUtils
import com.jgoodies.looks.plastic.PlasticLookAndFeel
import com.jgoodies.looks.plastic.theme.SkyBluer
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
        configureUI()

        barcodeSpiderMainFrame = BarcodeSpiderMainFrame.instance()
        barcodeSpiderMainFrame.pack()
        barcodeSpiderMainFrame.setVisible(true)
    }

    public void configureUI() {

        if (!System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            return;
        }
        System.setProperty("Windows.controlFont", "Tahoma-plain-11");
        System.setProperty("Windows.menuFont", "Tahoma-plain-11");
        System.setProperty("Plastic.controlFont", "Tahoma-plain-11");
        System.setProperty("Plastic.menuFont", "Tahoma-plain-11");

        try {
            PlasticLookAndFeel.setPlasticTheme(new SkyBluer());
        } catch (Exception e) {
        }

        UIManager.put("ClassLoader", LookUtils.class.getClassLoader());
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
        } catch (Exception e) {
        }
    }
}
