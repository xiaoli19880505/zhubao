package com.sys.pojo.apply;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.ParmItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 审批单id
 */
public class Approve implements Serializable {


    private String apvid;//审批单id

    private String aplid;//申请单id

    private String aplbh; //申请编号，属于冗余字段

    private String aptype;//申请类别

    private Date apldate;//申请发起日期

    private String apldateStr;//申请发起日期

    private String apluserid;//申请用户id

    private String state;//申请单状态

    private Date passdate;//审批通过的日期

    private String apvusers;//审批的用户组

    private String apdesc;//其他描述

    private String processinstanceid;//流程实例id

    private ParmItem applyType;//申请的业务类别

    private String atype;//申请类别
    private String userid;//用户id
    private String username;//用户姓名
    private String sfzh;//用户身份证号
    private String ssq;//所属区
    private String linktel;//联系电话
    private String ssj;//所属街道
    private String sh_ssq;//房屋所属区
    private String f_community;//小区名称
    private String f_buname;//楼栋号
    private String f_ronum;//房间号
    private String f_cecode;//单元
    private String poxm;//配偶姓名
    private String posfzh;//配偶身份证号
    private String unit;
    private BigDecimal f_conacre;//建筑面积
    private String opertime;
    private String operstate;
    private short numb;//分配的房屋数量
    private String num;//摇号顺序号
    private String apSqjwh;//社区居委会
    private String printable;
    private String processinstancename;
    private String ssqStr;//所属区
    private String ssjStr;//所属街
    private String xm;//姓名
    private String appFId;//本人家庭成员外键
    private String actype;//申请单状态

    private ApplyUserinfo applyUserinfo;

    public String getSsqStr() {
        return ssqStr;
    }

    public void setSsqStr(String ssqStr) {
        this.ssqStr = ssqStr;
    }

    public String getSsjStr() {
        return ssjStr;
    }

    public void setSsjStr(String ssjStr) {
        this.ssjStr = ssjStr;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getAppFId() {
        return appFId;
    }

    public void setAppFId(String appFId) {
        this.appFId = appFId;
    }

    public String getApvid() {
        return apvid;
    }

    public void setApvid(String apvid) {
        this.apvid = apvid == null ? null : apvid.trim();
    }

    public String getAplid() {
        return aplid;
    }

    public void setAplid(String aplid) {
        this.aplid = aplid == null ? null : aplid.trim();
    }

    public String getAptype() {
        return aptype;
    }

    public void setAptype(String aptype) {
        this.aptype = aptype == null ? null : aptype.trim();
    }

    public Date getApldate() {
        return apldate;
    }

    public void setApldate(Date apldate) {
        this.apldate = apldate;
    }

    public String getApluserid() {
        return apluserid;
    }

    public void setApluserid(String apluserid) {
        this.apluserid = apluserid == null ? null : apluserid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getPassdate() {
        return passdate;
    }

    public void setPassdate(Date passdate) {
        this.passdate = passdate;
    }

    public String getApvusers() {
        return apvusers;
    }

    public void setApvusers(String apvusers) {
        this.apvusers = apvusers == null ? null : apvusers.trim();
    }

    public String getApdesc() {
        return apdesc;
    }

    public void setApdesc(String apdesc) {
        this.apdesc = apdesc == null ? null : apdesc.trim();
    }

    public String getProcessinstanceid() {
        return processinstanceid;
    }

    public void setProcessinstanceid(String processinstanceid) {
        this.processinstanceid = processinstanceid == null ? null : processinstanceid.trim();
    }

    public ParmItem getApplyType() {
        return applyType;
    }

    public void setApplyType(ParmItem applyType) {
        this.applyType = applyType;
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public ApplyUserinfo getApplyUserinfo() {
        return applyUserinfo;
    }

    public void setApplyUserinfo(ApplyUserinfo applyUserinfo) {
        this.applyUserinfo = applyUserinfo;
    }

    public String getAplbh() {
        return aplbh;
    }

    public void setAplbh(String aplbh) {
        this.aplbh = aplbh;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getApldateStr() {
        return apldateStr;
    }

    public void setApldateStr(String apldateStr) {
        this.apldateStr = apldateStr;
    }

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    public String getSsj() {
        return ssj;
    }

    public void setSsj(String ssj) {
        this.ssj = ssj;
    }

    public String getSh_ssq() {
        return sh_ssq;
    }

    public void setSh_ssq(String sh_ssq) {
        this.sh_ssq = sh_ssq;
    }

    public String getF_community() {
        return f_community;
    }

    public void setF_community(String f_community) {
        this.f_community = f_community;
    }

    public String getF_buname() {
        return f_buname;
    }

    public void setF_buname(String f_buname) {
        this.f_buname = f_buname;
    }

    public String getF_ronum() {
        return f_ronum;
    }

    public void setF_ronum(String f_ronum) {
        this.f_ronum = f_ronum;
    }

    public String getPoxm() {
        return poxm;
    }

    public void setPoxm(String poxm) {
        this.poxm = poxm;
    }

    public String getPosfzh() {
        return posfzh;
    }

    public void setPosfzh(String posfzh) {
        this.posfzh = posfzh;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getF_conacre() {
        return f_conacre;
    }

    public void setF_conacre(BigDecimal f_conacre) {
        this.f_conacre = f_conacre;
    }

    public String getF_cecode() {
        return f_cecode;
    }

    public void setF_cecode(String f_cecode) {
        this.f_cecode = f_cecode;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public String getOperstate() {
        return operstate;
    }

    public void setOperstate(String operstate) {
        this.operstate = operstate;
    }

    public short getNumb() {
        return numb;
    }

    public void setNumb(short numb) {
        this.numb = numb;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getApSqjwh() {
        return apSqjwh;
    }

    public void setApSqjwh(String apSqjwh) {
        this.apSqjwh = apSqjwh;
    }

    public String getPrintable() {
        return printable;
    }

    public void setPrintable(String printable) {
        this.printable = printable;
    }

    public String getProcessinstancename() {
        return processinstancename;
    }

    public void setProcessinstancename(String processinstancename) {
        this.processinstancename = processinstancename;
    }

    public String getActype() {
        return actype;
    }

    public void setActype(String actype) {
        this.actype = actype;
    }
}