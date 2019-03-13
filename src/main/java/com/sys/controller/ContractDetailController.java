package com.sys.controller;

import com.sys.common.PropertiesUtil;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.ContractTemplateFillingDataPojo;
import com.sys.service.ContractDetailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("contract")
public class ContractDetailController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ContractDetailService contractDetailService;


    /**
     * 合同备案
     * @param request
     * @return
     */
    @RequestMapping("updateFiling")
    @ResponseBody
    public Object updateFiling(HttpServletRequest request) {
        String objId = request.getParameter("objId");//当前对象ID
        String apSqlb = request.getParameter("apSqlb");//申请类别
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo) session.getAttribute("user");//获取用户信息
        String result = null;
        if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))) {//经济适用
            //备案
            ContractTemplateFillingDataPojo contractTemplateFillingDataPojo = new ContractTemplateFillingDataPojo();
            contractTemplateFillingDataPojo.setObjId(objId);
            contractTemplateFillingDataPojo.setApSqlb(apSqlb);
            result = contractDetailService.updateFiling(contractTemplateFillingDataPojo, userinfo, null,null,null);
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//公租房
            result = "公租房不允许直接备案操作!";
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            result = "公租房不允许直接备案操作!";
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            result = "公租房不允许直接备案操作!";
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            result = "公租房不允许直接备案操作!";
        } else {
            return "不存在此申请类型的合同!";
        }
        return result;
    }

    /**
     * 合同备案
     * @param request
     * @return
     */
    @RequestMapping("downLoad")
    @ResponseBody
    public String downLoad(HttpServletRequest request, HttpServletResponse response){
        String objId = request.getParameter("objId");//当前对象ID
        String apSqlb = request.getParameter("apSqlb");//申请类别
        try {
            contractDetailService.downLoad(objId,apSqlb,response,request);
            return "下载成功!";
        } catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 申请单发送通知
     * @param request
     * @return
     */
    @RequestMapping("sendMessageForBA")
    @ResponseBody
    public String sendMessageForBA(HttpServletRequest request){
        String objId = request.getParameter("objId");//当前对象ID
        String apSqlb = request.getParameter("apSqlb");//申请类别
        return contractDetailService.findMsgToSend(objId,apSqlb);
    }


}
