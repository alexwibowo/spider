package org.github.alexwibowo.spider.catalogue

import com.jgoodies.common.collect.ArrayListModel
import com.jgoodies.validation.Validatable
import com.jgoodies.validation.ValidationResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class ProductCatalogue implements Validatable{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogue.class.getName());

    private final Map<String, Product> catalogue

    private final ArrayListModel<Product> productAsList //TODO: yuck.... this has to be kept synced with catalogue

    String name

    ProductCatalogue() {
        catalogue = Collections.synchronizedMap([:])
        productAsList = new ArrayListModel<>()
    }

    void addProductToCatalogue(Product product) {
        if (catalogue.containsKey(product.barcode)) {
            throw new NonUniqueProductException(product.barcode)
        }
        catalogue[product.barcode] = product
        productAsList.add(product)
    }

    void clear() {
        catalogue.clear()
        productAsList.clear()
    }

    int size() {
        assert catalogue.size() == productAsList.size()
        catalogue.size()
    }

    ArrayListModel<Product> getProductAsList() {
        return productAsList
    }

    String getItemName(String barcode) {
        return catalogue.get(barcode)?.name
    }

    ValidationResult validate() {
        ValidationResult validationResult = new ValidationResult()
        if (size() == 0) {
            validationResult.addError("Catalogue is empty")
        }
        return validationResult
    }
}
