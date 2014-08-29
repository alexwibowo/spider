package org.github.alexwibowo.spider.gui.model

import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.dictionary.BarcodeDictionary

import javax.swing.SwingWorker


class BarcodeProcessingTask extends SwingWorker<Void, Void> {

    List<FileEntry> inputFiles

    BarcodeReader barcodeReader

    BarcodeDictionary barcodeDictionary

    Closure callback

    @Override
    protected Void doInBackground() throws Exception {
        int photoSetSequenceNumber=0
        String currentPhotoSetItemName
        inputFiles.eachWithIndex { fileEntry, index ->
            fileEntry.setStatus(Status.Processing)
            callback.call(index)
            Thread.sleep(2000)
            InputStream is = fileEntry.getFile().newInputStream()
            def barcode = barcodeReader.readBarcode(is)
            if (barcode != null) {
                photoSetSequenceNumber=0
                currentPhotoSetItemName = barcodeDictionary.getItemName(barcode)
                fileEntry.itemName = currentPhotoSetItemName
            }else{
                photoSetSequenceNumber++
                fileEntry.itemName = "${currentPhotoSetItemName}-${photoSetSequenceNumber}"
                fileEntry.renameFile()
                // rename file using currentPhotoSetItemName & photoSet sequence number
            }
            is.close()
            fileEntry.setStatus(Status.Processed)
            callback.call(index)
            Thread.sleep(2000)
        }
        return Void.newInstance()
    }

}
