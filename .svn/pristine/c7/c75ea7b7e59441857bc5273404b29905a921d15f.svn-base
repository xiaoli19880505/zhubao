package com.sys.controller.sysma;

import com.sys.common.CommonUtils;
import com.sys.pojo.QxInfo;
import com.sys.pojo.extend.DataGridResult;
import com.sys.service.QxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("qxInfo")
public class QxInfoController {

    @Autowired
//    private QxInfoService qxInfoService;

    @RequestMapping("/toauthoradd")
    public String  toauthoradd(){
        return "SystemMa/authorityMa/authorityAdd";
    }

    @RequestMapping("toauthoraedit")
    public String  toauthoraedit(){
        return "SystemMa/authorityMa/authorityEdit";
    }


    @RequestMapping("findAllQxInfo")
    @ResponseBody
    public  Object findAllQxInfo(String page,String rows,String mid){
        Map<String,Object>  map=new HashMap<String, Object>();
        if (page==null || page==""){
            page="1";
        }
        map.put("page",page);
        map.put("rows",rows);
        map.put("mid",mid);
        DataGridResult dataGridResult= null;//this.qxInfoService.findAllQxInfo(map);
        return dataGridResult;
    }


    @RequestMapping("deleteQxInfo")
    public  Object deleteQxInfo(String qxid){
        String result="";
        Integer delete= 0;//this.qxInfoService.delete(qxid);
        if (delete>0){
            result="删除成功";
        }else {
            result="删除失败";
        }
        return  result;
    }

    @RequestMapping("updateQxInfo")
    public  Object updateQxInfo(QxInfo qxInfo){
        String result="";
        Integer updatecount= 0;//this.qxInfoService.update(qxInfo);
        if (updatecount>0){
            result="修改成功";
        }else{
            result="修改失败";
        }
        return result;
    }

    @RequestMapping("insertQxInfo")
    public  Object insertQxInfo(QxInfo qxInfo){
        String result="";
        Integer count=0;//this.qxInfoService.findQxinfoByNameOrMid(qxInfo.getQxName());
        if (count>0){
            result="模块下面有相同名称";
            return result;
        }
        qxInfo.setQxId(CommonUtils.getUUIDWith_());
        Integer updatecount= 0;//this.qxInfoService.insert(qxInfo);

        if (updatecount>0){
            result="新增成功";
        }else{
            result="新增失败";
        }
        return result;
    }


}
