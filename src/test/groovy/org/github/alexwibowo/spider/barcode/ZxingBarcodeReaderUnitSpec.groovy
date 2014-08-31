package org.github.alexwibowo.spider.barcode

import spock.lang.Specification

/**
 * User: alexwibowo
 */
class ZxingBarcodeReaderUnitSpec extends Specification {

    def "simple clear barcode picture"() {
        given:
        def stream = Thread.currentThread().contextClassLoader.getResourceAsStream("barcode_sample.jpg")

        when:
        def read = new ZxingBarcodeReader().readBarcode(stream)

        then:
        assert read == ["4905524449563","177326014300796"]
    }

    def "multiple barcodes"() {
        given:
        def stream = Thread.currentThread().contextClassLoader.getResourceAsStream("barcodecomparison.jpg")

        when:
        def read = new ZxingBarcodeReader().readBarcode(stream)

        then:
        assert read == ["PLAY4", "072700000420"]
    }

    def "multiple barcodes2"() {
        given:
        def stream = Thread.currentThread().contextClassLoader.getResourceAsStream("barcode_boxlabel_border.jpg")

        when:
        def read = new ZxingBarcodeReader().readBarcode(stream)

        then:
        assert read == ["51651651634"]
    }
}
