<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>经济适用住房申请</title>
    <link rel="stylesheet" href="<%=basePath %>thiemesApply/gray/easyui.css"/>
    <link rel="stylesheet" href="<%=basePath %>srcApply/css/webuploader.css"/>
    <link rel="stylesheet" href="<%=basePath %>srcApply/css/common.css"/>
    <link rel="stylesheet" href="<%=basePath %>srcApply/css/viewer.min.css" />
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/webuploader.js"></script>
    <!--[if lte IE 9]>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/ie.css">
    <script type="text/javascript" src="<%=basePath %>src/js/ie/html5shiv.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/ie/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/viewer-jquery.min.js"></script>
</head>

<body class="easyui-layout">
<div class="over" style="display: none;">
    <div id="loading"></div>
</div>
<div data-options="region:'center'" id="center">
    <span class="top-title">经济适用住房申请</span>
    <div id="top">
        <form method="post" id="form" class="easyui-form" enctype="multipart/form-data">
            <div style="display: none">
                <input class="easyui-textbox" name="apId" id="apId">
            </div>
            <ul class="input-group">
                <li>
                    <input class="easyui-combobox" name="apSsq" style="width: 92%" data-options="label:'户籍所在区（县）:',required:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/selectSsqExceptCenter',
                    onSelect:selectSSQ,panelHeight:'auto',panelMaxHeight:'280',editable:false">
                </li>
                <li>
                    <input class="easyui-combobox" name="apJdbsc" id="apJdbsc" style="width: 92%" data-options="label:'户籍所在街道办事处:',required:true,valueField:'piItemcode',textField:'piItemname',panelHeight:'auto',panelMaxHeight:'280',editable:false">
                </li>
                <li>
                    <input class="easyui-textbox" name="apSqjwh" style="width: 92%" data-options="label:'户籍所在社区:',required:true,validType:['chinese','empty']">
                </li>
            </ul>
            <!--进度条-->
            <div id="procsss">
                <div class="left">
                    <img src="<%=basePath %>srcApply/img/applicationForm/left.png"/>
                </div>
                <div class="right">
                    <img src="<%=basePath %>srcApply/img/applicationForm/right.png"/>
                </div>
                <ul class="step">
                    <li>
                        <div class="step-img-green">
                            <span>1</span>
                        </div>
                        <p>基本信息</p>
                    </li>
                    <li>
                        <div class="step-img">
                            <span>2</span>
                        </div>
                        <p class="blue">家庭住房情况</p>
                    </li>
                    <li>
                        <div class="step-img">
                            <span>3</span>
                        </div>
                        <p class="blue">同住家庭成员情况</p>
                    </li>
                    <li>
                        <div class="step-img">
                            <span>4</span>
                        </div>
                        <p class="blue">附件信息</p>
                    </li>
                </ul>
                <div class="process">
                    <div class="imgparent">
                        <img class="imgpro" src="<%=basePath %>src/img/audit/proces.png" />
                        <img class="imgpro" src="<%=basePath %>src/img/audit/proces.png" style="display: none"/>
                        <img class="imgpro" src="<%=basePath %>src/img/audit/proces.png" style="display: none"/>
                        <img class="imgpro" src="<%=basePath %>src/img/audit/proces.png" style="display: none"/>
                    </div>
                </div>
            </div>
            <!--填写内容区-->
            <div id="inner">
                <div>
                    <ul class="input-group windowInner">
                        <legend>申请人信息</legend>
                        <li style="display: none">
                            <input class="easyui-textbox" name="apSqrid" id="apSqrid" style="width: 92%" data-options="label:'abSqrid'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmId" id="applyFamilyMembers0afmId" style="width: 92%" data-options="label:'afmId'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmSqid" id="applyFamilyMembers0afmSqid" style="width: 92%" data-options="label:'afmSqid'">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmXm" id="applyFamilyMembers0afmXm" style="width: 92%" data-options="label:'姓名:',required:true,readonly:true,validType:['empty','chinese','specialCharacter']">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmSfzh" id="applyFamilyMembers0afmSfzh" style="width: 92%" data-options="label:'身份证号码:',required:true,validType:['sfz','empty'],readonly:true">
                        </li>
                        <li class="readonly">
                            <select class="easyui-combobox" name="applyFamilyMembers[0].afmXb" id="applyFamilyMembers0afmXb" style="width: 92%" data-options="label:'性别:',required:true,readonly:true,editable:false,panelHeight:'auto'">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </li>
                        <li >
                            <input class="easyui-combobox" name="applyFamilyMembers[0].afmHyzk" id="applyFamilyMembers0afmHyzk" style="width: 92%" data-options="label:'婚姻状况:',required:true,valueField:'piItemcode',textField:'piItemname',
                            url:'<%=basePath %>ParmItem/getOptions?parmSetcode=07',onSelect:selecthyzk,editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmGzdw" id="applyFamilyMembers0afmGzdw" style="width: 92%" data-options="label:'工作单位:',prompt:'请输入单位全称'">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmLxdh" id="applyFamilyMembers0afmLxdh" style="width: 92%" data-options="label:'联系电话:',required:true,validType:'phone',readonly:true">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmNsr"  id="applyFamilyMembers0afmNsr" style="width: 92%" data-options="label:'个人年收入(元):',required:true,validType:['empty','number'],events:{keyup:changeSr}">
                        </li>
                    </ul>
                    <ul class="input-group windowInner">
                        <legend>配偶信息</legend>
                        <li style="display: none">
                            <input class="easyui-textbox" name="apSqrpoid" id="apSqrpoid" style="width: 92%" data-options="label:'abSqrpoid'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmId" id="applyFamilyMembers1afmId" style="width: 92%" data-options="label:'afmId'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmSqid" id="applyFamilyMembers1afmSqid" style="width: 92%" data-options="label:'afmSqid'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmXm" id="applyFamilyMembers1afmXm" style="width: 92%" data-options="label:'姓名:',validType:['empty','chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmSfzh" id="applyFamilyMembers1afmSfzh" style="width: 92%" data-options="label:'身份证号码:',validType:['sfz','empty']">
                        </li>
                        <li>
                            <select class="easyui-combobox" name="applyFamilyMembers[1].afmXb" id="applyFamilyMembers1afmXb" style="width: 92%" data-options="label:'性别:',editable:false,panelHeight:'auto'">
                                <option value="女">女</option>
                                <option value="男">男</option>
                            </select>
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmGzdw" id="applyFamilyMembers1afmGzdw" style="width: 92%" data-options="label:'工作单位:',prompt:'请输入单位全称'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmLxdh" id="applyFamilyMembers1afmLxdh" style="width: 92%" data-options="label:'联系电话:',validType:['phone','empty']">
                        </li>
                        <li>
                            <select class="easyui-combobox" name="applyFamilyMembers[1].afmYsqrgx" id="applyFamilyMembers1afmYsqrgx" style="width: 92%" data-options="label:'关系:',valueField:'piItemname',textField:'piItemname',panelHeight:'auto',editable:false">
                                <option value="0">配偶</option>
                            </select>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyMembers[1].afmNsr" id="applyFamilyMembers1afmNsr" style="width: 92%" data-options="label:'个人年收入(元):',validType:['empty','number'],events:{keyup:changeSr}">
                        </li>
                    </ul>
                    <ul class="input-group windowInner">
                        <legend>户籍信息</legend>
                        <li>
                            <input class="easyui-textbox" name="apSqhjnx" style="width: 92%" data-options="label:'户籍年限(年):',required:true,validType:['numberZ','limitHJ[5]','empty']">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="apJtnsr" id="apJtnsr" style="width: 92%" data-options="label:'家庭年收入(元):',required:true,readonly:true,validType:['number','empty']">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="apJtrk" id="apJtrk" style="width: 92%" data-options="label:'家庭人口:',required:true,readonly:true,validType:['numberZ','empty']">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="apRjysr" id="apRjysr"  style="width: 92%" data-options="label:'人均月收入(元):',required:true,readonly:true,validType:['number','empty']">
                        </li>
                    </ul>
                    <ul class="input-group windowInner">
                        <legend>工作单位信息</legend>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyUnit.aplid" id="applyUnitaplid" style="width: 92%" data-options="label:'afhId:',prompt:'非必填'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyUnit.unitid" id="applyUnitunitid" style="width: 92%" data-options="label:'afhSqid:',prompt:'非必填'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.legelrep" id="applyUnitlegelrep" style="width:92%" data-options="label:'法定代表人:',prompt:'非必填',validType:['chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.bsls" id="applyUnitbsls" style="width: 92%" data-options="label:'统一社会信用代码:',prompt:'非必填',validType:'xyCode'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.entag" id="applyUnitentag" style="width:92%" data-options="label:'委托代理人:',prompt:'非必填',validType:['chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.tel" id="applyUnittel" style="width:92%" data-options="label:'联系方式:',validType:'phoneTel',prompt:'非必填'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.address" id="applyUnitaddress" style="width:92%" data-options="label:'联系住址:',prompt:'非必填'">
                        </li>
                    </ul>
                    <p class="sbumit right">
                        <span><a href="#">下一步</a></span>
                    </p>
                </div>
                <div style="display: none;">
                    <ul class="input-group windowInner">
                        <legend>家庭现住房情况</legend>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhId" id="applyFamilyHouses0afhId" style="width: 92%" data-options="label:'afhId:'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhSqid" id="applyFamilyHouses0afhSqid" style="width: 92%" data-options="label:'afhSqid:'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhZl" id="applyFamilyHouses0afhZl" style="width: 92%" data-options="label:'房屋地址:',required:true,validType:['empty']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhCqr" id="applyFamilyHouses0afhCqr" style="width: 92%" data-options="label:'产权人:',required:true,validType:['empty','chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhMj" id="applyFamilyHouses0afhMj" style="width: 92%" data-options="label:'建筑面积(m²):',required:true,validType:['number','empty'],events:{keyup:changeMj}">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhRjmj" id="applyFamilyHouses0afhRjmj" style="width: 92%" data-options="label:'人均建筑面积(m²):',readonly:true,validType:['number','empty']">
                        </li>
                        <li>
                            <input class="easyui-combobox" name="applyFamilyHouses[0].afhZfxz" id="applyFamilyHouses0afhZfxz" style="width: 92%" data-options="label:'现住房性质:',required:true,valueField:'piItemcode',textField:'piItemname',
                            url:'<%=basePath %>ParmItem/getOptions?parmSetcode=01',panelHeight:'auto',panelMaxHeight:'280',editable:false">
                        </li>
                    </ul>
                    <ul class="input-group windowInner">
                        <legend>住房变更情况</legend>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyHouseChange.afhcYzfzl" id="applyFamilyHouseChangeafhcYzfzl" style="width: 92%" data-options="label:'原住房地址:'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyHouseChange.afhcCqr" id="applyFamilyHouseChangeafhcCqr" style="width: 92%" data-options="label:'原产权人:',validType:['chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyFamilyHouseChange.afhcJzmj"  id="applyFamilyHouseChangeafhcJzmj" style="width: 92%" data-options="label:'建筑面积(m²):',validType:'number'">
                        </li>
                        <li>
                            <input type="radio" name="applyFamilyHouseChange.afhcBgfs" value="拆迁" onclick="change()" required/>拆迁&nbsp;&nbsp;
                            <input type="radio" name="applyFamilyHouseChange.afhcBgfs" value="转让" onclick="changeSecond()"/>转让 (时间)
                            <input class="easyui-datebox" name="applyFamilyHouseChange.afhcBgsj" id="applyFamilyHouseChangeafhcBgsj"  style="width: 58%" data-options="editable:false">
                        </li>
                        <li id="cq">
                            <input class="easyui-textbox" name="applyFamilyHouseChange.afhcZrbcje" id="applyFamilyHouseChangeafhcZrbcje" style="width: 92%" data-options="label:'货币补偿金额(元):',validType:'number'">
                        </li>
                    </ul>
                    <p class="sbumit right">
                        <span><a href="#">下一步</a></span>
                    </p>
                </div>
                <div style="display: none;">
                    <p id="addPerson"></p>
                    <p class="sbumit right">
                        <span><a href="#">下一步</a></span>
                    </p>
                </div>
                <div style="display: none;">
                    <table id="table" border="1" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
                        <tr style="background: #66a0d3">
                            <th style="width: 80px;">序号</th>
                            <th style="width: 500px;">附件名称</th>
                            <th style="width: 600px;">选择附件</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td class="tdname"><span class="name">收入情况证明材料</span><span class="red">*(必填)</span><span class="aspan" onclick="view(0)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList0" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td class="tdname"><span class="name">住房状况证明材料</span><span class="red">*(必填)</span><span class="aspan" onclick="view(1)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList1" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td class="tdname"><span class="name">家庭成员身份证</span><span class="red">*(必填)</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList2" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td class="tdname"><span class="name">家庭成员户口簿(含首页)</span><span class="red">*(必填)</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList3" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td class="tdname"><span class="name">合法有效家庭成员之间的赡养、抚养、扶养关系及共同生活的证明</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList4" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td class="tdname"><span class="name">反映家庭财产状况的有关证明材料</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList5" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td class="tdname"><span class="name">申请人申报财产真实性书面承诺</span><span class="red">*(必填)</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList6" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td class="tdname"><span class="name">婚姻情况证明</span><span class="red">*(必填)</span><span class="aspan" onclick="view(2)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList7" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td class="tdname"><span class="name">户籍所在地社区公示证明</span><span class="red">*(必填)</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList8" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td class="tdname"><span class="name">其他相关材料</span></td>
                            <td class="addJpg">
                                <div>
                                    <!--用来存放item-->
                                    <div id="fileList9" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                    </table>
                  <%--  <div class="sbumit" onclick="save()">
                        <span><a href="#">提交</a></span>
                    </div>--%>
                </div>
            </div>
        </form>
    </div>
    <div class="imgOperate">
        <div style="width: 100%" class="optColumn">
            <span  class="leftRotate"></span>
            <span  class="rightRotate"></span>
            <span  class="expand"></span>
            <span  class="shrink"></span>
            <span  class="close"></span>
        </div>
        <div class="optImg">
            <img src="" alt="" >
        </div>
    </div>
</div>
</body>

</html>
<style>
    .textbox-label {
        width: 130px;
    }
</style>
<script>
    var person=false;
    var i=1;//设置人口情况未1；
    var j=0,sucIndex=0,currentIndex=0,applyid="",num = "",codeArray=[],nameArray=[],initArray=[];
    var changeImg='';
    $('.filePicker').each(function (index) {
        nameArray[index] = $(this).parent().parent().siblings().find('.name').text();
    });
    uploader = WebUploader.create({
        auto: false,//不自动上传
        swf: '<%=basePath %>srcApply/js/Uploader.swf',
        server: '<%=basePath %>volel/uoloadVolel',
        pick:{
            id:'.filePicker',
            innerHTML:'添加附件'
        } ,
        fileVal: "volname1",
        threads:1,//并发最大数量限制
        fileNumLimit: 100,//限制最大张数100
        disableGlobalDnd: true, //禁用浏览器的拖拽功能，否则图片会被浏览器打开
        fileSizeLimit: 1000 * 1024 * 1024, // 总共的最大限制1000M
        fileSingleSizeLimit: 6* 1024 * 1024, // 单文件的最大限制4M
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/png,image/gif,image/bmp,image/jpeg'
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
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            codeArray.pop();
            codeArray.pop();
            codeArray.pop();
            uploader.refresh();
            bank.alertMessage("请上传JPG、PNG、GIF、BMP格式图片文件");
        }else if (type == "F_DUPLICATE") {
            bank.alertMessage("请不要重复选择文件！");
        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
            bank.alertMessage("单文件大小不超过4M，请调整");
        }
    });
    uploader.on('fileQueued', function (file) {
        var pickId=file.source.ruid;
        var pickClass=$("#"+pickId+"").parent().parent().parent().find(".filePicker").attr("class");
        if(pickClass!=undefined){
            j=pickClass.substring(8,9);
            codeArray.push(j);
            codeArray.push(j);
            codeArray.push(j);
        }
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img >' +
            '</div>'
            ),
            $img = $li.find('img');
        $("#fileList" + j + "").append($li);
        uploader.makeThumb(file, function (error, src) {
            if(error) {
                $("#fileList" + j + "").find(".thumbnail:last").remove();
                codeArray.pop();
                codeArray.pop();
                codeArray.pop();
                uploader.reset();
                weixin.alert('请选择正确图片格式');
            }else{
                $img.attr('src', src);
            }
        }, 15, 15);
    });
    function del(obj) {
        //移除一个j
        var code=Number(obj.parent().parent().attr('id').substring(8));
        var index=bank.getArrayIndex(codeArray,code);
        codeArray.splice(index,1);
        codeArray.splice((index-1),1);
        codeArray.splice((index-2),1);
        obj.parents(".thumbnail").remove();
        uploader.reset();
    }
    function add(index){
        j=index;
        codeArray.push(index);//执行3次
    }
    function send(applyid){
        //上传后才执行
        var newArray=solveArray(codeArray);
        //未修改附件
        if(newArray.length==0){
            bank.ajaxMessage('上传成功');
            setTimeout(function () {
                $(".over").hide();
                if (window.opener && !window.opener.closed) {
                    window.parent.opener.location.reload();
                }else{
                    parent.location.reload();
                }
                window.close(); return false;
            },1000);
        }else{
            uploader.on('uploadBeforeSend', function (obj, data, headers){
                var code=newArray[currentIndex];
                data.volname=nameArray[code];
                data.fileVal="volname"+(Number(1+Number(code)));
                data.applyid=applyid;
                currentIndex++;
            });
            uploader.upload();
            //上传后才执行
            uploader.on( 'uploadError', function(file) {
                if(currentIndex==newArray.length){
                    bank.ajaxMessage('上传失败');
                    $(".over").hide();
                }
            });
            //上传后才执行
            uploader.on( 'uploadSuccess', function(file,resopnse) {
                sucIndex++;
                if(sucIndex==newArray.length){
                    bank.ajaxMessage('上传成功');
                    setTimeout(function () {
                        $(".over").hide();
                        if (window.opener && !window.opener.closed) {
                            window.parent.opener.location.reload();
                        }else{
                            parent.location.reload();
                        }
                        window.close(); return false;
                    },2500);
                }
            });
        }
    }
    //处理数组,排序
    function solveArray(codeArray){
        var newArray=[];
        for(var i=0;i<codeArray.length;i++){
            if(i%3==0){
                newArray.push(codeArray[i])
            }
        }
        return newArray.sort();
    }
    function judgeArray(newArray,initArray){
        var judgeFlag=true;
        var bitianArray=[0,1,2,3,6,7,8];
        var endArray=newArray.concat(initArray);
        var temp=bank.arraySplice(endArray);
        if(temp.length>0){
            for(var x=0;x<bitianArray.length;x++){
                if(typeof(bank.getArrayIndex(temp,bitianArray[x]))=="boolean"){
                    judgeFlag=false;
                    return false;
                }
            }
        }else{
            judgeFlag=false;
        }
        return judgeFlag;
    }

    //提交
    function save() {
        var isValid = $('#form').form('validate');
        if(!isValid){
            bank.ajaxMessage('请检查是否有漏填或错填内容！');
            return false
        }else{
            checkUser()
        }

    }
    //验证身份证信息是否合法
    function checkUser(){
        var userArray=[],sfzhArray=[];//所有身份证数组
        var checkIndex=$("#addPerson ul").length+2;
        for(var k=1;k<checkIndex;k++){
            var user=$.trim($("#applyFamilyMembers"+k+"afmXm").textbox('getValue'));
            if(user.length>0){
                userArray.push($("#applyFamilyMembers"+k+"afmXm").textbox('getValue'));
                sfzhArray.push($("#applyFamilyMembers"+k+"afmSfzh").textbox('getValue'))
            }
        }
        if(userArray.length>0){
            $.ajax({
                url:'<%=basePath %>applyUserinfo/checkUsers',
                type:'post',
                async:true,//同步
                data:{
                    usernames:userArray.toString(),
                    sfzhs:sfzhArray.toString()
                },
                success:function(result){
                    if(result=="success"){
                        secondSave();
                    }else{
                        bank.ajaxMessage(result+"身份证和姓名不匹配");
                        return false
                    }
                },error:function () {
                    bank.alertMessage("数据库连接失败，请稍后再试");
                    return false
                }
            });
        }else{
            secondSave()
        }
    }
    function secondSave() {
        //获取签字
        var sign =bank.biography().getParams('sign');
        var judgeFlag=judgeArray(solveArray(codeArray),initArray);
        var flag=false;
        var jzxz=$.trim($("#applyFamilyHouses0afhZfxz").combobox('getValue'));
        var pjmj=$.trim($("#applyFamilyHouses0afhRjmj").textbox('getValue'));
        var apRjysr=$.trim($("#apRjysr").textbox('getValue'));
       $('#form').form('submit', {
           url: '<%=basePath %>apply/updateApply',
            onSubmit: function (param) {
                param.apBaseimg = sign.row;
                param.apSqlb = ${applyType};
                var isValid = $(this).form('validate');
                if (!isValid) {
                    flag=false;
                    bank.ajaxMessage('请检查是否有漏填或错填内容！');
                }else if(!judgeFlag){
                    flag=false;
                    bank.alertMessage("附件所有必填项不能为空！")
                }else{
                    if(jzxz=="4"&&Number(pjmj)>15){
                        flag=false;
                        bank.alertMessage("自住房平均建筑面积大于15不能申请");
                    }else if(Number(apRjysr)>2065){
                        flag=false;
                        bank.alertMessage("人均收入大于2065不能申请");
                    }else{
                        flag=true;
                    }
                }
                return flag;
            },
            success: function (data) {
                var data = JSON.parse(data);
                if (data.result == "success") {
                    $(".over").show();
                     applyid = data.applyid;
                    currentIndex=0;
                    send(applyid);
                } else {
                    bank.ajaxMessage(data.result)
                }
            },
            error: function (data) {
                bank.alertMessage("数据库连接失败，请稍后再试");
            }
        }, 'json');
    }
    //tab切换
    $(".step li").on("click", function (e) {
        e.preventDefault();
        num = $(this).index();
        changeDiv(num);
        uploader.refresh();//10
    });
    $(".left").on("click",function(){
        num--;
        if(num<= 0){
            num = 0;
        }
        changeDiv(num);
        uploader.refresh();//10
    });
    $(".right").on("click",function(){
        num++;
        if(num>= 3){
            num = 3;
        }
        changeDiv(num);
        uploader.refresh();//10
    });
    function changeDiv (num) {
        $(".step li").eq(num).children("div").removeClass("step-img").addClass("step-img-green");
        $(".step li").eq(num).siblings().children("div").addClass("step-img").removeClass("step-img-green");
        $(".step li").eq(num).children("p").removeClass("blue");
        $(".step li").eq(num).siblings().children("p").addClass("blue");
        $(".process .imgparent img:lt("+(num+1)+")").show();
        $(".process .imgparent img:gt("+(num)+")").hide();
        $("#inner div").eq(num).show().siblings().hide();
        //图片预览插件
        $('#table').viewer({
            url: 'src'
        });
    }

    //转换金额
    function change(){
       $("#cq label").text("货币补偿金额(元):");
    }
    function changeSecond(){
        $("#cq label").text("转让金额(元):");

    }
    //街区联动
    function selectSSQ(record){
        $("#apJdbsc").combobox({
            url:'<%=basePath%>ParmItem/findAllJd',
            onBeforeLoad:function(param){
                param.qid=record.piItemcode;
            }
        });
    }
    //计算收入和面积
    function changeMj(obj){
        var allMj;
        if(typeof(obj)=="number"){
            allMj=$("#applyFamilyHouses0afhMj").textbox("getValue");
        }else{
            allMj=$(this).val();
        }
        var rjmj=Number(Number(allMj)/Number(i)).toFixed(2);
        $("#applyFamilyHouses0afhRjmj").textbox('setValue',rjmj);
    }
    function changeSr(obj){
        var eachIndex=$("#addPerson ul").length+2;
        var sum=0,average=0,yuan=0;
        for(var j=0;j<eachIndex;j++){
            if($("#applyFamilyMembers"+j+"afmNsr").val()!=undefined){
                yuan= $("#applyFamilyMembers"+j+"afmNsr").textbox('getValue')
            }else{
                yuan=0;
            }
            sum+=Number(yuan);
        }
        if(typeof(obj)=="object"){
            var id=$(this).parent().siblings('input').attr("id");
            var oldValue=$('#'+id+'').textbox('getValue');
            sum=sum-Number(oldValue)+Number($(this).val());
        }
        average=Number(Number(sum)/(i)/12).toFixed(2);
        //计算年收入，平均收入
        $("#apJtnsr").textbox('setValue',sum);
        $("#apRjysr").textbox('setValue',average);
    }
    //验证身份是否已婚
    function selecthyzk(record){
        var ulLen=$("#addPerson ul").length;//判断是否有家庭成员
        if(record.piItemcode=="2"){//已婚
            i=2+Number(ulLen);
            person=true;
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('reload');
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:true,disabled:false});
        }else if(record.piItemcode=="1"){//未婚
            i=1;
            person=false;
            $("#addPerson").html("");
            $.parser.parse($("#addPerson"));
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,disabled:true});
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('clear');
        }else{
            i=Number(ulLen)+1;
            person=true;
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,disabled:true});
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
            var birthday= bank.setBirthday(sfzh);
            $("#applyFamilyMembers"+id+"afmCsny").textbox('setValue',birthday);
        }
    }
    /********************************************************************************************************************************************************************************************/
    //数据回显
    $(function () {
        $.ajax({
            url:'<%=basePath %>appove/getSqApproveDetail',
            type:'post',
            dataType:'json',
            data:{
                aptype:"${applyType}",
                aplid:"${applyId}"
            },
            success:function(data){
                setPerson(data);//加载同住家庭成员信息
                setValue($('#form'),data);//处理回填问题
                setImage(data); //回填图片
            },error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试")
            }
        })
    });
    //回填家庭成员 i = 2,本人为0 配偶为1
    function setPerson(data) {
        if(data.applyFamilyMembers.length>2){
            for(var i = 2; i< data.applyFamilyMembers.length;i++){
                $('<ul class="input-group windowInner">' +
                    '<legend>同住家庭成员情况</legend>' +
                    '<li style="display: none">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmId" id="applyFamilyMembers'+i+'afmId" style="width: 92%">' +
                    '</li>' +
                    '<li style="display: none">' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmSqid" id="applyFamilyMembers'+i+'afmSqid"  style="width: 92%">' +
                    '</li>' +
                    '<li>' +
                    '<select class="easyui-combobox" name="applyFamilyMembers['+i+'].afmYsqrgx" id="applyFamilyMembers'+i+'afmYsqrgx"  style="width: 92%" data-options="label:\'关系:\',valueField:\'piItemname\',textField:\'piItemname\',panelHeight:\'auto\',required:true,editable:false">' +
                    '<option value="3">子女</option>'+
                    '</select>'+
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmXm" id="applyFamilyMembers'+i+'afmXm"  style="width: 92%" data-options="label:\'姓名:\',required:true,validType:[\'empty\',\'chinese\',\'specialCharacter\']">' +
                    '</li>' +
                    '<li class="readonly">' +
                    '<input class="easyui-datebox" name="applyFamilyMembers['+i+'].afmCsny" id="applyFamilyMembers'+i+'afmCsny" style="width: 92%" data-options="label:\'出生年月:\',readonly:true,required:true,editable:false">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-combobox" name="applyFamilyMembers['+i+'].afmHyzk"  id="applyFamilyMembers'+i+'afmHyzk" style="width: 92%" data-options="label:\'婚姻状况:\',valueField:\'piItemcode\',textField:\'piItemname\',url:\'<%=basePath %>ParmItem/getOptions?parmSetcode=07\',required:true,panelHeight:\'auto\',panelMaxHeight:\'280\',editable:false">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmGzdw" id="applyFamilyMembers'+i+'afmGzdw" style="width: 92%" data-options="label:\'工作单位:\',prompt:\'请输入单位全称\'">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmSfzh" id="applyFamilyMembers'+i+'afmSfzh" style="width: 92%" data-options="label:\'身份证号码:\',required:true,validType:[\'sfz\',\'empty\'],events:{keyup:setBirthday}">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmNsr" id="applyFamilyMembers'+i+'afmNsr" style="width: 92%" data-options="label:\'个人年收入(元):\',required:true,validType:[\'number\',\'empty\'],events:{keyup:changeSr}">' +
                    '</li>' +
                    '</ul>').appendTo("#addPerson");
                $.parser.parse($("#addPerson"))
            }
        }
    }
    //回填数据
    function setValue(form,data){
        for(var x in data){
            if(typeof(data[x])=="object"){
                $.each(form.serializeArray(), function (index) {
                    if(this['name'].indexOf(x)!=-1){//是否含有此字段
                        var nameSplit=this['name'].split('.');
                        if(nameSplit[0].indexOf("[")==-1){
                            //普通对象
                            var idArray=[];
                            idArray.push(nameSplit[0],nameSplit[1]);
                            //根据不同类型赋值
                            var typeClass=$('#'+idArray[0]+idArray[1]+'').attr('class');
                            if(typeClass!=undefined){
                                if(typeClass.indexOf('combobox')!=-1){
                                    $('#'+idArray[0]+idArray[1]+'').combobox('setValue',(data[x])[idArray[1]])
                                }else if(typeClass.indexOf('datebox')!==-1){
                                    $('#'+idArray[0]+idArray[1]+'').datebox('setValue',(data[x])[idArray[1]])
                                }else{
                                    $('#'+idArray[0]+idArray[1]+'').textbox('setValue',(data[x])[idArray[1]])
                                }
                            }

                        }else{
                            //数组对象
                            var idArray=[];
                            var index=nameSplit[0].indexOf("[");
                            var field=nameSplit[0].substring(0,index);
                            var indexCode=nameSplit[0].substring(index+1,(nameSplit[0].length-1));
                            idArray.push(field,indexCode,nameSplit[1]);
                            //根据不同类型赋值
                            var typeClass=$('#'+idArray[0]+idArray[1]+idArray[2]+'').attr('class');
                            if(typeClass.indexOf('combobox')!==-1){
                                $('#'+idArray[0]+idArray[1]+idArray[2]+'').combobox('setValue',(data[x])[indexCode][idArray[2]])
                            }else if(typeClass.indexOf('datebox')!==-1){
                                $('#'+idArray[0]+idArray[1]+idArray[2]+'').datebox('setValue',(data[x])[indexCode][idArray[2]])
                            }else{
                                $('#'+idArray[0]+idArray[1]+idArray[2]+'').textbox('setValue',(data[x])[indexCode][idArray[2]])
                            }
                        }

                    }
                });
            }else{
                $("#form").form('load',data);//直接赋值
            }
        }
        if($.trim(data.applyFamilyHouseChange.afhcCqr).length>0){
            $("input:radio[value='"+data.applyFamilyHouseChange.afhcBgfs+"']").prop('checked',true);
            $("input:radio[value='"+data.applyFamilyHouseChange.afhcBgfs+"']").siblings('input').prop('disabled',true)
            if(data.applyFamilyHouseChange.afhcBgfs=="拆迁"){
                $("#cq label").text("货币补偿金额(元):");
            }else{
                $("#cq label").text("转让金额(元):");
            }
        }
        bank.biography().setParams({row:data.apBaseimg,title:'sign'});
    }
    //图片回显
    function setImage(data) {
        if(data.volList.length>0){
            var volList=data.volList;
            for(var i=0;i<volList.length;i++){
                for(var k=0;k<volList[i].volelearcDtls.length;k++){
                    var imageUrl = (volList[i].volelearcDtls)[k].imgname;
                    var height = (volList[i].volelearcDtls)[k].height;
                    var width = (volList[i].volelearcDtls)[k].width;
                    var index=$($("#table tr:contains('"+volList[i].elearcname+"')")).index()-1;
                    initArray.push(index);
                    $("#table tr:contains('"+volList[i].elearcname+"')").find(".uploader-list").append('<div id="' + (volList[i].volelearcDtls)[k].volelearcdtlId + '" class="file-item thumbnail">' +
                        '<img src="'+imageUrl+'">' +
                        '</div>');
                }
            }
        }
    }
    function view(type){
        bank.viewAttach(type,"<%=basePath%>path/toAttachment","查看详情",500,300,true)
    }
</script>