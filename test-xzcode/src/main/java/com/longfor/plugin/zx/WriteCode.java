package com.longfor.plugin.zx;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;

public class WriteCode {

    public static void main(String[] args) {
        ZxCodeUtil.buildFile("1390351289", new File("/Users/admin/Desktop/2dcode.png"), 200, 200);
    }

    /**
     * @param file   生成的文件名称
     * @param code   一维码存储的数据信息
     * @param width  生成图片的宽度
     * @param height 生成图片的高度
     * @return void
     */
    public static void generateCode(File file, String code, int width, int height) {
        // 定义位图矩阵BitMatrix
        BitMatrix matrix = null;
        try {
            // 使用code_128格式进行编码生成100*25的条形码
            MultiFormatWriter writer = new MultiFormatWriter();
            matrix = writer.encode(code, BarcodeFormat.CODE_128, width, height, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        // 将位图矩阵BitMatrix保存为图片
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png", outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
