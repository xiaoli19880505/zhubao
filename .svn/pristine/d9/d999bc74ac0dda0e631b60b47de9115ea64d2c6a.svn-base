<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9" >
		<title>徐州市住房保障管理系统</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>themes/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/icon/iconfont.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/common.css">
		<script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/home/common.js"></script>
		<link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">
	</head>

	<body class="easyui-layout" id="AllRegion">
		<!--顶部-->
		<div data-options="region:'north',border:false" class="northRegion">
			<div class="north_left">徐州市住房保障网站管理系统</div>
			<div class="north_center"></div>
			<div class="north_right">
				<div><img src="<%=basePath %>src/img/public/user.png" alt=""><span>xuzhoufenhang</span></div>
				<div>
					<a class="icon iconfont icon-tuichu white logout" title="退出系统" onclick="bank.logout('url');"></a>
				</div>
			</div>
		</div>
		<!--顶部结束-->
		<!--左侧导航-->
		<div data-options="region:'west',title:'首页',border:false" class="westRegion">
			<div class="easyui-accordion" border="false" id="leftNav">
				<div class="easyui-tree" id="westTree" data-options="animate:true,method:'post',url:'<%=basePath %>columnInfo/indexJson'"></div>
			</div>
		</div>
		<!--左侧导航结束-->
		<!--中间主体-->
		<div data-options="region:'center',border:false" class="centerRegion">
			<div id="AllTabs" class="easyui-tabs" style="width:100%;height: 100%">
				<div title="首页" class="index">
					<iframe src="<%=basePath %>pageColumn/welcome" frameborder="0" scrolling="auto" class="iframe" id="iframe"></iframe>
				</div>
			</div>
		</div>
		<!--中间主体结束-->
		<!--tabs右键tab操作-->
		<div id="menu" class="easyui-menu" style="width:200px;display: none">
			<div id="menu-closethis" data-options="name:1">关闭</div>
			<div id="menu-closeall" data-options="name:2">全部关闭</div>
			<div id="menu-closeother" data-options="name:3">关闭其他</div>
			<div id="menu-closeright" data-options="name:4">当前页右侧全部关闭</div>
			<div id="menu-closeleft" data-options="name:5">当前页左侧全部关闭</div>
		</div>
	</body>
</html>
<script>
    $(function(){
        bank.clickMenu('');
    });
</script>
