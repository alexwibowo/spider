package org.github.alexwibowo.spider.gui.task

import org.github.alexwibowo.spider.catalogue.ExcelBasedProductCatalogue
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.BarcodeSpiderMainFrame
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.*
import java.util.concurrent.ExecutionException

import static javax.swing.JOptionPane.showMessageDialog
import static org.apache.commons.io.IOUtils.closeQuietly

class ExcelBasedProductCatalogueLoadingTask extends SwingWorker<ProductCatalogue, Product>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBasedProductCatalogueLoadingTask.class.getName());

    File sourceFile

    Closure callback

    @Override
    protected ProductCatalogue doInBackground() throws Exception {
        def catalogue = new ExcelBasedProductCatalogue()
        LOGGER.info("About to load catalogue from [${sourceFile.absolutePath}");
        setProgress(0)     // make sure we reach 100% at the end
        catalogue.load(sourceFile) { Product product ->
            publish(product)
        }
        setProgress(100)     // make sure we reach 100% at the end
        LOGGER.info("Finished reading catalogue from [${sourceFile.absolutePath}]");
        return catalogue
    }

    @Override
    protected void process(List<Product> chunks) {
        chunks.each {
            callback.call(it)
        }
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (ExecutionException e) {
            LOGGER.error("An error has occurred while loading catalogue",e)
            String msg = String.format("Unexpected problem: %s",
                           e.getCause().getMessage());
            showMessageDialog(BarcodeSpiderMainFrame.instance(), msg, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException e) {
            LOGGER.info("Work was interrupted");
            // Process e here
        }
    }
}
