<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 引入el标识所需要的标签 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>徐州市市区公共租赁住房申请审核表（外来务工人员）</title>
	<script src="http://localhost:8000/CLodopfuncs.js"></script>
	<script src="http://localhost:18000/CLodopfuncs.js"></script>
	<script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
	<script language="javascript" type="text/javascript">
        function print() {
            var LODOP=getCLodop();
            var headerHTML = '<html><head><title>徐州市市区公共租赁住房申请审核表（新就业人员）</title></head><body>'
                + '<div id = "contain" style="margin:0 auto;width:700px;"> <style type="text/css">'
                + 'table,tr,th,td,th{margin:0;padding:0;}'
                + 'table{width:100%;border-collapse:collapse;}'
                + 'table,table tr th, table tr td{border:1px solid #000;}'
                + 'table tr th,table tr td{line-height:20px;height:50px;text-align:center;font-weight:normal;font-size:12px;font-family:宋体;}'
                + '.td_1{width:1%;}'
                + '.td_6{width:6%;}'
                + '.td_10{width:10%;}'
                + '.td_12{width:12%;}'
                + '.td_13{width:13%;}'
                + '.td_15{width:15%;}'
                + '.td_18{width:18%;}'
                + '.td_20{width:20%;}'
                + '.td_25{width:25%;}'
                + '.td_30{width:30%;}'
                + '.td_35{width:35%;}'
                + '.td_36{width:36%;}'
                + '.td_40{width:40%;}'
                + '.td_44{width:44%;}'
                + '.td_50{width:50%;}'
                + '.td_60{width:60%;}'
                + '.td_75{width:75%;}'
                + '.td_80{width:80%;}'
                + '.td_90{width:90%;}'
                + '.td_94{width:94%;text-align:left;}'
                + '</style>';
            var footHTML = '</div></body></html>';
            var str1HTML=document.getElementById("contain1").innerHTML;
            LODOP.PRINT_INITA(1,1,800,600,"测试预览功能");
            LODOP.ADD_PRINT_HTM(30,5,"85%","100%",headerHTML+str1HTML+footHTML);
            LODOP.NewPage();
            var str2HTML=document.getElementById("contain2").innerHTML;
            LODOP.ADD_PRINT_HTM(30,5,"85%","100%",headerHTML+str2HTML+footHTML);
            LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",1); //预览包含背景图
            LODOP.PREVIEW();
        }
	</script>
	<style type="text/css">
		table,tr,th,td,th{margin:0;padding:0;}
		table{width:100%;border-collapse:collapse;}
		table,table tr th, table tr td{border:1px solid #000;}
		table tr th,table tr td{line-height:20px;height:40px;text-align:center;
			font-weight:normal;font-size:12px;font-family:'宋体';}
		.td_1{width:1%;}
		.td_6{width:6%;}
		.td_10{width:10%;}
		.td_12{width:12%;}
		.td_13{width:13%;}
		.td_14{width:14%;}
		.td_15{width:15%;}
		.td_18{width:18%;}
		.td_20{width:20%;}
		.td_25{width:25%;}
		.td_30{width:30%;}
		.td_35{width:35%;}
		.td_36{width:36%;}
		.td_40{width:40%;}
		.td_44{width:44%;}
		.td_50{width:50%;}
		.td_60{width:60%;}
		.td_75{width:75%;}
		.td_80{width:80%;}
		.td_90{width:90%;}
		.td_94{width:94%;text-align:left;}
		.bgImg{
			display: inline-block;
			width:140px;
			height:65px;
			-moz-background-size: 100% 100%;
			-o-background-size: 100% 100%;
			-webkit-background-size: 100% 100%;
			background-size: 100% 100%;
			background-repeat:no-repeat\9;
			background-image:none\9;
		}
	</style>
</head>

<body>
<div><button onclick="print()">打印</button></div>
<div id = "contain1" style="margin:0 auto;width:600px;">
	<h1 style="text-align:center;font-family:'宋体';">徐州市市区公共租赁住房申请审核表<br/>（外来务工人员）</h1>
	<div style="margin-right:2%;margin-bottom:10px;text-align:right;font:normal 16px '宋体';"> 单位名称（全称）${applyForForeign.affDwmc } 单位地址 ${applyForForeign.affDwdz } </div>
	<table style="">
		<tr>
			<td class="td_10" colspan="10">申请人姓名</td>
			<td class="td_15" colspan="15">${applyForForeign.applyFamilyMembers[0].afmXm }</td>
			<td class="td_15" colspan="15">性别</td>
			<td class="td_15" colspan="15">${applyForForeign.applyFamilyMembers[0].afmXb }</td>
			<td class="td_10" colspan="10">身份证号</td>
			<td class="td_35" colspan="35">${applyForForeign.applyFamilyMembers[0].afmSfzh }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">外地户籍地址</td>
			<td class="td_35" colspan="35">${applyForForeign.affWdhjdz }</td>
			<td class="td_10" colspan="10">婚姻状况</td>
			<td class="td_15" colspan="15">${applyForForeign.applyFamilyMembers[0].afmHyzk }</td>
			<td class="td_10" colspan="10">联系电话</td>
			<td class="td_15" colspan="15">${applyForForeign.applyFamilyMembers[0].afmLxdh }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">来徐居住时间</td>
			<td class="td_35" colspan="35">${applyForForeign.affLxjzsj }至今</td>
			<td class="td_25" colspan="25">劳动合同<br/>签订年限</td>
			<td class="td_25" colspan="25">${applyForForeign.affLdhtkssj }至${applyForForeign.affLdhtjssj }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">社会保险交纳情况</td>
			<td class="td_35" colspan="35">${applyForForeign.affSbjnsj }至今</td>
			<td class="td_25" colspan="25">住房公积金<br/>交纳情况</td>
			<td class="td_25" colspan="25">${applyForForeign.affGjjjnsj }至 　年　月</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">个人年<br/>收入</td>
			<td class="td_15" colspan="15">${applyForForeign.affGrnsr }元</td>
			<td class="td_10" colspan="10">家庭年<br/>收入</td>
			<td class="td_15" colspan="15">${applyForForeign.affJtnsr }元</td>
			<td class="td_10" colspan="10">家庭<br/>人口</td>
			<td class="td_15" colspan="15">${applyForForeign.affJtrks }人</td>
			<td class="td_10" colspan="10">人均月<br/>收入</td>
			<td class="td_15" colspan="15">${applyForForeign.affRjysr }元</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10" rowspan="2">现住<br/>房情<br/>况</td>
			<td class="td_36" colspan="36">房屋坐落</td>
			<td class="td_18" colspan="18">产权单位（人）</td>
			<td class="td_18" colspan="18">建筑面积（㎡）</td>
			<td class="td_18" colspan="18">人均建筑面积（㎡）</td>
		</tr>
		<tr>
			<td class="td_36" colspan="36">${applyForForeign.applyFamilyHouse.afhZl }</td>
			<td class="td_18" colspan="18">${applyForForeign.applyFamilyHouse.afhCqr }</td>
			<td class="td_18" colspan="18">${applyForForeign.applyFamilyHouse.afhMj }</td>
			<td class="td_18" colspan="18">${applyForForeign.applyFamilyHouse.afhRjmj }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">现住房性质<br/>（√）</td>
			<td class="td_18" colspan="18">直管公房（<c:if test="${applyForForeign.applyFamilyHouse.afhZfxz == 1}" >√</c:if>）</td>
			<td class="td_18" colspan="18">单位公房（<c:if test="${applyForForeign.applyFamilyHouse.afhZfxz == 2}" >√</c:if>）</td>
			<td class="td_18" colspan="18">租赁私房（<c:if test="${applyForForeign.applyFamilyHouse.afhZfxz == 3}" >√</c:if>）</td>
			<td class="td_18" colspan="18">自有私房（<c:if test="${applyForForeign.applyFamilyHouse.afhZfxz== 4}" >√</c:if>）</td>
			<td class="td_18" colspan="18">借住（<c:if test="${applyForForeign.applyFamilyHouse.afhZfxz == 5}" >√</c:if>）</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10" rowspan="6">就业<br/>地共<br/>同生<br/>活的<br/>家庭<br/>成员<br/>情况</td>
			<td class="td_10" colspan="10">与申请人关系</td>
			<td class="td_10" colspan="10">姓名</td>
			<td class="td_10" colspan="10">出生时间</td>
			<td class="td_10" colspan="10">婚姻状况</td>
			<td class="td_25" colspan="25">工作单位</td>
			<td class="td_15" colspan="15">身份证号</td>
			<td class="td_10" colspan="10">年收入（元）</td>
		</tr>
		<c:forEach items="${applyForForeign.applyFamilyMembers }" varStatus="i" var="item" >
			<c:if test="${item.afmXm != null}" >
				<c:if test="${i.index != 0}" >
					<tr>
						<td class="td_10" colspan="10">${item.afmYsqrgx }</td>
						<td class="td_10" colspan="10">${item.afmXm }</td>
						<td class="td_10" colspan="10">${item.afmCsny }</td>
						<td class="td_10" colspan="10">
							<c:choose>
								<c:when test="${item.afmHyzk==2 }">已婚</c:when>
								<c:when test="${item.afmHyzk==1 }">未婚</c:when>
								<%--<c:otherwise>已婚</c:otherwise>--%>
							</c:choose>
						</td>
						<td class="td_25" colspan="25">${item.afmGzdw }</td>
						<td class="td_15" colspan="15">${item.afmSfzh }</td>
						<td class="td_10" colspan="10">${item.afmNsr }</td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${item.afmXm == null}" >
				<tr>
					<td class="td_10" colspan="10"></td>
					<td class="td_10" colspan="10"></td>
					<td class="td_10" colspan="10"></td>
					<td class="td_10" colspan="10"></td>
					<td class="td_25" colspan="25"></td>
					<td class="td_15" colspan="15"></td>
					<td class="td_10" colspan="10"></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>	<br/><br/><br/>
</div>
<div id = "contain2" style="margin:0 auto;width:600px;">
	<table style="">
		<tr>
			<td class="td_20" colspan="20">街道办事<br/>处意见</td>
			<td class="td_80" colspan="80" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[0].apcComment!=null }" >
					${approveRecords[0].apcComment }
				</c:if>
				<c:if test="${approveRecords[0].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[0].nameUrl!=null }">
				<img src="<%=basePath%>${approveRecords[0].nameUrl }" style="width:140px;height:65px;">
			</c:if><br/>
				<span style="margin-left:75%;">
					<c:if test="${approveRecords[0].approvetimeStr!=null }" >
						${approveRecords[0].approvetimeStr }
					</c:if>
					<c:if test="${approveRecords[0].approvetimeStr==null }" >
						年 月 日
					</c:if>
				</span>
			</td>
		<tr>
			<td class="td_20" colspan="20">区住房保<br/>障部门审<br/>核意见</td>
			<td class="td_80" colspan="80" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[1].apcComment!=null }" >
					${approveRecords[1].apcComment }
				</c:if>
				<c:if test="${approveRecords[1].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[1].nameUrl!=null }">
				<img src="<%=basePath%>${approveRecords[1].nameUrl }" style="width:140px;height:65px;">
			</c:if><br/>
				<span style="margin-left:75%;">
				<c:if test="${approveRecords[1].approvetimeStr!=null }" >
					${approveRecords[1].approvetimeStr }
				</c:if>
				<c:if test="${approveRecords[1].approvetimeStr==null }" >
					年 月 日
				</c:if></span>
			</td>
		<tr>
			<td class="td_20" colspan="20">市住房保障<br/>服务中心备案意见</td>
			<td class="td_80" colspan="80" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[2].apcComment!=null }" >
					${approveRecords[2].apcComment }
				</c:if>
				<c:if test="${approveRecords[2].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[2].nameUrl!=null }">
				<img src="<%=basePath%>${approveRecords[2].nameUrl }" style="width:140px;height:65px;">
			</c:if><br/>
				<span style="margin-left:75%;">
						<c:if test="${approveRecords[2].approvetimeStr!=null }" >
							${approveRecords[2].approvetimeStr }
						</c:if>
						<c:if test="${approveRecords[2].approvetimeStr==null }" >
							年 月 日
						</c:if></span>
			</td>
		</tr>
	</table>
</div>
</body>
</html>