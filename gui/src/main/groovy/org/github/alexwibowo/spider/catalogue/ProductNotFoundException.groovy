package org.github.alexwibowo.spider.catalogue

/**
 * User: alexwibowo
 */
class ProductNotFoundException extends Exception{

    ProductNotFoundException(String barcode) {
        super("Unable to find product with barcode [${barcode}]")
    }
}
