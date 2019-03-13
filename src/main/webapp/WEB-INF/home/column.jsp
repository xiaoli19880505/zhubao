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
		<title>栏目内容信息</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/icon/iconfont.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/common.css">
		<script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/home/common.js"></script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
			<div class="easyui-panel" style="width: 100%;overflow: hidden">
				<form id="queryForm">
					<ul class="search-group">
						<li><input class="easyui-textbox" name="columnName" id="columnName" style="width: 92%" data-options="label:'栏目名称:'"></li>
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
					<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
					<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
					<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
				<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px" data-options="url:'<%=basePath %>columnInfo/selectColumnInfo',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
					<thead>
						<tr>
							<th data-options="field:'columnCode',width:200,align:'center',halign:'center'">栏目编码</th>
							<th data-options="field:'columnName',width:200,align:'center',halign:'center'">栏目名称</th>
							<th data-options="field:'columnLevel',width:200,align:'center',halign:'center'">级别</th>
							<th data-options="field:'downFlag',width:200,align:'center',halign:'center',formatter:flagName">下载中心</th>
							<th data-options="field:'sequence',width:200,align:'center',halign:'center'">栏目排序位置</th>
							<th data-options="field:'createTimeStr',width:200,align:'center',halign:'center'">创建时间</th>
							<th data-options="field:'createPerson',width:200,align:'center',halign:'center'">创建人</th>
							<th data-options="field:'updateTimeStr',width:200,align:'center',halign:'center'">修改时间</th>
							<th data-options="field:'updatePerson',width:200,align:'center',halign:'center'">修改人</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>

</html>
<script>
	//格式化
    function flagName(row,val) {
		if (val.downFlag == "T") {
            return "是"
		}else{
            return "否"
		}
    }
	//新增
	function add() {
		bank.showWindow('<%=basePath %>pageColumn/columnAdd', "栏目内容信息-新增", 540, 350, true);
	}
	//修改
	function edit() {
		var row = $("#dataTable").datagrid("getSelected");
		bank.biography().setParams({row: row, title: 'columnEdit'});
		if(row) {
			bank.showWindow('<%=basePath %>pageColumn/columnEdit', "栏目内容信息-修改", 540, 350, true);
		} else {
			bank.alertMessage("请选中表中某条数据！")
		}
	}
    //删除
    function cut() {
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $.messager.confirm('操作提示', '您确定删除该数据吗？', function (r) {
                if (r) {
                    $.ajax({
                        type:"post",
                        url:"<%=basePath %>columnInfo/deleteById",
                        data:{
                            id:row.id
                        },
                        success:function (data) {
                            bank.ajaxMessage(data);
                            $("#dataTable").datagrid('reload');
                        },
                        error:function (data) {
                            bank.ajaxMessage(data);
                        }
                    });
                }
            });
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
    }
    //模糊查询
	function queryTable() {
		var columnName = $("#columnName").val();
        $("#dataTable").datagrid('reload',{columnName:columnName});
    }
</script>