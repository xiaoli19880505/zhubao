package com.sys.common.encrypt;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Date;

public class Base64Util {

    /**
     * 编码
     * @param byteArray
     * @return
     */
    public static String encode(byte[] byteArray) {
        return new String(new Base64().encode(byteArray));
    }

    /**
     * 解码
     * @param base64EncodedString
     * @return
     */
    public static byte[] decode(String base64EncodedString) {
        return new Base64().decode(base64EncodedString);
    }

    /**
     * base64转换成.svg-->转换成png
     * @param imgStr
     * @param filePath
     * @param fileName
     */
    public static void base64SvgXmlToImage(String imgStr,String filePath,String fileName){
        //替换表头信息
        String svgStrBase64 = imgStr.replace("data:image/svg+xml;base64,","");
        try{
            convertFromSvgXmlToSvgFile(svgStrBase64,filePath+fileName.replace("png","svg"));
            svgToPNG(filePath+fileName.replace("png","svg"),filePath+fileName);
        }catch (Exception e){
            throw new RuntimeException("转换PNG异常");
        }
    }

    public static void svgToPNG(String svgFile,String imgFile){
        PNGTranscoder t = new PNGTranscoder();
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
        String svgURI;
        try {
            svgURI = new File(svgFile).toURL().toString();
            TranscoderInput input = new TranscoderInput(svgURI);
            OutputStream ostream = new FileOutputStream(
                    imgFile);
            TranscoderOutput output = new TranscoderOutput(ostream);
            t.transcode(input, output);
            ostream.flush();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 将svg字符串转换为svgFile
     * @param svgCode svg代码
     * @param svgFilePath 保存的路径
     * @throws TranscoderException svg代码异常
     * @throws IOException io错误
     */
    public static void convertFromSvgXmlToSvgFile(String svgCode, String svgFilePath) throws IOException,
            TranscoderException {
        File file = new File(svgFilePath);
        FileOutputStream outputStream = null;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            // 解密
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(svgCode);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * base64字符串转化成图片并保存
     * @param imgStr
     * @param filePath(例如：D:/upload)
     * @param fileName(例如：abc.jpg)
     * @return
     */
    public static boolean base64ToImage(String imgStr,String filePath,String fileName){  //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null){//图像数据为空
            return false;
        }
        //BASE64Decoder decoder = new BASE64Decoder();
        try{
            //Base64解码
            byte[] b = decode(imgStr);//decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i){
                if(b[i]<0){//调整异常数据
                    b[i]+=256;
                }
            }
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(filePath+File.separator+fileName);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 将svg字符串转换为png
     *
     * @param svgCode svg代码
     * @param pngFilePath 保存的路径
     * @throws TranscoderException svg代码异常
     * @throws IOException io错误
     */
    public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
            TranscoderException {
        File file = new File(pngFilePath);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将svgCode转换成png文件，直接输出到流中
     *
     * @param svgCode svg代码
     * @param outputStream 输出流
     * @throws TranscoderException 异常
     * @throws IOException io异常
     */
    public static void convertToPng(String svgCode, OutputStream outputStream)
            throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes("utf-8");
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
//         String strImg = GetImageStr();
        String strImg="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMzcyIiBoZWlnaHQ9IjE4NyI+PHBhdGggc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZT0icmdiKDAsIDAsIDEzOSkiIGZpbGw9Im5vbmUiIGQ9Ik0gMjAgNjYgYyAwLjIzIC0wLjEyIDguNTUgLTUuMzEgMTMgLTcgYyA0Ljk3IC0xLjg4IDEwLjUxIC0yLjk2IDE2IC00IGMgNy4wNiAtMS4zMyAxMy43NyAtMi4zMiAyMSAtMyBjIDE4LjQ2IC0xLjcyIDQ0LjEzIC00LjU0IDU0IC00IGMgMS4yOSAwLjA3IDAuOTIgNC42NCAxIDcgYyAwLjI2IDguMDIgMi4zMiAxNy42OCAwIDI0IGMgLTIuOTggOC4xMSAtMTEuODMgMTguODMgLTE4IDI1IGMgLTIuMzUgMi4zNSAtOC43OSAyLjM0IC0xMSA0IGMgLTAuOTYgMC43MiAtMS4xMyAzLjUyIC0xIDUgYyAwLjE3IDEuODQgMC45IDQuNTMgMiA2IGMgMS42IDIuMTQgNS4yIDMuODQgNyA2IGMgMS4zMSAxLjU3IDIuNyAzLjk3IDMgNiBjIDEuMjUgOC41MSAyLjU1IDIyLjcyIDIgMjggYyAtMC4xIDAuOTYgLTMuMzMgMC45NiAtNSAxIGMgLTExLjE1IDAuMjkgLTIzLjYzIDEuODkgLTM0IDAgYyAtMTAuNDIgLTEuOSAtMjAuOTUgLTguNDMgLTMyIC0xMiBjIC0xMS4wNiAtMy41NyAtMjIuMjUgLTUuOCAtMzMgLTkgYyAtMS40MSAtMC40MiAtNCAtMS4zOCAtNCAtMiBjIDAgLTAuNzkgMi40OSAtMy4xNSA0IC00IGMgMy40NyAtMS45NSA3LjkxIC00LjEzIDEyIC01IGMgMTEuMTMgLTIuMzcgMjMuMDUgLTQuMTEgMzUgLTUgYyAyNC4zNSAtMS44MiA0OC42MSAtMS4xMiA3MiAtMyBsIDE1IC00Ii8+PHBhdGggc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZT0icmdiKDAsIDAsIDEzOSkiIGZpbGw9Im5vbmUiIGQ9Ik0gMjEwIDEgYyAwLjE5IDAuMTEgNy45NSAzLjg5IDExIDYgYyAwLjg4IDAuNjEgMS4yIDIuMiAyIDMgYyAxLjQzIDEuNDMgNSA0IDUgNCIvPjxwYXRoIHN0cm9rZS1saW5lam9pbj0icm91bmQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLXdpZHRoPSIyIiBzdHJva2U9InJnYigwLCAwLCAxMzkpIiBmaWxsPSJub25lIiBkPSJNIDIyMiAzOCBjIDAuMTkgLTAuMDUgNy40OCAtMS42NSAxMSAtMyBjIDUuMTEgLTEuOTcgOS45MyAtNS4zNiAxNSAtNyBjIDQuOTkgLTEuNjEgMTAuNTEgLTIuMjQgMTYgLTMgYyA2Ljc4IC0wLjk0IDEzLjMxIC0wLjk0IDIwIC0yIGMgNi4xMyAtMC45NyAxMiAtMy43MSAxOCAtNCBjIDIxLjE0IC0xLjAyIDU2LjQxIC0wLjY4IDY1IDAgYyAwLjcxIDAuMDYgLTEuMTMgMy40NSAtMiA1IGMgLTIuMSAzLjc0IC00LjMgNy45NiAtNyAxMSBjIC0yLjM2IDIuNjYgLTUuODEgNC44MyAtOSA3IGMgLTUuMjggMy41OSAtMTAuMzMgNy43MSAtMTYgMTAgYyAtMTcuNzggNy4xNiAtMzcuNjkgMTMuNjEgLTU2IDE5IGMgLTMuNzMgMS4xIC04LjIgMC4xNiAtMTIgMSBjIC00Ljk5IDEuMTEgLTEzLjgzIDQuMjYgLTE1IDUgYyAtMC4zMyAwLjIxIDIuNjkgMS43NiA0IDIgbCA3IDAiLz48cGF0aCBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS13aWR0aD0iMiIgc3Ryb2tlPSJyZ2IoMCwgMCwgMTM5KSIgZmlsbD0ibm9uZSIgZD0iTSAyMzUgNDggYyAtMC4wNCAwLjQ3IC0xLjY2IDE3Ljc1IC0yIDI3IGMgLTAuMzQgOS4yMiAwLjY4IDE3Ljk2IDAgMjcgYyAtMSAxMy4xNyAtMi42OSAyNS45MiAtNSAzOSBjIC0xLjc1IDkuOTIgLTQuOTQgMTkuMTkgLTcgMjkgYyAtMC42MyAyLjk4IC0xLjIxIDguNzkgLTEgOSBjIDAuMTggMC4xOCAyLjMzIC00LjY1IDMgLTcgYyAwLjYyIC0yLjE4IDEgLTcgMSAtNyIvPjxwYXRoIHN0cm9rZS1saW5lam9pbj0icm91bmQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLXdpZHRoPSIyIiBzdHJva2U9InJnYigwLCAwLCAxMzkpIiBmaWxsPSJub25lIiBkPSJNIDI2OSA4NyBjIDAuMDkgMC4wOSAzLjY5IDMuMTMgNSA1IGMgMy4yNCA0LjYyIDYuMDQgOS44MSA5IDE1IGMgMS4xNCAxLjk5IDIuMjIgMy45MiAzIDYgYyAxLjIxIDMuMjMgMyAxMCAzIDEwIi8+PHBhdGggc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZT0icmdiKDAsIDAsIDEzOSkiIGZpbGw9Im5vbmUiIGQ9Ik0gMjU3IDEyNyBjIDAuMTIgLTAuMTYgNC41IC02LjI5IDcgLTkgYyAxLjQxIC0xLjUyIDMuMTcgLTMuMDUgNSAtNCBjIDYuMjYgLTMuMjYgMTMuMDggLTYuNjkgMjAgLTkgYyAxMy4xIC00LjM3IDI2LjY5IC03LjkzIDQwIC0xMSBjIDMuODYgLTAuODkgOCAtMC4zMyAxMiAtMSBjIDguMTMgLTEuMzYgMTYuMDkgLTMuNjggMjQgLTUgbCA2IDAiLz48cGF0aCBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS13aWR0aD0iMiIgc3Ryb2tlPSJyZ2IoMCwgMCwgMTM5KSIgZmlsbD0ibm9uZSIgZD0iTSAzMzcgMTA4IGMgMC4wNSAwLjE2IDIuMzYgNS45OCAzIDkgYyAwLjY3IDMuMiAwLjMyIDYuNzUgMSAxMCBjIDAuOTggNC43MSAzLjQyIDkuMzkgNCAxNCBjIDAuNzEgNS43IDEuMDEgMTIuNjEgMCAxOCBjIC0wLjg2IDQuNTYgLTMuMTIgMTAuODUgLTYgMTQgYyAtMy4zMSAzLjYyIC0xMCA3LjE3IC0xNSA5IGMgLTQuMzMgMS41OSAtMTAuMDMgMS4xIC0xNSAyIGMgLTIuMzkgMC40MyAtNi42NyAyLjk5IC03IDIgYyAtMS4wNSAtMy4xNCAwIC0yMyAwIC0yMyIvPjxwYXRoIHN0cm9rZS1saW5lam9pbj0icm91bmQiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLXdpZHRoPSIyIiBzdHJva2U9InJnYigwLCAwLCAxMzkpIiBmaWxsPSJub25lIiBkPSJNIDMxNCAxMDUgYyAtMC4xNyAwLjI0IC02LjM5IDkuNTggLTEwIDE0IGMgLTIuMzggMi45IC01LjExIDUuNDcgLTggOCBjIC03Ljk5IDYuOTkgLTE1LjY3IDEzLjM4IC0yNCAyMCBjIC0zLjI2IDIuNTkgLTEwIDcgLTEwIDciLz48L3N2Zz4=";
        String imgFile = "D:/tupian/";//待处理的图片
        //System.out.println(strImg);
        //GenerateImage(strImg);
        try {
//            svgToPNG(imgFile+"ee.svg",imgFile+"ee.png");
            base64SvgXmlToImage(strImg,imgFile,"dd.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void svgToJpg2(String svgFile,String imgFile){
        PNGTranscoder t = new PNGTranscoder();
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
        String svgURI;
        try {
            svgURI = new File(svgFile).toURL().toString();
            TranscoderInput input = new TranscoderInput(svgURI);
            OutputStream ostream = new FileOutputStream(
                    imgFile);
            TranscoderOutput output = new TranscoderOutput(ostream);
            t.transcode(input, output);
            ostream.flush();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void svgToJpg(String svgFile,String imgFile){
        Date date = new Date();
        long timeBegin = date.getTime();
        // svg文件路径
        String strSvgURI;
        OutputStream ostream = null;
        try {
            // 根据路径获得文件夹
            File fileSvg = new File(svgFile);
            // 构造一个表示此抽象路径名的 file:URI
            URI uri = fileSvg.toURI();
            // 根据此 URI 构造一个 URL
            URL url = uri.toURL();
            // 构造此 URL 的字符串表示形式
            strSvgURI = url.toString();
            // 定义一个通用的转码器的输入
            TranscoderInput input = new TranscoderInput(strSvgURI);
            // 定义图片路径
            String strPngPath = imgFile;
            // 输入流
            ostream = new FileOutputStream(strPngPath);
            // 定义单路输出的转码器
            TranscoderOutput output = new TranscoderOutput(ostream);
            // 构造一个新的转码器，产生JPEG图像
            JPEGTranscoder transcoder = new JPEGTranscoder();
            // 设置一个转码过程，JPEGTranscoder.KEY_QUALITY设置输出png的画质精度，0-1之间
            transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
            // 转换svg文件为png
            transcoder.transcode(input, output);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TranscoderException e) {
            e.printStackTrace();
        } finally {
            try {
                ostream.flush();
                // 关闭输入流
                ostream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("转换图片成功");
// // 删除svg文件
// fileSvg.delete();
        System.out.println(svgFile + "已删除！");
        date = new Date();
        long timeEnd = date.getTime();
        long time = timeEnd - timeBegin;
        System.out.println("耗时" + time + "毫秒");

    }

}
