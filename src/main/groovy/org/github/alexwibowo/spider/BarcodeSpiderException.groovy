package org.github.alexwibowo.spider


class BarcodeSpiderException extends RuntimeException{

    BarcodeSpiderException(String s) {
        super(s)
    }

    BarcodeSpiderException(String s, Throwable throwable) {
        super(s, throwable)
    }
}
