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
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
            <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
        </div>
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
               data-options="url:'<%=basePath %>applyUserinfo/findPerOldData',singleSelect:true,rownumbers:true,pagination:false,border:true,striped:true,
                       pagePosition:'bottom',nowrap:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'APPTYPENAME',width:120,align:'center',halign:'center'">申请类型</th>
                <th data-options="field:'APLBH',width:150,align:'center'">申请编号</th>
                <th data-options="field:'USERNAME',width:150,align:'center'">申请人姓名</th>
                <th data-options="field:'APLDATE',width:100,align:'center'">录入日期</th>
                <th data-options="field:'SFZH',width:200,align:'center'">身份证号</th>
                <th data-options="field:'SSQ',width:100,align:'center'">所属县区</th>
                <th data-options="field:'SSJD',width:100,align:'center'">所属街道</th>
                <th data-options="field:'APPZT',width:100,align:'center',formatter:convertState">状态</th>
                <th data-options="field:'essetNature',width:200,align:'center',formatter:operate">查看明细</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script>
    function operate(value,row,index) {
       var apptype = row.APPTYPE;
       var aplid = row.APPID;
        return '<a class="blue" style="cursor:pointer" onclick="auditOpinion(\''+apptype+'\',\''+aplid +'\')">查看</a>';
    }
    function auditOpinion(apptype,aplid){
        var a = $('<a href="<%=basePath %>task/toApprove?applyType?applyType='+apptype+'&applyId='+aplid+'&chengxin=chengxin#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }

    /*转换状态*/
    function convertState(value) {
        //0已录入1待保障2异议3取消资格4已保障5保障结束
        switch (value){
            case 0:
                return "已录入";
            case 1:
                return "待保障";
            case 2:
                return "异议";
            case 3:
                return "取消资格";
            case 4:
                return "已保障";
            case 5:
                return "保障结束";
        }
    }
</script>
