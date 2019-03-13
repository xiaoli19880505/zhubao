<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <form class="changeForm easyui-form" id="form" style="padding-top: 0">
                <ul class="reportUl">
                    <li class="readonly">
                        <input class="easyui-textbox" name="blgDesc" id="blgDesc" style="width: 100%;height: 300px" data-options="label:'',readonly:true,multiline:true">
                    </li>
                </ul>
        </form>
    </div>
</div>
</body>
<script>
    $(function () {
        var datas=bank.biography().getParams("creditContent");
        $.ajax({
            url:'<%=basePath %>blivegs/selectCXInfo',
            type:'post',
            dataType:'json',
            data:{
                blgId:datas.row.blgId
            },
            success:function(data){
                var s=[];
                for(var i=0;i<data.bliveGongsDetails.length;i++){
                 s.push((data.bliveGongsDetails)[i].blgdDesc)
                }
              $("#blgDesc").textbox('setValue',(s.toString()));
            },error:function(){
                bank.alertMessage("数据库连接失败，请稍后再试")
            }
        })
    });
</script>
</html>