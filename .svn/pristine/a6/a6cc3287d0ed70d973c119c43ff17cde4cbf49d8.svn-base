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
					<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
				</div>
				<table id="dataTable" class="easyui-datagrid " data-options="url:'<%=basePath %>roleInfo/selectRoleInfo',
				   rownumbers:true,border:true,striped:true,nowrap:false,fitColumns:true,pagePosition:'bottom',pageSize:10,pagination:true">
					<thead>
					<tr>
						<th data-options="field:'cb',checkbox:true,align:'center'"></th>
						<th data-options="field:'dutyname',width:100,align:'center',halign:'center'">角色名称 </th>
						<th data-options="field:'describe',width:100,align:'center',halign:'center'">角色描述 </th>
					</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
<script>

    var userArray=[];//提交json
    var flag="init";
    var showArray=[];
    function multiSelect(row){
        $.ajax({
            url:'<%=basePath %>UserRoleInfo/findRoleByUid',
            type:'post',
            data:{
                userid:row.row.userid
            },
            dataType:'json',
            success:function(data){
                if(showArray.length>0){
                    $('#dataTable').datagrid('clearSelections');
                    var tableData=$("#dataTable").datagrid('getRows');
                    for(var i=0;i<tableData.length;i++){
                        for(var j=0;j<showArray.length;j++){
                            if(tableData[i].dutyid==showArray[j]){
                                $('#dataTable').datagrid('selectRow',i);
                            }
                        }
                    }
                }else{
                    //默认选中数据库中数据
                    var tableData=$("#dataTable").datagrid('getRows');
                    if(data.length>0){
                        for(var i=0;i<tableData.length;i++){
                            for(var j=0;j<data.length;j++){
                                if(tableData[i].dutyid==data[j].dutyid){
                                    $('#dataTable').datagrid('selectRow',i);
                                }
                            }
                        }
                    }
                }
                if(flag=="init"){
                    if(data.length>0){
                        for(var i=0;i<data.length;i++){
                            if($.inArray((data)[i].dutyid,userArray)==-1){
                                userArray.push(data[i].dutyid)//固定为元数据库中数据，非改变后数据
                            }
                        }
                    }
                    flag="uninit";
                    return false;
                }

            },error:function(){
                bank.alertMessage('数据库连接失败，请稍后再试')
            }

        });
    }
    $("#dataTable").datagrid({
        onSelect:function(rowIndex, rowData){
            if($.inArray(rowData.dutyid,userArray)==-1){
                userArray.push(rowData.dutyid)
            }
            showArray=userArray.slice(0);
        },
        onUnselect:function(rowIndex, rowData){
            $.each(userArray,function(index,value){
                if(rowData.dutyid==value){
                    userArray.splice(index, 1);
                }
            });
            showArray=userArray.slice(0);
        },
        onLoadSuccess:function(data){
            var row=  bank.biography().getParams('userBangRole');
            if(row){
                multiSelect(row);
			}
        }
    });
	function save(){
        var row=  bank.biography().getParams('userBangRole');
        userArray=userArray.toString();
        if(row) {
           $.ajax({
			   url:'<%=basePath %>UserRoleInfo/savaUserRoleInfoByUid',
			   type:'post',
			   data:{
			       userid:row.row.userid,
				   userArray:userArray,
			   },
			   success:function (data) {
				   bank.ajaxMessage(data);
                   parent.$("#dataTable").datagrid('reload');
                   parent.$("#tree").tree('reload');
				   if(data=="保存成功"){
                       setTimeout(function () {
                           parent.$('.bankWindow').dialog('close');
                       },1000);
				   }
               },error:function () {
				   bank.ajaxMessage("数据库连接失败，请稍后再试");
				   return false;
               }
		   })
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
	}
</script>
<style>
	.datagrid-htable .datagrid-header-check input{
		display: none;
	}
</style>
</html>
