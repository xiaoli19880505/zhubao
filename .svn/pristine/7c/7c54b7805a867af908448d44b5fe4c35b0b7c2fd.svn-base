package com.sys.controller;

import com.sys.pojo.ImageInfo;
import com.sys.service.ImageInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/13 0012
 * @desc 图片controller
 */
@Controller
@RequestMapping("imageInfo")
public class ImageInfoController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ImageInfoService imageInfoService;

    private String saveFileDir = "upload" + File.separator
            + "image" + File.separator;

    /***
     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
     * @param file
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public Object fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        // 判断文件是否为空
        JSONObject jsonObj = new JSONObject();
        if (!file.isEmpty()) {
            String code = String.valueOf(new Date().getTime());
            String filePath = saveFileDir + code ;
            return imageInfoService.saveImage(file,filePath,code,request);
        } else {
            jsonObj.put("flag",false);
            return jsonObj;
        }
    }

    /**
     * 删除文件
     * @return
     */
    @RequestMapping("deleteFile")
    @ResponseBody
    public Object deleteByFileId(HttpServletRequest request){
        String id = request.getParameter("id"); // 栏目编码
        return imageInfoService.deleteByFileId(id,request);
    }

    /**
     * 修改文件 暂时不用
     * @return
     */
    @RequestMapping("updateFile")
    @ResponseBody
    public Object UpdateByFileId(){
        return null;
    }

    /**
     * 图片查询
     * @param request
     * @return
     */
    @RequestMapping("selectFile")
    @ResponseBody
    public Object selectByConditions(HttpServletRequest request){
        String imgName = request.getParameter("imgName"); // 图片名称
        String imgType = request.getParameter("imgType"); //图片类型
        ImageInfo imageInfo = new ImageInfo();
        if(StringUtils.isNotEmpty(imgName)){
            imageInfo.setImgName(imgName);
        }
        if(StringUtils.isNotEmpty(imgType)){
            imageInfo.setImgType(imgType);
        }
        imageInfo.setDeleteFlag("F");
        return  imageInfoService.selectByConditions(imageInfo);
    }

}
