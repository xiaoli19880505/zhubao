<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="header.jsp"></jsp:include>
	<body class="easyui-layout">
		<div  data-options="region:'center',border:false" class="welcenter">
			<div class="easyui-panel welpanel" style="width: 100%">
				<h1 style="width: 100%">欢迎您使用徐州市住房保障管理系统！</h1>
			</div>
		</div>
	</body>
</html>