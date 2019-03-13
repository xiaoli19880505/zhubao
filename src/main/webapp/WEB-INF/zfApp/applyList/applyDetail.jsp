<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>我的申请</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/common.css"/>
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
			<a class="mui-icon mui-action-menu mui-icon-back mui-pull-left" id="back"></a>
	        <h1 class="mui-title">我的申请</h1>
	    </header>
	   <nav class="mui-bar mui-bar-tab">
		    <a class="mui-tab-item mui-active" id="apply">
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
		    <a class="mui-tab-item"  id="updatePwd">
		        <span class="mui-icon mui-icon-gear"></span>
		        <span class="mui-tab-label">修改密码</span>
		    </a>
		</nav>
	    <div class="mui-content mui-scroll-wrapper">
	      <div class="mui-scroll">
	        <!-- 主界面具体展示内容 -->
	        <ul class="mui-table-view ullist">
				<li class="mui-table-view-cell">申请类型<span style="float: right">${appove.aptype}</span></li>
				<li class="mui-table-view-cell">申请编号<span style="float: right">${appove.aplbh}</span></li>
				<li class="mui-table-view-cell">申请日期<span style="float: right"><fmt:formatDate value="${appove.apldate}" pattern="yyyy-MM-dd" /></span></li>
				<li class="mui-table-view-cell">身份证号<span style="float: right">${appove.sfzh}</span></li>
				<li class="mui-table-view-cell">所属县区<span style="float: right">${appove.ssq}</span></li>
				<li class="mui-table-view-cell">所属街道<span style="float: right">${appove.ssj}</span></li>
				<c:choose>
					<c:when test="${appove.state==null || appove.state ==''}">
						<li class="mui-table-view-cell">审批状态<span class="mui-badge mui-badge mui-badge-danger">${appove.state}</span></li>
					</c:when>
					<c:otherwise>
						<li class="mui-table-view-cell">审批状态<span class="mui-badge mui-badge mui-badge-success">${appove.state}</span></li>
					</c:otherwise>
				</c:choose>
				<li class="mui-table-view-cell">审核节点<span style="float: right">${appove.processinstancename}</span></li>
				<c:choose>
	           <c:when test="${fn:indexOf(appove.state,'打回修改')!='-1'}">
				<li class="mui-table-view-cell">操作
					<span class="mui-badge-warning state toedit" style="margin-left: 15px" >修改</span>
					<span class=" mui-badge-success state toview" >查看</span></li>
			   </c:when>
			  <c:otherwise>
				  <li class="mui-table-view-cell ">操作<span class="mui-badge mui-badge-success toview">查看</span></li>
			  </c:otherwise>
			  </c:choose>
			</ul>
	      </div>
	    </div>  
	    <div class="mui-off-canvas-backdrop"></div>
	  </div>
	</div>
    <script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/public/public.js" type="text/javascript" charset="utf-8"></script>
	<script>
		mui.init();
        mui('.mui-scroll-wrapper').scroll({
            deceleration: 0.0006
        });
        //底部导航跳转
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
                case 'updateAside':
                    weixin.openWindow('updatePwd','<%=basePath %>appPath/updatePwd');
                    break;
                default:
                    break;
            }
        });
        //侧边栏跳转
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
        //查看详情
        mui(".mui-table-view-cell").on('tap', '.toedit', function(e) {
            weixin.openWindow('toView','<%=basePath %>appApply/appUpdate?applyType=${appove.atype}&applyId=${appove.aplid}');
        });
		//修改
        mui(".mui-table-view-cell").on('tap', '.toview', function(e) {
            weixin.openWindow('toEdit','<%=basePath %>appApply/appViewApprove?applyType=${appove.atype}&applyId=${appove.aplid}');
        });
        //返回上一页
		mui(".mui-bar").on('tap','#back',function () {
            weixin.openWindow('applyList','<%=basePath %>appPath/applyList');
        })
	</script>
</body>
</html>

