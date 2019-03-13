<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>我的申请</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/mobile.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/common.css"/>
</head>
<body>
<div class="easyui-navpanel">
    <!-- 主界面不动、菜单移动 -->
    <!-- 侧滑导航根容器 -->
    <div class="mui-off-canvas-wrap mui-draggable mui-slide-in">
        <!-- 菜单容器 -->
        <%--<aside class="mui-off-canvas-left" id="offCanvasSide">
            <div class="mui-scroll-wrapper" >
                <div class="mui-scroll">
                    <!-- 菜单具体展示内容 -->
                    <ul class="mui-table-view">
                        <li class="mui-table-view-cell leftList" id="applyAside">
                            <a class="mui-navigate-right" >我的申请</a>
                        </li>
                        <li class="mui-table-view-cell leftList" id="audioAside">
                            <a class="mui-navigate-right" >我的年审</a>
                        </li>
                        <li class="mui-table-view-cell leftList" id="userAside">
                            <a class="mui-navigate-right" >个人信息</a>
                        </li>
                        <li class="mui-table-view-cell leftList" id="updateAside">
                            <a class="mui-navigate-right" >修改密码</a>
                        </li>
                        <li class="mui-table-view-cell leftList" id="exitAside">
                            <a class="mui-navigate-right" >退出</a>
                        </li>
                    </ul>
                </div>
            </div>
        </aside>--%>
        <!-- 主页面容器 -->
        <%--<div class="mui-inner-wrap">--%>
        <!-- 主页面标题 -->
        <header class="mui-bar mui-bar-nav">
            <a class="mui-icon mui-action-menu mui-icon-back mui-pull-left" id="back"></a>
            <h1 class="mui-title">低保特困住保补贴年审申请</h1>
        </header>
        <nav class="mui-bar mui-bar-tab">
            <a class="mui-tab-item " id="apply">
                <span class="mui-icon mui-icon-home"></span>
                <span class="mui-tab-label">我的申请</span>
            </a>
            <a class="mui-tab-item mui-active" id="audit">
                <span class="mui-icon mui-icon-list"></span>
                <span class="mui-tab-label">我的年审</span>
            </a>
            <a class="mui-tab-item" id="userInfo">
                <span class="mui-icon mui-icon-contact"></span>
                <span class="mui-tab-label">个人信息</span>
            </a>
            <a class="mui-tab-item" id="updatePwd">
                <span class="mui-icon mui-icon-gear"></span>
                <span class="mui-tab-label">修改密码</span>
            </a>
        </nav>
        <div class="mui-content mui-scroll-wrapper" id="pullrefresh">
            <div class="mui-scroll">
                <form method="post" id="form" class="easyui-form" enctype="multipart/form-data">
                    <!-- 主界面具体展示内容 -->
                    <div id="wrap1">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">基本信息<span class="signBtn" id="changeSign">查看签字</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="alsid" id="alsid">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="apSqlb" id="apSqlb">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-combobox" name="apSsq" style="width: 92%" data-options="label:'户籍所在区(县)',required:true,valueField:'piItemcode',textField:'piItemname',
	                                                                        	url:'<%=basePath %>ParmItem/selectSsqExceptCenter',onSelect:selectSSQ,editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-combobox" name="apJdbsc" id="apJdbsc" style="width: 92%" data-options="label:'户籍所在街道办事处',required:true,valueField:'piItemcode',textField:'piItemname',editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="apSqjwh" style="width: 92%" data-options="label:'户籍所在社区',required:true,validType:['chinese','empty']">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">申请人信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="abSqrid" id="abSqrid" data-options="label:'abSqrid'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmId" id="applyFamilyMembers0afmId" data-options="label:'afmId'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmSqid" id="applyFamilyMembers0afmSqid" data-options="label:'afmSqid'">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmXm" id="applyFamilyMembers0afmXm" data-options="label:'姓名',required:true,readonly:true,required:true,readonly:true,validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmSfzh" id="applyFamilyMembers0afmSfzh" data-options="label:'身份证号码',required:true,validType:['sfz','empty'],readonly:true">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[0].afmXb" id="applyFamilyMembers0afmXb" data-options="label:'性别',required:true,readonly:true,editable:false,panelHeight:'auto'">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                    <div class=" commonRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[0].afmHyzk" id="applyFamilyMembers0afmHyzk"  data-options="label:'婚姻状况',required:true,valueField:'piItemcode',textField:'piItemname',
		                                url:'<%=basePath %>ParmItem/getOptions?parmSetcode=07',editable:false,panelHeight:'auto',panelMaxHeight:'280',onSelect:selecthyzk">
                                        </select>
                                    </div>
                                    <div class=" commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmGzdw" id="applyFamilyMembers0afmGzdw" data-options="label:'工作单位',required:true,prompt:'请输入单位全称'">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmLxdh" id="applyFamilyMembers0afmLxdh" data-options="label:'联系电话',required:true,validType:'phone',readonly:true">
                                    </div>
                                    <div class=" commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmNsr" id="applyFamilyMembers0afmNsr" data-options="label:'个人年收入(元)',required:true,validType:['number','empty'],events:{keyup:changeSr}">
                                    </div>
                                    <div class="commonRow">
                                        <label class="textbox-label textbox-label-before" style="text-align: left; height: 32px; line-height: 10px;">是否特殊家庭</label>
                                        <input type="checkbox" name="apTsjt" value="3"/><label class="lispan">二级以上下肢重度残疾</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">配偶信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="abSqrpoid" id="abSqrpoid" data-options="label:'abSqrpoid'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmId" id="applyFamilyMembers1afmId" data-options="label:'afmId'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmSqid" id="applyFamilyMembers1afmSqid" data-options="label:'afmSqid'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmXm" id="applyFamilyMembers1afmXm" data-options="label:'姓名',required:true,validType:['empty','chinese','specialCharacter'],events:{blur:checkUser}">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmSfzh" id="applyFamilyMembers1afmSfzh" data-options="label:'身份证号码',required:true,validType:['sfz','empty'],events:{blur:checkUser}">
                                    </div>
                                    <div class="commonRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[1].afmXb" id="applyFamilyMembers1afmXb" data-options="label:'性别',required:true,editable:false,panelHeight:'auto'">
                                            <option value="女">女</option>
                                            <option value="男">男</option>
                                        </select>
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmGzdw" id="applyFamilyMembers1afmGzdw" data-options="label:'工作单位',prompt:'请输入单位全称'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmLxdh" id="applyFamilyMembers1afmLxdh" data-options="label:'联系电话',required:true,validType:'phone'">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[1].afmYsqrgx" id="applyFamilyMembers1afmYsqrgx"
                                                data-options="label:'关系',valueField:'piItemname',textField:'piItemname',panelHeight:'auto',readonly:true,editable:false">
                                            <option value="0">配偶</option>
                                        </select>
                                    </div>
                                    <li>
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmNsr" id="applyFamilyMembers1afmNsr" data-options="label:'个人年收入(元)',validType:['number','empty'],events:{keyup:changeSr}">
                                    </li>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">户籍信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="apSqhjnx" data-options="label:'户籍年限(年)',required:true,required:true,validType:['empty','numberZ','limitHJ[5]']">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="apJtnsr" id="apJtnsr" data-options="label:'家庭年收入(元)',readonly:true,required:true,validType:['number','empty']">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="apJtrk" id="apJtrk" data-options="label:'家庭人口',readonly:true,required:true,validType:['numberZ','empty']">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="apRjysr" id="apRjysr" data-options="label:'人均月收入(元)',required:true,validType:'number'">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">工作单位</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyUnit.aplid" id="applyUnitaplid" data-options="label:'afhId:'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyUnit.unitid" id="applyUnitunitid" data-options="label:'afhSqid:'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.legelrep" id="applyUnitlegelrep" data-options="label:'法定代表人',prompt:'非必填',validType:['chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.bsls" id="applyUnitbsls" data-options="label:'统一社会信用代码',prompt:'非必填',validType:'xyCode'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.entag" id="applyUnitentag" data-options="label:'委托代理人',prompt:'非必填',validType:['chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.tel"  id="applyUnittel" data-options="label:'联系方式',validType:'phoneTel',prompt:'非必填'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.address" id="applyUnitaddress" data-options="label:'联系住址',prompt:'非必填'">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-content-padded" style="margin-top: 2rem;">
                            <button id='next2' class="mui-btn mui-btn-block mui-btn-primary">下一步</button>
                        </div>
                    </div>
                    <div id="wrap2" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">家庭现住房情况</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhId" id="applyFamilyHouses0afhId" data-options="label:'afhId:'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhSqid" id="applyFamilyHouses0afhSqid" data-options="label:'afhSqid:'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhZl" id="applyFamilyHouses0afhZl" data-options="label:'房屋地址',required:true">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhCqr" id="applyFamilyHouses0afhCqr" data-options="label:'产权人',required:true,validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhMj" id="applyFamilyHouses0afhMj" data-options="label:'建筑面积(m²)',required:true,validType:['number','empty'],events:{keyup:changeMj}">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhRjmj" id="applyFamilyHouses0afhRjmj" data-options="label:'人均建筑面积(m²)',required:true,readonly:true,validType:['number','empty']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-combobox" name="applyFamilyHouses[0].afhZfxz" id="applyFamilyHouses0afhZfxz" data-options="label:'现住房性质',required:true,valueField:'piItemcode',textField:'piItemname',
		                                    url:'<%=basePath %>ParmItem/getOptions?parmSetcode=01',editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">住房变更情况</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcId" id="applyFamilyHouseChangeafhcId" data-options="label:'afhcId'">
                                    </div>
                                    <div style="display: none">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcSqid" id="applyFamilyHouseChangeafhcSqid" data-options="label:'afhcSqid'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcYzfzl" id="applyFamilyHouseChangeafhcYzfzl" data-options="label:'原住房地址',validType:['empty']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcCqr" id="applyFamilyHouseChangeafhcCqr" data-options="label:'原产权人',validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcJzmj" id="applyFamilyHouseChangeafhcJzmj" data-options="label:'建筑面积（m²）',validType:['number','empty']">
                                    </div>
                                    <div class="commonRow">
                                        <label class="textbox-label textbox-label-before" style="text-align: left; height: 32px; line-height: 10px;">方式</label>
                                        <input type="radio" name="applyFamilyHouseChange.afhcBgfs" value="拆迁" onclick="change()" required/>拆迁&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="applyFamilyHouseChange.afhcBgfs" value="转让" onclick="changeSecond()"/>转让
                                    </div>
                                    <div class="commonRow" id="cqtime">
                                        <input class="easyui-datebox" name="applyFamilyHouseChange.afhcBgsj" id="applyFamilyHouseChangeafhcBgsj" data-options="label:'拆迁时间',editable:false">
                                    </div>
                                    <div class="commonRow" id="cq">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcZrbcje" id="applyFamilyHouseChangeafhcZrbcje" data-options="label:'货币补偿金额(元)',validType:'number'">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">安置信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcAzfzl" id="applyFamilyHouseChangeafhcAzfzl" data-options="label:'安置房地址'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouseChange.afhcAzfjzmj" id="applyFamilyHouseChangeafhcAzfjzmj" data-options="label:'建筑面积(m²)',validType:['number']">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">低保/特困信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="apDbzbh" id="apDbzbh" data-options="label:'低保/特困证编号',required:true">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="apLqrs" id="apLqrs" data-options="label:'低保/特困领取人数',required:true,validType:['numberZ','empty']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-datebox" name="apFzsj" id="apFzsj" data-options="label:'低保/特困发证时间',required:true,editable:false,validType:['minDate','dbAnnual']">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-button-row">
                            <button type="button" class="mui-btn mui-btn-primary" id="prev1">上一步</button>
                            <button type="button" class="mui-btn mui-btn-success" id="next3">下一步</button>
                        </div>
                    </div>
                    <div id="wrap3" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">同住家庭成员情况<span class="greenPan">新增</span><span
                                    class="redPan">删除</span>
                            </div>
                        </div>
                        <div class="card-container"></div>
                        <div class="mui-button-row">
                            <button type="button" class="mui-btn mui-btn-primary" id="prev2">上一步</button>
                            <button type="button" class="mui-btn mui-btn-success" id="next4">下一步</button>
                        </div>
                    </div>
                    <div id="wrap4" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">收入情况证明材料</span><span class="reqSpan">*(必填)</span></span><span id="type0" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList0">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark0 filePicker" onclick="add(0)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">住房状况证明材料</span><span class="reqSpan">*(必填)</span></span><span id="type6" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList1">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark1 filePicker" onclick="add(1)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">家庭成员身份证</span><span class="reqSpan">*(必填)</span></span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList2">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark2 filePicker" onclick="add(2)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">家庭成员户口簿(含首页)</span><span class="reqSpan">*(必填)</span></span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList3">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark3 filePicker" onclick="add(3)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span class="name">合法有效家庭成员之间的赡养、抚养、扶养关系及共同生活的证明</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList4">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark4 filePicker" onclick="add(4)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span class="name">反映家庭财产状况的有关证明材料</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList5">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark5 filePicker" onclick="add(5)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">申请人申报财产真实性书面承诺</span><span class="reqSpan">*(必填)</span></span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList6">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark6 filePicker" onclick="add(6)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">特殊情况证明</span><span class="reqSpan">*(必填)</span></span><span id="type3" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList7">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark7 filePicker" onclick="add(7)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">婚姻情况证明</span><span class="reqSpan">*(必填)</span></span><span id="type2" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList8">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark8 filePicker" onclick="add(8)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">户籍所在地社区公示证明</span><span class="reqSpan">*(必填)</span></span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList9">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark9 filePicker" onclick="add(9)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span class="name">其他相关材料</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList10">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark10 filePicker" onclick="add(10)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-button-row">
                            <button type="button" class="mui-btn mui-btn-primary" id="prev3">上一步</button>
                            <button type="button" class="mui-btn mui-btn-success" id="tijiao">提交申请</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="mui-off-canvas-backdrop"></div>
        <%--</div>--%>
    </div>
</div>
<div id="picture" class="mui-popover mui-popover-action ">
    <ul class="mui-table-view"></ul>
</div>
<div id="signCard" class="mui-popover mui-popover-action ">
    <div id="signPicture"></div>
    <div id="signatureparent">
        <div id="signature"></div>
    </div>
    <div class="mui-button-row">
        <button type="button" class="mui-btn mui-btn-warning signBtn" id="clear">重置</button>
        <button type="button" class="mui-btn mui-btn-primary signBtn" id="confirmSign">确定</button>
    </div>
</div>
<style>
    .textbox-label {
        max-width: 46%;
        width: 11.2rem;
    }

    .commonRow .textbox, .commonRow .combo, .commonRow .datebox {
        min-width: 50% !important;
        max-width: 80% !important;
    }

    .commonRow .textbox-text, .commonRow .validatebox-text {
        width: 100% !important;
    }
    #signatureparent {
        color: darkblue;
        background-color: darkgrey;
    }
    #signPicture {
        width: 100%;
        background: #fff;
    }
    #signPicture img{
        width: 100%;
        height: 200px;

    }
</style>
<script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.mobile.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/webuploader/webuploader.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/public/jSignature.min.noconflict.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/public/public.js"></script>
<script>
    mui.init();
    var person = false;var i=1;//控制变量
    var j = 0, currentIndex = 0, sucIndex = 0,applyid="", codeArray = [], nameArray = [], initArray = [],idArray=[],sendArray=[];
    var paramSign="bukong";var initSign="";var signFlag=false;//签字版
    var isSave=true;//控制是否可以提交变量
    $sigdiv = $("#signature").jSignature({
        'UndoButton': false,
        'height':300,
        'width':400
    });
    var mask = mui.createMask();//创建遮罩层
    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.0006
    });
    $('.filePicker').each(function (index) {
        nameArray[index] = $(this).parent().parent().find('.name').text();
    });
    uploader = WebUploader.create({
        auto: false,
        swf: '<%=basePath %>srcApply/js/Uploader.swf',
        server: '<%=basePath %>volel/uoloadVolel',
        pick: {
            id: '.filePicker',
            label: '添加附件'
        },
        fileVal: "volname1",
        //sendAsBinary: true,//设置为二进制
        threads: 1,//并发最大数量限制
        fileNumLimit: 100,//限制最大张数100
        disableGlobalDnd: true, //禁用浏览器的拖拽功能，否则图片会被浏览器打开
        fileSizeLimit: 1000 * 1024 * 1024, // 总共的最大限制1000M
        fileSingleSizeLimit: 10 * 1024 * 1024, // 单文件的最大限制4M
        accept: {
            title: 'Images',
            extensions: 'jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        duplicate: true, //同一文件是否可重复选择
        compress: {
            width: 1200,
            height: 1200,
            quality: 90,// 图片质量，只有type为`image/jpeg`的时候才有效。
            allowMagnify: false,// 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            crop: false,// 是否允许裁剪。
            preserveHeaders: true,// 是否保留头部meta信息。
            noCompressIfLarger: false,
            compressSize: 0 // 单位字节，如果图片大小小于此值，不会采用压缩。
        },
        resize: false//尺寸不改变
    });
    function add(index){
        j=index;
    }
    function del(obj) {
        var fileId=obj.parent().attr("id");
        var index=weixin.getArrayIndex(idArray,fileId);
        idArray.splice(index,1);
        codeArray.splice(index,1);
        sendArray.splice(index,1);
        obj.parents(".thumbnail").remove();
        uploader.removeFile(fileId,true);
        uploader.getFiles('inited');
    }

    //图片上传提交
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            uploader.refresh();
            weixin.alert("请上传JPG、PNG、GIF、BMP格式图片文件");
        }else if (type == "F_DUPLICATE") {
            uploader.refresh();
            weixin.alert("请不要重复选择文件！");
        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
            uploader.refresh();
            weixin.alert("每次选择文件大小不超过10M");
        }else if(type=="F_EXCEED_SIZE"){
            uploader.refresh();
            weixin.alert("每次选择文件大小不超过10M")
        }else if(type=="Q_EXCEED_NUM_LIMIT"){
            uploader.refresh();
            weixin.alert("最多上传100张图片")
        }
    });
    uploader.on('fileQueued', function(file) {
        idArray.push(file.id);//存入数组
        codeArray.push(Number(j));
        sendArray.push(Number(j))
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img>' +
            '<div class="info" onclick="del($(this))">' + "删除" + '</div>' +
            '</div>'
            ),
            $img = $li.find('img');
        $("#fileList" + j + "").append($li);
        uploader.makeThumb(file, function(error, src) {
            if(error) {
                $("#fileList" + j + "").find(".thumbnail:last").remove();
                codeArray.pop();
                idArray.pop();
                sendArray.pop();
                uploader.removeFile(file);
                weixin.alert('请选择正确图片格式');
            }else{
                $img.attr('src', src);
            }
        }, 100, 100);
    });

    function send(applyid) {
        //上传后才执行
        var newArray=copyArray(sendArray);
        //未修改附件
        if(newArray.length==0) {
            weixin.toast('上传成功');
            setTimeout(function () {
                mask.close();
                weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
            }, 1000);
        }
        if(newArray.length>0){
            uploader.on('uploadBeforeSend', function (obj, data, headers) {
                var code = newArray[currentIndex];
                data.volname = nameArray[code];
                data.fileVal = "volname" + (Number(1 + Number(code)));
                data.applyid = applyid;
            });
            uploader.upload();
            //上传后才执行
            uploader.on('uploadError', function (file) {
                currentIndex++;
                if(currentIndex==newArray.length){
                    mask.close();
                    weixin.toast('上传失败');
                }
            });
            //上传后才执行
            uploader.on('uploadSuccess', function (file) {
                currentIndex++;
                sucIndex++;
                if (sucIndex == newArray.length) {
                    weixin.toast('上传成功');
                    setTimeout(function () {
                        mask.close();
                        weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
                    }, 1000);
                }
            });
        }
    }

    //处理数组
    function copyArray(codeArray){
        return codeArray
    }
    function solveArray(codeArray){
        return codeArray.sort(sequence);
    }
    function sequence(a,b){
        return a - b;
    }
    function judgeArray(newArray, initArray) {
        var judgeFlag = true;
        var bitianArray = [0, 1, 2, 3, 6, 7,8, 9];
        var endArray = newArray.concat(initArray);
        var temp = weixin.arraySplice(endArray);
        if (temp.length > 0) {
            for (var x in bitianArray) {
                if (typeof(weixin.getArrayIndex(temp, bitianArray[x])) == "boolean") {
                    judgeFlag = false;
                    return false;
                }
            }
        } else {
            judgeFlag = false;
        }
        return judgeFlag;
    }

    //提交
    function save() {
        if(isSave){
            var isValid = $('#form').form('validate');
            if(!isValid){
                isSave=true;
                weixin.toast('请检查是否有漏填或错填内容！');
                return false
            }else{
                isSave=false;
                checkUser()
            }
        }
    }
    //验证身份证信息是否合法
    function checkUser(){
        var userArray=[],sfzhArray=[];//所有身份证数组
        var checkIndex=$(".card-container .mui-card").length+2;
        for(var k=0;k<checkIndex;k++){
            var user=$.trim($("#applyFamilyMembers"+k+"afmXm").textbox('getValue'));
            if(user.length>0){
                userArray.push($("#applyFamilyMembers"+k+"afmXm").textbox('getValue'));
                sfzhArray.push($("#applyFamilyMembers"+k+"afmSfzh").textbox('getValue'));
            }
        }
        if(userArray.length>0){
            userArray.splice(0,1);
            sfzhArray.splice(0,1);
            var endUser=userArray;
            var endSfzh=sfzhArray;
            if(endUser.length==0){
                isSave=false;
                secondSave()
            }
            if(endUser.length>0){
                $.ajax({
                    url:'<%=basePath %>applyUserinfo/checkUsers',
                    type:'post',
                    async:true,//同步
                    data:{
                        usernames:endUser.toString(),
                        sfzhs:endSfzh.toString()
                    },
                    success:function(result){
                        if(result=="success"){
                            isSave=false;
                            secondSave();
                        }else{
                            isSave=true;
                            weixin.toast(result+"身份证和姓名不匹配");
                            return false
                        }
                    },error:function () {
                        isSave=true;
                        weixin.alert("数据库连接失败，请稍后再试");
                        return false
                    }
                });

            }
        }
    }
    function secondSave() {
        var judgeFlag=judgeArray(solveArray(codeArray),initArray);
        var flag=false;
        var jzxz=$.trim($("#applyFamilyHouses0afhZfxz").combobox('getValue'));
        var pjmj=$.trim($("#applyFamilyHouses0afhRjmj").textbox('getValue'));
        var apRjysr=$.trim($("#apRjysr").textbox('getValue'));
        var apTsjt=$.trim($("input[name='apTsjt']:checked").val());
        $('#form').form('submit', {
            url: '<%=basePath %>applyns/updateNsInfo',
            onSubmit: function (param) {
                param.apBaseimg=paramSign;
                param.apTsjt=apTsjt;//二级残疾
                var isValid = $(this).form('validate');
                if (!isValid) {
                    flag=false;
                    isSave=true;
                    weixin.toast('请检查是否有漏填或错填内容！');
                }else if(!judgeFlag){
                    flag=false;
                    isSave=true;
                    weixin.toast("附件所有必填项不能为空！")
                }else{
                    if(jzxz=="4"&&Number(pjmj)>15){
                        flag=false;
                        isSave=true;
                        weixin.alert("自住房平均建筑面积大于15不能申请");
                    }else if(paramSign==""){
                        flag=false;
                        isSave=true;
                        weixin.toast("请签下本人姓名")
                    }else if(Number(apRjysr)>1549){
                        flag=false;
                        isSave=true;
                        weixin.alert("人均收入大于1549不能申请");
                    }else{
                        isSave=false;
                        flag=true;
                    }
                }
                return flag;
            },
            success: function (data) {
                var data = JSON.parse(data);
                if (data.result == "success") {
                   mask.show();
                     applyid = data.applyid;
                    currentIndex=0;
                    send(applyid);
                } else {
                    isSave=true;
                    weixin.toast(data.result)
                }
            },
            error: function (data) {
                isSave=true;
                weixin.alert("数据库连接失败，请稍后再试");
            }
        }, 'json');
    }
    //根据id查看详情
    mui('.mui-card').on('tap', '.detail', function() {
        var id=this.getAttribute("id");
        var type=Number(id.substring(4));
        mui('#picture').popover('toggle');
        weixin.typeDetail(type);
    });
    //查看签字图片
    mui(document.body).on('tap','.signBtn',function(){
        var id=this.getAttribute("id");
        if(id=="changeSign"){
            mui('#signCard').popover('show');
            if(signFlag){
                $("#signPicture").html('');
            }else{
                $("#signPicture").html('<img src='+initSign+'>');
            }

        }
        if(id=="clear"){
            $sigdiv.jSignature('reset');
            paramSign="";
        }
        if(id=="confirmSign"){
            signFlag=true;
            var data=$sigdiv.jSignature('getData', 'svgbase64');//'image/svg+xml;base64';
            paramSign = 'data:' + data[0] + ',' + data[1];
            var compareSign = 'PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMCIgaGVpZ2h0PSIwIj48L3N2Zz4=';
            if(compareSign!=data[1]){
                mui('#signCard').popover('toggle');
            } else {
                weixin.alert("请输入本人姓名");
                return false;
            }
        }

    });
    //返回上一页
    mui(".mui-bar").on('tap','#back',function () {
        mui.back();
    });
    mui(document.body).on('tap', '.mui-btn', function (e) {
        var id = this.getAttribute("id");
        var code = id.substring(4);
        mui("#pullrefresh").scroll().scrollTo(0, 0, 0);
        if (id.indexOf("next") != -1) {
            $('#wrap' + code + '').show().siblings().hide();
            uploader.refresh();
        }
        if (id.indexOf("prev") != -1) {
            $('#wrap' + code + '').show().siblings().hide();
            uploader.refresh();
        }
        if (id == "tijiao") {
            save()
        }
    });
    mui(".mui-bar").on('tap', '.mui-tab-item', function (e) {
        var target = this.getAttribute("id");
        switch (target) {
            case 'apply':
                weixin.openWindow('applyList', '<%=basePath %>appPath/applyList');
                break;
            case 'audit':
                weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
                break;
            case 'userInfo':
                weixin.openWindow('userInfo', '<%=basePath %>appPath/userInfo');
                break;
            case 'updatePwd':
                weixin.openWindow('updatePwd', '<%=basePath %>appPath/updatePwd');
                break;
            default:
                break;
        }
    });
    mui(".mui-table-view").on('tap', '.leftList', function (e) {
        var target = this.getAttribute("id");
        switch (target) {
            case 'applyAside':
                weixin.openWindow('applyList', '<%=basePath %>appPath/applyList');
                break;
            case 'audioAside':
                weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
                break;
            case 'userAside':
                weixin.openWindow('userInfo', '<%=basePath %>appPath/userInfo');
                break;
            case 'updateAside':
                weixin.openWindow('updatePwd', '<%=basePath %>appPath/updatePwd');
                break;
            case 'exitAside':
                window.location.href = "<%=basePath %>appLogin/appExit";
                break;
            default:
                break;
        }
    });
    //新增家庭成员
    mui(".mui-card").on('tap', '.greenPan', function (e) {
        var addIndex = $(".card-container .mui-card").length + 2;
        if (person) {
            i++;
            $("#apJtrk").textbox('setValue', i);
            var perString = $('<div class="mui-card">' +
                '<div class="mui-card-header">同住家庭成员情况</div>' +
                '    <div class="mui-card-content">' +
                '       <div class="mui-input-group">' +
                '            <div class="commonRow">' +
                '                <select class="easyui-combobox" name="applyFamilyMembers[' + addIndex + '].afmYsqrgx"  data-options="label:\'关系:\',valueField:\'piItemname\',textField:\'piItemname\',panelHeight:\'auto\',required:true,editable:false">' +
                '                   <option value="3">子女</option>' +
                '               </select>' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmXm" id="applyFamilyMembers' + addIndex+ 'afmXm" data-options="label:\'姓名\',required:true,validType:[\'empty\',\'chinese\',\'specialCharacter\']">' +
                '            </div>' +
                '            <div class="commonRow readonlyRow">' +
                '                 <input class="easyui-datebox" name="applyFamilyMembers[' + addIndex + '].afmCsny" id="applyFamilyMembers' + addIndex + 'afmCsny"  data-options="label:\'出生年月\',readonly:true,required:true,editable:false">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '               <input class="easyui-combobox" name="applyFamilyMembers[' + addIndex + '].afmHyzk" id="applyFamilyMembers' + addIndex + 'afmHyzk"  data-options="label:\'婚姻状况\',valueField:\'piItemcode\',textField:\'piItemname\',url:\'<%=basePath %>ParmItem/getOptions?parmSetcode=07\',required:true,editable:false,panelHeight:\'auto\',panelMaxHeight:\'280\'">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                 <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmGzdw"  data-options="label:\'工作单位\',required:true,prompt:\'请输入单位全称\'">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                 <input class="easyui-textbox" name="applyFamilyMembers[' +addIndex + '].afmSfzh" id="applyFamilyMembers' + addIndex + 'afmSfzh"  data-options="label:\'身份证号码\',required:true,validType:[\'sfz\',\'empty\'],events:{keyup:setBirthday}">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmNsr" id="applyFamilyMembers' + addIndex + 'afmNsr"  value="0"  data-options="label:\'个人年收入(元)\',required:true,validType:[\'number\',\'empty\'],events:{keyup:changeSr}">' +
                '            </div>' +
                '       </div>' +
                '     </div>' +
                '</div>').appendTo(".card-container");
            $.parser.parse(perString);
            changeSr(i);
            changeMj(i);
        } else {
            weixin.alert("未婚状态下不能增加家庭成员！")
        }
    });
    //删除家庭成员
    mui(".mui-card").on('tap', '.redPan', function (e) {
        var len= $(".card-container .mui-card").length;
        if (len > 0) {
            i--;
        }
        $("#apJtrk").textbox('setValue', i);
        $(".card-container").find(".mui-card:last").remove();
        changeSr(i);
        changeMj(i);
    });

    //金额label切换
    function change() {
        $("#cq label").text("货币补偿金额(元)");
        $("#cqtime label").text("拆迁时间");
    }

    function changeSecond() {
        $("#cq label").text("转让金额(元)");
        $("#cqtime label").text("转让时间");
    }

    $(function () {
        $.ajax({
            url: '<%=basePath %>appove/getSqApproveDetail',
            type: 'post',
            dataType: 'json',
            data: {
                aptype: "${applyType}",
                aplid: "${applyId}"
            },
            success: function (data) {
                setPerson(data);//加载同住家庭成员信息
                setValue($('#form'), data);//处理回填问题
                setImage(data); //回填图片
                setSign(data);//回填签字；
            }, error: function () {
                weixin.alert("数据库连接失败，请稍后再试")
            }
        })
    });

    //回填家庭成员 本人为0 配偶为1
    function setPerson(data) {
        if (data.applyFamilyMembers.length > 2) {
            for (var i = 2; i < data.applyFamilyMembers.length; i++) {
                $('<div class="mui-card">' +
                    '<div class="mui-card-header">同住家庭成员情况</div>' +
                    '    <div class="mui-card-content">' +
                    '       <div class="mui-input-group">' +
                    '<div style="display: none">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmId" id="applyFamilyMembers' + i + 'afmId" >' +
                    '            </div>' +
                    '<div style="display: none">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmSqid" id="applyFamilyMembers' + i + 'afmSqid"  >' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<select class="easyui-combobox" name="applyFamilyMembers[' + i + '].afmYsqrgx" id="applyFamilyMembers' + i + 'afmYsqrgx"  data-options="label:\'关系\',valueField:\'piItemname\',textField:\'piItemname\',panelHeight:\'auto\',required:true,editable:false">' +
                    '<option value="3">子女</option>' +
                    '</select>' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmXm" id="applyFamilyMembers' + i + 'afmXm"  data-options="label:\'姓名\',required:true,validType:[\'empty\',\'chinese\',\'specialCharacter\']">' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<input class="easyui-datebox" name="applyFamilyMembers[' + i + '].afmCsny" id="applyFamilyMembers' + i + 'afmCsny"  data-options="label:\'出生年月\',readonly:true,required:true,editable:false">' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<input class="easyui-combobox" name="applyFamilyMembers[' + i + '].afmHyzk"  id="applyFamilyMembers' + i + 'afmHyzk"  data-options="label:\'婚姻状况\',valueField:\'piItemcode\',textField:\'piItemname\',url:\'<%=basePath %>ParmItem/getOptions?parmSetcode=07\',required:true,panelHeight:\'auto\',panelMaxHeight:\'280\',editable:false">' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmGzdw" id="applyFamilyMembers' + i + 'afmGzdw"  data-options="label:\'工作单位\',prompt:\'请输入单位全称\'">' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmSfzh" id="applyFamilyMembers' + i + 'afmSfzh"  data-options="label:\'身份证号码\',required:true,validType:[\'sfz\',\'empty\'],events:{keyup:setBirthday}">' +
                    '            </div>' +
                    '            <div class="commonRow">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmNsr" id="applyFamilyMembers' + i + 'afmNsr" data-options="label:\'个人年收入(元)\',required:true,validType:[\'number\',\'empty\'],events:{keyup:changeSr}">' +
                    '            </div>' +
                    '       </div>' +
                    '     </div>' +
                    '</div>').appendTo(".card-container");
                $.parser.parse($(".card-container"))
            }
        }
    }

    //回填数据
    function setValue(form, data) {
        for (var x in data) {
            if (typeof(data[x]) == "object") {
                $.each(form.serializeArray(), function (index) {
                    if (this['name'].indexOf(x) != -1) {//是否含有此字段
                        var nameSplit = this['name'].split('.');
                        if (nameSplit[0].indexOf("[") == -1) {
                            //普通对象
                            var idArray = [];
                            idArray.push(nameSplit[0], nameSplit[1]);
                            //根据不同类型赋值
                            var typeClass = $('#' + idArray[0] + idArray[1] + '').attr('class');
                            if (typeClass.indexOf('combobox') !== -1) {
                                $('#' + idArray[0] + idArray[1] + '').combobox('setValue', (data[x])[idArray[1]])
                            } else if (typeClass.indexOf('datebox') !== -1) {
                                $('#' + idArray[0] + idArray[1] + '').datebox('setValue', (data[x])[idArray[1]])
                            } else {
                                $('#' + idArray[0] + idArray[1] + '').textbox('setValue', (data[x])[idArray[1]])
                            }
                        } else {
                            //数组对象
                            var idArray = [];
                            var index = nameSplit[0].indexOf("[");
                            var field = nameSplit[0].substring(0, index);
                            var indexCode = nameSplit[0].substring(index + 1, (nameSplit[0].length - 1));
                            idArray.push(field, indexCode, nameSplit[1]);
                            //根据不同类型赋值
                            var typeClass = $('#' + idArray[0] + idArray[1] + idArray[2] + '').attr('class');
                            if(typeClass!=undefined){
                                if((data[x])[indexCode]!=undefined){
                                    if(typeClass.indexOf('easyui-combobox')!=-1){
                                        $('#'+idArray[0]+idArray[1]+idArray[2]+'').combobox('setValue',(data[x])[indexCode][idArray[2]])
                                    }else if(typeClass.indexOf('easyui-datebox')!=-1){
                                        $('#'+idArray[0]+idArray[1]+idArray[2]+'').datebox('setValue',(data[x])[indexCode][idArray[2]])
                                    }else{
                                        $('#'+idArray[0]+idArray[1]+idArray[2]+'').textbox('setValue',(data[x])[indexCode][idArray[2]])
                                    }
                                }
                            }
                        }

                    }
                });
            } else {
                $("#form").form('load', data);//直接赋值
            }
        }
        if ($.trim(data.applyFamilyHouseChange.afhcCqr).length > 0) {
            $("input:radio[value='" + data.applyFamilyHouseChange.afhcBgfs + "']").prop('checked', true);
            $("input:radio[value='" + data.applyFamilyHouseChange.afhcBgfs + "']").siblings('input').prop('disabled', true);
            if (data.applyFamilyHouseChange.afhcBgfs == "拆迁") {
                $("#cq label").text("拆迁金额(元)");
                $("#cqaz").show();
            } else {
                $("#cqaz").hide();
                $("#cq label").text("转让金额(元)");
            }
        }
    }

    //图片回显
    function setImage(data) {
        if (data.volList.length > 0) {
            var volList = data.volList;
            for (var i = 0; i < volList.length; i++) {
                for (var k = 0; k < volList[i].volelearcDtls.length; k++) {
                    var imageUrl =(volList[i].volelearcDtls)[k].imgname;
                    var index = $($("#wrap4 .mui-card:contains('" + volList[i].elearcname + "')")).index();
                    initArray.push(index);
                    $("#wrap4 .mui-card:contains('" + volList[i].elearcname + "')").find(".uploader-list").append('<div id="' + (volList[i].volelearcDtls)[k].volelearcdtlId + '" class="file-item thumbnail id">' +
                        '<img src="' + imageUrl + '" >' +
                        '<div class="info" onclick="delList($(this))">' + "删除" + '</div>' +
                        '</div>');
                }
            }
        }
    }
    //回填签名
    function setSign(data){
        if(data.apBaseimg!=undefined){
            initSign=data.apBaseimg;
        }else{
            initSign='PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMCIgaGVpZ2h0PSIwIj48L3N2Zz4='
        }
    }
    //图片删除
    function delList(obj) {
        var id = obj.parents().attr("id");
        var code = obj.parent().parent().attr("id").substring(8);
        $.ajax({
            url: '<%=basePath %>volel/deleteDetail',
            type: 'post',
            dataType: 'json',
            data: {
                id: id
            },
            success: function (data) {
                obj.parents(".thumbnail").remove();
                var index = weixin.getArrayIndex(initArray, Number(code));
                initArray.splice(index, 1);
            }, error: function () {
                weixin.alert("数据库连接失败，请稍后再试")
            }
        })
    }

    //街区联动
    function selectSSQ(record) {
        $("#apJdbsc").combobox({
            url: '<%=basePath%>ParmItem/findAllJd',
            onBeforeLoad: function (param) {
                param.qid = record.piItemcode;
            }
        });
    }

    //计算收入和面积
    function changeMj(obj) {
        var allMj;
        if (typeof(obj) == "number") {
            allMj = $("#applyFamilyHouses0afhMj").textbox("getValue");
        } else {
            allMj = $(this).val();
        }
        var rjmj = Number(Number(allMj) / Number(i)).toFixed(2);
        $("#applyFamilyHouses0afhRjmj").textbox('setValue', rjmj);
    }

    function changeSr(obj) {
        var eachIndex= $(".card-container .mui-card").length+2;
        var sum = 0, average = 0, yuan = 0;
        for (var j = 0; j < eachIndex; j++) {
            if ($("#applyFamilyMembers" + j + "afmNsr").val() != undefined) {
                yuan = $("#applyFamilyMembers" + j + "afmNsr").textbox('getValue')
            } else {
                yuan = 0;
            }
            sum += Number(yuan);
        }
        if (typeof(obj) == "object") {
            var id = $(this).parent().siblings('input').attr("id");
            var oldValue = $('#' + id + '').textbox('getValue');
            sum = sum - Number(oldValue) + Number($(this).val());
        }
        average = Number(Number(sum) / (i) / 12).toFixed(2);
        //计算年收入，平均收入
        $("#apJtnsr").textbox('setValue', sum);
        $("#apRjysr").textbox('setValue', average);
    }

    //根据婚姻状况控制配偶信息
    function selecthyzk(record){
        var ulLen=$(".card-container .mui-card").length;//判断是否有家庭成员
        //已婚
        if(record.piItemcode=="2"){
            i=2+Number(ulLen);
            person=true;
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('reload');
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:true,readonly:false});
        }else if(record.piItemcode=="1"){//未婚
            i=1;
            person=false;
            $(".card-container").html("");
            $.parser.parse($(".card-container"));
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,readonly:true});
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('clear');
        }else{
            i=Number(ulLen)+1;
            person=true;
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,readonly:true});
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('clear');
        }
        $("#apJtrk").textbox('setValue',i);
        changeMj(i);
        changeSr(i);
    }
    //回填出生日期
    function setBirthday(){
        var name=$(this).siblings().attr('name');
        var id=name.substring(name.indexOf('[')+1,name.indexOf(']'));
        if(name.indexOf('afmSfzh')>0){
            var  sfzh=$(this).val();
        }
        if(sfzh.length==0){
            $("#applyFamilyMembers"+id+"afmCsny").textbox('setValue','');
            return false
        }else{
            var birthday= weixin.setBirthday(sfzh);
            $("#applyFamilyMembers"+id+"afmCsny").textbox('setValue',birthday);
        }
    }
</script>
</body>
</html>

