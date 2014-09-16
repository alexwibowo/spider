package org.github.alexwibowo.spider.barcode

import com.google.common.collect.Lists
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.ChecksumException
import com.google.zxing.DecodeHintType
import com.google.zxing.FormatException
import com.google.zxing.LuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.ReaderException
import com.google.zxing.Result
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.multi.GenericMultipleBarcodeReader
import com.google.zxing.multi.MultipleBarcodeReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.imageio.ImageIO
import java.awt.image.BufferedImage


class ZxingBarcodeReader implements BarcodeReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZxingBarcodeReader.class.getName());

    private final Map<DecodeHintType, Object> HINTS;
    private final Map<DecodeHintType, Object> HINTS_PURE;

    ZxingBarcodeReader() {
        HINTS = new EnumMap<>(DecodeHintType.class);
        HINTS.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));

        HINTS_PURE = new EnumMap<>(HINTS);
        HINTS_PURE.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
    }


    @Override
    Collection<String> readBarcode(InputStream inputStream) {
        BufferedImage bufferedImage = ImageIO.read(inputStream)
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));

        Collection<Result> results = [];
        com.google.zxing.Reader reader = new MultiFormatReader();

        scanForMultipleBarcodes(reader, bitmap) { Result result ->
            results.add(result);
        }

        if (results.isEmpty()) {
            scanForPureBarcode(reader, bitmap)  { Result result ->
                results.add(result)
            }
        }

        if (results.isEmpty()) {
            scanForAllFormat(reader, bitmap){ Result result ->
                results.add(result)
            }
        }

        if (results.isEmpty()) {
            scanWithHybridBinarizer(source, reader){ Result result ->
                results.add(result)
            }
        }

        if (results.isEmpty()) {
            Lists.newArrayList()
        } else {
            return results.text
        }
    }

    private void scanForMultipleBarcodes(MultiFormatReader reader, BinaryBitmap bitmap, Closure closure)
            throws ReaderException{
        try {
            MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader)
            Result[] results = multiReader.decodeMultiple(bitmap, HINTS)
            results.each {
                closure.call(it)
            }
        } catch (ReaderException re) {
            handleException(re)
        }
    }

    private void scanWithHybridBinarizer(BufferedImageLuminanceSource source, MultiFormatReader reader, Closure closure)
            throws ReaderException{
        try {
            BinaryBitmap hybridBitmap = new BinaryBitmap(new HybridBinarizer(source))
            Result result = reader.decode(hybridBitmap, HINTS)
            closure.call(result)
        } catch (ReaderException re) {
            handleException(re)
        }
    }

    private void scanForAllFormat(MultiFormatReader reader, BinaryBitmap bitmap, Closure closure)
            throws ReaderException {
        try {
            Result result = reader.decode(bitmap, HINTS)
            closure.call(result)
        } catch (ReaderException re) {
            handleException(re)
        }
    }

    private void scanForPureBarcode(MultiFormatReader reader, BinaryBitmap bitmap, Closure closure)
            throws ReaderException {
        try {
            Result result = reader.decode(bitmap, HINTS_PURE)
            closure.call(result)
        } catch (ReaderException re) {
            handleException(re)
        }
    }


    private static void handleException(ReaderException re) {
        if (re instanceof NotFoundException) {
            LOGGER.info("Not found: " + re);
        } else if (re instanceof FormatException) {
            LOGGER.info("Format problem: " + re);
        } else if (re instanceof ChecksumException) {
            LOGGER.info("Checksum problem: " + re);
        } else {
            LOGGER.info("Unknown problem: " + re);
        }
    }
}
