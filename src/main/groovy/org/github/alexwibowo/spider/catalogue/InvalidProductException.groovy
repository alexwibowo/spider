package org.github.alexwibowo.spider.catalogue

class InvalidProductException extends CatalogueLoadingException{

    InvalidProductException(String message) {
        super(message)
    }
}
