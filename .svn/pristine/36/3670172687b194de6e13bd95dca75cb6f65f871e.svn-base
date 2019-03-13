package com.sys.service;

import com.sys.common.CommonUtils;
import com.sys.common.DatetimeUtils;
import com.sys.mapper.ListNumberMapper;
import com.sys.pojo.ListNumber;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:当日业务统计Service类
 * @Author:wangli
 * @CreateTime:16:43 2018/10/13
 */
@Service
public class ListNumberService extends BaseService<ListNumber> {

    @Autowired
    private ListNumberMapper listNumberMapper;

    /**
     * 生成申请业务编码
     * @param ssq 所属区
     * @param sqlb 所属业务类别
     * @return
     */
    public String addNewBM(String ssq,String sqlb){
        /*生成和设置申请编码*/
        Map<String,Object> conditionMap = new HashMap<String,Object>();
        conditionMap.put("type","0");//设置业务类型为0，即申请
        String lndate = DatetimeUtils.date2string(new Date(),"yyMMdd");
        conditionMap.put("date", lndate);
        ListNumber listNumber = listNumberMapper.getByMap(conditionMap);

        short lastNum=0;//业务量数目标记
        /*如果在listNum中不存在该条业务统计数据，则新增；否则更新最新的数目*/
        if(listNumber==null){
            listNumber = new ListNumber();//新建实体
            listNumber.setLnDate(lndate);//设置日期
            listNumber.setLnType("0");//设置请求类型
            lastNum=1;//当日业务熟练为1
            listNumber.setLnListnumber(lastNum);//设置业务统计数量
            listNumber.setLnId(CommonUtils.getUUIDWith_());//设置uuid
            this.listNumberMapper.insert(listNumber);//插入当日业务数据
        }else{
            lastNum=listNumber.getLnListnumber();
            lastNum++;
            listNumber.setLnListnumber(lastNum);//设置业务统计数量
            this.listNumberMapper.update(listNumber);//更新当日业务数据
        }

        /*在数字字符前面补充0*/
        StringBuffer sb = new StringBuffer(""+lastNum);
        int length = sb.length();
        for (int i=length;i<4;i++){
            sb.insert(0,"0");
        }
        String sqbm = ssq + sqlb + DateTime.now().toString("yyMMdd")+sb.toString();
        return  sqbm;
    }
}