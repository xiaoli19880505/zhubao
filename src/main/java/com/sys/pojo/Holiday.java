package com.sys.pojo;

import java.util.Date;

public class Holiday {
    private String hid;

    private String year;

    private String month;

    private String day;

    private String fullDate;

    private String week;

    private String isWeekday;

    private Date insertTime;

    private String insertUserid;

    private String remark;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid == null ? null : hid.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate == null ? null : fullDate.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getIsWeekday() {
        return isWeekday;
    }

    public void setIsWeekday(String isWeekday) {
        this.isWeekday = isWeekday == null ? null : isWeekday.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertUserid() {
        return insertUserid;
    }

    public void setInsertUserid(String insertUserid) {
        this.insertUserid = insertUserid == null ? null : insertUserid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}