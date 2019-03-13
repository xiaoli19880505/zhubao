<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="<%=basePath %>srcApply/js/jSignature.min.noconflict.js"></script>
<body class="easyui-layout">
    <div data-options="region:'center',border:false">
        <div style="overflow-x: auto;height:100%;" >
            <div class="abtn-group btnInner">
                <a class="icon iconfont icon-xinzeng green" onclick="clearForm()"><i>重置</i></a>
                <a class="icon iconfont icon-icon- green" onclick="saveAdd()"><i>保存</i></a>
                <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
            </div>
            <div id="signatureparent">
                <div id="signature"></div>
            </div>
        </div>
    </div>
</body>
</html>
<style>
    #signatureparent {
        color: darkblue;
        background-color: darkgrey;
    }
</style>
<script>
    //初始化签字插件
    var $sigdiv = '';
    $(function () {
        localStorage.removeItem('signApply');
        $sigdiv = $("#signature").jSignature({
            'UndoButton': false,
            'height':360,
            'width':720
        })
    })
    //重置签名区域
    function clearForm() {
        $sigdiv.jSignature('reset');
    }
    //保存签名
    function saveAdd() {
        var data=$sigdiv.jSignature('getData', 'svgbase64');//'image/svg+xml;base64';
        var img = 'data:' + data[0] + ',' + data[1]
        var applySign = '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\"><svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"0\" height=\"0\"></svg>'
        if(applySign != data[1]){
            bank.biography().setParams({row:img,title:'signApply'});
            parent.$('.bankWindow').dialog("close");
        } else {
            bank.alertMessage("请输入本人姓名");
        }
    }
</script>