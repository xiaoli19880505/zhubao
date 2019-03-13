package com.sys.pojo.apply;

import com.sys.pojo.Volelearc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:年审实体类
 * @Author:wangli
 * @CreateTime:16:12 2018/10/18
 */
public class ApplyNs {
    private String alsid;//年审表id

    private String apPh;//批号

    private String apSqbh;//年审申请编号

    private String apSqid;//申请单id

    private String apSqlb;//申请类别

    private String apSsq;//所属区

    private String apJdbsc;//街道办事处

    private String apSqjwh;//社区委员会

    private String apSqrid;//申请人ID

    private String apSqrpoid;//申请人配偶ID

    private Short apSqhjnx;//市区户籍年限

    private Short apJtrk;//家庭人口

    private BigDecimal apJtnsr;//家庭年收入

    private BigDecimal apRjysr;//人均月收入

    private BigDecimal apRjjzmj;//人均居住面积

    private String apXzfxz;//现住房性质

    private String apJtxzfqk;//家庭现住房情况

    private String apJtzfbgqk;//家庭住房变更情况

    private Short apTsjt;//特殊家庭

    private String apBtje;//补贴金额

    private String apFwid;//房屋ID

    private Short apFlag;//0为删除，1为正常

    private String apWfjt;//无房家庭 1是 0否

    private String affDwmc;//单位名称

    private String affDwdz;//单位地址

    private String affWdhjdz;//外地户籍地址

    private String affLxjzsj;//来徐居住时间

    private String affLdhtkssj;//劳动合同开始时间

    private String affLdhtjssj;//劳动合同结束时间

    private String affSbjnsj;//社保缴纳时间

    private String affGjjjnsj;//公积金缴纳时间

    private BigDecimal affGrnsr;//个人年收入

    private String afgByyx;//毕业院校

    private String afgBysj;//毕业时间

    private String afgXl;//学历

    private String afgHjdz;//户籍地址

    private String apDbzbh;//低保证编号

    private String apLqrs;//领取人数

    private String apFzsj;//发证时间

    private String apBaseimg;//图片文件

    private List<ApplyFamilyMember> applyFamilyMembers = new ArrayList<ApplyFamilyMember>();//申请表对应的家庭成员，供上传时使用

    private List<ApplyFamilyHouse> applyFamilyHouses = new ArrayList<ApplyFamilyHouse>();//申请表对应的家庭住房信息，供上传时使用

    private ApplyFamilyHouseChange applyFamilyHouseChange = new ApplyFamilyHouseChange();//更换住房信息
    private List<Volelearc> volList=new ArrayList<Volelearc>(); //对应附件list
    private ApplyUnit applyUnit = new ApplyUnit();//工作单位
    public String getAlsid() {
        return alsid;
    }

    public void setAlsid(String alsid) {
        this.alsid = alsid == null ? null : alsid.trim();
    }

    public String getApPh() {
        return apPh;
    }

    public void setApPh(String apPh) {
        this.apPh = apPh == null ? null : apPh.trim();
    }

    public String getApSqbh() {
        return apSqbh;
    }

    public void setApSqbh(String apSqbh) {
        this.apSqbh = apSqbh == null ? null : apSqbh.trim();
    }

    public String getApSqlb() {
        return apSqlb;
    }

    public void setApSqlb(String apSqlb) {
        this.apSqlb = apSqlb == null ? null : apSqlb.trim();
    }

    public String getApSsq() {
        return apSsq;
    }

    public void setApSsq(String apSsq) {
        this.apSsq = apSsq == null ? null : apSsq.trim();
    }

    public String getApJdbsc() {
        return apJdbsc;
    }

    public void setApJdbsc(String apJdbsc) {
        this.apJdbsc = apJdbsc == null ? null : apJdbsc.trim();
    }

    public String getApSqjwh() {
        return apSqjwh;
    }

    public void setApSqjwh(String apSqjwh) {
        this.apSqjwh = apSqjwh == null ? null : apSqjwh.trim();
    }

    public String getApSqrid() {
        return apSqrid;
    }

    public void setApSqrid(String apSqrid) {
        this.apSqrid = apSqrid == null ? null : apSqrid.trim();
    }

    public String getApSqrpoid() {
        return apSqrpoid;
    }

    public void setApSqrpoid(String apSqrpoid) {
        this.apSqrpoid = apSqrpoid == null ? null : apSqrpoid.trim();
    }

    public Short getApSqhjnx() {
        return apSqhjnx;
    }

    public void setApSqhjnx(Short apSqhjnx) {
        this.apSqhjnx = apSqhjnx;
    }

    public Short getApJtrk() {
        return apJtrk;
    }

    public void setApJtrk(Short apJtrk) {
        this.apJtrk = apJtrk;
    }

    public String getApXzfxz() {
        return apXzfxz;
    }

    public void setApXzfxz(String apXzfxz) {
        this.apXzfxz = apXzfxz == null ? null : apXzfxz.trim();
    }

    public String getApJtxzfqk() {
        return apJtxzfqk;
    }

    public void setApJtxzfqk(String apJtxzfqk) {
        this.apJtxzfqk = apJtxzfqk == null ? null : apJtxzfqk.trim();
    }

    public String getApJtzfbgqk() {
        return apJtzfbgqk;
    }

    public void setApJtzfbgqk(String apJtzfbgqk) {
        this.apJtzfbgqk = apJtzfbgqk == null ? null : apJtzfbgqk.trim();
    }

    public Short getApTsjt() {
        return apTsjt;
    }

    public void setApTsjt(Short apTsjt) {
        this.apTsjt = apTsjt;
    }

    public String getApBtje() {
        return apBtje;
    }

    public void setApBtje(String apBtje) {
        this.apBtje = apBtje == null ? null : apBtje.trim();
    }

    public String getApFwid() {
        return apFwid;
    }

    public void setApFwid(String apFwid) {
        this.apFwid = apFwid == null ? null : apFwid.trim();
    }

    public Short getApFlag() {
        return apFlag;
    }

    public void setApFlag(Short apFlag) {
        this.apFlag = apFlag;
    }

    public String getApWfjt() {
        return apWfjt;
    }

    public void setApWfjt(String apWfjt) {
        this.apWfjt = apWfjt == null ? null : apWfjt.trim();
    }

    public String getAffDwmc() {
        return affDwmc;
    }

    public void setAffDwmc(String affDwmc) {
        this.affDwmc = affDwmc == null ? null : affDwmc.trim();
    }

    public String getAffDwdz() {
        return affDwdz;
    }

    public void setAffDwdz(String affDwdz) {
        this.affDwdz = affDwdz == null ? null : affDwdz.trim();
    }

    public String getAffWdhjdz() {
        return affWdhjdz;
    }

    public void setAffWdhjdz(String affWdhjdz) {
        this.affWdhjdz = affWdhjdz == null ? null : affWdhjdz.trim();
    }

    public String getAffLxjzsj() {
        return affLxjzsj;
    }

    public void setAffLxjzsj(String affLxjzsj) {
        this.affLxjzsj = affLxjzsj == null ? null : affLxjzsj.trim();
    }

    public String getAffLdhtkssj() {
        return affLdhtkssj;
    }

    public void setAffLdhtkssj(String affLdhtkssj) {
        this.affLdhtkssj = affLdhtkssj == null ? null : affLdhtkssj.trim();
    }

    public String getAffLdhtjssj() {
        return affLdhtjssj;
    }

    public void setAffLdhtjssj(String affLdhtjssj) {
        this.affLdhtjssj = affLdhtjssj == null ? null : affLdhtjssj.trim();
    }

    public String getAffSbjnsj() {
        return affSbjnsj;
    }

    public void setAffSbjnsj(String affSbjnsj) {
        this.affSbjnsj = affSbjnsj == null ? null : affSbjnsj.trim();
    }

    public String getAffGjjjnsj() {
        return affGjjjnsj;
    }

    public void setAffGjjjnsj(String affGjjjnsj) {
        this.affGjjjnsj = affGjjjnsj == null ? null : affGjjjnsj.trim();
    }

    public BigDecimal getApJtnsr() {
        return apJtnsr;
    }

    public void setApJtnsr(BigDecimal apJtnsr) {
        this.apJtnsr = apJtnsr;
    }

    public BigDecimal getApRjysr() {
        return apRjysr;
    }

    public void setApRjysr(BigDecimal apRjysr) {
        this.apRjysr = apRjysr;
    }

    public BigDecimal getApRjjzmj() {
        return apRjjzmj;
    }

    public void setApRjjzmj(BigDecimal apRjjzmj) {
        this.apRjjzmj = apRjjzmj;
    }

    public BigDecimal getAffGrnsr() {
        return affGrnsr;
    }

    public void setAffGrnsr(BigDecimal affGrnsr) {
        this.affGrnsr = affGrnsr;
    }

    public String getAfgByyx() {
        return afgByyx;
    }

    public void setAfgByyx(String afgByyx) {
        this.afgByyx = afgByyx == null ? null : afgByyx.trim();
    }

    public String getAfgBysj() {
        return afgBysj;
    }

    public void setAfgBysj(String afgBysj) {
        this.afgBysj = afgBysj == null ? null : afgBysj.trim();
    }

    public String getAfgXl() {
        return afgXl;
    }

    public void setAfgXl(String afgXl) {
        this.afgXl = afgXl == null ? null : afgXl.trim();
    }

    public String getAfgHjdz() {
        return afgHjdz;
    }

    public void setAfgHjdz(String afgHjdz) {
        this.afgHjdz = afgHjdz == null ? null : afgHjdz.trim();
    }

    public String getApDbzbh() {
        return apDbzbh;
    }

    public void setApDbzbh(String apDbzbh) {
        this.apDbzbh = apDbzbh == null ? null : apDbzbh.trim();
    }

    public String getApLqrs() {
        return apLqrs;
    }

    public void setApLqrs(String apLqrs) {
        this.apLqrs = apLqrs == null ? null : apLqrs.trim();
    }

    public String getApSqid() {
        return apSqid;
    }

    public void setApSqid(String apSqid) {
        this.apSqid = apSqid;
    }

    public String getApFzsj() {
        return apFzsj;
    }

    public void setApFzsj(String apFzsj) {
        this.apFzsj = apFzsj == null ? null : apFzsj.trim();
    }

    public List<ApplyFamilyMember> getApplyFamilyMembers() {
        return applyFamilyMembers;
    }

    public void setApplyFamilyMembers(List<ApplyFamilyMember> applyFamilyMembers) {
        this.applyFamilyMembers = applyFamilyMembers;
    }

    public List<ApplyFamilyHouse> getApplyFamilyHouses() {
        return applyFamilyHouses;
    }

    public void setApplyFamilyHouses(List<ApplyFamilyHouse> applyFamilyHouses) {
        this.applyFamilyHouses = applyFamilyHouses;
    }

    public ApplyFamilyHouseChange getApplyFamilyHouseChange() {
        return applyFamilyHouseChange;
    }

    public void setApplyFamilyHouseChange(ApplyFamilyHouseChange applyFamilyHouseChange) {
        this.applyFamilyHouseChange = applyFamilyHouseChange;
    }

    public List<Volelearc> getVolList() {
        return volList;
    }

    public void setVolList(List<Volelearc> volList) {
        this.volList = volList;
    }

    public ApplyUnit getApplyUnit() {
        return applyUnit;
    }

    public void setApplyUnit(ApplyUnit applyUnit) {
        this.applyUnit = applyUnit;
    }

    public String getApBaseimg() {
        return apBaseimg;
    }

    public void setApBaseimg(String apBaseimg) {
        this.apBaseimg = apBaseimg;
    }
}