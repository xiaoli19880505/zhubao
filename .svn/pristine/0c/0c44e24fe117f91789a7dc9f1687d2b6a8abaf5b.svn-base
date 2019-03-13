package com.sys.common;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class PDFUtil {

    /*@RequestMapping("/PdfConvert.do")
    public void PdfConvert(HttpServletResponse response) {
        String path = "C:\\Users\\acer\\Desktop\\测试.doc";
        String path2 = "C:\\Users\\acer\\Desktop\\测试.pdf";
        word2PDF(path, path2);
        String path3 = "C:\\Users\\acer\\Desktop\\测试2.ppt";
        String path4 = "C:\\Users\\acer\\Desktop\\测试2.pdf";
        ppt2PDF(path3, path4);
        String path5 = "C:\\Users\\acer\\Desktop\\测试3.xls";
        String path6 = "C:\\Users\\acer\\Desktop\\测试3.pdf";
        excel2PDF(path5, path6);
    }*/

    /**
     * word转换pdf
     * @param inputFile word路径
     * @param pdfFile pdf路径
     * @return
     */
    public static boolean word2PDF(String inputFile, String pdfFile) {
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        try {
            app.setProperty("Visible", false);
            // 打开word文件
            Dispatch docs = app.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.call(docs, "Open",
                    inputFile, false, true).toDispatch();

            File file = new File(pdfFile.substring(0,pdfFile.lastIndexOf(File.separator)));
            //创建pdf保存路径
            if(!file.exists()){
                file.mkdirs();
            }
            Dispatch.call(doc, "SaveAs", pdfFile, 17);
            Dispatch.call(doc, "Close", false);

            /*Dispatch.call(doc, "ExportAsFixedFormat", new Object[]{pdfFile, 17});
            Dispatch.call(doc, "Close", new Object[]{false});*/
            app.invoke("Quit", 0);
            return true;
        } catch (Exception var6) {
            app.invoke("Quit", 0);
            var6.printStackTrace();
            return false;
        }
    }

    /**
     * excel 转换pdf
     * @param inputFile
     * @param pdfFile
     * @return
     */
    public static boolean excel2PDF(String inputFile, String pdfFile) {
        ComThread.InitSTA(true);
        ActiveXComponent app = new ActiveXComponent("Excel.Application");
        try {
            app.setProperty("Visible", false);
            app.setProperty("AutomationSecurity", new Variant(3));
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.invoke(excels, "Open", 1, new Object[]{inputFile, new Variant(false), new Variant(false)}, new int[9]).toDispatch();
            Dispatch.invoke(excel, "ExportAsFixedFormat", 1, new Object[]{new Variant(0), pdfFile, new Variant(0)}, new int[1]);
            Dispatch.call(excel, "Close", new Object[]{false});
            if (app != null) {
                app.invoke("Quit", new Variant[0]);
                app = null;
            }
            ComThread.Release();
            return true;
        } catch (Exception var6) {
            app.invoke("Quit");
            return false;
        }
    }

    /**
     * ppt转换pdf
     * @param inputFile
     * @param pdfFile
     * @return
     */
    public static boolean ppt2PDF(String inputFile, String pdfFile) {
        ActiveXComponent app = new ActiveXComponent("PowerPoint.Application");
        try {
            Dispatch ppts = app.getProperty("Presentations").toDispatch();
            Dispatch ppt = Dispatch.call(ppts, "Open", new Object[]{inputFile, true, true, false}).toDispatch();
            Dispatch.call(ppt, "SaveAs", new Object[]{pdfFile, 32});
            Dispatch.call(ppt, "Close");
            app.invoke("Quit");
            return true;
        } catch (Exception var6) {
            app.invoke("Quit");
            return false;
        }
    }


}