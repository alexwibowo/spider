package org.github.alexwibowo.spider.gui.task

import org.github.alexwibowo.spider.barcode.BarcodeReader
import org.github.alexwibowo.spider.catalogue.ProductCatalogue
import org.github.alexwibowo.spider.gui.model.FileEntry
import org.github.alexwibowo.spider.gui.model.Status

import javax.swing.SwingWorker


class BarcodeProcessingTask extends SwingWorker<Integer, Integer> {

    List<FileEntry> inputFiles

    File outputLocation

    BarcodeReader barcodeReader

    ProductCatalogue barcodeDictionary

    Closure callback

    @Override
    protected Integer doInBackground() throws Exception {
        int photoSetSequenceNumber = 0
        String currentPhotoSetItemName
        int size = inputFiles.size()
        inputFiles.eachWithIndex { fileEntry, index ->
            fileEntry.setStatus(Status.Processing)
            callback.call(index)
            InputStream is = fileEntry.getFile().newInputStream()
            Collection<String> barcodes = barcodeReader.readBarcode(is)
            if (barcodes) {
                photoSetSequenceNumber = 0
                currentPhotoSetItemName = findProductNameForBarcode(barcodes)
                fileEntry.barcode = "${barcodes}"
                fileEntry.itemName = "${currentPhotoSetItemName}-${photoSetSequenceNumber}"
            } else {
                photoSetSequenceNumber++
                fileEntry.barcode = "N/A"
                fileEntry.itemName = "${currentPhotoSetItemName}-${photoSetSequenceNumber}"
            }
            fileEntry.copyTo(outputLocation)
            is.close()
            fileEntry.setStatus(Status.Processed)
            int progress = (index + 1) * 100 / size
            setProgress(progress)
            publish(index)
        }
        setProgress(100)     // make sure we reach 100% at the end
        return size
    }


    private String findProductNameForBarcode(Collection<String> barcodes) {
        for (String barcode : barcodes) {
            def productName = barcodeDictionary.getItemName(barcode)
            if (productName) {
                return productName
            }
        }
        return "UNKNOWNPRODUCT"

    }

    @Override
    protected void process(List<Integer> chunks) {
        chunks.each {
            callback.call(it)
        }
    }
}
