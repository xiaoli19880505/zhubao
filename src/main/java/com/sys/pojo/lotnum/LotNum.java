package com.sys.pojo.lotnum;

import java.util.Date;

/**
 * 摇号实体
 */
public class LotNum {
    private String lid;//实体id

    private String name;//申请人姓名

    private String sfzh;//申请人身份证号

    private String sqbh;//申请编号

    private String num;//摇号的序号

    private String state;//状态 0候选 1待选房

    private String userid;//摇号用户id

    private Date qztime;//操作时间

    private String sqlb;//申请类别

    public String getSqlb() {
        return sqlb;
    }

    public void setSqlb(String sqlb) {
        this.sqlb = sqlb;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh == null ? null : sfzh.trim();
    }

    public String getSqbh() {
        return sqbh;
    }

    public void setSqbh(String sqbh) {
        this.sqbh = sqbh == null ? null : sqbh.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getQztime() {
        return qztime;
    }

    public void setQztime(Date qztime) {
        this.qztime = qztime;
    }
}