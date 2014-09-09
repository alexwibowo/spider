package org.github.alexwibowo.spider.gui.task

import org.github.alexwibowo.spider.catalogue.ExcelBasedProductCatalogue
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.*

import static org.apache.commons.io.IOUtils.closeQuietly

class ExcelBasedProductCatalogueLoadingTask extends SwingWorker<ProductCatalogue, Product>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBasedProductCatalogueLoadingTask.class.getName());

    File sourceFile

    Closure callback

    @Override
    protected ProductCatalogue doInBackground() throws Exception {
        def catalogue = new ExcelBasedProductCatalogue()
        LOGGER.info("About to load catalogue from [${sourceFile.absolutePath}");
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
}
