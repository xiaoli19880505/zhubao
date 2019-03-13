package com.sys.controller.sysma;


import com.github.pagehelper.PageInfo;
import com.sys.common.CommonUtils;
import com.sys.pojo.Parm;
import com.sys.pojo.ParmItem;
import com.sys.service.ParmItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ParmItem")
public class ParmItemController {

    @Autowired
    private ParmItemService parmItemService;

    /**
     * 新增右侧字典页面
     * @return
     */
    @RequestMapping("addParmImte")
    public String toAddParmImte()
    {
        return "SystemMa/dictionaryMa/dictionaryRightAdd";
    }
    /**
     * 修改右侧字典页面
     * @return
     */
    @RequestMapping("toEditParmImte")
    public String toEditParmImte()
    {
        return "SystemMa/dictionaryMa/dictionaryRightEdit";
    }

    @RequestMapping("findAllParmImteByChildren")
    @ResponseBody
    public Object findAllParmImteByChildren(ParmItem parmItem){
        ParmItem parmItem1=this.parmItemService.findAllParmImteByChildren(parmItem);
        return parmItem1.getChildren();
    }

    @RequestMapping("/findAllQu")
    @ResponseBody
    public Object findAllQu(){
        List<ParmItem> list=this.parmItemService.findAllQu();
        return  list;
    }

    @RequestMapping("findAllJd")
    @ResponseBody
    public  Object findAllJd(String qid){
        List<ParmItem> list=this.parmItemService.findAllJd(qid);
        return list;
    }







    @RequestMapping("/selectByPage")
    @ResponseBody
    public Object selectByPage(String rows,String page,String piSetcode){
        Map<String,Object> map=new HashMap();
        if(piSetcode!=null &&piSetcode!=""){
            map.put("piSetcode",piSetcode);
            if(page==null||page==""){
                page="1";
            }
            if(rows==null||rows==""){
                rows="20";
            }

            map.put("page",page);
            map.put("rows",rows);

            PageInfo<ParmItem> parms=parmItemService.pageParm(map);
            map.remove("page");
            map.remove("rows");
            map.put("rows",parms.getList());
            map.put("total",parms.getTotal());
        }

        return map;
    }

    /**
     * 新增数据字典 右侧数据
     * @param parmItem 字典对象
     * @param parmSetcode 左侧字典ID
     * @param parentSetcode 右侧上级ID
     * @return
     */
    @RequestMapping("/saveParmItem")
    @ResponseBody
    public Object saveParmItem(ParmItem parmItem,String parmSetcode,String parentSetcode){
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("piSetcode",parmSetcode);
//        map.put("piItemcode",parentSetcode);
        map.put("piItemname",parmItem.getPiItemname());

        /**
         * 判断同一字典类型下是否存在相同编号或者相同名称
         */
        //根据右侧字典ID和上级ID查询条目，如果有数据，说明

        int count=parmItemService.countParm(map);

//        List<ParmItem> list=parmItemService.selectBySetCodeAndItemCode(map);
        if(count>0){
            result="相同字典已存在";
        }else{
//            map.remove("piItemcode");
//            map.put("piItemname",parmItem.getPiItemname());
//            List<ParmItem> list2=parmItemService.selectBySetCodeAndItemCode(map);
//            if (list2!=null&&list2.size()>0){
//                result="相同字典已存在";
//            }else{
//                map.remove("piItemname");
                String maxItemCoe=parmItemService.getMaxPiItemCode(map);
                if("20".equals(parmSetcode)
                        ||"23".equals(parmSetcode)){
                    if(Integer.valueOf(maxItemCoe).intValue()>0){
                        result="字典字段唯一，不可添加";
                    }else{
                        parmItem.setPiItemcode(CommonUtils.getCode(maxItemCoe));
                        parmItem.setPiSetcode(parmSetcode);
                        int saveCount=parmItemService.insert(parmItem);
                        if(saveCount>0){
                            result="新增成功";
                        }else{
                            result="新增失败";
                        }
                    }
                }else{
                    parmItem.setPiItemcode(CommonUtils.getCode(maxItemCoe));
                    parmItem.setPiSetcode(parmSetcode);
                    int saveCount=parmItemService.insert(parmItem);
                    if(saveCount>0){
                        result="新增成功";
                    }else{
                        result="新增失败";
                    }
                }
        }
        return result;
    }

    /**
     * 修改右侧字典
     * @param parmItem
     * @param parmSetcode 左侧字典ID
     * @return
     */
    @RequestMapping("/updateParmItem")
    @ResponseBody
    public Object updateParmItem(ParmItem parmItem,String parmSetcode,String piItemcode){
        String result="";

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("piSetcode",parmSetcode);
        map.put("piItemcode",piItemcode);
        map.put("piItemname",parmItem.getPiItemname());
        System.out.println("parmSetcode*************"+parmSetcode);
        System.out.println("piItemcode*************"+piItemcode);
        System.out.println("parentSetcode**********"+parmItem.getPiParentsetcode());
        System.out.println("piItemname**************"+parmItem.getPiItemname());
        /**
         * 判断同一字典类型下是否存在相同编号或者相同名称
         */
        int count=parmItemService.countParm(map);
        //根据右侧字典ID和上级ID查询条目，如果有数据多于1，说明有相同的
//        List<ParmItem> list=parmItemService.selectBySetCodeAndItemCode(map);
        if(count>0){
            result="相同字典已存在";
        }else{
//            map.remove("piItemcode");
//            map.put("piItemname",parmItem.getPiItemname());
//            List<ParmItem> list2=parmItemService.selectBySetCodeAndItemCode(map);
//            if (list2!=null&&list2.size()>1){
//                result="相同字典已存在";
//            }else{
                parmItem.setPiItemcode(piItemcode);
                parmItem.setPiSetcode(parmSetcode);
                int updateCount=parmItemService.update(parmItem);
                if(updateCount>0){
                    result="修改成功";
                }else{
                    result="修改失败";
                }
//            }
        }
        return result;
    }

    /**
     * 删除右侧字典
     * @param parmSetcode 左侧字典id
     * @param piItemcode 右侧字典的值
     * @return
     */
    @RequestMapping("/deletePamItem")
    @ResponseBody
    public Object deletePamItem(String parmSetcode,String piItemcode){
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("parmSetcode",parmSetcode);
        map.put("piItemcode",piItemcode);
        int deleteCount=parmItemService.deletParmItem(map);
        if(deleteCount>0){
            result="删除成功";
        }else {
            result="删除失败";
        }

        return result;
    }


    @RequestMapping("/getOptions")
    @ResponseBody
    public Object getOptions(String parmSetcode){
        Map<String,Object> map=new HashMap<String, Object>();
        if("04".equals(parmSetcode)){
            map.put("piSetcode","04");
            map.put("piItemcode","0");
        }else if("05".equals(parmSetcode)){
            map.put("piSetcode","04");
        }else if("06".equals(parmSetcode)){
            map.put("piSetcode","05");
        }else {
            map.put("piSetcode",parmSetcode);
        }
        List<ParmItem> list=parmItemService.selectBySetCodeAndItemCode(map);

        return list;
    }

    @RequestMapping("/xzqOrder")
    @ResponseBody
    public Object xzqOrder(){

        List<ParmItem> list=parmItemService.xzqOrder();

        return list;
    }



    @RequestMapping("/selectGxExceptPeiou")
    @ResponseBody
    public Object selectGxExceptPeiou(){
        List<ParmItem> list=this.parmItemService.selectGxExceptPeiou();
        return  list;
    }

    @RequestMapping("/selectSsqExceptCenter")
    @ResponseBody
    public Object selectSsqExceptCenter(){
        List<ParmItem> list=this.parmItemService.selectSsqExceptCenter();
        return  list;
    }

    @RequestMapping("/findFamylyOnlypozn")
    @ResponseBody
    public Object findFamylyOnlypozn(){
        List<ParmItem> list=this.parmItemService.findFamylyOnlypozn();
        return  list;
    }

}
