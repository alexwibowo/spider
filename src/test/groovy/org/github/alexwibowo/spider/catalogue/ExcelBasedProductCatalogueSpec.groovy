package org.github.alexwibowo.spider.catalogue

import spock.lang.Specification


class ExcelBasedProductCatalogueSpec extends Specification {
    private ExcelBasedProductCatalogue catalogue

    def setup() {
        catalogue = new ExcelBasedProductCatalogue()
    }

    def "should be able to read the catalogue in XLS format"() {
        when: "reading the catalogue"
        catalogue.load(Thread.currentThread().contextClassLoader.getResourceAsStream("catalogue.xls"))

        then: "catalogue should have all the entries"
        assert catalogue.size() == 3
    }

    def "should be able to read the catalogue in XLSX format"() {

    }

    def "should fail when given catalogue without the barcode column"() {

    }

    def "should fail when given catalogue without the product name column"() {

    }

    def "should fail when given catalogue with missing barcode entry"() {
        when: "reading the catalogue"
        catalogue.load(Thread.currentThread().contextClassLoader.getResourceAsStream("catalogue-with-missing-barcode.xls"))

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Row 2 has blank barcode."

    }

    def "should fail when given catalogue with missing product name entry"() {
        when: "reading the catalogue"
        catalogue.load(Thread.currentThread().contextClassLoader.getResourceAsStream("catalogue-with-missing-product-name.xls"))

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Row 2 has blank product name."
    }

    def "should fail when given catalogue with duplicated barcode"() {
        when: "reading the catalogue"
        catalogue.load(Thread.currentThread().contextClassLoader.getResourceAsStream("catalogue-with-non-unique-barcode.xls"))

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Catalogue contains multiple product with the same barcode [51651651634]."
    }

    def "should be able to find a specific product in the catalogue"() {
        when: "reading the catalogue"
        catalogue.load(Thread.currentThread().contextClassLoader.getResourceAsStream("catalogue.xls"))

        then: "catalogue should have all the entries"
        assert catalogue.getItemName("4905524449563") == "Special Toaster"
    }

    def "should return null when unable to find product in the catalogue"() {
        when: "reading the catalogue"
        catalogue.load(Thread.currentThread().contextClassLoader.getResourceAsStream("catalogue.xls"))

        then: "catalogue should have all the entries"
        assert catalogue.getItemName("AAAAAA") == null

    }


}
