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
	<meta charset="UTF-8" />
	<title>个人信息</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
</head>
<body>
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
	      <a class="mui-icon mui-action-menu mui-icon-bars mui-pull-left" href="#offCanvasSide"></a>
	      <h1 class="mui-title">修改密码</h1>
	    </header>
	    <nav class="mui-bar mui-bar-tab">
		    <a class="mui-tab-item" id="apply">
		        <span class="mui-icon mui-icon-home"></span>
		        <span class="mui-tab-label">我的申请</span>
		    </a>
		    <a class="mui-tab-item"  id="audit">
		        <span class="mui-icon mui-icon-list"></span>
		        <span class="mui-tab-label">我的年审</span>
		    </a>
		    <a class="mui-tab-item"  id="userInfo">
		        <span class="mui-icon mui-icon-contact"></span>
		        <span class="mui-tab-label">个人信息</span>
		    </a>
		    <a class="mui-tab-item mui-active" id="updatePwd">
		        <span class="mui-icon mui-icon-gear"></span>
		        <span class="mui-tab-label">修改密码</span>
		    </a>
		</nav>
	    <div class="mui-content mui-scroll-wrapper">
	      <div class="mui-scroll">
	        <!-- 主界面具体展示内容 -->
	        <form class="mui-input-group" method="post" id="ff" >
				<div class="mui-input-row">
					<label>原始密码</label>
					<input type="password" id="oldpwd" name="oldpwd" class="mui-input-password" >
				</div>
				<div class="mui-input-row">
					<label>新密码</label>
					<input type="password" id="newPwd" name="newPwd" class="mui-input-password" >
				</div>
				<div class="mui-input-row">
					<label>确认密码</label>
					<input type="password" id="repeatPwd" name="repeatPwd" class="mui-input-password" >
				</div>
				<div class="mui-content-padded">
					<button id='login' class="mui-btn mui-btn-block mui-btn-primary">提交</button>
				</div>
			</form>
	      </div>
	    </div>  
	    <div class="mui-off-canvas-backdrop"></div>
	  </div>
	</div>
	<script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/public/public.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
       mui.init();
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
       //提交
       mui(".mui-content-padded").on('tap', '#login', function(e) {
           var flag=false;
           $('#ff').form('submit', {
               url:'<%=basePath %>applyUserinfo/updatePwd',
               onSubmit: function(param){
                   if ($.trim($('#newPwd').val()).length==0||$.trim($('#repeatPwd').val()).length==0||$.trim($('#repeatPwd').val()).length==0){
                       weixin.toast("所有输入项不能为空");
					   return false;
                   }else if($.trim($('#newPwd').val())!=$.trim($('#repeatPwd').val())){
                       weixin.toast("两次输入密码不同");
                       return false;
				   }else{
                       flag=true;
				   }
                   return flag;
               },
               success:function(data){
                   weixin.toast(data)
			   },
               error:function(){
                   weixin.alert("数据库连接失败，请稍后再试！");
                   return false;
               }
           },'json');

       });
    </script>
</body>
</html>
