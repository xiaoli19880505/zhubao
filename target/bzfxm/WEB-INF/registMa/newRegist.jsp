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
					<li><input class="easyui-textbox" name="applier" style="width: 92%" data-options="label:'申请人:'"></li>
					<li><input class="easyui-textbox" name="code" style="width: 92%" data-options="label:'身份证号:'"></li>
					<li>
						<select class="easyui-combobox" name="area" style="width: 92%" data-options="label:'所属县区:',panelHeight:'auto',panelMaxHeight:400,editable:false"></select>
					</li>
					<li>
						<select class="easyui-combobox" name="block" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:400,editable:false"></select>
					</li>
					<li class="query-btn">
						<a class="icon iconfont icon-sousuo" onclick="bank.queryTable('dataTable',['applier','code','area','block'])"><i>查&nbsp;询</i></a>
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
				   data-options="url:'',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
				<thead>
				<tr>
					<th data-options="field:'code',width:120,align:'center',halign:'center'">姓名</th>
					<th data-options="field:'applicationDate',width:100,align:'center',halign:'center'">性别</th>
					<th data-options="field:'organization_pathNume',width:200,align:'left',halign:'center'">手机号</th>
					<th data-options="field:'purchaser_name',width:100,align:'center',halign:'center'">登录密码</th>
					<th data-options="field:'purchaseDate',width:200,align:'center',halign:'center'">身份证号</th>
					<th data-options="field:'totalPrice',width:200,align:'left',halign:'center'">家庭地址</th>
					<th data-options="field:'supplier_name',width:100,align:'left',halign:'center'">所属县区</th>
					<th data-options="field:'invoiceNo',width:100,align:'center',halign:'center'">所属街道</th>
					<th data-options="field:'purpose',width:100,align:'left',halign:'center'">实名认证</th>
					<th data-options="field:'addtionWay',width:150,align:'left',halign:'center'">创建时间</th>
					<th data-options="field:'essetNature',width:200,align:'left',halign:'center',formatter:operate">操作</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	</body>
	<script>
		function operate(value,row,index) {
		    var id=row.id;
			return '<a onclick="certificate('+id+','+index+')">实名认证</a> <a onclick="cut('+id+','+index+')">删除</a>'
        }
        function certificate(id,index) {
			//实名认证
        }
        function cut(id,index){
            $("#dataTable").datagrid('selectRow',index);
            $.messager.confirm('操作提示', '您确定删除此条记录吗？', function (r) {
                if (r) {
                    //删除
                }
            });
        }
	</script>
</html>