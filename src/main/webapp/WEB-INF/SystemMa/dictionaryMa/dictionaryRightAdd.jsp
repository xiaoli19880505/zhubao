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
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow('dicTable')"><i>关闭</i></a>
        </div>
        <form method="post" class="easyui-form" id="form">
            <ul class="search-group windowInner">
                <legend>基本信息</legend>
                <li style="width:380px" class="readonly">
                    <input class="easyui-combobox" id="prSetcode" name="prSetcode" style="width: 92%" data-options="label:'字典名称:',readonly:true,valueField:'id',textField:'text'">
                </li>
                <li style="width:380px">
                    <input class="easyui-textbox" name="piItemname" style="width: 92%" data-options="label:'字段名:',required:true,validType:['chinese','empty','specialCharacter']">
                </li>
                <li style="width:380px;display: none" class="lastLi">
                    <input class="easyui-combobox" name="piParentsetcode" id="piParentsetcode" style="width: 92%" data-options="label:'上级字段:',url:'<%=basePath %>ParmItem/getOptions',valueField:'piItemcode',
                    textField:'piItemname',panelHeight:'auto',editable:false,panelMaxHeight:280,onBeforeLoad:function(param){
                    var data=bank.biography().getParams('RightAdd'); param.parmSetcode=data.row.prSetcode}">
                </li>
            </ul>
        </form>
    </div>

</div>
</body>
</html>
<script>
    $(function(){
        var data=bank.biography().getParams('RightAdd');
        if(data.row.prSetcode=="04"||data.row.prSetcode=="05"||data.row.prSetcode=="06"){
            $('.lastLi').show();
            $('#piParentsetcode').combobox({required:true});
        }
        $("#prSetcode").combobox('setValue',{id:data.row.prSetcode,text:data.row.prSetName})
    });
    function saveAdd() {
        var data=bank.biography().getParams('RightAdd');
        bank.ajaxForm("#form",'<%=basePath %>ParmItem/saveParmItem',{parmSetcode:data.row.prSetcode},function(data){
            bank.ajaxMessage(data);
            parent.$('#dataTable').datagrid('reload');
            if(data=="新增成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }

        });
    }
</script>