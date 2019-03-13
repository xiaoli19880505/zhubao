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
		<div data-options="region:'center',split:false" title="">
			<div class="h3"></div>
			<div class="easyui-panel p5" style="width:100%;height: 98%;">
				<div class="abtn-group">
					<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>新增</i></a>
					<a class="icon iconfont icon-xiugaiziliao golden" onclick="edit()"><i>修改</i></a>
					<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
					<a class="icon iconfont icon-permissions-user green" onclick="setModule()"><i>设置角色权限</i></a>
					<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
				</div>
				<table id="dataTable" class="easyui-datagrid"  style="height: 91%;min-height:300px" data-options="url:'<%=basePath %>menuInfo/listModuleAll',singleSelect:true,
				rownumbers:true,pagination:true,border:true,striped:true,pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
					<thead>
					<tr>
						<th data-options="field:'meName',width:80,align:'left',halign:'center'">菜单名称</th>
						<th data-options="field:'meCode',width:80,align:'center'">菜单编码</th>
						<th data-options="field:'meUrl',width:80,align:'center'">链接地址</th>
						<th data-options="field:'meOrderno',width:80,align:'center'">排序</th>
						<th data-options="field:'meDesc',width:80,align:'center'">描述</th>
					</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
	<script>
//左侧树形操作
        //选中加载树形文件
        $("#tree").tree({
            onClick:function(node){
               $("#dataTable").datagrid('load',{
                    moduleid:node.id
				});
            }
        });
        //新增
       function add(){
           var node=$("#tree").tree("getSelected");
           if(node){

               bank.biography().setParams({node:node,title:'moduleAdd'});
               bank.showWindow('<%=basePath %>menuInfo/addMenu', "新增模块", 500, 460, true,"reload");

           }else{
               bank.alertMessage("请选则左侧模块！")
           }
	   }
		//修改
       function edit() {
           var row=$("#dataTable").datagrid('getSelected');
           if(row){
               bank.biography().setParams({row:row,title:'moduleEdit'});
               bank.showWindow('<%=basePath %>menuInfo/editMenu', "修改模块", 500, 460, true,"reload");
           }else{
               bank.alertMessage("请选则表中的模块！")
           }
       }
		//删除
		function cut(){
            var row=$("#dataTable").datagrid('getSelected');
            if(row){
                $.messager.confirm('操作提示', '您确删除该菜单吗？', function (r) {
                    if (r) {
                        //删除
                        $.ajax({
                            url:'<%=basePath %>menuInfo/deleteMenuInfo',
                            type:'post',
                            data:{moduleid:row.meId},
                            success:function (data) {
                                bank.ajaxMessage(data);
                                $("#dataTable").datagrid('reload');
                                $("#tree").tree('reload');
                                parent.$("#westTree").tree('reload');
                            },error:function(){
                                bank.ajaxMessage("删除失败，请稍后再试！");
                                return false;
                            }
                        })
                    }
                });
            }else{
                bank.alertMessage("请选则表中的菜单！")
            }
		}
		//设置角色权限模块
		function setModule(){
            bank.showWindow('<%=basePath %>menuInfo/setRole', "设置角色模块权限", 740, 610, true);
		}
	</script>
</html>