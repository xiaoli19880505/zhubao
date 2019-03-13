package com.sys.common.encrypt;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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

    public static void main(String[] args)
    {
        // String strImg = GetImageStr();
        String strImg="iVBORw0KGgoAAAANSUhEUgAAAGQAAAAZCAYAAADHXotLAAAAbUlEQVRoQ+3TQREAAAiEQK9/aWvsAxMw4O06ysAommCuINgTFKQgmAEMp4UUBDOA4bSQgmAGMJwWUhDMAIbTQgqCGcBwWkhBMAMYTgspCGYAw2khBcEMYDgtpCCYAQynhRQEM4DhtJCCYAYwnAckXAAa1K+P3AAAAABJRU5ErkJggg==";
        String imgFile = "D:\\tupian\\";//待处理的图片
        //System.out.println(strImg);
        //GenerateImage(strImg);
        Base64Util.base64ToImage(strImg,imgFile,"bb11.jpg");
    }
}
