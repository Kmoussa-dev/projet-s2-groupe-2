package projet.group2.gestionEmargement.config;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class TokenGenerator {
    private TokenGenerator(){}

    private static final QRCodeWriter qrCodeWriter = new QRCodeWriter();

    public static BufferedImage generateQRCode(String uri) throws WriterException {
        BitMatrix bitMatrix = qrCodeWriter.encode(uri, BarcodeFormat.QR_CODE, 200,200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


}
