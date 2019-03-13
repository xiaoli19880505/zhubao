<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div class="easyui-panel p5" style="width: 100%;height:98%;overflow: auto">
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
        <table id="dataTable" class="easyui-datagrid resTable" style="height:88%" data-options="rownumbers:true,pagination:true,
					border:true,striped:true,pagePosition:'bottom',pageSize:10,nowrap:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'itName',width:100,align:'center'">项目名称</th>
                <th data-options="field:'fBuname',width:100,align:'center'">楼号</th>
                <th data-options="field:'fCecode',width:100,align:'center'" >单元号</th>
                <th data-options="field:'fRonum',width:100,align:'center'">房间号</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script>
$(function () {
    var row=bank.biography().getParams("changeRecord");
    $("#dataTable").datagrid({
        url:'<%=basePath %>FactMapping/findFactByApplyid?applyid='+row.row
    })
});
</script>
</html>

