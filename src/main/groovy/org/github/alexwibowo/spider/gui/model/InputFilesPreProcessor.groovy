package org.github.alexwibowo.spider.gui.model

/**
 * User: alexwibowo
 */
class InputFilesPreProcessor {

    List<FileEntry> preProcess(List<FileEntry> files) {
        // filter only images
        def filtered = files.findAll {
            def lowerCase = it.file.name.toLowerCase()
            return lowerCase.endsWith(".jpg") ||
                    lowerCase.endsWith(".png")
        }

        // sort
        def filenamePattern = /(.*?)(\d+)\.(jpg|png)/
        filtered.sort { a, b ->
            def matcher1 = a.file.name =~ filenamePattern
            def matcher2 = b.file.name =~ filenamePattern
            matcher1[0][2] as Integer <=> matcher2[0][2] as Integer
        }

        return filtered
    }
}
