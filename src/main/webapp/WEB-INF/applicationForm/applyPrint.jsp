<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
    <div class="easyui-panel" style="width: 100%;overflow: hidden">
        <form id="queryForm">
            <ul class="search-group">
                <li>
                    <select class="easyui-combobox combobox-f combo-f textbox-f" id="aptype" data-options="label:'申请类型',panelHeight:'auto',panelMaxHeight:'400',editable:false,
                valueField:'id',textField:'text'" style="width: 92%; display: none;">
                        <option value="">全部</option>
                        <option value="1">经济适用住房</option>
                        <option value="2">公共租赁住房租赁补贴</option>
                        <option value="3">公共租赁住房（低保、特困家庭）</option>
                        <option value="4">公共租赁住房（外来务工人员）</option>
                        <option value="5">公共租赁住房（新就业人员）</option>
                    </select>
                </li>
                <li><input class="easyui-textbox" name="" id="xm" style="width: 92%" data-options="label:'姓名:'"></li>
                <li><input class="easyui-textbox" name="" id="aplbh" style="width: 92%" data-options="label:'申请单号:'"></li>
                <li class="query-btn">
                    <a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
                </li>
                <li class="query-btn reset" >
                    <a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
                </li>
            </ul>
        </form>
    </div>
</div>
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
               data-options="url:'<%=basePath%>appove/findApplyForApprove',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'aplbh',width:150,align:'center',halign:'center'">申请编号</th>
                <th data-options="field:'xm',width:100,align:'center',halign:'center'">申请人</th>
                <th data-options="field:'sfzh',width:150,align:'center',halign:'center'">身份证号</th>
                <th data-options="field:'ssqStr',width:100,align:'center',halign:'center'">所属县区</th>
                <th data-options="field:'ssjStr',width:100,align:'center',halign:'center'">所属街道</th>
                <th data-options="field:'atype',width:100,align:'center',halign:'center',formatter:convenrtApplyType">申请类型</th>
                <th data-options="field:'essetNature',width:200,align:'center',halign:'center',formatter:operate">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script>
    //格式化
    /*function applicationDate(val,row){
        return row.approve.apldate;
    }*/
    //查询
    function query(){
        $("#dataTable").datagrid('load',{
            aptype :$.trim($("#aptype").val()),
            aplbh:$.trim($("#aplbh").val()),
            xm:$.trim($("#xm").val())
        })
    }
    //操作
    function operate(value,row,index) {
        var aptype = row.aptype;
        var aplid =  row.aplid;
        return '<a class="ablue" onclick="view(\''+aptype+'\',\''+aplid+'\')">查看</a>';
    }
    function view(aptype,aplid) {
        var a = $('<a href="<%=basePath %>appove/toApplyForApprove?aptype='+aptype+'&aplid='+aplid+'" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }

    /*转换业务类别*/
    function convenrtApplyType(value,row,index){
        var apptype = ""+value;
        switch (apptype){
            case "经济适用房":
                return "经济适用住房";
            case "补贴":
                return "公共租赁住房租赁补贴";
            case "公租房":
                return "公共租赁住房（低保、特困家庭）";
            case "外来务工":
                return "公共租赁住房（外来务工人员）";
            case "新就业":
                return "公共租赁住房（新就业人员）";
        }
    }
</script>
</html>