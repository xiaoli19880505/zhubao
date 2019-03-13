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
            <a class="icon iconfont icon-icon- green" onclick="saveAdd()"><i>保存</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
        <form method="post" class="easyui-form" id="form">
            <ul class="search-group windowInner">
                <legend>基本信息</legend>
                <li style="width: 380px" class="readonly">
                    <input class="easyui-textbox" name="prSetcode" style="width: 92%" data-options="label:'字典编号:',readonly:true"></li>
                <li style="width:380px"><input class="easyui-textbox" name="prSetName" style="width: 92%" data-options="label:'字典名称:',required:true,validType:['chinese','empty','specialCharacter']"></li>
            </ul>
        </form>
    </div>

</div>
</body>
<script>
    $(function() {
        var data = bank.biography().getParams("dictionaryEdit");
        $("#form").form('load',data.row)
    });

    function saveAdd() {
        var data = bank.biography().getParams("dictionaryEdit");
        bank.ajaxForm('#form','<%=basePath %>parm/uodateParm',{
            id:data.row.id
        },function (data) {
            bank.ajaxMessage(data);
            parent.$('#dataTable').datagrid('reload');
            if(data=="修改成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
        })
    }
</script>

</html>