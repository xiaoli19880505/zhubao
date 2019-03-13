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
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/19 0012
 * @desc 低保特困住保申请
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyButie extends DataGridResult {
    private String abId;//补贴申请ID

    private String abSqbh;//申请编号

    private String abBzlb;//保障类型

    private String abSsq;//所属区

    private String abJdbsc;//街道办事处

    private String abSqjwh;//社区居委会

    private String abSqrid;//申请人ID

    private String abSqrpoid;//申请人配偶ID

    private Short abSqhjnx;//市区户籍年限

    private Short abJtrk;//家庭人口

    private BigDecimal abJtnsr;//家庭年收入

    private BigDecimal abRjysr;//人均月收入

    private BigDecimal abRjjzmj;//人均居住面积

    private String abXzfxz;//现住房性质

    private Short abTsjt;//特殊家庭

    private BigDecimal abBtje;//补贴金额

    private String abYhkh;//银行卡号

    private String abKsrq;//开始日期

    private String abJsrq;//结束日期

    private Short abZt;//状态（1正常、0取消资格）

    private Short abLc;//流程（0申请录入、）

    private String abDalc;//档案流程

    private Short abFlag;//0为删除，1为正常

    private String abLrr;//录入人

    private String abLrsj;//录入时间

    private String abXgr;//修改人

    private String abXgsj;//修改时间

    private String abSmr;//扫描人

    private String abSmsj;//扫描时间

    private String dwmc;//单位名称

    //最后添加20190108
    private String abDbzbh;//低保证编号

    private String abLqrs;//领取人数

    private String abFzsj;//发证时间

    private String abBaseimg;//图片文件

    private String cHtbh;//合同编号

    private List<ApplyFamilyMember> applyFamilyMembers = new ArrayList<ApplyFamilyMember>();//申请表对应的家庭成员，供上传时使用

    private ApplyFamilyHouse applyFamilyHouse;//申请表对应的家庭住房信息，供上传时使用

    private List<ApplyFamilyHouse> applyFamilyHouses = new ArrayList<ApplyFamilyHouse>();//申请表对应的家庭住房信息，供上传时使用

    private ApplyFamilyHouseChange applyFamilyHouseChange;//申请表对应住房变更

    private List<Volelearc> volList; //对应附件list

    private String ssqStr;//所属区中文

    private String ssjStr;//所属街道中文

    private String cZtName;//备案状态中文

    private ApplyUserinfo applyUserinfo;//申请人对象

    private Approve approve;//申请表

    private ContractDetail contractDetail;//合同表

    private ApplyUnit applyUnit;//单位信息

    private BigDecimal sumArea;//面积

    public BigDecimal getSumArea() {
        return sumArea;
    }

    public void setSumArea(BigDecimal sumArea) {
        this.sumArea = sumArea;
    }

    public ApplyUnit getApplyUnit() {
        return applyUnit;
    }

    public void setApplyUnit(ApplyUnit applyUnit) {
        this.applyUnit = applyUnit;
    }

    public String getAbId() {
        return abId;
    }

    public void setAbId(String abId) {
        this.abId = abId == null ? null : abId.trim();
    }

    public String getAbSqbh() {
        return abSqbh;
    }

    public void setAbSqbh(String abSqbh) {
        this.abSqbh = abSqbh == null ? null : abSqbh.trim();
    }

    public String getAbBzlb() {
        return abBzlb;
    }

    public void setAbBzlb(String abBzlb) {
        this.abBzlb = abBzlb == null ? null : abBzlb.trim();
    }

    public String getAbSsq() {
        return abSsq;
    }

    public void setAbSsq(String abSsq) {
        this.abSsq = abSsq == null ? null : abSsq.trim();
    }

    public String getAbJdbsc() {
        return abJdbsc;
    }

    public void setAbJdbsc(String abJdbsc) {
        this.abJdbsc = abJdbsc == null ? null : abJdbsc.trim();
    }

    public String getAbSqjwh() {
        return abSqjwh;
    }

    public void setAbSqjwh(String abSqjwh) {
        this.abSqjwh = abSqjwh == null ? null : abSqjwh.trim();
    }

    public String getAbSqrid() {
        return abSqrid;
    }

    public void setAbSqrid(String abSqrid) {
        this.abSqrid = abSqrid == null ? null : abSqrid.trim();
    }

    public String getAbSqrpoid() {
        return abSqrpoid;
    }

    public void setAbSqrpoid(String abSqrpoid) {
        this.abSqrpoid = abSqrpoid == null ? null : abSqrpoid.trim();
    }

    public Short getAbSqhjnx() {
        return abSqhjnx;
    }

    public void setAbSqhjnx(Short abSqhjnx) {
        this.abSqhjnx = abSqhjnx;
    }

    public Short getAbJtrk() {
        return abJtrk;
    }

    public void setAbJtrk(Short abJtrk) {
        this.abJtrk = abJtrk;
    }



    public String getAbXzfxz() {
        return abXzfxz;
    }

    public void setAbXzfxz(String abXzfxz) {
        this.abXzfxz = abXzfxz == null ? null : abXzfxz.trim();
    }

    public Short getAbTsjt() {
        return abTsjt;
    }

    public void setAbTsjt(Short abTsjt) {
        this.abTsjt = abTsjt;
    }

    public BigDecimal getAbJtnsr() {
        return abJtnsr;
    }

    public void setAbJtnsr(BigDecimal abJtnsr) {
        this.abJtnsr = abJtnsr;
    }

    public BigDecimal getAbRjysr() {
        return abRjysr;
    }

    public void setAbRjysr(BigDecimal abRjysr) {
        this.abRjysr = abRjysr;
    }

    public BigDecimal getAbRjjzmj() {
        return abRjjzmj;
    }

    public void setAbRjjzmj(BigDecimal abRjjzmj) {
        this.abRjjzmj = abRjjzmj;
    }

    public BigDecimal getAbBtje() {
        return abBtje;
    }

    public void setAbBtje(BigDecimal abBtje) {
        this.abBtje = abBtje;
    }

    public ApplyFamilyHouse getApplyFamilyHouse() {
        return applyFamilyHouse;
    }

    public void setApplyFamilyHouse(ApplyFamilyHouse applyFamilyHouse) {
        this.applyFamilyHouse = applyFamilyHouse;
    }

    public String getAbYhkh() {
        return abYhkh;
    }

    public void setAbYhkh(String abYhkh) {
        this.abYhkh = abYhkh == null ? null : abYhkh.trim();
    }

    public String getAbKsrq() {
        return abKsrq;
    }

    public void setAbKsrq(String abKsrq) {
        this.abKsrq = abKsrq == null ? null : abKsrq.trim();
    }

    public String getAbJsrq() {
        return abJsrq;
    }

    public void setAbJsrq(String abJsrq) {
        this.abJsrq = abJsrq == null ? null : abJsrq.trim();
    }

    public Short getAbZt() {
        return abZt;
    }

    public void setAbZt(Short abZt) {
        this.abZt = abZt;
    }

    public Short getAbLc() {
        return abLc;
    }

    public void setAbLc(Short abLc) {
        this.abLc = abLc;
    }

    public String getAbDalc() {
        return abDalc;
    }

    public void setAbDalc(String abDalc) {
        this.abDalc = abDalc == null ? null : abDalc.trim();
    }

    public Short getAbFlag() {
        return abFlag;
    }

    public void setAbFlag(Short abFlag) {
        this.abFlag = abFlag;
    }

    public String getAbLrr() {
        return abLrr;
    }

    public void setAbLrr(String abLrr) {
        this.abLrr = abLrr == null ? null : abLrr.trim();
    }

    public String getAbLrsj() {
        return abLrsj;
    }

    public void setAbLrsj(String abLrsj) {
        this.abLrsj = abLrsj == null ? null : abLrsj.trim();
    }

    public String getAbXgr() {
        return abXgr;
    }

    public void setAbXgr(String abXgr) {
        this.abXgr = abXgr == null ? null : abXgr.trim();
    }

    public String getAbXgsj() {
        return abXgsj;
    }

    public void setAbXgsj(String abXgsj) {
        this.abXgsj = abXgsj == null ? null : abXgsj.trim();
    }

    public String getAbSmr() {
        return abSmr;
    }

    public void setAbSmr(String abSmr) {
        this.abSmr = abSmr == null ? null : abSmr.trim();
    }

    public String getAbSmsj() {
        return abSmsj;
    }

    public void setAbSmsj(String abSmsj) {
        this.abSmsj = abSmsj == null ? null : abSmsj.trim();
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

    public List<Volelearc> getVolList() {
        return volList;
    }

    public void setVolList(List<Volelearc> volList) {
        this.volList = volList;
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

    public String getcZtName() {
        return cZtName;
    }

    public void setcZtName(String cZtName) {
        this.cZtName = cZtName;
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

    public String getAbDbzbh() {
        return abDbzbh;
    }

    public void setAbDbzbh(String abDbzbh) {
        this.abDbzbh = abDbzbh;
    }

    public String getAbLqrs() {
        return abLqrs;
    }

    public void setAbLqrs(String abLqrs) {
        this.abLqrs = abLqrs;
    }

    public String getAbFzsj() {
        return abFzsj;
    }

    public void setAbFzsj(String abFzsj) {
        this.abFzsj = abFzsj;
    }

    public String getAbBaseimg() {
        return abBaseimg;
    }

    public void setAbBaseimg(String abBaseimg) {
        this.abBaseimg = abBaseimg;
    }

    public String getcHtbh() {
        return cHtbh;
    }

    public void setcHtbh(String cHtbh) {
        this.cHtbh = cHtbh;
    }
}