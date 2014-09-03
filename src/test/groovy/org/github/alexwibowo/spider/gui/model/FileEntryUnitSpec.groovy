package org.github.alexwibowo.spider.gui.model

import org.apache.commons.io.FileUtils
import spock.lang.Shared
import spock.lang.Specification


class FileEntryUnitSpec extends Specification {

    @Shared
    def tempDirectory = FileUtils.getTempDirectory()

    def "when copying file to a directory"() {
        given:
        def sourceFile = File.createTempFile("alex", ".jpg")
        sourceFile.deleteOnExit()


        def fileEntry = new FileEntry(sourceFile)
        fileEntry.itemName="newProduct-1"

        when:
        fileEntry.copyTo(tempDirectory)


        then:
        def expectedOutputFile = new File(tempDirectory.absolutePath + File.separator + "newProduct-1.jpg")
        assert expectedOutputFile.exists()

        cleanup:
        expectedOutputFile.delete()
    }
}
