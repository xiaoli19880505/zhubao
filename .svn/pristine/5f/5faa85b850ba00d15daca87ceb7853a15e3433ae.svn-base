<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout" id="house">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
	<form id="queryForm">
		<ul class="search-group">
			<li><input class="easyui-combobox" id="project" data-options="label:'项目',panelHeight:'auto',panelMaxHeight:'400',editable:false" style="width: 92%"></li>
			<li style="width:280px">
				<label style="width:80px;display: inline-block">选房操作:</label>
				<input type="checkbox" id="checkALL" ><label class="lispan" for="checkALL">全选</label>
				<span class="lispace"></span>
				<input  type="checkbox" id="uncheckAll" ><label class="lispan" for="uncheckAll">反选</label>
			</li>
		</ul>
		<ul class="search-group">
			<li style="width:700px" class="areaGruop">
				<label style="width:80px;display: inline-block">配给区:</label>
			</li>
			<li class="query-btn" onclick="ration()">
				<a class="icon iconfont icon-shoucang" ><i>分配</i></a>
			</li>
		</ul>
	</form>
</div>
<div data-options="region:'west',split:false,border:false" style="width:270px; height: auto;overflow-y: auto;">
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
<script>
    var bulidArray=[];
    var isClick=true;
	$(function(){
	    //加载区域
	    $.ajax({
			url:'<%=basePath %>ParmItem/xzqOrder',
			type:'post',
			dataType:'json',
			success:function (data) {
			    if(data.length>0){
                    for(var i=0;i<data.length;i++){
                        $('.areaGruop').append('<input type="radio" name="area" value="'+data[i].piItemcode+'" id="'+data[i].piItemcode+'"><label class="lispan" for="'+data[i].piItemcode+'">'+data[i].piItemname+'</label><span class="lispace"></span>')
                    }
				}
            },
			error:function () {
				bank.alertMessage("数据库连接失败，请稍后再试")
            }
		})
	});
	//根据项目加载楼号，
    var itemId;
	$("#project").combobox({
        url: '<%=basePath %>sourceHouse/findSourceHouses',
        valueField: 'id',
        textField: 'text',
        onLoadSuccess: function (data) { //默认选中第一条数据
            bulidArray=[];
            if (data.length > 0) {
                $('#project').combobox('setValue', data[0].id);
            }
        },
        onChange: function () {
            bulidArray=[];
			itemId=$('#project').combobox('getValue');
            //根据楼号加载房子信息
             setBuild(itemId);
        }
    });
	//全选
	$("#checkALL").change(function(){
	    if($(this).is(':checked')){ //选中
          $('.houseContainer .housecheck input[type="checkbox"]').each(function(){
              if($(this).prop('disabled')){
                  $(this).prop("checked",false);
              }else{
                  $(this).prop("checked",true);
                  var houseid=$(this).parent().siblings('.houseCode').attr('data-houseId');
                  if($.inArray(houseid,bulidArray)==-1){
                      bulidArray.push(houseid)
				  }
              }
          })
		}else{
            $('.houseContainer .housecheck input[type="checkbox"]').each(function(){
                $(this).prop("checked",false);
                var houseid=$(this).parent().siblings('.houseCode').attr('data-houseId');
                if($.inArray(houseid,bulidArray)!=-1){
                    var index=bank.getArrayIndex(bulidArray,houseid);  //获取index
                    bulidArray.splice(index,1);
                }
            })
		}
	});
	//反选
    $("#uncheckAll").change(function(){
        if($(this).is(':checked')){ //选中
            $('.houseContainer .housecheck input[type="checkbox"]').each(function(){
                if($(this).prop('disabled')){
                    $(this).prop("checked",false);
                }else{
                    $(this).prop("checked",false);
                    var houseid=$(this).parent().siblings('.houseCode').attr('data-houseId');
                    if($.inArray(houseid,bulidArray)!=-1){
                        var index=bank.getArrayIndex(bulidArray,houseid);  //获取index
                        bulidArray.splice(index,1);
                    }
                }
            })
        }else{//反选
            $('.houseContainer .housecheck input[type="checkbox"]').each(function(){
                if($(this).prop('disabled')){
                    $(this).prop("checked",false);
                }else{
                    $(this).prop("checked",true);
                    var houseid=$(this).parent().siblings('.houseCode').attr('data-houseId');
                    if($.inArray(houseid,bulidArray)==-1){
                        bulidArray.push(houseid)
                    }
                }
            })
        }
    });
    $('body').on('change','input[type=checkbox]',function(){
        var houseid=$.trim($(this).parent().siblings('.houseCode').attr("data-houseId"));
        if($(this).is(':checked')){
            bulidArray.push(houseid);
        }else{
            var index= bank.getArrayIndex(bulidArray,houseid);
            bulidArray.splice(index,1)
        }
    });
   /* $("body").on('click','.houseContainer .nocheck',function(){
        var houseid= $(this).find('.houseCode').attr('data-houseId');
        $(this).find('.choose').prop('checked',true);
        //在数组中，不加入，不在数组中加入
        if($.inArray(houseid,bulidArray)==-1){
            bulidArray.push(houseid);
        }
    });
    $("body").on('dblclick','.houseContainer .nocheck',function(){
        var houseid= $(this).find('.houseCode').attr('data-houseId');
        $(this).find('.choose').prop('checked',false);
        //从数组中移除
        if($.inArray(houseid,bulidArray)!=-1){
            var index=bank.getArrayIndex(bulidArray,houseid);  //获取index
            bulidArray.splice(index,1);
        }
    });*/
    //根据项目加载楼号
    function setBuild(itemId){
        $(".build").html("");
        $.ajax({
            url: '<%=basePath %>BuidInfo/findBuildInfoByMap',
            type: 'post',
            dataType: 'json',
            data: {
                itemId: itemId
            },
            success: function (data) {
                for(var i=0;i<data.length;i++){
                    $(".build").append('<li id="'+ data[i].buildinginfoId+'">'+ data[i].buname+'</li>');
                    if(i==0){
                        $('#'+ data[i].buildinginfoId+'').addClass('buildSelect').siblings().removeClass('buildSelect');
                        setHouse(data[i].buildinginfoId)
					}
				}
            },
            error: function () {
                bank.alertMessage("数据库连接失败，请稍后再试！")
            }
        });
    }
    //点击左侧，根据楼号查询
    $("body").on('click','.build li',function(){
        $(this).addClass('buildSelect').siblings().removeClass('buildSelect');
        bulidArray=[];//重置数组；
        var buildid =$(this).attr('id');
         setHouse(buildid);
        $('.over').show();
    });
	 //根据楼号加载房子
    function setHouse(buildid){
        $('.houseContainer').html("");
        $.ajax({
            url:'<%=basePath%>FactMapping/findFactByBuildingInfoId',
            type:'post',
            dataType:'json',
			data:{
                buildid:buildid
            },
            success:function(data){
                if(data.length>0){
                    for(var i=0;i<data.length;i++){
                        if(data[i].rationflag=="TRUE"){
                            $('.houseContainer').append(' <li class="houseItem nocheck">' +
                                '<p class="housecheck"><input type="checkbox" class="choose"><span>'+data[i].fylxname+'</span></p>' +
                                '<p class="houseCode" data-houseId="'+data[i].factmappingId+'">'+data[i].fCondonum+'</p>' +
                                '<p class="houseArea">'+data[i].fConacre+'m²</p>' +
                                '</li>')
                        }else{
                            $('.houseContainer').append(' <li class="houseItem hasCheck">' +
                                '<p class="housecheck"><input type="checkbox" disabled><span>'+data[i].fylxname+'</span></p>' +
                                '<p class="houseCode" data-houseId="'+data[i].factmappingId+'">'+data[i].fCondonum+'</p>' +
                                '<p class="houseArea">'+data[i].fConacre+'m²</p>' +
                                '</li>')
						}

					}
				}
                //加载并回填已选项目
                $('.over').hide();
            },error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试！")
            }
        })
	}
    //分配房子
    function ration(){
        if(isClick){
            if($.trim($("#project").combobox('getValue')).length==0){
                bank.alertMessage("请选择项目!")
            }else if($.trim($('input[name="area"]:checked').val()).length==0){
                bank.alertMessage("请选择分配区!")
            }else if(bulidArray.length==0){
                bank.alertMessage("请选择待分配的房子!")
            }else{
                isClick=false;
                //提交
                $.ajax({
                    url:'<%=basePath %>sourceHouse/updateHouses',
                    type:'post',
                    data:{
                        ssq:$.trim($("input[name='area']:checked").val()),//分配区
                        houseid:bulidArray.toString()
                    },
                    success:function(data){
                        bank.ajaxMessage(data);
                        if(data=="配给成功"){
                            isClick=true;
                            bulidArray=[];
                            $('#project').combobox('reload','<%=basePath %>sourceHouse/findSourceHouses');
                            setBuild(itemId);
                        }
                    },error:function(){
                        bank.alertMessage("数据库连接失败，请稍后再试！")
                    }

                })
            }
		}

    }
</script>
</html>