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
	<script type="text/javascript" src="<%=basePath %>src/js/ie/html5shiv.js"></script>
	<script type="text/javascript" src="<%=basePath %>src/js/ie/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript" src="<%=basePath %>src/js/common.js"></script>
	<link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">

</head>


<body class="easyui-layout login" id="adminPanel" >
<div class="formContainer">
	<div class="form">
		<div class="header" style="font-size: 20px; text-align: center;">
			<h1>徐州市住房保障服务中心</h1>
			<div>
				<span class="left"></span>
				<span>用户登录</span>
				<span class="right"></span>
			</div>
		</div>
		<div class="center">
			<div id="loginform" class="form-vertical" onkeydown="checklogin(event)">
				<form  action="<%=basePath %>login/submitLogin"  method="post"  name="form" onsubmit="return doCheck()">
					<div class="userInput" style="height: 73px">
						<i class="icon iconfont icon-zhanghao black">&nbsp;&nbsp;用户名</i>
						<input id="username" name="username" value="${username}" class="input" type="text" autofocus="autofocus" maxlength="25" style="padding-left: 100px">
					</div>
					<div class="userInput" style="height: 73px">
						<i class="icon iconfont icon-mima black">&nbsp;&nbsp;密码</i>
						<input id="password" name="password" value="" class="input" type="password" maxlength="25" onkeyUp="lenCheck(this);">
					</div>
					<div class="userInput" style="height: 81px">
						<i class="icon iconfont black">&nbsp;&nbsp;验证码</i>
						<input id="check" name="checkcode" class="input" type="text" maxlength="25"   style="margin-bottom: 0">
						<div class="img"><img src="${pageContext.request.contextPath}/getCode" id="imgObj"/></div>
						<a href="#" onclick="change()" class="achange">换一张</a>
						<div class="status red">${message}</div>
					</div>
					<div class="button">
						<input type="submit" value="登  录"  class="btn-login" />
					</div>
				</form>
			</div>

		</div>
	</div>

</div>

</div>
</body>
<script type="text/javascript">
    $(function(){
        var password = "${password}";
        $("#password").attr("type","password");
        $("#password").val(password);

        if(window !=top) {
            top.location.href = location.href;
        }
    });
    function lenCheck(obj){
        if(obj.value.length==0){
            $('#password').get(0).setAttribute("type","text");
        }else{
            $('#password').get(0).setAttribute("type","password");
        }
    }
	//修改方式
	function doCheck(){
			var username=$.trim($("#username").val());
			var password=$.trim($("#password").val());
			var checkcode=$.trim($("#check").val());
        	if((username.length>0)&&(password.length>=6)&&(password.length<=20)&&(checkcode.length>0)){
            return true
        	}else if(password.length<6||password.length>20){
               $(".status").html("密码长度6-20位").addClass("red").removeClass("green");
               return false;
           }else{
               $(".status").html("输入项出错！").addClass("red").removeClass("green");
               return false;
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
	//验证码
</script>
</html>