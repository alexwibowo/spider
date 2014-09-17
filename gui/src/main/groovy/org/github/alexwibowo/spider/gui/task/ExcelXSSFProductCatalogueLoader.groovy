package org.github.alexwibowo.spider.gui.task

import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * User: alexwibowo
 */
class ExcelXSSFProductCatalogueLoader extends BaseExcelProductCatalogueLoader {

    protected XSSFWorkbook getWorkbook(InputStream is) {
        new XSSFWorkbook(is)
    }
}
