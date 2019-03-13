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
		<div class="layout articles">
			<p1 class="title" id="text"> </p1>
			<div class="fujian">
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
	$(function () {
        $.ajax({
            type:"post",
            url:"<%=basePath %>columnInfo/selectColumnInfo",
            async:true,
            success:function(data){
                var list ="";
                var data = $.parseJSON(data);
                for (var i = 0;i<data.length;i++) {
                    list += '<li>' +
                        '<a href="<%=basePath %>pageColumn/external/h_active?id='+data[i].id+'" class="trans" title="'+data[i].columnName +'">' + data[i].columnName + '</a>' +
                        '</li>';
                }
                $("#first").append(list);
            }
        });
        //新闻展示
        var id = window.location.search.substr(4);
        $.ajax({
            type:"post",
            url:"<%=basePath %>articleInfo/selectArticleInfoById",
            async:true,
			data:{
                id:id
			},
			success:function (data) {
                var data = $.parseJSON(data);
                if(data.articleInfo.articleBody == undefined){
                    articleBody = '';
				} else {
                    articleBody = data.articleInfo.articleBody;
				}
                var list = '<h3 class="title" style="letter-spacing: 2px;">发表时间：' + data.articleInfo.createTimeStr + '&nbsp;&nbsp;作者：' + data.articleInfo.author + '&nbsp;&nbsp;阅读量：' + data.articleInfo.clickNumber + '</h3>' + '<div class="content">' +'<h1 class="title" style="color: #000;font-size: 24px;font-weight: 500;line-height: 150%;letter-spacing: 1px;margin-bottom: 20px;text-shadow:none"> ' +
                    data.articleInfo.articleName + '</h1>' + articleBody + '</div>';
                if (data.annexFiles.length>0) {
                    var annexFiles=data.annexFiles;
                    for (var i = 0;i<annexFiles.length;i++) {
						$(".fujian").append('<a href="<%=basePath %>file/getFileByIdUrl?fileId='+annexFiles[i].id+'">' + annexFiles[i].fileName +
                        '</a>')
					}
				}
                $("#text").append(list);
                $("#title").text(data.articleInfo.columnInfo.columnName);
            }
		})
    })
    //查询功能
    $(".find").click(function () {
        var articleName  = $(".txt_search").val();
        window.location.href = "<%=basePath %>pageColumn/external/h_search?articleName=" +articleName;
    })
</script>