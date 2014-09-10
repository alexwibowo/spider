package org.github.alexwibowo.spider.catalogue

import com.jgoodies.validation.ValidationResult


interface ProductCatalogue {

    String name()

    void clear();

    int size();

    String getItemName(String barcode)

    ValidationResult validate()
}
