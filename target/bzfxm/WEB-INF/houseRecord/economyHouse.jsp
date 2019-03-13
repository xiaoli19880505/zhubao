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
				<li><input class="easyui-textbox" name="applyUserinfo.username" id="username" style="width: 92%" data-options="label:'申请人:'"></li>
				<li><input class="easyui-textbox" name="applyUserinfo.sfzh" id="sfzh" style="width: 92%" data-options="label:'身份证号:'"></li>
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
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath%>apply/selectApply',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true,queryParams:{applyType:1}">
			<thead>
			<tr>
				<th data-options="field:'apSqbh',width:150,align:'center',halign:'center'">申请编号</th>
				<%--<th data-options="field:'approve',width:200,align:'center',halign:'center',formatter:applicationDate">申请日期</th>--%>
				<th data-options="field:'purchaser_name',width:100,align:'center',halign:'center',formatter:applicationPerson">申请人</th>
				<th data-options="field:'purchaseDate',width:150,align:'center',halign:'center', formatter:applicationSfzh">身份证号</th>
				<th data-options="field:'ssqStr',width:100,align:'center',halign:'center'">所属县区</th>
				<th data-options="field:'ssjStr',width:100,align:'center',halign:'center'">所属街道</th>
				<th data-options="field:'cZtName',width:100,align:'center',halign:'center'">备案状态</th>
				<th data-options="field:'essetNature',width:200,align:'center',halign:'center',formatter:operate">合同签订</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
    function operate(value,row,index) {
        var  appid = row.apId;
        var apLc = row.apLc;
        if(apLc == 5){
            return '<a class="ared">已备案</a>'
        }else if(apLc == 4){
            return '<a class="agreen" onclick="auditOpinion(1,\''+appid+'\')">查看</a><a class="agreen" onclick="record(\''+appid+'\')">备案</a>'
        }
    }
    //格式化
    /*function applicationDate(val,row){
        return row.approve.apldate;
    }*/
    function applicationPerson(val,row){
        return row.applyUserinfo.username;
    }
    function applicationSfzh(val,row){
        return row.applyUserinfo.sfzh;
    }
    //查询
    function query(){
        $("#dataTable").datagrid('load',{
            username :$.trim($("#username").val()),
            sfzh:$.trim($("#sfzh").val()),
            ssq:$.trim($("#ssq").val()),
            ssj:$('input[name="ssjd"]').val(),
            applyType:1
        })
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
    function record(appid) {
		$.ajax({
            type:"get",
            url:"<%=basePath %>contract/updateFiling",
            data:{
                apSqlb:1,
                objId:appid
            },
			success:function (data) {
                bank.ajaxMessage(data);
                $("#dataTable").datagrid("reload",{
                    username :$.trim($("#username").val()),
                    sfzh:$.trim($("#sfzh").val()),
                    ssq:$.trim($("#ssq").val()),
                    ssj:$('input[name="ssjd"]').val(),
                    applyType:1
				});
            }
		})
    }
    //发送通知
    function sendMessage(appid) {
        $.ajax({
            type:"get",
            url:"<%=basePath %>contract/sendMessageForBA",
            async:true,
            data:{
                apSqlb:1,
                objId:appid
            },
            success:function (data) {
                bank.ajaxMessage(data);
            }
        });
    }
    //查看
    function auditOpinion(apptype,aplid){
        var a = $('<a href="<%=basePath %>task/toApplyForHtLook?applyType='+ apptype+'&applyId='+aplid+'#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
</script>
</html>