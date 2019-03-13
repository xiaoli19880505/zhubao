<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="" style="width:350px; height: auto;overflow-y: auto;">
		<div class="h3"></div>
		<div class="easyui-panel p5" style="width:100%;height: 98%;">
			<ul class="easyui-tree" id="tree" data-options="url:'<%=basePath %>ParmItem/findAllParmImteByChildren',animate:true,lines:true"></ul>
		</div>

	</div>
		<div data-options="region:'center',border:false">
			<div class="h3"></div>
				<div class="easyui-panel p5" style="width: 100%;overflow: auto">
					<form id="queryForm">
						<ul class="search-btnGroup">
							<div class="query-tab-left">账号状态：</div>
							<div class="query-tab-right">
								<a class="query-cur" data-id="1">可用</a> <a data-id="0" class="">禁用</a>
							</div>
						</ul>
						<ul class="search-group">
							<li><input class="easyui-textbox" id="username" style="width: 92%" data-options="label:'姓名:'"></li>
							<li><select class="easyui-combobox" id="sex" style="width: 92%" data-options="label:'性别:',panelHeight:'auto',panelMaxHeight:400">
								<option value="男">男</option>
								<option value="女">女</option>
							    </select>
							</li>
							<li><input class="easyui-textbox" id="linktel" style="width: 92%" data-options="label:'联系电话:'"></li>
							<li class="query-btn">
								<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
							</li>
						</ul>
					</form>
					<div class="abtn-group">
						<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>新增</i></a>
						<a class="icon iconfont icon-xiugaiziliao golden" onclick="edit()"><i>修改</i></a>
						<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
						<a class="icon iconfont icon-zhongzhimima green" onclick="changePwd()"><i>重置密码</i></a>
						<a class="icon iconfont icon-tingyong red" onclick="changeState()" data-state="0"><i>停用</i></a>
						<a class="icon iconfont icon-qiyong green" onclick="changeState()" data-state="1"><i>启用</i></a>
						<a class="icon iconfont icon-permissions-user green" onclick="setRole()"><i>设置角色</i></a>
						<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
						<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
					</div>
				</div>
				<div class="easyui-panel p5" style="width: 100%;height:75%;overflow: auto">
					<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;" data-options="url:'<%=basePath %>UserInfo/findAllUserInfo',singleSelect:true,
					rownumbers:true,pagination:true,border:true,striped:true,pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
						<thead>
						<tr>
							<th data-options="field:'username',width:100,align:'center'">姓名</th>
							<th data-options="field:'usercode',width:100,align:'center'">登录名</th>
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
<script>
	//tree深度
    var treeHeight;
	//状态转换
	function state(value,row,index){
	  var state=(value=="1")?('<a style="color:#008000">可用</a>'):('<a style="color:#ff0000">停用</a>');
	    return state;
	}
    function parmname(value,row,index){
        if(value){
            return value.piItemname;
        }
    }
	//新增
	function add() {
	    var node=$("#tree").tree('getSelected');
	    if(node){
            var parent=$("#tree").tree('getParent',node.target);
            var parentText=(parent)?(parent.text):'';
            node.parentText=parentText;
            if(bank.nodeLevel(node,'#tree')==treeHeight){
                node.remarkCode="street"
            }else{
                node.remarkCode="block"
            }
            bank.biography().setParams({row:node,title:'userAdd'});
            bank.showWindow('<%=basePath%>UserInfo/touseradd', "新增用户",740, 450, true,"reload");
		}else{
            bank.alertMessage("请选择左侧机构")
	    }

	}
	//修改
	function edit() {
		var row = $("#dataTable").datagrid("getSelected");
		if(row) {
            bank.biography().setParams({row:row,title:'userEdit'});
			bank.showWindow('<%=basePath%>UserInfo/touseredit',"修改用户", 740, 450, true,"reload");
		} else {
			bank.alertMessage("请选中表中某条数据！")
		}
	}
	//删除
	function cut(){
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $.messager.confirm('操作提示','确定删除此用户吗?',function(r) {
                if (r) {
                    $.ajax({
                        url : "<%=basePath %>UserInfo/delete",//对应接口
                        type : "post",
                        data : {
                            userid : row.userid
                        },
                        success : function(data) {
                            $('#dataTable').datagrid('reload');
                            bank.ajaxMessage(data);
                        },
                        error : function(data) {
                            bank.ajaxMessage("数据库连接失败，请稍后再试!");
                            return false;
                        }
                    });
                } else {
                    return false;
                }
            });
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
	}
	//权限
	function setRole() {
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            bank.biography().setParams({row:row,title:'userBangRole'});
            bank.showWindow('<%=basePath %>UserInfo/toBangRole', "角色列表",740, 500, true,"reload");
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
	}
    //停用或启用或修改密码
    function changeState(event) {
        var event = event || window.event;
        var target =event.currentTarget|| event.target || event.srcElement;
        var row = $("#dataTable").datagrid("getSelected");
        var state=$(target).attr("data-state");
        var enable=(row.state==state)?(row.state):state;
        if(row) {
            $.ajax({
                url:"<%=basePath %>UserInfo/update",
                type:"post",
                data:{
                    userid:row.userid,
                    state:enable
                },
                success:function(data){
                    bank.ajaxMessage(data);
                    $("#dataTable").datagrid('reload');
                },error:function(){
                    bank.ajaxMessage("数据库连接失败，请稍后再试！");
                    return false;
                }
            })
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
    }
	//重置密码
	function changePwd(){
        var row = $("#dataTable").datagrid("getSelected");
        if(row) {
            $.ajax({
                url:"<%=basePath %>UserInfo/updateUserInfoByCsh",
                type:"post",
                data:{
                    userid:row.userid
                },
                success:function(data){
                    bank.ajaxMessage(data);
                    $("#dataTable").datagrid('reload');
                },error:function(){
                    bank.ajaxMessage("数据库连接失败，请稍后再试！");
                    return false;
                }
            })
        } else {
            bank.alertMessage("请选中表中某条数据！")
        }
	}
    $('.search-btnGroup a').click(function () {
		$(this).addClass('query-cur').siblings().removeClass('query-cur');
		var node=$("#tree").tree('getSelected');
		var state=$(this).attr('data-id');
        if(bank.nodeLevel(node,'#tree')==treeHeight){
            $("#dataTable").datagrid('load',{
                ssq:node.piParentsetcode,
                ssj:node.piItemcode,
                state:state
            });
        }else{
            $("#dataTable").datagrid('load',{
                ssq:node.piItemcode,
                ssj:"",
                state:$(this).attr('data-id')
            })
        }
    });

	$("#tree").tree({
		onClick:function(node){
		    var state=$('.search-btnGroup .query-cur').attr("data-id");
			if(bank.nodeLevel(node,'#tree')==treeHeight){
                $("#dataTable").datagrid('load',{
                    ssq:node.piParentsetcode,
                    ssj:node.piItemcode,
					state:state
                });
			}else{
                $("#dataTable").datagrid('load',{
                    ssq:node.piItemcode,
                    ssj:"",
                    state:state
                })
			}
        },
		onLoadSuccess:function (node,data) {
            treeHeight=bank.treeLevel(data);
        }
	});
	function query(){
        $("#dataTable").datagrid('load',{
            state:$('.search-btnGroup .query-cur').attr('data-id'),
			username:$.trim($("#username").val()),
			sex:$.trim($("#sex").combobox('getValue')),
			linktel:$.trim($("#linktel").val())
        });
	}
</script>