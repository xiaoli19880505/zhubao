package com.sys.pojo.blagsh;

import java.util.Date;

/**
 * @author wangli
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/19 0012
 * @desc 诚信审核实体类(加入失信)
 */
public class Blgsh {
    private String bsId;//主键id

    private String blgShyh;//审核用户id

    private Date blgSbrq;//上报日期

    private String blgState;//状态

    private Date blgPassdate;//审核完成日期

    private String blgProcessinstanceid;//流程实例id

    private String blgId;//关联公示条目单id

    private String blgApvusers;//审批用户记录

    public String getBsId() {
        return bsId;
    }

    public void setBsId(String bsId) {
        this.bsId = bsId == null ? null : bsId.trim();
    }

    public String getBlgShyh() {
        return blgShyh;
    }

    public void setBlgShyh(String blgShyh) {
        this.blgShyh = blgShyh == null ? null : blgShyh.trim();
    }

    public Date getBlgSbrq() {
        return blgSbrq;
    }

    public void setBlgSbrq(Date blgSbrq) {
        this.blgSbrq = blgSbrq;
    }

    public String getBlgState() {
        return blgState;
    }

    public void setBlgState(String blgState) {
        this.blgState = blgState == null ? null : blgState.trim();
    }

    public Date getBlgPassdate() {
        return blgPassdate;
    }

    public void setBlgPassdate(Date blgPassdate) {
        this.blgPassdate = blgPassdate;
    }

    public String getBlgProcessinstanceid() {
        return blgProcessinstanceid;
    }

    public void setBlgProcessinstanceid(String blgProcessinstanceid) {
        this.blgProcessinstanceid = blgProcessinstanceid == null ? null : blgProcessinstanceid.trim();
    }

    public String getBlgId() {
        return blgId;
    }

    public void setBlgId(String blgId) {
        this.blgId = blgId == null ? null : blgId.trim();
    }

    public String getBlgApvusers() {
        return blgApvusers;
    }

    public void setBlgApvusers(String blgApvusers) {
        this.blgApvusers = blgApvusers;
    }
}