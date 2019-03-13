<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
		<div data-options="region:'center',border:false">
			<div class="easyui-panel windowPanel">
				<div class="abtn-group btnInner">
					<a class="icon iconfont icon-icon- green" onclick="saveAdd()"><i>保存</i></a>
					<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
				</div>
				<form method="post" class="easyui-form" id="form">
					<ul class="search-group-add windowInner">
						<legend>基本信息</legend>
						<li style="width: 320px"><input class="easyui-textbox" name="dutyname" style="width: 92%" data-options="label:'<span>*</span>角色名称:',required:true,validType:['specialCharacter']"></li>
						<li style="width: 320px"><input class="easyui-textbox" name="describe" style="width: 92%;height: 100px" data-options="label:'描述:',multiline:true"></li>
					</ul>
				</form>
			</div>
		</div>
	</body>
</html>
<script>
    function saveAdd(){
        bank.ajaxForm('#form','<%=basePath %>roleInfo/saveRoleInfo',{},function(data){
            bank.ajaxMessage(data);
            parent.$('#dataTable').datagrid('reload');
            if(data=="新增成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
        });
    }
</script>