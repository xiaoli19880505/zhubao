package com.sys.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sys.pojo.extend.DataGridResult;

import java.util.Date;
import java.util.List;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc 栏目
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColumnInfo extends DataGridResult {
    private String id;//主键ID

    private String columnCode;//栏目编码

    private String columnName;//栏目名称

    private String url;//路径

    private Short columnLevel;//级别(顶级 1,2...)

    private String parentId;//父级ID

    private Short sequence;//栏目排序位置

    private Date createTime;//创建时间

    private String createPerson;//创建人

    private Date updateTime;//修改时间

    private String updatePerson;//修改人

    private String deleteFlag;//删除标记(T:删除 F:可用)

    private String downFlag;//是否为下载中心（T:是F否）

    private String createTimeStr;//创建时间

    private String updateTimeStr;//修改时间

    private List<ArticleInfo> articleInfoList;//文章列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode == null ? null : columnCode.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getColumnLevel() {
        return columnLevel;
    }

    public void setColumnLevel(Short columnLevel) {
        this.columnLevel = columnLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Short getSequence() {
        return sequence;
    }

    public void setSequence(Short sequence) {
        this.sequence = sequence;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public String getDownFlag() {
        return downFlag;
    }

    public void setDownFlag(String downFlag) {
        this.downFlag = downFlag;
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

    public List<ArticleInfo> getArticleInfoList() {
        return articleInfoList;
    }

    public void setArticleInfoList(List<ArticleInfo> articleInfoList) {
        this.articleInfoList = articleInfoList;
    }
}