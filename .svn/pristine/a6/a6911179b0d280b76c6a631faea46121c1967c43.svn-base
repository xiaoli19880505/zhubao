<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div class="over" style="display: none;">
	<div id="loading"></div>
</div>
<div data-options="region:'west',split:true,title:'#流程节点',border:false" style="width:280px">
	<!--左侧流程图标-->
	<div class="easyui-panel" data-options="border:false"  style="height: 98.5%;">
		<div class="addckprocess">
			<div class="quan">开始</div>
			<div class="fang addjiedian">节点</div>
			<div class="quan">结束</div>
			<div class="line">连线</div>
		</div>
	</div>
	<!--左侧流程图标 end-->
</div>
<div data-options="region:'center',border:false" id="flowPanel">
	<form id="flowattr" class="easyui-form"  method="post">
		<div class="easyui-panel">
			<div class="abtn-group btnInner">
				<a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
				<a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
			</div>
		</div>
		<!--右侧流程图-->
		<div title="流程配置">
			<div class="easyui-panel mb10" title="流程配置" style="overflow: auto">
				<div class="ckprocess">
					<div class="proStart">
						<div class="quan">开始</div>
					</div>
					<!--放置新增节点-->
					<div id="processbox"></div>
					<div class="proEnd">
						<div class="line"></div>
						<div class="quan">结束</div>
					</div>

				</div>
			</div>
		</div>
		<!--右侧流程图--end！-->
		<div class="easyui-panel mb10 " title="流程属性" style="background: #fff;width: 100%">
			<table id="lcattribute" cellpadding="0" cellspacing="0">
				<tr>
					<td><input class="easyui-textbox" id="flowname" name="flowname" style="width: 90%" data-options="label:'流程名称',validType:['empty','allNumber','specialCharacter']"/></td>
					<td class="readonly"><input class="easyui-textbox" id="flowno" name="flowno" style="width: 90%" data-options="label:'流程编码',readonly:true,validType:['empty','allNumber','specialCharacter']"/></td>
				</tr>
			</table>
		</div>
		<!--右侧数据 end-->
	</form>
	</div>
	<!--模态框-->
	<script>
	//首次进入渲染
    var rolenoArray=[];
	$(function () {
		var key = "${key }";//获取节点的节点编号
		$("#flowattr #flowname").textbox("setValue","${name }");//回填流程名
		$("#flowattr #flowno").textbox("setValue",key);//回填流程编码
        var url = "<%=basePath %>flow/getTaskByFlowKey";
		$.post(url,{key:key},function(data){
					//根据获取内容加载节点信息
					if(data.length>0){
					   for(var i=0;i<data.length;i++){
                           rolenoArray.push(data[i].roleno);
					    $("#processbox").append('<div class="line" id="line'+i+'"></div>'
					    +'<div class="fanContainer" id="node'+i+'"><div class="fang">'+data[i].taskname+'</div>'
					    +'<img src="<%=basePath %>src/img/system/cancel.png" alt="" class="fanclose"></div>');
                           var targetObj = $('<div class="panel panel-htop" style="display: block;">'
                               + '<div class="panel-header" style="width: 100%;">'
                               + '<div class="panel-title">节点属性</div>'
                               + '<div class="panel-tool"></div>'
                               + '</div>'
                               + '<div class="easyui-panel mb10 addpanel panel-body" title="" style="background: rgb(255, 255, 255);width:100%">'
                               + '<table cellpadding="0" cellspacing="0" id="form'+i+'"> '
                               + '<tr>'
                               + '<td><input name="nodename"   class="easyui-textbox nodename" value='+data[i].taskname+' style="width:90%" data-options="label:\'节点名称\',validType:[\'empty\',\'allNumber\',\'specialCharacter\'],events:{keyup:change}"></td> '
                               + '<td><input name="node_role_id"  class="easyui-combogrid" id="grid'+i+'" style="width:90%" data-options="label:\'接收人\',panelHeight:\'auto\',panelMaxHeight:\'350\',idField:\'dutyid\',textField:\'dutyname\','
                               + 'url:\'<%=basePath%>roleInfo/selectRoleInfo\',editable:false,pagination:true,columns:[['
                               +'{field:\'dutyid\',title:\'角色编号\',width:250},'
                               +'{field:\'dutyname\',title:\'角色名称\',width:250}]]"></td>'
                               + '</tr>'
                               + '<tr>'
                               + '<td class="readonly"><input  name="nodeno" class="easyui-textbox nodeno" value='+data[i].taskno+' style="width:90%" data-options="label:\'节点编号\',readonly:true,validType:[\'empty\',\'allNumber\',\'specialCharacter\']""></td> '
                               + '<td><input  name="desc"   class="easyui-textbox desc"  value='+data[i].desc+' style="width:90%" data-options="label:\'描述\'"></td>'
                               + '</tr>'
                               + '</table>'
                               + '</div></div>').appendTo("#flowattr");
                           $.parser.parse(targetObj);
                           $('#grid' + i + '').combogrid('setValue',{dutyid:data[i].roleno,dutyname:(data[i].roleInfo).dutyname});
					}
					}
				},"json");
		
	});
    $(function() {
        $('.addjiedian').on('click',function(e) {
            var e = e || window.event;
            //遍历一遍最大的值，自增，不重复,默认含有流程信息
            var formLen = $("#flowattr .addpanel").find("table").length;//表个数
            var fanLen = $("#processbox").find(".fanContainer").length;//节点个数
            var formarray = [];//定义一个form编码数组
            var nodearray = [];//定义一个node编码数组
            var newarray = [];//定义一个新数组
            if (fanLen == 0 && formLen == 0) {
                $('.ckprocess #processbox').append('<div class="line" id="line0"></div>'
                    + '<div class="fanContainer" id="node0">'
                    + '<div class="fang">节点名称</div>'
                    + '<img src="<%=basePath %>src/img/system/cancel.png" alt="" class="fanclose"> '
                    + '</div>')
            } else if (formLen == 0 && fanLen > 0) {
                //截取最大的，对比，加1
                $(".ckprocess #processbox").find(".fanContainer").each(function() {
                    newarray.push($(this).attr("id").substring('4'));});
                newarray.sort();
                var newcode = parseInt(newarray[newarray.length - 1]) + 1;
                $('.ckprocess #processbox').append('<div class="line" id="line'+newcode+'"></div>'
                    + '<div class="fanContainer" id="node'+newcode+'">'
                    + '<div class="fang">节点名称</div>'
                    + '<img src="<%=basePath %>src/img/system/cancel.png" alt="" class="fanclose"> '
                    + '</div>');
            } else {
                //遍历获取form截取字符串的值，值每次加1
                //遍历节点截取字符串的值，与formid对比，加1
                $("#flowattr .addpanel").find("table").each(function() {
                    formarray.push($(this).attr("id").substring('4'));});
                $(".ckprocess #processbox").find(".fanContainer").each(function() {
                    nodearray.push($(this).attr("id").substring('4'));});
                formarray.sort();
                nodearray.sort();
                var formcode = parseInt(formarray[formarray.length - 1]) + 1;
                var nodecode = parseInt(nodearray[nodearray.length - 1]) + 1;
                var code;
                if (formcode > nodecode) {
                    code = formcode;
                } else {
                    code = nodecode;
                }
                $('.ckprocess #processbox').append('<div class="line" id="line'+code+'"></div>'
                    + '<div class="fanContainer" id="node'+code+'">'
                    + '<div class="fang">节点名称</div>'
                    + '<img src="<%=basePath %>src/img/system/cancel.png" alt="" class="fanclose"> '
                    + '</div>');
            }
        });
        //点击节点名称，如果当前名称为节点名称且不存在则增加一个空表格，否则不增加，formid为form加编号
        $('body').on('click','#processbox  .fang',function(e) {
            var e = e || window.event;
            $(this).addClass("fangred").parent().siblings().find('.fang').removeClass("fangred");
            var index = $(this).parent().attr("id").substring('4');//当前节点编号
            $('#form' + index + '').parent().parent().siblings().find('.panel-title').css("color", "#ff0000");
            $('#form' + index + '').parent().parent().parent().siblings().find('.panel-title').css("color", "#575765");
            if (($(this).text() == "节点名称")&& ($("#flowattr").find('#form' + index + '').length == 0)) {
                var targetObj = $('<div class="panel panel-htop" style="display: block;">'
                    + '<div class="panel-header" style="width: 100%;">'
                    + '<div class="panel-title">节点属性</div>'
                    + '<div class="panel-tool"></div>'
                    + '</div>'
                    + '<div class="easyui-panel mb10 addpanel panel-body" title="" style="background: rgb(255, 255, 255);width:100%">'
                    + '<table cellpadding="0" cellspacing="0" id="form'+index+'"> '
                    + '<tr>'
                    + '<td><input name="nodename"   class="easyui-textbox nodename" style="width:90%" data-options="label:\'节点名称\',validType:[\'empty\',\'allNumber\',\'specialCharacter\'],events:{keyup:change}"></td> '
                    + '<td><input name="node_role_id"  class="easyui-combogrid"  style="width:90%" data-options="label:\'接收人\',panelHeight:\'auto\',panelMaxHeight:\'350\',idField:\'dutyid\',textField:\'dutyname\','
                    + 'url:\'<%=basePath%>roleInfo/selectRoleInfo\',editable:false,pagination:true,columns:[['
                    +'{field:\'dutyid\',title:\'角色编号\',width:250},'
                    +'{field:\'dutyname\',title:\'角色名称\',width:250}]]"></td>'
                    + '</tr>'
                    + '<tr>'
                    + '<td><input  name="nodeno" class="easyui-textbox nodeno" style="width:90%" data-options="label:\'节点编号\',validType:[\'empty\',\'allNumber\',\'specialCharacter\']""></td> '
                    + '<td><input  name="desc"   class="easyui-textbox desc"  style="width:90%" data-options="label:\'描述\'"></td>'
                    + '</tr>'
                    + '</table>'
                    + '</div></div>').appendTo("#flowattr");
                $.parser.parse(targetObj);
                $('#form' + index + '').find(".easyui-combogrid").combogrid({
                    onLoadSuccess:function(data){
                        if (data.total> 0) {
                            $(this).datagrid("selectRecord",(data.rows)[0].dutyid);
                        }
                    }
                });
            } else {
                return false;
            }
        });
        //删除新添加的节点，同时移去对应的表格
        $('body').on("click", '#processbox .fanclose', function(e) {
            var e = e || window.event;
            var thisfang=$(this);
            var index = $(this).parent().attr("id").substring('4');//截取编码获取对应的formid
            $.messager.confirm('确认', '确定移除当前节点吗?', function(r) {
                if(r) {
                    $('#processbox').find("#line" + index + "").remove();
                    thisfang.parent().remove();
                    $('#form' + index + '').parent().parent().parent().remove();
                    bank.ajaxMessage("移除成功");
                }
            });

        });
    });

    //input操作,修改下面节点名称的同时，修改上面的
    function change(){
        var name=$.trim($(this).val());
        var code = $(this).parent().parent().parent().parent().parent().attr("id").substring('4');//获取表对应的节点
        if (name.length > 0) {
            $("#processbox #node" + code + "").find(".fang").text($(this).val());
        } else {
            $("#processbox #node" + code + "").find(".fang").text("节点名称");
        }
    }
    //如果新增节点，保存新增，否则返回
    function save(){
        $(".over").show();
        var addJSON = {};
        var formarray = [];//定义一个form编码数组
        var userarray = [];//定义一个接收人数组
        var namearray = [];//定义一个节点名称数组
        var nodenoarray=[];//定义一个节点编码数组
        $("#flowattr .addpanel").find("table").each(function() {
            formarray.push($(this).attr("id").substring('4'));
            namearray.push($(this).find(".nodename").val());
            nodenoarray.push($(this).find(".nodeno").val());
            userarray.push($(this).find(".easyui-combogrid").combogrid("getValue"));
        });
        $("#flowattr").form('submit', {
            url:'<%=basePath %>flow/updateFlow',
            onSubmit: function(param){
                var isValid=false;
				    isValid = $(this).form('validate');
                var flag=true;
                if (isValid){
                    if ((formarray.length==0)) {
                        flag=false;
                        bank.alertMessage("每个流程至少添加一个节点");
                    }
                    for(var i=0;i<userarray.length;i++){

                        if(namearray[i]==namearray[i+1]){
                            flag=false;
                            bank.alertMessage("同一流程节点名称不能相同");
                        }
                       /* else if(userarray[i]==userarray[i+1]){
                            flag=false;
                            bank.alertMessage("同一流程接收人不能相同");
                        }*/
                        else if(nodenoarray[i]==nodenoarray[i+1]){
                            flag=false;
                            bank.alertMessage("同一流程节点编号不能相同");
                        }
                    }
                    if(flag){
                        for ( var i = 0; i < formarray.length; i++) {
                            addJSON[formarray[i]] = {};
                            addJSON[formarray[i]].nodename = $.trim($("#form" + formarray[i] + "").find(".nodename").val());
                            addJSON[formarray[i]].nodeno = $.trim($("#form" + formarray[i] + "").find(".nodeno").val());
                            addJSON[formarray[i]].node_role_id =$.trim($("#form" + formarray[i] + "").find(".easyui-combogrid").combogrid("getValue"));
                            addJSON[formarray[i]].desc = $.trim($("#form" + formarray[i] + "").find(".desc").val());
                        }
                        param.nodeJson=JSON.stringify(addJSON);
                    }
                    return isValid;
                }

                if(!isValid){
                    return false;
                }
                return flag;
            },
            success:function(data){
                $(".over").hide();
                bank.ajaxMessage(data);
                setTimeout(function () {
					self.close();
					window.opener.location.reload();
                })
            },
            error:function(){
                $(".over").hide();
                bank.ajaxMessage("数据库连接失败，请稍后再试！");
                return false;
            }
        },'json');

    }
	</script>
</body>
<style>
	.textbox-label{
		margin-left: 20px;
	}
	.textbox .textbox-text {
		color: #27A9E3;
	}
	.layout-panel-west .panel-header {
		background: #D3D3D3 !important;
		border: none;
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#D3D3D3,endColorstr=#D3D3D3,GradientType=0)!important;
	}
</style>
</html>