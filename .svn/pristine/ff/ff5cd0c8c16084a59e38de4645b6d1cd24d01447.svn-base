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
					<input class="easyui-combobox" name="ssjd" id="ssjd" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:400,
					valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=06',editable:false">
				</li>
				<li class="query-btn">
					<a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
				</li>
				<li class="query-btn reset" >
					<a class="icon iconfont icon-hetongqianzi " onclick="publish()"><i>发&nbsp;布</i></a>
				</li>
			</ul>
		</form>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
			   data-options="url:'<%=basePath%>blivegs/Shgongbulb',rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true">
			<thead>
			<tr>
				<th data-options="field:'cb',checkbox:true,align:'center'"></th>
				<th data-options="field:'blgTypeName',width:120,align:'center',halign:'center'">申请类型</th>
				<th data-options="field:'blgSqbh',width:150,align:'center'">申请编号</th>
				<th data-options="field:'apldate',width:150,align:'center'">申请日期</th>
				<th data-options="field:'blgUserName',width:100,align:'center'">申请人</th>
				<th data-options="field:'blgIsgs',width:100,align:'center',formatter:gongshi">是否公示</th>
				<th data-options="field:'blgSfzh',width:150,align:'center'">身份证号</th>
				<th data-options="field:'blgSsqStr',width:100,align:'center'">所属县区</th>
				<th data-options="field:'blgSsjStr',width:100,align:'center'">所属街道</th>
				<th data-options="field:'blgStatusStr',width:100,align:'center'">审核状态</th>
				<th data-options="field:'operate',width:300,align:'center',formatter:operate">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>
<script>
    //是否公示
    function gongshi(value,row,index){
       if(value=="0"){
           return "<a class='agreen'>未发布</a>"
	   }else{
           return "<a class='ared'>已发布</a>"
	   }
    }
	var publishArray=[];
    //操作转换
    function operate(value,row,index) {
        var blgIsgs=row.blgIsgs;//是否发布
        var blgId=row.blgId;//此数据id
        var processInstanceId=row.blgProcessinstanceid;//流程id
		var apptype=row.blgType;
		var aplid=row.blgApid;
		return '<a class="ablue" onclick="queryDetail(\''+apptype+'\',\''+aplid +'\',\''+processInstanceId +'\',\''+blgIsgs +'\')">申请单详情</a>'+
		        '<a class="agreen" onclick="auditOpinion(\''+blgId+'\',\''+processInstanceId+'\');">审核意见</a>' +
                '<a class="ablue" onclick="auditProcess(\''+blgId+'\',\''+processInstanceId+'\');"> 审核进度</a> '+
                '<a class="agreen" onclick="creditContent(\''+blgId+'\');"> 查看失信内容</a> '
    }

    //查看申请详情
    function queryDetail(apptype,aplid,processInstanceId,blgIsgs){
        var data={blgIsgs:blgIsgs,proid:processInstanceId};
        bank.biography().setParams({row:data,title:'viewGongshi'});
        var a = $('<a href="<%=basePath %>task/toApprove?applyType='+apptype+'&applyId='+aplid+'&chengxin=gongshi#true" target="_blank"></a>')[0];
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
        bank.showWindow("<%=basePath %>task/toAuditProcess","审核进度",740, 300, true);
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
            appssq:$.trim($("#ssq").combobox('getValue')),
            appssjd:$.trim($("#ssjd").combobox('getValue'))
        });
    }
    //发布
	function publish(){
        if(publishArray.length==0){
           bank.alertMessage("请选择要发布的数据")
		}else{
            $.ajax({
                type:'post',
                url:'<%=basePath %>blivegs/updateRelease',
                dataType:'json',
                data:{
                    blgIds:JSON.stringify(publishArray)
                },success:function (data) {
                    bank.ajaxMessage(data);
					$("#dataTable").datagrid('reload');
					publishArray=[];
                },error:function () {
                    bank.alertMessage("数据库连接失败，请稍后重试")
                }
            })
		}

	}

	//回显已选中数据
	 function multiShow(data){
         if(publishArray.length>0){
             $('#dataTable').datagrid('clearSelections');
             var datas=$("#dataTable").datagrid('getRows');
             for(var i=0;i<datas.length;i++){
                 for(var j=0;j<publishArray.length;j++){
                     if(datas[i].blgId==publishArray[j]){
                         $('#dataTable').datagrid('selectRow',i);
                     }
                 }
             }
         }
	}
	//选择失信人员
    $("#dataTable").datagrid({
        onSelect:function(rowIndex, rowData){
			if($.inArray(rowData.blgId,publishArray)==-1){
				publishArray.push(rowData.blgId)
			}
        },
        onUnselect:function(rowIndex, rowData){
            $.each(publishArray,function(index,value){
                if(rowData.blgId==value){
                    publishArray.splice(index, 1);
                }
            });
        },
        onClickRow : function(rowIndex, rowData) {
             if (rowData.blgIsgs == "1"||rowData.blgStatusStr=="非失信状态") {
                $('#dataTable').datagrid('unselectRow', rowIndex);
            }
        },
        onDblClickRow : function(rowIndex, rowData) {
            //根据status值 双击单选行不可用
            if (rowData.blgIsgs == "1"||rowData.blgStatusStr=="非失信状态") {
                $('#dataTable').datagrid('unselectRow', rowIndex);
            }
        },
        onLoadSuccess:function(data){
            for ( var i = 0; i < data.rows.length; i++) {
                //根据blgIsgs值使复选框 不可用
                if ((data.rows)[i].blgIsgs == "1"||(data.rows)[i].blgStatusStr=="非失信状态") {
                    $("input[type='checkbox']")[i + 1].disabled = true;
                }
            }
            //回填选中
            multiShow(data)
        }
    });
</script>
<style>
	.datagrid-header-check input{
		display: none;
	}
</style>
</html>