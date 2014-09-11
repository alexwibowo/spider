package org.github.alexwibowo.spider.catalogue

import com.google.common.collect.ImmutableMap
import com.jgoodies.common.collect.ArrayListModel
import com.jgoodies.validation.ValidationResult

/**
 * User: alexwibowo
 */
class DemoProductCatalogue implements ProductCatalogue {

    Map<String, String> catalogue = ImmutableMap.<String, String> builder()
            .put("51651651634", "Best Printer")
            .put("2165165412655", "Best Printer")
            .put("4905524449563", "Nice Shoe")
            .put("072700000420", "Pareve")
            .put("77260002051", "Russel Stover")
            .build()

    @Override
    String name() {
        ""

    }

    @Override
    void clear() {
        throw new UnsupportedOperationException()
    }

    @Override
    int size() {
        return catalogue.size()
    }

    @Override
    ArrayListModel<Product> getProductAsList() {
        return null
    }

    @Override
    String getItemName(String barcode) {
        def productName = catalogue.get(barcode)
        if (productName) {
            return productName
        }
        return null
    }

    @Override
    ValidationResult validate() {
        return new ValidationResult()
    }
}
