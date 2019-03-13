<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="overflow: hidden;">
			<div class="easyui-panel">
				<form id="queryForm">
					<ul class="search-group">
						<li><input class="easyui-textbox" id="rolename" style="width: 92%" data-options="label:'角色名称:'"></li>
						<li class="query-btn">
							<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
						</li>
					</ul>
				</form>
				<div class="abtn-group">
					<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>新增</i></a>
					<a class="icon iconfont icon-xiugaiziliao golden" onclick="edit()"><i>修改</i></a>
					<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
					<a class="icon iconfont icon-permissions-user green" onclick="setUser()"><i>设置用户角色</i></a>
					<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
					<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
					<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-panel" style="width: 100%;height: 98%;overflow: auto">
				<table id="dataTable" class="easyui-datagrid" style="height: 100%;min-height: 300px" data-options="url:'<%=basePath %>roleInfo/selectRoleInfo',singleSelect:true,rownumbers:true,fitColumns:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
					<thead>
						<tr>
							<th data-options="field:'dutyname',width:100,align:'center',halign:'center'">角色名称 </th>
							<th data-options="field:'describe',width:100,align:'center',halign:'center'">角色描述 </th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>

</html>
<script>
	//新增
	function add() {
		bank.showWindow('<%=basePath %>roleInfo/addRole', "新增角色", 420, 350, true,"reload");
	}
	//修改
	function edit() {
		var  row = $("#dataTable").datagrid("getSelected");
		if(row) {
            bank.biography().setParams({row:row,title:"roleEdit"});
			bank.showWindow('<%=basePath %>roleInfo/editRole',"修改", 420, 350, true,"reload");
		} else {
			bank.alertMessage("请选中表中某条数据！")
		}
	}
	//查询
	function query(){
	    $("#dataTable").datagrid('load',{
	        dutyname:$.trim($("#rolename").val())
		})
	}
	//绑定用户
	function setUser(){
        var a = $('<a href="<%=basePath %>roleInfo/setRole#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
	}
	//删除
	function cut(){
        var  row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $.messager.confirm('操作提示', '您确定删除吗？', function (r) {
                if (r) {
                    //删除
                    $.ajax({
                        url:'<%=basePath %>roleInfo/deleteRoleInfo',
                        type:'post',
                        data:{dutyid:row.dutyid},
                        success:function (data) {
                            bank.ajaxMessage(data);
                            $("#dataTable").datagrid('reload');
                        },error:function(){
                            bank.ajaxMessage("删除失败，请稍后再试！");
                            return false;
                        }
                    })
                }
            });

        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
	}
</script>