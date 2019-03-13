package com.sys.pojo;

public class HisRent {
    private String hisId;

    private String userid;//租赁人ID

    private String fwId;//房屋id

    private String qysj;//签约时间

    private String jzsj;//截止时间

    private String sfsj;//上房时间

    private String tfsj;//退房时间

    private String sqid;//申请单id

    private String username;
    private String sfzh;

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId == null ? null : hisId.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFwId() {
        return fwId;
    }

    public void setFwId(String fwId) {
        this.fwId = fwId == null ? null : fwId.trim();
    }

    public String getQysj() {
        return qysj;
    }

    public void setQysj(String qysj) {
        this.qysj = qysj == null ? null : qysj.trim();
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj == null ? null : jzsj.trim();
    }

    public String getSfsj() {
        return sfsj;
    }

    public void setSfsj(String sfsj) {
        this.sfsj = sfsj == null ? null : sfsj.trim();
    }

    public String getTfsj() {
        return tfsj;
    }

    public void setTfsj(String tfsj) {
        this.tfsj = tfsj == null ? null : tfsj.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }
}