package com.sys.controller.sysma;

import com.github.pagehelper.PageInfo;
import com.sys.common.CommonUtils;
import com.sys.pojo.Parm;
import com.sys.pojo.ParmItem;
import com.sys.pojo.RoleInfo;
import com.sys.service.ParmItemService;
import com.sys.service.ParmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc
 */
@Controller
@RequestMapping("/parm")
public class ParmController {
    @Autowired
    ParmService parmService;
    @Autowired
    ParmItemService parmItemService;
    /**
     * 新增字典页面
     * @return
     */
    @RequestMapping("addParm")
    public String toAddParm()
    {
        return "SystemMa/dictionaryMa/dictionaryAdd";
    }
    /**
     * 修改字典页面
     * @return
     */
    @RequestMapping("editParm")
    public String toEditParm()
    {
        return "SystemMa/dictionaryMa/dictionaryEdit";
    }

    /**
     * 分页查询字典列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectParmByPage")
    @ResponseBody
    public Object selectParmByPage(String page,String rows){
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        map.put("rows",rows);
        PageInfo<Parm> parms=parmService.pageParm(map);
        map.remove("page");
        map.remove("rows");
        map.put("rows",parms.getList());
        map.put("total",parms.getTotal());

        return map;
    }
    @RequestMapping("/savaParm")
    @ResponseBody
    public Object savaParm(Parm parm){
        String result="";
        Map<String,Object> map=new HashMap();
        map.put("prSetcode",parm.getPrSetcode());
        int countCode=parmService.parmCount(map);
        if(countCode>0){
            result="字典编号已存在";
        }else{
            map.remove("prSetcode");
            map.put("prSetName",parm.getPrSetName());
            int countName=parmService.parmCount(map);
            if(countName>0){
                result="字典名称已存在";
            }else{
                parm.setPrId(CommonUtils.getUUIDWith_());
                int savaCount=parmService.insert(parm);
                if(savaCount>0){
                    result="添加成功";
                }else{
                    result="添加失败";
                }
            }
        }
        return result;
    }
    @RequestMapping("/uodateParm")
    @ResponseBody
    public Object uodateParm(Parm parm){
        String result="";
        Map<String,Object> map=new HashMap();
        map.put("prSetName",parm.getPrSetName());
        map.put("prSetcode", parm.getPrSetcode());
        int countParm=parmService.countParmExceptSelf(map);
        if(countParm>0){
            result="相同字典名称已存在";
        }else{
            int updateCount=parmService.update(parm);
            if(updateCount>0){
                result="修改成功";
            }else{
                result="修改失败";
            }
        }
        return result;
    }
    @RequestMapping("/deletParm")
    @ResponseBody
    public Object deletParm(String prSetcode){
        String result="";

        Map<String,Object> map=new HashMap();
        map.put("piSetcode",prSetcode);
        map.put("page","1");
        map.put("rows","20");

        PageInfo<ParmItem> parmItemPageInfo=parmItemService.pageParm(map);
        if(parmItemPageInfo.getTotal()>0){
            result="该字典包含子级字典信息，不可以删除";
        }else{
            int deleteCount=parmService.delete(prSetcode);
            if(deleteCount>0){
                result="删除成功";
            }else{
                result="删除失败";
            }
        }

        return result;
    }
}
