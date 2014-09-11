package org.github.alexwibowo.spider.catalogue

import com.jgoodies.common.collect.ArrayListModel
import com.jgoodies.validation.ValidationResult


interface ProductCatalogue {

    String name()

    void clear();

    int size();

    ArrayListModel<Product> getProductAsList()

    String getItemName(String barcode)

    ValidationResult validate()
}
