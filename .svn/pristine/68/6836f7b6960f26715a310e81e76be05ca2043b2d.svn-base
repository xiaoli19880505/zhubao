<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 引入el标识所需要的标签 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<title>徐州市公共租赁住房租赁补贴申请审核表</title>
	<script src=”http://local:8000/CLodopfuncs.js”></script>
	<script src=”http://local:18000/CLodopfuncs.js”></script>
	<script type="text/javascript" src="<%=basePath %>srcApply/js/jquery.min.js"></script>
	<script language="javascript" type="text/javascript">
        function print() {
            var LODOP=getCLodop();
            var strHTML=document.getElementsByTagName("html")[0].innerHTML;
            LODOP.PRINT_INITA(1,1,950,800,"测试预览功能");
            LODOP.ADD_PRINT_HTM(30,5,"100%","100%",strHTML);
            LODOP.PREVIEW();
        }

	</script>
	<style type="text/css">
		table,tr,th,td,th{margin:0;padding:0;}
		table{width:100%;border-collapse:collapse;}
		table,table tr th, table tr td{border:1px solid #000;}
		table tr th,table tr td{line-height:40px;height:40px;text-align:center;
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
	</style>
</head>

<body>
<div><button onclick="print()">打印</button></div>
<div style="margin:0 auto;width:780px;">
	<h1 style="text-align:center;font-family:'宋体';">徐州市公共租赁住房租赁补贴申请审核表</h1>
	<div style="margin-left:50%;margin-bottom:10px;font:normal 16px '宋体';">${applyButie.ssqStr } ${applyButie.ssjStr }街道办事处 ${applyButie.abSqjwh }社区居委会</div>
	<table style="">
		<tr>
			<td class="td_10" colspan="10">申请人姓名</td>
			<td class="td_15" colspan="15">${applyButie.applyFamilyMembers[0].afmXm }</td>
			<td class="td_10" colspan="10">性别</td>
			<td class="td_15" colspan="15">${applyButie.applyFamilyMembers[0].afmXb }</td>
			<td class="td_15" colspan="15">身份证号码</td>
			<td class="td_35" colspan="35">${applyButie.applyFamilyMembers[0].afmSfzh }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">代理人姓名</td>
			<td class="td_15" colspan="15">${applyButie.applyUnit.entag }</td>
			<td class="td_10" colspan="10">性别</td>
			<td class="td_15" colspan="15"></td>
			<td class="td_15" colspan="15">身份证号码</td>
			<td class="td_35" colspan="35"></td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">户籍地址</td>
			<td class="td_40" colspan="40">${applyButie.abSsq }</td>
			<td class="td_10" colspan="10">户籍年限</td>
			<td class="td_15" colspan="15">${applyButie.abSqhjnx }</td>
			<td class="td_10" colspan="10">户籍人口数</td>
			<td class="td_15" colspan="15">${applyButie.abJtrk }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">现居住地址</td>
			<td class="td_50" colspan="50">${applyButie.applyFamilyHouses[0].afhZl }</td>
			<td class="td_20" colspan="20">申请租房补贴<br/>家庭人口数</td>
			<td class="td_20" colspan="20"></td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">联系电话</td>
			<td class="td_90" colspan="90">${applyButie.applyFamilyMembers[0].afmLxdh }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10" rowspan="6">申<br/>请<br/>家<br/>庭<br/>人<br/>员<br/>情<br/>况</td>
			<td class="td_14" colspan="14">与申请人关系</td>
			<td class="td_13" colspan="13">姓名</td>
			<td class="td_25" colspan="25">工作单位</td>
			<td class="td_25" colspan="25">身份证号</td>
			<td class="td_13" colspan="13">婚姻状况</td>
		</tr>
		<c:forEach items="${applyButie.applyFamilyMembers }" varStatus="i" var="item" >
			<c:if test="${i.index != 0}" >
			<tr>
				<td class="td_14" colspan="14">${item.afmYsqrgx }</td>
				<td class="td_13" colspan="13">${item.afmXm }</td>
				<td class="td_25" colspan="25">${item.afmGzdw }</td>
				<td class="td_25" colspan="25">${item.afmSfzh }</td>
				<td class="td_13" colspan="13">
					<c:choose>
						<c:when test="${item.afmHyzk==2 }">已婚</c:when>
						<c:when test="${item.afmHyzk==1 }">未婚</c:when>
						<%--<c:otherwise>已婚</c:otherwise>--%>
					</c:choose>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td class="td_10" colspan="10" rowspan="5">住<br/>房<br/>情<br/>况</td>
			<td class="td_25" colspan="25">住房地址</td>
			<td class="td_13" colspan="13">住房类型</td>
			<td class="td_13" colspan="13">住房类别</td>
			<td class="td_13" colspan="13">使用面积<br>(m2)</td>
			<td class="td_13" colspan="13">产权人或<br>承租人</td>
			<td class="td_13" colspan="13">与申请人<br>关系</td>
		</tr>
		<c:forEach items="${applyButie.applyFamilyHouses }" varStatus="i" var="item" >
			<tr>
				<td class="td_25" colspan="25">${item.afhZl }</td>
				<td class="td_13" colspan="13">
					<c:if test="${applyButie.abXzfxz == '1'}" >2</c:if>
					<c:if test="${applyButie.abXzfxz == '2'}" >3</c:if>
					<c:if test="${applyButie.abXzfxz == '3'}" >6</c:if>
					<c:if test="${applyButie.abXzfxz == '4'}" >1</c:if>
					<c:if test="${applyButie.abXzfxz == '5'}" >7</c:if>
				</td>
				<td class="td_13" colspan="13"></td>
				<td class="td_13" colspan="13">${item.afhMj }</td>
				<td class="td_13" colspan="13">${item.afhCqr }</td>
				<td class="td_13" colspan="13"></td>
			</tr>
		</c:forEach>

		<tr>
			<td class="td_90" colspan="90">
				以上现住房类型栏填序号：私产－1、直管公产－2、单位产－3、近亲属私产—4、、近亲属公产—5、<br/>
				市场租赁住房—6、其它—7。<br/>
				住房类别栏填序号：高层楼房－1、多层或老式楼房－2、平房－3、已拆除-4。
			</td>
		</tr>
		<tr>
			<td class="td_15" colspan="15">家庭现住房<br/>(套数)</td>
			<td class="td_15" colspan="15">${fn:length(applyButie.applyFamilyHouses)}</td>
			<td class="td_18" colspan="18">申请租房补贴家庭住<br/>房总建筑面积(m2)</td>
			<td class="td_15" colspan="15">${applyButie.sumArea }</td>
			<td class="td_12" colspan="12">人均建筑面积<br/>(m2)</td>
			<td class="td_15" colspan="15">${applyButie.abRjjzmj }</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10" rowspan="5" style="line-height:20px;">
				申<br/>请<br/>租<br/>房<br/>补<br/>
				贴<br/>家<br/>庭<br/>人<br/>口<br/>收<br/>入<br/>情<br/>况<br/>
			</td>
			<td class="td_20" colspan="20">关系</td>
			<td class="td_20" colspan="20">姓名</td>
			<td class="td_20" colspan="20">月收入(元)</td>
			<td class="td_30" colspan="30">家庭月收入(元)</td>
		</tr>
		<c:forEach items="${applyButie.applyFamilyMembers }" varStatus="i" var="item" >
			<c:if test="${i.index == 0}" >
				<tr>
					<td class="td_20" colspan="20">申请人</td>
					<td class="td_20" colspan="20">${item.afmXm }</td>
					<td class="td_20" colspan="20">${item.afmNsr/12 }</td>
					<td class="td_30" colspan="30" rowspan="2">${applyButie.abJtnsr/12 }</td>
				</tr>
			</c:if>
			<c:if test="${i.index == 1}" >
				<tr>
					<td class="td_20" colspan="20">${item.afmYsqrgx }</td>
					<td class="td_20" colspan="20">${item.afmXm }</td>
					<td class="td_20" colspan="20">${item.afmNsr/12 }</td>
				</tr>
			</c:if>
			<c:if test="${i.index == 2}" >
				<tr>
					<td class="td_20" colspan="20">${item.afmYsqrgx }</td>
					<td class="td_20" colspan="20">${item.afmXm }</td>
					<td class="td_20" colspan="20"><c:if test="${item.afmNsr != null}" >${item.afmNsr/12 }</c:if></td>
					<td class="td_30" colspan="30">人均月收入(元)</td>
				</tr>
			</c:if>
			<c:if test="${i.index >= 3 and i.index < 4 }" >
				<tr>
					<td class="td_20" colspan="20">${item.afmYsqrgx }</td>
					<td class="td_20" colspan="20">${item.afmXm }</td>
					<td class="td_20" colspan="20"><c:if test="${item.afmNsr != null}" >${item.afmNsr/12 }</c:if></td>
					<td class="td_30" colspan="30">${applyButie.abRjysr }</td>
				</tr>
			</c:if>
		</c:forEach>
		<!---->
	</table>	<br/><br/><br/>
	<table style="">
		<tr>
			<td class="td_10" colspan="10">审核意见街道办事处</td>
			<td class="td_90" colspan="90" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[0].apcComment!=null }" >
					${approveRecords[0].apcComment }
				</c:if>
				<c:if test="${approveRecords[0].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[0].nameUrl!=null }">
				<div></div><img src="<%=basePath%>${approveRecords[0].nameUrl }">
			</c:if><br/>
				<span style="margin-left:80%;">
					<c:if test="${approveRecords[0].approvetimeStr!=null }" >
						${approveRecords[0].approvetimeStr }
					</c:if>
					<c:if test="${approveRecords[0].approvetimeStr==null }" >
						年 月 日
					</c:if>
				</span>
			</td>
		<tr>
			<td class="td_10" colspan="10">审核意见区民政部门</td>
			<td class="td_90" colspan="90" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[1].apcComment!=null }" >
					${approveRecords[1].apcComment }
				</c:if>
				<c:if test="${approveRecords[1].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[1].nameUrl!=null }">
				<img src="<%=basePath%>${approveRecords[1].nameUrl }">
			</c:if><br/>
				<span style="margin-left:80%;">
						<c:if test="${approveRecords[1].approvetimeStr!=null }" >
							${approveRecords[1].approvetimeStr }
						</c:if>
						<c:if test="${approveRecords[1].approvetimeStr==null }" >
							年 月 日
						</c:if></span>
			</td>
		<tr>
			<td class="td_10" colspan="10">审核意见区住保部门</td>
			<td class="td_90" colspan="90" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[2].apcComment!=null }" >
					${approveRecords[2].apcComment }
				</c:if>
				<c:if test="${approveRecords[2].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[2].nameUrl!=null }">
				<img src="<%=basePath%>${approveRecords[2].nameUrl }">
			</c:if><br/>
				<span style="margin-left:80%;">
						<c:if test="${approveRecords[2].approvetimeStr!=null }" >
							${approveRecords[2].approvetimeStr }
						</c:if>
						<c:if test="${approveRecords[2].approvetimeStr==null }" >
							年 月 日
						</c:if></span>
			</td>
		</tr>
		<tr>
			<td class="td_10" colspan="10">意市住保中心</td>
			<td class="td_90" colspan="90" style="text-align:left;padding-left:10px;">
				<c:if test="${approveRecords[3].apcComment!=null }" >
					${approveRecords[3].apcComment }
				</c:if>
				<c:if test="${approveRecords[3].apcComment==null }" >
					经审核，符合条件。
				</c:if><br/>
				本单位承担审查责任。<br/>
				审核人 ：<c:if test="${approveRecords[3].nameUrl!=null }">
				<img src="<%=basePath%>${approveRecords[3].nameUrl }">
			</c:if><br/>
				<span style="margin-left:80%;">
						<c:if test="${approveRecords[3].approvetimeStr!=null }" >
							${approveRecords[3].approvetimeStr }
						</c:if>
						<c:if test="${approveRecords[3].approvetimeStr==null }" >
							年 月 日
						</c:if></span>
			</td>
		</tr>
	</table>
</div>
</body>
</html>