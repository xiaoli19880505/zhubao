<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="" style="width:350px; height: auto;overflow-y: auto;">
		<div class="easyui-panel p5" style="width:100%;height: 98%;">
			<ul class="easyui-tree" id="tree" data-options="url:'<%=basePath %>menuInfo/findAllMouduleList',animate:true,lines:true"></ul>
		</div>
	</div>
		<div data-options="region:'center',border:false">
				<div class="easyui-panel p5">
					<div class="abtn-group">
						<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>新增</i></a>
						<a class="icon iconfont icon-xiugaiziliao golden" onclick="edit()"><i>修改</i></a>
						<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
					</div>
				</div>
				<div class="easyui-panel p5" style="height: 89.5%;overflow: auto">
					<table id="dataTable" class="easyui-datagrid resTable" style="height:98%;min-height: 300px" data-options="url:'<%=basePath %>qxInfo/findAllQxInfo',singleSelect:true,
					 rownumbers:true,pagination:true,border:true,striped:true,pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
						<thead>
						<tr>
							<th data-options="field:'qxName',width:100,align:'center'">操作权限名称</th>
							<th data-options="field:'qxMeid',width:100,align:'center',formatter:name">所属菜单名称</th>
							<th data-options="field:'qxCode',width:100,align:'center'">权限编码</th>
							<th data-options="field:'qxDesc',width:100,align:'center'">描述</th>
						</tr>
						</thead>
					</table>
				</div>
		</div>
	</body>

</html>
<script>
	var treeLen=0;//tree深度变量
    function name(value,row,index){
            return row.qxMeid.meName;
    }
    $('#tree').tree({
		onClick:function(node,data){
		    $("#dataTable").datagrid('load',{
                mid:node.id
			});
		},
		onLoadSuccess:function(node,data){
            treeLen=bank.treeLevel(data);
		}
	});
	//新增
	function add() {
        var node=$('#tree').tree('getSelected');
        if(node){
            if(treeLen==bank.nodeLevel(node,'#tree')){
                bank.biography().setParams({row:node,title:'authorityAdd'});
                bank.showWindow('<%=basePath %>qxInfo/toauthoradd', "新增权限", 500, 400, true,"reload");
			}else{
                bank.alertMessage("请选该模块的底层子菜单！")
			}
		}else{
            bank.alertMessage("请选中左侧模块！")
		}
	}
	//修改
	function edit() {
		var row = $("#dataTable").datagrid("getSelected");
		if(row) {
            bank.biography().setParams({row:row,title:'authorityEdit'});
			bank.showWindow('<%=basePath %>qxInfo/toauthoraedit',"修改权限", 500, 400, true,"reload");
		} else {
			bank.alertMessage("请选中表中某条数据！")
		}
	}
  //删除
    function cut() {
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $.messager.confirm('确认', '确定删除当前记录?', function(r) {
                if(r) {
                    $.ajax({
                        url :'<%=basePath %>qxInfo/deleteQxInfo',
                        data:{qxid:row.qxId},
                        type:"post",
                        success : function(data) {
                            bank.ajaxMessage(data);
                            $("#dataTable").datagrid("reload");
                            $("#tree").tree('reload');
                         },error:function(){
                            bank.alertMessage("数据库连接失败，请稍后再试")
						}
                    });
                }
            });
        } else {
            bank.alertMessage("请选择表中要删除的模块！")
        }
    }
</script>