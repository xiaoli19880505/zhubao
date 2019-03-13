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
				<li class="readonly"><input class="easyui-textbox" name="usercode" style="width: 92%" data-options="label:'<span>*</span>登录名:',readonly:true"></li>
				<li class="readonly"><input class="easyui-textbox" name="username" style="width: 92%" data-options="label:'<span>*</span>姓名:',readonly:true"></li>
				<li><select class="easyui-combobox" name="sex" id="sex" style="width: 92%" data-options="label:'性别:',panelHeight:'auto',panelMaxHeight:400,required:true,editable:false">
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
					<select class="easyui-combobox" name="state" id="state" style="width: 92%" data-options="label:'<span>*</span>用户状态:',panelHeight:'auto',panelMaxHeight:400,required:true,editable:false">
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
        var data=bank.biography().getParams('userEdit');
        $('#form').form('load',data.row);
        $("#sex").combobox('setValue',data.row.sex);
        $("#state").combobox('setValue',data.row.state);
        if((data.row).parmItemssjd){
            $("#ssj").combobox('setValue',{id:(data.row).parmItemssjd.piItemcode,text:data.row.parmItemssjd.piItemname});//待修改
        }else{
            $("#ssj").combobox('setValue',"");//待修改
        }
        $("#ssq").combobox('setValue',{id:(data.row).parmItemssq.piItemcode,text:data.row.parmItemssq.piItemname});//待修改

    });
    function  save() {//保存
        var data=bank.biography().getParams('userEdit');
        bank.ajaxForm('#form','<%=basePath %>UserInfo/update',{userid:data.row.userid},function(data){
            if(data=="修改成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
            parent.$('#dataTable').datagrid('reload');
            bank.ajaxMessage(data);
        });
    }
</script>
