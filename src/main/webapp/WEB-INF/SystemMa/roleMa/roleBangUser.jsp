<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
	<div data-options="region:'north',border:false" id="publicNorth" >
			<div class="abtn-group btnInner">
				<a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
				<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
				<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
			</div>
	</div>
	<div data-options="region:'west',split:true,title:'角色列表'" title="" style="width:280px; height: auto;overflow-y: auto;">
		<div class="h3"></div>
		<div class="easyui-panel p5" style="width:100%;height: 98%;">
			<table id="roleTable" class="easyui-datagrid " style="height: 98%" data-options="url:'<%=basePath %>roleInfo/selectRoleInfo',singleSelect:true,pagination:true,
			rownumbers:true,border:true,striped:true,nowrap:false,pagePosition:'bottom',pageSize:10,onSelect:select">
				<thead>
				<tr>
					<th data-options="field:'cb',checkbox:true,align:'center'"></th>
					<th data-options="field:'dutyname',width:200,align:'center',halign:'center'">角色名称 </th>
				</tr>
				</thead>
			</table>
		</div>

	</div>
		<div data-options="region:'center',border:false,title:'用户列表'">
			<div class="h3"></div>
				<div class="easyui-panel p5" style="width: 100%;height:98%;overflow: auto">
					<table id="userTable" class="easyui-datagrid resTable" style="height:100%" data-options="url:'<%=basePath %>UserInfo/findAllUserInfo',rownumbers:true,pagination:true,
					border:true,striped:true,pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
						<thead>
						<tr>
							<th data-options="field:'cb',checkbox:true,align:'center'"></th>
							<th data-options="field:'username',width:100,align:'center'">用户登录名</th>
							<th data-options="field:'sex',width:100,align:'center'">性别</th>
							<th data-options="field:'email',width:100,align:'center'" >电子邮件</th>
							<th data-options="field:'linktel',width:100,align:'center'">联系电话</th>
							<th data-options="field:'state',width:100,align:'center',formatter:state">用户状态</th>
							<th data-options="field:'parmItemssq',width:100,align:'center',formatter:parmname" >所属区</th>
							<th data-options="field:'parmItemssjd',width:100,align:'center',formatter:parmname" >所属街道</th>
							<th data-options="field:'describe',width:100,align:'center'">描述</th>
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
	.datagrid-htable .datagrid-header-check input{
		display: none;
	}
</style>
<script>

    function state(value,row,index){
        var state=(value=="1")?('<a style="color:#008000">可用</a>'):('<a style="color:#ff0000">停用</a>');
        return state;
    }
    function parmname(value,row,index){
        if(value){
            return value.piItemname;
        }
    }

    $(function(){
        $('#publicNorth').panel('resize',{height:'auto'});
    });
    var userArray=[];//提交json
	var flag="init";
	var showArray=[];
    function multiSelect(row){
        $.ajax({
            url :'<%=basePath %>UserInfo/findUserByRoleId',
            data:{
                dutyid:row.dutyid
            },
            type:"post",
            dataType:'json',
            success : function(data) {
               if(showArray.length>0){
                    $('#userTable').datagrid('clearSelections');
                    var datas=$("#userTable").datagrid('getRows');
                        for(var i=0;i<datas.length;i++){
                            for(var j=0;j<showArray.length;j++){
                                if(datas[i].userid==showArray[j]){
                                    $('#userTable').datagrid('selectRow',i);
                                }
                            }
                        }
				}else{
                   //默认选中数据库中数据
                    var datas=$("#userTable").datagrid('getRows');
                    if(data.userInfo.length>0){
                        for(var i=0;i<datas.length;i++){
                            for(var j=0;j<data.userInfo.length;j++){
                                if(datas[i].userid==(data.userInfo)[j].userid){
                                    $('#userTable').datagrid('selectRow',i);
                                }
                            }
                        }
                    }
				}
                if(flag=="init"){
                    if(data.userInfo.length>0){
                        for(var i=0;i<data.userInfo.length;i++){
                            if($.inArray((data.userInfo)[i].userid,userArray)==-1){
                                userArray.push((data.userInfo)[i].userid)//固定为元数据库中数据，非改变后数据
                            }
                        }
                    }
                    flag="uninit";
                    return false;
                }


            }
        });
    }
    function select(index,row){
       $("#userTable").datagrid('clearSelections');
       userArray=[];
       showArray=[];
       flag="init";
       multiSelect(row);
   }
    $("#userTable").datagrid({
        onSelect:function(rowIndex, rowData){
            if($.inArray(rowData.userid,userArray)==-1){
                userArray.push(rowData.userid)
            }
           showArray=userArray.slice(0);
        },
        onUnselect:function(rowIndex, rowData){
            $.each(userArray,function(index,value){
                if(rowData.userid==value){
                    userArray.splice(index, 1);
                }
            });
            showArray=userArray.slice(0);
        },
        onLoadSuccess:function(data){
            var row=$('#roleTable').datagrid('getSelected');
            if(row){
                //回填选中
                multiSelect(row)
            }
        }
    });
    //根据角色和check提交
    function  save(){
        var roleRows=$('#roleTable').datagrid('getSelected');
        if(!roleRows){
          bank.alertMessage("请选择左侧角色")
        }
        else{
           userArray=userArray.toString();
           $.ajax({
                url :'<%=basePath %>UserRoleInfo/savaUserRoleInfo',
                type:"post",
                dataType:"json",
                data:{
                    dutyid:roleRows.dutyid,
                    userArray:userArray
                },
                success:function(data){
                   bank.ajaxMessage(data)
                },
                error:function(){
                   bank.alertMessage("数据库连接失败,请稍后再试")
                }
            });
        }
    }


</script>