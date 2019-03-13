<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
	<title>文章内容信息</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/icon/iconfont.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/common.css">
	<script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>src/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>src/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>src/js/common.js"></script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
	<div class="easyui-panel" style="width: 100%;overflow: hidden">
		<form id="queryForm">
			<ul class="search-group">
			<li>
					<select class="easyui-combogrid" id="columnInfoId" style="width: 92%" data-options="url:'<%=basePath%>columnInfo/selectColumnInfo',label:'栏目名称:',editable:false,
                         panelWidth:'175',panelHeight:'auto',idField: 'id',textField: 'columnName',columns:[[{field:'columnName',title:'栏目名称',width:167,align:'center'}]]">
					</select>
				</li>
				<li><input class="easyui-textbox" name="columnName" id="article" style="width: 92%" data-options="label:'文章名称:'"></li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="queryTable()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset">
					<a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
				</li>
			</ul>
		</form>
		<div class="abtn-group">
			<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>新增</i></a>
			<a class="icon iconfont icon-xiugaiziliao green" onclick="edit()"><i>修改</i></a>
			<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
			<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
			<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
			<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
		</div>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px" data-options="url:'<%=basePath %>articleInfo/selectArticleInfoes',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
			<thead>
			<tr>
				<th data-options="field:'columnInfo',width:200,align:'center',halign:'center',formatter:formatName">栏目名称</th>
				<th data-options="field:'articleName',width:200,align:'center',halign:'center'">文章名称</th>
				<th data-options="field:'author',width:200,align:'center',halign:'center'">作者</th>
				<th data-options="field:'createTimeStr',width:150,align:'center',halign:'center'">创建时间</th>
				<th data-options="field:'createPerson',width:100,align:'center',halign:'center'">创建人</th>
				<th data-options="field:'updateTimeStr',width:150,align:'center',halign:'center'">修改时间</th>
				<th data-options="field:'updatePerson',width:100,align:'center',halign:'center'">修改人</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>

</html>
<script>
    //新增
    function add() {
        bank.showWindow('<%=basePath %>pageColumn/textAdd', "文章-新增", 1000, 600, true);
    }
    //修改
    function edit() {
        var row = $("#dataTable").datagrid("getSelected");
        bank.biography().setParams({
            row: row,
            title: 'textEdit'
        });
        if(row) {
            bank.showWindow('<%=basePath %>pageColumn/textEdit', "文章-修改", 1000, 600, true);
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
    }
    //删除
    function cut() {
        var row = $("#dataTable").datagrid("getSelected");
        var id = row.id;
        if(row) {
            //ajax
            $.ajax({
                type:"get",
                url:"<%=basePath %>articleInfo/deleteArticleInfo",
                async:true,
                data:{
                    id:id
                },
                success:function (data) {
                    bank.ajaxMessage(data);
                    $("#dataTable").datagrid('reload');
                },
                error:function (data) {
                    bank.ajaxMessage(data);
                }
            });
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
    }
    //模糊查询
    function queryTable() {
        $("#dataTable").datagrid('load',{
            articleName:$.trim($("#article").val()),
            columnInfoId:$.trim($("#columnInfoId").combobox('getValue'))
        });
    }
    function  formatName(row) {
		return row.columnName;
    }
</script>