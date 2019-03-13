package com.sys.service;

import com.sys.common.FileUtils;
import com.sys.mapper.AnnexFileMapper;
import com.sys.pojo.AnnexFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Service
public class AnnexFileService extends BaseService<AnnexFile> {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AnnexFileMapper annexFileMapper;

    public void getAnnexFileById(String fileId, HttpServletResponse response){
        //获取文件路径
        AnnexFile annexFile = annexFileMapper.selectById(fileId);
        try{
            FileUtils.buildResponseEntity(response,
                    new File(annexFile.getFileUrl()), "application/msword",annexFile.getFileName());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void getAnnexFileByIdUrl(String fileId, HttpServletResponse response){
        //获取文件路径
        AnnexFile annexFile = annexFileMapper.selectById(fileId);
        try{
            FileUtils.buildResponseEntityUrl(response,
                    annexFile.getFileUrl(), null,annexFile.getFileName());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void getAnnexFtpFileById(String fileId, HttpServletResponse response){
        //获取文件路径
        AnnexFile annexFile = annexFileMapper.selectById(fileId);
        try{
            FileUtils.buildResponseEntity(response,
                    new File(annexFile.getFileUrl()), "application/msword");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
