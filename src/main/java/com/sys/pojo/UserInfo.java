package com.sys.pojo;

public class UserInfo {
    private String userid;

    private String usercode;

    private String userpwd;

    private String username;

    private String unitid;

    private String sex;

    private String linktel;

    private String email;

    private String state;

    private String describe;

    private String loginip;

    private String ssq;

    private String  ssj;

    /**
     * 所属区
     */
    private ParmItem parmItemssq;

    private ParmItem parmItemssjd;

    private String xzq;//"04"行政区对应的编号

    private String jiedao;//"05"街道对应编号


    public ParmItem getParmItemssq() {
        return parmItemssq;
    }

    public void setParmItemssq(ParmItem parmItemssq) {
        this.parmItemssq = parmItemssq;
    }

    public ParmItem getParmItemssjd() {
        return parmItemssjd;
    }

    public void setParmItemssjd(ParmItem parmItemssjd) {
        this.parmItemssjd = parmItemssjd;
    }

    public String getSsj() {
        return ssj;
    }

    public void setSsj(String ssj) {
        this.ssj = ssj == null ? null : ssj.trim();
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel == null ? null : linktel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq == null ? null : ssq.trim();
    }

    public String getXzq() {
        return xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getJiedao() {
        return jiedao;
    }

    public void setJiedao(String jiedao) {
        this.jiedao = jiedao;
    }
}