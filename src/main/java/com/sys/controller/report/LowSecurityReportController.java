package com.sys.controller.report;

import com.sys.pojo.UserInfo;
import com.sys.service.report.LowSecurityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("lowSecurityReport")
public class LowSecurityReportController {

    @Autowired
    private LowSecurityReportService lowSecurityReportService;

    /**
     * 报表导出
     * @param request
     */
    @RequestMapping("report")
    @ResponseBody
    public Object getRetain(HttpServletRequest request, HttpServletResponse response){
        String yearAndMonth = request.getParameter("yearAndMonth");//查询月份
        String type = request.getParameter("type");//类型
        try {
            if("001".equals(type)){//局补贴汇总报表
                return lowSecurityReportService.getRetain(yearAndMonth,response);
            }else if("002".equals(type)){//住保中心补贴清册报表
                HttpSession session = request.getSession();
                UserInfo userInfo = (UserInfo)session.getAttribute("user");
                return lowSecurityReportService.getLiveInsuranceCenterRetain(yearAndMonth,userInfo,response);
            }else if("003".equals(type)){//银行补贴明细报表
                return lowSecurityReportService.getPay(yearAndMonth,response);
            }else if("004".equals(type)){//公示补贴名单报表
                return lowSecurityReportService.getPublicity(yearAndMonth,response);
            }else{
                return "请选择类型!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
