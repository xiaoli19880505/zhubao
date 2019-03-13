package com.sys.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
public class FactMapping {
    private String factmappingId;

    private String buildinginfoId;//楼盘id

    private String condoplotId;

    private String  inputflag;//类型是否为空

    private String rationflag;//所属区

    private String orderflag;//是否已分配

    private String fylxname;//房源类型名称

    private String fHocode;

    private String fMapcode;

    private String fBunum;

    private String fHicode;

    private String fFlcode;

    private String fItsite;

    private String fHostru;

    private String fHouse;

    private Short fBulay;

    private String fCurlayname;

    private String fBuname;//楼号名称

    private String fCecodeL;

    private String fCecode;//单元

    private String fRonum;//房间号

    private BigDecimal fConacre2;//建筑面积

    private BigDecimal fInacre2;//套内面积

    private BigDecimal fApacre2;

    private String fCondonum;

    private BigDecimal fLayhig;

    private String hLaytype;

    private String housemodelId;

    private String fHostate;

    private String fPrebunum;

    private String fCommunity;

    private String fItsitedetail;

    private BigDecimal fCurlay;

    private String fRoomnum;

    private String bdchocode;

    private BigDecimal fConacre;

    private BigDecimal fInacre;

    private BigDecimal fApacre;

    private String xhocode;

    private String remark;

    private String sh_applyid;

    private String apluserid;

    private String shFylx;//房源类型
    private String shSsq;//所属区
    private String afmXm;//申请人姓名
    private String itName;//项目名称
    private String num;
    private String conacre;//String类型的建筑面积，可以顿号拼接

    private String icDist;//区域
    private String fRoomAddress;//楼号-单元-室

    public String getFylxname() {
        return fylxname;
    }

    public void setFylxname(String fylxname) {
        this.fylxname = fylxname;
    }

    private ApplyUserinfo applyUserinfo;

    public String getInputflag() {
        return inputflag;
    }

    public void setInputflag(String inputflag) {
        this.inputflag = inputflag;
    }

    public String getRationflag() {
        return rationflag;
    }

    public void setRationflag(String rationflag) {
        this.rationflag = rationflag;
    }

    public String getFactmappingId() {
        return factmappingId;
    }

    public void setFactmappingId(String factmappingId) {
        this.factmappingId = factmappingId == null ? null : factmappingId.trim();
    }

    public String getBuildinginfoId() {
        return buildinginfoId;
    }

    public void setBuildinginfoId(String buildinginfoId) {
        this.buildinginfoId = buildinginfoId == null ? null : buildinginfoId.trim();
    }

    public String getCondoplotId() {
        return condoplotId;
    }

    public void setCondoplotId(String condoplotId) {
        this.condoplotId = condoplotId == null ? null : condoplotId.trim();
    }

    public String getfHocode() {
        return fHocode;
    }

    public void setfHocode(String fHocode) {
        this.fHocode = fHocode == null ? null : fHocode.trim();
    }

    public String getfMapcode() {
        return fMapcode;
    }

    public void setfMapcode(String fMapcode) {
        this.fMapcode = fMapcode == null ? null : fMapcode.trim();
    }

    public String getfBunum() {
        return fBunum;
    }

    public void setfBunum(String fBunum) {
        this.fBunum = fBunum == null ? null : fBunum.trim();
    }

    public String getfHicode() {
        return fHicode;
    }

    public void setfHicode(String fHicode) {
        this.fHicode = fHicode == null ? null : fHicode.trim();
    }

    public String getfFlcode() {
        return fFlcode;
    }

    public void setfFlcode(String fFlcode) {
        this.fFlcode = fFlcode == null ? null : fFlcode.trim();
    }

    public String getfItsite() {
        return fItsite;
    }

    public void setfItsite(String fItsite) {
        this.fItsite = fItsite == null ? null : fItsite.trim();
    }

    public String getfHostru() {
        return fHostru;
    }

    public void setfHostru(String fHostru) {
        this.fHostru = fHostru == null ? null : fHostru.trim();
    }

    public String getfHouse() {
        return fHouse;
    }

    public void setfHouse(String fHouse) {
        this.fHouse = fHouse == null ? null : fHouse.trim();
    }

    public Short getfBulay() {
        return fBulay;
    }

    public void setfBulay(Short fBulay) {
        this.fBulay = fBulay;
    }

    public String getfCurlayname() {
        return fCurlayname;
    }

    public void setfCurlayname(String fCurlayname) {
        this.fCurlayname = fCurlayname == null ? null : fCurlayname.trim();
    }

    public String getfBuname() {
        return fBuname;
    }

    public void setfBuname(String fBuname) {
        this.fBuname = fBuname == null ? null : fBuname.trim();
    }

    public String getfCecodeL() {
        return fCecodeL;
    }

    public void setfCecodeL(String fCecodeL) {
        this.fCecodeL = fCecodeL == null ? null : fCecodeL.trim();
    }

    public String getfCecode() {
        return fCecode;
    }

    public void setfCecode(String fCecode) {
        this.fCecode = fCecode == null ? null : fCecode.trim();
    }

    public String getfRonum() {
        return fRonum;
    }

    public void setfRonum(String fRonum) {
        this.fRonum = fRonum == null ? null : fRonum.trim();
    }

    public BigDecimal getfConacre2() {
        return fConacre2;
    }

    public void setfConacre2(BigDecimal fConacre2) {
        this.fConacre2 = fConacre2;
    }

    public BigDecimal getfInacre2() {
        return fInacre2;
    }

    public void setfInacre2(BigDecimal fInacre2) {
        this.fInacre2 = fInacre2;
    }

    public BigDecimal getfApacre2() {
        return fApacre2;
    }

    public void setfApacre2(BigDecimal fApacre2) {
        this.fApacre2 = fApacre2;
    }

    public String getfCondonum() {
        return fCondonum;
    }

    public void setfCondonum(String fCondonum) {
        this.fCondonum = fCondonum == null ? null : fCondonum.trim();
    }

    public BigDecimal getfLayhig() {
        return fLayhig;
    }

    public void setfLayhig(BigDecimal fLayhig) {
        this.fLayhig = fLayhig;
    }

    public String gethLaytype() {
        return hLaytype;
    }

    public void sethLaytype(String hLaytype) {
        this.hLaytype = hLaytype == null ? null : hLaytype.trim();
    }

    public String getHousemodelId() {
        return housemodelId;
    }

    public void setHousemodelId(String housemodelId) {
        this.housemodelId = housemodelId == null ? null : housemodelId.trim();
    }

    public String getfHostate() {
        return fHostate;
    }

    public void setfHostate(String fHostate) {
        this.fHostate = fHostate == null ? null : fHostate.trim();
    }

    public String getfPrebunum() {
        return fPrebunum;
    }

    public void setfPrebunum(String fPrebunum) {
        this.fPrebunum = fPrebunum == null ? null : fPrebunum.trim();
    }

    public String getfCommunity() {
        return fCommunity;
    }

    public void setfCommunity(String fCommunity) {
        this.fCommunity = fCommunity == null ? null : fCommunity.trim();
    }

    public String getfItsitedetail() {
        return fItsitedetail;
    }

    public void setfItsitedetail(String fItsitedetail) {
        this.fItsitedetail = fItsitedetail == null ? null : fItsitedetail.trim();
    }

    public BigDecimal getfCurlay() {
        return fCurlay;
    }

    public void setfCurlay(BigDecimal fCurlay) {
        this.fCurlay = fCurlay;
    }

    public String getfRoomnum() {
        return fRoomnum;
    }

    public void setfRoomnum(String fRoomnum) {
        this.fRoomnum = fRoomnum == null ? null : fRoomnum.trim();
    }

    public String getBdchocode() {
        return bdchocode;
    }

    public void setBdchocode(String bdchocode) {
        this.bdchocode = bdchocode == null ? null : bdchocode.trim();
    }

    public BigDecimal getfConacre() {
        return fConacre;
    }

    public void setfConacre(BigDecimal fConacre) {
        this.fConacre = fConacre;
    }

    public BigDecimal getfInacre() {
        return fInacre;
    }

    public void setfInacre(BigDecimal fInacre) {
        this.fInacre = fInacre;
    }

    public BigDecimal getfApacre() {
        return fApacre;
    }

    public void setfApacre(BigDecimal fApacre) {
        this.fApacre = fApacre;
    }

    public String getXhocode() {
        return xhocode;
    }

    public void setXhocode(String xhocode) {
        this.xhocode = xhocode == null ? null : xhocode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public ApplyUserinfo getApplyUserinfo() {
        return applyUserinfo;
    }

    public void setApplyUserinfo(ApplyUserinfo applyUserinfo) {
        this.applyUserinfo = applyUserinfo;
    }

    public String getOrderflag() {
        return orderflag;
    }

    public void setOrderflag(String orderflag) {
        this.orderflag = orderflag;
    }

    public String getSh_applyid() {
        return sh_applyid;
    }

    public void setSh_applyid(String sh_applyid) {
        this.sh_applyid = sh_applyid;
    }

    public String getApluserid() {
        return apluserid;
    }

    public void setApluserid(String apluserid) {
        this.apluserid = apluserid;
    }

    public String getShFylx() {
        return shFylx;
    }

    public void setShFylx(String shFylx) {
        this.shFylx = shFylx;
    }

    public String getShSsq() {
        return shSsq;
    }

    public void setShSsq(String shSsq) {
        this.shSsq = shSsq;
    }

    public String getAfmXm() {
        return afmXm;
    }

    public void setAfmXm(String afmXm) {
        this.afmXm = afmXm;
    }

    public String getItName() {
        return itName;
    }

    public void setItName(String itName) {
        this.itName = itName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getConacre() {
        return conacre;
    }

    public void setConacre(String conacre) {
        this.conacre = conacre;
    }

    public String getIcDist() {
        return icDist;
    }

    public void setIcDist(String icDist) {
        this.icDist = icDist;
    }

    public String getfRoomAddress() {
        return fRoomAddress;
    }

    public void setfRoomAddress(String fRoomAddress) {
        this.fRoomAddress = fRoomAddress;
    }
}