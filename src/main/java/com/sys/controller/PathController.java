package com.sys.controller;

import com.sys.common.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:15:06 2018/10/11
 */
@Controller
@RequestMapping("/path")
public class PathController {

    @RequestMapping("/todsh")
    public String  todsh(){
        return "houseApply/noauditApply";
    }



    @RequestMapping("/totype")
    public  String totype(){
        return "applicationForm/type";
    }

    @RequestMapping("/toforgetPwd")
    public  String toforgetPwd(){
        return "index/forgetPwd";
    }

    @RequestMapping("/tohousema")
    public  String tohousema(){
        return "houseInfoMa/houseInto";
    }

    @RequestMapping("/toysh")
    public String  toysh(){
        return "houseApply/auditApply";
    }


    @RequestMapping("/toBzdsh")
    public String  toBzdsh(){
        return "houseAudit/noauditApply";
    }

    @RequestMapping("/toBzysh")
    public String  toBzysh(){
        return "houseAudit/auditApply";
    }


    @RequestMapping("/toregiste")
    public String toregiste (){
        return "index/registe";
    }

    /**
     * 新就业公租房
     * @return
     */
    @RequestMapping("/toJygzf")
    public String  toJygzf(){
        return "houseRecord/jobHouse";
    }

    @RequestMapping("/toApplyPrint")
    public  String toApplyPrint(){
        return "applicationForm/applyPrint";
    }


    /**
     * 申请人签字板
     * @return
     */
    @RequestMapping("/toApplySign")
    public  String toApplySign(){
        return "applicationForm/sign";
    }

    /**
     * 申请人签字板
     * @return
     */
    @RequestMapping("/toSearchSign")
    public  String toSearchSign(){
        return "houseApply/applyDialog/searchSign";
    }


    /**
     * 补贴报表
     * @return
     */
    @RequestMapping("/toApplyButieReport")
    public String  toButieReport(){
        return "applyReport/butieReport";
    }

    /**
     * 外来务工公租房
     * @return
     */
    @RequestMapping("/toWggzf")
    public String  toWggzf(){
        return "houseRecord/outsideHouse";
    }

    @RequestMapping("/toexit")
    public  String toexit(Integer sx, HttpSession session){
        String result="";
        session.removeAttribute("applyUserinfo");
        return "index/applogin";
       /* if (sx==1){
            session.removeAttribute("user");
            return "index/login";
        }else{
            session.removeAttribute("applyUserinfo");
            return "index/applogin";
        }*/
    }

    /**
     * 低保特困住保房
     * @return
     */
    @RequestMapping("/toDbtkgzf")
    public ModelAndView  toDbtkgzf(){
        ModelAndView modelAndView = new ModelAndView();
        String toPath = "houseRecord/allowanceHouse";
        String applyType= PropertiesUtil.getApplyTypeProperties("gongzf");
        modelAndView.setViewName(toPath);
        modelAndView.addObject("applyType",applyType);
        return modelAndView;
    }

    /**
     * 低保特困补贴
     * @return
     */
    @RequestMapping("/toDbtkbt")
    public String toDbtkbt(){
        return "houseRecord/allowance";
    }

    /**
     * 经济适用房
     * @return
     */
    @RequestMapping("/toJjSyf")
    public ModelAndView toJjSyf(){
        ModelAndView modelAndView = new ModelAndView();
        String toPath = "houseRecord/economyHouse";
        String applyType= PropertiesUtil.getApplyTypeProperties("jingsf");
        modelAndView.setViewName(toPath);
        modelAndView.addObject("applyType",applyType);
        return modelAndView;
    }

    @RequestMapping("/toFyxxgl")
    public String  toFyxxgl(){
        return "houseInfoMa/houseInfo";
    }

    @RequestMapping("/toFydr")
    public String  toFydr(){
        return "houseInfoMa/houseImport";
    }
    @RequestMapping("/toFypj")
    public String  toFypj(){
        return "houseInfoMa/houseRation";
    }

    @RequestMapping("/toFyfp")
    public String  toFyfp(){
        return "houseInfoMa/houseDis";
    }

    @RequestMapping("/tonewRegist")
    public String  tonewRegist(){
        return "registMa/newRegist";
    }

    @RequestMapping("/topassedRegist")
    public String  topassedRegist(){
        return "registMa/passedRegist";
    }

    @RequestMapping("/tofailRegist")
    public String  tofailRegist(){
        return "registMa/failRegist";
    }

    /**
     * 加载页面头部
     * @return
     */
    @RequestMapping("/tohead")
    public String toHead(){
        return "index/header";
    }

    /**
     * 跳到首页
     * @return
     */
    @RequestMapping("/toindex")
    public String toIndex(){
        return "index/index";
    }


    /**
     * 跳到登录
     * @return
     */
    @RequestMapping("/toapplogin")
    public String toapplogin(){
        return "index/applogin";
    }

    /**
     * 跳到登录
     * @return
     */
    @RequestMapping("/tologin")
    public String tologin(){
        return "index/login";
    }

    /**
     * 合同录入分配房源选择
     * @return
     */
    @RequestMapping("/choose")
    public String choose(){
        return "houseRecord/choose";
    }

    /**
     * 跳到欢迎页面
     * @return
     */
    @RequestMapping("/towelcome")
    public String toWelcome(){
        return "index/welcome";
    }

    /**
     * 跳到用户页面
     * @return
     */
    @RequestMapping("/touser")
    public String toUser(){
        return "SystemMa/userMa";
    }

    /**
     * 跳到角色页面
     * @return
     */
    @RequestMapping("/torole")
    public String toRole(){
        return "SystemMa/roleMa";
    }

    /**
     * 跳到模块页面
     * @return
     */
    @RequestMapping("/tomodule")
    public String toModule(){
        return "SystemMa/moduleMa";
    }

    /**
     * 跳到权限页面
     * @return
     */
    @RequestMapping("/toauthor")
    public String toAuthor(){
        return "SystemMa/authorityMa";
    }

    /**
     * 跳到字典页面
     * @return
     */
    @RequestMapping("/todic")
    public String toDic(){
        return "SystemMa/dictionaryMa";
    }

    /**
     * 跳到合同页面
     * @return
     */
    @RequestMapping("/tocon")
    public String toCon(){
        return "SystemMa/contractMa";
    }

    /**
     * 跳到合同页面
     * @return
     */
    @RequestMapping("/contractTemplate")
    public String toContractTemplate(){
        return "SystemMa/contractTemplate";
    }

    /**
     * 跳转到流程管理界面
     *
     * @return
     */
    @RequestMapping("toFlowMa")
    public String toFlowMa()
    {
        return "SystemMa/flowMa";
    }

    /**
     * 跳到房源信息管理页面
     * @return
     */
    @RequestMapping("/tohouseInfo")
    public String tohouseInfo(){
        return "houseInfoMa/houseInfo";
    }
    /**
     * 跳到房源分配页面
     * @return
     */
    @RequestMapping("/tohouseDis")
    public String tohouseDis(){
        return "houseInfoMa/houseDis";
    }

    /**
     * 保障房申请已审核页面
     * @return
     */
    @RequestMapping("/toAuditApply")
    public String toAuditApply(){
        return "houseApply/auditApply";
    }
    /**
     * 保障房申请待审核页面
     * @return
     */
    @RequestMapping("/toNoAuditApply")
    public String toNoAuditApply(){
        return "houseApply/noauditApply";
    }

    /**
     * 跳到房源配给页面
     * @return
     */
    @RequestMapping("/tohouseRation")
    public String tohouseRation(){
        return "houseInfoMa/houseRation";
    }

    /**
     * 跳到低保特困公租房
     * @return
     */
    @RequestMapping("/toLowRentPubHousing")
    public String toLowRentPubHousing(){
        return "applicationForm/lowRentPublicHousing/lowRentPublic";
    }

    /**
     * 跳到低保特困住保补贴
     * @return
     */
    @RequestMapping("/lowPaulTrap")
    public String lowPaulTrap(){
        return "applicationForm/lowPaulTrapProtection/lowPaulTrap";
    }

    /**
     * 跳到字典页面
     * @return
     */
    @RequestMapping("/tomirapply")
    public String toMigrantWorkersApply(){
        return "applicationForm/migrantWorkers/wailaiwg";
    }

    /**
     * 跳到到测试页面
     * @return
     */
    @RequestMapping("/toApplyType")
    public String toApplyType(){
        return "applicationForm/type";
    }

    /**
     * 跳到到测试页面
     * @return
     */
    @RequestMapping("/tomigrantWorkers")
    public String toFileTest(){
        return "applicationForm/migrantWorkers/migrantWorkers";
    }

    /**
     * 跳到经适房/廉租申请页面
     * @return
     */
    @RequestMapping("/toEco")
    public String toEco(){
        return "applicationForm/economicApplication/jingsf";
    }

    @RequestMapping("toApplyIndex")
    public String toApplyIndex(){
        return "applicationForm/index";
    }

    /**
     * 退房页面
     * @return
     */
    @RequestMapping("toReturnHouse")
    public String toReturnHouse(){
        return "houseInfoMa/returnhouse";
    }

    @RequestMapping("toNotification")
    public String toNotification(){
        return "houseInfoMa/notification";
    }

    @RequestMapping("toApplyChange")
    public String toApplyChange(){
        return "applyChange/applyChangeList";
    }
    @RequestMapping("toChangeHouse")
    public String toChangeHouse(){
        return "houseChange/houseChange";
    }
    @RequestMapping("toQuitHouse")
    public String toQuitHouse(){
        return "houseInfoMa/housequit";
    }
    @RequestMapping("toHisTable")
    public String toHisTable(){
        return "houseInfoMa/reportDialog/disHistory";
    }
    @RequestMapping("toFenPeiTable")
    public String toFenPeiTable(){
        return "houseChange/changeDialog/changeRecord";
    }

    //所有附件弹出说明
    @RequestMapping("toAttachment")
    public String toAttachment(){
        return "applicationForm/attchNotation/attachNotation";
    }
    //修改密码
    @RequestMapping("toChangePwd")
    public String toChangePwd(){
        return "index/changePwd";
    }


    @RequestMapping("toSign")
    public String toSign(){
        return "houseApply/applyDialog/sign";
    }

    @RequestMapping("toActiv")
    public String toActiv(){
        return "registMa/activate";
    }
}