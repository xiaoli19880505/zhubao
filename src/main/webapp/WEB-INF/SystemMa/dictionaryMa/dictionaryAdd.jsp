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
            <a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
        <form method="post" class="easyui-form" id="form">
            <ul class="search-group windowInner">
                <legend>基本信息</legend>
                <li style="width: 380px"><input class="easyui-textbox" name="prSetcode" style="width: 92%" data-options="label:'字典编号:',required:true,validType:['specialCharacter','empty','canNumber']"></li>
                <li style="width:380px"><input class="easyui-textbox" name="prSetName" style="width: 92%" data-options="label:'字典名称:',required:true,validType:['specialCharacter','chinese','empty']"></li>
            </ul>
        </form>
    </div>

</div>
</body>
</html>
<script>
    function save() {
        bank.ajaxForm("#form",'<%=basePath %>parm/savaParm',{},function(data){
           bank.ajaxMessage(data);
            parent.$('#dataTable').datagrid('reload');
           if(data=="添加成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }

        });
    }
</script>