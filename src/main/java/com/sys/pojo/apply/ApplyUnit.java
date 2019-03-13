package com.sys.pojo.apply;

/**
 * 申请人单位信息表
 */
public class ApplyUnit {
    private String unitid; //主键id

    private String legelrep;//法定代表

    private String bsls;//营业执照

    private String entag;//委托代理人

    private String tel;//联系电话

    private String address;//联系地址

    private String aplid;//申请id

    private String apltype;//申请类别

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getLegelrep() {
        return legelrep;
    }

    public void setLegelrep(String legelrep) {
        this.legelrep = legelrep == null ? null : legelrep.trim();
    }

    public String getBsls() {
        return bsls;
    }

    public void setBsls(String bsls) {
        this.bsls = bsls == null ? null : bsls.trim();
    }

    public String getEntag() {
        return entag;
    }

    public void setEntag(String entag) {
        this.entag = entag == null ? null : entag.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAplid() {
        return aplid;
    }

    public void setAplid(String aplid) {
        this.aplid = aplid == null ? null : aplid.trim();
    }

    public String getApltype() {
        return apltype;
    }

    public void setApltype(String apltype) {
        this.apltype = apltype == null ? null : apltype.trim();
    }
}