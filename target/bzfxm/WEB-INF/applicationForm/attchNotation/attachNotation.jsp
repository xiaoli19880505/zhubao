<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div  data-options="region:'center',border:false" class="welcenter">
    <div class="easyui-panel welpanel" style="width: 100%">
         <div class="showBox"></div>
    </div>
</div>
</body>
</html>
<script>
    $(function () {
        var type =bank.biography().getParams("attachType").row;
        var showString="";
        //0  收入情况证明材料
        //1  住房状况证明材料
        //2  婚姻情况证明
        //3  特殊情况证明
        //4  申请人和共同承租家庭成员身份证
        //5  新就业收入情况证明材料
        //6  新就业、外来务工、低保特困 住房状况证明材料
        //7  单位出具的公租房申请公示证明
        //8  大中专院校毕业证书
        //9  营业执照
        //10 户口簿及其他居住证明
        //11 外来务工收入情况证明材料
        switch (type){
            case 0:
                showString="<p>1）有工作单位的，出具单位开具的收入证明（加盖单位公章）以及半年以上的单位发放工资的银行流水单（发放银行盖章）；</p>" +
                           "<p>2）无工作单位的，由所属社区根据实际情况出具收入证明；</p>" +
                           "<p>3）已退休的超过6个月以上的提供6个月社保处发放退休金银行流水单（发放银行盖章），已退休未超过6个月的，提供社保处已发放退休金银行流水单（发放银行盖章）。</p>";
                break;
            case 1:
                showString="<p>1）没有房产的提供申请人所有家庭成员的无房证明[徐州市不动产（住户）查询信息；</p>" +
                           "<p>2）有房产的提供不动产证或土地证和房产证；</p>" +
                           "<p>3）未租公有住房证明。</p>";

                break;
            case 2:
                showString="<p>1）已婚的提供结婚证；</p>" +
                           "<p>2）离异的提供离婚证及离婚判决书；</p>" +
                           "<p>3）单身的提供单身具结书。</p>";
                break;
            case 3:
                showString="<p>1）低保家庭提供低保证（一年以上）；</p>" +
                           "<p>2）特困家庭提供特困证（一年以上）；</p>" +
                           "<p>3）二级以上下肢重度残疾者提供相应残疾证明。</p>";
                break;
            case 4:
                showString="<p>家庭成员不包括父母及已成年子女（年满18周岁）</p>";
                break;
            case 5:
                showString="<p>出具单位开具的收入证明（加盖单位公章）以及半年以上的单位发放工资的银行流水单（发放银行盖章）。</p>";
                break;
            case 6:
                showString="<p>1）没有房产的提供申请人所有家庭成员的无房证明[徐州市不动产（住户）查询信息]；</p>" +
                           "<p>2）未租公有住房证明。</p>";
                break;
            case 7:
                showString="<p>公示期为10天</p>";
                break;
            case 8:
                showString="<p>毕业时长不超过5年</p>";
                break;
            case 9:
                showString="<p>所属单位的营业执照（复印件加盖单位公章）</p>";
                break;
            case 10:
                showString="<p>户口簿包含首页，居住证明为暂住证</p>";
                break;
            case 11:
                showString="<p>出具单位开具的收入证明（加盖单位公章）以及半年以上的单位发放工资的银行流水单（发放银行盖章）。</p>";
                break;
            default :
                break;
        }
        $(".showBox").html(showString)
    })
</script>
