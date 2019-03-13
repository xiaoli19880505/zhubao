<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout" >
<div data-options="region:'center',border:false"  >
	<div class="easyui-panel windowPanel" >
		<div class="abtn-group btnInner">
			<a class="icon iconfont icon-zhongzhi golden" onclick="bank.clearForm('changeForm','reset')"><i>重置</i></a>
			<a class="icon iconfont icon-icon- green" onclick="saveAdd()"><i>保存</i></a>
			<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
			<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
		</div>

		<form class="easyui-form" method="post" id="changeForm">
			<ul class="search-group-add windowInner">
				<legend>基本信息</legend>
				<li style="width: 380px"><input class="easyui-textbox" name="meName" style="width: 92%" data-options="label:'<span>*</span>菜单名称:',required:true,validType:['specialCharacter','chinese']"></li>
				<li style="width: 380px"><input class="easyui-textbox" name="meUrl"  style="width: 92%" data-options="label:'<i style=\'color:red;margin-left:0\'>  </i>链接地址:'"></li>
				<li style="width: 380px"><input class="easyui-textbox" name="meDesc" style="width: 92%;height: 100px" data-options="label:'备注:',multiline:true"></li>
			</ul>
		</form>

	</div>
</div>
</body>
</html>
<script>

    function  saveAdd() {//保存
        var data=bank.biography().getParams('moduleAdd');
        bank.ajaxForm('#changeForm','<%=basePath %>menuInfo/saveMenuInfo',{moduleid:data.node.id},function(data){
            if(data=="新增成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
            parent.$('#dataTable').datagrid('reload');
            parent.$('#tree').tree('reload');
            parent.parent.$('#westTree').tree('reload');
            bank.ajaxMessage(data);
        });


    }
</script>

