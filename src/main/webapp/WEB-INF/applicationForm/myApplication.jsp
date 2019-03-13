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
               data-options="url:'',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
            <thead>
            <tr>
                <th data-options="field:'code',width:120,align:'center',halign:'center'">申请类型</th>
                <th data-options="field:'applicationDate',width:150,align:'center',halign:'center'">申请编号</th>
                <th data-options="field:'organization_pathNume',width:200,align:'left',halign:'center'">申请日期</th>
                <th data-options="field:'purchaser_name',width:100,align:'center',halign:'center'">申请人</th>
                <th data-options="field:'purchaseDate',width:150,align:'center',halign:'center'">身份证号</th>
                <th data-options="field:'supplier_name',width:100,align:'left',halign:'center'">所属县区</th>
                <th data-options="field:'invoiceNo',width:100,align:'center',halign:'center'">所属街道</th>
                <th data-options="field:'purpose',width:100,align:'left',halign:'center'">审核节点</th>
                <th data-options="field:'essetNature',width:100,align:'left',halign:'center',formatter:operate">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>

</html>