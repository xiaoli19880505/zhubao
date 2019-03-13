package com.sys.service.home;

import com.sys.common.CommonUtils;
import com.sys.common.FtpUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileFtpService {

    public Object uploadFtp(MultipartFile file){

        try {
            InputStream in = file.getInputStream();
            FtpUtils.uploadFile("temp/img",file.getOriginalFilename(),in);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object downFtp(String fileName, HttpServletResponse response){

        try {//receivedManagerWorkFlow.png
            FtpUtils.downloadFile("temp/img/" + fileName,"444.png",response);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
