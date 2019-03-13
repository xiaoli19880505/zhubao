package com.sys.controller;

import com.sys.pojo.UserInfo;
import com.sys.pojo.blagsh.BliveGongS;
import com.sys.service.SysExcelInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ExportExcel")
public class ExportExcelController {

    @Autowired
    private SysExcelInfoService sysExcelInfoService;

    @RequestMapping("export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.reset(); //清除buffer缓存
        Map<String,Object> map=new HashMap<String, Object>();
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String blgUserName = request.getParameter("blgUserName");
        String blgSfzh = request.getParameter("blgSfzh");
        String blgSsq = request.getParameter("blgSsq");
        String blgSsj = request.getParameter("blgSsj");
        BliveGongS bliveGongS = new BliveGongS();
        if(StringUtils.isNotEmpty(blgUserName)){
            bliveGongS.setBlgUserName(blgUserName);
        }
        if(StringUtils.isNotEmpty(blgSfzh)){
            bliveGongS.setBlgSfzh(blgSfzh);
        }
        if(StringUtils.isNotEmpty(blgSsq)){
            bliveGongS.setBlgSsq(blgSsq);
        }
        if(StringUtils.isNotEmpty(blgSsj)){
            bliveGongS.setBlgSsj(blgSsj);
        }
        if(userInfo!=null){
            if(StringUtils.isNotEmpty(userInfo.getUserid())){
                bliveGongS.setBlgRpuserid(userInfo.getUserid());
            }
        }
        //Map<String,Object> map=new HashMap<String,Object>();
        // 指定下载的文件名
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+new String("诚信列表.xlsx".getBytes(),"iso-8859-1"));
        //导出Excel对象
        XSSFWorkbook workbook = sysExcelInfoService.exportExcelInfo(bliveGongS);
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.flush();
            workbook.write(bufferedOutput);
            bufferedOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
