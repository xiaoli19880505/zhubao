<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>我的申请</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/common.css"/>
</head>
<body>
	<!-- 主界面不动、菜单移动 -->
	<!-- 侧滑导航根容器 -->
	<div class="mui-off-canvas-wrap mui-draggable mui-slide-in">
	  <!-- 菜单容器 -->
	  <aside class="mui-off-canvas-left" id="offCanvasSide">
	    <div class="mui-scroll-wrapper">
	      <div class="mui-scroll">
	        <!-- 菜单具体展示内容 -->
	        <ul class="mui-table-view">
			    <li class="mui-table-view-cell leftList" id="applyAside">
			        <a class="mui-navigate-right" >我的申请</a>
			    </li>
			    <li class="mui-table-view-cell leftList" id="audioAside">
			        <a class="mui-navigate-right" >我的年审</a>
			    </li>
			    <li class="mui-table-view-cell leftList" id="userAside">
			        <a class="mui-navigate-right" >个人信息</a>
			    </li>
			    <li class="mui-table-view-cell leftList" id="updateAside">
			        <a class="mui-navigate-right" >修改密码</a>
			    </li>
				<li class="mui-table-view-cell leftList" id="exitAside">
					<a class="mui-navigate-right" >退出</a>
				</li>
			</ul>
	      </div>
	    </div>
	  </aside>
	  <!-- 主页面容器 -->
	  <div class="mui-inner-wrap">
	    <!-- 主页面标题 -->
	    <header class="mui-bar mui-bar-nav">
	      <a class="mui-icon mui-action-menu mui-icon-bars mui-pull-left" href="#offCanvasSide"></a>
	      <h1 class="mui-title">我的申请</h1>
	    </header>
	   <nav class="mui-bar mui-bar-tab">
		    <a class="mui-tab-item mui-active" id="apply">
		        <span class="mui-icon mui-icon-home"></span>
		        <span class="mui-tab-label">我的申请</span>
		    </a>
		    <a class="mui-tab-item"  id="audit">
		        <span class="mui-icon mui-icon-list"></span>
		        <span class="mui-tab-label">我的年审</span>
		    </a>
		    <a class="mui-tab-item"  id="userInfo">
		        <span class="mui-icon mui-icon-contact"></span>
		        <span class="mui-tab-label">个人信息</span>
		    </a>
		    <a class="mui-tab-item"  id="updatePwd">
		        <span class="mui-icon mui-icon-gear"></span>
		        <span class="mui-tab-label">修改密码</span>
		    </a>
		</nav>
	    <div class="mui-content mui-scroll-wrapper" id="freshContainer">
	      <div class="mui-scroll" >
	        <!-- 主界面具体展示内容 -->
	        <ul class="mui-table-view ullist"></ul>
			<div class="mui-row" id="clickFlag" style="text-align: center;margin-top: 2rem;display: none" >
				<a href="#popover" id="openPopover" class="mui-btn mui-btn-primary ">新增申请</a>
			</div>
	      </div>
	    </div>  
	    <div class="mui-off-canvas-backdrop"></div>
	  </div>
	</div>
    <div id="popover" class="mui-popover">
		<ul class="mui-table-view">
           <c:if test="${appType==null}">
			<li class="mui-table-view-cell apptype" id="type1"><a >经济适用住房申请</a></li>
			<li class="mui-table-view-cell apptype" id="type5"><a >公共租赁住房（新就业人员）</a></li>
			<li class="mui-table-view-cell apptype" id="type4"><a >公共租赁住房（外来务工人员）</a></li>
		   </c:if>
           <c:if test="${appType==null || appType =='2'}">
			<li class="mui-table-view-cell apptype" id="type3"><a >公共租赁住房（低保、特困家庭）</a></li>
		   </c:if>
           <c:if test="${appType==null || appType=='3'}">
			<li class="mui-table-view-cell apptype" id="type2"><a>公共租赁住房租赁补贴</a></li>
		   </c:if>
		</ul>
	</div>
    <script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>srcApp/js/public/public.js" type="text/javascript" charset="utf-8"></script>
	<script>
		var page=1;total=1;
		mui.init({
            pullRefresh : {
                container:"#freshContainer",//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
                up:{
                    height:50,
                    contentdown : "上拉加载下一页",
                    contentrefresh: '正在加载...',
                    contentnomore:'没有更多数据了',
                    callback:loadNext //上拉加载下一页
                },
                down : {
                    height:50,
                    contentdown : "下拉加载上一页",
                    contentover : "释放立即刷新",
                    contentrefresh : "正在加载...",
                    callback :loadBefore //下拉加载上一页
                }
            }
		});
        //底部导航跳转
        mui(".mui-bar").on('tap', '.mui-tab-item', function(e) {
            var target = this.getAttribute("id");
            switch(target) {
                case 'apply':
                    weixin.openWindow('applyList','<%=basePath %>appPath/applyList');
                    break;
                case 'audit':
                    weixin.openWindow('audioList','<%=basePath %>appPath/audioList');
                    break;
                case 'userInfo':
                    weixin.openWindow('userInfo','<%=basePath %>appPath/userInfo');
                    break;
                case 'updateAside':
                    weixin.openWindow('updatePwd','<%=basePath %>appPath/updatePwd');
                    break;
                default:
                    break;
            }
        });
        //侧边栏跳转
        mui(".mui-table-view").on('tap', '.leftList', function(e) {
            var target = this.getAttribute("id");
            switch(target) {
                case 'applyAside':
                    weixin.openWindow('applyList','<%=basePath %>appPath/applyList');
                    break;
                case 'audioAside':
                    weixin.openWindow('audioList','<%=basePath %>appPath/audioList');
                    break;
                case 'userAside':
                    weixin.openWindow('userInfo','<%=basePath %>appPath/userInfo');
                    break;
                case 'updateAside':
                    weixin.openWindow('updatePwd','<%=basePath %>appPath/updatePwd');
                    break;
                case 'exitAside':
                    window.location.href="<%=basePath %>appLogin/appExit";
                    break;
                default:
                    break;
            }
        });
        //申请方式跳转
        mui(".mui-table-view").on('tap', '.apptype', function(e) {
            var target = this.getAttribute("id");
            switch(target) {
                case 'type1':
                    weixin.openWindow('type1','<%=basePath %>appApply/toapptype?applytype=1');
                    break;
                case 'type2':
                    weixin.openWindow('type2','<%=basePath %>appApply/toapptype?applytype=2');
                    break;
                case 'type3':
                    weixin.openWindow('type3','<%=basePath %>appApply/toapptype?applytype=3');
                    break;
                case 'type4':
                    weixin.openWindow('type4','<%=basePath %>appApply/toapptype?applytype=4');
                    break;
                case 'type5':
                    weixin.openWindow('type5','<%=basePath %>appApply/toapptype?applytype=5');
                    break;
                default:
                    break;
            }
        });
		//点击查看详情页
        mui(".ullist").on('tap','.listDetail',function(){
			   var id = this.children[0].innerHTML;
			   var sfzh=this.children[0].getAttribute('data-sfzh');
			 //打开详情页面
               weixin.openWindow('applyDetail','<%=basePath %>appApply/h5FindPerApply?sqbh='+id+'&sfzh='+sfzh+'');
		});
        //上拉加载下一页
        function loadNext(){//多次触发
            var allPage=Math.ceil(total/10);
            if(page>=1&&(page<allPage)){
                page++;
                loadData(page);
                this.endPullupToRefresh(false);
			} else{
                loadData(page);
                this.endPullupToRefresh(false);
			}
        }
        //下拉加载上一页
        function loadBefore(){
            var allPage=Math.ceil(total/10);
            if(page>1&&(page<=allPage)){
                 page--;
                 loadData(page);
                this.endPulldownToRefresh(false);
			 }else{
                loadData(page);
                this.endPulldownToRefresh(false);
			}
        }
        //加载数据方法
		function loadData(page){
            $.ajax({
                url:'<%=basePath %>appove/findApplyByUserSfzh',
                type:'post',
                dataType:'json',
                data:{
                    rows:'10',
                    page:page.toString()
                },
                success:function(data){
                    total=data.total;
                    if(data.rows.length>0){
                        $(".ullist").html("");
                        for(var i=0;i<data.rows.length;i++){
                            var state=(data.rows)[i].state;
                            if(state=='' || state==null){
                                $(".ullist").append('<li class="mui-table-view-cell listDetail">'+(data.rows)[i].aptype+'<span class="nbspan" data-sfzh="'+(data.rows)[i].sfzh+'">'+(data.rows)[i].aplbh+'</span><span class="mui-badge mui-badge-danger">审核中</span></li>');
                            }else if(state.indexOf("审批通过")!=-1){
                                $(".ullist").append('<li class="mui-table-view-cell listDetail">'+(data.rows)[i].aptype+'<span class="nbspan" data-sfzh="'+(data.rows)[i].sfzh+'">'+(data.rows)[i].aplbh+'</span><span class="mui-badge mui-badge-danger">审核中</span></li>');
                            }else{
                                $(".ullist").append('<li class="mui-table-view-cell listDetail">'+(data.rows)[i].aptype+'<span class="nbspan" data-sfzh="'+(data.rows)[i].sfzh+'">'+(data.rows)[i].aplbh+'</span><span class="mui-badge mui-badge-success">'+state+'</span></li>');
                            }
                        }
                    }
                }
            });

        }
        //申请单列表展示
        $(function () {
			//判断是否可以请求
            $.ajax({
                url:'<%=basePath %>appApply/toAppForm',
                type:'post',
                success:function(data){
                    if(data=="success"){
                       $("#clickFlag").show()
                    }else{
                        weixin.alert(data)
					}
                }
            });
            loadData('1');
        })
	</script>
</body>
</html>

