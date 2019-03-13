package com.sys.controller.house;

import com.sys.pojo.ItemCodeInfo;
import com.sys.service.BuidInfoService;
import com.sys.service.ItemCodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/ItemCodeInfo")
public class ItemCodeInfoController {

    @Autowired
    private ItemCodeInfoService itemCodeInfoService;
    @Autowired
    private BuidInfoService buidInfoService;


    @RequestMapping("/select")
    @ResponseBody
    public Object select(){
        List<ItemCodeInfo> list=this.itemCodeInfoService.selectList();
        System.out.println(list);
        return list;
    }

    @RequestMapping("/import")
    @ResponseBody
    public String impotr(HttpServletRequest request) throws Exception {
        int adminId = 1;
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upfile");
        InputStream in = file.getInputStream();
        //数据导入
        String result= itemCodeInfoService.importExcelInfo(in,file);
        in.close();
        return result;
    }
    @RequestMapping("/deleteItem")
    @ResponseBody
    public Object deleteItem(String itemid){
        String result="";
        int buidCount=buidInfoService.findCountByItemId(itemid);
        if(buidCount>0){
            result="请先删除该项目下的楼栋";
        }else{
            int deletCount=itemCodeInfoService.delete(itemid);
            if(deletCount>0){
                result="删除成功";
            }else{
                result="删除失败";
            }
        }
        return result;
    }

}
