<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden;">
	<div class="easyui-panel">
		<form id="queryForm" target="_blank" method="post" action="<%=basePath %>file/getFile">
			<ul class="search-group">
				<li><input class="easyui-textbox" id="ctName" style="width: 92%" data-options="label:'合同名称:'"></li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset">
					<input style="display: none" id="pama" name="filePath">
					<a class="icon iconfont icon-xiazai" onclick="fileDownload()"><i>下&nbsp;载</i></a>
				</li>
			</ul>
		</form>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid" style="height: 100%;min-height: 300px" data-options="url:'<%=basePath %>contractTemplate/selectContractTemplate',singleSelect:true,rownumbers:true,fitColumns:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
			<thead>
			<tr>
				<th data-options="field:'ctCode',width:100,align:'center',halign:'center'">合同编码 </th>
				<th data-options="field:'ctName',width:600,align:'center',halign:'center'">合同名称 </th>
				<th data-options="field:'createPerson',width:100,align:'center',halign:'center'">创建人 </th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>

</html>
<script>
    function query(){
        $("#dataTable").datagrid('load',{
            ctName:$.trim($("#ctName").val())
        })
    }
    function fileDownload() {
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $("#pama").attr("value", row.ctUrl);
            $('#queryForm').form('submit');
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
    }
</script>