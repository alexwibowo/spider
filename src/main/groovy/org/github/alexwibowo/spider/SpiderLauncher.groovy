package org.github.alexwibowo.spider

import com.jgoodies.looks.LookUtils
import com.jgoodies.looks.Options
import com.jgoodies.looks.plastic.PlasticLookAndFeel
import com.jgoodies.looks.plastic.theme.SkyBluer
import org.github.alexwibowo.spider.gui.BarcodeSpiderMainFrame

import javax.swing.*
import java.awt.*

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
        System.setProperty("jgoodies.looks", "true");

        UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
        PlasticLookAndFeel.setPlasticTheme(new SkyBluer());

        Options.setUseNarrowButtons(false);
        Options.setDefaultIconSize(new Dimension(18, 18));
        Options.setPopupDropShadowEnabled(true);
        Options.setTabIconsEnabled(false); // bug in 1.3.0 looks (false is true!)
        ToolTipManager.sharedInstance().setInitialDelay(200);
        ToolTipManager.sharedInstance().setDismissDelay(20000);


        UIManager.put("ClassLoader", LookUtils.class.getClassLoader());
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
        } catch (Exception e) {
        }

    }
}
