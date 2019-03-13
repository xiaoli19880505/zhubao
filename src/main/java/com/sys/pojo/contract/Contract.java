package com.sys.pojo.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sys.pojo.extend.DataGridResult;

import java.util.Date;
/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/19 0012
 * @desc 合同明细
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contract extends DataGridResult {
    private String cId;//合同ID

    private String cFwid;//房屋ID

    private String cHtbh;//合同编号

    private String cHtqyrq;//合同签约日期

    private String cHtqysj;//合同签约时间

    private String cHtbarq;//合同备案日期

    private String cHtbasj;//合同备案时间

    private String cQyczy;//签约操作人

    private String cBaczy;//备案操作人

    private Short cLc;//合同备案流程 1签约 2备案

    private Short cZt;//合同备案状态 1正常 2续约 3作废

    private String cSqid;//申请ID

    private String cHtlx;//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同

    private String cCol1;

    private String cCol2;

    private String cCol3;

    private String cCol4;

    private String cCol5;

    private String cCol6;

    private String cCol7;

    private String cCol8;

    private String cCol9;

    private String cCol10;

    private String cCol11;

    private String cCol12;

    private String cCol13;

    private String cCol14;

    private String cCol15;

    private String cCol16;

    private String cCol17;

    private String cCol18;

    private String cCol19;

    private String cCol20;

    private String cCol21;

    private String cCol22;

    private String cCol23;

    private String cCol24;

    private String cCol25;

    private String cCol26;

    private String cCol27;

    private String cCol28;

    private String cCol29;

    private String cCol30;

    private String cCol31;

    private String cCol32;

    private String cCol33;

    private String cCol34;

    private String cCol35;

    private String cCol36;

    private String cCol37;

    private String cCol38;

    private String cCol39;

    private String cCol40;

    private String cSsq;//区域

    private Short cZfzt;//作废状态:0无状态 1中途作废 2到期作废

    private Object cStime;//合同租期开始时间

    private Object cEtime;//合同租期结束时间

    private Short cRent;//合同租金

    private Object cRentenddate;//租金到期时间

    private Object cPropertychargeenddate;//物业费到期时间

    private Short cBasementacre;//地下室面积

    private Short cBalconyacre;//阳台面积

    private Short cLoftacre;//阁楼面积

    private Short cLofteaveshight;//阁楼檐高

    private Short cUseacre;//使用面积

    private Date cHtnssj;//合同下一次年审时间

    private Date cHtkssj;//合同内容：合同开始时间

    private Date cHtjssj;//合同内容：合同结束时间

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    public String getcFwid() {
        return cFwid;
    }

    public void setcFwid(String cFwid) {
        this.cFwid = cFwid == null ? null : cFwid.trim();
    }

    public String getcHtbh() {
        return cHtbh;
    }

    public void setcHtbh(String cHtbh) {
        this.cHtbh = cHtbh == null ? null : cHtbh.trim();
    }

    public String getcHtqyrq() {
        return cHtqyrq;
    }

    public void setcHtqyrq(String cHtqyrq) {
        this.cHtqyrq = cHtqyrq == null ? null : cHtqyrq.trim();
    }

    public String getcHtqysj() {
        return cHtqysj;
    }

    public void setcHtqysj(String cHtqysj) {
        this.cHtqysj = cHtqysj == null ? null : cHtqysj.trim();
    }

    public String getcHtbarq() {
        return cHtbarq;
    }

    public void setcHtbarq(String cHtbarq) {
        this.cHtbarq = cHtbarq == null ? null : cHtbarq.trim();
    }

    public String getcHtbasj() {
        return cHtbasj;
    }

    public void setcHtbasj(String cHtbasj) {
        this.cHtbasj = cHtbasj == null ? null : cHtbasj.trim();
    }

    public String getcQyczy() {
        return cQyczy;
    }

    public void setcQyczy(String cQyczy) {
        this.cQyczy = cQyczy == null ? null : cQyczy.trim();
    }

    public String getcBaczy() {
        return cBaczy;
    }

    public void setcBaczy(String cBaczy) {
        this.cBaczy = cBaczy == null ? null : cBaczy.trim();
    }

    public Short getcLc() {
        return cLc;
    }

    public void setcLc(Short cLc) {
        this.cLc = cLc;
    }

    public Short getcZt() {
        return cZt;
    }

    public void setcZt(Short cZt) {
        this.cZt = cZt;
    }

    public String getcSqid() {
        return cSqid;
    }

    public void setcSqid(String cSqid) {
        this.cSqid = cSqid == null ? null : cSqid.trim();
    }

    public String getcHtlx() {
        return cHtlx;
    }

    public void setcHtlx(String cHtlx) {
        this.cHtlx = cHtlx == null ? null : cHtlx.trim();
    }

    public String getcCol1() {
        return cCol1;
    }

    public void setcCol1(String cCol1) {
        this.cCol1 = cCol1 == null ? null : cCol1.trim();
    }

    public String getcCol2() {
        return cCol2;
    }

    public void setcCol2(String cCol2) {
        this.cCol2 = cCol2 == null ? null : cCol2.trim();
    }

    public String getcCol3() {
        return cCol3;
    }

    public void setcCol3(String cCol3) {
        this.cCol3 = cCol3 == null ? null : cCol3.trim();
    }

    public String getcCol4() {
        return cCol4;
    }

    public void setcCol4(String cCol4) {
        this.cCol4 = cCol4 == null ? null : cCol4.trim();
    }

    public String getcCol5() {
        return cCol5;
    }

    public void setcCol5(String cCol5) {
        this.cCol5 = cCol5 == null ? null : cCol5.trim();
    }

    public String getcCol6() {
        return cCol6;
    }

    public void setcCol6(String cCol6) {
        this.cCol6 = cCol6 == null ? null : cCol6.trim();
    }

    public String getcCol7() {
        return cCol7;
    }

    public void setcCol7(String cCol7) {
        this.cCol7 = cCol7 == null ? null : cCol7.trim();
    }

    public String getcCol8() {
        return cCol8;
    }

    public void setcCol8(String cCol8) {
        this.cCol8 = cCol8 == null ? null : cCol8.trim();
    }

    public String getcCol9() {
        return cCol9;
    }

    public void setcCol9(String cCol9) {
        this.cCol9 = cCol9 == null ? null : cCol9.trim();
    }

    public String getcCol10() {
        return cCol10;
    }

    public void setcCol10(String cCol10) {
        this.cCol10 = cCol10 == null ? null : cCol10.trim();
    }

    public String getcCol11() {
        return cCol11;
    }

    public void setcCol11(String cCol11) {
        this.cCol11 = cCol11 == null ? null : cCol11.trim();
    }

    public String getcCol12() {
        return cCol12;
    }

    public void setcCol12(String cCol12) {
        this.cCol12 = cCol12 == null ? null : cCol12.trim();
    }

    public String getcCol13() {
        return cCol13;
    }

    public void setcCol13(String cCol13) {
        this.cCol13 = cCol13 == null ? null : cCol13.trim();
    }

    public String getcCol14() {
        return cCol14;
    }

    public void setcCol14(String cCol14) {
        this.cCol14 = cCol14 == null ? null : cCol14.trim();
    }

    public String getcCol15() {
        return cCol15;
    }

    public void setcCol15(String cCol15) {
        this.cCol15 = cCol15 == null ? null : cCol15.trim();
    }

    public String getcCol16() {
        return cCol16;
    }

    public void setcCol16(String cCol16) {
        this.cCol16 = cCol16 == null ? null : cCol16.trim();
    }

    public String getcCol17() {
        return cCol17;
    }

    public void setcCol17(String cCol17) {
        this.cCol17 = cCol17 == null ? null : cCol17.trim();
    }

    public String getcCol18() {
        return cCol18;
    }

    public void setcCol18(String cCol18) {
        this.cCol18 = cCol18 == null ? null : cCol18.trim();
    }

    public String getcCol19() {
        return cCol19;
    }

    public void setcCol19(String cCol19) {
        this.cCol19 = cCol19 == null ? null : cCol19.trim();
    }

    public String getcCol20() {
        return cCol20;
    }

    public void setcCol20(String cCol20) {
        this.cCol20 = cCol20 == null ? null : cCol20.trim();
    }

    public String getcCol21() {
        return cCol21;
    }

    public void setcCol21(String cCol21) {
        this.cCol21 = cCol21 == null ? null : cCol21.trim();
    }

    public String getcCol22() {
        return cCol22;
    }

    public void setcCol22(String cCol22) {
        this.cCol22 = cCol22 == null ? null : cCol22.trim();
    }

    public String getcCol23() {
        return cCol23;
    }

    public void setcCol23(String cCol23) {
        this.cCol23 = cCol23 == null ? null : cCol23.trim();
    }

    public String getcCol24() {
        return cCol24;
    }

    public void setcCol24(String cCol24) {
        this.cCol24 = cCol24 == null ? null : cCol24.trim();
    }

    public String getcCol25() {
        return cCol25;
    }

    public void setcCol25(String cCol25) {
        this.cCol25 = cCol25 == null ? null : cCol25.trim();
    }

    public String getcCol26() {
        return cCol26;
    }

    public void setcCol26(String cCol26) {
        this.cCol26 = cCol26 == null ? null : cCol26.trim();
    }

    public String getcCol27() {
        return cCol27;
    }

    public void setcCol27(String cCol27) {
        this.cCol27 = cCol27 == null ? null : cCol27.trim();
    }

    public String getcCol28() {
        return cCol28;
    }

    public void setcCol28(String cCol28) {
        this.cCol28 = cCol28 == null ? null : cCol28.trim();
    }

    public String getcCol29() {
        return cCol29;
    }

    public void setcCol29(String cCol29) {
        this.cCol29 = cCol29 == null ? null : cCol29.trim();
    }

    public String getcCol30() {
        return cCol30;
    }

    public void setcCol30(String cCol30) {
        this.cCol30 = cCol30 == null ? null : cCol30.trim();
    }

    public String getcCol31() {
        return cCol31;
    }

    public void setcCol31(String cCol31) {
        this.cCol31 = cCol31 == null ? null : cCol31.trim();
    }

    public String getcCol32() {
        return cCol32;
    }

    public void setcCol32(String cCol32) {
        this.cCol32 = cCol32 == null ? null : cCol32.trim();
    }

    public String getcCol33() {
        return cCol33;
    }

    public void setcCol33(String cCol33) {
        this.cCol33 = cCol33 == null ? null : cCol33.trim();
    }

    public String getcCol34() {
        return cCol34;
    }

    public void setcCol34(String cCol34) {
        this.cCol34 = cCol34 == null ? null : cCol34.trim();
    }

    public String getcCol35() {
        return cCol35;
    }

    public void setcCol35(String cCol35) {
        this.cCol35 = cCol35 == null ? null : cCol35.trim();
    }

    public String getcCol36() {
        return cCol36;
    }

    public void setcCol36(String cCol36) {
        this.cCol36 = cCol36 == null ? null : cCol36.trim();
    }

    public String getcCol37() {
        return cCol37;
    }

    public void setcCol37(String cCol37) {
        this.cCol37 = cCol37 == null ? null : cCol37.trim();
    }

    public String getcCol38() {
        return cCol38;
    }

    public void setcCol38(String cCol38) {
        this.cCol38 = cCol38 == null ? null : cCol38.trim();
    }

    public String getcCol39() {
        return cCol39;
    }

    public void setcCol39(String cCol39) {
        this.cCol39 = cCol39 == null ? null : cCol39.trim();
    }

    public String getcCol40() {
        return cCol40;
    }

    public void setcCol40(String cCol40) {
        this.cCol40 = cCol40 == null ? null : cCol40.trim();
    }

    public String getcSsq() {
        return cSsq;
    }

    public void setcSsq(String cSsq) {
        this.cSsq = cSsq == null ? null : cSsq.trim();
    }

    public Short getcZfzt() {
        return cZfzt;
    }

    public void setcZfzt(Short cZfzt) {
        this.cZfzt = cZfzt;
    }

    public Object getcStime() {
        return cStime;
    }

    public void setcStime(Object cStime) {
        this.cStime = cStime;
    }

    public Object getcEtime() {
        return cEtime;
    }

    public void setcEtime(Object cEtime) {
        this.cEtime = cEtime;
    }

    public Short getcRent() {
        return cRent;
    }

    public void setcRent(Short cRent) {
        this.cRent = cRent;
    }

    public Object getcRentenddate() {
        return cRentenddate;
    }

    public void setcRentenddate(Object cRentenddate) {
        this.cRentenddate = cRentenddate;
    }

    public Object getcPropertychargeenddate() {
        return cPropertychargeenddate;
    }

    public void setcPropertychargeenddate(Object cPropertychargeenddate) {
        this.cPropertychargeenddate = cPropertychargeenddate;
    }

    public Short getcBasementacre() {
        return cBasementacre;
    }

    public void setcBasementacre(Short cBasementacre) {
        this.cBasementacre = cBasementacre;
    }

    public Short getcBalconyacre() {
        return cBalconyacre;
    }

    public void setcBalconyacre(Short cBalconyacre) {
        this.cBalconyacre = cBalconyacre;
    }

    public Short getcLoftacre() {
        return cLoftacre;
    }

    public void setcLoftacre(Short cLoftacre) {
        this.cLoftacre = cLoftacre;
    }

    public Short getcLofteaveshight() {
        return cLofteaveshight;
    }

    public void setcLofteaveshight(Short cLofteaveshight) {
        this.cLofteaveshight = cLofteaveshight;
    }

    public Short getcUseacre() {
        return cUseacre;
    }

    public void setcUseacre(Short cUseacre) {
        this.cUseacre = cUseacre;
    }

    public Date getcHtnssj() {
        return cHtnssj;
    }

    public void setcHtnssj(Date cHtnssj) {
        this.cHtnssj = cHtnssj;
    }

    public Date getcHtkssj() {
        return cHtkssj;
    }

    public void setcHtkssj(Date cHtkssj) {
        this.cHtkssj = cHtkssj;
    }

    public Date getcHtjssj() {
        return cHtjssj;
    }

    public void setcHtjssj(Date cHtjssj) {
        this.cHtjssj = cHtjssj;
    }
}