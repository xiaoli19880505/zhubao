package com.sys.pojo.apply;

import java.util.Date;

public class ApproveRecord {
    private String apcId;

    private String applyId;

    private String approveId;

    private String apcComment;

    private Date approvetime;

    private String approvetimeStr;

    private String nameUrl;

    private String approveType;

    private String apremark;

    private String applyType;

    private String approveNode;

    private String approveUserid;

    private String baseImg;

    private String orderBy;//排序字段

    public String getApcId() {
        return apcId;
    }

    public void setApcId(String apcId) {
        this.apcId = apcId == null ? null : apcId.trim();
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId == null ? null : approveId.trim();
    }

    public String getApcComment() {
        return apcComment;
    }

    public void setApcComment(String apcComment) {
        this.apcComment = apcComment == null ? null : apcComment.trim();
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl == null ? null : nameUrl.trim();
    }

    public String getApproveType() {
        return approveType;
    }

    public void setApproveType(String approveType) {
        this.approveType = approveType == null ? null : approveType.trim();
    }

    public String getApremark() {
        return apremark;
    }

    public void setApremark(String apremark) {
        this.apremark = apremark == null ? null : apremark.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public String getApproveNode() {
        return approveNode;
    }

    public void setApproveNode(String approveNode) {
        this.approveNode = approveNode == null ? null : approveNode.trim();
    }

    public String getApproveUserid() {
        return approveUserid;
    }

    public void setApproveUserid(String approveUserid) {
        this.approveUserid = approveUserid == null ? null : approveUserid.trim();
    }

    public String getBaseImg() {
        return baseImg;
    }

    public void setBaseImg(String baseImg) {
        this.baseImg = baseImg;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getApprovetimeStr() {
        return approvetimeStr;
    }

    public void setApprovetimeStr(String approvetimeStr) {
        this.approvetimeStr = approvetimeStr;
    }
}