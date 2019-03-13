<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>注册</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/mobile.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/common.css"/>
</head>
<body>
	  <!-- 主页面容器 -->
	  <div class="mui-inner-wrap" >
	    <div class="mui-content mui-scroll-wrapper" id="loginBg">
	      <div class="mui-scroll" style="height: 100%;" >
	        <!-- 主界面具体展示内容 -->
	         <div class="loginTitle">徐州市住房保障管理系统</div>
			  <div class="stepcon">
				  <div class="step0">
					  <form class="mui-input-group" id="changeForm" method="post">
						  <div class="mui-input-row" >
							  <label>姓名</label>
							  <input type="text" name="username" id="username"  class="mui-input-clear" required>
						  </div>
						  <div class="mui-input-row">
							  <label>身份证号</label>
							  <input type="text" name="sfzh"  id="sfzh" class="mui-input-clear" required>
						  </div>
						  <div class="mui-input-row">
							  <label>手机号</label>
							  <input type="text" name="linktel" id="linktel" class="mui-input-clear" required>
						  </div>
						  <div class="mui-content-padded">
							  <button  class="mui-btn mui-btn-block mui-btn-primary" id="next1">下一步</button>
						  </div>
					  </form>
				  </div>
				  <div class="step1" style="display: none">
					  <form class="mui-input-group" id="confirmForm" method="post">
						  <div class="mui-input-row">
							  <label>姓名</label>
							  <input type="text" name="username" id="username2"  disabled>
						  </div>
						  <div class="mui-input-row">
							  <label>身份证号</label>
							  <input type="text" name="sfzh"  id="sfzh2"  disabled>
						  </div>
						  <div class="mui-input-row">
							  <label>手机号</label>
							  <input type="text" name="linktel"  id="linktel2" disabled>
						  </div>
						  <div class="mui-input-row mui-left">
							  <label style="float: right" class="code">短信验证码</label>
							  <input type="text" name="checkcode" id="checkcode" class="mui-input-clear" required placeholder="请输入验证码" style="width: 50%">
						  </div>
						  <div class="mui-button-row">
							  <button type="button" class="mui-btn mui-btn-primary" id="prev0">上一步</button>
							  <button type="button" class="mui-btn mui-btn-success" id="next2" >下一步</button>
						  </div>
					  </form>
				  </div>
				  <div class="step2" style="display: none">
					  <form class="mui-input-group" id="pwdForm" method="post">
						  <div class="mui-input-row">
							  <label>姓名</label>
							  <input type="text" name="username" id="username3"   disabled>
						  </div>
						  <div class="mui-input-row">
							  <label>新密码</label>
							  <input type="password" id="newPwd" name="newPwd" class="mui-input-password" >
						  </div>
						  <div class="mui-input-row">
							  <label>确认密码</label>
							  <input type="password" id="repeatPwd" name="repeatPwd" class="mui-input-password" >
						  </div>
						  <div class="mui-button-row">
							  <button type="button" class="mui-btn mui-btn-primary" id="prev1">上一步</button>
							  <button type="button" class="mui-btn mui-btn-success" id="save" >提交</button>
						  </div>
					  </form>
				  </div>
			  </div>
	      </div>
	    </div>  
	  </div>
	  <script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript" ></script>
	  <script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
	  <script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
	  <script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.mobile.js" type="text/javascript" ></script>
	  <script src="<%=basePath %>srcApp/js/webuploader/webuploader.js" type="text/javascript" ></script>
	  <script src="<%=basePath %>srcApp/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
	  <script src="<%=basePath %>srcApp/js/public/public.js"></script>
	<script>
		mui.init();
        //倒计时60秒
        $(function () {
            clearSession();
        });
        var flag=false;
        var user={};
        var timer=null;
        mui(document.body).on('tap', '.mui-btn', function (e) {
            var id = this.getAttribute("id");
            var code = id.substring(4);
            if (id=="next1") {
                firstStep();
            }
            if(id=="next2"){
                secondStep();
			}
            if (id.indexOf("prev") != -1) {
                $('.step' + code + '').show().siblings().hide();
            }
            if(id=="save"){
                save()
            }
        });
        mui(".mui-input-row").on('tap', '.code', function() {
            sendCode();
        });
		//上一步
        //点击下一步验证信息
        function firstStep(){
            var flag=false;
            var sfzh=$.trim($("#sfzh").val());
            var linktel=$.trim($("#linktel").val());
            var username=$.trim($("#username").val());
            $('#changeForm').form('submit', {
                url:'<%=basePath %>applyUserinfo/selectAllApp',
                onSubmit: function(param){
                    if(username.length==0||sfzh.length==0||linktel.length==0){
                        weixin.toast("所有输入项不能为空");
                        return false
                    }else if(!weixin.validType().isIdCard(sfzh)){
                        weixin.toast("身份证格式错误");
                        return false
                    }else if(!weixin.validType().isPhone(linktel)){
                        weixin.toast("手机格式错误");
                        return false
                    }else{
                        flag=true
                    }
                    return flag;
                },
                success:function(data){
                    var data=JSON.parse(data);
                    if(data.length>0){
                        $('.step1').show().siblings().hide();
                        user=data[0];
                        flag=true;
                        //回填信息
                        $("#username2").val(data[0].username);
                        $("#sfzh2").val(data[0].sfzh);
                        $("#linktel2").val(data[0].linktel);
                    }else{
                        weixin.alert("身份信息不匹配")
                    }
                },
                error:function(){
                    weixin.alert("数据库连接失败，请稍后再试！");
                    return false;
                }
            },'json');
        }

        //设置倒计时
        function setCode(){
            mui(".mui-input-row").off('tap', '.code', function() {});
            clearInterval(timer);
            var time=60;
            timer=setInterval(function(){
                if(time==0){
                    $(".code").html('获取验证码');
                    mui(".mui-input-row").on('tap', '.code', function() {sendCode();});
                    clearInterval(timer);//这句话至关重要
                    return false;
                }else {
                    time--;
                    $(".code").html('倒计时'+time+'');
                }
            },1000);
        }
        //发送验证码
        function sendCode(){
            var linktel=$.trim($("#linktel2").val());
            if(linktel.length==0){
                weixin.alert("请输入手机号")
            }else{
                setCode();
                $.ajax({
                    url:'<%=basePath %>applyUserinfo/SendCode',
                    type:'post',
                    data:{
                        linktel:$.trim($("#linktel2").val())
                    },
                    success:function(data){
                    },error:function(){
                        weixin.alert("数据库连接失败，请稍后再试")
                    }
                })
            }
        }
        //清空后台session
        function clearSession(){
            $.ajax({
                url:'<%=basePath %>applyUserinfo/newSend',
                type:'post',
                data:null,
                success:function (data) {
                    if(data=="刷新"){
                        clearInterval(timer);
                        $(".code").html('获取验证码');
                    }
                },
                error:function () {
                    weixin.alert("数据库连接失败，稍后再试");
                }
            })
        }
        //点击第二步
        function secondStep(){
            weixin.ajaxForm('#confirmForm','<%=basePath %>applyUserinfo/submitCode',{},function(data){
                if(data=="成功"){
                    mui(".mui-input-row").on('tap', '.code', function() {sendCode();});
                    $('.step2').show().siblings().hide();
                    clearSession();//清空缓存
                    $("#username3").val(user.username);
                }else{
                   weixin.toast("验证码输入错误！")
                }
            });
        }

        function save(){
            var newPwd=$.trim($("#newPwd").val());
            var repeatPwd=$.trim($("#repeatPwd").val());
            if(newPwd.length<5||newPwd.length>20){
                weixin.toast("密码长度6-20位");
				return false
			}else if(newPwd!=repeatPwd){
                weixin.toast("两次密码输入不一致");
                return false
			}else{
                weixin.ajaxForm('#pwdForm','<%=basePath %>applyUserinfo/newPwd',{userid:user.userid},function(data){
                    if(data=="修改成功"){
                        mui.confirm('修改成功,跳转至登录页面？', '提示',['是','否'], function(e) {
                            e.index == 0 ? weixin.openWindow('login','<%=basePath %>appPath/login'): ''
                        })
                        //跳转首页
                    }else{
                        weixin.toast(data);
                    }
                });
			}
        }

        //离开页面清缓存
        window.onunload=function(){
            clearSession();
        }
	</script>
</body>
</html>

