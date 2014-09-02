package org.github.alexwibowo.spider.catalogue


interface ProductCatalogue {

    String name()

    void clear();

    int size();

    String getItemName(String barcode)
}
