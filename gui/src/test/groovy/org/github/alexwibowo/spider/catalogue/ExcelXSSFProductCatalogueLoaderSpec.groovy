package org.github.alexwibowo.spider.catalogue

import org.github.alexwibowo.spider.gui.task.ExcelXSSFProductCatalogueLoader
import spock.lang.Specification

class ExcelXSSFProductCatalogueLoaderSpec extends Specification {
    private ExcelXSSFProductCatalogueLoader catalogueLoader

    private ProductCatalogue catalogue

    def setup() {
        catalogue = new ProductCatalogue()
        catalogueLoader = new ExcelXSSFProductCatalogueLoader(catalogue: catalogue)
    }

    private File getTestInputFile(String filename) {
        new File(Thread.currentThread().contextClassLoader.getResource(filename).toURI())
    }


    def "should be able to read the catalogue in XLSX format"() {
        when: "reading the catalogue"
        catalogueLoader.load getTestInputFile("catalogue.xlsx")

        then: "catalogue should have all the entries"
        assert catalogue.size() == 3
    }


}
