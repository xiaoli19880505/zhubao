<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9" >
    <title>徐州市住房保障管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>thiemesApply/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>thiemesApply/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApply/css/icon/iconfont.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApply/css/common.css">
    <!--<link href="<%=basePath %>srcApply/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">-->
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/common.js"></script>
</head>

<body class="easyui-layout" id="AllRegion">
<!--顶部-->
<div data-options="region:'north',border:false" class="northRegion">
    <div class="north_left">徐州市住房保障管理系统</div>
    <div class="north_center"></div>
    <div class="north_right">
        <div>
            <a class="icon iconfont white logout" title="退出系统" onclick="logout()">退出</a>
        </div>
        <div><img src="<%=basePath %>src/img/public/user.png" alt="">
            <span>${sessionScope.applyUserinfo.username}</span>
        </div>
    </div>
</div>
<!--顶部结束-->
<!--左侧导航-->
<div data-options="region:'west',title:'导航栏',border:false" class="westRegion">
    <div class="easyui-accordion" border="false" id="leftNav">
        <div class="easyui-tree" id="westTree" data-options="animate:true,method:'get',url:'<%=basePath%>srcApply/js/data.json'"></div>
    </div>
</div>
<!--左侧导航结束-->
<!--中间主体-->
<div data-options="region:'center',border:false" class="centerRegion">
    <div id="AllTabs" class="easyui-tabs" style="width:100%;height: 100%">
        <div title="首页" class="index">
            <iframe src="<%=basePath %>path/towelcome" frameborder="0" scrolling="auto" class="iframe" id="iframe"></iframe>
        </div>
    </div>
</div>
<!--中间主体结束-->
<!--tabs右键tab操作-->
<div id="menu" class="easyui-menu" style="width:200px;display: none">
    <div id="menu-closethis" data-options="name:1">关闭</div>
    <div id="menu-closeall" data-options="name:2">全部关闭</div>
    <div id="menu-closeother" data-options="name:3">关闭其他</div>
    <div id="menu-closeright" data-options="name:4">当前页右侧全部关闭</div>
    <div id="menu-closeleft" data-options="name:5">当前页左侧全部关闭</div>
</div>
</body>
</html>
<script>
    $(function(){
        bank.clickMenu('');
        /*查看我的通知个数*/
        $.post("<%=basePath%>applyUserinfo/getInformCount",{},
        function (data) {
            if(data.count!=0){
                bank.alertMessage("您有"+data.count+"条未读通知，请查看我的通知");
            }
        },"json");
    });
    function logout(){
        $.messager.confirm('操作提示', '您确定退出当前系统吗?', function (r) {
            if (r) {
                window.location.href='<%=basePath %>path/toexit?sx=0';
            } else {
                return false;
            }
        });
    }
</script>
