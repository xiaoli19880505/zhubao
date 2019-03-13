package com.sys.pojo.contract;

import java.util.Date;
/**
 * @author wangli
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/15 0015
 * @desc 合同实体表
 */
public class ContractDetail {
    private String cId; //合同ID

    private String cFwid; //房屋ID

    private String cHtbh;//合同编号

    private Date cHtbarq;//合同备案日期

    private String cBaczy;//备案操作人

    private Short cLc;//合同备案流程1签约 2备案

    private Short cZt;//合同备案状态1正常 2续约 3作废

    private String cSqid;//申请ID

    private String cHtlx;//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同

    private Short cZfzt;//作废状态:0无状态 1中途作废 2到期作废

    private Date createDate;//创建时间

    private String createPerson;//创建人

    private Date updateDate;//修改时间

    private String updatePerson;//修改人

    private String cUrl;//合同文件路径

    private Short subsidyNum;//补贴人数

    private Date cBeginTime;//合同开始时间

    private Date cEndTime;//合同到期时间

    private String cType;//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同

    private String cDataInput;//合同填充数据

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    public String getcFwid() {
        return cFwid;
    }

    public void setcFwid(String cFwid) {
        this.cFwid = cFwid == null ? null : cFwid.trim();
    }

    public String getcHtbh() {
        return cHtbh;
    }

    public void setcHtbh(String cHtbh) {
        this.cHtbh = cHtbh == null ? null : cHtbh.trim();
    }

    public Date getcHtbarq() {
        return cHtbarq;
    }

    public void setcHtbarq(Date cHtbarq) {
        this.cHtbarq = cHtbarq;
    }

    public String getcBaczy() {
        return cBaczy;
    }

    public void setcBaczy(String cBaczy) {
        this.cBaczy = cBaczy == null ? null : cBaczy.trim();
    }

    public Short getcLc() {
        return cLc;
    }

    public void setcLc(Short cLc) {
        this.cLc = cLc;
    }

    public Short getcZt() {
        return cZt;
    }

    public void setcZt(Short cZt) {
        this.cZt = cZt;
    }

    public String getcSqid() {
        return cSqid;
    }

    public void setcSqid(String cSqid) {
        this.cSqid = cSqid == null ? null : cSqid.trim();
    }

    public String getcHtlx() {
        return cHtlx;
    }

    public void setcHtlx(String cHtlx) {
        this.cHtlx = cHtlx == null ? null : cHtlx.trim();
    }

    public Short getcZfzt() {
        return cZfzt;
    }

    public void setcZfzt(Short cZfzt) {
        this.cZfzt = cZfzt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public String getcUrl() {
        return cUrl;
    }

    public void setcUrl(String cUrl) {
        this.cUrl = cUrl;
    }

    public Short getSubsidyNum() {
        return subsidyNum;
    }

    public void setSubsidyNum(Short subsidyNum) {
        this.subsidyNum = subsidyNum;
    }

    public Date getcBeginTime() {
        return cBeginTime;
    }

    public void setcBeginTime(Date cBeginTime) {
        this.cBeginTime = cBeginTime;
    }

    public Date getcEndTime() {
        return cEndTime;
    }

    public void setcEndTime(Date cEndTime) {
        this.cEndTime = cEndTime;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcDataInput() {
        return cDataInput;
    }

    public void setcDataInput(String cDataInput) {
        this.cDataInput = cDataInput;
    }
}