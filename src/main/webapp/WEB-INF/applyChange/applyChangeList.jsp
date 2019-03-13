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
				<li><input class="easyui-textbox" name="username" id="username" style="width: 92%" data-options="label:'操作人:'"></li>
				<li><input class="easyui-textbox" name="acSqbh" id="acSqbh" style="width: 92%" data-options="label:'申请编号:'"></li>
				<li style="width: 320px">
					<select class="easyui-combobox" id="acApplyType" data-options="label:'申请类型',panelHeight:'auto',panelMaxHeight:'400',editable:false,
                valueField:'id',textField:'text'" style="width: 92%">
						<option value="">全部</option>
						<option value="1">经济适用住房</option>
						<option value="2">公共租赁住房租赁补贴</option>
						<option value="3">公共租赁住房（低保、特困家庭）</option>
						<option value="4">公共租赁住房（外来务工人员）</option>
						<option value="5">公共租赁住房（新就业人员）</option>
					</select>
				</li>
				<li style="width: 320px">
					<select class="easyui-combobox" id="actype" data-options="label:'操作类型',panelHeight:'auto',panelMaxHeight:'400',editable:false,
                valueField:'id',textField:'text'" style="width: 92%">
						<option value="">全部</option>
						<option value="1">终审通过</option>
						<option value="9">申请驳回</option>
						<option value="10">年审通过</option>
						<option value="11">年审驳回</option>
						<option value="2">摇号成功</option>
						<option value="13">摇号轮候</option>
						<option value="3">摇号放弃</option>
						<option value="12">摇号过期取消</option>
						<option value="4">分房</option>
						<option value="5">换房</option>
						<option value="7">退房</option>
						<option value="8">放弃房源/补贴</option>
						<option value="6">签订合同</option>
					</select>
				</li>
				<li><input class="easyui-datebox" name="beginTime" id="beginTime" style="width: 92%" data-options="label:'开始时间:'"></li>
				<li><input class="easyui-datebox" name="endTime" id="endTime" style="width: 92%" data-options="label:'结束时间:'"></li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset" >
					<a class="icon iconfont icon-zhongzhi " onclick="bank.clearForm('queryForm')"><i>重&nbsp;置</i></a>
				</li>
				<li class="query-btn red" style="background-color: red" >
					<a class="icon iconfont " onclick="javascript :history.go(-1);"><i>返&nbsp;回</i></a>
				</li>
			</ul>
		</form>

	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
			<thead>
			<tr>
				<th data-options="field:'acApplyType',width:120,align:'center',halign:'center',formatter:convenrt">申请类型</th>
				<th data-options="field:'acSqbh',width:150,align:'center'">申请编号</th>
				<th data-options="field:'acTime',width:100,align:'center'">操作日期</th>
				<th data-options="field:'username',width:100,align:'center'">操作人</th>
				<th data-options="field:'acType',width:150,align:'center',formatter:convenrtType">操作类别</th>
				<th data-options="field:'acRemark',width:150,align:'center'">备注</th>
				<th data-options="field:'essetNature',width:200,align:'center',formatter:operate">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>

	$(function () {
	    /*通过申请编号查询单条申请单的变更记录*/
		var sqbh = "${sqbh}";
		if(sqbh!=''){
            $("#acSqbh").textbox("setValue",sqbh);
		   // $("#acSqbh").attr("readOnly",true);
            $('#acSqbh').textbox('textbox').attr('readonly',true);
            $("#dataTable").datagrid({
                url:'<%=basePath %>applyChange/getApplyChangeList',
                queryParams:{
                    acSqbh:sqbh
                }
            });
        }else{
            $("#dataTable").datagrid({
                url:'<%=basePath %>applyChange/getApplyChangeList'
            });
        }
    });

    /*转换业务类别*/
    function convenrt(value,row,index){
        var apptype = row.acApplyType;
        switch (apptype){
            case "1":
                return "经济适用住房";
            case "2":
                return "公共租赁住房租赁补贴";
            case "3":
                return "公共租赁住房（低保、特困家庭）";
            case "4":
                return "公共租赁住房（外来务工人员）";
            case "5":
                return "公共租赁住房（新就业人员）";
        }
    }

    /*类型转换*/
    function convenrtType(value,row,index){
        var acType = row.acType;
        switch(acType){
            case "1":
                return "终审通过";
            case "9":
                return "申请驳回";
            case "10":
                return "年审通过";
            case "11":
                return "年审驳回";
            case "2":
                return "摇号成功";
            case "13":
                return "摇号轮候";
            case "3":
                return "摇号放弃";
            case "12":
                return "摇号过期取消";
            case "4":
                return "分房";
            case "5":
                return "换房";
            case "7":
                return "退房";
            case "8":
                return "放弃房源/补贴";
            case "6":
                return "签订合同";
        }
    }


    //根据条件查询
    function query(){
        $("#dataTable").datagrid('load',{
            username:$.trim($("#username").val()),
            acSqbh:$.trim($("#acSqbh").val()),
            acApplyType:$.trim($("#acApplyType").combobox('getValue')),
            acType:$.trim($("#actype").combobox('getValue')),
            beginTime:$.trim($("#beginTime").val()),
            endTime:$.trim($("#endTime").val())
        });
    }

    function operate(value,row,index) {
        var aplid = row.acSqid;
        var apptype = row.acApplyType;
		return '<a class="ablue" onclick="queryDetail(\''+apptype+'\',\''+aplid +'\')">查看</a>';
    }

    function queryDetail(apptype,aplid){
        var a = $('<a href="<%=basePath %>task/toApprove?applyType='+apptype+'&applyId='+aplid+'&chengxin=chengxin#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
</script>
</html>