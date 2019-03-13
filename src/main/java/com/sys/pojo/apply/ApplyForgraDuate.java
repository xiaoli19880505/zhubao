package com.sys.pojo.apply;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.Volelearc;
import com.sys.pojo.contract.ContractDetail;
import com.sys.pojo.extend.DataGridResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/19 0012
 * @desc 新就业applyFamilyMembers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyForgraDuate extends DataGridResult {
    private String afgId;//申请ID

    private String afgSqbh;//申请编号

    private String afgSqrid;//申请人ID

    private String afgSsq;//所属区

    private String afgDwmc;//单位名称

    private String afgDwdz;//单位地址

    private String afgByyx;//毕业院校

    private String afgBysj;//毕业时间

    private String afgXl;//学历

    private String afgHjdz;//户籍地址

    private Short afgSqhjnx;//市区户籍年限

    private Short afgJtrks;//家庭人口数

    private BigDecimal afgGrnsr;//个人年收入

    private BigDecimal afgJtnsr;//家庭年收入

    private BigDecimal afgRjysr;//人均月收入

    private String afgLdhtkssj;//劳动合同开始时间

    private String afgLdhtjssj;//劳动合同结束时间

    private String afgSbjnsj;//社保缴纳时间

    private String afgGjjjnsj;//公积金缴纳时间

    private BigDecimal afgRjzfmj;//人均住房面积

    private String afgXzfxz;//现住房性质

    private String afgXzfqk;//现住房情况

    private Short afgZt;//状态（0已录入1待保障2异议3取消资格4已保障5保障结束）

    private Short afgLc;//流程（1申请录入，2档案扫描，3摇号，4选房，5签约）

    private String afgWfjt;//无房家庭

    private String afgLrsj;//录入时间

    private String afgLrr;//录入人

    private String afgSmsj;//扫描时间

    private String afgSmr;//扫描人

    private String afgBzsj;//保障时间

    private Short afgCxda;//诚信档案(0正常，1信息重复,2信息不实)

    private Short afgFlag;//0为删除，1为正常

    private String afgDalc;//档案流程(10:扫描保存20:扫描提审)

    private String afgBaseimg;//图片文件

    private String cZtStr;

    private String cHtbh;//合同编号

    private List<ApplyFamilyMember> applyFamilyMembers;//申请表对应的家庭成员，供上传时使用

    private ApplyFamilyHouseChange applyFamilyHouseChange;//住房变更表

    private List<Volelearc> volList; //对应附件list

    private ApplyUnit applyUnit;//申请表对应的申请人的单位信息表

    private List<ApplyFamilyHouse> applyFamilyHouses;//申请表对应的家庭住房信息，供上传时使用

    private String ssqStr;//所属区中文

    private String ssjStr;//所属街道中文

    private String cZtName;//备案状态中文

    private ApplyUserinfo applyUserinfo;//申请人对象

    private Approve approve;//申请表

    private ContractDetail contractDetail;//合同表

    public String getAfgId() {
        return afgId;
    }

    public void setAfgId(String afgId) {
        this.afgId = afgId == null ? null : afgId.trim();
    }

    public String getAfgSqbh() {
        return afgSqbh;
    }

    public void setAfgSqbh(String afgSqbh) {
        this.afgSqbh = afgSqbh == null ? null : afgSqbh.trim();
    }

    public String getAfgSqrid() {
        return afgSqrid;
    }

    public void setAfgSqrid(String afgSqrid) {
        this.afgSqrid = afgSqrid == null ? null : afgSqrid.trim();
    }

    public String getAfgSsq() {
        return afgSsq;
    }

    public void setAfgSsq(String afgSsq) {
        this.afgSsq = afgSsq == null ? null : afgSsq.trim();
    }

    public String getAfgDwmc() {
        return afgDwmc;
    }

    public void setAfgDwmc(String afgDwmc) {
        this.afgDwmc = afgDwmc == null ? null : afgDwmc.trim();
    }

    public String getAfgDwdz() {
        return afgDwdz;
    }

    public void setAfgDwdz(String afgDwdz) {
        this.afgDwdz = afgDwdz == null ? null : afgDwdz.trim();
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

    public Short getAfgSqhjnx() {
        return afgSqhjnx;
    }

    public void setAfgSqhjnx(Short afgSqhjnx) {
        this.afgSqhjnx = afgSqhjnx;
    }

    public Short getAfgJtrks() {
        return afgJtrks;
    }

    public void setAfgJtrks(Short afgJtrks) {
        this.afgJtrks = afgJtrks;
    }

    public BigDecimal getAfgGrnsr() {
        return afgGrnsr;
    }

    public void setAfgGrnsr(BigDecimal afgGrnsr) {
        this.afgGrnsr = afgGrnsr;
    }

    public BigDecimal getAfgJtnsr() {
        return afgJtnsr;
    }

    public void setAfgJtnsr(BigDecimal afgJtnsr) {
        this.afgJtnsr = afgJtnsr;
    }

    public BigDecimal getAfgRjysr() {
        return afgRjysr;
    }

    public void setAfgRjysr(BigDecimal afgRjysr) {
        this.afgRjysr = afgRjysr;
    }

    public BigDecimal getAfgRjzfmj() {
        return afgRjzfmj;
    }

    public void setAfgRjzfmj(BigDecimal afgRjzfmj) {
        this.afgRjzfmj = afgRjzfmj;
    }

    public String getAfgLdhtkssj() {
        return afgLdhtkssj;
    }

    public void setAfgLdhtkssj(String afgLdhtkssj) {
        this.afgLdhtkssj = afgLdhtkssj == null ? null : afgLdhtkssj.trim();
    }

    public String getAfgLdhtjssj() {
        return afgLdhtjssj;
    }

    public void setAfgLdhtjssj(String afgLdhtjssj) {
        this.afgLdhtjssj = afgLdhtjssj == null ? null : afgLdhtjssj.trim();
    }

    public String getAfgSbjnsj() {
        return afgSbjnsj;
    }

    public void setAfgSbjnsj(String afgSbjnsj) {
        this.afgSbjnsj = afgSbjnsj == null ? null : afgSbjnsj.trim();
    }

    public String getAfgGjjjnsj() {
        return afgGjjjnsj;
    }

    public void setAfgGjjjnsj(String afgGjjjnsj) {
        this.afgGjjjnsj = afgGjjjnsj == null ? null : afgGjjjnsj.trim();
    }

    public String getAfgXzfxz() {
        return afgXzfxz;
    }

    public void setAfgXzfxz(String afgXzfxz) {
        this.afgXzfxz = afgXzfxz == null ? null : afgXzfxz.trim();
    }

    public String getAfgXzfqk() {
        return afgXzfqk;
    }

    public void setAfgXzfqk(String afgXzfqk) {
        this.afgXzfqk = afgXzfqk == null ? null : afgXzfqk.trim();
    }

    public Short getAfgZt() {
        return afgZt;
    }

    public void setAfgZt(Short afgZt) {
        this.afgZt = afgZt;
    }

    public Short getAfgLc() {
        return afgLc;
    }

    public void setAfgLc(Short afgLc) {
        this.afgLc = afgLc;
    }

    public String getAfgWfjt() {
        return afgWfjt;
    }

    public void setAfgWfjt(String afgWfjt) {
        this.afgWfjt = afgWfjt == null ? null : afgWfjt.trim();
    }

    public String getAfgLrsj() {
        return afgLrsj;
    }

    public void setAfgLrsj(String afgLrsj) {
        this.afgLrsj = afgLrsj == null ? null : afgLrsj.trim();
    }

    public String getAfgLrr() {
        return afgLrr;
    }

    public void setAfgLrr(String afgLrr) {
        this.afgLrr = afgLrr == null ? null : afgLrr.trim();
    }

    public String getAfgSmsj() {
        return afgSmsj;
    }

    public void setAfgSmsj(String afgSmsj) {
        this.afgSmsj = afgSmsj == null ? null : afgSmsj.trim();
    }

    public String getAfgSmr() {
        return afgSmr;
    }

    public void setAfgSmr(String afgSmr) {
        this.afgSmr = afgSmr == null ? null : afgSmr.trim();
    }

    public String getAfgBzsj() {
        return afgBzsj;
    }

    public void setAfgBzsj(String afgBzsj) {
        this.afgBzsj = afgBzsj == null ? null : afgBzsj.trim();
    }

    public Short getAfgCxda() {
        return afgCxda;
    }

    public void setAfgCxda(Short afgCxda) {
        this.afgCxda = afgCxda;
    }

    public Short getAfgFlag() {
        return afgFlag;
    }

    public void setAfgFlag(Short afgFlag) {
        this.afgFlag = afgFlag;
    }

    public String getAfgDalc() {
        return afgDalc;
    }

    public void setAfgDalc(String afgDalc) {
        this.afgDalc = afgDalc == null ? null : afgDalc.trim();
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

//    public ApplyFamilyHouse getApplyFamilyHouse() {
//        return applyFamilyHouse;
//    }
//
//    public void setApplyFamilyHouse(ApplyFamilyHouse applyFamilyHouse) {
//        this.applyFamilyHouse = applyFamilyHouse;
//    }

    public void setApplyFamilyHouses(List<ApplyFamilyHouse> applyFamilyHouses) {
        this.applyFamilyHouses = applyFamilyHouses;
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

    public ApplyFamilyHouseChange getApplyFamilyHouseChange() {
        return applyFamilyHouseChange;
    }

    public void setApplyFamilyHouseChange(ApplyFamilyHouseChange applyFamilyHouseChange) {
        this.applyFamilyHouseChange = applyFamilyHouseChange;
    }

    public ApplyUserinfo getApplyUserinfo() {
        return applyUserinfo;
    }

    public void setApplyUserinfo(ApplyUserinfo applyUserinfo) {
        this.applyUserinfo = applyUserinfo;
    }

    public Approve getApprove() {
        return approve;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
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

    public ContractDetail getContractDetail() {
        return contractDetail;
    }

    public void setContractDetail(ContractDetail contractDetail) {
        this.contractDetail = contractDetail;
    }

    public String getcZtStr() {
        return cZtStr;
    }

    public void setcZtStr(String cZtStr) {
        this.cZtStr = cZtStr;
    }

    public String getAfgBaseimg() {
        return afgBaseimg;
    }

    public void setAfgBaseimg(String afgBaseimg) {
        this.afgBaseimg = afgBaseimg;
    }

    public String getcHtbh() {
        return cHtbh;
    }

    public void setcHtbh(String cHtbh) {
        this.cHtbh = cHtbh;
    }
}