<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>徐州市住房保障管理系统</title>
    <link rel="stylesheet" href="<%=basePath %>thiemesApply/gray/easyui.css" />
    <link rel="stylesheet" href="<%=basePath %>srcApply/css/webuploader.css" />
    <link rel="stylesheet" href="<%=basePath %>srcApply/css/common.css" />
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/webuploader.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/common.js"></script
    <link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">
</head>

<body class="easyui-layout">
<div class="over" style="display: none;">
    <div id="loading"><img src="<%=basePath %>srcApply/img/public/loading.gif" ></div>
</div>
<div data-options="region:'center'" id="center">
    <span class="top-title">公共租赁住房(外来务工人员)申请</span>
    <div id="top">
        <form method="post" id="form" class="easyui-form" enctype="multipart/form-data">
            <div style="display: none">
                <input class="easyui-textbox" name="alsid" id="alsid">
            </div>
            <div style="display: none">
                <input class="easyui-textbox" name="apSqlb" id="apSqlb">
            </div>
            <ul class="input-group">
                <li>
                    <input class="easyui-textbox" name="affDwmc" style="width: 92%" data-options="label:'单位名称:',required:true,prompt:'请输入单位全称'">
                </li>
                <li>
                    <input class="easyui-combobox" name="apSsq" style="width: 92%" data-options="label:'单位所在区（县）:',required:true,valueField:'piItemcode',textField:'piItemname',
                    url:'<%=basePath %>ParmItem/selectSsqExceptCenter',onSelect:selectSSQ,editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                </li>
                <li>
                    <input class="easyui-combobox" name="apJdbsc" id="apJdbsc" style="width: 92%" data-options="label:'单位所在街道办事处:',required:true,valueField:'piItemcode',textField:'piItemname',editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                </li>
            </ul>
            <!--进度条-->
            <div id="procsss">
                <div class="left">
                    <img src="<%=basePath %>srcApply/img/applicationForm/left.png" />
                </div>
                <div class="right">
                    <img src="<%=basePath %>srcApply/img/applicationForm/right.png" />
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
                 <%--   <p class="agreenSign">
                        <span onclick="changeSign()"><a href="#">承诺书签字修改</a></span>
                    </p>--%>
                    <ul class="input-group windowInner">
                        <legend>申请人信息</legend>
                        <li style="display: none">
                            <input class="easyui-textbox" name="affSqrid" id="affSqrid" style="width: 92%" data-options="label:'affSqrid'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmId" id="applyFamilyMembers0afmId" style="width: 92%" data-options="label:'afmId'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmSqid" id="applyFamilyMembers0afmSqid" style="width: 92%" data-options="label:'afmSqid'">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmXm" id="applyFamilyMembers0afmXm" style="width: 92%" data-options="label:'姓名:',required:true,readonly:true">
                        </li>
                        <li class="readonly">
                            <select class="easyui-combobox" name="applyFamilyMembers[0].afmXb" id="applyFamilyMembers0afmXb" style="width: 92%" data-options="label:'性别:',required:true,readonly:true,editable:false">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </li>
                        <li>
                            <select class="easyui-combobox" name="applyFamilyMembers[0].afmHyzk" id="applyFamilyMembers0afmHyzk" style="width: 92%" data-options="label:'婚姻状况:',required:true,valueField:'piItemcode',textField:'piItemname',
                            url:'<%=basePath %>ParmItem/getOptions?parmSetcode=07',editable:false,onSelect:selecthyzk">
                            </select>
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmSfzh" id="applyFamilyMembers0afmSfzh" style="width: 92%" data-options="label:'身份证号码:',required:true,validType:['sfz','empty'],readonly:true">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyMembers[0].afmLxdh" id="applyFamilyMembers0afmLxdh" style="width: 92%" data-options="label:'联系电话:',required:true,validType:['phone','empty'],readonly:true">
                        </li>
                        <li >
                            <input class="easyui-textbox" name="affGrnsr"  id="applyFamilyMembers0afmNsr" style="width: 92%" data-options="label:'个人年收入(元):',required:true,validType:['number','empty'],events:{keyup:changeSr}">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="affWdhjdz" style="width: 92%" data-options="label:'外地户籍地址:',required:true,validType:['empty']">
                        </li>
                    </ul>
                    <ul class="input-group windowInner">
                        <legend>配偶信息</legend>
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
                        <legend>工作社保情况</legend>
                        <li>
                            <input class="easyui-datebox" name="affLxjzsj" style="width: 83%" data-options="label:'来徐时间',required:true,editable:false,validType:['minDate']"> &nbsp;至今
                        </li>
                        <li>
                            <input class="easyui-datebox" name="affLdhtkssj" style="width: 92%" data-options="label:'劳动合同签订年限:',required:true,editable:false,validType:['minDate']">
                        </li>
                        <li>
                            <input class="easyui-datebox" name="affLdhtjssj" style="width: 92%" data-options="label:'至',required:true,editable:false">
                        </li>
                        <li>
                            <input class="easyui-datebox" name="affSbjnsj" style="width: 83%" data-options="label:'社会保险缴纳情况',required:true,editable:false,validType:['minDate','annual']"> &nbsp;至今
                        </li>
                        <li>
                            <input class="easyui-datebox" name="affGjjjnsj" style="width: 83%" data-options="label:'住房公积金缴纳情况',editable:false,validType:['minDate']"> &nbsp;至今
                        </li>
                    </ul>
                    <ul class="input-group windowInner">
                        <legend>工作单位信息</legend>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyUnit.aplid" id="applyUnitaplid" style="width: 92%" data-options="label:'afhId:'">
                        </li>
                        <li style="display: none">
                            <input class="easyui-textbox" name="applyUnit.unitid" id="applyUnitunitid" style="width: 92%" data-options="label:'afhSqid:'">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.legelrep" id="applyUnitlegelrep" style="width:92%" data-options="label:'法定代表人',required:true,validType:['empty','chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.bsls" id="applyUnitbsls" style="width: 92%" data-options="label:'统一社会信用代码:',required:true,validType:['xyCode','empty']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.entag" id="applyUnitentag" style="width:92%" data-options="label:'委托代理人',validType:['empty','chinese','specialCharacter']">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.tel" id="applyUnittel" style="width:92%" data-options="label:'联系方式:',validType:'phoneTel',required:true">
                        </li>
                        <li>
                            <input class="easyui-textbox" name="applyUnit.address"  id="applyUnitaddress" style="width:92%" data-options="label:'联系住址',required:true,required:true,validType:['empty']">
                        </li>
                    </ul>
                    <p class="sbumit right">
                        <span><a href="#">下一步</a></span>
                    </p>
                </div>
                <div style="display: none;">
                    <ul class="input-group windowInner">
                        <legend>家庭收入情况</legend>
                        <li class="readonly">
                            <input class="easyui-textbox" name="apJtrk" id="apJtrk"  style="width: 92%" data-options="label:'家庭人口:',readonly:true,validType:['numberZ','empty']">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="apJtnsr" id="apJtnsr" style="width: 92%" data-options="label:'家庭年收入(元):',required:true,validType:'number',readonly:true">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="apRjysr" id="apRjysr" style="width: 92%" data-options="label:'人均月收入(元):',required:true,validType:'number',readonly:true">
                        </li>
                    </ul>
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
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhMj" id="applyFamilyHouses0afhMj" style="width: 92%" data-options="label:'房屋面积(m²):',required:true,validType:['number','empty'],events:{keyup:changeMj}">
                        </li>
                        <li class="readonly">
                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhRjmj" id="applyFamilyHouses0afhRjmj" style="width: 92%" data-options="label:'人均建筑面积(m²):',validType:['number','empty'],readonly:true">
                        </li>
                        <li>
                            <input class="easyui-combobox" name="applyFamilyHouses[0].afhZfxz" id="applyFamilyHouses0afhZfxz" style="width: 92%" data-options="label:'现住房性质:',required:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=01'" editable="false">
                        </li>
                    </ul>
                    <p class="sbumit right">
                        <span><a href="#">下一步</a></span>
                    </p>
                </div>
                <div style="display: none;">
                    <input type="button" value="删除同住家庭成员" class="delPerson" onclick="delPerson()" />
                    <input type="button" value="添加同住家庭成员" class="addPerson" onclick="addPerson()" />
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
                            <td class="tdname"><span class="name">申请人和共同承租家庭成员身份证</span> <span class="red">*(必填)</span><span class="aspan" onclick="view(4)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark0 filePicker" onclick="add(0)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList0" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td class="tdname"><span class="name">户口簿及其他居住证明</span><span class="red">*(必填)</span><span class="aspan" onclick="view(10)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark1 filePicker" onclick="add(1)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList1" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td class="tdname"><span class="name">劳动合同或者聘用合同</span><span class="red">*(必填)</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark2 filePicker" onclick="add(2)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList2" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td class="tdname"><span class="name">收入情况证明材料</span><span class="red">*(必填)</span><span class="aspan" onclick="view(11)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark3 filePicker" onclick="add(3)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList3" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td class="tdname"><span class="name">住房状况证明材料</span><span class="red">*(必填)</span><span class="aspan" onclick="view(6)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark4 filePicker" onclick="add(4)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList4" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td class="tdname"><span class="name">单位出具的公租房申请公示证明</span><span class="red">*(必填)</span><span class="aspan" onclick="view(7)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark5  filePicker " onclick="add(5)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList5" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td class="tdname"><span class="name">住房公积金缴存证明或者社会保险经办机构出具的社会保险缴存证明</span><span class="red">*(必填)</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark6 filePicker" onclick="add(6)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList6" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td class="tdname"><span class="name">婚姻状况证明</span><span class="red">*(必填)</span><span class="aspan" onclick="view(2)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark7  filePicker" onclick="add(7)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList7" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td class="tdname"><span class="name">营业执照</span><span class="red">*(必填)</span><span class="aspan" onclick="view(9)">查看详情</span></td>
                            <td class="addJpg">
                                <div>
                                    <div class="fileMark8 filePicker" onclick="add(8)">添加附件</div>
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
                                    <div class="fileMark9 filePicker" onclick="add(9)">添加附件</div>
                                    <!--用来存放item-->
                                    <div id="fileList9" class="uploader-list"></div>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <div class="sbumit" onclick="save()">
                        <span><a href="#">提交</a></span>
                    </div>
                </div>
            </div>
        </form>
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
    var person=false;var i=1;
    var j=0,currentIndex=0,sucIndex=0,applyid="",num = "",codeArray=[],nameArray=[],initArray=[],idArray=[],sendArray=[];
    var isSave=true;//控制是否可以提交变量
    $('.filePicker').each(function (index) {
        nameArray[index]=$(this).parent().parent().siblings().find('.name').text();
    });
    uploader = WebUploader.create({
        auto: false,
        swf: '<%=basePath %>srcApply/js/Uploader.swf',
        server: '<%=basePath %>volel/uoloadVolel',
        pick:{
            id:'.filePicker',
            label:'添加附件'
        } ,
        fileVal: "volname1",
        threads:1,//并发最大数量限制
        fileNumLimit: 100,//限制最大张数100
        disableGlobalDnd: true, //禁用浏览器的拖拽功能，否则图片会被浏览器打开
        fileSizeLimit: 1000 * 1024 * 1024, // 总共的最大限制1000M
        fileSingleSizeLimit: 10 * 1024 * 1024, // 单文件的最大限制4M
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
    function del(obj) {
        var fileId=obj.parent().attr("id");
        var index=bank.getArrayIndex(idArray,fileId);
        idArray.splice(index,1);
        codeArray.splice(index,1);
        sendArray.splice(index,1);
        obj.parents(".thumbnail").remove();
        uploader.removeFile(fileId,true);
        uploader.getFiles('inited');
    }
    function add(index){
        j=index;
    }
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            uploader.refresh();
            bank.alertMessage("请上传JPG、PNG、GIF、BMP格式图片文件");
        }else if (type == "F_DUPLICATE") {
            uploader.refresh();
            bank.alertMessage("请不要重复选择文件！");
        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
            uploader.refresh();
            bank.alertMessage("每次选择文件大小不超过10M");
        }else if(type=="F_EXCEED_SIZE"){
            uploader.refresh();
            bank.alertMessage("每次选择文件大小不超过10M")
        }else if(type=="Q_EXCEED_NUM_LIMIT"){
            uploader.refresh();
            bank.alertMessage("最多上传100张图片")
        }
    });
    uploader.on('fileQueued', function (file) {
        idArray.push(file.id);//存入数组
        var pickId=file.source.ruid;
        var pickClass=$("#"+pickId+"").parent().parent().parent().find(".filePicker").attr("class");
        if(pickClass!=undefined){
            j=pickClass.substring(8,9);
            codeArray.push(Number(j));
            sendArray.push(Number(j))
        }else{
            codeArray.push(Number(j));
            sendArray.push(Number(j))
        }
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img >' +
            '<div class="info" onclick="del($(this))">' + "删除" + '</div>' +
            '</div>'
            ),
            $img = $li.find('img');
        $("#fileList" + j + "").append($li);
        uploader.makeThumb(file, function (error, src) {
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
        }, 15, 15);
    });
    function send(applyid){
        //上传后才执行
        var newArray=copyArray(sendArray);
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
        }
        if(newArray.length>0){
            uploader.on('uploadBeforeSend', function (obj, data, headers){
                var code=newArray[currentIndex];
                data.volname=nameArray[code];
                data.fileVal="volname"+(Number(1+Number(code)));
                data.applyid=applyid;
            });
            uploader.upload();
            //上传后才执行
            uploader.on( 'uploadError', function(file) {
                currentIndex++;
                if(currentIndex==newArray.length){
                    bank.ajaxMessage('上传失败');
                    $(".over").hide();
                    window.location.reload();
                }
            });
            //上传后才执行
            uploader.on( 'uploadSuccess', function( file ) {
                currentIndex++;
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
    //处理数组
    function copyArray(codeArray){
        return codeArray
    }
    function solveArray(codeArray){
        return codeArray.sort();
    }
    function judgeArray(newArray,initArray){
        var judgeFlag=true;
        var bitianArray=[0,1,2,3,4,5,6,7,8];
        var endArray=newArray.concat(initArray);
        var temp=bank.arraySplice(endArray);
        if(temp.length>0){
            for(var x in bitianArray){
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
        if(isSave){
            var isValid = $('#form').form('validate');
            if(!isValid){
                isSave=true;
                bank.ajaxMessage('请检查是否有漏填或错填内容！');
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
        var checkIndex=$("#addPerson ul").length+2;
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
                            bank.ajaxMessage(result+"身份证和姓名不匹配");
                            return false
                        }
                    },error:function () {
                        isSave=true;
                        bank.alertMessage("数据库连接失败，请稍后再试");
                        return false
                    }
                });

            }
        }
    }
    function secondSave() {
       /* //获取签字
        var sign =bank.biography().getParams('sign');*/
        var judgeFlag=judgeArray(solveArray(codeArray),initArray);
        var flag=false;
        $('#form').form('submit', {
            url: '<%=basePath %>applyns/updateNsInfo',
            onSubmit: function(param){
                /*param.apBaseimg = sign.row;*/
                var isValid = $(this).form('validate');
                if (!isValid) {
                    flag=false;
                    isSave=true;
                    bank.ajaxMessage('请检查是否有漏填或错填内容！');
                }else if(!judgeFlag){
                    flag=false;
                    isSave=true;
                    bank.alertMessage("附件所有必填项不能为空！")
                }else{
                    isSave=false;
                    flag=true
                }
                return flag
            },
            success: function(data){
                var data=JSON.parse(data);
                if(data.result == "success"){
                    $(".over").show();
                     applyid=data.applyid;
                    currentIndex=0;
                    send(applyid);
                }else{
                    isSave=true;
                    bank.ajaxMessage(data.result)
                }
            },
            error:function (data) {
                isSave=true;
                bank.alertMessage("数据库连接失败，请稍后再试");
            }
        },'json');
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
    }
    //添加家庭成员
    function addPerson() {
        var addIndex=Number($("#addPerson ul").length)+2;
        if(person){
            i++;
            $("#apJtrk").textbox('setValue',i);
            var perString=$('<ul class="input-group windowInner">' +
                '<legend>同住家庭成员情况</legend>' +
                '<li>' +
                '<select class="easyui-combobox" name="applyFamilyMembers['+addIndex+'].afmYsqrgx" id="applyFamilyMembers'+addIndex+'afmYsqrgx" style="width: 92%" data-options="label:\'关系:\',valueField:\'piItemname\',textField:\'piItemname\',panelHeight:\'auto\',required:true,editable:false">' +
                '<option value="3">子女</option>'+
                '</select>'+
                '</li>' +
                '<li>' +
                '<input class="easyui-textbox" name="applyFamilyMembers['+addIndex+'].afmXm" id="applyFamilyMembers'+addIndex+'afmXm" style="width: 92%" data-options="label:\'姓名:\',required:true,validType:[\'empty\',\'chinese\',\'specialCharacter\']">' +
                '</li>' +
                '<li class="readonly">' +
                '<input class="easyui-datebox" name="applyFamilyMembers['+addIndex+'].afmCsny" id="applyFamilyMembers'+addIndex+'afmCsny" style="width: 92%" data-options="label:\'出生年月:\',required:true,readonly:true,editable:false">' +
                '</li>' +
                '<li class="readonly">' +
                '<select  class="easyui-combobox" name="applyFamilyMembers['+addIndex+'].afmHyzk" id="applyFamilyMembers'+addIndex+'afmHyzk" style="width: 92%" data-options="label:\'婚姻状况:\',valueField:\'piItemcode\',textField:\'piItemname\',readonly:true,editable:false">' +
                '<option value="1">未婚</option>'+
                '</select>'+
                '</li>' +
                '<li>' +
                '<input class="easyui-textbox" name="applyFamilyMembers['+addIndex+'].afmGzdw" style="width: 92%" data-options="label:\'工作单位:\',prompt:\'请输入单位全称\'">' +
                '</li>' +
                '<li>' +
                '<input class="easyui-textbox" name="applyFamilyMembers['+addIndex+'].afmSfzh" id="applyFamilyMembers'+addIndex+'afmSfzh"  style="width: 92%" data-options="label:\'身份证号码:\',required:true,validType:[\'sfz\',\'empty\'],events:{blur:setBirthday}">' +
                '</li>' +
                '<li>' +
                '<input class="easyui-textbox" name="applyFamilyMembers['+addIndex+'].afmNsr" id="applyFamilyMembers'+addIndex+'afmNsr" style="width: 92%" data-options="label:\'年收入(元):\',required:true,validType:\'number\',events:{keyup:changeSr}">' +
                '</li>' +
                '</ul>').appendTo("#addPerson");
            $.parser.parse(perString);
            changeMj(i);
            changeSr(i)
        }else{
            bank.alertMessage("婚姻状况未填或未婚状态，不能添加家庭成员")
        }
    }
    //删除同住人口
    function delPerson(){
        var len= $("#addPerson ul").length;
        if(len>0){
            i--;
        }
        $("#apJtrk").textbox('setValue',i);
        $("#addPerson").find("ul:last").remove();
        changeMj(i);
        changeSr(i)
    }
    /********************************************************************************************************************************************************************************************/
    //数据回显
    $(function () {
        $.ajax({
            url:'<%=basePath %>appove/getNsApproveDetail',
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
    //回填家庭成员 i = 3 ,本人为1 配偶为2 ，默认不能删除为3
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
                    '<li class="readonly">' +
                    '<select class="easyui-combobox" name="applyFamilyMembers['+i+'].afmYsqrgx" id="applyFamilyMembers'+i+'afmYsqrgx" style="width: 92%" data-options="label:\'关系:\',valueField:\'piItemname\',textField:\'piItemname\',required:true,editable:false,panelHeight:\'auto\'">' +
                    '<option value="3">子女</option>'+
                    '</select>'+
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmXm" id="applyFamilyMembers'+i+'afmXm"  style="width: 92%" data-options="label:\'姓名:\',required:true,validType:[\'empty\',\'chinese\',\'specialCharacter\']">' +
                    '</li>' +
                    '<li class="readonly">' +
                    '<input class="easyui-datebox" name="applyFamilyMembers['+i+'].afmCsny" id="applyFamilyMembers'+i+'afmCsny" style="width: 92%" data-options="label:\'出生年月:\',required:true,readonly:true,editable:false">' +
                    '</li>' +
                    '<li class="readonly">' +
                    '<input class="easyui-combobox" name="applyFamilyMembers['+i+'].afmHyzk"  id="applyFamilyMembers'+i+'afmHyzk" style="width: 92%" data-options="label:\'婚姻状况:\',valueField:\'piItemcode\',textField:\'piItemname\',url:\'<%=basePath %>ParmItem/getOptions?parmSetcode=07\',required:true,readonly:true,editable:false">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmGzdw" id="applyFamilyMembers'+i+'afmGzdw" style="width: 92%" data-options="label:\'工作单位:\',required:true,prompt:\'请输入单位全称\'">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmSfzh" id="applyFamilyMembers'+i+'afmSfzh" style="width: 92%" data-options="label:\'身份证号码:\',required:true,validType:[\'sfz\',\'empty\'],events:{blur:setBirthday}">' +
                    '</li>' +
                    '<li>' +
                    '<input class="easyui-textbox" name="applyFamilyMembers['+i+'].afmNsr" id="applyFamilyMembers'+i+'afmNsr" style="width: 92%" data-options="label:\'个人年收入(元):\',required:true,validType:\'number\',events:{keyup:changeSr}">' +
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
                            if(typeClass.indexOf('combobox')!==-1){
                                $('#'+idArray[0]+idArray[1]+'').combobox('setValue',(data[x])[idArray[1]])
                            }else if(typeClass.indexOf('datebox')!==-1){
                                $('#'+idArray[0]+idArray[1]+'').datebox('setValue',(data[x])[idArray[1]])
                            }else{
                                $('#'+idArray[0]+idArray[1]+'').textbox('setValue',(data[x])[idArray[1]])
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
            }else{
                $("#form").form('load',data);//直接赋值
            }
        }
        /*bank.biography().setParams({row:data.apBaseimg,title:'sign'});*/
    }
    //图片回显
    function setImage(data) {
        if(data.volList.length>0){
            var volList=data.volList;
            for(var i=0;i<volList.length;i++){
                for(var k=0;k<volList[i].volelearcDtls.length;k++){
                    var imageUrl = (volList[i].volelearcDtls)[k].imgname;
                    var index=$($("#table tr:contains('"+volList[i].elearcname+"')")).index()-1;
                    initArray.push(index);
                    $("#table tr:contains('"+volList[i].elearcname+"')").find(".uploader-list").append('<div id="' + (volList[i].volelearcDtls)[k].volelearcdtlId + '" class="file-item thumbnail id">' +
                        '<img src="'+imageUrl+'">' +
                        '<div class="info" onclick="delList($(this))">' + "删除" + '</div>' +
                        '</div>');
                }
            }
        }
    }
    //图片删除
    function delList(obj) {
        var id = obj.parents().attr("id");
        var code=obj.parent().parent().attr("id").substring(8);
        $.ajax({
            url:'<%=basePath %>volel/deleteDetail',
            type:'post',
            dataType:'json',
            data:{
                id:id
            },
            success:function(data){
                obj.parents(".thumbnail").remove();
                var index=bank.getArrayIndex(initArray,Number(code));
                initArray.splice(index,1);
            },error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试")
            }
        })
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
        var eachIndex = $("#addPerson ul").length + 2;
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

    //先输入了姓名后选择关系
    function judgeByAge(birthday,id){
        if(new Date().getFullYear()-(birthday.substring(0,4))>18){
            $("#applyFamilyMembers"+id+"afmSfzh").textbox('setValue');
            $("#applyFamilyMembers"+id+"afmCsny").textbox('setValue','');//清空
            bank.alertMessage('子女年龄大于18岁不能添加')
        }
    }
    //验证身份是否已婚
    function selecthyzk(record){
        var ulLen=$("#addPerson ul").length;//判断是否有家庭成员
        if(record.piItemcode=="2"){//已婚
            i=2+Number(ulLen);
            person=true;
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('reload');
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:true,readonly:false});
        }else if(record.piItemcode=="1"){//未婚
            i=1;
            person=false;
            $("#addPerson").html("");
            $.parser.parse($("#addPerson"));
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,readonly:true});
            setReadonly();
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('clear');
        }else{
            i=Number(ulLen)+1;
            person=true;
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,readonly:true});
            setReadonly();
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
            judgeByAge(birthday,id)
        }
    }
    //设置不可读
    function  setReadonly(){
        $('#applyFamilyMembers1afmXm').textbox('textbox').attr("unselectable","on");
        $('#applyFamilyMembers1afmSfzh').textbox('textbox').attr("unselectable","on");
        $('#applyFamilyMembers1afmLxdh').textbox('textbox').attr("unselectable","on");
        $('#applyFamilyMembers1afmNsr').textbox('textbox').attr("unselectable","on");
        $('#applyFamilyMembers1afmYsqrgx').textbox('textbox').attr("unselectable","on");
        $('#applyFamilyMembers1afmXb').textbox('textbox').attr("unselectable","on");
        $('#applyFamilyMembers1afmGzdw').textbox('textbox').attr("unselectable","on");
    }
    function view(type){
        bank.viewAttach(type,"<%=basePath%>path/toAttachment","查看详情",500,300,true)
    }
    /*//承诺书修改
    function changeSign() {
        bank.showWindow("<%=basePath %>path/toSign","签字",740, 500, true);
    }*/
</script>