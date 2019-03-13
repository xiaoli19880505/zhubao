<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="overflow: hidden;">
			<div class="easyui-panel">
				<form id="queryForm">
					<ul class="search-group">
						<li style="width: 380px"><input class="easyui-datebox" id="time" style="width: 92%" data-options="label:'请选择时间:',required:true,editable:false"></li>
						<li style="width: 380px"><input class="easyui-combobox" id="type" style="width: 92%" data-options="label:'请选择报表类型:',required:true,editable:false,
                         valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=25',required:true,editable:false,panelHeight:'auto',panelMaxHeight:'280'"></li>
						<li class="query-btn" onclick="query()">
							<a class="icon iconfont icon-20daochu" ><i>导出</i></a>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<div data-options="region:'center',border:false">

		</div>
	</body>

</html>
<style>
	.textbox-label{
		width: 120px;
	}
</style>
<script>
    $(function() {
        $('#time').datebox({
            onShowPanel: function () {
                span.trigger('click');
                if (!tds)
                    setTimeout(function() {
                        tds = p.find('div.calendar-menu-month-inner td');
                        tds.click(function(e) {
                            e.stopPropagation();
                            //得到年份
                            var year = /\d{4}/.exec(span.html())[0] ,
                                month = parseInt($(this).attr('abbr'), 10);
                            $('#time').datebox('hidePanel').datebox('setValue', year + '-' + month);
                        });
                    }, 0);
            },
            parser: function (s) {
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            formatter: function (d) {
                var currentMonth = (d.getMonth()+1);
                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
                return d.getFullYear() + '-' + currentMonthStr;
            }
        });
        var p = $('#time').datebox('panel'),
            tds = false,
            span = p.find('span.calendar-text');
        var curr_time = new Date();
        $("#time").datebox("setValue", myformatter(curr_time));
    });
    //格式化日期
    function myformatter(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        return y + '-' + m;
    }
	//导出
	function query(){
        var time=$.trim($("#time").datebox('getValue'));
        var type=$.trim($("#type").combobox('getValue'));
        if(time.length==0||type.length==0){
            bank.ajaxMessage("时间和类型均不能为空！")
		}else{
            time=time.split("-").join("");
           window.location.href="<%=basePath %>lowSecurityReport/report?yearAndMonth="+time+"&type="+type;
		}

	}

</script>