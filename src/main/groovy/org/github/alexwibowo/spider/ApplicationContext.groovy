package org.github.alexwibowo.spider

import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.barcode.ZxingBarcodeReader
import org.github.alexwibowo.spider.catalogue.FilebasedProductCatalogue
import org.github.alexwibowo.spider.catalogue.InMemoryProductCatalogue
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.model.InputFilesPreProcessor


class ApplicationContext {

    ProductCatalogue barcodeDictionary

    BarcodeReader barcodeReader

    InputFilesPreProcessor inputFilesPreProcessor

    public static final ApplicationContext INSTANCE = new ApplicationContext()

    private ApplicationContext() {
        barcodeDictionary = new InMemoryProductCatalogue()
        barcodeReader = new ZxingBarcodeReader()
        inputFilesPreProcessor = new InputFilesPreProcessor()
    }

    static ApplicationContext instance() {
        return INSTANCE
    }
}
