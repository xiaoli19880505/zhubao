package com.sys.controller.home;

import com.sys.service.home.ColumnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/13 0012
 * @desc 栏目controller
 */
@Controller
@RequestMapping("columnInfo")
public class ColumnInfoController {

    @Autowired
    private ColumnInfoService columnInfoService;

    @RequestMapping("controller")
    public String showController(){
        return "home/index";
    }

    /**
     * 展示列表信息（暂时不用）
     * @param request
     * @return
    @RequestMapping("indexJson")
    @ResponseBody
    public Object showIndexJson(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("id","501");
        map1.put("text","栏目内容信息");
        map1.put("src",basePath + "pageColumn/column");
        list.add(map1);
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("id","502");
        map2.put("text","图片上传");
        map2.put("src",basePath + "pageColumn/photo");
        list.add(map2);
        Map<String,Object> map3 = new HashMap<String, Object>();
        map3.put("id","503");
        map3.put("text","文章内容信息");
        map3.put("src",basePath + "pageColumn/text");
        list.add(map3);
        JSONArray jsonObj = JSONArray.fromObject(list);
        return jsonObj;
    }*/


    /**
     * 栏目新增方法
     * @param request
     * @return
     */
    @RequestMapping("saveColumnInfo")
    @ResponseBody
    public Object saveColumnInfo(HttpServletRequest request) {
        String columnCode = request.getParameter("columnCode"); // 栏目编码
        String columnName = request.getParameter("columnName"); // 栏目名称
        String url = request.getParameter("url"); // url
        String sequence = request.getParameter("sequence"); // 栏目排序位置
        String downFlag = request.getParameter("downFlag");//是否为下载中心（T:是F否）
        return columnInfoService.saveColumnInfo(columnCode,columnName,url,sequence,downFlag,request);
    }

    /**
     * 删除栏目方法
     * @param request
     * @return
     */
    @RequestMapping("deleteById")
    @ResponseBody
    public Object deleteById(HttpServletRequest request){
        String id = request.getParameter("id");
        return columnInfoService.deleteById(id,request);
    }

    /**
     * 修改栏目方法
     * @param request
     * @return
     */
    @RequestMapping("updateColumnInfo")
    @ResponseBody
    public Object updateColumInfo(HttpServletRequest request){
        String columnCode = request.getParameter("columnCode"); // 栏目编码
        String columnName = request.getParameter("columnName"); // 栏目名称
        String url = request.getParameter("url"); // url
        String sequence = request.getParameter("sequence"); // 栏目排序位置
        String id = request.getParameter("id"); // id
        String downFlag = request.getParameter("downFlag");//是否为下载中心（T:是F否）
        return columnInfoService.updateColumInfo(id,columnCode,columnName,url,sequence,downFlag,request);
    }

    /**
     * 查询栏目方法
     * @param request
     * @return
     */
    @RequestMapping("selectColumnInfo")
    @ResponseBody
    public Object selectByConditions(HttpServletRequest request){
        String columnName = request.getParameter("columnName"); // 栏目名称
        return columnInfoService.selectByConditions(columnName);
    }

    /**
     * 查询栏目、文章tree方法
     * @param request
     * @return
     */
    @RequestMapping("getAllTree")
    @ResponseBody
    public Object getAllTree(HttpServletRequest request){
        String columnName = request.getParameter("columnName"); // 栏目名称
        return columnInfoService.getAllTree();
    }

    /**
     * 查询栏目、文章tree方法jstl
     * @param request
     * @return
    @RequestMapping("getAllTreeByJstl")
    public String getAllTreeByJstl(HttpServletRequest request, HttpServletResponse response){
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("小明");
        request.setAttribute("list",list);
        return "home/external/hhh";
    }
     */

}
