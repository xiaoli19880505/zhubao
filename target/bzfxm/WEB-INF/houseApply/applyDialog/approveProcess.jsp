<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<div class="easyui-panel" style="overflow-x:auto;height: 100%;" >
			<div class="ckprocess">
				<div class="proStart"><div class="quan">开始</div></div>
				<div id="processbox">
					<!--放置新增节点-->
				</div>
				<div class="proEnd">
					<div class="line"></div>
					<div class="quan">结束</div>
				</div>
			</div>
	</div>

</div>
<script>
	$(function () {
	    var data=bank.biography().getParams('auditProcess');
        $.ajax({
            url:'<%=basePath %>task/getApproveProcess',
            type:'post',
            data:{
                processInstanceId:data.row.processInstanceId
            },
            dataType:'json',
            success:function (data) {
				if(data.length>0){
                    for(var i=0;i<data.length;i++){
                       $("#processbox").append('<div class="line" id="line'+i+'"></div>'
                            +'<div class="fanContainer" id="node'+i+'"><div class="fang">'+data[i].nodename+'</div>'
                            +'</div>');
                       if(data[i].flag==1){
                         $('#line'+i+'').addClass('linered');
                           $('#node'+i+' .fang').addClass('red');
					   }
					}


				}
            },
            error:function () {
             bank.alertMessage("数据库连接失败，请稍后再试")
            }
        })
    })

</script>
</body>
<style>
	.linered{
		background: #FF0000 !important;
	}
	.red{
		color: #ff0000;
		border: #ff0000 solid 1px !important;
	}
</style>
</html>