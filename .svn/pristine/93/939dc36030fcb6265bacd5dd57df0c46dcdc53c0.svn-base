package com.sys.service.apply;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sys.common.BatchUtil;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.mapper.IMapper;
import com.sys.mapper.VolelearcDtlMapper;
import com.sys.mapper.VolelearcMapper;
import com.sys.mapper.apply.*;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.Volelearc;
import com.sys.pojo.apply.*;
import com.sys.service.BaseService;
import com.sys.service.FlowService;
import com.sys.service.ListNumberService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:16:34 2018/10/18
 */
@Service
public class ApplyNsService extends BaseService<ApplyNs>{

    @Autowired
    private ListNumberService listNumberService;

    @Autowired
    private BatchUtil batchUtil;

    @Autowired
    private ApplyFamilyHouseChangeMapper applyFamilyHouseChangeMapper;

    @Autowired
    private FlowService flowService;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ApplyFamilyMemberMapper applyFamilyMemberMapper;

    @Autowired
    private ApplyFamilyHouseMapper applyFamilyHouseMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    public ApplyNsMapper applyNsMapper;

    @Autowired
    public ApplyUnitMapper applyUnitMapper;

    @Autowired
    private VolelearcDtlMapper volelearcDtlMapper;

    @Autowired
    private VolelearcMapper volelearcMapper;


    public ApplyNs selectByMap(Map map){
        return applyNsMapper.selectByMap(map);
    }


    /**
     * 添加申请信息
     * @return
     */
    public String addApply(ApplyNs pojo, HttpServletRequest request){
        int updateColumn = 0;
        String applytype = pojo.getApSqlb();//申请类别
        /**
         * 在添加之前，先判断用户是否有申请的外来务工单没有走完流程，如果存在，则不得申请；
         * 流程走完的标志是已经分配完房子，并且备案
         * 此块的业务逻辑留由以后再开发
         */
        String appid = CommonUtils.getUUIDWith_();//生成申请单id
        pojo.setAlsid(appid);//设置年审申请单id
        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();//获取所有家庭成员信息

        /*生成并设置申请编号*/
        //String applytype= PropertiesUtil.getApplyTypeProperties(sqType);
        String sqbh = this.listNumberService.addNewBM(pojo.getApSsq(), applytype);
        pojo.setApSqbh(sqbh);



        /*插入家庭成员信息*/
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(appid);
            // this.applyFamilyMemberMapper.insert(tempMem);
        }
        /*设置申请人id和申请人配偶id*/
        pojo.setApSqrid(members.get(0).getAfmId());
        members.get(0).setAfmYsqrgx("本人");
        /*如果是补贴，公租房，还要填写配偶信息*/
//        if(applytype.equals(PropertiesUtil.getApplyTypeProperties("butie"))
//                || pojo.getApSqlb().equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
//            pojo.setApSqrpoid(members.get(1).getAfmId());
//            members.get(1).setAfmYsqrgx("配偶");
//        }
        /*插入申请单*/
        updateColumn=super.insert(pojo);
        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");//批量插入家庭成员信息


        /*设置申请住房信息，并插入住房信息*/
        List<ApplyFamilyHouse> applyHouse = pojo.getApplyFamilyHouses();
        for(ApplyFamilyHouse tempHou:applyHouse){
            tempHou.setAfhSqid(appid);
            tempHou.setAfhId(CommonUtils.getUUIDWith_());
        }
        batchUtil.save(applyHouse,"apply.ApplyFamilyHouseMapper");//批量插入住房信息

        /*如果申请类别为经济适应房，补贴，公租房，则设置更换住房信息并插入*/
        if(applytype.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))
                ||applytype.equals(PropertiesUtil.getApplyTypeProperties("butie"))
                ||applytype.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
            ApplyFamilyHouseChange applyFamilyHouseChange = pojo.getApplyFamilyHouseChange();
            applyFamilyHouseChange.setAfhcId(CommonUtils.getUUIDWith_());
            applyFamilyHouseChange.setAfhcSqid(appid);
            applyFamilyHouseChangeMapper.insert(applyFamilyHouseChange);
        }

//        if(applytype.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))
//                ||applytype.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            ApplyUnit applyUnit = pojo.getApplyUnit();
            applyUnit.setUnitid(CommonUtils.getUUIDWith_());
            applyUnit.setAplid(appid);
            applyUnitMapper.insert(applyUnit);
//        }


        if(updateColumn>0){
            /*创建审批单,启动流程实例*/
            Approve approve = new Approve();//审批单实体
            approve.setApldate(new Date());//设置审批单的申请日期
            approve.setAplbh(sqbh);//设置申请编号
            approve.setAplid(appid);//设置审批单对应的申请单id
            approve.setAptype("ns"+applytype);//设置审批单对应的申请业务类别，外来公务人员申请为4
            approve.setApvid(CommonUtils.getUUIDWith_());//设置主键uuid
            /*设置申请用户id，从session读取登录的申请用户信息，将其id设置于审批单 */
            ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
            approve.setApluserid(applyUserinfo.getUserid());
//            approve.setApluserid("0101010101010101");
            Map<String,Object> conditionMap = Maps.newHashMap();
            conditionMap.put("applyUsername",applyUserinfo.getUsername());
            conditionMap.put("applyUsercard",applyUserinfo.getSfzh());
           // conditionMap.put("applyUserSsq",applyUserinfo.getSsq());
            //conditionMap.put("applyUserJd",applyUserinfo.getSsj());
             conditionMap.put("applyUserSsq",pojo.getApSsq());
            conditionMap.put("applyUserJd",pojo.getApJdbsc());
            conditionMap.put("aplb","ns");
            conditionMap.put("applyType",applytype);
            this.flowService.addProcessInstance("ns"+applytype,approve,conditionMap);//添加流程实例，并且将流程实例设置于审批单实体
            this.approveMapper.insert(approve);//添加审批单
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 提交修改后的年审信息
     * @param pojo
     * @return
     */
    public String updateNsInfo(ApplyNs pojo){
        int updateColumn = 0;
        String applytype = pojo.getApSqlb();//申请类别

        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();//获取所有家庭成员信息
        applyFamilyMemberMapper.deleteFamilyMember(pojo.getAlsid());

        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(pojo.getAlsid());
            // this.applyFamilyMemberMapper.insert(tempMem);
        }
        members.get(0).setAfmYsqrgx("本人");
//        members.get(1).setAfmYsqrgx("配偶");

        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");//批量插入家庭成员信息
        //住房信息
        List<ApplyFamilyHouse> applyHouse = pojo.getApplyFamilyHouses();
        //删除住房信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("applyid",pojo.getAlsid());
        applyFamilyHouseMapper.deleteByMap(map);

        for(ApplyFamilyHouse ApplyFamilyHouse : applyHouse){
            ApplyFamilyHouse.setAfhSqid(pojo.getAlsid());
            ApplyFamilyHouse.setAfhId(CommonUtils.getUUIDWith_());
        }

        //批量插入住房信息
        batchUtil.save(applyHouse,"apply.ApplyFamilyHouseMapper");
        /*如果申请类别为经济适房或者补贴，则更新更换住房信息*/
        if(applytype.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))
                ||applytype.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
            ApplyFamilyHouseChange applyFamilyHouseChange = pojo.getApplyFamilyHouseChange();
            applyFamilyHouseChangeMapper.update(applyFamilyHouseChange);
        }
        /*如果是新就业或者外来务工，更新工作单位*/
//        if(applytype.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            ApplyUnit applyUnit=pojo.getApplyUnit();
            applyUnitMapper.update(applyUnit);
        updateColumn=applyNsMapper.update(pojo);
//        }
//        //获取附件信息
//        List<Volelearc> volelearcs=pojo.getVolList();
//        //获取附件的id集合
//        List<String> ids=new ArrayList<String>();
//        for (Volelearc v:volelearcs) {
//            ids.add(v.getElearcvolId());
//        }
//        //根据附件id集合删除附件详细
//        volelearcDtlMapper.deleteVoleearcDtl(ids);
//        //根据申请id删除附件信息
//        volelearcMapper.delete(pojo.getApSqid());

        if(updateColumn>0){
            Approve approve=approveMapper.findByApplyId(pojo.getAlsid());
            /*更新完毕后重新激活挂起的流程实例*/
            String processInstanceId = approve.getProcessinstanceid();
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            /*如果流程是挂起状态，则重新激活*/
            if(pi.isSuspended()){
                this.runtimeService.activateProcessInstanceById(processInstanceId);
            }
            approve.setState("");
            approveMapper.update(approve);
            return "success";
        }else{
            return "false";
        }


    }

    /**
     * 查询待年审的申请表
     * @return
     */
    public List<Map> selectNsUserApp(Map map){
        return this.applyNsMapper.selectNsUserApp(map);
    }

    /**
     * 查询待年审的申请表,查询总数
     * @return
     */
    public int selectNsUserAppCount(Map map){
        return this.applyNsMapper.selectNsUserAppCount(map);
    }

}