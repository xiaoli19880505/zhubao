<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>登录</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/common.css"/>
</head>
<body>
	  <!-- 主页面容器 -->
	  <div class="mui-inner-wrap" >
	    <div class="mui-content mui-scroll-wrapper" id="loginBg">
	      <div class="mui-scroll" style="height: 100%;">
	        <!-- 主界面具体展示内容 -->
	         <div class="loginTitle">徐州市住房保障管理系统</div>
	         <form id="form" class="mui-input-group" method="post" onsubmit="return doCheck()" action="<%=basePath %>appLogin/login">
			    <div class="mui-input-row">
			        <label>身份证号</label>
			        <input type="text" id="sfzh" name="sfzh" class="mui-input-clear" >
			    </div>
			    <div class="mui-input-row">
			        <label>密码</label>
			        <input type="password" name="userpwd" id="userpwd" class="mui-input-password">
			    </div>
				 <div class="img mui-input-row">
					 <label ><img src="<%=basePath %>/getCode" id="imgObj" style="margin: -12px -15px"/></label>
					 <input type="text" name="checkcode" id="checkcode" class="mui-input-clear" placeholder=" 验证码" style="width: 58%">
				 </div>
			    <div class="mui-content-padded">
				    <button id='login' class="mui-btn mui-btn-block mui-btn-primary btn">立即登录</button>
				    <div class="link-area" >
				    	   <a id='forgetPwd' class="btn">忘记密码?</a>
				    	   <span class="spliter">|</span> 
				    	   <a id='reg'   class="btn">注册账号</a>
				    </div>
			    </div>
			</form>
		   
	      </div>
	    </div>  
	  </div>
	  <script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	  <script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
	  <script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
	  <script src="<%=basePath %>srcApp/js/public/public.js" type="text/javascript" charset="utf-8"></script>
	  <script>
				mui.init();
                mui(".mui-content-padded").on('tap', '.btn', function(e) {
                    var target = this.getAttribute("id");
                    switch(target) {
                        case 'forgetPwd':
                            weixin.openWindow('forgetPwd','<%=basePath %>appPath/forgetPwd');
                            break;
                        case 'reg':
                            weixin.openWindow('registe','<%=basePath %>appPath/registe');
                            break;
                        case 'login':
                            $("#form").submit();
                            break;
                        default:
                            break;
                    }
                });
				//更换图片
				mui(".img").on('tap','#imgObj',function(){
			        var src =$("#imgObj").attr("src");
			        $("#imgObj").attr("src", chgUrl(src));
			    });
				  function chgUrl(url) {
				        var timestamp = (new Date()).valueOf();
				        var index=url.indexOf("getCode");
				        url = url.substring(0, (index+7));
				        if ((url.indexOf("&") >= 0)) {
				             url = url + "×tamp=" + timestamp;
						} else {
				        	url = url + "?timestamp=" + timestamp;
						}
				            return url;
					}
                  function doCheck(){
                    var sfzh=$.trim($("#sfzh").val());
                    var password=$.trim($("#userpwd").val());
                    var checkcode=$.trim($("#checkcode").val());
                    if((sfzh.length>0)&&(password.length>0)&&(checkcode.length>0)){
                        return true;
                        weixin.setCookie('sfzh',sfzh,24);
                    }else{
                        weixin.alert("输入项出错！");
                        return false;
                    }
                }
                $(function () {
                    if("${message}".length>0){
                        var message="${message}";
                        weixin.toast(message)
                    }
                    var exist=weixin.getCookie('sfzh');
                    if(exist){
                        var sfzh=weixin.getCookie('sfzh');
                        $("#sfzh").val(sfzh);
                    }
                })
	</script>
</body>
</html>

