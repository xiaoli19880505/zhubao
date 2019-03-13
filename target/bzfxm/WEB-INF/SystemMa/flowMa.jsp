<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:'false'" style="overflow: hidden" id="publicNorth">
	<div class="easyui-panel">
		<div class="abtn-group">
			<a class="icon iconfont icon-xinzeng green" onclick="add()"><i>新增</i></a>
			<a class="icon iconfont icon-xiugaiziliao golden"  onclick="edit()"><i>修改</i></a>
			<a class="icon iconfont icon-shangchu red" onclick="cut()"><i>删除</i></a>
			<a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
			<a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
			<a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
		</div>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="h3"></div>
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath%>flow/flowPage',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
			<thead>
			<tr>
				<th data-options="field:'key',width:100,align:'center'">流程编码</th>
				<th data-options="field:'name',width:100,align:'center'">流程名称</th>
				<th data-options="field:'deploytime',width:80,align:'center'">部署时间</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
</html>
<script>
    //流程管理新增
	function add(){
        var a = $('<a href="<%=basePath %>flow/toAddFlow#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
	}
   //流程管理修改
	function edit(obj){
	    var node = $("#dataTable").datagrid("getSelected");
	    if(node){
            var a = $('<a href="<%=basePath %>flow/toEditFlow?key='+ node.key+'&name='+encodeURIComponent(node.name)+'#true" target="_blank"></a>')[0];
            var e = document.createEvent('MouseEvents');
            e.initEvent('click', true, true);
            a.dispatchEvent(e);
		}else{
	        bank.alertMessage("请选择要修改的行")
		}
	}

  //删除流程
    function cut() {
        var node = $('#dataTable').treegrid('getSelected');
        if (node ) {
            $.messager.confirm('操作提示','确定删除此流程?',function(r) {
                if (r) {
                    $.ajax({
                        url : "<%=basePath %>flow/deleteFlow",//对应接口
                        type : "post",
                        data : {
                            key : node.key
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
        }
        else {
            bank.alertMessage("请选择要删除的数据!");
        }
    }
</script>
