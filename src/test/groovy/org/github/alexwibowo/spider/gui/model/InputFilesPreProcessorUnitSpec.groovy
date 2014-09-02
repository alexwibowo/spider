package org.github.alexwibowo.spider.gui.model

import spock.lang.Specification

/**
 * User: alexwibowo
 */
class InputFilesPreProcessorUnitSpec extends Specification {

    def "should return empty list when given empty list"() {
        expect:
        new InputFilesPreProcessor().preProcess([]) == []
    }

    def "should ignore file without any number in it"() {
        given:
        def inputFiles = [
                new FileEntry(new File("DSCONE.jpg")),
                new FileEntry(new File("DSCTWO.png")),
        ]

        when:
        def preprocessed = new InputFilesPreProcessor().preProcess(inputFiles)

        then:
        assert preprocessed.isEmpty()
    }

    def "should only accept jpg or png file"() {
        given:
        def inputFiles = [
                new FileEntry(new File("DSC1.xls")),
                new FileEntry(new File("DSC1.jpg")),
                new FileEntry(new File("DSC2.png")),
        ]

        when:
        def preprocessed = new InputFilesPreProcessor().preProcess(inputFiles)

        then:
        assert preprocessed.size() == 2
        assert preprocessed[0].file.name == "DSC1.jpg"
        assert preprocessed[1].file.name == "DSC2.png"
    }

    def "should sort the file using the digit portion"() {
        given:
        def inputFiles = [
                new FileEntry(new File("DSC1000.jpg")),
                new FileEntry(new File("DSC1001.jpg")),
                new FileEntry(new File("DSC200.jpg")),
                new FileEntry(new File("DSC201.jpg")),
                new FileEntry(new File("DSC100.jpg")),
                new FileEntry(new File("DSC101.jpg"))
        ]

        when:
        def preprocessed = new InputFilesPreProcessor().preProcess(inputFiles)

        then:
        assert preprocessed.size() == 6
        assert preprocessed[0].file.name == "DSC100.jpg"
        assert preprocessed[1].file.name == "DSC101.jpg"
        assert preprocessed[2].file.name == "DSC200.jpg"
        assert preprocessed[3].file.name == "DSC201.jpg"
        assert preprocessed[4].file.name == "DSC1000.jpg"
        assert preprocessed[5].file.name == "DSC1001.jpg"
    }
}
