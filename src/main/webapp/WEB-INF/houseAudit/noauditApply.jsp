<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
	<div class="easyui-panel" style="width: 100%;overflow: hidden">
		<div class="houseType">
			<c:if test="${area==1}">
			<div class="smallType type1">
				<div class="typeCount" id="xinjyCount">${xinjyCount}/${allCount}</div>
				<div class="typeName">公共租赁住房（新就业人员）</div>
				<input name="typeCode" type="hidden" value="5">
			</div>
			<div class="smallType type2">
				<div class="typeCount" id="wailaiCount">${wailaiCount}/${allCount}</div>
				<div class="typeName">公共租赁住房（外来务工人员）</div>
				<input name="typeCode" type="hidden" value="4">
			</div>
			<div class="smallType type3">
				<div class="typeCount" id="gongzfCount">${gongzfCount}/${allCount}</div>
				<div class="typeName">公共租赁住房（低保、特困家庭）</div>
				<input name="typeCode" type="hidden" value="3">
			</div>
			</c:if>
			<c:if test="${area==0}">
			<div class="smallType type5">
				<div class="typeCount" id="butieCount">${butieCount}/${allCount}</div>
				<div class="typeName">公共租赁住房租赁补贴</div>
				<input name="typeCode" type="hidden" value="2">
			</div>
			</c:if>
			<%--<div class="smallType type5">
				<div class="typeCount">${jingsfCount}/${allCount}</div>
				<div class="typeName">经济适用房</div>
				<input name="typeCode" type="hidden" value="1">
			</div>--%>
		</div>
		<form id="queryForm">
			<ul class="search-group">
				<li><input type="radio" name="all" value="all"><label class="lispan">全部审核</label>
					<span class="lispace"></span>
					<input type="radio" name="all" value="my"><label class="lispan">待我审核</label>
				</li>
				<li><input class="easyui-textbox" name="username" id="username" style="width: 92%" data-options="label:'申请人:'"></li>
				<li><input class="easyui-textbox" name="sfzh" id="sfzh" style="width: 92%" data-options="label:'身份证号:'"></li>
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
			</ul>
		</form>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath %>nstask/listPerToHandleNsTask',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true,queryParams:{applyUserSsq:'${sessionScope.user.parmItemssq.piItemcode}',applyUserJd:'${sessionScope.user.ssj}'}">
			<thead>
			<tr>
				<th data-options="field:'applyType',width:120,align:'center',halign:'center',formatter:convenrtApplyType">申请类型</th>
				<th data-options="field:'sqbh',width:150,align:'center'">申请编号</th>
				<th data-options="field:'applydate',width:100,align:'center'">申请日期</th>
				<th data-options="field:'username',width:100,align:'center'">申请人</th>
				<th data-options="field:'sfzh',width:150,align:'center'">身份证号</th>
				<th data-options="field:'ssq',width:100,align:'center'">所属县区</th>
				<th data-options="field:'ssjd',width:100,align:'center'">所属街道</th>
				<th data-options="field:'nodename',width:100,align:'center',formatter:nodeName">审核节点</th>
				<th data-options="field:'essetNature',width:200,align:'center',formatter:operate">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
    function nodeName(value,row,index) {
        if(value == '') {
            return '审核全部通过';
        } else {
            return value ;
        }
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

    /*转换业务类别*/
    function convenrtApplyType(value,row,index){
        //alert("value:"+value);
        var apptype = value;
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

    //操作转换
    function operate(value,row,index) {
        var apptypename=row.applyType;//申请类型
        var appid=row.appid;//此数据id
        var processInstanceId=row.processInstanceId;//审批进度id
		//alert("apptypename:"+apptypename);
        if(row.flag){
            if(row.yuanjian==1){
                return '<a class="agreen" onclick="auditStart(\''+apptypename+'\',\''+appid+'\',\''+processInstanceId+'\')">原件审核</a> ' +
                    '<a class="ablue" onclick="auditOpinion(\''+appid+'\',\''+processInstanceId+'\');">审核意见</a>' +
                    '<a class="ablue" onclick="auditProcess(\''+appid+'\',\''+processInstanceId+'\');"> 审核进度</a> '
            }else {
                return '<a class="agreen" onclick="auditStart(\'' + apptypename + '\',\'' + appid + '\',\'' + processInstanceId + '\')">开始审核</a> ' +
                    '<a class="ablue" onclick="auditOpinion(\'' + appid + '\',\'' + processInstanceId + '\');">审核意见</a>' +
                    '<a class="ablue" onclick="auditProcess(\'' + appid + '\',\'' + processInstanceId + '\');"> 审核进度</a> '
            }
		}else{
            return '<a class="ared">审核中</a> ' +
                '<a class="ablue" onclick="auditOpinion(\''+appid+'\',\''+processInstanceId+'\');">审核意见</a> ' +
                '<a class="ablue" onclick="auditProcess(\''+appid+'\',\''+processInstanceId+'\');"> 审核进度</a> '
        }
    }
    //开始审核
    function auditStart(apptype,appid,processInstanceId){
        var a = $('<a href="<%=basePath %>appove/toNsAudit?applyType='+apptype+'&applyId='+appid+'&processInstanceId='+processInstanceId+'&chengxin=feicheng" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
    //审核意见
    function auditOpinion(appid,processInstanceId){
        var data={
            appid:appid,
            processInstanceId:processInstanceId
        };
        bank.biography().setParams({row:data,title:'auditOpion'});
        bank.showWindow("<%=basePath %>task/toApproveOpinion","审核意见",740, 500, true);
    }
    //审核进度
    function auditProcess(appid,processInstanceId){
        var data={
            appid:appid,
            processInstanceId:processInstanceId
        };
        bank.biography().setParams({row:data,title:'auditProcess'});
        bank.showWindow("<%=basePath %>task/toApproveProcess","审核进度",740, 500, true);
    }

    //点击类型查询
    $('.houseType .smallType').click(function () {
        $(this).addClass('selectType').siblings().removeClass('selectType');
        var applytype=$.trim($(this).find('input[name="typeCode"]').val());
        $("#dataTable").datagrid('load',{
            applyType:applytype,
            applyUsername:$.trim($("#username").val()),
            applyUsercard:$.trim($("#sfzh").val()),
            applyUserSsq:$.trim($("#ssq").combobox('getValue')),
            applyUserJd:$.trim($("#ssjd").combobox('getValue'))
        })
    });
    //根据条件查询
    function query(){
        //拼接题头数据
        $.ajax({
            type:"post",
            url:"<%=basePath %>task/getPerApprove",
            async:true,
            dataType:'json',
            data:{
                ssq:$.trim($("#ssq").combobox('getValue')),
                ssj:$.trim($("#ssjd").combobox('getValue')),
                aplb:"ns"
            },
            success:function (data) {
                $("#xinjyCount").text(data.xinjyCount+'/'+data.allCount);
                $("#wailaiCount").text(data.wailaiCount+'/'+data.allCount);
                $("#gongzfCount").text(data.gongzfCount+'/'+data.allCount);
                $("#butieCount").text(data.butieCount+'/'+data.allCount);
            }
        });
        var applyType=$.trim($('.selectType').find('input[name="typeCode"]').val());
        $("#dataTable").datagrid('load',{
            applyType:applyType,
            applyUsername:$.trim($("#username").val()),
            applyUsercard:$.trim($("#sfzh").val()),
            applyUserSsq:$.trim($("#ssq").combobox('getValue')),
            applyUserJd:$.trim($("#ssjd").combobox('getValue'))
        });
    }
    //选择是否是我审核
    $("input[name='all']").change(function(){
        var applyType=$.trim($('.selectType').find('input[name="typeCode"]').val());
        $("#dataTable").datagrid('load',{
            all:$.trim($("input[name='all']:checked").val()),
            applyType:applyType
        })
    })
</script>
</html>