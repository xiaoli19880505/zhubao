<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<header>
    <script type="text/javascript" src="<%=basePath %>src/js/webuploader.js"></script>
    <link rel="stylesheet" href="<%=basePath %>src/css/webuploader.css" />
</header>
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
    <div class="easyui-panel" style="width: 100%;overflow: hidden">
        <form id="queryForm">
            <ul class="search-group">
                <li><input type="radio" name="all" value="0" checked="true"><label class="lispan">节假日</label>
                    <span class="lispace"></span>
                    <input type="radio" name="all" value="1"><label class="lispan">特殊工作日</label>
                </li>
                <li class="readonly">
                    <input class="easyui-combobox" name="year" id="year" style="width: 92%" data-options="label:'年份:',panelHeight:'auto',panelMaxHeight:400,
					editable:false,valueField:'id',textField:'name',onSelect:select">
                </li>
                <li><input class="easyui-datebox" id="date" name="afgLdhtjssj" style="width: 80%" data-options="label:'日期查询:',required:true,editable:false"></li>
                <li class="query-btn">
                    <a class="icon iconfont icon-sousuo" onclick="query()"><i>提&nbsp;交</i></a>
                </li>
            </ul>
        </form>
        <div class="abtn-group">
            <a class="icon iconfont icon-xinzeng green" onclick="addHoliday()"><i>新增节假日</i></a>
            <a class="icon iconfont icon-xiugaiziliao golden" onclick="addWeekday()"><i>新增特殊工作日</i></a>
            <a class="icon iconfont icon-icon-refresh green" onclick="bank.tbReload('dataTable')"><i>刷新</i></a>
            <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.closeCurrent()"><i>关闭</i></a>
        </div>
    </div>
</div>
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
               data-options="url:'<%=basePath %>holiday/findHoliday',singleSelect:true,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',pageSize:20,nowrap:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'fullDate',width:120,align:'center',halign:'center'">日期</th>
                <th data-options="field:'isWeekday',width:100,align:'center',formatter:convert">是否是假节日</th>
                <th data-options="field:'insertTime',width:100,align:'center'">插入时间</th>
                <th data-options="field:'remark',width:100,align:'center'">相关描述</th>
                <th data-options="field:'hid',width:200,align:'center',formatter:operate">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script>
    
    $(function () {
       // $("#year").combobox("loadData",[{a:1},{a:2}]);
        $("#year").combobox("loadData",[{id:2018,name:'2018'}
            ,{id:2019,name:'2019'},{id:2020,name:'2020'}]);
    });
    
    //为街道和街区默认赋值
    function convert(val,row,index){
        if(val=="0"){
            return "节假日";
        }else{
            return "工作日";
        }
    }

    //为街道和街区默认赋值
    function operate(val,row,index){
        return '<a class="agreen" onclick="del(\''+val+'\')">删除</a> ';
    }

    /*删除节日信息*/
    function del(id) {
        $.post("<%=basePath%>holiday/deleteSetDay",
            {holid:id},
             function (data) {
             bank.ajaxMessage(data);
            if(data.indexOf("成功")!=-1){
                $("#dataTable").datagrid("reload");
            }
        },"json");
    }

    //根据条件查询
    function query(){
        $("#dataTable").datagrid('load',{
            year:$.trim($("#year").combobox("getValue")),
            type:$.trim($("input[name='all']:checked").val()),
            date:$.trim($("#date").val())
        });
    }

    //选择日期类型
    $("input[name='all']").change(function(){
        $("#dataTable").datagrid('load',{
            type:$.trim($("input[name='all']:checked").val())
        })
    })

    /*新增节假日*/
    function addHoliday() {
        bank.showWindow('<%=basePath%>holiday/toAddHoliday', "新增节假日",740, 450, true,"reload");
    }

    /*新增工作日*/
    function addWeekday() {
        bank.showWindow('<%=basePath%>holiday/toAddWeekday', "新增特殊工作日",740, 450, true,"reload");
    }

    function select(){

    }




</script>
<style>
    .webuploader-pick {
        background:none !important;
        position: relative;
        top:-10px;
    }

</style>
</html>