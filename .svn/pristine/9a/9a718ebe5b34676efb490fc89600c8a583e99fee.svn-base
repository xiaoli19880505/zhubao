<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9">
		<title>图片上传</title>
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
						<li><input class="easyui-textbox" name="imgName" id="imgName" style="width: 92%" data-options="label:'图片名称:'"></li>
						<li class="query-btn">
							<a class="icon iconfont icon-sousuo" onclick="queryTable()"><i>查&nbsp;询</i></a>
						</li>
						<li class="query-btn reset">
							<a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
						</li>
					</ul>
				</form>
				<div class="abtn-group">
					<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>上传</i></a>
					<a class="icon iconfont icon-xiugaiziliao green" onclick="edit()"><i>查看</i></a>
					<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
					<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
					<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
					<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
				<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px" data-options="url:'<%=basePath %>imageInfo/selectFile',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
					<thead>
						<tr>
							<th data-options="field:'imgCode',width:150,align:'center',halign:'center'">图片编码</th>
							<th data-options="field:'imgName',width:300,align:'center',halign:'center'">图片名称</th>
							<th data-options="field:'imgType',width:200,align:'center',halign:'center',formatter:type">图片类型</th>
							<th data-options="field:'imgUrl',width:600,align:'center',halign:'center'">图片url</th>
							<th data-options="field:'createTimeStr',width:200,align:'center',halign:'center'">创建时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>

</html>
<script>
	function type(value) {
		if(value == 1){
		    return '轮播图';
		} else {
            return '二维码';
		}
    }
	//新增
	function add() {
		bank.showWindow('<%=basePath %>pageColumn/photoAdd', "图片上传", 640, 400, true);
	}
    //删除
    function cut() {
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $.messager.confirm('操作提示', '您确定删除此数据吗？', function (r) {
                if (r) {
                    $.ajax({
                        type:"post",
                        url:"<%=basePath %>imageInfo/deleteFile",
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
    //查看
	function edit() {
        var row = $("#dataTable").datagrid("getSelected");
        if (row) {
			window.open(row.imgUrl);
		} else {
            bank.alertMessage("请选中表中某条数据！")
		}
    }
    //模糊查询
    function queryTable() {
        var imgName = $("#imgName").val();
        $("#dataTable").datagrid('reload',{imgName:imgName});
    }
</script>