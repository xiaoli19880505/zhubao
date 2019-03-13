package com.sys.controller.house;

import com.sys.pojo.FactMapping;
import com.sys.service.FactMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/FactMapping")
public class FactMappingController {

    @Autowired
    private FactMappingService factMappingService;


    @RequestMapping("/findFactByItemid")
    @ResponseBody
    public Object findFactByItemid(String buildid,String fylx){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("buildid",buildid);
        map.put("fylx",fylx);
        List<FactMapping> list=this.factMappingService.findFactByItemid(map);
        return  list;
    }

    @RequestMapping("/findFactByBuildingInfoId")
    @ResponseBody
    public Object findFactByBuildingInfoId(String buildid){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("buildid",buildid);
        List<FactMapping> list=this.factMappingService.findFactByBuildingInfoId(map);
        return  list;
    }
    @RequestMapping("/findFactByMap")
    @ResponseBody
    public Object findFactByMap(String buildid,String fylx,String ssq){
        System.out.println("**************"+buildid);
        System.out.println("**************"+fylx);
        System.out.println("**************"+ssq);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("buildid",buildid);
        map.put("fylx",fylx);
        map.put("ssq",ssq);
        List<FactMapping> list=this.factMappingService.findFactByMap(map);
        return  list;
    }


    @RequestMapping("/findFactlxByBid")
    @ResponseBody
    public Object findFactlxByBid(String xmid,String lpid,String fylx){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("xmid",xmid);
        map.put("lpid",lpid);
        map.put("fylx",fylx);
        List<FactMapping> list=this.factMappingService.findFactlxByBid(map);
        return  list;
    }

    @RequestMapping("/import")
    @ResponseBody
    public String impotr(HttpServletRequest request) throws Exception {
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upfile");
        String buildid=request.getParameter("buildid");
        InputStream in = file.getInputStream();
        //数据导入
        String result= factMappingService.importExcelInfo(in,buildid,file);
        in.close();
        return result;
    }

    /**
     * 删除房屋
     * @param idsArray
     * @return
     */
    @RequestMapping("/deleteHouses")
    @ResponseBody
    public Object deleteHouses(String idsArray){
        String result="";
        int deleteCount=factMappingService.deleteHouse(idsArray);
        if(deleteCount>0){
            result="删除成功";
        }else{
            result="删除失败";
        }
        return result;
    }

    @RequestMapping("/findFactByApplyid")
    @ResponseBody
    public Object findFactByApplyid(String applyid){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("applyid",applyid);
        List<FactMapping> list=this.factMappingService.findFactByApplyid(map);
        return  list;
    }

    @RequestMapping("/findFactCountByApplyid")
    @ResponseBody
    public Object findFactCountByApplyid(String applyid){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("applyid",applyid);
        int count =this.factMappingService.findFactCountByApplyid(map);
        return  count;
    }

    /**
     * 根据类型跳转选房确认单页面
     * @param houseIds
     * @param request
     * @return
     */
    @RequestMapping("/findFactByFwId")
    public Object findFactByFwId(String houseIds,HttpServletRequest request){
        String path="";
        String atype=request.getParameter("atype");
        String username=request.getParameter("username");
        String sfzh=request.getParameter("sfzh");
        String ssq=request.getParameter("ssq");
        Map<String,Object> map=new HashMap<String, Object>();

        if(ssq.equals("0")){
            ssq="市区";
        }else if(ssq.equals("1")){
            ssq="云龙区";
        }else if(ssq.equals("2")){
            ssq="鼓楼区";
        }else if(ssq.equals("3")){
            ssq="泉山区";
        }else if(ssq.equals("4")){
            ssq="经济技术开发区";
        }

        List<String> list= Arrays.asList(houseIds.split(","));
        map.put("list",list);
        List<FactMapping> facList=this.factMappingService.findFactByFwId(map);
        FactMapping factMapping=null;
        if(facList!=null&&facList.size()>0){

                factMapping=facList.get(0);
                StringBuffer romstr = new StringBuffer();
                StringBuffer arecstr = new StringBuffer();
                for (int i = 0; i <facList.size() ; i++) {
                    if(i == 0){
                        romstr.append(facList.get(i).getfRonum());
                        arecstr.append(facList.get(i).getfConacre());
                    }else{
                        romstr.append("、"+facList.get(i).getfRonum());
                        arecstr.append("、"+facList.get(i).getfConacre());
                    }

                factMapping.setfRonum(romstr.toString());
                factMapping.setConacre(arecstr.toString());
            }
        }

        request.setAttribute("atype",atype);
        request.setAttribute("username",username);
        request.setAttribute("sfzh",sfzh);
        request.setAttribute("ssq",ssq);
        request.setAttribute("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        request.setAttribute("factMapping",factMapping);

        if(atype.equals("1")){//经适房确认

            if(ssq.equals("经济技术开发区")){
                path="houseInfoMa/reportDialog/jkfConfirm";
            }else{
                path="houseInfoMa/reportDialog/jglConfirm";
            }

        }else if(atype.equals("2")){//低保特困
            path="houseInfoMa/reportDialog/dbConfirm";
        }else{//外来务工新就业
            path="houseInfoMa/reportDialog/gzfConfirm";
        }


        return  path;
    }
}
