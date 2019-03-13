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
				<%--<li >
					<input class="easyui-combobox" name="ssq"  id="ssq" style="width: 92%" data-options="label:'所属县区:',panelHeight:'auto',panelMaxHeight:300,
					valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=05',editable:false">
				</li>--%>
				<%--<li>
					<select class="easyui-combobox" name="ssjd" id="ssjd" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:300,
					valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=06',editable:false"></select>
				</li>--%>
				<li class="readonly ssq">
					<input class="easyui-combobox" name="ssq" id="ssq" style="width: 92%" data-options="label:'所属县区:',panelHeight:'auto',panelMaxHeight:400,
					editable:false,valueField:'piItemcode',textField:'piItemname',onSelect:select">
				</li>
				<li>
					<select class="easyui-combobox" name="ssjd" id="ssjd" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:400,
					editable:false,valueField:'piItemcode',textField:'piItemname'"></select>
				</li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset" >
					<a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
				</li>
			</ul>
		</form>
		<%--<div class="abtn-group">
			<a class="icon iconfont icon-20daochu green" onclick="fileExport()"><i>导出EXCEL</i></a>
		</div>--%>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath %>blivegs/getPerBliveGongSList',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
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
				<th data-options="field:'operate',width:200,align:'center',formatter:operate">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
    //操作转换
    function operate(value,row,index) {
        var appid=row.blgId;//此数据id
		var processInstanceId=row.blgProcessinstanceid;//流程id
        if(row.blgStatusStr=="未提交"){
            return '<a class="agreen" onclick="creditAudit(\''+appid+'\');">提交审核</a>'+
                   '<a class="ablue" onclick="creditCut(\''+appid+'\');">撤回</a> '
        }
        else{
            return '<a class="ablue" onclick="creditOpinion(\''+appid+'\',\''+processInstanceId+'\');">审核意见</a> '
        }
    }
    //提交审核
    function creditAudit(blgId){
        var data={blgId:blgId};
        bank.biography().setParams({row:data,title:'creditAudit'});
     /*   bank.openWindow('<%=basePath %>blivegs/toChengxinReport');*/
        var a = $('<a href="<%=basePath %>blivegs/toChengxinReport" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
    //撤回
    function creditCut(blgId){
        $.messager.confirm('操作提示', '您确定撤回吗？', function (r) {
            if (r) {
                 $.ajax({
					 url:'<%=basePath %>blivegs/updateWithdraw',
					 type:'post',
					 data:{
					     blgId:blgId
					 },success:function(data){
					     bank.ajaxMessage(data);
					     if(data=="撤回成功!"){
					         $("#dataTable").datagrid('reload');
						 }
					 },error:function () {
                         bank.alertMessage("数据库连接失败，请稍后再试")
                     }
				 })
            }
        });
    }
    //审核意见
    function creditOpinion(blgId,processInstanceId){
        var data={
            appid:blgId,
            processInstanceId:processInstanceId
        };
        bank.biography().setParams({row:data,title:'creditOpion'});
        bank.showWindow("<%=basePath %>blivegs/toChengxinOpinion","审核意见",740, 500, true);
    }

    //根据条件查询
    function query(){
        $("#dataTable").datagrid('load',{
            blgUserName:$.trim($("#username").val()),
            blgSfzh:$.trim($("#sfzh").val()),
            blgSsq:$.trim($("#ssq").combobox('getValue')),
            blgSsj:$.trim($("#ssjd").combobox('getValue'))
        });
    }
    //导出
	function fileExport() {
        blgUserName = $.trim($("#username").val());
        blgSfzh = $.trim($("#sfzh").val());
        blgSsq = $.trim($("#ssq").combobox('getValue'));
        blgSsj = $.trim($("#ssjd").combobox('getValue'));
        window.location.href= "<%=basePath %>ExportExcel/export?blgUserName=" + blgUserName +"&blgSfzh=" + blgSfzh + "&blgSsq=" + blgSsq + "&blgSsj=" +blgSsj;
    }
    //为街道和街区默认赋值
    $(function () {
        var ssqId = "${sessionScope.user.parmItemssq.piItemcode}";
        var ssqname = "${sessionScope.user.parmItemssq.piItemname}";
        var ssjText = "${sessionScope.user.parmItemssjd.piItemname}";

        $("#ssq").combobox({
            url:'<%=basePath %>ParmItem/getOptions?parmSetcode=05',
            onLoadSuccess:function(){
                var datas=$("#ssq").combobox('getData');
                if(ssqId =="0"){
                    $("#ssq").combobox('setValue',datas[0]);
                } else {
                    $("#ssq").combobox("setValue",ssqId);
                    $("#ssq").combobox("readonly",true);
                }
            }
        });
    });
    function select(record){
        var qid = record.piItemcode;
        var ssqId = "${sessionScope.user.parmItemssq.piItemcode}";
        var ssjId = "${sessionScope.user.ssj}";
        if(qid==undefined){
            qid=record;
        }
        $("#ssjd").combobox({
            url:'<%=basePath%>ParmItem/findAllJd',
            onBeforeLoad:function(param){
                param.qid=qid;
            },
            onLoadSuccess:function () {
                if(ssqId !="0"){
                    $("#ssjd").combobox("setValue",ssjId);
                    $("#ssjd").combobox("readonly",true);
                }
            }
        });
    }
</script>
</html>