<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <title>系统管理-我的申请</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>thiemesApply/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApply/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/icon/iconfont.css">
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/common.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <ul class="search-btnGroup">
            <div class="abtn-group btnInner">
                <a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
                <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
                <a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
                <div class="query-tab-right" style="width: 100% ;text-align: center;display: inline-block">
                    <a class="query-cur all" data-id="1" onclick="allRed()">全部通知</a> <a data-id="2" class="noread" onclick="noRead()">未读通知</a>
                </div>
            </div>
        </ul>
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px;"
               data-options="url:'<%=basePath %>InfoForm/findAllFormByUid',singleSelect:true,rownumbers:false,pagination:true,border:false,striped:false,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
            <thead>
            <tr>
                <th data-options="field:'uifBt',width:450,align:'center',halign:'center'">通知内容</th>
                <th data-options="field:'uiFxfrq',width:300,align:'center',halign:'center'">下发日期</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script>
   function allRed() {
      $("#dataTable").datagrid('load',{
           uifRead:1
       })
   }
   function noRead() {
       $("#dataTable").datagrid('load',{
           uifRead:0
       })
   }
</script>
