package org.github.alexwibowo.spider.barcode

import org.github.alexwibowo.spider.BarcodeSpiderException

/**
 * User: alexwibowo
 */
class BarcodeNotFoundException extends BarcodeSpiderException{

    BarcodeNotFoundException() {
        super("Unable to find barcode")
    }
}
