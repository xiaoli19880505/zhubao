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
		 <aside class="mui-off-canvas-left" id="offCanvasSide">
              <div class="mui-scroll-wrapper">
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
          </aside>
		<!-- 主页面容器 -->
		<div class="mui-inner-wrap">
		<!-- 主页面标题 -->
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-action-menu mui-icon-back mui-pull-left" id="back"></a>
			<h1 class="mui-title">经济适用住房申请</h1>
		</header>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active" id="apply">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">我的申请</span>
			</a>
			<a class="mui-tab-item" id="audit">
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
				<form method="post" id="form" class="easyui-form" enctype="multipart/form-data" >
					<div id="wrap1" >
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header">基本信息<span class="signBtn" id="changeSign">查看签字</span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group">
									<div class="commonRow readonlyRow">
										<input class="easyui-combobox" name="apSsq"  data-options="label:'户籍所在区(县)',readonly:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=05'">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-combobox" name="apJdbsc"  data-options="label:'户籍所在街道办事处',readonly:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=06'">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="apSqjwh"  data-options="label:'户籍所在社区',required:true,readonly:true">
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
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[0].afmXm" id="applyFamilyMembers0afmXm"  data-options="label:'姓名',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[0].afmSfzh" id="applyFamilyMembers0afmSfzh" data-options="label:'身份证号码',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<select class="easyui-combobox" name="applyFamilyMembers[0].afmXb" id="applyFamilyMembers0afmXb"  data-options="label:'性别',readonly:true">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
									<div class="commonRow readonlyRow">
										<select class="easyui-combobox" name="applyFamilyMembers[0].afmHyzk" id="applyFamilyMembers0afmHyzk"  data-options="label:'婚姻状况',readonly:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=07'">
										</select>
									</div>
									<div class="commonRow commonRow">
										<input class="easyui-textbox" name="applyFamilyMembers[0].afmGzdw" id="applyFamilyMembers0afmGzdw"  data-options="label:'工作单位',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[0].afmLxdh" id="applyFamilyMembers0afmLxdh"  data-options="label:'联系电话',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[0].afmNsr"  id="applyFamilyMembers0afmNsr"  data-options="label:'个人年收入(元)',readonly:true">
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
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[1].afmXm"  id="applyFamilyMembers1afmXm"  data-options="label:'姓名',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[1].afmSfzh" id="applyFamilyMembers1afmSfzh"  data-options="label:'身份证号码',readonly:true">
									</div>
									<div class="commonRow">
										<select class="easyui-combobox" name="applyFamilyMembers[1].afmXb" id="applyFamilyMembers1afmXb"  data-options="label:'性别',readonly:true">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[1].afmGzdw" id="applyFamilyMembers1afmGzdw"  data-options="label:'工作单位',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[1].afmLxdh" id="applyFamilyMembers1afmLxdh"  data-options="label:'联系电话',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyMembers[1].afmNsr"  id="applyFamilyMembers1afmNsr"  data-options="label:'个人年收入(元)',readonly:true">
									</div>
								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header">户籍信息</div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group">
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="apSqhjnx"  data-options="label:'户籍年限(年)',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="apJtnsr"  data-options="label:'家庭年收入(元)',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="apJtrk"  data-options="label:'家庭人口',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="apRjysr" data-options="label:'人均月收入(元)',readonly:true">
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
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyUnit.legelrep" id="applyUnitlegelrep"   data-options="label:'法定代表人',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyUnit.bsls" id="applyUnitbsls"   data-options="label:'统一社会信用代码',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyUnit.entag"  id="applyUnitentag"  data-options="label:'委托代理人',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyUnit.tel" id="applyUnittel"  data-options="label:'手机号码',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyUnit.address" id="applyUnitaddress"  data-options="label:'联系地址',readonly:true">
									</div>
								</div>
							</div>
						</div>
						<div class="mui-content-padded" style="margin-top: 2rem;">
							<button class="mui-btn mui-btn-block mui-btn-primary"  id='next2'>下一步</button>
						</div>
					</div>
					<div id="wrap2" style="display: none;">
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header">家庭现住房情况</div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group">
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouses[0].afhZl" id="applyFamilyHouses0afhZl"  data-options="label:'房屋坐落',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouses[0].afhCqr" id="applyFamilyHouses0afhCqr"  data-options="label:'产权人',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouses[0].afhMj" id="applyFamilyHouses0afhMj"  data-options="label:'建筑面积(m²)',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouses[0].afhRjmj" id="applyFamilyHouses0afhRjmj"  data-options="label:'人均建筑面积(m²)',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-combobox" name="applyFamilyHouses[0].afhZfxz" id="applyFamilyHouses0afhZfxz"  data-options="label:'现住房性质',readonly:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=01'">
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
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouseChange.afhcYzfzl" id="applyFamilyHouseChangeafhcYzfzl"  data-options="label:'原住房坐落',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouseChange.afhcCqr" id="applyFamilyHouseChangeafhcCqr"  data-options="label:'原产权人',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouseChange.afhcJzmj" id="applyFamilyHouseChangeafhcJzmj"  data-options="label:'建筑面积（m²）',readonly:true">
									</div>
									<div class="commonRow readonlyRow">
										<label class="textbox-label textbox-label-before" style="text-align: left; height: 32px; line-height: 10px;">
											方式
										</label>
										<input type="radio" name="applyFamilyHouseChange.afhcBgfs" value="拆迁" disabled/>拆迁&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="applyFamilyHouseChange.afhcBgfs" value="转让" disabled/>转让

									</div>
									<div class="commonRow readonlyRow" id="cqtime">
										<input class="easyui-datebox" name="applyFamilyHouseChange.afhcBgsj" id="applyFamilyHouseChangeafhcBgsj"  data-options="label:'拆迁时间',editable:false,readonly:true">
									</div>
									<div id="cq" class="commonRow readonlyRow">
										<input class="easyui-textbox" name="applyFamilyHouseChange.afhcZrbcje" id="applyFamilyHouseChangeafhcZrbcje"  data-options="label:'货币补偿金额(元)',readonly:true">
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
							<div class="mui-card-header">同住家庭成员情况</div>
						</div>
						<div class="card-container">

						</div>
						<div class="mui-button-row">
							<button type="button" class="mui-btn mui-btn-primary" id="prev2">上一步</button>
							<button type="button" class="mui-btn mui-btn-success" id="next4">下一步</button>
						</div>
					</div>
					<div id="wrap4" style="display: none">
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">收入情况证明材料<span class="reqSpan">*(必填)</span></span><span id="type0" class="detail">详情</span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList0">

								</div>
							</div>

						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">住房状况证明材料<span class="reqSpan">*(必填)</span></span><span id="type1" class="detail">详情</span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList1">

								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">家庭成员身份证<span class="reqSpan">*(必填)</span></span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList2">

								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">家庭成员户口簿(含首页)<span class="reqSpan">*(必填)</span></span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList3">

								</div>
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
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">反映家庭财产状况的有关证明材料</span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList5">

								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">申请人申报财产真实性书面承诺<span class="reqSpan">*(必填)</span></span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList6">

								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">婚姻情况证明<span class="reqSpan">*(必填)</span></span><span id="type2" class="detail">详情</span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList7">

								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">户籍所在地社区公示证明<span class="reqSpan">*(必填)</span></span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList8">

								</div>
							</div>
						</div>
						<div class="mui-card">
							<!--页眉，放置标题-->
							<div class="mui-card-header"><span class="name">其他相关材料</span></div>
							<!--内容区-->
							<div class="mui-card-content">
								<div class="mui-input-group uploader-list" id="fileList9">

								</div>
							</div>
						</div>
						<div class="mui-button-row">
							<button type="button" class="mui-btn mui-btn-primary" id="prev3">上一步</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="mui-off-canvas-backdrop"></div>
		 </div>
	</div>
</div>
	<div id="picture" class="mui-popover mui-popover-action ">
		<ul class="mui-table-view"></ul>
	</div>
	<div id="signCard" class="mui-popover mui-popover-action ">
		<div id="signatureparent"></div>
	</div>
<style>
	.textbox-label{
		max-width: 46%;
		width:11.2rem;
	}
	.commonRow .textbox,.commonRow .combo,.commonRow .datebox{
		min-width:50% !important;
		max-width: 80% !important;
	}
	.commonRow .textbox-text ,.commonRow .validatebox-text{
		width: 100% !important;
	}
	#signatureparent {
		color: darkblue;
		background-color: #fff;
		width: 100%;
		margin: 0 auto;
		height: 200px;
	}
	#signatureparent img{
		width: 100%;
		height: 100%;
	}
</style>
<script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript" ></script>
<script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.mobile.js" type="text/javascript" ></script>
<script src="<%=basePath %>srcApp/js/webuploader/webuploader.js" type="text/javascript" ></script>
<script src="<%=basePath %>srcApp/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/public/jSignature.min.noconflict.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/public/public.js"></script>
<script>
    mui.init();
    var initSign='';
    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.0006
    });
    mui(document.body).on('tap', '.mui-btn', function (e) {
        var id = this.getAttribute("id");
        var code = id.substring(4);
        mui("#pullrefresh").scroll().scrollTo(0,0,0);
		$('#wrap' + code + '').show().siblings().hide();
    });
    //返回上一页
    mui(".mui-bar").on('tap','#back',function () {
        mui.back();
    });
	//根据id查看详情
    //查看签字图片
    mui(document.body).on('tap','.signBtn',function(){
        var id=this.getAttribute("id");
        if(id=="changeSign"){
            mui('#signCard').popover('toggle');
            $("#signatureparent").html('<img src='+initSign+'>');
        }
    });
	mui('.mui-card').on('tap', '.detail', function() {
		var id=this.getAttribute("id");
		var type=Number(id.substring(4));
		mui('#picture').popover('toggle');
		weixin.typeDetail(type);
	});
    //底部导航
    mui(".mui-bar").on('tap', '.mui-tab-item', function(e) {
        var target = this.getAttribute("id");
        switch(target) {
            case 'apply':
                weixin.openWindow('applyList','<%=basePath %>appPath/applyList');
                break;
            case 'audit':
                weixin.openWindow('audioList','<%=basePath %>appPath/audioList');
                break;
            case 'userInfo':
                weixin.openWindow('userInfo','<%=basePath %>appPath/userInfo');
                break;
            case 'updatePwd':
                weixin.openWindow('updatePwd','<%=basePath %>appPath/updatePwd');
                break;
            default:
                break;
        }
    });
    //侧边导航
    mui(".mui-table-view").on('tap', '.leftList', function(e) {
        var target = this.getAttribute("id");
        switch(target) {
            case 'applyAside':
                weixin.openWindow('applyList','<%=basePath %>appPath/applyList');
                break;
            case 'audioAside':
                weixin.openWindow('audioList','<%=basePath %>appPath/audioList');
                break;
            case 'userAside':
                weixin.openWindow('userInfo','<%=basePath %>appPath/userInfo');
                break;
            case 'updateAside':
                weixin.openWindow('updatePwd','<%=basePath %>appPath/updatePwd');
                break;
            case 'exitAside':
                window.location.href="<%=basePath %>appLogin/appExit";
                break;
            default:
                break;
        }
    });

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
                setSign(data);//回填签字
            },error:function(){
                weixin.alert("数据库连接失败，请稍后再试")
            }
        })
    });
    //回填家庭成员
    function setPerson(data){
        if(data.applyFamilyMembers.length>2){
            for(var i=2;i<data.applyFamilyMembers.length;i++) {
                $('<div class="mui-card">' +
                    '<div class="mui-card-header">同住家庭成员情况</div>' +
                    '    <div class="mui-card-content">' +
                    '       <div class="mui-input-group">' +
                    '            <div class="commonRow readonlyRow">' +
                    '                 <select class="easyui-combobox" name="applyFamilyMembers[' + i + '].afmYsqrgx" id="applyFamilyMembers' + i + 'afmYsqrgx"  style="width: 92%" data-options="label:\'关系\',valueField:\'piItemname\',textField:\'piItemname\',panelHeight:\'auto\',readonly:true,editable:false">' +
                    '                 <option value="3">子女</option>' +
                    '                  </select>' +
                    '             </div>' +
                    '            <div class="commonRow readonlyRow">' +
                    '       			<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmXm" id="applyFamilyMembers' + i + 'afmXm"  data-options="label:\'姓名\',readonly:true">' +
                    '            </div>' +
                    '            <div class="commonRow readonlyRow">' +
                    '					<input class="easyui-datebox" name="applyFamilyMembers[' + i + '].afmCsny" id="applyFamilyMembers' + i + 'afmCsny"  data-options="label:\'出生年月\',readonly:true">' +
                    '            </div>' +
                    '            <div class="commonRow readonlyRow">' +
                    '					<input class="easyui-combobox" name="applyFamilyMembers[' + i + '].afmHyzk" id="applyFamilyMembers' + i + 'afmHyzk"  data-options="label:\'婚姻状况\',readonly:true,valueField:\'piItemcode\',textField:\'piItemname\',url:\'<%=basePath %>ParmItem/getOptions?parmSetcode=07\'">' +
                    '            </div>' +
                    '            <div class="commonRow readonlyRow">' +
                    '					<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmGzdw" id="applyFamilyMembers' + i + 'afmGzdw"  data-options="label:\'工作单位\',readonly:true">' +
                    '            </div>' +
                    '            <div class="commonRow readonlyRow">' +
                    '					<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmSfzh" id="applyFamilyMembers' + i + 'afmSfzh"  data-options="label:\'身份证号码\',readonly:true">' +
                    '            </div>' +
                    '            <div class="commonRow readonlyRow">' +
                    '					<input class="easyui-textbox" name="applyFamilyMembers[' + i + '].afmNsr" id="applyFamilyMembers' + i + 'afmNsr"  data-options="label:\'个人年收入(元)\',readonly:true">' +
                    '            </div>' +
                    '       </div>' +
                    '     </div>' +
                    '</div>').appendTo(".card-container");
                $.parser.parse($(".card-container"))
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
                            if(typeClass.indexOf('easyui-combobox')!=-1){
                                $('#'+idArray[0]+idArray[1]+'').combobox('setValue',(data[x])[idArray[1]])
                            }else if(typeClass.indexOf('easyui-datebox')!=-1){
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
        if($.trim(data.applyFamilyHouseChange.afhcCqr).length>0){
            $("input:radio[value='"+data.applyFamilyHouseChange.afhcBgfs+"']").prop('checked',true);
            $("input:radio[value='"+data.applyFamilyHouseChange.afhcBgfs+"']").siblings('input').prop('disabled',true);
            if(data.applyFamilyHouseChange.afhcBgfs=="拆迁"){
                $("#cq label").text("拆迁金额(元):");
            }
        }
    }
    //回填图片
	function setImage(data){
    if(data.volList.length>0){
        var volList=data.volList;
        for(var i=0;i<volList.length;i++){
            for(var j=0;j<volList[i].volelearcDtls.length;j++){
                var imageUrl = (volList[i].volelearcDtls)[j].imgname;
                $("#wrap4 .mui-card:contains('"+volList[i].elearcname+"')").find(".uploader-list").append(
                    '<img src="'+imageUrl+'">')
            }
        }

    }
    }
    //回填签名
    function setSign(data){
        if(data.apBaseimg!=undefined){
            initSign=data.apBaseimg;
        }
    }
</script>
</body>
</html>

