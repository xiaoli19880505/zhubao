package com.sys.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sys.pojo.extend.DataGridResult;

import java.util.Date;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc 文章
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleInfo extends DataGridResult {

    private String id;//主键ID

    private String articleCode;//文章编码

    private String articleName;//文章标题

    private Date issuingTime;//发表时间

    private String author;//作者

    private Integer clickNumber;//点击次数

    private String columnId;//栏目外键

    private Date createTime;//创建时间

    private String createPerson;//创建人

    private Date updateTime;//修改时间

    private String updatePerson;//修改人

    private String deleteFlag;//删除标记(T:删除 F:可用)

    private String articleBody;//文章正文

    private ColumnInfo columnInfo;//外键对象栏目

    private String createTimeStr;//创建时间

    private String updateTimeStr;//修改时间

    private String issuingTimeStr;//发布时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode == null ? null : articleCode.trim();
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName == null ? null : articleName.trim();
    }

    public Date getIssuingTime() {
        return issuingTime;
    }

    public void setIssuingTime(Date issuingTime) {
        this.issuingTime = issuingTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId == null ? null : columnId.trim();
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

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody == null ? null : articleBody.trim();
    }

    public ColumnInfo getColumnInfo() {
        return columnInfo;
    }

    public void setColumnInfo(ColumnInfo columnInfo) {
        this.columnInfo = columnInfo;
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

    public String getIssuingTimeStr() {
        return issuingTimeStr;
    }

    public void setIssuingTimeStr(String issuingTimeStr) {
        this.issuingTimeStr = issuingTimeStr;
    }
}