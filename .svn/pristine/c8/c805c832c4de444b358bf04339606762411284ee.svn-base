package com.sys.controller.home;

import com.sys.service.home.FileFtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/13 0012
 * @desc FTP 测试 controller
 */
@Controller
@RequestMapping("fileFtp")
public class FileFtpController {

    @Autowired
    private FileFtpService fileFtpService;

    /**
     * ftp文件上传
     * @param file
     * @return
     */
    @PostMapping("uploadFtp")
    @ResponseBody
    public Object uploadFtp(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "文件为空上传失败";
        }
        try {
            return fileFtpService.uploadFtp(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ftp文件上传
     * @param fileName
     * @return
     */
    @PostMapping("downFtp")
    @ResponseBody
    public Object downFtp(@RequestParam("fileName") String fileName, HttpServletResponse response){
        try {
            return fileFtpService.downFtp(fileName, response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
