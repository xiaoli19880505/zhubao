package com.sys.pojo.apply;

/**
 * 申请人住房信息
 */
public class ApplyFamilyHouse {
    private String afhId;//申报房屋ID

    private String afhSqid;//申请ID

    private String afhFwid;//房屋ID

    private String afhZl;//坐落

    private String afhCqr;//产权人

    private String afhMj;//面积

    private String afhRjmj;//人均面积

    private String afhZfxz;//住房性质

    private String afhLb;//类别(1、补贴)

    private Short afhFlag;//0为删除，1为正常

    public String getAfhId() {
        return afhId;
    }

    public void setAfhId(String afhId) {
        this.afhId = afhId == null ? null : afhId.trim();
    }

    public String getAfhSqid() {
        return afhSqid;
    }

    public void setAfhSqid(String afhSqid) {
        this.afhSqid = afhSqid == null ? null : afhSqid.trim();
    }

    public String getAfhFwid() {
        return afhFwid;
    }

    public void setAfhFwid(String afhFwid) {
        this.afhFwid = afhFwid == null ? null : afhFwid.trim();
    }

    public String getAfhZl() {
        return afhZl;
    }

    public void setAfhZl(String afhZl) {
        this.afhZl = afhZl == null ? null : afhZl.trim();
    }

    public String getAfhCqr() {
        return afhCqr;
    }

    public void setAfhCqr(String afhCqr) {
        this.afhCqr = afhCqr == null ? null : afhCqr.trim();
    }

    public String getAfhMj() {
        return afhMj;
    }

    public void setAfhMj(String afhMj) {
        this.afhMj = afhMj == null ? null : afhMj.trim();
    }

    public String getAfhRjmj() {
        return afhRjmj;
    }

    public void setAfhRjmj(String afhRjmj) {
        this.afhRjmj = afhRjmj == null ? null : afhRjmj.trim();
    }

    public String getAfhZfxz() {
        return afhZfxz;
    }

    public void setAfhZfxz(String afhZfxz) {
        this.afhZfxz = afhZfxz == null ? null : afhZfxz.trim();
    }

    public String getAfhLb() {
        return afhLb;
    }

    public void setAfhLb(String afhLb) {
        this.afhLb = afhLb == null ? null : afhLb.trim();
    }

    public Short getAfhFlag() {
        return afhFlag;
    }

    public void setAfhFlag(Short afhFlag) {
        this.afhFlag = afhFlag;
    }
}