package com.sys.controller;

import com.sys.common.FileUtils;
import com.sys.service.AnnexFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 文件公共类
 */
@Controller
@RequestMapping("file")
public class FileController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AnnexFileService annexFileService;


    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getFile")
    public Object getFile(HttpServletRequest request, HttpServletResponse response){
        //获取文件路径
        String filePath = request.getParameter("filePath");
        try{
            FileUtils.buildResponseEntity(response,
                    new File(filePath), "application/msword");
        } catch (Exception e){
            logger.error(e.getMessage());
            return "下载文件失败";
        }
        return "下载文件成功";
    }

    /**
     * 根据文件ID获取文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getFileById")
    public void getAnnexFileById(HttpServletRequest request, HttpServletResponse response){
        String fileId = request.getParameter("fileId");
        annexFileService.getAnnexFileById(fileId,response);
    }

    /**
     * 根据文件ID获取文件URL
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getFileByIdUrl")
    public void getAnnexFileByIdUrl(HttpServletRequest request, HttpServletResponse response){
        String fileId = request.getParameter("fileId");
        annexFileService.getAnnexFileByIdUrl(fileId,response);
    }

    /**
     * 根据文件ID获取文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getFtpFileById")
    public void getFtpFileById(HttpServletRequest request, HttpServletResponse response){
        String fileId = request.getParameter("fileId");
        annexFileService.getAnnexFtpFileById(fileId,response);
    }


}
