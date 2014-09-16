package org.github.alexwibowo.spider.gui.model
/**
 * User: alexwibowo
 */
class InputFilesPreProcessor {

    List<FileEntry> preProcess(List<FileEntry> files) {
        // filter only images
        def filenamePattern = /(.*?)(\d+)\.(jpg|png)/
        def filtered = files.findAll {
            return it.file.name =~ filenamePattern
        }

        // sort
        filtered.sort { a, b ->
            def matcher1 = a.file.name =~ filenamePattern
            def matcher2 = b.file.name =~ filenamePattern
            matcher1[0][2] as Integer <=> matcher2[0][2] as Integer
        }

        return filtered
    }
}
