<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
    <div class="easyui-panel" style="width: 100%;overflow: hidden">
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
            <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
    </div>
</div>
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
               data-options="url:'',singleSelect:false,rownumbers:true,pagination:false,border:true,striped:true,
                       pagePosition:'bottom',nowrap:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'fCommunity',width:150,align:'center',halign:'center'">项目名称</th>
                <th data-options="field:'icDist',width:100,align:'center',halign:'center'">所属区县</th>
                <th data-options="field:'fItsitedetail',width:300,align:'center',halign:'center'">位置</th>
                <th data-options="field:'fRoomAddress',width:100,align:'center',halign:'center'">楼号</th>
                <th data-options="field:'fConacre2',width:100,align:'center',halign:'center'">面积(㎡)</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script>
    //数据回显
    var showArray=[];
    var objId;
    var apSqlb;
    $(function () {
        var data =bank.biography().getParams('idData');
        objId = data.row.objId;
        apSqlb = data.row.apSqlb;
        $('#dataTable').datagrid({
            url:'<%=basePath %>contractTemplate/getFactMappings',
            queryParams:{
                apSqlb:apSqlb,
                objId:objId
            },
            onSelect:function(rowIndex, rowData){
                if($.inArray(rowData.factmappingId,showArray)==-1){
                    showArray.push(rowData.factmappingId);
                }
            },
            onUnselect:function(rowIndex, rowData){
                $.each(showArray,function(index,value){
                    if(rowData.factmappingId==value){
                        showArray.splice(index, 1);
                    }
                });
            },
        });
    })
    //保存
    function  save() {//保存
        $.ajax({
            type:"post",
            url:"<%=basePath %>contractTemplate/getFactMappings",
            async:true,
            dataType:'json',
            data:{
                apSqlb:apSqlb,
                objId:objId
            },
            success:function (data) {
                flag = data.flag;
                if(flag == true){
                    into();
                }
            }
        });
    }
    function into() {
        $.ajax({
            type:"post",
            url:"<%=basePath %>contractTemplate/toPath",
            async:false,
            data:{
                apSqlb:apSqlb,
                objId:objId,
                fmIds:showArray
            },
            dataType:'json',
            success:function (datas) {
                var path=$.trim(datas.path);
                if(datas.flag){
                    var a = $('<a href="<%=basePath %>'+path+'#true" target="_blank"></a>')[0];
                    var e = document.createEvent('MouseEvents');
                    e.initEvent('click', true, true);
                    a.dispatchEvent(e);
                }
            }
        });
    }
</script>