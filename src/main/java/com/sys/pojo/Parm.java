package com.sys.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parm {
    private String prId;
    private String prSetcode;//字典名
    private String prSetName;//名称
    private String prIsmaintainable;//是否可维护
    private String prIscolor;//是否调用颜色

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getPrSetcode() {
        return prSetcode;
    }

    public void setPrSetcode(String prSetcode) {
        this.prSetcode = prSetcode;
    }

    public String getPrSetName() {
        return prSetName;
    }

    public void setPrSetName(String prSetName) {
        this.prSetName = prSetName;
    }

    public String getPrIsmaintainable() {
        return prIsmaintainable;
    }

    public void setPrIsmaintainable(String prIsmaintainable) {
        this.prIsmaintainable = prIsmaintainable;
    }

    public String getPrIscolor() {
        return prIscolor;
    }

    public void setPrIscolor(String prIscolor) {
        this.prIscolor = prIscolor;
    }
}
