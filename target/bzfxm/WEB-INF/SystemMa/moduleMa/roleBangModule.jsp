<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
	<div data-options="region:'north',border:false" id="publicNorth">
		<div class="abtn-group btnInner">
			<a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
			<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
			<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'角色列表'" title="" style="width:280px; height: auto;overflow-y: auto;">
		<div class="easyui-panel p5" style="width:100%;height:98%;">
			<table id="roleTable" class="easyui-datagrid " style="height:100%" data-options="url:'<%=basePath %>roleInfo/selectRoleInfo',singleSelect:true,pagination:true,
						rownumbers:true,border:true,striped:true,nowrap:false,pagePosition:'bottom',pageSize:10,onSelect:select,">
				<thead>
				<tr>
					<th data-options="field:'cb',checkbox:true,align:'center'"></th>
					<th data-options="field:'dutyname',width:200,align:'center',halign:'center'">角色名称</th>
				</tr>
				</thead>
			</table>
		</div>

	</div>
	<div data-options="region:'center',border:false,title:'模块列表'">
		<div class="easyui-panel p5" style="width: 100%;height:99%;overflow: auto">
			<ul id="tree" class="easyui-tree" data-options="checkbox:true,url:'<%=basePath %>menuInfo/findAllMouduleList',line:true"></ul>
		</div>

	</div>
	</body>
</html>
<style>
	.layout-panel-west .panel-header {
		background: #f3f3f3 !important;
		border: none;
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#f3f3f3,endColorstr=#f3f3f3,GradientType=0)!important;
	}
	.datagrid-htable .datagrid-header-check input{
		display: none;
	}
</style>
<script>
    $(function(){
        $('#publicNorth').panel('resize',{height:'auto'});
    });
    function select(index,row){
        var root = $("#tree").tree("getRoot");
        $("#tree").tree("uncheck", root.target);
        $.ajax({
            url :'<%=basePath %>MenuRole/selectByRoleId',
            data:{
                dutyid:row.dutyid
            },
            type:"post",
            dataType:'json',
            success : function(data) {
                var checkArray=[];
                var childrenArray=[];
                if(data.menuRole.length>0){
                    for(var i=0;i<data.menuRole.length;i++){
                        checkArray.push((data.menuRole)[i].mrMenuid);
                        checkArray.sort();
                    }
                    if(checkArray.length>0){
                        for (var x=0;x<checkArray.length;x++) {
                            var dai=checkArray[x];
                            var node = $('#tree').tree("find", dai);//重点方法
                            if(node){
                                if(node.children.length>0){
                                    for(var i=0;i<node.children.length;i++ ){
                                        childrenArray.push(node.children[i].id);
                                        childrenArray.sort();
									}
									if(checkArray.toString().indexOf(childrenArray.toString())!=-1){
                                        $("#tree").tree("check", node.target);
									}else{
                                       $('#'+node.domId+'').find(".tree-checkbox").removeClass(" tree-checkbox0");
                                        $('#'+node.domId+'').find(".tree-checkbox").addClass(" tree-checkbox2");
									}
                                }else{
                                    $("#tree").tree("check", node.target);//被选中的节点

                                }

                            }

                        }
                    }
                }
            }
        });
   }
    //根据角色和check提交
    function  save(){
        var roleRows=$('#roleTable').datagrid('getSelected');
        if(!roleRows){
          bank.ajaxMessage("请选择左侧角色")
        }else{
            var menuArray=getAllCheck();
            menuArray=menuArray.toString();
           $.ajax({
                url :'<%=basePath %>MenuRole/saveMenuRole',
                type:"post",
                dataType:"json",
                data:{
                    dutyid:roleRows.dutyid,
                    menuArray:menuArray
                },
                success:function(data){
                   bank.ajaxMessage(data);
                    parent.$('#dataTable').datagrid('reload');
                    parent.$('#tree').tree('reload');
                    parent.parent.$('#westTree').tree('reload');
                },
                error:function(){
                   bank.alertMessage("数据库连接失败,请稍后再试")
                }
            });
        }
    }
    function getAllCheck(){
        var menuArray=[];
        var node = $('#tree').tree('getChecked','indeterminate');
        var nodes = $('#tree').tree('getChecked');
        if(node.length>0){
            for(var i=0;i<node.length;i++){
                menuArray.push(node[i].id);
                menuArray.push(node[i].meFaid);
            }
        }
        if(nodes.length>0){
            for(var i=0;i<nodes.length;i++){
                menuArray.push(nodes[i].id);
                menuArray.push(nodes[i].meFaid);
            }
        }
        if($.inArray("0",menuArray)!=-1){
            var index=bank.getArrayIndex(menuArray,"0");
            menuArray.splice(index, 1);//移除最高级的父节点id；
            menuArray.sort();
            for(var i=0;i<menuArray.length;i++){
                if(menuArray[i]==menuArray[i+1]){
                    menuArray.splice(i,1);
                    i=i-1;
                }
            }
		}
       return menuArray;
	}
</script>