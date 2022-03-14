package com.longfor.shop.pdf;

import com.longfor.shop.pdf.itex.ItexAnalyse;
import com.longfor.shop.pdf.pdfbox.PdfBoxAnalyse;
import com.longfor.shop.pdf.sprie.SpireAnalyse;
import com.longfor.shop.pdf.tika.TikaAnalyse;
import org.junit.Test;


public class PdfBoxAnalyseTest {
    private static final String pdfName = "/Users/admin/Desktop/烟威地产-一期-春江天境-洁具陶瓷,洁具龙头材料第3批进场需求.pdf";
    private static final String pdfNameCreate = "/Users/admin/Desktop/helloWorld.pdf";

    @Test
    public void itexAnalyse() {
        try {
            long start = System.currentTimeMillis();
            ItexAnalyse boxAnalyse=new ItexAnalyse();
            String analyse = boxAnalyse.readPdf(pdfName);
            System.out.println(analyse);
            long end = System.currentTimeMillis();

            System.out.println("pdfBox use time:" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void itexAnalyseCrete() {
        try {
            long start = System.currentTimeMillis();
            ItexAnalyse boxAnalyse=new ItexAnalyse();
            boxAnalyse.createPdf(pdfName,"大家好，我是龙湖地产");
            long end = System.currentTimeMillis();
            System.out.println("pdfBox use time:" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void itexAnalyseUpdate() {
        try {
            long start = System.currentTimeMillis();
            ItexAnalyse boxAnalyse=new ItexAnalyse();
            String context = boxAnalyse.readPdf(pdfName);
            context="hi！～\r"+context;
            boxAnalyse.createPdf(pdfNameCreate,context);


            long end = System.currentTimeMillis();
            System.out.println("pdfBox use time:" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void pdfBoxAnalyse() {
        try {
            long start = System.currentTimeMillis();
            PdfBoxAnalyse boxAnalyse=new PdfBoxAnalyse();
            boxAnalyse.analyse(pdfName);
            long end = System.currentTimeMillis();

            System.out.println("pdfBox use time:" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void spireAnalyse() {
        try {
            long start = System.currentTimeMillis();

            SpireAnalyse analyse = new SpireAnalyse();
            String s = analyse.analyse(pdfName);
            System.out.println(s);

            long end = System.currentTimeMillis();

            System.out.println("spire use time:" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tikaAnalyse() {
        long start = System.currentTimeMillis();

        TikaAnalyse tikaAnalyse = new TikaAnalyse();
        String analyse = tikaAnalyse.analyse(pdfName);
        System.out.println(analyse);

        long end = System.currentTimeMillis();

        System.out.println("tika use time:" + (end - start));

    }




}
