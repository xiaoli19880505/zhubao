<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div style="overflow-x: auto;height:100%;" >
        <div id="signatureparent" style="width: 100%;height: 100%">

        </div>
    </div>
</div>
</body>
</html>
<style>
    #signatureparent img {
        margin-top: 30px;
    }
</style>
<script>
    $(function () {
        var data=bank.biography().getParams('searchSign');
        img = '<img src="'+data.row+'">';
        $('#signatureparent').html(img)
    })
</script>