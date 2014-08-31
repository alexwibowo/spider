package org.github.alexwibowo.spider.barcode


public interface BarcodeReader {

    Collection<String> readBarcode(InputStream inputStream)
}