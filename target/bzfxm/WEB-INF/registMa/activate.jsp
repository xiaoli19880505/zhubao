<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
	<div class="easyui-panel" style="width: 100%;overflow: hidden">
		<form id="queryForm">
			<ul class="search-group">
				<li><input class="easyui-textbox" id="username" style="width: 92%" data-options="label:'申请人:'"></li>
				<li><input class="easyui-textbox" id="sfzh" style="width: 92%" data-options="label:'身份证号:'"></li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset" >
					<a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
				</li>
			</ul>
		</form>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath%>applyUserinfo/findNoActiveUsers',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
			<thead>
			<tr>
				<th data-options="field:'username',width:100,align:'center'">姓名</th>
				<th data-options="field:'sex',width:50,align:'center'">性别</th>
				<th data-options="field:'linktel',width:150,align:'center'">手机号</th>
				<th data-options="field:'sfzh',width:150,align:'center'">身份证号</th>
				<th data-options="field:'ssqStr',width:100,align:'center'">所属县区</th>
				<th data-options="field:'ssjStr',width:100,align:'center'">所属街道</th>
				<th data-options="field:'state',width:150,align:'center',formatter:state">激活状态</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:operate">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
    function state(value,row,index){
        if(value == 0){
            return '未激活'
        }else{
            return '已激活'
        }
    }
	function query(){
     $("#dataTable").datagrid('load',{
         username:$.trim($("#username").textbox('getValue')),
		 sfzh:$.trim($("#sfzh").textbox('getValue'))
	 })
	}


    function operate(value,row,index) {
        var userid = row.userid;
        // var atype=row.atype;
        // var aplid = row.aplid;
        // var apsqlb=row.aplbh;
        // var printable=row.printable;

		return '<a class="agreen" onclick="jsfOpinion(\''+userid +'\')">激活</a>';

    }
    function jsfOpinion(apluserid){
        $.messager.confirm('操作提示', '您确定激活该用户吗？', function (r) {
            if (r) {
                $.ajax({
                    url:'<%=basePath %>applyUserinfo/updateState',
                    type:'post',
                    data:{
                        userid:apluserid
                    },
                    success:function(data){
                        bank.ajaxMessage(data);
                        $("#dataTable").datagrid('reload')
                    }
                })
            }
        });
    }



</script>
</html>