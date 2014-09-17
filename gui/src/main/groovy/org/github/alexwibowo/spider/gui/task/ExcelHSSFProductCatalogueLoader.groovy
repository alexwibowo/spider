package org.github.alexwibowo.spider.gui.task

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Workbook

class ExcelHSSFProductCatalogueLoader extends BaseExcelProductCatalogueLoader{

    protected Workbook getWorkbook(InputStream is){
        POIFSFileSystem fs = new POIFSFileSystem(is);
        new HSSFWorkbook(fs);
    }
}
