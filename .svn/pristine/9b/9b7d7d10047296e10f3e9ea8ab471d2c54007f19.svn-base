package com.sys.pojo.apply;

import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.Volelearc;
import com.sys.pojo.contract.ContractDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请单（经适房、廉租房(住房补贴申请)）
 */
public class Apply {
    private String apId;//申请ID

    private Short apPh;//批号

    private String apSqbh;//申请编号

    private String apSqlb;//申请类别

    private String apSsq;//所属区

    private String apJdbsc;//街道办事处

    private String apSqjwh;//社区居委会

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

    private Short apZt;//不用的字段，状态（0已录入1待保障2异议3取消资格4已保障5保障结束）

    private String apBzfs;//不用的字段，保障方式(0选房，1货币补贴)

    private String apBtje;//不用的字段，补贴金额

    private Short apLc;//不用的字段，流程（1申请录入，2房产审查，3摇号，4选房，5签约）

    private String apWfjt;//不用的字段，无房家庭 1是 0否

    private String apZgh;//不用的字段，资格号

    private String apSxh;//不用的字段，顺序号

    private String apLrsj;//不用的字段，录入时间

    private String apLrr;//不用的字段，录入人

    private String apSmsj;//不用的字段，扫描时间

    private String apSmr;//不用的字段，扫描人

    private String apBzsj;//不用的字段，保障时间

    private String apFwid;//房屋ID

    private Short apCxda;//不用的字段，诚信档案(0正常，1信息重复,2信息不实)

    private Short apFlag;//不用的字段，0为删除，1为正常

    private String apDbzbh;//不用的字段，低保证编号

    private String apLqrs;//不用的字段，领取人数

    private String apFzsj;//不用的字段，发证时间

    private String apDalc;//不用的字段，档案流程

    private String apWfjtshr;//不用的字段，无房家庭审核人

    private String apWfjtshsj;//不用的字段，无房家庭审核时间

    private String dwmc;//单位名称

    private List<ApplyFamilyMember> applyFamilyMembers = new ArrayList<ApplyFamilyMember>();//申请表对应的家庭成员，供上传时使用

    private List<ApplyFamilyHouse> applyFamilyHouses = new ArrayList<ApplyFamilyHouse>();//申请表对应的家庭住房信息，供上传时使用

    private ApplyFamilyHouseChange applyFamilyHouseChange = new ApplyFamilyHouseChange();//更换住房信息

    private List<Volelearc> volList; //对应附件list

    private String ssqStr;//所属区中文

    private String ssjStr;//所属街道中文

    private String cZtName;//备案状态中文

    private ApplyUserinfo applyUserinfo;//申请人对象

    private Approve approve;//申请表

    private ContractDetail contractDetail;//合同表

    private ApplyUnit applyUnit;//单位

    private String apBaseimg;//图片文件

    private String cHtbh;//合同编号

    private List<Apply> applys;

    public ApplyUnit getApplyUnit() {
        return applyUnit;
    }

    public void setApplyUnit(ApplyUnit applyUnit) {
        this.applyUnit = applyUnit;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId == null ? null : apId.trim();
    }

    public Short getApPh() {
        return apPh;
    }

    public void setApPh(Short apPh) {
        this.apPh = apPh;
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

    public Short getApZt() {
        return apZt;
    }

    public void setApZt(Short apZt) {
        this.apZt = apZt;
    }

    public String getApBzfs() {
        return apBzfs;
    }

    public void setApBzfs(String apBzfs) {
        this.apBzfs = apBzfs == null ? null : apBzfs.trim();
    }

    public String getApBtje() {
        return apBtje;
    }

    public void setApBtje(String apBtje) {
        this.apBtje = apBtje == null ? null : apBtje.trim();
    }

    public Short getApLc() {
        return apLc;
    }

    public void setApLc(Short apLc) {
        this.apLc = apLc;
    }

    public String getApWfjt() {
        return apWfjt;
    }

    public void setApWfjt(String apWfjt) {
        this.apWfjt = apWfjt == null ? null : apWfjt.trim();
    }

    public String getApZgh() {
        return apZgh;
    }

    public void setApZgh(String apZgh) {
        this.apZgh = apZgh == null ? null : apZgh.trim();
    }

    public String getApSxh() {
        return apSxh;
    }

    public void setApSxh(String apSxh) {
        this.apSxh = apSxh == null ? null : apSxh.trim();
    }

    public String getApLrsj() {
        return apLrsj;
    }

    public void setApLrsj(String apLrsj) {
        this.apLrsj = apLrsj == null ? null : apLrsj.trim();
    }

    public String getApLrr() {
        return apLrr;
    }

    public void setApLrr(String apLrr) {
        this.apLrr = apLrr == null ? null : apLrr.trim();
    }

    public String getApSmsj() {
        return apSmsj;
    }

    public void setApSmsj(String apSmsj) {
        this.apSmsj = apSmsj == null ? null : apSmsj.trim();
    }

    public String getApSmr() {
        return apSmr;
    }

    public void setApSmr(String apSmr) {
        this.apSmr = apSmr == null ? null : apSmr.trim();
    }

    public String getApBzsj() {
        return apBzsj;
    }

    public void setApBzsj(String apBzsj) {
        this.apBzsj = apBzsj == null ? null : apBzsj.trim();
    }

    public String getApFwid() {
        return apFwid;
    }

    public void setApFwid(String apFwid) {
        this.apFwid = apFwid == null ? null : apFwid.trim();
    }

    public Short getApCxda() {
        return apCxda;
    }

    public void setApCxda(Short apCxda) {
        this.apCxda = apCxda;
    }

    public Short getApFlag() {
        return apFlag;
    }

    public void setApFlag(Short apFlag) {
        this.apFlag = apFlag;
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

    public String getApFzsj() {
        return apFzsj;
    }

    public void setApFzsj(String apFzsj) {
        this.apFzsj = apFzsj == null ? null : apFzsj.trim();
    }

    public String getApDalc() {
        return apDalc;
    }

    public void setApDalc(String apDalc) {
        this.apDalc = apDalc == null ? null : apDalc.trim();
    }

    public String getApWfjtshr() {
        return apWfjtshr;
    }

    public void setApWfjtshr(String apWfjtshr) {
        this.apWfjtshr = apWfjtshr == null ? null : apWfjtshr.trim();
    }

    public String getApWfjtshsj() {
        return apWfjtshsj;
    }

    public void setApWfjtshsj(String apWfjtshsj) {
        this.apWfjtshsj = apWfjtshsj == null ? null : apWfjtshsj.trim();
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

    public ApplyUserinfo getApplyUserinfo() {
        return applyUserinfo;
    }

    public void setApplyUserinfo(ApplyUserinfo applyUserinfo) {
        this.applyUserinfo = applyUserinfo;
    }

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

    public String getcZtName() {
        return cZtName;
    }

    public void setcZtName(String cZtName) {
        this.cZtName = cZtName;
    }

    public Approve getApprove() {
        return approve;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
    }

    public ContractDetail getContractDetail() {
        return contractDetail;
    }

    public void setContractDetail(ContractDetail contractDetail) {
        this.contractDetail = contractDetail;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getApBaseimg() {
        return apBaseimg;
    }

    public void setApBaseimg(String apBaseimg) {
        this.apBaseimg = apBaseimg;
    }

    public String getcHtbh() {
        return cHtbh;
    }

    public void setcHtbh(String cHtbh) {
        this.cHtbh = cHtbh;
    }
}