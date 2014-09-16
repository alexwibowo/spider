package org.github.alexwibowo.spider.catalogue


class NonUniqueProductException extends CatalogueLoadingException{
    NonUniqueProductException(String barcode) {
        super("Catalogue contains multiple product with the same barcode [${barcode}].")
    }
}
