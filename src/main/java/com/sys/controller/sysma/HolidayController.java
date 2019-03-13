package com.sys.controller.sysma;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.*;
import com.sys.pojo.Holiday;
import com.sys.pojo.UserInfo;
import com.sys.service.sysma.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private BatchUtil batchUtil;

    /**
     * 跳转到假期列表页面
     * @return
     */
    @RequestMapping("toholidaylist")
    public String toHolidayList(){
        return "SystemMa/holiday";
    }


    /**
     * 跳转到假期新增页面
     * @return
     */
    @RequestMapping("toAddHoliday")
    public String toAddHoliday(){
        return "SystemMa/holidayMa/holidayAdd";
    }

    /**
     * 跳转到工作日新增页面
     * @return
     */
    @RequestMapping("toAddWeekday")
    public String toAddWeekday(){
        return "SystemMa/holidayMa/weekdayAdd";
    }



    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "findHoliday",method =RequestMethod.POST)
    @ResponseBody
    public Object findHolidayList(HttpServletRequest request){
        String year = request.getParameter("year");//条件查询：年查询
        String type = request.getParameter("type");//条件查询：类别查询
        String rows = request.getParameter("rows");//条件查询：页行数
        String page = request.getParameter("page");//条件查询：页标
        String date = request.getParameter("date");//条件查询：日期查询
        int pageIndex = 0;
        int pageSize = 20;
        if(!StringUtils.isEmpty(page)){
            pageIndex = Integer.parseInt(page);
        }
        if(!StringUtils.isEmpty(rows)){
            pageSize = Integer.parseInt(rows);
        }
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("year",year);
        conditionMap.put("type",type);
        conditionMap.put("date",date);
        PageHelper.startPage(pageIndex,pageSize);
        PageInfo pageInfo = new PageInfo(holidayService.selectByMap(conditionMap));

        Map<String,Object> resultnMap = Maps.newHashMap();
        resultnMap.put("rows",pageInfo.getList());
        resultnMap.put("total",pageInfo.getTotal());
        return resultnMap;
    }

    /**
     * 插入节假日
     * @return
     */
    @RequestMapping(value = "addHoliday",method = RequestMethod.POST)
    @ResponseBody
    public Object addHoliday(@RequestParam(value = "beginDate") String beginDate,@RequestParam(value = "endDate")String endDate,String desc,HttpServletRequest request) throws Exception {

        UserInfo userInfo =(UserInfo)request.getSession().getAttribute("user");

       // String beginDate = request.getParameter("beginDate");//开始日期
        //String endDate = request.getParameter("endDate");//结束日期
       // String desc = request.getParameter("desc");//相关描述
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DatetimeUtils.string2date(beginDate, "yyyy-MM-dd"));//按照日期格式进行转换

            Long begin = DatetimeUtils.string2date(beginDate, "yyyy-MM-dd").getTime();//获取开始日期毫秒数
            Long end = DatetimeUtils.string2date(endDate, "yyyy-MM-dd").getTime();//获取截止日期毫秒数

            long days = (end - begin) / (1000 * 60 * 60 * 24)+1;//获取节假日天数
            List<Holiday> holidayList = Lists.newArrayList();
            Map<String,Object> conditionMap = Maps.newHashMap();//year

            /*插入节假日的信息*/
            calendar.add(Calendar.DATE, -1);//日期先退回到前一天，节假日包括首尾日期都在内
            for (int i = 0; i < days; i++) {
                Holiday holiday = new Holiday();
                calendar.add(Calendar.DATE, 1);//日期
                /*判断日期在数据库中是否存在*/
                conditionMap.put("date",DatetimeUtils.date2string(calendar.getTime(), "yyyy-MM-dd"));
                int count = this.holidayService.selectCountMap(conditionMap);
                if(count>0){
                    return "存在重复的日期:"+DatetimeUtils.date2string(calendar.getTime(), "yyyy-MM-dd");
                }
                holiday.setFullDate(DatetimeUtils.date2string(calendar.getTime(), "yyyy-MM-dd"));
                holiday.setHid(CommonUtils.getUUIDWith_());//uuid
                holiday.setInsertTime(new Date());//插入时间为当前时间
                holiday.setIsWeekday("0");//为非工作日
                holiday.setRemark(desc);//插入描述
                holiday.setInsertUserid(userInfo.getUserid());//设置用户id
                holidayList.add(holiday);
                // holiday.setDay(""+calendar.get(Calendar.DAY_OF_MONTH));
                // holiday.setMonth(""+calendar.get(Calendar.DAY_OF_MONTH));
            }

            /*批量插入节假日*/
            batchUtil.save(holidayList, "HolidayMapper");
        }catch (Exception e){
            e.printStackTrace();;
            throw new Exception("插入失败");
        }
        return "插入成功";
    }


    /**
     * 插入工作日（针对将周六、周末设为工作日的特殊情况）
     * @return
     */
    @RequestMapping(value = "addWeekDay",method = RequestMethod.POST)
    @ResponseBody
    public Object addWeekDay(@RequestParam(value = "weekDay")String weekDay,String desc,HttpServletRequest request) throws Exception {

        UserInfo userInfo =(UserInfo)request.getSession().getAttribute("user");

        /*String weekDay = request.getParameter("weekDay");//开始日期
        String desc = request.getParameter("desc");//相关描述*/
        Map<String,Object> conditionMap = Maps.newHashMap();//year
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DatetimeUtils.string2date(weekDay,"yyyy-MM-dd"));
            /*判断日期是否是周六或者周末*/
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek!=1 && dayOfWeek!=7){//特殊日期，必须是周六或者周末设为工作日
                return "日期必须是周六或者周末";
            }

            /*判断日期在数据库中是否存在*/
            conditionMap.put("date",weekDay);
            int count = this.holidayService.selectCountMap(conditionMap);
            if(count>0){
                return "存在重复的日期:"+DatetimeUtils.date2string(calendar.getTime(), "yyyy-MM-dd");
            }
           Holiday holiday = new Holiday();
            holiday.setHid(CommonUtils.getUUIDWith_());//设置uuid
            holiday.setRemark(desc);//设置描述
            holiday.setIsWeekday("1");//设置为工作日描述
            holiday.setFullDate(weekDay);//设置日期为当前日期
            holiday.setInsertUserid(userInfo.getUserid());//设置用户为当前用户
            holiday.setInsertTime(new Date());//设置插入时间为当前时间
            /*插入工作日*/
            holidayService.insert(holiday);
        }catch (Exception e){
            e.printStackTrace();;
            throw new Exception("插入失败");
        }
        return "插入成功";
    }


    /**
     * 删除设置的节假期或者特殊工作日的信息
     * @return
     */
    @RequestMapping("deleteSetDay")
    @ResponseBody
    public Object deleteSetDay(@RequestParam(value = "holid" ) String holid)  {

        String result = "";
        int num = holidayService.delete(holid);//删除所设置项
        if(num>0){
            result="删除成功";
        }else{
            result="删除失败";
        }
        return result;
    }
}
