package com.sys.controller;

import com.sys.common.BatchUtil;
import com.sys.common.CommonUtils;
import com.sys.common.ImageTools;
import com.sys.common.PropertiesUtil;
import com.sys.pojo.Volelearc;
import com.sys.pojo.VolelearcDtl;
import com.sys.service.VolelearcDtlService;
import com.sys.service.VolelearcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Desc:档案附件管理控制类
 * @Author:wangli
 * @CreateTime:9:49 2018/10/15
 */
@Controller
@RequestMapping("/volel")
public class VolelearcController {

    @Autowired
    private VolelearcService volelearcService;
    @Autowired
    private VolelearcDtlService volelearcDtlService;

    /*
     *文件图片的批量上传
     */
    @RequestMapping("uoloadVolel")
    @ResponseBody
    public String  addUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        try {
            String result = volelearcService.addVolelearcMultipart(request);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败!";
        }
    }

    /*
     *文件图片的单张上传
     */
    @RequestMapping("uoloadVolelByOne")
    @ResponseBody
    public String  addUploadByOne(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException
    {
        this.volelearcService.addUploadByOne(request,response);
        String result="添加成功";
        if("success".equals(result)){
            result="添加成功";
        }else{
            result="添加失败";
        }
        return result;
    }
    @RequestMapping("deleteDetail")
    @ResponseBody
    public Object deleteDetail(String id){
        String result="";
        int deleteCount=volelearcDtlService.deleteDetail(id);
        if(deleteCount>0){
            result="删除成功";
        }else{
            result="删除失败";
        }

        return result;
    }
}