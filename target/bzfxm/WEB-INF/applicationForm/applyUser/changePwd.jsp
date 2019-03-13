<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <title>系统管理-修改密码</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>thiemesApply/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApply/css/common.css">
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/common.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:'false'">
    <form class="changeForm" id="changeForm" method="post">
        <div class="formItem">
            <input class="easyui-textbox" id="oldpwd" name="oldpwd" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>原始密码:',required:true,validType:['checkLength[6,20]']">
        </div>
        <div class="formItem">
            <input class="easyui-textbox" id="newPwd" name="newPwd" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>新密码:',required:true,validType:['checkLength[6,20]']">
        </div>
        <div class="formItem">
            <input class="easyui-textbox" id="repeatPwd" name="repeatPwd" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>确认新密码:',required:true,validType:['equalTo[\'#newPwd\']']">
        </div>
        <div class="formItem">
            <a class="query-btn submit" style="margin-left: 80px" onclick="save()">保存</a>
        </div>
    </form>
</div>
</body>
<style>
    .textbox-label {
        width: 120px;
    }
</style>
<script>
    function save(){
        bank.ajaxForm('.changeForm','<%=basePath%>applyUserinfo/updatePwd',{}, function(data){
                bank.ajaxMessage(data);
        });
    }
</script>
</html>
