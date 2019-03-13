package com.sys.controller.home;

import com.github.pagehelper.PageInfo;
import com.sys.common.CommonUtils;
import com.sys.common.FileUtils;
import com.sys.common.FtpUtils;
import com.sys.pojo.AnnexFile;
import com.sys.pojo.ArticleInfo;
import com.sys.service.home.ArticleInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/13 0012
 * @desc 文章controller
 */
@Controller
@RequestMapping("articleInfo")
public class ArticleInfoController {

    @Autowired
    private ArticleInfoService articleInfoService;

    private Logger logger = Logger.getLogger(this.getClass());

    private String saveFileDir = "upload" + File.separator
            + "article" + File.separator;


    /**
     * 新增
     * @param request
     * @return
     */
    @RequestMapping("insertArticleInfo")
    @ResponseBody
    public Object insertArticleInfo(HttpServletRequest request){
        String articleName = request.getParameter("articleName");//文章名称
        String author = request.getParameter("author");//作者
        String articleBody = request.getParameter("articleBody");//正文
        String columnId = request.getParameter("columnId");//栏目ID
        return articleInfoService.insertArticleInfo(articleName,author,articleBody,columnId,request);
    }

    /***
     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
     * @param file
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public Object fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String code = String.valueOf(new Date().getTime());//时间戳
            String filePath = saveFileDir + code ;//相对路径
            String articleId = request.getParameter("articleId");
            return articleInfoService.saveArticle(file,articleId,filePath,request);
        } else {
            return CommonUtils.getMsgForRet(false,"未找到上传的文件!");
        }
    }

    /**
     * 下载中心下载文件
     * @param request
     * @param response
     */
    @RequestMapping("getFileDownArt")
    public Object getFileDownArt(HttpServletRequest request, HttpServletResponse response){
        //获取文件路径
        String artId = request.getParameter("artId");
        //获取下载中心文件 相对url
        String relativelyUrl = articleInfoService.getFileUrl(artId);
        //文件的绝对路径
        String filePath = FileUtils.getRealPath() + relativelyUrl;
        try{
            FileUtils.buildResponseEntity(response,
                    new File(filePath), "application/msword");
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("文件下载失败");
        }
        return "下载文件成功";
    }

    /**
     * 下载中心FTP下载文件
     * @param request
     * @param response
     */
    @RequestMapping("getFTPFileDownArt")
    public Object getFTPFileDownArt(HttpServletRequest request, HttpServletResponse response){
        //获取文件路径
        String artId = request.getParameter("artId");
        //获取下载中心文件 相对url
        AnnexFile annexFile = articleInfoService.getArticleFileUrl(artId);
        //文件的绝对路径
        String filePath = annexFile.getFileUrl().replace(FtpUtils.FTPUrl,"");
        //下载文件名称
        String fileName = annexFile.getFileName();
        try{
            FtpUtils.downloadFile(filePath,fileName,response);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("文件下载失败");
        }
        return "下载文件成功";
    }

    /***
     * 删除文件 用@RequestParam注解来指定表单上的file为MultipartFile
     * @param request
     * @return
     */
    @RequestMapping("deleteFile")
    @ResponseBody
    public Object deleteFile(HttpServletRequest request){
        String fileId = request.getParameter("fileId");
        try {
            return articleInfoService.deleteFile(fileId);
        } catch (Exception e){
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @RequestMapping("deleteArticleInfo")
    @ResponseBody
    public Object deleteArticleInfo(HttpServletRequest request){
        String id = request.getParameter("id");//主键id
        try {
            return articleInfoService.deleteArticleInfo(id,request);
        } catch (Exception e){
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping("updateArticleInfo")
    @ResponseBody
    public Object updateArticleInfo(HttpServletRequest request){
        String id = request.getParameter("id");//文章id
        String articleName = request.getParameter("articleName");//文章名称
        String author = request.getParameter("author");//作者
        String articleBody = request.getParameter("articleBody");//正文
        String columnId = request.getParameter("columnId");//栏目ID
        return articleInfoService.updateArticleInfo(id, articleName, author, articleBody, columnId,request);
    }

    /**
     * 查询（分页）
     * @param request
     * @return
     */
    @RequestMapping("selectArticleInfoes")
    @ResponseBody
    public Object selectArticleInfoes(HttpServletRequest request){
        String columnInfoId = request.getParameter("columnInfoId");//栏目ID
        String articleName = request.getParameter("articleName");//文章名称
        String author = request.getParameter("author");//作者
        String page = request.getParameter("page");//页数
        if(StringUtils.isEmpty(page))
            page = "1";
        String pageSize = request.getParameter("rows");//条数
        if(StringUtils.isEmpty(pageSize))
            pageSize = "20";
//        String issuingTime = request.getParameter("issuingTime");//发布时间
        PageInfo<ArticleInfo> articleInfoes = (PageInfo<ArticleInfo>)articleInfoService.checkArticleInfoes(null,columnInfoId,articleName,author,page,pageSize);
        Map<String,Object> map=new HashMap();
        map.put("rows",articleInfoes.getList());
        map.put("total",articleInfoes.getTotal());
        return map;
    }

    /**
     * 查询根据id
     * @param request
     * @return
     */
    @RequestMapping("selectArticleInfoById")
    @ResponseBody
    public Object selectArticleInfoesById(HttpServletRequest request){
        String id = request.getParameter("id");//ID
        return articleInfoService.checkArticleInfoes(id,null,null,null,null,null);
    }

}
