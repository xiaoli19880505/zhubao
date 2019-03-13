<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>徐州市住房保障服务中心</title>
		<link rel="stylesheet" href="<%=basePath %>src/css/index.css" />
		<script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/sgallery.js"></script>
		<script type="text/javascript" src="<%=basePath %>src/js/index.js"></script>
		<link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">
	</head>

	<body>
		<!--head-->
		<div class="layout header">
			<div class="logo"><img src="<%=basePath %>src/img/home/logo.png"></div>
			<h1>徐州市住房保障服务中心</h1>
			<h2>Xuzhou Housing Security Service Center</h2>
		</div>
		<!--head end-->
		<div id="menu_container_id">
			<div class="layout menus">
				<ul id="first">
					<li >
						<a href="<%=basePath %>pageColumn/external/h_index" class="trans" title="网站首页">网站首页</a>
					</li>
				</ul>
				<!--搜索-->
				<div class="form">
					<input type="text" class="txt_search" placeholder="请输入关键字" />
					<input type="image" src="<%=basePath %>src/img/home/tj.png" class="find" style="position:relative;top: -27px;left: 180px;" />
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="layout sub-nva">
			当前位置：
			<a href="<%=basePath %>/pageColumn/external/h_index">首页</a> >
			<a href="#" id="title"></a>
		</div>
		<div class="layout mb30">
			<!--左侧-->
			<div class="sub-left" id="scrollleft">
				<h1>当前位置</h1>
				<ul id="second">
				</ul>
			</div>
			<!--右侧-->
			<div class="sub-right">
				<div class="news-title">
					<h3></h3>
				</div>
				<div class="newslist">
					<ul id="thrid">
					</ul>
				</div>
				<!--分页-->
				<div class="pager">
					<span class="off" onclick="off()">上一页</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="off offNext" onclick="offNext()">下一页</span>
				</div>
				<!--分页 end-->
			</div>
		</div>
		<!--foot-->
		<div class="foot-wrap">
			<div class="copyright">
				<span style="font-size: 16px">版权所有：徐州市住房保障服务中心</span><br/>
				<span>友情链接：<a href="http://www.mohurd.gov.cn/" target="_blank">国家住建部</a>&nbsp;&nbsp;<a href="http://jsszfhcxjst.jiangsu.gov.cn/" target="_blank">江苏省住房和城乡建设厅</a>&nbsp;&nbsp;<a href="http://www.xz.gov.cn/" target="_blank">徐州市政府网</a>&nbsp;&nbsp;<a href="http://fg.xz.gov.cn/" target="_blank">徐州市房管局</a>&nbsp;&nbsp;<a href="http://www.xzjs.gov.cn/" target="_blank">徐州市城乡建设局</a></span><br>
				<span style="font-size: 16px">苏ICP备06020539号-3</span>
			</div>
		</div>
		<div class="bottom">

		</div>
		<!--foot end-->
	</body>

</html>
<script>
    $(".txt_search").val("");
	var smp = $('.sub-left').position();
	var rows;
	var num = 1;
	var id = "";
	$(window).scroll(function() {
		var wst = $(window).scrollTop();
		if(smp && wst >= smp.top - 100) {
			$('#scrollleft').css({
				'position': 'fixed',
				'top': '60px'
			});
		} else {
			$('#scrollleft').css({
				'position': 'absolute',
				'top': ''
			});
		}
	});
    $(function() {
        //头部导航拼接
        $.ajax({
            type:"post",
            url:"<%=basePath %>columnInfo/selectColumnInfo",
            async:true,
            success:function(data){
                var list ="";
                var data = $.parseJSON(data)
                for (var i = 0;i<data.length;i++) {
                    list += '<li>' +
                        '<a href="<%=basePath %>/pageColumn/external/h_active?id='+data[i].id+'" class="trans" title="'+data[i].columnName +'">' + data[i].columnName + '</a>' +
                        '</li>';
                }
                $("#first").append(list);
                $("#second").append(list);
            }
        });
        //截取id
        id = window.location.search.substr(4);
        list(id);
    })
	function off() {
		num--;
		if (num <= 0) {
		    num = 1
		}
        list(id,num);
    }
    function offNext() {
		num++;
		if(num <= Math.ceil(rows/10)) {
            list(id,num);
		}else{
           	num =  Math.ceil(rows/10);
		}
    }
    function list(id,num) {
        $.ajax({
            type:"post",
            url:"<%=basePath %>articleInfo/selectArticleInfoes",
            async:true,
            data:{
                columnInfoId :id,
				page:num,
                rows:10
            },
            success:function(data){
                var list ="";
                var data = $.parseJSON(data);
                if(data.total != 0){
                    for (var i = 0;i<data.rows.length;i++) {
                        list += '<li>' +
                            '<a href="<%=basePath %>/pageColumn/external/h_text?id='+data.rows[i].id+'" class="trans" title="'+data.rows[i].articleName +'">' +
                            '<span>' + data.rows[i].createTimeStr +
                            '</span>' + data.rows[i].articleName +
                            '</li>'+'<li class="line"></li>';
                    }
                    $("#thrid").html(list);
                    $(".news-title h3").text(data.rows[0].columnInfo.columnName);
                    $("#title").text(data.rows[0].columnInfo.columnName);
                    rows = data.total;
                }
            }
        })
    }
    //查询功能
    $(".find").click(function () {
        var articleName  = $(".txt_search").val();
        window.location.href = "<%=basePath %>/pageColumn/external/h_search?articleName=" +articleName;
    })
</script>