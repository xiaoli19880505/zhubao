package com.sys.pojo.blagsh;


import com.sys.pojo.apply.ApplyUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangli
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/19 0012
 * @desc 诚信公司条目实体（加入失信）
 */
public class BliveGongS {
    private String blgId;//主键id

    private String blgType;//申请类型

    private String blgSqbh;//申请编号

    private Date blgSqrq;//添加日期

    private String blgUserid;//申请人ID

    private String blgShProcessid;//申请审核流程实例id

    private String blgSqtype;//失信行为类型(1.一般 2.严重)

    private String blgSqtypeName;//失信行为类型中文

    private String blgDesc;//失信行为描述

    private String blgIsgs;//是否公示

    private String blgApid;//申请单ID

    private String blgRpuserid;//上报用户id

    private String blgTypeName;//申请类型

    private String apldate;//申请日期

    private String blgUserName;//申请人姓名

    private String blgSfzh;//申请人身份证

    private String blgSsq;//所在区

    private String blgSsqStr;//所在区中文

    private String blgSsj;//所在街

    private String blgSsjStr;//所在街道中文

    private String blgStatus;//审核状态

    private String blgStatusIsNull;//审核是否为空

    private String blgStatusStr;//审核状态中文

    private String sex;//性别

    private String afmHyzk;//婚姻状态

    private String afmLxdh;//联系电话

    private String blgProcessinstanceid;//公示流程ID

    private String[] lostLetterTypeArr;//失信行为类型

    private List<BliveGongsDetail> bliveGongsDetails = new ArrayList<BliveGongsDetail>();//失信行信息

    private String blgOpinion;//失信意见

    private ApplyUnit applyUnit;//单位

    private String lostLetterNo;//失信编号

    public String getLostLetterNo() {
        return lostLetterNo;
    }

    public void setLostLetterNo(String lostLetterNo) {
        this.lostLetterNo = lostLetterNo;
    }

    public ApplyUnit getApplyUnit() {
        return applyUnit;
    }

    public void setApplyUnit(ApplyUnit applyUnit) {
        this.applyUnit = applyUnit;
    }

    public String[] getLostLetterTypeArr() {
        return lostLetterTypeArr;
    }

    public void setLostLetterTypeArr(String[] lostLetterTypeArr) {
        this.lostLetterTypeArr = lostLetterTypeArr;
    }

    public String getBlgOpinion() {
        return blgOpinion;
    }

    public void setBlgOpinion(String blgOpinion) {
        this.blgOpinion = blgOpinion;
    }

    public List<BliveGongsDetail> getBliveGongsDetails() {
        return bliveGongsDetails;
    }

    public void setBliveGongsDetails(List<BliveGongsDetail> bliveGongsDetails) {
        this.bliveGongsDetails = bliveGongsDetails;
    }

    public String getBlgId() {
        return blgId;
    }

    public void setBlgId(String blgId) {
        this.blgId = blgId == null ? null : blgId.trim();
    }

    public String getBlgType() {
        return blgType;
    }

    public void setBlgType(String blgType) {
        this.blgType = blgType == null ? null : blgType.trim();
    }

    public String getBlgSqbh() {
        return blgSqbh;
    }

    public void setBlgSqbh(String blgSqbh) {
        this.blgSqbh = blgSqbh == null ? null : blgSqbh.trim();
    }

    public Date getBlgSqrq() {
        return blgSqrq;
    }

    public void setBlgSqrq(Date blgSqrq) {
        this.blgSqrq = blgSqrq;
    }

    public String getBlgUserid() {
        return blgUserid;
    }

    public void setBlgUserid(String blgUserid) {
        this.blgUserid = blgUserid == null ? null : blgUserid.trim();
    }

    public String getBlgShProcessid() {
        return blgShProcessid;
    }

    public void setBlgShProcessid(String blgShProcessid) {
        this.blgShProcessid = blgShProcessid == null ? null : blgShProcessid.trim();
    }

    public String getBlgSqtype() {
        return blgSqtype;
    }

    public void setBlgSqtype(String blgSqtype) {
        this.blgSqtype = blgSqtype == null ? null : blgSqtype.trim();
    }

    public String getBlgDesc() {
        return blgDesc;
    }

    public void setBlgDesc(String blgDesc) {
        this.blgDesc = blgDesc == null ? null : blgDesc.trim();
    }

    public String getBlgIsgs() {
        return blgIsgs;
    }

    public void setBlgIsgs(String blgIsgs) {
        this.blgIsgs = blgIsgs == null ? null : blgIsgs.trim();
    }

    public String getBlgApid() {
        return blgApid;
    }

    public void setBlgApid(String blgApid) {
        this.blgApid = blgApid == null ? null : blgApid.trim();
    }

    public String getBlgRpuserid() {
        return blgRpuserid;
    }

    public void setBlgRpuserid(String blgRpuserid) {
        this.blgRpuserid = blgRpuserid == null ? null : blgRpuserid.trim();
    }

    public String getBlgTypeName() {
        return blgTypeName;
    }

    public void setBlgTypeName(String blgTypeName) {
        this.blgTypeName = blgTypeName;
    }

    public String getApldate() {
        return apldate;
    }

    public void setApldate(String apldate) {
        this.apldate = apldate;
    }

    public String getBlgUserName() {
        return blgUserName;
    }

    public void setBlgUserName(String blgUserName) {
        this.blgUserName = blgUserName;
    }

    public String getBlgSfzh() {
        return blgSfzh;
    }

    public void setBlgSfzh(String blgSfzh) {
        this.blgSfzh = blgSfzh;
    }

    public String getBlgSsqStr() {
        return blgSsqStr;
    }

    public void setBlgSsqStr(String blgSsqStr) {
        this.blgSsqStr = blgSsqStr;
    }

    public String getBlgSsjStr() {
        return blgSsjStr;
    }

    public void setBlgSsjStr(String blgSsjStr) {
        this.blgSsjStr = blgSsjStr;
    }

    public String getBlgStatus() {
        return blgStatus;
    }

    public void setBlgStatus(String blgStatus) {
        this.blgStatus = blgStatus;
    }

    public String getBlgSsq() {
        return blgSsq;
    }

    public void setBlgSsq(String blgSsq) {
        this.blgSsq = blgSsq;
    }

    public String getBlgSsj() {
        return blgSsj;
    }

    public void setBlgSsj(String blgSsj) {
        this.blgSsj = blgSsj;
    }

    public String getBlgStatusStr() {
        return blgStatusStr;
    }

    public void setBlgStatusStr(String blgStatusStr) {
        this.blgStatusStr = blgStatusStr;
    }

    public String getSex() {
        return sex;
    }

    public String getBlgSqtypeName() {
        return blgSqtypeName;
    }

    public void setBlgSqtypeName(String blgSqtypeName) {
        this.blgSqtypeName = blgSqtypeName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAfmHyzk() {
        return afmHyzk;
    }

    public void setAfmHyzk(String afmHyzk) {
        this.afmHyzk = afmHyzk;
    }

    public String getAfmLxdh() {
        return afmLxdh;
    }

    public void setAfmLxdh(String afmLxdh) {
        this.afmLxdh = afmLxdh;
    }

    public String getBlgProcessinstanceid() {
        return blgProcessinstanceid;
    }

    public void setBlgProcessinstanceid(String blgProcessinstanceid) {
        this.blgProcessinstanceid = blgProcessinstanceid;
    }

    public String getBlgStatusIsNull() {
        return blgStatusIsNull;
    }

    public void setBlgStatusIsNull(String blgStatusIsNull) {
        this.blgStatusIsNull = blgStatusIsNull;
    }
}