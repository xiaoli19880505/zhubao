package com.sys.controller.ueditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.common.DatetimeUtils;
import com.sys.common.FtpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;


@Controller
@RequestMapping("/umeditor")
public class UploadController {

    private String umeditor = "umeditor";

    @RequestMapping(value = "/upload", method = { RequestMethod.POST })
    public void upload(@RequestParam("upfile") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //ftp文件上传
        Date date = new Date();
        String fileDir = this.umeditor + "/" + DatetimeUtils.date2string(date,DatetimeUtils.YYYYMMDD);
        String dirFileName = String.valueOf(date.getTime());
        String fileNameNew = "";
        String curFileName = file.getOriginalFilename();
        if(curFileName.indexOf(".")<0){
            fileNameNew = dirFileName;
        }else{
            fileNameNew = new String(curFileName)
                    .replace(curFileName.substring(0,curFileName.indexOf(".")),dirFileName);
        }
        FtpUtils.uploadFile(fileDir, fileNameNew, file.getInputStream());

        String callback = request.getParameter("callback");

        String result = "{\"name\":\"" + fileNameNew
                + "\", \"originalName\": \"" + file.getOriginalFilename()
                + "\", \"size\": " + file.getSize() + ", \"state\": \""
                + "SUCCESS" + "\", \"type\": \"" + curFileName.substring(curFileName.indexOf("."))
                + "\", \"url\": \"" + fileDir + "/" + fileNameNew + "\"}";//FtpUtils.FTPUrl +

        result = result.replaceAll("\\\\", "\\\\");

        if (callback == null) {
            response.getWriter().print(result);
        } else {
            response.getWriter().print(
                    "<script>" + callback + "(" + result + ")</script>");
        }
    }

}
