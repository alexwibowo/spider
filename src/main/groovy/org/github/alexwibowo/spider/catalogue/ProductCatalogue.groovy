package org.github.alexwibowo.spider.catalogue


interface ProductCatalogue {

    void clear();

    int size();

    String getItemName(String barcode)
}
