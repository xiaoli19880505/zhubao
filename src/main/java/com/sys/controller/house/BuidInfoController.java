package com.sys.controller.house;


import com.sys.pojo.BuidInfo;
import com.sys.service.BuidInfoService;
import com.sys.service.FactMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/BuidInfo")
public class BuidInfoController {

    @Autowired
    private BuidInfoService buidInfoService;
    @Autowired
    private FactMappingService factMappingService;

    @RequestMapping("/findBuildInfoByIid")
    @ResponseBody
    public Object findBuildInfoByIid(String itemId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("itemId",itemId);
        List<BuidInfo> list=this.buidInfoService.findBuildInfoByIid(map);
        return list;
    }

    @RequestMapping("/findBuildInfoByMap")
    @ResponseBody
    public Object findBuildInfoByMap(String itemId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("itemId",itemId);
        List<BuidInfo> list=this.buidInfoService.findBuildInfoByMap(map);
        return list;
    }


    @RequestMapping("/import")
    @ResponseBody
    public String impotr(HttpServletRequest request) throws Exception {
        int adminId = 1;
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upfile");
        String itemid=request.getParameter("itemid");
        InputStream in = file.getInputStream();
        //数据导入
        String result= buidInfoService.importExcelInfo(in,itemid,file);
        in.close();
        return result;
    }

    /**
     * 根据楼栋ID删除楼栋
     * @param buid
     * @return
     */
    @RequestMapping("/deleteBuid")
    @ResponseBody
    public Object deleteBuid(String buid){
        String result="";
        //该楼栋下是否有房屋
        int houseCount=factMappingService.findFactMappingByBuId(buid);
        if(houseCount>0){
            result="请先删除该楼栋下的房屋";
        }else{
            int deleteCount=buidInfoService.delete(buid);
            if(deleteCount>0){
                result="删除成功";
            }else{
                result="删除失败";
            }
        }
        return result;
    }

}
