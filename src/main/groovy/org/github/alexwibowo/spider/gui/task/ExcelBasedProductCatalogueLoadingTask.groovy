package org.github.alexwibowo.spider.gui.task

import org.apache.commons.io.IOUtils
import org.github.alexwibowo.spider.catalogue.ExcelBasedProductCatalogue
import org.github.alexwibowo.spider.catalogue.Product
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.swing.SwingWorker

import static org.apache.commons.io.IOUtils.closeQuietly


class ExcelBasedProductCatalogueLoadingTask extends SwingWorker<ProductCatalogue, Product>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBasedProductCatalogueLoadingTask.class.getName());

    File sourceFile

    Closure callback

    @Override
    protected ProductCatalogue doInBackground() throws Exception {
        def catalogue = new ExcelBasedProductCatalogue()
        def stream = new FileInputStream(sourceFile)
        LOGGER.info("About to load catalogue from [${sourceFile.absolutePath}");
        try {
            catalogue.load(stream) { Product product ->
                publish(product)
            }
            LOGGER.info("Finished reading catalogue from [${sourceFile.absolutePath}]");
            return catalogue
        } finally {
            if (stream) {
                closeQuietly(stream)
            }
        }
    }

    @Override
    protected void process(List<Product> chunks) {
        chunks.each {
            callback.call(it)
        }
    }
}
