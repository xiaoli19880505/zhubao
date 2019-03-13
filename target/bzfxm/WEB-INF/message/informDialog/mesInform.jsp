<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div style="width:100%;height:90%;" >
        <div class="message" style="margin: 20px auto 0;width: 80%;overflow: hidden">
               <span class="before" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
               <input class="easyui-datebox" id="sendTime" style="width: 120px" data-options="label:'',required:true,editable:false,validType:['overTime']">
               <span class="after"></span>
        </div>
        <ul class="search-group" style="margin-top: 100px">
            <li class="query-btn" style="margin-left: 190px" onclick="submit()">
                <a class="icon iconfont icon-icon-" ><i>发&nbsp;送</i></a>
            </li>
        </ul>
    </div>
</div>
<script>
$(function(){
    var data=bank.biography().getParams("mesInform");
     if(data.row.apptype=="2"){
         var before=''+data.row.username+'同志，您好！您所享受的公共租赁住房租赁补贴，合同期限将于';
         var after= '到期，请您收到提示短信后，请及时登录徐州市住保系统，办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+'，' +
             '徐州市住房保障服务中心';
    }else{
         var before=''+data.row.username+'同志，您好！您所承租的公租房'+data.row.room+'，合同期限将于';
         var after='到期，请您收到提示短信后，请及时登录徐州市住保系统，' +
             '办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+',徐州市住房保障服务中心';
     }
    $('.before').append(before);
     if(data.row.qysj!=null && data.row.qysj!='undefined'){
         alert(data.row.qysj);
         $("#sendTime").textbox("setValue",data.row.qysj.substr(0,10));
         $("#sendTime").textbox().textbox("readonly",true);
     }
    var reg=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
    if(reg.test(data.row.linktel)){
        $('.after').append(after);
    }else{
        $('.after').append(after);
        $('<input class="easyui-textbox" id="newTel" style="width: 220px" data-options="label:\';补充手机号\',required:true,editable:true,validType:[\'phone\']">').appendTo(".after");
        $.parser.parse( $('.after'));

    }

});
    function submit(){
        var htqx=$.trim($("#sendTime").datebox('getValue'));
        var data=bank.biography().getParams("mesInform");
        var reg=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
       // var updateTelFlag = "";
        if(!reg.test(data.row.linktel)){
            if(!reg.test($("#newTel").val())){
                bank.alertMessage("手机号需要按照格式输入");
                return;
            }else{
                data.row.linktel=$("#newTel").val();
            }
        }
        if(htqx.length!=0){
            if(data.row.apptype!="2"){
                var datas={
                    username:data.row.username,
                    linktel:data.row.linktel,
                    sqbh:data.row.sqbh,
                    applytype:data.row.apptype,
                    htqx:htqx,
                    room:data.row.room,
                    sfzh:data.row.sfzh,
                    applyid:data.row.appid,
                    temType:"7",
                    addConFlag:data.row.addConFlag,
                    updateTelFlag:data.row.updateTelFlag
                }
            }
            else{
                var datas={
                    username:data.row.username,
                    linktel:data.row.linktel,
                    sqbh:data.row.sqbh,
                    applytype:data.row.apptype,
                    sfzh:data.row.sfzh,
                    htqx:htqx,
                    applyid:data.row.appid,
                    temType:"10",
                    addConFlag:data.row.addConFlag,
                    updateTelFlag:data.row.updateTelFlag
                }
            }
            $.ajax({
                url:'<%=basePath%>message/sendMessage',
                type:'post',
                data:datas,
                success:function(data){
                    bank.ajaxMessage(data);
                    if(data.indexOf("成功")!=-1){
                        parent.$("#dataTable").datagrid("reload");
                    }
                    setTimeout(function () {
                        parent.$('.bankWindow').dialog('close');
                    },1000);
                }
            })
        }else{
            bank.alertMessage("到期时间不能为空")
        }

    }
</script>
</body>
</html>