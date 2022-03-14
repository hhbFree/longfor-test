package com.longfor.shop.pdf.itex;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ItexAnalyse {

    public String readPdf(String fileName) throws Exception {
        //获取 PdfReader 对象,文件名称要是在classpath中的文件
        PdfReader read = new PdfReader(fileName);
        //获取pdf中页数
        int pageCount = read.getNumberOfPages();
        System.out.println("page count : " + pageCount);
        //循环遍历取出内容.
        //PdfTextExtractor.getTextFromPage(read,page)方法调用
        //注意: i要从 1 开始
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 1; i <= pageCount ; i++) {
            stringBuilder.append(PdfTextExtractor.getTextFromPage(read, i));
            //System.out.println(content);
        }
        read.close();
        return stringBuilder.toString();
    }

    public void createPdf(String fileName,String context){
        Document document = new Document();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Font f1 = FontFactory.getFont("仿宋_GB2312.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            document.add(new Paragraph(context, f1));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
            writer.close();
        }
    }


}
