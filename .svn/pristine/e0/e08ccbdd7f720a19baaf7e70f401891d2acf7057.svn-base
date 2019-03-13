package com.sys.controller;

import com.google.common.collect.Maps;
import com.sys.common.DatetimeUtils;
import com.sys.common.JedisUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.common.encrypt.AESUtil;
import com.sys.common.verification.CodeUtil;
import com.sys.pojo.ParmItem;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.service.ParmItemService;
import com.sys.service.ParmService;
import com.sys.service.RoleInfoService;
import com.sys.service.UserInfoService;
import com.sys.service.sysma.HolidayService;
import org.activiti.engine.TaskService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ParmItemService parmItemService ;

    @Autowired
    private RoleInfoService roleInfoService ;

    @Autowired
    private HolidayService holidayService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    /**
     * 审核用户登录
     * @param username  用户账号
     * @param password  用户登录密码
     * @param checkcode 用户验证码
     * @param request
     * @return
     */
    @RequestMapping("/submitLogin")
    public String submitLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password")String password, @RequestParam(value = "checkcode")String checkcode, HttpServletRequest request){

        HttpSession session = request.getSession();

        /*清空首尾字符*/
        username=username.trim();
        password=password.trim();
        checkcode=checkcode.trim();
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        logger.debug("password:--"+password+"---username:"+username);

        String goUrl = "index/login";
       // String indexUrl ="index/index";
        /*判断验证码是否正确*/
        String sessionCode = (String)session.getAttribute("code");
       // System.out.println("sessionCode:"+sessionCode+"---checkcode:"+checkcode);
        if(!checkcode.equalsIgnoreCase(sessionCode)){
           request.setAttribute("status", 600);
           request.setAttribute("message"," 验证码错误！");
            return goUrl;
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            logger.debug("AESUtil.encryptAES(password)--"+AESUtil.encryptAES(password));
            UsernamePasswordToken token = new UsernamePasswordToken(username, AESUtil.encryptAES(password));
            UserInfo userInfo=this.userInfoService.findUserByUserCode(username,AESUtil.encryptAES(password));
            if(userInfo==null){
                Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
                session.setAttribute("code",codeMap.get("code").toString());
                request.setAttribute("status", 300);
                request.setAttribute("message"," 帐号或密码错误！");
            }
            try{
                logger.debug("userInfoCode--"+userInfo.getUsercode()+"---"+userInfo);
                subject.login(token);
                //JedisUtils.del(username);
                logger.debug("userInfoCode--end");
                String name =SecurityUtils.getSubject().getPrincipal().toString();
                request.setAttribute("name",name);
                //session.setAttribute("user",userInfo);
                logger.debug("userInfoCode--name----"+name);
                Map<String,Object> conditionMap = Maps.newHashMap();
                conditionMap.put("userid",userInfo.getUserid());
                if(userInfo.getSsq().equals("0")){
                    /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
                    boolean flag = false;
                    List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
                    for(RoleInfo roleInfo:roleInfoList){
                        if(roleInfo.getDutyname().indexOf("公租房")!=-1){
                            flag=true;
                            break;
                        }
                    }
                    request.setAttribute("isShi","1");
                    if(flag){
                        request.setAttribute("area","公租房管理公司");
                    }else{
                        request.setAttribute("area","市住房保障中心");
                    }

                }else if (!StringUtils.isEmpty(userInfo.getSsj())){
                    request.setAttribute("area","街道办事处");
                }else{
                    /*需要判断用户是区民政局还是区住房保障部门，通过角色名判断*/
                    boolean flag = false;
                    List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
                    for(RoleInfo roleInfo:roleInfoList){
                        if(roleInfo.getDutyname().indexOf("民政局")!=-1){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        request.setAttribute("area","区民政局");
                    }else{
                        request.setAttribute("area","区住房保障部门");
                    }
                }
               request.setAttribute("overTimeMap",this.findPerOverTimeTask(userInfo));//查询待审批数量
                /*//设置session过期时间*/
               Session shiroSession = subject.getSession();//
                //shiroSession.setTimeout(Long.valueOf(PropertiesUtil.getOverTimeProperties("outtime")));//（毫秒）三十分钟1800000
                shiroSession.setAttribute("user",userInfo);//用户信息

                goUrl="index/index";//跳转到首页
            } catch (LockedAccountException dax) {
                request.setAttribute("status", 500);
                request.setAttribute("message"," 帐号已被禁用！！");
            } catch (UnknownAccountException ae) {
                request.setAttribute("status", 300);
                request.setAttribute("message"," 帐号或密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goUrl;
    }


    /**
     * 查看个人待审批任务信息
     * @param userInfo
     * @return
     */
    private Map<String,Object> findPerOverTimeTask(UserInfo userInfo){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dateCount = 0;

       // parmItemService.selectBySetCodeAndItemCode()
        Map<String,Object> conditionMap = Maps.newHashMap();

        String ssq = userInfo.getSsq();//获取用户所在区
        if(StringUtils.isEmpty(ssq) || "0".equals(ssq)){
            ssq = "";
        }
        String ssj = userInfo.getSsj();//获取用户所在街道
        if(StringUtils.isEmpty(ssj)){
            ssj = "";
        }
        conditionMap.put("userid",userInfo.getUserid());//用户id
        /*当用户没有街道信息，则认为用户不是街道用户*/
        if(StringUtils.isEmpty(ssj)){
            /*当用户所属区为0的时，则为市区或者公租房管理公司用户*/
            if(ssq.equals("0")){
                List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
                for(RoleInfo roleInfo:roleInfoList){
                    if(roleInfo.getDutyname().indexOf("管理公司")!=-1){
                        conditionMap.put("piItemcode","5");//查询公租房管理公司超时时间
                        break;
                    }
                }
                if(conditionMap.get("piItemcode")==null){
                    conditionMap.put("piItemcode","3");//查询市住保初审超时时间
                }
            }else{
                conditionMap.put("piItemcode","2");//区住保初审超时时间
            }
        }else{
            conditionMap.put("piItemcode","1");//区住保初审超时时间
        }

        conditionMap.put("piSetcode","21");//21为超时时间的数据字典关键码
        List<ParmItem> parmItemList = parmItemService.selectBySetCodeAndItemCode(conditionMap);
        dateCount=parmItemList.get(0).getPiItemvalue();
        Date beforeDate = getLastWeekDay(calendar,dateCount);
        /*查询过期的初审待审批数目*/
        Long   allCount = 0L;
        if(!conditionMap.get("piItemcode").equals("5")){
            allCount=this.taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("aplb","cs")
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .taskCreatedBefore(beforeDate)
                    .active()
                    .count();
        }

        /*查询过期的年审待审批数目*/
        Long   allNsCount = 0L;
        if(StringUtils.isEmpty(ssj) && "0".equals(ssq)){
            /*当当前用户角色不是公租房管理公司时，则改为市住保年审超时时间查询*/
            if(!conditionMap.get("piItemcode").equals("5")){
                conditionMap.put("piItemcode",4);
                dateCount=parmItemService.selectBySetCodeAndItemCode(conditionMap).get(0).getPiItemvalue();
            }
            beforeDate = getLastWeekDay(calendar,dateCount);
            allNsCount=this.taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("aplb","ns")
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .taskCreatedBefore(beforeDate)
                    .active()
                    .count();
        }


        Map<String,Object> resultMap = Maps.newHashMap();
        if(allCount>0){
            resultMap.put("csCount",allCount);
        }
        if(allNsCount>0){
            resultMap.put("nsCount",allNsCount);
        }
        return resultMap;
    }

    /**
     * 获取推延天数为dateCount的上一个工作日
     * @param calendar
     * @param dateCount
     * @return
     */
    private Date getLastWeekDay(Calendar calendar,int dateCount){
        Map<String,Object> conditionMap = Maps.newHashMap();
        for(int i=0;i<dateCount;i++){
            calendar.add(Calendar.DATE,-1);
            conditionMap.put("date",DatetimeUtils.date2string(calendar.getTime(),"yyyy-MM-dd"));//根据日期查询数据库是否存在
            int count = holidayService.selectCountMap(conditionMap);
            /*如果存在且是否为假期的标志位0，则表示当前日期为假期，则循环下标递减，则不加入计算之内*/
            if(count>0){
                String dayStr = holidayService.selectByMap(conditionMap).get(0).getIsWeekday();
                if(dayStr.equals("0")){
                    //calendar.add(Calendar.DATE,1);
                    i--;
                }
            }else{
                /*如果当前日期为周六或者周末，循环下标递减，则不加入计算之内*/
                int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
                if(weekDay==1 || weekDay==7){
                    i--;
                }
            }
        }
        return calendar.getTime();
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("toIndex")
    public String toIndex(){
        return "index/index";
    }

    


}
