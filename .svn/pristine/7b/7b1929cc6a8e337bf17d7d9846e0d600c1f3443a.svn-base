<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>申请类型</title>
		<link rel="stylesheet" href="<%=basePath%>thiemesApply/gray/easyui.css" />
		<link rel="stylesheet" href="<%=basePath%>srcApply/css/common.css" />
		<script type="text/javascript" src="<%=basePath%>srcApply/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>srcApply/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>srcApply/js/easyui-lang-zh_CN.js"></script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'center'" id="centerType">
			<div class="application">
				<h2>请选择您要申请的类型</h2>
				<div class="changeType">
					<%--<c:out value="${appType}"></c:out>--%>
					<c:if test="${appType==null}">
					<div>
						<p>
							<a href="<%=basePath%>toappinform/toapplytype?applytype=1" class="lineH">经济适用住房</a>
						</p>
					</div>
					<div>
						<p>
							<a href="<%=basePath%>toappinform/toapplytype?applytype=5">公共租赁住房
								<br>（新就业人员）</a>
						</p>
					</div>
					<div>
						<p>
							<a href="<%=basePath%>toappinform/toapplytype?applytype=4">公共租赁住房
								<br>（外来务工人员）</a>
						</p>
					</div>
					</c:if>
					<c:if test="${appType==null || appType =='2'}">
					<div>
						<p>
							<a href="<%=basePath%>toappinform/toapplytype?applytype=3">公共租赁住房
								<br>（低保、特困家庭）</a>
						</p>
					</div>
					</c:if>
					<c:if test="${appType==null || appType=='3'}">
					<div>
						<p>
							<a href="<%=basePath%>toappinform/toapplytype?applytype=2" class="lineH">公共租赁住房租赁补贴</a>
						</p>
					</div>
					</c:if>

				</div>
			</div>
		</div>
	</body>

</html>
