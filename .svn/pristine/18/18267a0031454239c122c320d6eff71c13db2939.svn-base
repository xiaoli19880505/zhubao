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
			<a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
			<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
			<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
		</div>
		<form method="post" id="form" class="easyui-form">
			<ul class="search-group-add windowInner">
				<legend>基本信息</legend>
				<li><input class="easyui-textbox" name="usercode" style="width: 92%" data-options="label:'<span>*</span>登录名:',required:true,validType:['specialCharacter','allNumber']"></li>
				<li><input class="easyui-textbox" name="username" style="width: 92%" data-options="label:'<span>*</span>姓名:',required:true,validType:['specialCharacter','allNumber','chinese']"></li>
				<li><select class="easyui-combobox" name="sex" style="width: 92%" data-options="label:'性别:',panelHeight:'auto',panelMaxHeight:400,required:true,editable:false">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
				</li>
				<li><input class="easyui-textbox" name="linktel" style="width: 92%" data-options="label:'<span>*</span>联系电话:',required:true,validType:['phoneTel']"></li>
				<li class="readonly"><input class="easyui-combobox" name="ssq" id="ssq" style="width: 92%" data-options="label:'所属区:',panelHeight:'auto',
						panelMaxHeight:400,readonly:true,editable:false,valueField:'id',textField:'text'"></li>
				<li class="readonly"><input class="easyui-combobox" name="ssj" id="ssj" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',
						panelMaxHeight:400,readonly:true,editable:false,valueField:'id',textField:'text'"></li>
				<li><input class="easyui-textbox" name="email" style="width: 92%" data-options="label:'电子邮箱:',validType:['email']"></li>
				<li>
					<select class="easyui-combobox" name="state" style="width: 92%" data-options="label:'<span>*</span>用户状态:',panelHeight:'auto',panelMaxHeight:400,required:true,editable:false">
						<option value="1">可用</option>
						<option value="0">停用</option>
					</select>
				</li>
				<li><input class="easyui-textbox" name="describe" style="width: 92%;height:100px" data-options="label:'备注',multiline:true"></li>
			</ul>
		</form>
	</div>
</div>
</body>

</html>
<style>
	.textbox-label {
		width: 100px;
	}
</style>
<script>
    $(function(){
        var data=bank.biography().getParams('userAdd');
        if(data.row.remarkCode=="block"){
            $("#ssq").combobox('setValue',{id:data.row.piItemcode,text:data.row.text});
            $("#ssj").combobox('setValue',"");
        }else{
            $("#ssq").combobox('setValue',{id:data.row.piParentsetcode,text:data.row.parentText});
            $("#ssj").combobox('setValue',{id:data.row.piItemcode,text:data.row.text});
        }
    });
    function  save() {//保存
        bank.ajaxForm('#form','<%=basePath%>UserInfo/insert',{},function(data){
            if(data=="添加成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
            parent.$('#dataTable').datagrid('reload');
            bank.ajaxMessage(data);
        });


    }
</script>
