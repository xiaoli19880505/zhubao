package com.sys.pojo.apply;

import java.math.BigDecimal;

/**
 * 申请人家庭成员实体
 */
public class ApplyFamilyMember implements Comparable<ApplyFamilyMember>{
    private String afmId;//申请家庭成员ID

    private String afmSqid;//申请ID

    private String afmXm;//姓名

    private String afmXb;//性别

    private String afmCsny;//出生年月

    private String afmSfzh;//身份证号

    private String afmHyzk;//婚姻状况

    private String afmGzdw;//工作单位

    private String afmLxdh;//联系电话

    private BigDecimal afmNsr;//年收入

    private String afmYsqrgx;//与申请人关系

    private Short afmFlag;//0为删除，1为正常

    private Short afmOrder;//0本人，>1配偶或对应申请表的顺序

    private String afmSsq;//所属区

    private String afmLb;//类别（0房屋保障，1补贴）

    public String getAfmId() {
        return afmId;
    }

    public void setAfmId(String afmId) {
        this.afmId = afmId == null ? null : afmId.trim();
    }

    public String getAfmSqid() {
        return afmSqid;
    }

    public void setAfmSqid(String afmSqid) {
        this.afmSqid = afmSqid == null ? null : afmSqid.trim();
    }

    public String getAfmXm() {
        return afmXm;
    }

    public void setAfmXm(String afmXm) {
        this.afmXm = afmXm == null ? null : afmXm.trim();
    }

    public String getAfmXb() {
        return afmXb;
    }

    public void setAfmXb(String afmXb) {
        this.afmXb = afmXb == null ? null : afmXb.trim();
    }

    public String getAfmCsny() {
        return afmCsny;
    }

    public void setAfmCsny(String afmCsny) {
        this.afmCsny = afmCsny == null ? null : afmCsny.trim();
    }

    public String getAfmSfzh() {
        return afmSfzh;
    }

    public void setAfmSfzh(String afmSfzh) {
        this.afmSfzh = afmSfzh == null ? null : afmSfzh.trim();
    }

    public String getAfmHyzk() {
        return afmHyzk;
    }

    public void setAfmHyzk(String afmHyzk) {
        this.afmHyzk = afmHyzk == null ? null : afmHyzk.trim();
    }

    public String getAfmGzdw() {
        return afmGzdw;
    }

    public void setAfmGzdw(String afmGzdw) {
        this.afmGzdw = afmGzdw == null ? null : afmGzdw.trim();
    }

    public String getAfmLxdh() {
        return afmLxdh;
    }

    public void setAfmLxdh(String afmLxdh) {
        this.afmLxdh = afmLxdh == null ? null : afmLxdh.trim();
    }

    public BigDecimal getAfmNsr() {
        return afmNsr;
    }

    public void setAfmNsr(BigDecimal afmNsr) {
        this.afmNsr = afmNsr;
    }

    public String getAfmYsqrgx() {
        return afmYsqrgx;
    }

    public void setAfmYsqrgx(String afmYsqrgx) {
        this.afmYsqrgx = afmYsqrgx == null ? null : afmYsqrgx.trim();
    }

    public Short getAfmFlag() {
        return afmFlag;
    }

    public void setAfmFlag(Short afmFlag) {
        this.afmFlag = afmFlag;
    }

    public Short getAfmOrder() {
        return afmOrder;
    }

    public void setAfmOrder(Short afmOrder) {
        this.afmOrder = afmOrder;
    }

    public String getAfmSsq() {
        return afmSsq;
    }

    public void setAfmSsq(String afmSsq) {
        this.afmSsq = afmSsq == null ? null : afmSsq.trim();
    }

    public String getAfmLb() {
        return afmLb;
    }

    public void setAfmLb(String afmLb) {
        this.afmLb = afmLb == null ? null : afmLb.trim();
    }

    @Override
    public int compareTo(ApplyFamilyMember o) {
        if("本人".equals(this.getAfmYsqrgx())){
            return -1;
        } else if("配偶".equals(this.getAfmYsqrgx()) && "本人".equals(o.getAfmYsqrgx())){
            return 1;
        }else if("配偶".equals(this.getAfmYsqrgx())){
            return -1;
        }else{
            return  1;
        }

    }
}