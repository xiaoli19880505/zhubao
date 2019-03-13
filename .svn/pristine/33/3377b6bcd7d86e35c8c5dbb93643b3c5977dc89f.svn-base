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
				<li><input class="easyui-textbox" name="username" id="username" style="width: 92%" data-options="label:'申请人:'"></li>
				<li><input class="easyui-textbox" name="sfzh" id="sfzh" style="width: 92%" data-options="label:'身份证号:'"></li>
				<li >
					<input class="easyui-combobox" name="ssq"  id="ssq" style="width: 92%" data-options="label:'所属县区:',panelHeight:'auto',panelMaxHeight:400,
					valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=05',editable:false">
				</li>
				<li>
					<select class="easyui-combobox" name="ssjd" id="ssjd" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:400,
					valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=06',editable:false"></select>
				</li>
				<li>
					<select class="easyui-combobox" name="state" id="state" style="width: 92%" data-options="label:'审核状态:',panelHeight:'auto',panelMaxHeight:400,
					valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=06',editable:false"></select>
				</li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset" >
					<a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
				</li>
			</ul>
		</form>
		<div class="abtn-group">
			<a class="icon iconfont icon-20daochu green" onclick="bank.fileExport('2234')"><i>导出EXCEL</i></a>
		</div>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath %>blivegs/getIntegrityAudit',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
			<thead>
			<tr>
				<th data-options="field:'blgTypeName',width:120,align:'center',halign:'center'">申请类型</th>
				<th data-options="field:'blgSqbh',width:150,align:'center'">申请编号</th>
				<th data-options="field:'apldate',width:100,align:'center'">申请日期</th>
				<th data-options="field:'blgUserName',width:100,align:'center'">申请人</th>
				<th data-options="field:'blgSfzh',width:150,align:'center'">身份证号</th>
				<th data-options="field:'blgSsqStr',width:100,align:'center'">所属县区</th>
				<th data-options="field:'blgSsjStr',width:100,align:'center'">所属街道</th>
				<th data-options="field:'blgStatusStr',width:100,align:'center'">审核状态</th>
				<th data-options="field:'operate',width:250,align:'center',formatter:operate">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
    //操作转换
    function operate(value,row,index) {
        var blgId=row.blgId;//此数据id
        var processInstanceId=row.blgProcessinstanceid;//流程id
        if(row.blgStatusStr=="未审核"){
            return '<a class="agreen" onclick="auditStart(\''+blgId+'\')">开始审核</a> ' +
                '<a class="ablue" onclick="auditOpinion(\''+blgId+'\',\''+processInstanceId+'\');">审核意见</a>' +
                '<a class="ablue" onclick="auditProcess(\''+blgId+'\',\''+processInstanceId+'\');"> 审核进度</a> '+
                '<a class="ablue" onclick="creditContent(\''+blgId+'\');"> 查看失信内容</a> '
        }else{
            return '<a class="ared">审核中</a> ' +
                '<a class="ablue" onclick="auditOpinion(\''+blgId+'\',\''+processInstanceId+'\');">审核意见</a> ' +
                '<a class="ablue" onclick="auditProcess(\''+blgId+'\',\''+processInstanceId+'\');"> 审核进度</a> '+
                '<a class="ablue" onclick="creditContent(\''+blgId+'\');"> 查看失信内容</a> '
        }
    }
    //开始审核
	function auditStart(blgId,blgTypeName,processInstanceId){
        var data={blgId:blgId};
        bank.biography().setParams({row:data,title:'auditStart'});
       /* bank.openWindow("<%=basePath %>blivegs/toChengxinAudioSH");*/
        var a = $('<a href="<%=basePath %>blivegs/toChengxinAudioSH" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
	}

    //审核意见
    function auditOpinion(blgId,processInstanceId){
        var data={
            appid:blgId,
            processInstanceId:processInstanceId
        };
        bank.biography().setParams({row:data,title:'creditOpion'});
        bank.showWindow("<%=basePath %>blivegs/toChengxinOpinion","审核意见",740, 500, true);
    }
    //审核进度
    function auditProcess(blgId,processInstanceId){
        var data={
            appid:blgId,
            processInstanceId:processInstanceId
        };
        bank.biography().setParams({row:data,title:'auditProcess'});
        bank.showWindow("<%=basePath %>task/toAuditProcess","审核进度",740, 500, true);
    }
    //查看失信内容
	function creditContent(blgId){
        var data={blgId:blgId};
        bank.biography().setParams({row:data,title:'creditContent'});
        bank.showWindow("<%=basePath %>blivegs/toChengxinContent","失信内容",500, 400, true);
	}
    //根据条件查询
    function query(){
        $("#dataTable").datagrid('load',{
            appusername:$.trim($("#username").val()),
            appsfzh:$.trim($("#sfzh").val()),
            appssq:$.trim($("#ssq").val()),
            appssjd:$.trim($("#ssjd").combobox('getValue')),
            shstatus:$.trim($("#state").combobox('getValue'))
        });
    }
</script>
</html>