package com.sys.pojo;

public class QxInfo {
    private String qxId;

    private String qxCode;

    private String qxName;

    private MenuInfo qxMeid=new MenuInfo();

    private String meId;

    private String qxDesc;

    public String getMeId() {
        return meId;
    }

    public void setMeId(String meId) {
        this.meId = meId;
    }

    public String getQxId() {
        return qxId;
    }

    public void setQxId(String qxId) {
        this.qxId = qxId == null ? null : qxId.trim();
    }

    public String getQxCode() {
        return qxCode;
    }

    public void setQxCode(String qxCode) {
        this.qxCode = qxCode == null ? null : qxCode.trim();
    }

    public String getQxName() {
        return qxName;
    }

    public void setQxName(String qxName) {
        this.qxName = qxName == null ? null : qxName.trim();
    }

    public MenuInfo getQxMeid() {
        return qxMeid;
    }

    public void setQxMeid(MenuInfo qxMeid) {
        this.qxMeid = qxMeid;
    }

    public String getQxDesc() {
        return qxDesc;
    }

    public void setQxDesc(String qxDesc) {
        this.qxDesc = qxDesc == null ? null : qxDesc.trim();
    }
}