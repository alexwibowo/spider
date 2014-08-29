package org.github.alexwibowo.spider.gui.model

import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.dictionary.BarcodeDictionary

import javax.swing.SwingWorker


class BarcodeProcessingTask extends SwingWorker<Void, Integer> {

    List<FileEntry> inputFiles

    BarcodeReader barcodeReader

    BarcodeDictionary barcodeDictionary

    Closure callback

    @Override
    protected Void doInBackground() throws Exception {
        int photoSetSequenceNumber=0
        String currentPhotoSetItemName
        int size = inputFiles.size()
        inputFiles.eachWithIndex { fileEntry, index ->
            fileEntry.setStatus(Status.Processing)
            callback.call(index)
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
            int progress = (index + 1) * 100 / size
            setProgress(progress)
            publish(index)
            Thread.sleep(1000)
        }
        setProgress(100)     // make sure we reach 100% at the end
        return Void.newInstance()
    }

    @Override
    protected void process(List<Integer> chunks) {
        chunks.each {
            callback.call(it)
        }
    }
}
