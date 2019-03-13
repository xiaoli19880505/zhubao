package com.sys.pojo.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sys.pojo.extend.DataGridResult;

import java.util.Date;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/19 0012
 * @desc 合同模板
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractTemplate extends DataGridResult {
    private String ctId;//合同模板ID

    private String ctCode;//合同模板编码

    private String ctName;//合同模板名称

    private String ctUrl;//合同路径

    private String ctInputInfo;//合同填入信息json

    private Date createTime;//创建时间

    private String createPerson;//创建人

    private Date updateTime;//修改时间

    private String updatePerson;//修改人

    private String deleteFlag;//删除标记(T删除,F未删除)

    private String createTimeStr;

    private String updateTimeStr;

    public String getCtId() {
        return ctId;
    }

    public void setCtId(String ctId) {
        this.ctId = ctId == null ? null : ctId.trim();
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode == null ? null : ctCode.trim();
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName == null ? null : ctName.trim();
    }

    public String getCtUrl() {
        return ctUrl;
    }

    public void setCtUrl(String ctUrl) {
        this.ctUrl = ctUrl == null ? null : ctUrl.trim();
    }

    public String getCtInputInfo() {
        return ctInputInfo;
    }

    public void setCtInputInfo(String ctInputInfo) {
        this.ctInputInfo = ctInputInfo == null ? null : ctInputInfo.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}