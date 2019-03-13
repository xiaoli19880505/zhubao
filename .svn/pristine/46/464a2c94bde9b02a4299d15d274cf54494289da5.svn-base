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
	<!--[if lte IE 9]>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/ie.css">
	<![endif]-->
	<script type="text/javascript" src="<%=basePath %>src/js/common.js"></script>
	<link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">
</head>
<body class="easyui-layout login" id="loginPanel" >
<div class="formContainer">
<div class="form" style="height: 480px">
	<div class="header" style="font-size: 20px; text-align: center;">
		<h1>徐州市住房保障服务中心</h1>
		<div>
			<span class="left"></span>
			<span>用户登录</span>
			<span class="right"></span>
		</div>
	</div>
	<div class="center">
		<div id="loginform" class="form-vertical" onkeydown="checklogin(event)" >
			<form action="<%=basePath %>applyUserinfo/loadMenu" method="post" onsubmit="return doCheck()">
				<div class="userInput">
					<i class="icon iconfont icon-zhanghao black">&nbsp;&nbsp;身份证号</i>
					<input id="sfzh" name="sfzh"  value="${sfzh}" class="input" type="text" autofocus="autofocus" style="padding-left: 100px">
				</div>
				<div class="userInput">
					<i class="icon iconfont icon-mima black">&nbsp;&nbsp;密码</i>
					<input id="password" name="userpwd" value="${userpwd}" class="input" type="text"   onkeyUp="lenCheck(this);">
				</div>
				<div class="userInput">
					<i class="icon iconfont black">&nbsp;&nbsp;验证码</i>
					<input id="check" name="checkcode" class="input" type="text"  style="margin-bottom: 0">
					<div class="img"><img src="${pageContext.request.contextPath}/getCode" id="imgObj"/></div>
					<a href="#" onclick="change()">换一张</a>
					<div class="status red" style="text-align: left;height: 26px;margin-left: 50px">${message}</div>
				</div>
				<div class="button">
					<input type="submit" value="登  录"  class="btn-login" />
					 <p class="forgetp">
						 <a onclick="forgetPwd()" style="color: #3e7cc4;cursor: pointer">忘记密码?</a>
						 <a href="<%=basePath %>/pageColumn/external/h_index" style="float: right;color: #3e7cc4;cursor: pointer;margin-right: 50px">返回首页</a>
					 </p>
				</div>
			</form>
		</div>
	</div>
</div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        var password = "${userpwd}";
        $("#password").attr("type","password");
        $("#password").val(password);
        if(window !=top) {
            top.location.href = location.href;
        }

        });
    function doCheck(){
        var sfzh=$.trim($("#sfzh").val());
        var password=$.trim($("#password").val());
        var checkcode=$.trim($("#check").val());
        if((sfzh.length>0)&&(password.length>=6)&&(password.length<=20)&&(checkcode.length>0)){
            return true
        }else if(password.length<6||password.length>20){
            $(".status").html("密码长度6-20位").addClass("red").removeClass("green");
            return false;
		}else{
            $(".status").html("输入项出错！").addClass("red").removeClass("green");
            return false;
        }
	}
    function lenCheck(obj){
        if(obj.value.length==0){
            $('#password').get(0).setAttribute("type","text");
        }else{
            $('#password').get(0).setAttribute("type","password");
        }
    }
    function checklogin(e){
        var e = e || window.event;
        var key = window.event ? e.keyCode : e.which;
        if(key==13){
            submit();
        }else if(key==9){
        }
        else{
            return false;
        }
    }
    //更换图片
    function change() {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
	}
	// 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
   function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 21);
        if ((url.indexOf("&") >= 0)) {
             url = url + "×tamp=" + timestamp;
		} else {
        	url = url + "?timestamp=" + timestamp;
		}
            return url;
	}
    function forgetPwd(){
        var a = $('<a href="<%=basePath%>path/toforgetPwd" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);

	}
</script>
</html>