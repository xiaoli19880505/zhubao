<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <title>徐州市住房保障管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>themes/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/icon/iconfont.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/common.css">
    <script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/common.js"></script>
    <link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:'false'">
    <form class="changeForm" id="changeForm" method="post">
        <div class="formItem">
            <input class="easyui-textbox" id="oldpwd" name="oldPass" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>原始密码:',required:true,validType:['checkLength[6,20]']" >
        </div>
        <div class="formItem">
            <input class="easyui-textbox" id="newPwd" name="newPass" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>新密码:',required:true,validType:['checkLength[6,20]']">
        </div>
        <div class="formItem">
            <input class="easyui-textbox" id="repeatPwd" name="repeatNewPass" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>确认新密码:',required:true,validType:['equalTo[\'#newPwd\']']">
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
    .changeForm {
        padding-top: 50px;
        margin-left: 36px;
    }
</style>
</html>
<script>
    function save(){
        bank.ajaxForm('.changeForm','<%=basePath%>UserInfo/updatePerPass',{},function(data){
            bank.ajaxMessage(data);
            if(data=="修改成功"){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
            }
            });
    }
</script>
