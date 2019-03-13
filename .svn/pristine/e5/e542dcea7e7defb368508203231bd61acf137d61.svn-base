package com.sys.common;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
    private static Logger logger = Logger.getLogger(FileUtils.class);


    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getRealPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileUrl 要删除的文件名路径
     * @return 删除成功返回true，否则返回false
     */
    public static Map<String, Object> delete(String fileUrl) {
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File(fileUrl);
        if (!file.exists()) {
            map.put("flag", false);
            map.put("msg", "删除文件失败:" + fileUrl + "不存在！");
            return map;
        } else {
            if (file.isFile())
                return deleteFile(fileUrl);
            else
                return deleteDirectory(fileUrl);
        }
    }

    /**
     * 文件上传保存
     *
     * @param file     文件
     * @param filePath 文件保存路径(绝对路径)
     * @return false失败 true成功
     */
    public static boolean saveFile(MultipartFile file, String filePath) {
        if (!file.isEmpty()) {
            try {
                new File(filePath).mkdirs();//创建文件路径
                // 转存文件
                String fileUrl = filePath + File.separator + file.getOriginalFilename();
                file.transferTo(new File(fileUrl));
            } catch (Exception e) {
                logger.error("文件上传异常：" + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * 判断文件大小
     *
     * @param :multipartFile:上传的文件
     * @param size:                限制大小
     * @param unit:限制单位（B,K,M,G)
     * @return boolean:是否大于
     */
    public static boolean checkFileSize(MultipartFile multipartFile, int size, String unit) {
        long len = multipartFile.getSize();//上传文件的大小, 单位为字节.
        //准备接收换算后文件大小的容器
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        //如果上传文件大于限定的容量
        if (fileSize > size) {
            return false;
        }
        return true;
    }

    public static File createFile(String fileUrl) {
        File file = new File(fileUrl);
        if (!file.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();//创建文件
                return file;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("创建文件异常!" + e.toString());
                throw new RuntimeException("创建文件异常!");
            }
        } else {
            return file;
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static Map<String, Object> deleteFile(String fileName) {
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                map.put("flag", true);
                map.put("msg", "删除单个文件" + fileName + "成功！");
                return map;
            } else {
                map.put("flag", false);
                map.put("msg", "删除单个文件" + fileName + "失败！");
                return map;
            }
        } else {
            map.put("flag", false);
            map.put("msg", "删除单个文件失败：" + fileName + "不存在！");
            return map;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static Map<String, Object> deleteDirectory(String dir) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return CommonUtils.getMsgForRet(false, "删除目录失败：" + dir + "不存在！");
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                Map<String, Object> mapOut = FileUtils.deleteFile(files[i].getAbsolutePath());
                flag = (Boolean) mapOut.get("flag");
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {// 删除子目录
                Map<String, Object> mapOut = FileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                flag = (Boolean) mapOut.get("flag");
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return CommonUtils.getMsgForRet(false, "删除目录失败！");
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return CommonUtils.getMsgForRet(true, "删除成功！");
        } else {
            return CommonUtils.getMsgForRet(false, "删除目录失败！");
        }
    }

    /**
     * 构建下载类
     *
     * @param response
     * @param file
     * @param contentType word文档:application/msword 文本:text/plain; charset=utf-8 xml数据:application/xml html:text/html; charset=utf-8
     * @return
     * @throws IOException
     */
    public static void buildResponseEntity(HttpServletResponse response, File file, String contentType) throws RuntimeException {
        try {
            response.setContentType(contentType);
            // 清空response
            response.reset();
            //以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("gbk"), "iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            response.reset();
            logger.error("文件下载失败!" + e.getMessage());
            throw new RuntimeException("文件下载失败!");
        }
    }


    /**
     * 构建下载类
     *
     * @param response
     * @param file
     * @param contentType word文档:application/msword 文本:text/plain; charset=utf-8 xml数据:application/xml html:text/html; charset=utf-8
     * @param fileName    根据文件名称
     * @return
     * @throws IOException
     */
    public static void buildResponseEntity(HttpServletResponse response, File file, String contentType, String fileName) throws RuntimeException {
        try {
            response.setContentType(contentType);
            // 清空response
            response.reset();

            //以流的形式下载文件
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
            response.addHeader("Content-Length", "" + file.length());
            fis.read(buffer);
            fis.close();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件下载失败!" + e.getMessage());
            throw new RuntimeException("文件下载失败!");
        }
    }

    /**
     * 构建下载类
     *
     * @param response
     * @param urlfile
     * @param contentType word文档:application/msword 文本:text/plain; charset=utf-8 xml数据:application/xml html:text/html; charset=utf-8
     * @param fileName    根据文件名称
     * @return
     * @throws IOException
     */
    public static void buildResponseEntityUrl(HttpServletResponse response, String urlfile, String contentType, String fileName) throws RuntimeException {
        try {
            URL url = new URL(urlfile);
//            response.setContentType(contentType);
            // 清空response
            response.reset();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] bs = readInputStream(inputStream);
            response.setContentType("application/octet-stream;charset=UTF-8");
            // 作为附件下载
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));  //转码之后下载的文件不会出现中文乱码

            BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
            output.write(bs);
            if(output!=null){
                output.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            response.flushBuffer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }



}