package com.longfor.shop.pdf;

import com.itextpdf.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import java.io.File;
public class ParsePdfTextAndRectangleTest {
    private static final String PDF_TO_IMAGE = "/Users/admin/Desktop/pdfToImage-";
    /**
     * 提取PDF中的图片
     * @param file pdf文件
     */
    public static void extractImages(File file) {
        try{
            PdfReader reader = new PdfReader(file.getAbsolutePath());
            //获取pdf文件总页数
            int pageNum = reader.getNumberOfPages();
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            ImageRenderListener listener = new ImageRenderListener(PDF_TO_IMAGE +
                    file.getName().substring(0,file.getName().lastIndexOf(".")));
            for (int i = 1; i <= pageNum; i++) {
                parser.processContent(i,listener);
            }
        } catch(IOException | java.io.IOException ex){
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception {
        new ParsePdfTextAndRectangleTest().extractImages(new File("/Users/admin/Desktop/烟威地产-一期-春江天境-洁具陶瓷,洁具龙头材料第3批进场需求.pdf"));
    }
}

