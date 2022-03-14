package com.longfor.shop.pdf.sprie;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

public class SpireAnalyse {


    public String analyse(String fileName) {
        PdfDocument doc = null;
        try {
            //创建PdfDocument实例
            doc = new PdfDocument();
            //加载PDF文件
            doc.loadFromFile(fileName);

            //创建StringBuilder实例
            StringBuilder sb = new StringBuilder();

            PdfPageBase page;
            //遍历PDF页面，获取每个页面的文本并添加到StringBuilder对象
            for (int i = 0; i < doc.getPages().getCount(); i++) {
                page = doc.getPages().get(i);
                sb.append(page.extractText(true));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
        return null;
    }

    public static void main(String[] args) {


    }
}