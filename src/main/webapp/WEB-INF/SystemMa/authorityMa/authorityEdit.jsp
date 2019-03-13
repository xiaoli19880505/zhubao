<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div class="easyui-panel windowPanel">
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-icon- green" onclick="saveEdit()"><i>保存</i></a>
            <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
        <form method="post" class="easyui-form" id="form">
            <ul class="search-group windowInner">
                <legend>基本信息</legend>
                <li style="width: 380px"><input class="easyui-textbox" name="qxName" style="width: 92%" data-options="label:'操作名称:',required:true,validType:['empty','chinese','specialCharacter']"></li>
                <li class="readonly" style="width: 380px">
                    <input class="easyui-combobox" name="meId" id="meId" style="width: 92%" data-options="label:'所属菜单:',readonly:true,valueField:'id',textField:'text'">
                </li>
                <li style="width: 380px"><input class="easyui-textbox" name="qxCode" style="width: 92%" data-options="label:'权限编码:',required:true,validType:['specialCharacter']"></li>
                <li style="width: 380px"><input class="easyui-textbox" name="qxDesc"  style="width: 92%;height: 100px" data-options="label:'描述:',multiline:true"></li>
            </ul>
        </form>
    </div>

</div>
</body>
<script>
    $(function() {
        var data = bank.biography().getParams("authorityEdit");
        $('#form').form('load',data.row);
        $("#meId").combobox('setValue',{id:data.row.qxMeid.meId,text:data.row.qxMeid.meName});
    });

    function saveEdit() {
        var data = bank.biography().getParams("authorityEdit");
        bank.ajaxForm('#form','<%=basePath %>qxInfo/updateQxInfo',{qxId:data.row.qxId},function (data) {
            bank.ajaxMessage(data);
            parent.$('#dataTable').datagrid('reload');
            parent.$('#tree').tree('reload');
            if(data=="修改成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
        })
    }
</script>

</html>