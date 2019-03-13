package com.sys.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParmItem {
    private String id;
    private String piSetcode;
    private String piItemcode;
    private String piItemname;
    private String text;
    private String piColorvalue;
    private Integer piItemvalue;
    private String piParentsetcode;
    private List<ParmItem> children=new ArrayList<ParmItem>();
    private String state;
    private String parentName;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ParmItem> getChildren() {
        return children;
    }

    public void setChildren(List<ParmItem> children) {
        this.children = children;
    }

    public String getPiSetcode() {
        return piSetcode;
    }

    public void setPiSetcode(String piSetcode) {
        this.piSetcode = piSetcode;
    }

    public String getPiItemcode() {
        return piItemcode;
    }

    public void setPiItemcode(String piItemcode) {
        this.piItemcode = piItemcode;
    }

    public String getPiItemname() {
        return piItemname;
    }

    public void setPiItemname(String piItemname) {
        this.piItemname = piItemname;
    }

    public String getPiColorvalue() {
        return piColorvalue;
    }

    public void setPiColorvalue(String piColorvalue) {
        this.piColorvalue = piColorvalue;
    }

    public Integer getPiItemvalue() {
        return piItemvalue;
    }

    public void setPiItemvalue(Integer piItemvalue) {
        this.piItemvalue = piItemvalue;
    }

    public String getPiParentsetcode() {
        return piParentsetcode;
    }

    public void setPiParentsetcode(String piParentsetcode) {
        this.piParentsetcode = piParentsetcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
