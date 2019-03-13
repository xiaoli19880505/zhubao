<%@ page import="com.sys.pojo.UserInfo" %>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<body class="easyui-layout" id="AllRegion">
<!--顶部-->
<div data-options="region:'north',border:false" class="northRegion">
    <div class="north_left">徐州市住房保障管理系统</div>
    <div class="north_center">
        <span style="display: inline-block;float: left;margin-left: 4%">当前登录:
            <c:choose>
                <c:when test="${requestScope.isShi!=null}">
                    ${area}
                </c:when>
                <c:when test="${sessionScope.user.parmItemssjd!=null}">
                    ${sessionScope.user.parmItemssq.piItemname}${sessionScope.user.parmItemssjd.piItemname}${area}
                </c:when>
                <c:otherwise>
                    ${sessionScope.user.parmItemssq.piItemname}${area}
                </c:otherwise>
            </c:choose>&nbsp;&nbsp;
        </span>
        <c:choose>
            <c:when test="${overTimeMap.csCount!=null and overTimeMap.nsCount!=null}">
                <span class="red"><marquee behavior="scroll" direction="left">您有${overTimeMap.csCount}条待审批申请单和${overTimeMap.nsCount}条待审批年审单已超时，请及时处理</marquee></span>
            </c:when>
            <c:when test="${overTimeMap.csCount!=null}">
                <span class="red"><marquee direction="left" behavior="scroll" scrollamount="1" scrolldelay="0" loop="-1" width="400" height="50" bgcolor="#006BA0" hspace="10" vspace="10">您有${overTimeMap.csCount}条待审批申请单已超时，请及时处理</marquee></span>
            </c:when>
            <c:when test="${overTimeMap.nsCount!=null}">
                <span class="red">您有${overTimeMap.nsCount}条待审批年审单已超时，请及时处理</span>
            </c:when>
        </c:choose>
    </div>
    <div class="north_right">
        <div>
            <a class="icon iconfont white logout" title="退出系统" onclick="logout()">退出</a>
        </div>
        <div onclick="updatePwd()">
            <a class="icon iconfont white logout" title="修改密码" style="width: 60px">修改密码</a>
        </div>
        <div><img src="<%=basePath %>src/img/public/user.png" alt="">
            <span>${sessionScope.user.username}</span>
        </div>
    </div>
</div>
<!--顶部结束-->
<!--左侧导航-->
<div data-options="region:'west',title:'导航栏',border:false" class="westRegion">
    <div class="easyui-accordion" border="false" id="leftNav">
        <ul class="easyui-tree" id="westTree" data-options="animate:true,url:'<%=basePath %>menuInfo/loadMenu'"></ul>
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
        bank.clickMenu('<%=basePath %>');

        var csCount="${overTimeMap.csCount}";
        var nsCount="${overTimeMap.nsCount}";
        if(csCount!='' && nsCount!=''){
            bank.alertMessage("您有"+csCount+"条待审批申请单和"+nsCount+"条待审批年审单已超时，请及时处理")
        }else if(csCount!=''){
            bank.alertMessage("您有"+csCount+"条待审批申请单，请及时处理");
        }else if(nsCount!=''){
            bank.alertMessage("您有"+nsCount+"条待审批年审单，请及时处理");
        }
    });
    function logout(){
        $.messager.confirm('操作提示', '您确定退出当前系统吗?', function (r) {
            if (r) {
                window.location.href='<%=basePath %>path/toexit?sx=1';
            } else {
                return false;
            }
        });
    }
    function updatePwd(){
        bank.showWindow('<%=basePath %>path/toChangePwd',"修改密码", 500, 320, true);
    }
</script>
