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
				<li><input class="easyui-textbox" id="username" style="width: 92%" data-options="label:'申请人:'"></li>
				<li><input class="easyui-textbox" id="sfzh" style="width: 92%" data-options="label:'身份证号:'"></li>
				<%--<li>
					<select class="easyui-combobox" id="ssq" style="width: 92%" data-options="label:'所属县区:',panelHeight:'auto',panelMaxHeight:400,
					editable:false,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath%>ParmItem/getOptions?parmSetcode=05'"></select>
				</li>
				<li>
					<select class="easyui-combobox" id="ssj" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:400,
					editable:false,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath%>ParmItem/getOptions?parmSetcode=06'"></select>
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
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath%>applyUserinfo/findAllApplyUserInfo',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true,queryParams:{ssq:'${sessionScope.user.parmItemssq.piItemcode}',ssj:'${sessionScope.user.ssj}'}">
			<thead>
			<tr>
				<th data-options="field:'username',width:100,align:'center'">姓名</th>
				<th data-options="field:'sex',width:50,align:'center'">性别</th>
				<th data-options="field:'linktel',width:150,align:'center'">手机号</th>
				<th data-options="field:'sfzh',width:150,align:'center'">身份证号</th>
				<th data-options="field:'ssqStr',width:100,align:'center'">所属县区</th>
				<th data-options="field:'ssjStr',width:100,align:'center'">所属街道</th>
				<th data-options="field:'state',width:150,align:'center',formatter:state">激活状态</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
	function state(value,row,index){
	  if(value == 0){
	      return '未激活'
	  }else{
	      return '已激活'
	  }
	}
	function query(){
		 $("#dataTable").datagrid('load',{
			 username:$.trim($("#username").textbox('getValue')),
			 sfzh:$.trim($("#sfzh").textbox('getValue')),
			 ssq:$.trim($("#ssq").combobox('getValue')),
			 ssj:$.trim($("#ssjd").combobox('getValue'))
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

    /**
	 * 按市区查询
     * @param record
     */
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