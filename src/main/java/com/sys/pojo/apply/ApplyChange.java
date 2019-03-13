package com.sys.pojo.apply;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 申请单变更记录实体
 */
public class ApplyChange {
    private String acId;//主键id

    private String acUserid;//操作人id

    private String acSqbh;//申请单编号

    private String acType;//操作类别（1、终审通过，2、摇号成功，3、摇号放弃，4、分房，5、换房，6、签订合同，7、退房）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date acTime;//操作日期

    private String acSqid;//申请id

    private String acApplyType;//申请业务类别

    private String username;//操作人姓名

    private String typename;//申请类型名称

    private String acTypeName;//操作类别名称

    private String acRemark;

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId == null ? null : acId.trim();
    }

    public String getAcUserid() {
        return acUserid;
    }

    public void setAcUserid(String acUserid) {
        this.acUserid = acUserid == null ? null : acUserid.trim();
    }

    public String getAcSqbh() {
        return acSqbh;
    }

    public void setAcSqbh(String acSqbh) {
        this.acSqbh = acSqbh == null ? null : acSqbh.trim();
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType == null ? null : acType.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getAcTime() {
        return acTime;
    }

    public void setAcTime(Date acTime) {
        this.acTime = acTime;
    }

    public String getAcSqid() {
        return acSqid;
    }

    public void setAcSqid(String acSqid) {
        this.acSqid = acSqid == null ? null : acSqid.trim();
    }

    public String getAcApplyType() {
        return acApplyType;
    }

    public void setAcApplyType(String acApplyType) {
        this.acApplyType = acApplyType == null ? null : acApplyType.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getAcTypeName() {
        return acTypeName;
    }

    public void setAcTypeName(String acTypeName) {
        this.acTypeName = acTypeName;
    }

    public String getAcRemark() {
        return acRemark;
    }

    public void setAcRemark(String acRemark) {
        this.acRemark = acRemark;
    }
}