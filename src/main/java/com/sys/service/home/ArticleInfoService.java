package com.sys.service.home;

import com.github.pagehelper.PageHelper;
import com.sys.common.CommonUtils;
import com.sys.common.DatetimeUtils;
import com.sys.common.FileUtils;
import com.sys.common.FtpUtils;
import com.sys.mapper.AnnexFileMapper;
import com.sys.mapper.ArticleInfoMapper;
import com.sys.mapper.ImageInfoMapper;
import com.sys.pojo.AnnexFile;
import com.sys.pojo.ArticleInfo;
import com.github.pagehelper.PageInfo;
import com.sys.pojo.ImageInfo;
import com.sys.pojo.UserInfo;
import com.sys.service.BaseService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ArticleInfoService extends BaseService<ArticleInfo> {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private AnnexFileMapper annexFileMapper;

    @Autowired
    private ImageInfoMapper imageInfoMapper;

    private Logger logger = Logger.getLogger(this.getClass());

    private String articleInfoDir = "article";

    /**
     * 插入文章
     * @param articleName 文章名称
     * @param author 文章作者
     * @param articleBody 正文
     * @param columnId 栏目ID
     * @return
     */
    @Transactional
    public Object insertArticleInfo(String articleName, String author, String articleBody, String columnId
            ,HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
            if(StringUtils.isEmpty(columnId))
                return CommonUtils.getMsgForRet(false,"栏目类型不可为空!");
            if(StringUtils.isEmpty(articleName))
                return CommonUtils.getMsgForRet(false,"文章名称不可为空!");
            if(StringUtils.isEmpty(author))
                return CommonUtils.getMsgForRet(false,"文章作者不可为空!");
            ArticleInfo articleInfo = new ArticleInfo();
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            articleInfo.setId(uuid);
            Date date = new Date();
            articleInfo.setArticleCode(String.valueOf(date.getTime()));
            articleInfo.setArticleName(articleName);
            articleInfo.setIssuingTime(date);
            articleInfo.setAuthor(author);
            articleInfo.setColumnId(columnId);
            articleInfo.setDeleteFlag("F");
            articleInfo.setArticleBody(articleBody);
            articleInfo.setClickNumber(0);
            articleInfo.setCreateTime(date);
            articleInfo.setCreatePerson(userinfo.getUsercode());
            articleInfo.setUpdateTime(date);
            articleInfo.setUpdatePerson(userinfo.getUsercode());
            int num = articleInfoMapper.insert(articleInfo);
            if(num>0){
                //保存文章图片
                saveArticleImg(uuid,articleInfo.getArticleCode(),articleName,articleBody,userinfo);
                return CommonUtils.getMsgForRet(true,articleInfo.getId());
            }else{
                return CommonUtils.getMsgForRet(false,"新增失败");
            }
        } catch (Exception e){
            throw new RuntimeException("新增文章出错!");
        }
    }

    /**
     * 获取文件的url（相对路径）
     * @return
     */
    public String getFileUrl(String artId){
        AnnexFile annexFile = new AnnexFile();
        annexFile.setFkId(artId);
        List<AnnexFile> annexFiles = annexFileMapper.selectByCondition(annexFile);
        if(annexFiles==null || annexFiles.get(0)==null){
            return null;
        }
        return annexFiles.get(0).getFileUrl();
    }

    /**
     * 获取文件的url（相对路径）
     * @return
     */
    public AnnexFile getArticleFileUrl(String artId){
        AnnexFile annexFile = new AnnexFile();
        annexFile.setFkId(artId);
        List<AnnexFile> annexFiles = annexFileMapper.selectByCondition(annexFile);
        if(annexFiles==null || annexFiles.get(0)==null){
            return null;
        }
        return annexFiles.get(0);
    }

    /**
     * 删除文章（逻辑删除）
     * @param id 主键
     * @param request
     * @return
     */
    @Transactional
    public  Object deleteArticleInfo(String id,HttpServletRequest request) throws RuntimeException {
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        if(StringUtils.isEmpty(id)){
            return  "文章信息不完整无法删除";
        }
        ArticleInfo articleInfo = articleInfoMapper.selectById(id);
        if(articleInfo==null){
            return "文章信息不存在!";
        }
        articleInfo.setDeleteFlag("T");
        articleInfo.setUpdateTime(new Date());
        articleInfo.setUpdatePerson(userinfo.getUsercode());
        int num = articleInfoMapper.update(articleInfo);
        //附件删除
        AnnexFile annexFile = new AnnexFile();
        annexFile.setFkId(articleInfo.getId());
        List<AnnexFile> annexFiles = annexFileMapper.selectByCondition(annexFile);
        for (AnnexFile ann : annexFiles) {
            deleteFile(ann.getId());
        }
        if(num>0){
            return "删除成功!";
        }else{
            return "删除失败!";
        }
    }

    /**
     * 修改文章
     * @param articleName 文章名称
     * @param author 文章作者
     * @param articleBody 正文
     * @param columnId 栏目ID
     * @return
     */
    @Transactional
    public Object updateArticleInfo(String id,String articleName, String author,
                                    String articleBody, String columnId,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        if(StringUtils.isEmpty(columnId))
            return "栏目类型不可为空!";
        if(StringUtils.isEmpty(articleName))
            return "文章名称不可为空!";
        if(StringUtils.isEmpty(author))
            return "文章作者不可为空!";
        if(StringUtils.isEmpty(id)){
            return  "文章信息提供不全!";
        }
        ArticleInfo articleInfo = articleInfoMapper.selectById(id);
        if(articleInfo==null){
            return  "文章信息丢失!";
        }
        Date date = new Date();
        articleInfo.setArticleName(articleName);
        articleInfo.setIssuingTime(date);
        articleInfo.setAuthor(author);
        articleInfo.setColumnId(columnId);
        articleInfo.setArticleBody(articleBody);
        //保存文章图片
        saveArticleImg(id,articleInfo.getArticleCode(),articleName,articleBody,userinfo);

        articleInfo.setUpdateTime(date);
        articleInfo.setUpdatePerson(userinfo.getUsercode());
        int num = articleInfoMapper.update(articleInfo);
        if(num>0){
            return "修改成功!";
        }else{
            return "修改失败!";
        }
    }

    /**
     * 保存文章图片
     * @param articleBody
     */
    private void saveArticleImg(String articleId, String articleCode, String articleName,String articleBody,UserInfo userinfo) {
        //解析html信息
        List<String> srcList = new ArrayList<String>(); //用来存储获取到的图片地址
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");//匹配字符串中的img标签
        Matcher matcher = p.matcher(articleBody);
        boolean hasPic = matcher.find();
        if(hasPic == true)//判断是否含有图片
        {
            while(hasPic) //如果含有图片，那么持续进行查找，直到匹配不到
            {
                String group = matcher.group(2);//获取第二个分组的内容，也就是 (.*?)匹配到的
                Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");//匹配图片的地址
                Matcher matcher2 = srcText.matcher(group);
                if( matcher2.find() )
                {
                    srcList.add( matcher2.group(3) );//把获取到的图片地址添加到列表中
                }
                hasPic = matcher.find();//判断是否还有img标签
            }
        }
        System.out.println("匹配到的内容："+srcList);
        //保存url
        if(CollectionUtils.isEmpty(srcList)){
            return;
        }
        String lastUrl = srcList.get(srcList.size()-1);
        //处理url并保存
        //获取项目名最后出现的url
//        String urlNotHttp = lastUrl.replace("http://","");
        /*urlNotHttp = urlNotHttp.replace("https://","");
        int urlNotHttpInd = urlNotHttp.indexOf("/");
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String bzfxm = request.getContextPath();//如果在tomcat的server中配置了项目的虚拟路径则会得到项目名，没有配置则为空。
        if(StringUtils.isEmpty(bzfxm)){
            if(urlNotHttpInd<0){
                urlNotHttpInd = urlNotHttp.indexOf("\\");
                urlNotHttpInd += 1;
            } else {
                urlNotHttpInd += 1;
            }
        }else{
            urlNotHttpInd = urlNotHttp.indexOf(bzfxm) + bzfxm.length() + 1;//项目名称后边的信息
        }
        String saveUrl = urlNotHttp.substring(urlNotHttpInd);*/
        //保存或是修改
        //保存文件至系统
        Date date = new Date();
        //获取image信息是否存在
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setImgCode(articleCode);
        List<ImageInfo> imageInfos = imageInfoMapper.selectByCondition(imageInfo);
        boolean flag = true;
        if(CollectionUtils.isEmpty(imageInfos)){
            imageInfo = new ImageInfo();
            imageInfo.setImgCode(articleCode);
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            imageInfo.setId(uuid);
            flag = false;
        } else {
            imageInfo = imageInfos.get(0);
        }
        //暂时写死
        imageInfo.setCreatePerson(userinfo.getUsercode());
        imageInfo.setUpdatePerson(userinfo.getUsercode());
        imageInfo.setCreateTime(date);
        imageInfo.setUpdateTime(date);
        imageInfo.setDeleteFlag("F");
        imageInfo.setImgName(articleName);
        imageInfo.setImgUrl(lastUrl);//ftp图片地址
        //后期增加图片分为 文章类型图片/二维码图片
        imageInfo.setImgType("1");//图片类型（1.首页图片切换2.二维码）
        imageInfo.setArticleId(articleId);//文章外键
        imageInfo.setExtranetUrl(null);//外网URL(二维码类型专用)
        if(flag){
            imageInfoMapper.update(imageInfo);
        }else{
            imageInfoMapper.insert(imageInfo);
        }
    }

    /**
     * 新增
     * @param file 文件
     * @param filePath 保存文件路径（相对路径）
     * @param request
     * @return
     */
    public Object saveArticle(MultipartFile file, String articleId, String filePath, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        if(StringUtils.isEmpty(articleId)){
            return CommonUtils.getMsgForRet(false,"未关联文章信息!");
        }
        // 判断文件是否为空
        JSONObject jsonObj = new JSONObject();
        if (!file.isEmpty()) {
            boolean flag = FileUtils.checkFileSize(file,50,"M");
            if(!flag){
                logger.error("文章附件上传异常");
                jsonObj.put("flag",false);
                jsonObj.put("msg","文章附件超出" + 50 + "M");
                return jsonObj;
            }
//            FileUtils.saveFile(file,FileUtils.getRealPath() + filePath);
            Date date = new Date();
            String fileDir = articleInfoDir + "/" + DatetimeUtils.date2string(date,DatetimeUtils.YYYYMMDD);
            String dirFileName = String.valueOf(date.getTime());
            String fileNameNew = "";
            if(file.getOriginalFilename().indexOf(".")<0){
                fileNameNew = dirFileName;
            }else{
                fileNameNew = new String(file.getOriginalFilename())
                        .replace(file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf(".")),dirFileName);
            }

            try {
                FtpUtils.uploadFile(
                        fileDir, fileNameNew,file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文章附件上传异常");
                jsonObj.put("flag",false);
                jsonObj.put("msg","附件上传FTP异常!");
                return jsonObj;
            }
            if(!flag){
                logger.error("文章附件上传异常");
                jsonObj.put("flag",false);
                jsonObj.put("msg","文章附件上传异常");
                return jsonObj;
            }
            //保存文件至系统
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            AnnexFile annexFile = new AnnexFile();
            annexFile.setId(uuid);//ID
            annexFile.setFkId(articleId);//外键ID
            annexFile.setFileName(file.getOriginalFilename());//文件名称
            annexFile.setFileUrl(FtpUtils.FTPUrl + fileDir
                    + "/" + fileNameNew);//文件路径
            annexFile.setCreateDate(date);//创建时间
            annexFile.setCreateName(userinfo.getUsercode());//创建人
            int num = annexFileMapper.insert(annexFile);
            if(num<1){
                jsonObj.put("flag",false);
                jsonObj.put("msg","文章附件保存异常");
                return jsonObj;
            }
        }
        jsonObj.put("flag",true);
        return jsonObj;
    }

    /**
     * 删除附件
     * @param fileId 附件ID
     * @return
     * @throws RuntimeException
     */
    public Object deleteFile(String fileId) throws RuntimeException{
        //获取附件行信息
        AnnexFile annexFile = annexFileMapper.selectById(fileId);
        /*String fileUrl = annexFile.getFileUrl();//文件相对路径
        String fileName = annexFile.getFileName();//文件名称
        String httpFileUrl = FileUtils.getRealPath() + fileUrl ;//文件绝对路径
        String fileDir = httpFileUrl.replace(File.separator + fileName,"");//文件所在文件夹（至时间戳）
        Map<String,Object> map = FileUtils.deleteDirectory(fileDir);//删除文件夹（时间戳）
        if(!(Boolean)map.get("flag")){
           logger.error("文件删除失败!");
        }*/
        //URL
        String url = annexFile.getFileUrl();
        //获取ftp路径
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        String fileDir = url.replace(FtpUtils.FTPUrl,"").replace(fileName,"");
        //ftp删除
        try {
            FtpUtils.deleteFile(fileDir,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(fileName + "文件删除失败!");
        }
        int num = annexFileMapper.delete(fileId);
        if(num<1){
            throw new RuntimeException("文件信息删除失败!");
        }
        return "删除成功!";
    }

    /**
     * 查询文章
     * @param id 主键
     * @param columnInfoId 栏目外键
     * @param articleName 文章名称（模糊）
     * @param author 作者（模糊）
     * @param page 作者（模糊）
     * @param rows 作者（模糊）
     * @return
     */
    public Object checkArticleInfoes(String id, String columnInfoId, String articleName, String author, String page, String rows){
        if(StringUtils.isNotEmpty(id)){//主键查询
            ArticleInfo articleInfo = articleInfoMapper.selectById(id);
            articleInfo.setClickNumber(articleInfo.getClickNumber()==null?1:articleInfo.getClickNumber() + 1);
            articleInfoMapper.update(articleInfo);//修改阅读数
            //查询文章附件信息
            AnnexFile annexFile = new AnnexFile();
            annexFile.setFkId(id);//外键ID
            List<AnnexFile> annexFiles = annexFileMapper.selectByCondition(annexFile);
            Map<String,Object> mapOut = new HashMap<String, Object>();
            mapOut.put("articleInfo",articleInfo);
            mapOut.put("annexFiles",annexFiles);
            return mapOut;
        } else {//条件查询
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setArticleName(StringUtils.isEmpty(articleName)?null:articleName);
            articleInfo.setAuthor(StringUtils.isEmpty(author)?null:author);
            articleInfo.setColumnId(StringUtils.isEmpty(columnInfoId)?null:columnInfoId);
            articleInfo.setDeleteFlag("F");
            //分页条件
            PageHelper.startPage(Integer.parseInt(page),
                    Integer.parseInt(rows));
            List<ArticleInfo> articleInfoes = articleInfoMapper.selectByConditions(articleInfo);
            return new PageInfo<ArticleInfo>(articleInfoes);
        }
    }




}
