package com.sys.pojo;

public class QxRoleInfo {
    private String qrId;

    private String qrQxinfoid;

    private String qrRoleid;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId == null ? null : qrId.trim();
    }

    public String getQrQxinfoid() {
        return qrQxinfoid;
    }

    public void setQrQxinfoid(String qrQxinfoid) {
        this.qrQxinfoid = qrQxinfoid == null ? null : qrQxinfoid.trim();
    }

    public String getQrRoleid() {
        return qrRoleid;
    }

    public void setQrRoleid(String qrRoleid) {
        this.qrRoleid = qrRoleid == null ? null : qrRoleid.trim();
    }
}