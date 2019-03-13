package com.sys.pojo.report;

import java.math.BigDecimal;

public class ReportPojo {

    private String abId;//补贴申请ID

    private String abYhkh;//银行卡号

    private String afmXm;//姓名

    private String afmXb;//性别

    private Integer householdsNum;//户数

    private String sfzh;//身份证

    private String afhZl;//房屋地址

    private String afmLxdh;//联系电话

    private String afhZfxz;//住房性质

    private String afhZfxzStr;//住房性质

    private BigDecimal abBtje;//补贴金额

    private String abBtxz;//补贴性质

    private String yearAndMonth;//年份月份

    private String effectiveYearAndMonth;//有效年份月份

    private Integer subsidyNum;//补贴人数

    private Integer abJtrk;//家庭人口

    private String abRjjzmj;//人均面积

    private Integer lowIncomeHardFamilyNum;//困难家庭数(低收入)

    private Integer lowIncomeHardPersonNum;//困难人数(低收入)

    private BigDecimal lowIncomeHardSubsidyMoney;//困难补贴金额(低收入)

    private Integer lowSecurityHardFamilyNum;//困难家庭数(低保)

    private Integer lowSecurityHardPersonNum;//困难人数(低保)

    private BigDecimal lowSecurityHardSubsidyMoney;//困难补贴金额(低保)

    private BigDecimal disbursementAmount;//发放金额

    private String disbursementAmountZH;//发放金额(中文)

    private Short abZt;//状态（4.备案完成）

    private Short abLc;//流程（5.备案完成）

    public String getAbId() {
        return abId;
    }

    public void setAbId(String abId) {
        this.abId = abId;
    }

    public String getAbYhkh() {
        return abYhkh;
    }

    public void setAbYhkh(String abYhkh) {
        this.abYhkh = abYhkh;
    }

    public String getAfmXm() {
        return afmXm;
    }

    public void setAfmXm(String afmXm) {
        this.afmXm = afmXm;
    }

    public String getAfmXb() {
        return afmXb;
    }

    public void setAfmXb(String afmXb) {
        this.afmXb = afmXb;
    }

    public Integer getHouseholdsNum() {
        return householdsNum;
    }

    public void setHouseholdsNum(Integer householdsNum) {
        this.householdsNum = householdsNum;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getAfhZl() {
        return afhZl;
    }

    public void setAfhZl(String afhZl) {
        this.afhZl = afhZl;
    }

    public String getAfmLxdh() {
        return afmLxdh;
    }

    public void setAfmLxdh(String afmLxdh) {
        this.afmLxdh = afmLxdh;
    }

    public String getAfhZfxz() {
        return afhZfxz;
    }

    public void setAfhZfxz(String afhZfxz) {
        this.afhZfxz = afhZfxz;
    }

    public String getAfhZfxzStr() {
        return afhZfxzStr;
    }

    public void setAfhZfxzStr(String afhZfxzStr) {
        this.afhZfxzStr = afhZfxzStr;
    }

    public BigDecimal getAbBtje() {
        return abBtje;
    }

    public void setAbBtje(BigDecimal abBtje) {
        this.abBtje = abBtje;
    }

    public String getAbBtxz() {
        return abBtxz;
    }

    public void setAbBtxz(String abBtxz) {
        this.abBtxz = abBtxz;
    }

    public String getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(String yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public String getEffectiveYearAndMonth() {
        return effectiveYearAndMonth;
    }

    public void setEffectiveYearAndMonth(String effectiveYearAndMonth) {
        this.effectiveYearAndMonth = effectiveYearAndMonth;
    }

    public Integer getSubsidyNum() {
        return subsidyNum;
    }

    public void setSubsidyNum(Integer subsidyNum) {
        this.subsidyNum = subsidyNum;
    }

    public Integer getAbJtrk() {
        return abJtrk;
    }

    public void setAbJtrk(Integer abJtrk) {
        this.abJtrk = abJtrk;
    }

    public String getAbRjjzmj() {
        return abRjjzmj;
    }

    public void setAbRjjzmj(String abRjjzmj) {
        this.abRjjzmj = abRjjzmj;
    }

    public Integer getLowIncomeHardFamilyNum() {
        return lowIncomeHardFamilyNum;
    }

    public void setLowIncomeHardFamilyNum(Integer lowIncomeHardFamilyNum) {
        this.lowIncomeHardFamilyNum = lowIncomeHardFamilyNum;
    }

    public Integer getLowIncomeHardPersonNum() {
        return lowIncomeHardPersonNum;
    }

    public void setLowIncomeHardPersonNum(Integer lowIncomeHardPersonNum) {
        this.lowIncomeHardPersonNum = lowIncomeHardPersonNum;
    }

    public BigDecimal getLowIncomeHardSubsidyMoney() {
        return lowIncomeHardSubsidyMoney;
    }

    public void setLowIncomeHardSubsidyMoney(BigDecimal lowIncomeHardSubsidyMoney) {
        this.lowIncomeHardSubsidyMoney = lowIncomeHardSubsidyMoney;
    }

    public Integer getLowSecurityHardFamilyNum() {
        return lowSecurityHardFamilyNum;
    }

    public void setLowSecurityHardFamilyNum(Integer lowSecurityHardFamilyNum) {
        this.lowSecurityHardFamilyNum = lowSecurityHardFamilyNum;
    }

    public Integer getLowSecurityHardPersonNum() {
        return lowSecurityHardPersonNum;
    }

    public void setLowSecurityHardPersonNum(Integer lowSecurityHardPersonNum) {
        this.lowSecurityHardPersonNum = lowSecurityHardPersonNum;
    }

    public BigDecimal getLowSecurityHardSubsidyMoney() {
        return lowSecurityHardSubsidyMoney;
    }

    public void setLowSecurityHardSubsidyMoney(BigDecimal lowSecurityHardSubsidyMoney) {
        this.lowSecurityHardSubsidyMoney = lowSecurityHardSubsidyMoney;
    }

    public BigDecimal getDisbursementAmount() {
        return disbursementAmount;
    }

    public void setDisbursementAmount(BigDecimal disbursementAmount) {
        this.disbursementAmount = disbursementAmount;
    }

    public String getDisbursementAmountZH() {
        return disbursementAmountZH;
    }

    public void setDisbursementAmountZH(String disbursementAmountZH) {
        this.disbursementAmountZH = disbursementAmountZH;
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
}
