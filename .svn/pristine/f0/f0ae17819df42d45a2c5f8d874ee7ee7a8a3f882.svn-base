<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
	<title>栏目内容信息-修改</title>
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

<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<div class="easyui-panel windowPanel">
		<div class="abtn-group btnInner">
			<a class="icon iconfont icon-xinzeng green" onclick="bank.clearForm('form')"><i>重置</i></a>
			<a class="icon iconfont icon-icon- green" onclick="saveAdd()"><i>保存</i></a>
			<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
		</div>
		<form method="post" id="form" class="easyui-form">
			<ul class="search-group windowInner">
				<legend>基本信息</legend>
				<li style="width: 370px"><input class="easyui-textbox" name="columnCode" id="columnCode" style="width: 92%" data-options="label:'栏目编码:',required:true,validType:['empty']"></li>
				<li style="width: 370px"><input class="easyui-textbox" name="columnName" id="columnName" style="width: 92%" data-options="label:'栏目名称:',required:true,validType:['empty']"></li>
				<li style="width: 370px"><input class="easyui-textbox" name="sequence" id="sequence" style="width: 92%" data-options="label:'栏目排序位置:',required:true,validType:['empty']"></li>
				<li style="width: 370px">
					<select class="easyui-combobox" name="downFlag" id="downFlag" style="width: 92%" data-options="label:'下载中心:',required:true,validType:['empty']">
						<option value="T">是</option>
						<option value="F">否</option>
					</select>
				</li>
			</ul>
		</form>
	</div>

</div>
<style>
	.textbox-label {
		width: 100px;
	}
</style>
<script>
	//数据回显
    var data=bank.biography().getParams("columnEdit");
	$(function () {
        $("#columnCode").textbox('setValue',data.row.columnCode);
        $("#columnName").textbox('setValue',data.row.columnName);
        $("#sequence").textbox('setValue',data.row.sequence);
        $("#downFlag").combobox('setValue',data.row.downFlag);
    });
    function saveAdd() {
        bank.ajaxForm('#form','<%=basePath %>columnInfo/updateColumnInfo',{id:data.row.id},function(data){
            bank.ajaxMessage(data);
            if(data=="操作成功!"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
            parent.$('#dataTable').datagrid('reload');
		});
    }
</script>
</body>
</html>

