package org.github.alexwibowo.spider.catalogue

import spock.lang.Specification


class ExcelBasedProductCatalogueSpec extends Specification {
    private ExcelBasedProductCatalogue catalogue

    def setup() {
        catalogue = new ExcelBasedProductCatalogue()
    }

    private File getTestInputFile(String filename) {
        new File(Thread.currentThread().contextClassLoader.getResource(filename).toURI())
    }

    def "should throw CatalogueLoadingException when loading corrupted excel file"() {
        given:
        def file = getTestInputFile("corrupted-catalogue.xls")

        when: "reading corrupted catalogue"
        catalogue.load file

        then:
        CatalogueLoadingException ex = thrown()
        ex.message == "Failed to load the catalogue. Message: Error encountered while reading [${file.absolutePath}]."
    }

    def "should return absolute file path as the catalogue name"() {
        given:
        def inputFile = getTestInputFile("catalogue.xls")
        when: "reading the catalogue"
        catalogue.load inputFile

        then: "catalogue should have all the entries"
        assert catalogue.name() == inputFile.absolutePath

    }

    def "should be able to read the catalogue in XLS format"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue.xls")

        then: "catalogue should have all the entries"
        assert catalogue.size() == 3
    }

    def "should be able to read the catalogue in XLSX format"() {

    }

    def "should fail when given catalogue without the barcode column"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue-with-no-barcode-column.xls")

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Row 1 has blank barcode."

    }

    def "should fail when given catalogue without the product name column"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue-with-no-product-name-column.xls")

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Row 1 has blank product name."
    }

    def "should fail when given catalogue with missing barcode entry"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue-with-missing-barcode.xls")

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Row 2 has blank barcode."

    }

    def "should fail when given catalogue with missing product name entry"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue-with-missing-product-name.xls")

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Row 2 has blank product name."
    }

    def "should fail when given catalogue with duplicated barcode"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue-with-non-unique-barcode.xls")

        then: "should have failed"
        CatalogueLoadingException ex = thrown()
        assert ex.message == "Failed to load the catalogue. Message: Catalogue contains multiple product with the same barcode [51651651634]."
    }

    def "should be able to find a specific product in the catalogue"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue.xls")

        then: "catalogue should have all the entries"
        assert catalogue.getItemName("4905524449563") == "Special Toaster"
    }

    def "should return null when unable to find product in the catalogue"() {
        when: "reading the catalogue"
        catalogue.load getTestInputFile("catalogue.xls")

        then: "catalogue should have all the entries"
        assert catalogue.getItemName("AAAAAA") == null

    }


}
