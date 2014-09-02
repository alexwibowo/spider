package org.github.alexwibowo.spider.catalogue

import org.github.alexwibowo.spider.BarcodeSpiderException


class CatalogueLoadingException extends BarcodeSpiderException{
    CatalogueLoadingException(String detailedMessage) {
        super("Failed to load the catalogue. Message: ${detailedMessage}")
    }

    CatalogueLoadingException(String detailedMessage, Throwable throwable) {
        super("Failed to load the catalogue. Message: ${detailedMessage}", throwable)
    }
}
