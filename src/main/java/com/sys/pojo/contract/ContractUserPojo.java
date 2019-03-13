package com.sys.pojo.contract;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

/**
 * 合同信息表
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractUserPojo {

    private String userName;//用户名

    private String userIDCard;//身份证

    private String regionName;//区域名称

    private String regionCode;//区域编码

    private String  year;//年份

    private String month;//月份

    private String day;//日期

    private String code;//协议序号

    private String roomNum;//居室数量

    private String leaseTerm;//租赁期限

    private String leaseTermYearSta;//租赁期限开始年

    private String leaseTermMonthSta;//租赁期限开始月

    private String leaseTermDaySta;//租赁期限开始日

    private String leaseTermYearEnd;//租赁期限开始年

    private String leaseTermMonthEnd;//租赁期限开始月

    private String leaseTermDayEnd;//租赁期限开始日

    private BigDecimal monthlyRent;//月租金

    private String monthlyRentZH;//月租金(中文)

    private String firstPartyOpinion;//甲方意见

//    private String SecondPartyOpinion;//乙方意见

    private String disputeResolution;//争议解决方式

    private String rentalSubsidiesNum;//租赁补贴人数

    private int rentalSubsidiesArea;//人均补贴面积

    private BigDecimal rentalSubsidiesUP;//补贴单价

    private BigDecimal rentalSubsidiesTP;//补贴总价

    private String rentalSubsidiesTPZH;//补贴总价(中文)

    private String settlementDate;//结算日期

    private String accountTitle;//账户名称

    private String bankAccount;//银行账户

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIDCard() {
        return userIDCard;
    }

    public void setUserIDCard(String userIDCard) {
        this.userIDCard = userIDCard;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public String getLeaseTermYearSta() {
        return leaseTermYearSta;
    }

    public void setLeaseTermYearSta(String leaseTermYearSta) {
        this.leaseTermYearSta = leaseTermYearSta;
    }

    public String getLeaseTermMonthSta() {
        return leaseTermMonthSta;
    }

    public void setLeaseTermMonthSta(String leaseTermMonthSta) {
        this.leaseTermMonthSta = leaseTermMonthSta;
    }

    public String getLeaseTermDaySta() {
        return leaseTermDaySta;
    }

    public void setLeaseTermDaySta(String leaseTermDaySta) {
        this.leaseTermDaySta = leaseTermDaySta;
    }

    public String getLeaseTermYearEnd() {
        return leaseTermYearEnd;
    }

    public void setLeaseTermYearEnd(String leaseTermYearEnd) {
        this.leaseTermYearEnd = leaseTermYearEnd;
    }

    public String getLeaseTermMonthEnd() {
        return leaseTermMonthEnd;
    }

    public void setLeaseTermMonthEnd(String leaseTermMonthEnd) {
        this.leaseTermMonthEnd = leaseTermMonthEnd;
    }

    public String getLeaseTermDayEnd() {
        return leaseTermDayEnd;
    }

    public void setLeaseTermDayEnd(String leaseTermDayEnd) {
        this.leaseTermDayEnd = leaseTermDayEnd;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getMonthlyRentZH() {
        return monthlyRentZH;
    }

    public void setMonthlyRentZH(String monthlyRentZH) {
        this.monthlyRentZH = monthlyRentZH;
    }

    public String getFirstPartyOpinion() {
        return firstPartyOpinion;
    }

    public void setFirstPartyOpinion(String firstPartyOpinion) {
        this.firstPartyOpinion = firstPartyOpinion;
    }

    public String getDisputeResolution() {
        return disputeResolution;
    }

    public void setDisputeResolution(String disputeResolution) {
        this.disputeResolution = disputeResolution;
    }

    public String getRentalSubsidiesNum() {
        return rentalSubsidiesNum;
    }

    public void setRentalSubsidiesNum(String rentalSubsidiesNum) {
        this.rentalSubsidiesNum = rentalSubsidiesNum;
    }

    public int getRentalSubsidiesArea() {
        return rentalSubsidiesArea;
    }

    public void setRentalSubsidiesArea(int rentalSubsidiesArea) {
        this.rentalSubsidiesArea = rentalSubsidiesArea;
    }

    public BigDecimal getRentalSubsidiesUP() {
        return rentalSubsidiesUP;
    }

    public void setRentalSubsidiesUP(BigDecimal rentalSubsidiesUP) {
        this.rentalSubsidiesUP = rentalSubsidiesUP;
    }

    public BigDecimal getRentalSubsidiesTP() {
        return rentalSubsidiesTP;
    }

    public void setRentalSubsidiesTP(BigDecimal rentalSubsidiesTP) {
        this.rentalSubsidiesTP = rentalSubsidiesTP;
    }

    public String getRentalSubsidiesTPZH() {
        return rentalSubsidiesTPZH;
    }

    public void setRentalSubsidiesTPZH(String rentalSubsidiesTPZH) {
        this.rentalSubsidiesTPZH = rentalSubsidiesTPZH;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
