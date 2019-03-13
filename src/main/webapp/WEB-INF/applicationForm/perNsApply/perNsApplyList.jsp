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
    <div class="over" style="display: none;">
        <div id="loading"><img src="<%=basePath %>srcApply/img/public/loading.gif" ></div>
    </div>
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-xinzeng green" onclick="saveAdd()" id="add"><i>添加年审</i></a>
            <a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
            <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
        </div>
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 90%;min-height: 300px"
               data-options="url:'<%=basePath %>applyns/getPerNsApply',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
            <thead>
            <tr>
                <th data-options="field:'atype',width:180,align:'center',halign:'center',formatter:convenrtApplyType">申请类型</th>
                <th data-options="field:'aplbh',width:120,align:'center'">申请编号</th>
                <th data-options="field:'apldate',width:100,align:'center'">申请日期</th>
                <th data-options="field:'applyUserinfo',width:100,align:'center',formatter:name">申请人</th>
                <th data-options="field:'sfzh',width:160,align:'center',formatter:sfzh">身份证号</th>
                <th data-options="field:'ssq',width:100,align:'center'">所属县区</th>
                <th data-options="field:'ssj',width:100,align:'center'">所属街道</th>
                <th data-options="field:'state',width:100,align:'center',formatter:convertState">审批状态</th>
                <th data-options="field:'processinstanceid',width:100,align:'center',formatter:processinstanceid">审核节点</th>
                <th data-options="field:'essetNature',width:200,align:'center',formatter:operate">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script>
    /*转换业务类别*/
    function convenrtApplyType(value,row,index){
        var apptype = row.atype;
        switch (apptype){
            case "ns2":
                return "公共租赁住房租赁补贴";
            case "ns3":
                return "公共租赁住房（低保、特困家庭）";
            case "ns4":
                return "公共租赁住房（外来务工人员）";
            case "ns5":
                return "公共租赁住房（新就业人员）";
        }
    }
    function operate(value,row,index) {
        var state = row.state;
        var apptype = row.aptype;
        var aplid = row.aplid;
        var bool = -1;
        if(state!=null){
            bool=state.indexOf('打回修改');
        }

        if(bool!=-1){
            return '<a class="ablue" onclick="auditOpinion(\''+apptype+'\',\''+aplid +'\')">修改</a>'
                +'<a class="ablue" onclick="queryDetail(\''+apptype+'\',\''+aplid +'\')">查看</a>';
        }else{
            return '<a class="ablue" onclick="queryDetail(\''+apptype+'\',\''+aplid +'\')">查看</a>';
        }
    }
    function auditOpinion(apptype,aplid){
        var a = $('<a href="<%=basePath %>applyns/toUpdateNsApply?applyType='+apptype+'&applyId='+aplid+'#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
    function queryDetail(apptype,aplid){
        var a = $('<a href="<%=basePath %>appove/toNsAudit?applyType='+apptype+'&applyId='+aplid+'&chengxin=chengxin#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
    function saveAdd(){
       /* $('.over').show();
        $.ajax({
            type:"post",
            url:"<%=basePath %>appove/cangAddNs",
            async:true,
            success:function (data) {
                $('.over').hide();
                if(data == "success"){
                    var type = "${applyType}";
                    var applyid = "${applyid}";
                    var a = $('<a href="<%=basePath %>applyns/toAddNsApply?applyType='+type+'&applyid='+applyid+'#true" target="_blank"></a>')[0];
                    var e = document.createEvent('MouseEvents');
                    e.initEvent('click', true, true);
                    a.dispatchEvent(e);
                }else{
                    bank.ajaxMessage(data);
                }
            }
        });*/

        var type = "${applyType}";
        var applyid = "${applyid}";
        var a = $('<a href="<%=basePath %>applyns/toAddNsApply?applyType='+type+'&applyid='+applyid+'#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
    function applyType(value,row,index) {
        return row.applyType.piItemname;
    }
    function name(value,row,index) {
        return row.applyUserinfo.username;
    }
    function sfzh(value,row,index) {
        return row.applyUserinfo.sfzh;
    }
    function ssq(value,row,index) {
        return row.applyUserinfo.parmItemssq.piItemname;
    }
    function ssjd(value,row,index) {
        return row.applyUserinfo.parmItemssjd.piItemname;
    }
    function processinstanceid(value,row,index) {
        return row.processinstanceid;
    }
    function  convertState(value,row,index) {
        if(value=='' || value==null){
            return "审核中";
        }else if(value.indexOf("审批通过")!=-1){
            return "审核中";
        }else{
            return value;
        }
    }
    $(function () {
        $("#dataTable").datagrid("reload");
        var type = "${applyType}";
        var haveHouse = "${haveHouse}";
        if(type == ""){
            $("#add").show();
            //bank.ajaxMessage("您尚未通过初审流程");
        }else if(haveHouse=="0" && type!=2){
            $("#add").show();
            //bank.ajaxMessage("尚未分房，暂时不可申请年审");
        }else{

        }
    })
</script>
