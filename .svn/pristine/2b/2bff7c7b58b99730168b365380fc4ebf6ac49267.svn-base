<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<div class="reportTitle"><h3>诚信上报信息列表</h3></div>
		<div class="reportContainer">
			<form method="post" id="form" class="easyui-form">
				<ul class="reportUl">
					<li >
						<label class="reportLabel">主申请人信息:</label>
					</li>
					<li class="reportLi readonly">
						<input class="easyui-textbox" name="applyUserinfo.afmXm" id="applyUserinfoafmXm" style="width: 93%" data-options="label:'姓名:',readonly:true">
					</li>
					<li class="reportLi readonly">
						<select class="easyui-combobox" name="applyUserinfo.afmXb" id="applyUserinfoafmXb" style="width: 92%" data-options="label:'性别:',readonly:true">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</li>
					<li class="reportLi readonly">
						<input class="easyui-textbox" name="applyUserinfo.afmSfzh" id="applyUserinfoafmSfzh" style="width: 92%" data-options="label:'身份证号码:',readonly:true">
					</li>
					<li class="reportLi readonly">
						<input class="easyui-textbox" name="applyUserinfo.afmLxdh" id="applyUserinfoafmLxdh" style="width: 92%" data-options="label:'联系电话:',readonly:true">
					</li>
				</ul>
				<ul class="reportUl job" style="display: none">
					<li >
						<label class="reportLabel">关联企事业单位信息:</label>
					</li>
					<li class="reportLi">
						<input class="easyui-textbox" name="applyUnit.legelrep" id="applyUnitlegelrep" style="width:92%" data-options="label:'法定代表人'">
					</li>
					<li class="reportLi">
						<input class="easyui-textbox" name="applyUnit.bsls" id="applyUnitbsls" style="width: 92%" data-options="label:'营业执照号:'">
					</li>
					<li class="reportLi">
						<input class="easyui-textbox" name="applyUnit.entag" id="applyUnitentag" style="width:92%" data-options="label:'委托代理人'">
					</li>
					<li class="reportLi">
						<input class="easyui-textbox" name="applyUnit.tel" id="applyUnittel" style="width:92%" data-options="label:'手机号码'">
					</li>
					<li class="reportLi">
						<input class="easyui-textbox" name="applyUnit.address" id="applyUnitaddress" style="width:92%" data-options="label:'联系住址'">
					</li>
				</ul>
				<ul class="reportUl">
					<li class="reportLi readonly">
						<input class="easyui-textbox" name="bliveGongS.blgTypeName" id="bliveGongSblgTypeName" style="width: 92%" data-options="label:'业务类型:',readonly:true">
					</li>
					<li class="reportLi readonly">
						<input class="easyui-textbox" name="bliveGongS.blgSqbh" id="bliveGongSblgSqbh" style="width: 92%" data-options="label:'申请编号:',readonly:true">
					</li>
					<li class="reportLi" style="margin-bottom: 0">
						<select class="easyui-combobox" name="bliveGongsDetails[0].blgdSqtype" style="width: 92%" data-options="label:'失信严重程度:',required:true,editable:false,panelHeight:'auto',
						panelMaxHeight:400,valueField:'id',textField:'text'">
							<option value="1">一般</option>
							<option value="2">严重</option>
						</select>
					</li>
					<li class="reportLi" style="margin-bottom: 0">
						<label>失信行为类型</label>
						<input type="radio" style="margin-left: 15px" name="bliveGongsDetails[0].lostLetterType" value="1">个人<input type="radio" style="margin-left: 15px" name="lostLetterTypeArr[0]" value="2">企业
					</li>
				</ul>
				<ul class="reportUl">
					<li >
						<label class="reportLabel">失信行为描述:</label>
						<input class="easyui-textbox" name="bliveGongsDetails[0].blgdDesc" style="width: 96%;height: 100px" data-options="label:'',required:true,multiline:true">
					</li>
				</ul>
				<div class="addPlace"></div>
				<ul class="reportUl">
					<li >
						<label class="reportLabel">审核意见:</label>
						<input class="easyui-textbox" name="blgOpinion" style="width: 96%;height: 100px" data-options="multiline:true,required:true">
					</li>
				</ul>
				<ul class="reportUl">
					<li >
						<label class="reportLabel">附件:</label>
						<a onclick="attachment()" style="color: #00978A;cursor: pointer">点击查看附件信息</a>
					</li>
				</ul>
				<ul class="reportUl">
					<li class="query-btn reportLi" style="margin-left: 180px;background: gold">
						<a class="icon iconfont icon-f " onclick="add()"><i>添加失信</i></a>
					</li>
					<li class="query-btn reportLi" style="background: red">
						<a class="icon iconfont icon-f " onclick="remove()"><i>删除失信</i></a>
					</li>
					<li class="query-btn reportLi">
						<a class="icon iconfont icon-icon-" onclick="report()"><i>上&nbsp;报</i></a>
					</li>
					<li class="query-btn reset reportLi" >
						<a class="icon iconfont icon-f " onclick="back()"><i>返&nbsp;回</i></a>
					</li>
				</ul>
			</form>
		</div>
	</div>
</div>
</body>
<script>
	var resultData={};
    $(function () {
        var datas=bank.biography().getParams("creditAudit");
        $.ajax({
            url:'<%=basePath %>blivegs/selectCXInfo',
            type:'post',
            dataType:'json',
            data:{
                blgId:datas.row.blgId
            },
            success:function(data){
                resultData=data.bliveGongS;
				if(data.applyUnitFlag){
                      $(".job").show();
				}
                setValue($('#form'),data);
            },error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试")
            }
        })
    });
    function setValue(form,data){
        for(var x in data){
            if(typeof(data[x])=="object"){
                $.each(form.serializeArray(), function (index) {
                    if(this['name'].indexOf(x)!=-1){//是否含有此字段
                        var nameSplit=this['name'].split('.');
                            //普通对象
                            var idArray=[];
                            idArray.push(nameSplit[0],nameSplit[1]);
                            //根据不同类型赋值
                            var typeClass=$('#'+idArray[0]+idArray[1]+'').attr('class');
						if(typeClass!=undefined){
                              if(typeClass.indexOf('easyui-combobox')!=-1){
                            $('#'+idArray[0]+idArray[1]+'').combobox('setValue',(data[x])[idArray[1]])
                         }else if(typeClass.indexOf('easyui-datebox')!=-1){
                             $('#'+idArray[0]+idArray[1]+'').datebox('setValue',(data[x])[idArray[1]])
                         }else{
                             $('#'+idArray[0]+idArray[1]+'').textbox('setValue',(data[x])[idArray[1]])
                         }
						}

                    }
                });
            }else{
                $("#form").form('load',data);//直接赋值
            }
        }
    }
	function report(){
        var datas=bank.biography().getParams("creditAudit");
		  bank.ajaxForm('#form','<%=basePath %>blivegs/addCXReview',{blgId:datas.row.blgId},function (data) {
             bank.ajaxMessage(data);
             if(data=="上报成功"){
                 setTimeout(function () {
					 window.close();
					 opener.location.reload();
                 },1000)
			 }
		  })
	  }
	  function back() {
		  window.close();
		  opener.location.reload();
	  }

    //查看附件信息
    function attachment(){
        if(resultData.blgType.indexOf("ns")!=-1){
            //年审
            var a = $('<a href="<%=basePath %>appove/toNsAudit?applyType='+resultData.blgType +'&applyId='+resultData.blgApid+'&processInstanceId='+resultData.blgShProcessid +'&chengxin=chengxin" target="_blank"></a>')[0];
            var e = document.createEvent('MouseEvents');
            e.initEvent('click', true, true);
            a.dispatchEvent(e);
		}else{
            //普通申请
            var a = $('<a href=<%=basePath %>task/toApprove?applyType='+resultData.blgType +'&applyId='+resultData.blgApid +'&processInstanceId='+resultData.blgShProcessid +'&chengxin=chengxin target="_blank"></a>')[0];
            var e = document.createEvent('MouseEvents');
            e.initEvent('click', true, true);
            a.dispatchEvent(e);
        }
    }
	//添加失信
	function add() {
        if($(".addPlace").children().length == 0){
            var addInformation = $(".addPlace").append('<ul class="reportUl">' +
                '<li class="reportLi" style="margin-bottom: 0">' +
                '<select class="easyui-combobox" name="bliveGongsDetails[1].blgdSqtype" style="width: 92%" data-options="label:\'失信严重程度:\',required:true,editable:false,panelHeight:\'auto\',' +
                'panelMaxHeight:400,valueField:\'id\',textField:\'text\'">' +
                '<option value="1">一般</option>' +
                '<option value="2">严重</option>' +
                '</select>' +
                '</li>' +
                '<li class="reportLi" style="margin-bottom: 0">' +
                '<label>失信行为类型</label>' +
                '<input type="radio" style="margin-left:15px" name="bliveGongsDetails[1].lostLetterType" value="1">个人<input type="radio" style="margin-left: 15px" name="bliveGongsDetails[1].lostLetterType" value="2">企业' +
                '</li>' +
                '</ul>' +
                '<ul class="reportUl">' +
                '<li >' +
                '<label class="reportLabel">失信行为描述:</label>' +
                '<input class="easyui-textbox" name="bliveGongsDetails[1].blgdDesc" style="width: 96%;height: 100px" data-options="label:\'\',required:true,multiline:true">' +
                '</li>' +
                '</ul>');
            $.parser.parse(addInformation);
		}
    }
    //删除失信
	function remove() {
		$(".addPlace").find('ul').remove();
   }
</script>
<style>
	.textbox-label{
		width: 100px;
	}
</style>
</html>