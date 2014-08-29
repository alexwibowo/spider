package org.github.alexwibowo.spider

import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.barcode.ZxingBarcodeReader
import org.github.alexwibowo.spider.dictionary.BarcodeDictionary
import org.github.alexwibowo.spider.dictionary.FilebasedBarcodeDictionary


class ApplicationContext {

    BarcodeDictionary barcodeDictionary

    BarcodeReader barcodeReader

    public static final ApplicationContext INSTANCE = new ApplicationContext()

    private ApplicationContext() {
        barcodeDictionary = new FilebasedBarcodeDictionary()
        barcodeReader = new ZxingBarcodeReader()
    }

    static ApplicationContext instance() {
        return INSTANCE
    }
}
