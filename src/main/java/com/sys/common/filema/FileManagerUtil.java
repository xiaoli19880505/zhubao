package com.sys.common.filema;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Desc:文件公共管理类
 * @Author:wangli
 * @CreateTime:15:29 2018/10/29
 */
public class FileManagerUtil {

    /**
     * 将数据生成特定格式的excel
     * @param mapList 数据list
     * @param mapKeys list中map对应key，按照顺序
     * @param fileHeads excel表头
     * @param filepath  生成的文件路径
     */
    public static void generatorExcelFile(List<Map<String,Object>> mapList,String[] mapKeys,String[] fileHeads,String filepath){

        ArrayList<ArrayList<Object>> new_list=new ArrayList<ArrayList<Object>> ();

        /*用于生成excel时，表头的信息存储*/
        ArrayList<Object> header=new ArrayList<Object>();
        header.addAll(Arrays.asList(fileHeads));
        new_list.add(header);
        /*将从数据库种查询得到的业务单数据转存list*/
        for(Map data:mapList){
            ArrayList<Object> content=new ArrayList<Object>();
            for(String key:mapKeys) {
                content.add(data.get(key));
            }
            new_list.add(content);
        }
        try {
            ExcelUtil.writeExcel(new_list,filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*以下为文件输出流的下载的代码，将保存在工程包下的excel文件通过浏览器下载到本地*/
    public static void downloadToLocal(String filePath, String newName, HttpServletResponse response, HttpServletRequest request)
            throws IOException{
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = new File(filePath).length();
        response.setContentType("application/octet-stream");

        String fileName="";
        String agent = request.getHeader("User-Agent").toUpperCase(); //获得浏览器信息并转换为大写
        if (agent.indexOf("MSIE") > 0 || (agent.indexOf("GECKO")>0 && agent.indexOf("RV:11")>0)) {  //IE浏览器和Edge浏览器
            fileName = URLEncoder.encode(newName, "UTF-8");
        } else {  //其他浏览器
            fileName = new String(newName.getBytes("UTF-8"), "iso-8859-1");
        }
        response.setHeader("Content-disposition", "attachment; filename="
                + fileName);
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(filePath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }



}