<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
	<form id="queryForm">
		<ul class="search-group" style="margin-bottom: 15px">
			<li><input class="easyui-combobox" id="project" data-options="label:'项目',panelHeight:'auto',panelMaxHeight:'400',editable:false,
            valueField:'itemcodeinfoId',textField:'icItname'," style="width: 92%"></li>
			<li style="width: 320px">
				<label style="margin-right: 20px">导入类型</label>
				<select class="" id="type"  style="width: 60%;height: 30px;border: solid 1px gainsboro">
					<option value="">请选择导入类型</option>
					<option value="1">项目导入</option>
					<option value="2">楼号导入</option>
					<option value="3">房屋导入</option>
				</select>
			</li>
			<li class="query-btn" >
				<a class="icon iconfont icon-19daoru ration" onclick="fileImport()"><i>导入</i></a>
			</li>
			<li class="query-btn" >
				<a class="icon iconfont icon-shangchu " onclick="delGroup()"><i>删除</i></a>
			</li>
		</ul>
	</form>
	<form id="uploadForm" enctype="multipart/form-data" method="post" style="display: none">
		<input  id="file" name="upfile" class="easyui-filebox" data-options=" multiple:true,onChange:fileUpload,
		    accept:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'">
	</form>
</div>
<div data-options="region:'west',split:false,border:false,title:'楼栋信息'" style="width:270px; height: auto;overflow-y: auto;">
	<div class="easyui-panel p5" style="width:100%;height: 98%;border: solid 1px #cdcdcd">
		<ul class="build"></ul>
	</div>
</div>
<div data-options="region:'center',border:false">
	<div class="over" style="display: none;">
	<div id="loading"><img src="<%=basePath %>srcApply/img/public/loading.gif" ></div>
</div>
	<div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
		<ul class="houseContainer"></ul>
	</div>
</div>
</body>
<style>
	.layout-panel-west .panel-header {
		background: #d3d3d3 !important;
	}
	.proDelImg{
		background: url("<%=basePath %>src/img/public/close.png") no-repeat;
		display: inline-block;
		float: right;
		width: 20px;
		height: 20px;
		cursor: pointer;
	}
</style>
<script>
	var   bulidArray=[];
    //根据项目加载楼号
    $("#project").combobox({
        url:'<%=basePath %>ItemCodeInfo/select',
        onLoadSuccess:function(data){ //默认选中第一条数据
            bulidArray=[];
            if (data.length > 0) {
                $('#project').combobox('setValue', data[0].itemcodeinfoId);
                //追加删除
				$(".combobox-item").append('<span class="proDelImg" onclick="delProject($(this))"></span>')
            }
        },
        onChange:function () {
            bulidArray=[];
            var itemId = $('#project').combobox('getValue');
            setBuild(itemId);
        }
    });
    function setBuild(itemId){
        $(".build").html("");
        $.ajax({
            url:'<%=basePath %>BuidInfo/findBuildInfoByIid',
            type:'post',
            dataType:'json',
            data:{
                itemId:itemId
            },
            success:function(data){
                for(var i=0;i<data.length;i++){
                    $(".build").append('<li id="'+ data[i].buildinginfoId+'">'+ data[i].buname+'<span class="delImg" onclick="delBuild(\''+ data[i].buildinginfoId+'\')"></span></li>');
                    if(i==0){
                        $('#'+ data[i].buildinginfoId+'').addClass('buildSelect').siblings().removeClass('buildSelect');
                        houseInfo(data[i].buildinginfoId)
                    }
                }
            },
            error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试！")
            }
        })
    }
    //点击左侧，根据楼号查询
    $("body").on('click','.build li',function(){
        $(this).addClass('buildSelect').siblings().removeClass('buildSelect');
        var id = $(this).attr('id');
        houseInfo(id);
        $('.over').show();
    });
    //根据楼号加载房源信息
    function houseInfo(id) {
        $(".houseContainer").html("");
        $.ajax({
            url:'<%=basePath %>FactMapping/findFactByItemid',
            type:'post',
            dataType:'json',
            data:{
                buildid:id,
                fylx:""//选择类型
            },
            success:function(data){
                for (var i = 0;i<data.length;i++) {
                    if(data[i].inputflag == 1 || ((data[i].inputflag == 1 )&& (data[i].rationflag == 1))){
                        $('.houseContainer').append(' <li class="houseItem nocheck">' +
                            '<p class="housecheck"><input type="checkbox" name="selectRoom" class="selectRoom"><span>'+data[i].fylxname+'</span>' +
							'<span class="delImg" onclick="delRoom(\''+data[i].factmappingId+'\');"></span></p>' +
                            '<p class="houseCode" data-houseId="'+data[i].factmappingId+'">'+data[i].fCondonum+'</p>' +
                            '<p class="houseArea">'+data[i].fConacre+'m²</p>' +
                            '</li>')

                    }else if(data[i].inputflag == 0) {
                        $('.houseContainer').append(' <li class="houseItem hasCheck">' +
                            '<p class="housecheck"><input type="checkbox" disabled name="selectRoom"><span>'+data[i].fylxname+'</span></p>' +
                            '<p class="houseCode" data-houseId="'+data[i].factmappingId+'">'+data[i].fCondonum+'</p>' +
                            '<p class="houseArea">'+data[i].fConacre+'m²</p>' +
                            '</li>');
                    }
                }
                $('.over').hide();
            },
            error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试！")
            }
        })
    }
    //点击导入
    function fileImport() {
        /*var type=$.trim($("#type").combobox('getValue'));*/
		var type = $('#type').find('option:selected').val();
        if(type.length==0){
            bank.alertMessage("请选择导入类型");
		}else{
            $.messager.confirm('操作提示', '您确认导入此项目吗？', function (r) {
                if (r) {
                    $('#file').next('span').find('.filebox-label').click();
                }
            });
		}
    }
    function fileUpload() {
        var flag=false;
        /*var type=$.trim($("#type").combobox('getValue'));*/
        var type = $('#type').find('option:selected').val();
        var itemid=$.trim($("#project").combobox('getValue'));
        var buildid=$.trim($(".buildSelect").attr("id"));
        var url=(type=="1")?("<%=basePath %>ItemCodeInfo/import"):((type=="2")?("<%=basePath %>BuidInfo/import"):("<%=basePath %>FactMapping/import"));
        var data=(type=="1")?({itemid:""}):((type=="2")?({itemid:itemid}):({buildid :buildid }));
        var temp = $('#file').filebox('getValue');
        if (temp != '') {
            var arr = temp.split('.');
            if (arr.length > 1) {
                var expanded_name = arr[arr.length - 1].toLowerCase();//取得文件扩展名
                if (expanded_name == "xls" || expanded_name == "xlsx") {//确实是Excel文件
                    $('#uploadForm').form('submit', {
                        url:url,
                        contentType: false,
                        processData: false,
                        dataType:"json",
                        iframe:false,
                        onSubmit: function(param){
                            if(type=="2"||type=="3"){
                                for(var key in data){
                                    param[key]=data[key]
                                }
							}
                            var isValid = $(this).form('validate');
                             if(isValid){
                                 if(type=="3"&&(buildid.length==0)){
                                     bank.alertMessage("请选择楼号");
                                     flag=false;
                                 }else{
                                     flag=isValid;
								 }
							 }
                            return flag;
                        },
                        success:function(data){
                          bank.alertMessage(data);
                            //刷新项目
                            $('#project').combobox('clear');
                            $('#project').combobox('reload','<%=basePath %>ItemCodeInfo/select');

                        },error:function(){
                         bank.alertMessage("请选择Excel文件!")
                        }
                    });
                }
                else {//选择了其他类型的文件
                    $('#file').filebox('reset');
                    bank.alertMessage("请选择Excel文件!");
                }
            }
            else {//选择了无扩展名的文件
                $('#file').filebox('reset');
                bank.alertMessage("请选择Excel文件!");
            }
        }
    }
    //选择复选框，批量删除房间
    $('body').on('change','input[type=checkbox][name=selectRoom]',function(){
            var id=$.trim($(this).parent().siblings('.houseCode').attr("data-houseId"));
		    if($(this).is(':checked')){
				bulidArray.push(id)
			}else{
				var index= bank.getArrayIndex(bulidArray,id);
				bulidArray.splice(index,1)
			}
    });
   /* $('body').on('click','.houseContainer .nocheck',function(){
        var id= $(this).find('.houseCode').attr('data-houseId');
        $(this).find('.selectRoom').prop('checked',true);
        //在数组中，不加入，不在数组中加入
        if($.inArray(id,bulidArray)==-1){
            bulidArray.push(id)
        }
    });
    $('body').on('dblclick','.houseContainer .nocheck',function(){
        var id= $(this).find('.houseCode').attr('data-houseId');
        $(this).find('.selectRoom').prop('checked',false);
        if($.inArray(id,bulidArray)!=-1){
            var index=bank.getArrayIndex(bulidArray,id);  //获取index
            bulidArray.splice(index,1);
        }
    });*/

	function delGroup(){
	    if(bulidArray.length>0){
            $.messager.confirm('操作提示', '您确定删除吗？', function (r) {
                if (r) {
                     $.ajax({
                         url:"<%=basePath %>FactMapping/deleteHouses",
                         type:"post",
                         data:{
                             idsArray:bulidArray.toString()
                         },
                        success:function (result) {
                            bank.ajaxMessage(result);
                        //刷新房子
                        //刷新项目
                           $('#project').combobox('clear');
                           $('#project').combobox('reload','<%=basePath %>ItemCodeInfo/select');
                        },
                       error:function(){
                           bank.ajaxMessage("删除失败，请稍后再试！");
                           return false;
                      }
                })
                }
            });
		}else{
	        bank.ajaxMessage("请选择要删除的数据！")
		}

	}
	//单次删除房间号
	function delRoom(id){
       $.messager.confirm('操作提示', '您确定删除吗？', function (r) {
            if (r) {
                $.ajax({
                    url:"<%=basePath %>FactMapping/deleteHouses",
                    type:"post",
                    data:{
                        idsArray:id
                    },
                    success:function (result) {
                        bank.ajaxMessage(result);
                        //刷新房子
                        //刷新项目
                        $('#project').combobox('clear');
                        $('#project').combobox('reload','<%=basePath %>ItemCodeInfo/select');
                    },
                    error:function(){
                        bank.ajaxMessage("删除失败，请稍后再试！");
                        return false;
                    }
                })
            }
        });
	}
	//删除楼号
	function delBuild(id){
        $.messager.confirm('操作提示', '您确定删除吗？', function (r) {
            if (r) {
                $.ajax({
                    url:"<%=basePath %>BuidInfo/deleteBuid",
                    type:"post",
                    data:{
                        buid:id
                    },
                    success:function (result) {
                        bank.ajaxMessage(result);
                        $('#project').combobox('clear');
                        $('#project').combobox('reload','<%=basePath %>ItemCodeInfo/select');
                    },
                    error:function(){
                        bank.ajaxMessage("删除失败，请稍后再试！");
                        return false;
                    }
                })
            }
        });
	}
	//删除项目
	function delProject(obj){
	   var index=(obj.parent().attr("id")).substring(20);
	   var id=($("#project").combobox('getData'))[index].itemcodeinfoId;
       $.messager.confirm('操作提示', '您确定删除吗？', function (r) {
            if (r) {
                $.ajax({
                    url:"<%=basePath %>ItemCodeInfo/deleteItem",
                    type:"post",
                    data:{
                        itemid:id
                    },
                    success:function (result) {
                        bank.ajaxMessage(result);
                        $('#project').combobox('clear');
                        $('#project').combobox('reload','<%=basePath %>ItemCodeInfo/select');
                    },
                    error:function(){
                        bank.ajaxMessage("删除失败，请稍后再试！");
                        return false;
                    }
                })
            }
        });
	}
</script>
</html>