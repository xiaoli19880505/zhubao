<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
	<div data-options="region:'west',border:false,split:true" style="width:350px; height: auto;overflow-y: auto;">
		<div class="easyui-panel p5" style="width:100%;height: 98%;">
			<div class="abtn-group">
				<a class="icon iconfont icon-xinzeng green" onclick="addLeft()"><i>新增</i></a>
				<a class="icon iconfont icon-xiugaiziliao green" onclick="editLeft()"><i>修改</i></a>
				<a class="icon iconfont icon-shangchu red" onclick="cutLeft()"><i>删除</i></a>
				<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dicTable')"><i>刷新</i></a>
			</div>
			<table id="dataTable" class="easyui-datagrid resTable" style="height: 89%;" data-options="url:'<%=basePath%>parm/selectParmByPage',singleSelect:true,
				rownumbers:true,pagination:true,border:true,fitColumns:true,pagePosition:'bottom',pageSize:20,nowrap:false">
				<thead>
				<tr>
					<th data-options="field:'prSetcode',width:80,align:'center',halign:'center'" >编号 </th>
					<th data-options="field:'prSetName',width:200,align:'center',halign:'center'" >字典名称 </th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<div class="easyui-panel p5" style="width: 100%;height: 98%;overflow: auto">
				<div class="abtn-group">
					<a class="icon iconfont icon-xinzeng green" onclick="addRight()"><i>新增</i></a>
					<a class="icon iconfont icon-xiugaiziliao green" onclick="editRight()"><i>修改</i></a>
					<a class="icon iconfont icon-shangchu red" onclick="cutRight()"><i>删除</i></a>
					<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
					<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
					<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
				</div>
			<table id="dicTable" class="easyui-datagrid resTable" style="height:89%;" data-options="singleSelect:true,
				rownumbers:true,pagination:true,border:true,fitColumns:true,pagePosition:'bottom',pageSize:20,nowrap:false">
				<thead>
				<tr>
					<th data-options="field:'piItemcode',width:200,align:'center',halign:'center'" >字段编号 </th>
					<th data-options="field:'piItemname',width:200,align:'center',halign:'center'" >字段名 </th>
					<th data-options="field:'parentName',width:200,align:'center',halign:'center'" >上级</th>
				</tr>
				</thead>
			</table>
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
</style>
<script type="text/javascript">
/***********左侧操作**************/
    //新增
    function addLeft() {
        bank.showWindow('<%=basePath %>parm/addParm', "增加数据字典",450, 400, true);
    }
    //修改
    function editLeft() {
        var row = $("#dataTable").datagrid("getSelected");
        bank.biography().setParams({row:row,title:'dictionaryEdit'});
        if(row) {
            bank.showWindow('<%=basePath %>parm/editParm', "修改数据字典", 450, 400, true);
            //回填
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
    }

    //删除
    function cutLeft(){
        var row=$("#dataTable").datagrid('getSelected');
        if(row){
            $.ajax({
                type:"post",
                url:"<%=basePath %>parm/deletParm",
                data:{
                    prSetcode:row.prSetcode
                },
                success:function(data){
                    bank.ajaxMessage(data);
                    $("#dataTable").datagrid('reload');
                    $("#dicTable").datagrid('reload');
                },
                error:function(data){
                    bank.ajaxMessage(data);
                }
            });
        }else{
            bank.alertMessage("请选则表中的菜单！")
        }
    }

/***********右侧操作**************/
	//新增
	function addRight() {
       var row = $("#dataTable").datagrid("getSelected");
       bank.biography().setParams({row:row,title:'RightAdd'});
		bank.showWindow('<%=basePath %>ParmItem/addParmImte', "增加数据字典字段",450, 400, true,"reload");
	}
  //修改
   function editRight() {
       var row = $("#dicTable").datagrid("getSelected");
       bank.biography().setParams({row:row,title:'RightEdit'});
    if(row) {
        bank.showWindow('<%=basePath %>ParmItem/toEditParmImte', "修改数据字典字段",450, 400, true,"reload");
        //回填
    } else {
        bank.alertMessage("请选中表中某条数据！")
    }
}
	//删除
	function cutRight(){
		var row=$("#dicTable").datagrid('getSelected');

		if(row){
			$.ajax({
				type:"post",
				url:"<%=basePath %>ParmItem/deletePamItem",
				data:{
                    parmSetcode:row.piSetcode,
                    piItemcode:row.piItemcode
				},
				success:function(data){
					bank.ajaxMessage(data);
					$("#dicTable").datagrid('reload');
					$("#tree").tree('reload');
				},
				error:function(data){
					bank.ajaxMessage(data);
				}
			});
		}else{
			bank.alertMessage("请选则表中的菜单！")
		}
	}

     //默认显示右侧表格数据
    $("#dataTable").datagrid({
        onClickRow:function(rowIndex,rowData){
           $("#dicTable").datagrid('load',{
               piSetcode:rowData.prSetcode
		   });
		},
		onLoadSuccess:function (data) {
            $("#dataTable").datagrid('selectRow',0);
            $("#dicTable").datagrid({
                url:'<%=basePath %>ParmItem/selectByPage',
                queryParams:{
                    piSetcode:data.rows[0].prSetcode
                }
            })
        }
	});
</script>
