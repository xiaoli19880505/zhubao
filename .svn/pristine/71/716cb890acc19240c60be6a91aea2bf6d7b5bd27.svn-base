<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'',singleSelect:true,rownumbers:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:10,nowrap:true,fitColumns:true">
			<thead>
			<tr>
				<th data-options="field:'nodename',width:120,align:'center',halign:'center'">审批节点</th>
				<th data-options="field:'comment',width:150,align:'center',halign:'center'">审批意见</th>
				<th data-options="field:'approveUser',width:200,align:'center',halign:'center'">审批人</th>
				<th data-options="field:'commentDate',width:100,align:'center',halign:'center'">审批日期</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
	$(function () {
	    var data=bank.biography().getParams('auditOpion');
	    var processInstanceId=data.row.processInstanceId;
        $('#dataTable').datagrid({
			url:"<%=basePath %>task/listComment",
            queryParams: {
                processInstanceId: processInstanceId
            }
        });
    });
</script>
</html>