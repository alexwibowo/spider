package org.github.alexwibowo.spider.gui.task

import org.github.alexwibowo.spider.catalogue.CatalogueLoadingException

/**
 * User: alexwibowo
 */
class ExcelProductCatalogueLoaderFactory {

    BaseExcelProductCatalogueLoader createLoader(File file) {
        if (file.name.toLowerCase().endsWith(".xls")) {
            return new ExcelHSSFProductCatalogueLoader()
        }else if (file.name.toLowerCase().endsWith(".xlsx")) {
            return new ExcelXSSFProductCatalogueLoader()
        }
        throw new CatalogueLoadingException("Unsupported excel file [${file.name}]. Only .xls and .xlsx files are supported")
    }
}
