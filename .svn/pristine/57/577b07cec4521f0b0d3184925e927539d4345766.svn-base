package com.sys.pojo.apply;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.Volelearc;
import com.sys.pojo.contract.ContractDetail;
import com.sys.pojo.extend.DataGridResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 外来用户申请单实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyForForeign extends DataGridResult {
    private String affId;//申请id

    private String affSqbh;//申请编号

    private String affSsq;//所属区

    private String affDwmc;//单位名称

    private String affDwdz;//单位地址

    private String affWdhjdz;//外地户籍地址

    private String affSqrid;//申请人ID

    private String affLxjzsj;//来徐居住时间

    private String affLdhtkssj;//劳动合同开始时间

    private String affLdhtjssj;//劳动合同结束时间

    private String affSbjnsj;//社保缴纳时间

    private String affGjjjnsj;//公积金缴纳时间

    private BigDecimal affGrnsr;//个人年收入

    private BigDecimal affJtnsr;//家庭年收入

    private Short affJtrks;//家庭人口数

    private BigDecimal affRjysr;//人均月收入

    private BigDecimal affRjjzmj;//人均居住面积

    private String affXzfxz;//现住房性质

    private String affXzfqk;//现住房情况

    private Short affZt;//不用的老数据，状态（0已录入1待保障2异议3取消资格4已保障5保障结束）

    private Short affLc;//不用的老数据，流程（1申请录入，2档案扫描，3摇号，4选房，5签约）

    private String affWfjt;//不用的老数据，无房家庭

    private String affLrsj;//不用的老数据，录入时间

    private String affLrr;//不用的老数据，录入人

    private String affSmsj;//不用的老数据，扫描时间

    private String affSmr;//不用的老数据，扫描人

    private String affBzsj;//不用的老数据，保证时间

    private Short affCxda;//诚信档案(0正常，1信息重复,2信息不实)

    private Short affFlag;//不用的老数据，0为删除，1为正常

    private String affDalc;//不用的老数据，档案流程(10:扫描保存,20:扫描提审)

    private List<ApplyFamilyMember> applyFamilyMembers = new ArrayList<ApplyFamilyMember>();//申请表对应的家庭成员，供上传时使用

    private ApplyFamilyHouse applyFamilyHouse = new ApplyFamilyHouse();//申请表对应的家庭住房信息，供上传时使用

    private ApplyUnit applyUnit = new ApplyUnit(); //申请表对应的申请人的单位信息表

    private String ssqStr;//所属区中文

    private String ssjStr;//所属街道中文

    private String cZtName;//备案状态中文

    private String affBaseimg;//图片文件

    private ApplyUserinfo applyUserinfo;//申请人对象

    private String cHtbh;//合同编号

    private Approve approve;//申请表

    private ContractDetail contractDetail;//合同表

    private List<Volelearc> volList = new ArrayList<Volelearc>(); //对应附件list

    public List<Volelearc> getVolList() {
        return volList;
    }

    public void setVolList(List<Volelearc> volList) {
        this.volList = volList;
    }

    public List<ApplyFamilyMember> getApplyFamilyMembers() {
        return applyFamilyMembers;
    }

    public void setApplyFamilyMembers(List<ApplyFamilyMember> applyFamilyMembers) {
        this.applyFamilyMembers = applyFamilyMembers;
    }

    public ApplyFamilyHouse getApplyFamilyHouse() {
        return applyFamilyHouse;
    }

    public void setApplyFamilyHouse(ApplyFamilyHouse applyFamilyHouse) {
        this.applyFamilyHouse = applyFamilyHouse;
    }


    public String getAffId() {
        return affId;
    }

    public void setAffId(String affId) {
        this.affId = affId == null ? null : affId.trim();
    }

    public String getAffSqbh() {
        return affSqbh;
    }

    public void setAffSqbh(String affSqbh) {
        this.affSqbh = affSqbh == null ? null : affSqbh.trim();
    }

    public String getAffSsq() {
        return affSsq;
    }

    public void setAffSsq(String affSsq) {
        this.affSsq = affSsq == null ? null : affSsq.trim();
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

    public String getAffSqrid() {
        return affSqrid;
    }

    public void setAffSqrid(String affSqrid) {
        this.affSqrid = affSqrid == null ? null : affSqrid.trim();
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

    public BigDecimal getAffGrnsr() {
        return affGrnsr;
    }

    public void setAffGrnsr(BigDecimal affGrnsr) {
        this.affGrnsr = affGrnsr;
    }

    public BigDecimal getAffJtnsr() {
        return affJtnsr;
    }

    public void setAffJtnsr(BigDecimal affJtnsr) {
        this.affJtnsr = affJtnsr;
    }

    public BigDecimal getAffRjysr() {
        return affRjysr;
    }

    public void setAffRjysr(BigDecimal affRjysr) {
        this.affRjysr = affRjysr;
    }

    public BigDecimal getAffRjjzmj() {
        return affRjjzmj;
    }

    public void setAffRjjzmj(BigDecimal affRjjzmj) {
        this.affRjjzmj = affRjjzmj;
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


    public Short getAffJtrks() {
        return affJtrks;
    }

    public void setAffJtrks(Short affJtrks) {
        this.affJtrks = affJtrks;
    }

    public String getAffXzfxz() {
        return affXzfxz;
    }

    public void setAffXzfxz(String affXzfxz) {
        this.affXzfxz = affXzfxz == null ? null : affXzfxz.trim();
    }

    public String getAffXzfqk() {
        return affXzfqk;
    }

    public void setAffXzfqk(String affXzfqk) {
        this.affXzfqk = affXzfqk == null ? null : affXzfqk.trim();
    }

    public Short getAffZt() {
        return affZt;
    }

    public void setAffZt(Short affZt) {
        this.affZt = affZt;
    }

    public Short getAffLc() {
        return affLc;
    }

    public void setAffLc(Short affLc) {
        this.affLc = affLc;
    }

    public String getAffWfjt() {
        return affWfjt;
    }

    public void setAffWfjt(String affWfjt) {
        this.affWfjt = affWfjt == null ? null : affWfjt.trim();
    }

    public String getAffLrsj() {
        return affLrsj;
    }

    public void setAffLrsj(String affLrsj) {
        this.affLrsj = affLrsj == null ? null : affLrsj.trim();
    }

    public String getAffLrr() {
        return affLrr;
    }

    public void setAffLrr(String affLrr) {
        this.affLrr = affLrr == null ? null : affLrr.trim();
    }

    public String getAffSmsj() {
        return affSmsj;
    }

    public void setAffSmsj(String affSmsj) {
        this.affSmsj = affSmsj == null ? null : affSmsj.trim();
    }

    public String getAffSmr() {
        return affSmr;
    }

    public void setAffSmr(String affSmr) {
        this.affSmr = affSmr == null ? null : affSmr.trim();
    }

    public String getAffBzsj() {
        return affBzsj;
    }

    public void setAffBzsj(String affBzsj) {
        this.affBzsj = affBzsj == null ? null : affBzsj.trim();
    }

    public Short getAffCxda() {
        return affCxda;
    }

    public void setAffCxda(Short affCxda) {
        this.affCxda = affCxda;
    }

    public Short getAffFlag() {
        return affFlag;
    }

    public void setAffFlag(Short affFlag) {
        this.affFlag = affFlag;
    }

    public String getAffDalc() {
        return affDalc;
    }

    public void setAffDalc(String affDalc) {
        this.affDalc = affDalc == null ? null : affDalc.trim();
    }

    public ApplyUnit getApplyUnit() {
        return applyUnit;
    }

    public void setApplyUnit(ApplyUnit applyUnit) {
        this.applyUnit = applyUnit;
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

    public String getAffBaseimg() {
        return affBaseimg;
    }

    public void setAffBaseimg(String affBaseimg) {
        this.affBaseimg = affBaseimg;
    }

    public String getcHtbh() {
        return cHtbh;
    }

    public void setcHtbh(String cHtbh) {
        this.cHtbh = cHtbh;
    }
}