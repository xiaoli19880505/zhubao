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
    <div class="pwdcontainer">
        <div class="pwdTitle">
            <div class="titleItem arrow arrowOne">1.输入信息</div>
            <div class="titleItem arrow"> 2.确认信息</div>
            <div class="titleItem arrow"> 3.修改密码</div>
        </div>
        <div class="pwdContent">
            <div class="stepOne step">
                <form class="changeForm" id="changeForm" method="post" style="padding-top: 75px;width: 420px" >
                    <div class="formItem">
                        <input class="easyui-textbox"  name="username"   style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>姓名:',required:true,validType:['empty']">
                    </div>
                    <div class="formItem">
                        <input class="easyui-textbox"  name="sfzh"   style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>身份证:',required:true,validType:['empty']">
                    </div>
                    <div class="formItem">
                        <input class="easyui-textbox"  name="linktel"   style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>手机号:',required:true,validType:['empty','phone']">
                    </div>
                    <div class="formItem">
                        <a class="query-btn submit" style="margin-left: 80px" onclick="firstStep()">下一步</a>
                    </div>
                </form>
            </div>
            <div  class="stepTwo step" style="display: none">
                <form class="changeForm" id="confirmForm" method="post" style="padding-top: 75px;width: 420px;" >
                    <div class="formItem readonly">
                        <input class="easyui-textbox" id="username1" name="username" style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>姓名:',readonly:true">
                    </div>
                    <div class="formItem readonly">
                        <input class="easyui-textbox" id="sfzh" name="sfzh" style="width: 92%"  data-options="label:'<i style=\'color:red\'>* </i>身份证:',readonly:true">
                    </div>
                    <div class="formItem readonly">
                        <input class="easyui-textbox" id="linktel" name="linktel" style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>手机号:',readonly:true">
                    </div>
                    <div class="formItem last">
                        <input class="easyui-textbox" id="checkcode" name="checkcode" style="width:63%" data-options="label:'<i style=\'color:red\'>* </i>验证码:',required:true,validType:['empty']">
                        <a  class="code" style="display:inline-block;width: 116px;cursor: pointer" onclick="sendCode()">获取验证码</a>
                    </div>
                    <div class="formItem">
                        <a class="query-btn submit" style="margin-left: 80px" onclick="secondStep()">下一步</a>
                    </div>
                </form>
            </div>
            <div  class="stepThree step" style="display: none">
                <form class="changeForm" id="pwdForm" method="post" style="padding-top: 75px;width: 420px;" >
                    <div class="formItem readonly">
                        <input class="easyui-textbox" id="username2" name="username" style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>姓名:',readonly:true">
                    </div>
                    <div class="formItem">
                        <input class="easyui-textbox" id="newPwd" name="newPwd" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>新密码:',required:true,validType:['checkLength[6,20]']" >
                    </div>
                    <div class="formItem">
                        <input class="easyui-textbox" id="repeatPwd" name="repeatPwd" type="password"  style="width: 92%" data-options="label:'<i style=\'color:red\'>* </i>确认密码:',required:true,validType:['equalTo[\'#newPwd\']','empty']">
                    </div>
                    <div class="formItem">
                        <a class="query-btn submit" style="margin-left: 80px" onclick="save()">保存</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
<script>
 //倒计时60秒
  $(function () {
     clearSession();
 });
     var flag=false;
     var user={};
     var timer=null;
     //点击下一步验证信息
     function firstStep(){
     bank.ajaxForm('#changeForm','<%=basePath %>applyUserinfo/selectAllApp',{},function(data){
         var data=JSON.parse(data);
         if(data.length>0){
             $('.stepTwo').show().siblings().hide();
              user=data[0];
             flag=true;
             //回填信息
             $("#username1").textbox('setValue',data[0].username);
             $("#sfzh").textbox('setValue',data[0].sfzh);
             $("#linktel").textbox('setValue',data[0].linktel);
             $(".titleItem").eq(0).addClass('lightBlue');
         }else{
             bank.alertMessage("身份信息不匹配")
         }
     });
     }
    //设置倒计时
     function setCode(){
         $(".code").prop("onclick",null).off("click");
         clearInterval(timer);
         var time=60;
         timer=setInterval(function(){
             if(time==0){
                 $(".code").html('获取验证码');
                 $(".code").attr('onclick',"sendCode();");
                 clearInterval(timer);//这句话至关重要
                 return false;
             }else {
                 time--;
                 $(".code").html(time+'秒后可重新获取');
             }
         },1000);
     }
     //发送验证码
     function sendCode(){
         var linktel=$.trim($("#linktel").textbox('getValue'));
         if(linktel.length==0){
             bank.alertMessage("请输入手机号")
         }else{
             setCode();
             $.ajax({
                 url:'<%=basePath %>applyUserinfo/SendCode',
                 type:'post',
                 data:{
                     linktel:$.trim($("#linktel").textbox('getValue'))
                 },
                 success:function(data){
                 },error:function(){
                     bank.alertMessage("数据库连接失败，请稍后再试")
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
              bank.alertMessage("数据库连接失败，稍后再试");
             }
         })
    }

     //点击第二步
     function secondStep(){
        bank.ajaxForm('#confirmForm','<%=basePath %>applyUserinfo/submitCode',{},function(data){
            if(data=="成功"){
                $(".code").attr('onclick',"sendCode();");
                $('.stepThree').show().siblings().hide();
                clearSession();//清空缓存
                $("#username2").textbox('setValue',user.username);
                $(".titleItem:lt(2)").addClass('lightBlue');
                //跳转修改密码接口
            }else{
                bank.alertMessage("验证码输入错误！")
            }
            /*bank.ajaxMessage("登录成功！");*/
        });
     }

     function save(){
        bank.ajaxForm('#pwdForm','<%=basePath %>applyUserinfo/newPwd',{userid:user.userid},function(data){
            bank.ajaxMessage(data);
            if(data=="修改成功"){
                clearSession();//清空缓存
                setTimeout(function(){
                    window.close();
                    opener.location.reload();
                    //跳转首页
                },1500);

            }
        });
    }
    //tab选项卡
    $(".titleItem").click(function () {
     if(flag){
         $(".step").eq($(this).index()).show().siblings().hide();
     }
 });
     //离开页面清缓存
     window.onunload=function(){
         clearSession();
     }

</script>
<style>

    .textbox{
        width: 301px !important;
    }
    .last .textbox{
        width:180px !important;
    }

</style>
</html>
