<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <title>提示须知</title>
        <link rel="stylesheet" href="<%=basePath%>thiemesApply/gray/easyui.css" />
        <link rel="stylesheet" href="<%=basePath%>srcApply/css/common.css" />
        <script type="text/javascript" src="<%=basePath%>srcApply/js/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>srcApply/js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>srcApply/js/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<%=basePath %>srcApply/js/common.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
    <div id="information" class="easyui-panel" title="申请须知" style="width: 100%;overflow: hidden;">
        <div class="content"></div>
        <div id="into">
            <div class="beforeRed">
                <input type="Checkbox" id="check" />&nbsp;&nbsp;<span class="readaleray">我已认真阅读申请须知</span>
            </div>
            <div class="into" onclick="into()">
                <span>下一步</span>
            </div>
        </div>
        <div id="input" style="display: none">
            <div class="beforeRed">
                <span class="readagree" id="readagree" onclick="readagree()">输入签名</span>
               <span class="readagree" id="agree" onclick="agree()" style="display: none">我同意，继续申请</span>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<style>
    .beforeRed {
        text-align: center;
        color: rgba(22, 155, 213, 1);
        margin-bottom: 15px;
        margin-top: 20px;

    }
    .readaleray {
        font-size: 20px;
    }
    .into {
        text-align: center;
    }

    .into span {
        display: inline-block;
        width: 90px;
        height: 40px;
        text-align: center;
        background-color: rgba(22, 155, 213, 1);
        border: none;
        border-radius: 5px;
        color: #FFFFFF;
        line-height: 40px;
        margin-bottom: 40px;
        cursor: pointer;
    }
    #input {
        margin-top: 280px;
    }
    #input .name {
        font-size: 20px;
        color:black ;
    }
    #input .write {
        border: 0px;
        border-bottom-color: currentcolor;
        border-bottom-style: none;
        border-bottom-width: 0px;
        border-bottom: 1px solid;
        height: 25px;
        font-size: 20px;
    }
    .readagree{
        display: inline-block;
        width: 150px;
        height: 40px;
        text-align: center;
        background-color: rgba(22, 155, 213, 1);
        border: none;
        border-radius: 5px;
        color: #FFFFFF;
        line-height: 40px;
        cursor: pointer;
    }
</style>
<script>
    var xjy = '\t<div>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:center;margin-top: 40px;"><span style="font-family:宋体; font-size:18pt; font-weight:bold">徐州市市区公共租赁住房申请须知</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:center"><span style="font-family:宋体; font-size:16pt">（新就业、外来务工人员）</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:仿宋_GB2312; font-size:14pt">&#xa0;</span><span style="font-family:仿宋_GB2312; font-size:14pt"> </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">根据《徐州市市区公共租赁住房管理办法》徐政规[2012]8号）的有关规定，现将本市公共租赁住房申请有关事项告知如下：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">一、徐州市市区公共租赁住房保障范围</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">徐州市市区公共租赁住房主要面向在市区（鼓楼区、云龙区、泉山区、经济技术开发区）范围内稳定就业的新就业人员和外来务工人员。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">申请人应当为具有完全民事行为能力的人，特殊情况可委托代理人。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">二、申请条件</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、新就业人员申请条件：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（1）市区户口；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（2）大中专院校毕业当月起计算未满5年；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（3）签订了劳动合同或聘用合同；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（4）有固定收入并有支付能力的证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（5）在市区正常缴纳社会保险或住房公积金一年以上；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（6）本人及配偶、未成年子女在市区无住房、商铺、写字楼（包括已签订合同未取得不动产登记证的房屋），未租住公有住房。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、外来务工人员申请条件：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（1）签订了劳动合同或聘用合同；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（2）有固定收入并有支付能力的证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（3）在市区正常缴纳社会保险或住房公积金一年以上；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（4）本人及配偶、未成年子女在市区无住房、商铺、写字楼（包括已签订合同未取得不动产登记证的房屋），未租住公有住房；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（5）持有我市公安机关核发的《居住证》。（贾汪区、铜山区除外）</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">三、申请所需材料</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、新就业人员申请提供下列材料： </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（1）学历证书； </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（2）劳动合同或聘用合同；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（3）社会保险或住房公积金的交款单或证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（4）收入情况证明材料身份证、户口簿、结婚证或婚姻情况具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（5）无租住单位公房证明、住房情况证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（6）收入证明；（6个月以上单位发放工资的银行流水单）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（7）单位公示证明、具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（8）单位营业执照；（具有法人资格的企事业单位或社会团体）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（9）申请人诚信具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（10）按规定需要提交的其他证明材料；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、外来务工人员申请提供下列材料：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（1）劳动合同或聘用合同；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（2）社会保险或住房公积金的交款单或证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（3）身份证、结婚证或婚姻情况具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（4）无租住单位公房证明、住房情况证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（5）收入证明；（6个月以上单位发放工资的银行流水单）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（6）居住证；（贾汪区、铜山区除外）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（7）单位公示证明、具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（8）单位营业执照；（具有法人资格的企事业单位或社会团体）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（9）申请人诚信具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:21pt"><span style="font-family:宋体; font-size:14pt">（10）按规定需要提交的其他证明材料。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">四、申请审核程序</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人向用工单位提出申请， 用工单位对申请人提交的材料进行审核并公示10天。经公示无异议或者异议不成立的，申请人登录徐州市住房保障系统，按系统提示要求进行注册、填写资料、提交上传各项证明资料（原件拍照后上传）。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、申请人单位所在地的办事处住保窗口工作人员凭个人账号和密码登录徐州市住房保障系统，对申请人提交的资料进行本级审核，3个工作日内完成对申请人提交的材料及有关情况的调查核实。审核通过的，在审核系统上签署审核通过意见并加盖街道办事处公章（电子章）；对审核不通过的，审核人填写初审不通过原因，由审核系统向申请人发送短信告知本人。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、区住保部门工作人员凭账号和密码登录徐州市住房保障系统，对经办事处住保窗口审核通过的申请人提交的资料进行本级审核，由系统向申请人发送短信告知其审核申请资料原件的时间、地点、；审核通过的，审核人在审核系统上签署审核通过意见并签字, 由审核系统向申请人发送审核通过短信；审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。区住保部门 5个工作日内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、市住保中心工作人员凭账号和密码登录徐州市住房保障系统，对经各区审核通过的申请人提交的资料进行本级审核。审核通过的，审核人在审核系统上签署审核通过意见并签字，由审核系统向申请人发送审核通过的短信，市住保中心在市住房保障和房产管理局政务网、市政府政务网公告公示栏目上公示10天，期间接受举报和举证，并组织复查。公示无异议及调查核实后符合条件的家庭，由市住房保障服务中心进行备案，确认为配租对象并进行房源分配。审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。市住保中心 5个工作日（不含公示期）内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">五、公共租赁住房租金及物业服务费标准</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">徐州市市区公共租赁住房成套住宅按建筑面积计算租金，租金标准为5.6元/㎡/月；公寓住房按套计算租金，租金标准为200元/套/月。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">公共租赁住房小区，实行物业管理，物业服务企业的选聘按照相关规定执行。物业服务费标准不高于同地段、同类型普通商品房小区物业服务费市场水平。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">六、其他需注意事项</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">公共租赁住房承租人有下列情形之一的，住房保障部门有权解除租赁合同，收回公共租赁住房，将其行为记入个人信用档案：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、以欺骗等不正当手段承租公共租赁住房的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、将所承租的公共租赁住房转租、转借、擅自调换或者改变用途的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、无正当理由连续3个月以上未在所承租的公共租赁住房居住的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、无正当理由拖欠租金和物业管理服务费累积3个月以上的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、未按要求及时申报家庭人口、收入及住房变动情况的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">6、在该房屋中从事违法活动的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">7、破坏或者擅自装修所承租公共租赁住房，拒不恢复原状的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">8、法律法规或租赁合同约定的其他情形。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">七、其他未尽事项，以《徐州市市区公共租赁住房管理办法》徐政规[2012]8号）相关规定为准，申请前请详细阅读相关政策。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">特此告知。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:仿宋_GB2312; font-size:14pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify"><span style="font-family:仿宋_GB2312; font-size:14pt; font-weight:bold">                            </span><span style="font-family:宋体; font-size:14pt;float: right;margin-right: 10%;">徐州市住房保障服务中心</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:center"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>\n' +
        '\t\t</div>';
    var gzf = '\t<div>\n' +
        '\t\t\t<p style="margin:0pt; text-align:center;margin-top: 40px;"><span style="font-family:宋体; font-size:18pt; font-weight:bold">徐州市市区公共租赁住房租赁补贴申请须知</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:center"><span style="font-family:宋体; font-size:16pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">根据《徐州市市区公共租赁住房租赁补贴实施细则》徐住保办发[2017]6号）的有关规定，现将本市公共租赁住房租赁补贴申请有关事项告知如下：</span></p>\n' +
        '\t\t\t<p style=" margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">一、徐州市公共租赁住房租赁补贴保障范围</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">徐州市市区公共租赁住房租赁补贴主要面向在市区（鼓楼区、云龙区、泉山区、经济技术开发区）范围内符合条件的低保、特困、低收入家庭。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">申请人应当为具有完全民事行为能力的人，特殊情况可委托代理人。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">二、申请条件</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1.具有市区城镇常住户口5年以上；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2.家庭人均月收入低于1549元（低保特困家庭需连续享受城市最低生活保障或持有市总工会核发的《特困职工证》一年以上）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3.家庭人均住房建筑面积低于15平方米。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">注意事项：申请家庭正在享受其他住房保障政策的，不得申请。低收入家庭人均月收入认定标准在年度市区住房保障政策动态调后，以新标准执行。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">三、申请所需材料</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人户口所在地社区公示证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、收入情况的证明材料（有单位的由单位出具证明【6个月以上单位发放工资的银行流水单】，无单位的由社区出具证明。在法定劳动年龄内并有劳动能力的申请人，本人不能提供收入证明的，以及单位出具收入证明低于上年度市区最低工资标准或无法计算的，均按照市区上年度最低工资标准计算收入）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、住房状况的证明材料（包括不动产证、产权证、他项权证、公房租约、5年内已转让和已领取拆迁补偿款房屋、已签订合同尚未交付房屋等相关资料，无房的出具无房证明）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、家庭成员身份证和户口簿 ；申请家庭委托代理人的，须提供申请家庭的书面《委托书》及代理人的身份证件；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、合法有效的家庭成员之间的赡养、抚养、扶养关系及共同生活的证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">6、反映家庭财产状况的有关证明材料（申请人应申报家庭存款、汽车、股票、公积金、开办企业等情况，并对申报财产的真实性作出书面承诺）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">7、申请人诚信具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">8、按规定需要提交的其他证明材料。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">四、申请审核程序</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人向户口所在地的区街道办事处住房保障服务窗口提出申请，住保窗口同时组织所辖社区居委会对申请人递交的材料进行初审，并就家庭有关情况进行调查确认，对是否符合规定条件提出审核意见，并在社区进行公示，公示期限为10天。经公示无异议或者异议不成立的，申请人登录徐州市住房保障系统，按系统提示要求进行注册、填写资料、提交上传各项证明资料（原件拍照后上传）。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、申请人户口所在地的办事处住保窗口工作人员凭账号和密码登录徐州市住房保障系统，对申请人提交的资料进行本级审核，3个工作日内完成对申请人提交的材料及有关情况的调查核实。审核通过的，在审核系统上签署审核通过意见并签字；对审核不通过的，审核人填写初审不通过原因，由审核系统向申请人发送短信告知本人。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、区民政部门工作人员凭账号和密码登录徐州市住房保障系统，对经办事处住保窗口审核通过的申请人提交的资料进行收入审核，审核通过的，在审核系统上签署审核通过意见并签字。区民政部门 3个工作日内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、区住保部门工作人员凭账号和密码登录徐州市住房保障系统，对经区民政部门审核通过的申请人提交的资料进行本级审核，由系统向申请人发送短信告知其审核申请资料原件的时间、地点、；审核通过的，审核人在审核系统上签署审核通过意见并签字,由审核系统向申请人发送审核通过短信；审核不通过的，审核人填写复审不通过原因，由审核系统向申请人发送短信告知本人。区住保部门 4个工作日内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、市住保中心工作人员凭账号和密码登录徐州市住房保障系统，对经各区审核通过的申请人提交的资料进行本级审核。审核通过的，审核人在审核系统上签署审核通过意见并签字，由审核系统向申请人发送审核通过的短信，市住保中心在市住房保障和房产管理局政务网、市政府政务网公告公示栏目上公示10天，期间接受举报和举证，并组织复查。公示无异议及调查核实后符合条件的家庭，由市住房保障服务中心进行备案，确认为租赁补贴发放对象。审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。市住保中心5个工作日（不含公示期）内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">五、公共租赁住房租赁补贴标准</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、低保、特困家庭14元/㎡。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、低收入家庭10元/㎡。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">六、其他需注意事项</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、公共租赁住房租赁补贴保障有效期为一年，按年度进行审核，补贴协议一年一签。保障家庭应在保障有效期届满前30天内，向市<住房保障服务中心提交相关材料进行资格年审。经年审符合条件的，可继续按规定申领补贴；年审不符合条件的，补贴保障终止；未按规定进行资格审核的，视为自愿放弃租赁补贴资格，将停发租赁补贴。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、对按规定申报家庭人口、收入、住房变化情况的家庭，市、区住保部门审核其租赁补贴资格和租赁补贴额度，对不再符合租赁补贴条件的家庭，自不符合条件当月起停发租房补贴；对需调整补贴额的家庭，自申报次月起调整租房补贴的补贴额度。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、对未按规定进行年度申报的家庭，市住房保障服务中心自该家庭享受租赁补贴期满次月起停发补贴；享受租赁补贴的家庭补报本年度家庭变化情况，并经审核，符合享受租赁补贴条件的，自审核合格的次月起恢复发放租房补贴，但停发期间租赁补贴不予补发。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、对经核查不符合公共租赁房租赁补贴条件的家庭，自发现不符合条件次月起停发租房补贴；对需调整补贴额的家庭，自核查次月起调整租房补贴的补贴额度。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、对申请家庭自行购房、中断租赁关系、自愿退出的，市住房保障服务中心通知申请人自复核次月起停发租房补贴。自愿退出租房补贴的家庭，需到市住房保障服务中心办理退出手续。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">6、对于原申请人已故且该家庭仍符合租房补贴条件的，由申请家庭共同推荐的申请人到市住房保障服务中心办理租赁补贴变更手续后，可由变更后的申请人继续领取租房补贴。</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">七、其他未尽事项，以《徐州市市区公共租赁住房租赁补贴实施细则》徐住保办发[2017]6号）相关规定为准，申请前请详细阅读相关政策。</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">特此告知。</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:\'Times New Roman\'; font-size:10.5pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="margin:0pt 0pt 0pt 36pt; text-align:justify"><span style="font-family:仿宋_GB2312; font-size:14pt; font-weight:bold">                           </span><span style="font-family:宋体; font-size:14pt;float: right;margin-right: 10%;">徐州市住房保障服务中心</span></p>\n' +
        '\t\t</div>';
    var dbtk = '<div>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:center;margin-top: 40px;"><span style="font-family:宋体; font-size:18pt; font-weight:bold">徐州市市区公共租赁住房申请须知</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:center"><span style="font-family:宋体; font-size:16pt">（低保、特困家庭）</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">根据《徐州市市区公共租赁住房管理办法》徐政规[2012]8号）、《徐州市市区廉租住房保障办法》徐政规[2009]3号）的有关规定，现将本市公共租赁住房申请有关事项告知如下：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">一、徐州市公共租赁住房保障范围</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">徐州市市区公共租赁住房主要面向主要面向在市区（鼓楼区、云龙区、泉山区、经济技术开发区）范围内符合条件的低保、特困家庭。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">申请人应当为具有完全民事行为能力的人，特殊情况可委托代理人。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">二、申请条件</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、具有市区城镇常住户口5年以上；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、连续享受城市最低生活保障或持有市总工会核发的《特困职工证》一年以上，且人均住房建筑面积低于10平方米（含10平方米）的家庭。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">年满35周岁的单身人员可作为独立家庭进行申请。申请家庭正在享受其他住房保障政策的，不得申请。现正在享受廉租住房租赁补贴保障的，转申请实物配租保障获批后，将停止发放廉租住房租赁补贴。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">三、申请所需材料</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人户口所在地社区公示证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、收入情况的证明材料（有单位的由单位出具证明【6个月以上单位发放工资的银行流水单】，无单位的由社区出具证明。在法定劳动年龄内并有劳动能力的申请人，本人不能提供收入证明的，以及单位出具收入证明低于上年度市区最低工资标准或无法计算的，均按照市区上年度最低工资标准计算收入）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、住房状况的证明材料（包括产权证、他项权证、公房租约、已转让和已领取拆迁补偿款房屋及已签订合同尚未交付房屋等相关资料，无房的出具无房证明）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、家庭成员身份证和户口簿；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、合法有效的家庭成员之间的赡养、抚养、扶养关系及共同生活的证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">6、反映家庭财产状况的有关证明材料（申请人应申报家庭存款、汽车、股票、公积金、开办企业等情况，并对申报财产的真实性作出书面承诺）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">7、申请人诚信具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">8、按规定需要提交的其他证明材料。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">四、申请审核程序</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人向户口所在地的区街道办事处住房保障服务窗口提出申请，住保窗口同时组织所辖社区居委会对申请人递交的材料进行初审，并就家庭有关情况进行调查确认，对是否符合规定条件提出审核意见，并在社区进行公示，公示期限为10天。经公示无异议或者异议不成立的，申请人登录徐州市住房保障系统，按系统提示要求进行注册、填写资料、提交上传各项证明资料（原件拍照后上传）。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、申请人户口所在地的区街道办事处住保窗口工作人员凭账号和密码登录徐州市住房保障系统，对申请人提交的资料进行本级审核，5个工作日内完成对申请人提交的材料及有关情况的调查核实。审核通过的，在审核系统上签署审核通过意见并签字；对审核不通过的，审核人填写初审不通过原因，由审核系统向申请人发送短信告知本人。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、区民政部门工作人员凭账号和密码登录徐州市住房保障系统，对经办事处住保窗口审核通过的申请人提交的资料进行收入审核，审核通过的，在审核系统上签署审核通过意见并签字。区民政部门 3个工作日内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、区住保部门工作人员凭账号和密码登录徐州市住房保障系统，对经区民政部门审核通过的申请人提交的资料进行本级审核，由系统向申请人发送短信告知其审核申请资料原件的时间、地点。审核通过的，审核人在审核系统上签署审核通过意见并签字，由审核系统向申请人发送审核通过短信；审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。区住保部门 7个工作日（不含公示期）内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、市住房保障服务中心对审核后符合申请条件的申请人进行备案。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">五、公共租赁住房租金及物业服务费标准</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">徐州市市区面向低保、特困家庭的公共租赁住房租金标准按不超过同地段、同类型住房市场租金10%的标准计算。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">公共租赁住房小区，实行物业管理，物业服务企业的选聘按照相关规定执行。物业服务费标准不高于同地段、同类型普通商品房小区物业服务费市场水平。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">六、其他需注意事项</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">公共租赁住房承租人有下列情形之一的，住房保障部门有权解除租赁合同，收回公共租赁住房，将其行为记入个人信用档案：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、以欺骗等不正当手段承租公共租赁住房的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、将所承租的公共租赁住房转租、转借、擅自调换或者改变用途的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、无正当理由连续3个月以上未在所承租的公共租赁住房居住的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、无正当理由拖欠租金和物业管理服务费累积3个月以上的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、未按要求及时申报家庭人口、收入及住房变动情况的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">6.在该房屋中从事违法活动的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">7.破坏或者擅自装修所承租公共租赁住房，拒不恢复原状的；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">8、法律法规或租赁合同约定的其他情形。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">七、其他未尽事项，以徐州市市区公共租赁住房管理办法》徐政规[2012]8号）相关规定为准，申请前请详细阅读相关政策。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">特此告知。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify; text-indent:203pt"><span style="font-family:宋体; font-size:14pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify; text-indent:203pt"><span style="font-family:宋体; font-size:14pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify; text-indent:203pt"><span style="font-family:宋体; font-size:14pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify; text-indent:203pt"><span style="font-family:宋体; font-size:14pt;float: right;margin-right: 10%;">徐州市住房保障服务中心</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify"><span style="font-family:宋体; font-size:18pt; font-weight:bold">&#xa0;</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify"><span style="font-family:宋体; font-size:18pt; font-weight:bold">&#xa0;</span></p>\n' +
        '\t\t\t<p style="margin:0pt; text-align:justify"><span style="font-family:宋体; font-size:18pt; font-weight:bold">&#xa0;</span></p>\n' +
        '\t\t</div>\t';
    var jsf = '<div>\n' +
        '\t\t\t<p style="margin:0pt; text-align:center;margin-top: 40px;"><span style="font-family:宋体; font-size:18pt; font-weight:bold">徐州市市区经济适用住房申请须知</span></p>\n' +
        '\t\t\t<p style="line-height:15pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:\'宋体\'; font-size:10.5pt">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">根据《徐州市市区经济适用住房管理办法》徐政规[2009]2号）的有关规定，现将本市经济适用住房申请有关事项告知如下：</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">一、徐州市经济适用住房保障范围</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">徐州市市区经济适用住房主要面向在市区（鼓楼区、云龙区、泉山区、经济技术开发区）范围内符合条件的低收入、中等偏下收入家庭。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">申请人应当为具有完全民事行为能力的人，特殊情况可委托代理人。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">二、申请条件</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、具有市区城镇常住户口5年以上；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、家庭人均月收入低于2065元；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、家庭人均住房建筑面积低于15平方米（含15平方米）。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">年满35周岁的单身人员可作为独立家庭进行申请。申请家庭正在享受其他类型住房保障的，不得再申请购买经济适用住房。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">三、申请所需材料</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人户口所在地社区公示证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、收入情况的证明材料（有单位的由单位出具证明【6个月以上单位发放工资的银行流水单】，无单位的由社区出具证明。在法定劳动年龄内并有劳动能力的申请人，本人不能提供收入证明的，以及单位出具收入证明低于上年度市区最低工资标准或无法计算的，均按照市区上年度最低工资标准计算收入）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、住房状况的证明材料（包括产权证、他项权证、公房租约、已转让和已领取拆迁补偿款房屋及已签订合同尚未交付房屋等相关资料，无房的出具无房证明）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、家庭成员身份证和户口簿；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、合法有效的家庭成员之间的赡养、抚养、扶养关系及共同生活的证明；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">6、反映家庭财产状况的有关证明材料（申请人应申报家庭存款、汽车、股票、公积金、开办企业等情况，并对申报财产的真实性作出书面承诺）；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">7、申请人诚信具结书；</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">8、按规定需要提交的其他证明材料。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">四、申请审核程序</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、申请人向户口所在地的区街道办事处住房保障服务窗口提出申请，住保窗口同时组织所辖社区居委会对申请人递交的材料进行审核，并就家庭有关情况进行调查确认，对是否符合规定条件提出审核意见，并在社区进行公示，公示期限为10天。经公示无异议或者异议不成立的，申请人登录徐州市住房保障系统，按系统提示要求进行注册、填写资料、提交上传各项证明资料（原件拍照后上传）。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、申请人户口所在地的区街道办事处住保窗口工作人员凭个人账号和密码登录徐州市住房保障系统，对申请人提交的资料进行本级审核，3个工作日内完成对申请人提交的材料及有关情况的调查核实。审核通过的，在审核系统上签署审核通过意见并加盖街道办事处及区民政部门公章（电子章）；对审核不通过的，审核人填写初审不通过原因，由审核系统向申请人发送短信告知本人。 </span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、区民政部门工作人员凭账号和密码登录徐州市住房保障系统，对经办事处住保窗口审核通过的申请人提交的资料进行收入审核，审核通过的，在审核系统上签署审核通过意见并签字。区民政部门 3个工作日内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、区住保部门工作人员凭账号和密码登录徐州市住房保障系统，对经办事处住保窗口审核通过的申请人提交的资料进行本级审核，由系统向申请人发送短信告知其审核申请资料原件的时间、地点。审核通过的，审核人在审核系统上签署审核通过意见并签字，由审核系统向申请人发送审核通过短信；审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。区住保部门 7个工作日（不含公示期）内完成本级审核工作。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、市住房保障服务中心对审核后符合申请条件的申请人进行备案。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">五、其他需注意事项</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify;text-indent:28pt"><span style="font-family:宋体; font-size:14pt">1、经济适用住房购房人拥有有限产权，购买经济适用住房不满10年，不得直接上市交易。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify;text-indent:28pt"><span style="font-family:宋体; font-size:14pt">2、购买经济适用住房满10年，购房人上市转让经济适用住房的，应当按照届时同地段普通商品住房与经济适用住房差价的20%比例向政府交纳土地收益等相关价款。购房人也可以按照规定的标准向政府交纳土地收益等相关价款，取得完全产权后上市转让。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify;text-indent:28pt"><span style="font-family:宋体; font-size:14pt">3、对轮候到位的经济适用住房申购家庭，因各种原因而放弃此轮购房的，2年内不得再申请购买经济适用住房。轮候期间，申请人家庭收入、人口、住房等情况发生变化的，经市住房保障实施机构核实后，对不符合申购经济适用住房条件的，取消其申请购买经济适用住房资格。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify;text-indent:28pt"><span style="font-family:宋体; font-size:14pt">4、每个经济适用住房申购家庭只能购买一套经济适用住房，已购买经济适用住房的家庭不得再申请购买经济适用住房。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt; text-align:justify;text-indent:28pt"><span style="font-family:宋体; font-size:14pt">5、经济适用住房在未取得完全产权之前，不得私下转让或者用于出租经营。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">六、其他未尽事项，以《徐州市市区经济适用住房管理办法》徐政规[2009]2号）相关规定为准，申请前请详细阅读相关政策。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:justify; text-indent:28pt"><span style="font-family:宋体; font-size:14pt">特此告知。</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify"><span style="font-family:仿宋_GB2312; font-size:14pt; font-weight:bold">&#xa0;</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt 0pt 0pt 36pt; text-align:justify"><span style="font-family:仿宋_GB2312; font-size:14pt; font-weight:bold">                            </span><span style="font-family:宋体; font-size:14pt;float: right;margin-right: 10%;">徐州市住房保障服务中心</span></p>\n' +
        '\t\t\t<p style="line-height:20pt; margin:0pt; text-align:center"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>\n' +
        '\t\t</div>';
    var num;
    $(function () {
        num = ${applytype};
        if(num == 5){
            $(".content").append(xjy)
        }else if(num == 4){
            $(".content").append(xjy)
        }else if(num == 2){
            $(".content").append(gzf)
        }else if(num == 3){
            $(".content").append(dbtk)
        }else {
            $(".content").append(jsf)
        }
    })
    function into() {
        var check = $("#check").prop("checked");
        if (check) {
            $('#information').panel({title:'诚信承诺'});
            $(".content").html('<div>\n' +
                '\t\t\t<p style="margin:0pt; text-align:center;margin-top: 40px;"><span style="font-family:宋体; font-size:18pt; font-weight:bold">徐州市公共租赁住房网上申报平台-诚信承诺书</span></p></br></br>' +
                '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt;; text-align:justify; text-indent:24pt"><span style="font-family:宋体; font-size:14pt">一、本人及家庭成员了解徐州市公共租赁住房配租政策，所提供的材料全部真实有效，所申报的家庭收入和财产全面真实，如有虚假或隐瞒，我愿意放弃申报或退缴已享受的住房保障政策待遇，并接受住房保障部门按照有关规定给予的处罚。</span></p>\n' +
                '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt;; text-align:justify; text-indent:24pt"><span style="font-family:宋体; font-size:14pt">二、本人及家庭成员授权并自愿配合住房保障部门核查其房产情况。</span></p>\n' +
                '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt;; text-align:justify; text-indent:24pt"><span style="font-family:宋体; font-size:14pt">三、本人及家庭成员授权居民家庭经济状况核对机构从人力资源和社会保障、公安、民政、住房公积金、公安局交通管理等部门和机构获取个人的就业、户籍、婚姻、死亡、公积金、机动车等方面的信息，对本人及家庭全体成员的收入和车辆拥有等经济状况信息进行核查。并自愿配合居民家庭经济状况核对机构完成上述工作。</span></p>\n' +
                '\t\t\t<p style="line-height:20pt; margin:7.8pt 0pt 14pt;; text-align:justify; text-indent:24pt"><span style="font-family:宋体; font-size:14pt">四、本《声明及授权》在申请及保障期间一直有效。</span></p>\n' +
                '\t\t</div>');
            $("#into").hide();
            $("#input").show();
        }
    }
    function readagree() {
        bank.showWindow("<%=basePath %>path/toApplySign","请输入您的姓名",740, 500, true);
        $('#readagree').hide();
        $('#agree').show();
    }
    function agree() {
        var data =bank.biography().getParams('signApply');
        if(data != null){
            if (num == 5) {
                window.location.href = '<%=basePath%>applyForgraDuate/toNewGraduate';
            }else if(num == 4){
                window.location.href = '<%=basePath%>applyForForegin/toApplyForegin';
            }else if(num == 3){
                window.location.href = '<%=basePath%>apply/toApply?applyType=1';
            }else if(num == 2){
                window.location.href = '<%=basePath%>applyButie/toLowPaulTrap';
            }else {
                window.location.href = '<%=basePath%>apply/toApply?applyType=0';
            }
        } else {
            $('#agree').hide();
            $('#readagree').show();
            bank.alertMessage("请输入本人姓名");
        }
    }
</script>
