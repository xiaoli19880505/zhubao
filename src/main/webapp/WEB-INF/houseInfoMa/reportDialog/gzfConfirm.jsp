<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout" id="house">
<div class="over" style="display: none;">
    <div id="loading"><img src="<%=basePath %>srcApply/img/public/loading.gif" ></div>
</div>
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
    <div class="search-group" style="float: right;width:180px;margin-right: 50px">
        <li class="query-btn" style="float: right"><a class="icon iconfont icon-icon- " onclick="save()"><i>打印</i></a></li>
    </div>
</div>
<div data-options="region:'center',border:false">
    <form class='easyui-form' id='form' method='post'>
        <div class="contract">
            <div class="conPage">
                <div class="conTitle">2018年徐州市公共租赁住房（${ssq}）</div>
                <div class="conTitle">（存根联）</div>
                <div class="conRole">
                    <span class="rolespan">申请人：${username}身份证号：${sfzh}</span>
                </div>
                <div class="conContent">
                    &nbsp;&nbsp;&nbsp;&nbsp;所选房号：<span class="conUnder">${factMapping.itName}</span>小区
                    <span class="conUnder">${factMapping.fBuname}</span>号楼
                    <span class="conUnder">${factMapping.fCecode}</span>单元
                    <span class="conUnder">${factMapping.fRonum}</span>室，
                    建筑面积<span class="conUnder">${factMapping.conacre}</span> 平方米。
                </div>
                <div class="conContent">
                    申请人签字：<span class="conUnder">&nbsp;       &nbsp; &nbsp;       &nbsp;</span>
                </div>
                <div class="conCode">徐州市${ssq}房产服务中心   </div>
                <div class="conCode" style="margin-top: 10px">年 月 日</div>

                <div style="border: dashed 1px #000;margin:50px 0"></div>

                <div class="conTitle">2018年徐州市公共租赁住房（${ssq}）</div>
                <div class="conRole">
                    <span class="rolespan">申请人：${username}身份证号：${sfzh}</span>
                </div>
                <div class="conContent">
                    &nbsp;&nbsp;&nbsp;&nbsp;所选房号：<span class="conUnder">${factMapping.itName}</span>小区
                    <span class="conUnder">${factMapping.fBuname}</span>号楼
                    <span class="conUnder">${factMapping.fCecode}</span>单元
                    <span class="conUnder">${factMapping.fRonum}</span>室，
                    建筑面积<span class="conUnder">${factMapping.conacre}</span> 平方米。
                </div>
                <div class="conContent">
                    申请人签字：<span class="conUnder">&nbsp;       &nbsp; &nbsp;       &nbsp;</span>
                </div>
                <div class="conCode">徐州市${ssq}房产服务中心   </div>
                <div class="conCode" style="margin-top: 10px">年 月 日</div>
                <input type="hidden" name="atype" value="${atype}"/>
                <input type="hidden" name="year" value="${year}"/>
                <input type="hidden" name="ssq" value="${ssq}"/>
                <input type="hidden" name="username" value="${username}"/>
                <input type="hidden" name="sfzh" value="${sfzh}"/>
                <input type="hidden" name="num" value="${factMapping.num}"/>
                <input type="hidden" name="itName" value="${factMapping.itName}"/>
                <input type="hidden" name="fBuname" value="${factMapping.fBuname}"/>
                <input type="hidden" name="fCecode" value="${factMapping.fCecode}"/>
                <input type="hidden" name="fRonum" value="${factMapping.fRonum}"/>
                <input type="hidden" name="conacre" value="${factMapping.conacre}"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<style>
    .contract .conUnder {
        border-bottom: solid 1px #000;
    }
</style>
<script>
    function save(){
        $('.over').show();
        bank.ajaxForm('#form','<%=basePath %>sourceHouse/getConfirmFile',{},function(data){
            //提交表单
            // bank.ajaxMessage(data)
            var a = $('<a href="<%=basePath %>'+data+'" target="_blank"></a>')[0];
            var e = document.createEvent('MouseEvents');
            e.initEvent('click', true, true);
            a.dispatchEvent(e);
            setTimeout(function () {
                $('.over').hide();
            },2000)
        });
    }
</script>
