<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div class="easyui-panel p5" style="width: 100%;height:98%;overflow: auto">
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-icon- green" onclick="save()"><i>保存</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
        <table id="dataTable" class="easyui-datagrid resTable" style="height:88%" data-options="rownumbers:true,pagination:true,
					border:true,striped:true,pagePosition:'bottom',pageSize:10,nowrap:true,fitColumns:true, onDblClickRow:onClickRow">
            <thead>
            <tr>
                <th data-options="field:'username',width:100,align:'center'">申请人</th>
                <th data-options="field:'sfzh',width:100,align:'center'">身份证号</th>
                <th data-options="field:'qysj',width:100,align:'center'" >签约时间</th>
                <th data-options="field:'jzsj',width:100,align:'center'">截止时间</th>
                <th data-options="field:'sfsj',width:100,align:'center',editor:{type:'datebox',options:{required:true,editable:false}}">上房时间</th>
                <th data-options="field:'tfsj',width:100,align:'center'" >退房时间</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script>
$(function () {
    var row=bank.biography().getParams("dishistory");
    $("#dataTable").datagrid({
        url:'<%=basePath %>hisRent/findByFwId?fwid='+row.row
    })
});
    //编辑
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){
        return true
    }
    if ($('#dataTable').datagrid('validateRow', editIndex)){
        $('#dataTable').datagrid('endEdit', editIndex);//此无效果
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickRow(index,row){
        if (editIndex != index){
            if (endEditing()){
                $('#dataTable').datagrid('selectRow', index).datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#dataTable').datagrid('selectRow', editIndex);
            }
        }
}
/*保存*/
function save() {
    var ed = $('#dataTable').datagrid('getEditors',editIndex);
    if(ed.length!=0){  //已编辑
        $('#dataTable').datagrid("endEdit", editIndex);//结束编辑
        editIndex = undefined;
        var datas = $('#dataTable').datagrid('getData');
        var data=JSON.stringify(datas.rows);
        $.ajax({
            url:'<%=basePath %>hisRent/updateSFSJBySqid',
            type:"post",
            data:{jsonStr:data},
            success:function(data){
                bank.ajaxMessage(data);
                setTimeout(function () {
                    window.close();
                    opener.location.reload();
                },1000);
            },error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试！")
            }
        })
    }
}
</script>
</html>

