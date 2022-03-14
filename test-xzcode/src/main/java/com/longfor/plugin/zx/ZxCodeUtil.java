package com.longfor.plugin.zx;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ZxCodeUtil {

    /**
     * 创建二进制流文件
     * @param content
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static byte[] buildBytes(String content,int width,int height) {
        String format = "png";
        // 使用code_128格式进行编码生成100*25的条形码
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(content, BarcodeFormat.CODE_128, width, height, null);
            return ZxCodeUtil.writeToStream(matrix, format);
        } catch (WriterException e) {
            log.error("BitMatrix buildBytes:{}",e);
        } catch (IOException e) {
           log.error("getContent buildBytes:{}",e);
        }
        return null;
    }

    /**
     * 创建文件
     * @param content
     * @param file
     * @throws IOException
     * @throws WriterException
     */
    public static void buildFile(String content, File file,int width,int height) {
        String format = "png";
        // 使用code_128格式进行编码生成100*25的条形码
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(content, BarcodeFormat.CODE_128, width, height, null);
            ZxCodeUtil.writeToFile(matrix, format, file);
        } catch (WriterException e) {
            log.error("BitMatrix buildBytes:{}",e);
        } catch (IOException e) {
            log.error("writeToFile buildBytes:{}",e);
        }
    }
    
    /**
     * 读取文件内容
     * @param readImage
     * @return void
     */
    public static String readContextByFile(File readImage)  {
        try {
            BufferedImage image = ImageIO.read(readImage);
            return getContent(image);
        } catch (IOException e) {
            log.error("read readContextByFile:{}",e);
        } catch (NotFoundException e) {
            log.error("getContent readContextByFile:{}",e);
        }
        return null;
    }

    /**
     * 读取二进制流文件
     * @param bytes
     * @return void
     */
    public static String readContextByBytes(byte[] bytes) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            return getContent(image);
        } catch (IOException e) {
            log.error("read byteArrayInputStream:{}",e);
        } catch (NotFoundException e) {
            log.error("getContent byteArrayInputStream:{}",e);
        }
        return null;
    }

    private static String getContent(BufferedImage image) throws NotFoundException {
        if (image == null) {
            return null;
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "gbk");
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static byte[] writeToStream(BitMatrix matrix, String format) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
        return stream.toByteArray();
    }

    public static void main(String[] args) {
        byte[] his = buildBytes("hi",500,200);
        String s = readContextByBytes(his);
        System.out.println(s);
        buildFile("1390351289",new File("/Users/admin/Desktop/2dcode.png"),500,200);
        String s1 = readContextByFile(new File("/Users/admin/Desktop/2dcode.png"));
        System.out.println(s1);
    }
}
