<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
		<div data-options="region:'center',border:false">
			<div class="easyui-panel windowPanel">
				<div class="abtn-group btnInner">
					<a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
					<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
					<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
				</div>
				<form method="post" id="form" class="easyui-form">
					<ul class="search-group-add windowInner">
						<legend>新增假期</legend>
						<li><input class="easyui-datebox" name="beginDate" style="width: 92%" data-options="label:'<span>*</span>开始日期:',required:true"></li>
						<li><input class="easyui-datebox" name="endDate" style="width: 92%" data-options="label:'<span>*</span>结束日期:',required:true"></li>
						<li><input class="easyui-textbox" name="desc" style="width: 92%;height:100px" data-options="label:'假期描述:',multiline:true"></li>
					</ul>
				</form>
			</div>
		</div>
	</body>

</html>
<style>
	.textbox-label {
		width: 100px;
	}
</style>
<script>

    function  save() {//保存
        /*var beginDate = $("input[name='begindate']").val();
        var endDate = $("input[name='enddate']").val();
        if(endDate<=beginDate){
            bank.ajaxMessage("");
		}*/
        bank.ajaxForm('#form','<%=basePath%>holiday/addHoliday',{},function(data){
            bank.ajaxMessage(data);
            if(data.indexOf("成功")!=-1){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
                }
                parent.$('#dataTable').datagrid('reload');

            });
    }
</script>
