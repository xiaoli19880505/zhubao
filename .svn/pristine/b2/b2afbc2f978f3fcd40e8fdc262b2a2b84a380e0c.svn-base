package com.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 短信发送记录实体
 */
public class Message {
    private String msid;//主键id

    private String linktel;//联系电话

    private String mstem;//短信模板

    private String mstype;//短信类型

    private Date mstime;//发送时间

    private String msuserid;//短信操作用户id

    private String sqbh;//申请编号

    private String toUsername;//发送去向用户姓名

    private String toUserSfzh;//发送去向用户身份证号

    private String appType;//业务类别

    private String opUsername;//操作用户姓名

    public String getMsid() {
        return msid;
    }

    public void setMsid(String msid) {
        this.msid = msid == null ? null : msid.trim();
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel == null ? null : linktel.trim();
    }

    public String getMstem() {
        return mstem;
    }

    public void setMstem(String mstem) {
        this.mstem = mstem == null ? null : mstem.trim();
    }

    public String getMstype() {
        return mstype;
    }

    public void setMstype(String mstype) {
        this.mstype = mstype == null ? null : mstype.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getMstime() {
        return mstime;
    }

    public void setMstime(Date mstime) {
        this.mstime = mstime;
    }

    public String getMsuserid() {
        return msuserid;
    }

    public void setMsuserid(String msuserid) {
        this.msuserid = msuserid == null ? null : msuserid.trim();
    }

    public String getSqbh() {
        return sqbh;
    }

    public void setSqbh(String sqbh) {
        this.sqbh = sqbh == null ? null : sqbh.trim();
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername == null ? null : toUsername.trim();
    }

    public String getToUserSfzh() {
        return toUserSfzh;
    }

    public void setToUserSfzh(String toUserSfzh) {
        this.toUserSfzh = toUserSfzh == null ? null : toUserSfzh.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getOpUsername() {
        return opUsername;
    }

    public void setOpUsername(String opUsername) {
        this.opUsername = opUsername;
    }
}