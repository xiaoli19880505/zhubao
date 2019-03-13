package com.sys.pojo.serialnum;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc 流水号子表
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SerialNumLine {
    private String snlId;//主键ID

    private String snhId;//外键ID

    private String nowDate;//当前时间

    private Integer nowPosition;//流水号当前位置

    private Date createTime;//创建时间

    private String createPerson;//创建人

    private Date updateTime;//更改时间

    private String updatePerson;//更改人


    //前缀、后缀、中间部分 为编码拆分后的值，仅为方便操作，不做数据库存储
    private String snPrefix;//前缀

    private String snDateStructure;//中间部分

    private String position;//流水号

    private String code;//编码

    public String getSnlId() {
        return snlId;
    }

    public void setSnlId(String snlId) {
        this.snlId = snlId == null ? null : snlId.trim();
    }

    public String getSnhId() {
        return snhId;
    }

    public void setSnhId(String snhId) {
        this.snhId = snhId == null ? null : snhId.trim();
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public Integer getNowPosition() {
        return nowPosition;
    }

    public void setNowPosition(Integer nowPosition) {
        this.nowPosition = nowPosition;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public String getSnPrefix() {
        return snPrefix;
    }

    public void setSnPrefix(String snPrefix) {
        this.snPrefix = snPrefix;
    }

    public String getSnDateStructure() {
        return snDateStructure;
    }

    public void setSnDateStructure(String snDateStructure) {
        this.snDateStructure = snDateStructure;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}