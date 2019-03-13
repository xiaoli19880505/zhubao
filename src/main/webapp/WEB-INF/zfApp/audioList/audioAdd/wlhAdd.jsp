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
    <meta charset="UTF-8"/>
    <title>我的申请</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/mui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/mobile.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/easyui/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>srcApp/css/common.css"/>
</head>
<body>
<div class="easyui-navpanel">
    <!-- 主界面不动、菜单移动 -->
    <!-- 侧滑导航根容器 -->
    <div class="mui-off-canvas-wrap mui-draggable mui-slide-in">
        <!-- 菜单容器 -->
        <%--<aside class="mui-off-canvas-left" id="offCanvasSide">
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
        </aside>--%>
        <!-- 主页面容器 -->
        <%--<div class="mui-inner-wrap">--%>
        <!-- 主页面标题 -->
        <header class="mui-bar mui-bar-nav">
            <a class="mui-icon mui-action-menu mui-icon-back mui-pull-left" id="back"></a>
            <h1 class="mui-title">公共租赁住房(外来务工人员)年审申请</h1>
        </header>
        <nav class="mui-bar mui-bar-tab">
            <a class="mui-tab-item " id="apply">
                <span class="mui-icon mui-icon-home"></span>
                <span class="mui-tab-label">我的申请</span>
            </a>
            <a class="mui-tab-item mui-active" id="audit">
                <span class="mui-icon mui-icon-list"></span>
                <span class="mui-tab-label">我的年审</span>
            </a>
            <a class="mui-tab-item" id="userInfo">
                <span class="mui-icon mui-icon-contact"></span>
                <span class="mui-tab-label">个人信息</span>
            </a>
            <a class="mui-tab-item" id="updatePwd">
                <span class="mui-icon mui-icon-gear"></span>
                <span class="mui-tab-label">修改密码</span>
            </a>
        </nav>
        <div class="mui-content mui-scroll-wrapper" id="pullrefresh">
            <div class="mui-scroll">
                <form method="post" id="form" class="easyui-form" enctype="multipart/form-data">
                    <!-- 主界面具体展示内容 -->
                    <div id="wrap0">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">在线申请用户须知</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-row" style="padding:5px">
                                    根据《徐州市市区公共租赁住房管理办法》徐政规[2012]8号）的有关规定，现将本市公共租赁住房申请有关事项告知如下：
                                </div>
                                <div class="mui-row" style="padding:5px">一、徐州市市区公共租赁住房保障范围</div>
                                <div class="mui-row" style="padding:5px">
                                    徐州市市区公共租赁住房主要面向在市区（鼓楼区、云龙区、泉山区、经济技术开发区）范围内稳定就业的新就业人员和外来务工人员。
                                </div>
                                <div class="mui-row" style="padding:5px">申请人应当为具有完全民事行为能力的人，特殊情况可委托代理人。</div>
                                <div class="mui-row" style="padding:5px">二、申请条件</div>
                                <div class="mui-row" style="padding:5px">1、新就业人员申请条件：</div>
                                <div class="mui-row" style="padding:5px">（1）市区户口；</div>
                                <div class="mui-row" style="padding:5px">（2）大中专院校毕业当月起计算未满5年；</div>
                                <div class="mui-row" style="padding:5px">（3）签订了劳动合同或聘用合同；</div>
                                <div class="mui-row" style="padding:5px">（4）有固定收入并有支付能力的证明；</div>
                                <div class="mui-row" style="padding:5px">（5）在市区正常缴纳社会保险或住房公积金一年以上；</div>
                                <div class="mui-row" style="padding:5px">
                                    （6）本人及配偶、未成年子女在市区无住房、商铺、写字楼（包括已签订合同未取得不动产登记证的房屋），未租住公有住房。
                                </div>
                                <div class="mui-row" style="padding:5px">2、外来务工人员申请条件：</div>
                                <div class="mui-row" style="padding:5px">（1）签订了劳动合同或聘用合同；</div>
                                <div class="mui-row" style="padding:5px">（2）有固定收入并有支付能力的证明；</div>
                                <div class="mui-row" style="padding:5px">（3）在市区正常缴纳社会保险或住房公积金一年以上；</div>
                                <div class="mui-row" style="padding:5px">
                                    （4）本人及配偶、未成年子女在市区无住房、商铺、写字楼（包括已签订合同未取得不动产登记证的房屋），未租住公有住房；
                                </div>
                                <div class="mui-row" style="padding:5px">（5）持有我市公安机关核发的《居住证》。（贾汪区、铜山区除外）</div>
                                <div class="mui-row" style="padding:5px">三、申请所需材料</div>
                                <div class="mui-row" style="padding:5px">1、新就业人员申请提供下列材料：</div>
                                <div class="mui-row" style="padding:5px">（1）学历证书；</div>
                                <div class="mui-row" style="padding:5px">（2）劳动合同或聘用合同；</div>
                                <div class="mui-row" style="padding:5px">（3）社会保险或住房公积金的交款单或证明；</div>
                                <div class="mui-row" style="padding:5px">（4）身份证、户口簿、结婚证或婚姻情况具结书；</div>
                                <div class="mui-row" style="padding:5px">（5）无租住单位公房证明、住房情况证明；</div>
                                <div class="mui-row" style="padding:5px">（6）收入证明；（6个月以上单位发放工资的银行流水单）；</div>
                                <div class="mui-row" style="padding:5px">（7）单位公示证明、具结书；</div>
                                <div class="mui-row" style="padding:5px">（8）单位营业执照；（具有法人资格的企事业单位或社会团体）；</div>
                                <div class="mui-row" style="padding:5px">（9）申请人诚信具结书；</div>
                                <div class="mui-row" style="padding:5px">（10）按规定需要提交的其他证明材料；</div>
                                <div class="mui-row" style="padding:5px">2、外来务工人员申请提供下列材料：</div>
                                <div class="mui-row" style="padding:5px">（1）劳动合同或聘用合同；</div>
                                <div class="mui-row" style="padding:5px">（2）社会保险或住房公积金的交款单或证明；</div>
                                <div class="mui-row" style="padding:5px">（3）身份证、结婚证或婚姻情况具结书；</div>
                                <div class="mui-row" style="padding:5px">（4）无租住单位公房证明、住房情况证明；</div>
                                <div class="mui-row" style="padding:5px">（5）收入证明；（6个月以上单位发放工资的银行流水单）；</div>
                                <div class="mui-row" style="padding:5px">（6）居住证；（贾汪区、铜山区除外）；</div>
                                <div class="mui-row" style="padding:5px">（7）单位公示证明、具结书；</div>
                                <div class="mui-row" style="padding:5px">（8）单位营业执照；（具有法人资格的企事业单位或社会团体）</div>
                                <div class="mui-row" style="padding:5px">（9）申请人诚信具结书；</div>
                                <div class="mui-row" style="padding:5px">（10）按规定需要提交的其他证明材料。</div>
                                <div class="mui-row" style="padding:5px">四、申请审核程序</div>
                                <div class="mui-row" style="padding:5px">1、申请人向用工单位提出申请，
                                    用工单位对申请人提交的材料进行审核并公示10天。经公示无异议或者异议不成立的，申请人登录徐州市住房保障系统，按系统提示要求进行注册、填写资料、提交上传各项证明资料（原件拍照后上传）。
                                </div>
                                <div class="mui-row" style="padding:5px">
                                    2、申请人单位所在地的办事处住保窗口工作人员凭账号和密码登录徐州市住房保障系统，对申请人提交的资料进行本级审核，5个工作日内完成对申请人提交的材料及有关情况的调查核实。审核通过的，在审核系统上签署审核通过意见并签字；对审核不通过的，审核人填写初审不通过原因，由审核系统向申请人发送短信告知本人。
                                </div>
                                <div class="mui-row" style="padding:5px">
                                    3、区住保部门工作人员凭账号和密码登录徐州市住房保障系统，对经办事处住保窗口审核通过的申请人提交的资料进行本级审核，由系统向申请人发送短信告知其审核申请资料原件的时间、地点、；审核通过的，审核人在审核系统上签署审核通过意见并签字,
                                    由审核系统向申请人发送审核通过短信；审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。区住保部门 5个工作日内完成本级审核工作。
                                </div>
                                <div class="mui-row" style="padding:5px">
                                    4、市住保中心工作人员凭账号和密码登录徐州市住房保障系统，对经各区审核通过的申请人提交的资料进行本级审核。审核通过的，审核人在审核系统上签署审核通过意见并签字，由审核系统向申请人发送审核通过的短信，市住保中心在市住房保障和房产管理局政务网、市政府政务网公告公示栏目上公示10天，期间接受举报和举证，并组织复查。公示无异议及调查核实后符合条件的家庭，由市住房保障服务中心进行备案，确认为配租对象并进行房源分配。审核不通过的，审核人填写审核不通过原因，由审核系统向申请人发送短信告知本人。市住保中心
                                    5个工作日（不含公示期）内完成本级审核工作。
                                </div>
                                <div class="mui-row" style="padding:5px">五、公共租赁住房租金及物业服务费标准</div>
                                <div class="mui-row" style="padding:5px">
                                    徐州市市区公共租赁住房成套住宅按建筑面积计算租金，租金标准为5.6元/㎡/月；公寓住房按套计算租金，租金标准为200元/套/月。
                                </div>
                                <div class="mui-row" style="padding:5px">
                                    公共租赁住房小区，实行物业管理，物业服务企业的选聘按照相关规定执行。物业服务费标准不高于同地段、同类型普通商品房小区物业服务费市场水平。
                                </div>
                                <div class="mui-row" style="padding:5px">六、其他需注意事项</div>
                                <div class="mui-row" style="padding:5px">
                                    公共租赁住房承租人有下列情形之一的，住房保障部门有权解除租赁合同，收回公共租赁住房，将其行为记入个人信用档案：
                                </div>
                                <div class="mui-row" style="padding:5px">1、以欺骗等不正当手段承租公共租赁住房的；</div>
                                <div class="mui-row" style="padding:5px">2、将所承租的公共租赁住房转租、转借、擅自调换或者改变用途的；</div>
                                <div class="mui-row" style="padding:5px">3、无正当理由连续3个月以上未在所承租的公共租赁住房居住的；</div>
                                <div class="mui-row" style="padding:5px">4、无正当理由拖欠租金和物业管理服务费累积3个月以上的；</div>
                                <div class="mui-row" style="padding:5px">5、未按要求及时申报家庭人口、收入及住房变动情况的；</div>
                                <div class="mui-row" style="padding:5px">6、在该房屋中从事违法活动的；</div>
                                <div class="mui-row" style="padding:5px">7、破坏或者擅自装修所承租公共租赁住房，拒不恢复原状的；</div>
                                <div class="mui-row" style="padding:5px">8、法律法规或租赁合同约定的其他情形。</div>
                                <div class="mui-row" style="padding:5px">
                                    七、其他未尽事项，以《徐州市市区公共租赁住房管理办法》徐政规[2012]8号）相关规定为准，申请前请详细阅读相关政策。
                                </div>
                                <div class="mui-row" style="padding:5px">特此告知。</div>
                                <div class="mui-row" style="padding:5px">徐州市住房保障服务中心</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">徐州市公共租赁住房网上申报平台-诚信承诺书</div>
                            <!--内容区-->
                            <div class="mui-card-content" >
                                <div class="mui-row" style="padding:5px">一、本人及家庭成员了解徐州市公共租赁住房配租政策，所提供的材料全部真实有效，所申报的家庭收入和财产全面真实，如有虚假或隐瞒，我愿意放弃申报或退缴已享受的住房保障政策待遇，并接受住房保障部门按照有关规定给予的处罚。</div>
                                <div class="mui-row" style="padding:5px">二、本人及家庭成员授权并自愿配合住房保障部门核查其房产情况。</div>
                                <div class="mui-row" style="padding:5px">三、本人及家庭成员授权居民家庭经济状况核对机构从人力资源和社会保障、公安、民政、住房公积金、公安局交通管理等部门和机构获取个人的就业、户籍、婚姻、死亡、公积金、机动车等方面的信息，对本人及家庭全体成员的收入和车辆拥有等经济状况信息进行核查。并自愿配合居民家庭经济状况核对机构完成上述工作。</div>
                                <div class="mui-row" style="padding:5px">四、本《声明及授权》在申请及保障期间一直有效。</div>
                            </div>
                            <!--页脚，放置补充信息或支持的操作-->
                            <div class="mui-card-footer">
                                <div class="mui-input-row mui-checkbox mui-left">
                                    <label>我已阅读同意</label>
                                    <input name="checkbox1" id="check" type="checkbox">
                                </div>
                            </div>
                        </div>
                        <div class="mui-content-padded" style="margin-top: 2rem;">
                            <button id='next1' class="mui-btn mui-btn-block mui-btn-primary">继续申请</button>
                        </div>
                    </div>
                    <div id="wrap1" style="display: none">
                    <div class="mui-card">
                    <!--页眉，放置标题-->
                    <div class="mui-card-header">签字确认</div>
                    <!--内容区-->
                    <div class="mui-card-content">
                    <div class="mui-row">
                    <div id="signatureparent">
                    <div id="signature"></div>
                    </div>
                    </div>
                    </div>
                    </div>
                    <div class="mui-button-row">
                    <button type="button" class="mui-btn mui-btn-warning" id="clear">重置</button>
                    <button type="button" class="mui-btn mui-btn-primary" id="next2">确定</button>
                    </div>
                    </div>
                    <div id="wrap2" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">基本信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="affDwmc" data-options="label:'单位名称',required:true,prompt:'请输入单位全称',validType:['empty']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-combobox" name="apSsq" data-options="label:'单位所在区（县）',required:true,valueField:'piItemcode',textField:'piItemname',
	                                        	url:'<%=basePath %>ParmItem/selectSsqExceptCenter',onSelect:selectSSQ,editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-combobox" name="apJdbsc" id="apJdbsc" data-options="label:'单位所在街道办事处',required:true,valueField:'piItemcode',textField:'piItemname',
		                                editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">申请人信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmXm" id="applyFamilyMembers0afmXm" value="${sessionScope.applyUserinfo.username}" data-options="label:'姓名',required:true,readonly:true,validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow readonlyRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[0].afmXb" id="applyFamilyMembers0afmXb"  data-options="label:'性别',required:true,readonly:true,editable:false,panelHeight:'auto'">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                    <div class="commonRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[0].afmHyzk" id="applyFamilyMembers0afmHyzk" data-options="label:'婚姻状况',required:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=07',
		                                    editable:false,panelHeight:'auto',panelMaxHeight:'280',onSelect:selecthyzk">
                                        </select>
                                    </div>
                                    <div class="commonRow readonlyRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmSfzh" id="applyFamilyMembers0afmSfzh" value="${sessionScope.applyUserinfo.sfzh}" data-options="label:'身份证号码',required:true,validType:['sfz','empty'],readonly:true">
                                    </div>
                                    <div class="commonRow readonlyRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[0].afmLxdh" value="${sessionScope.applyUserinfo.linktel}" data-options="label:'联系电话',required:true,validType:['phone','empty'],readonly:true">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="affGrnsr" id="applyFamilyMembers0afmNsr"
                                               data-options="label:'个人年收入(元)',required:true,validType:['number','empty'],events:{keyup:changeSr}">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="affWdhjdz" data-options="label:'外地户籍地址',required:true,validType:['empty']">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">配偶信息</div>
                            <!--内容区-->
                            <div class="mui-card-content" >
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmXm" id="applyFamilyMembers1afmXm"   data-options="label:'姓名',validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmSfzh" id="applyFamilyMembers1afmSfzh"  data-options="label:'身份证号码',validType:['sfz','empty']">
                                    </div>
                                    <div class="commonRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[1].afmXb" id="applyFamilyMembers1afmXb"  data-options="label:'性别',required:true,editable:false,panelHeight:'auto'">
                                            <option value="女">女</option>
                                            <option value="男">男</option>
                                        </select>
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmGzdw" id="applyFamilyMembers1afmGzdw"  data-options="label:'工作单位',prompt:'请输入单位全称'">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmLxdh" id="applyFamilyMembers1afmLxdh"  data-options="label:'联系电话',validType:'phone'">
                                    </div>
                                    <div class="commonRow readonlyRow">
                                        <select class="easyui-combobox" name="applyFamilyMembers[1].afmYsqrgx" id="applyFamilyMembers1afmYsqrgx" data-options="label:'关系',valueField:'piItemname',textField:'piItemname',panelHeight:'auto',readonly:true,editable:false">
                                            <option value="0">配偶</option>
                                        </select>
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyMembers[1].afmNsr" id="applyFamilyMembers1afmNsr"
                                               data-options="label:'个人年收入(元)',validType:['number','empty'],events:{keyup:changeSr}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">工作社保情况</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-datebox" name="affLxjzsj" data-options="label:'来徐时间',required:true,editable:false,validType:['minDate']">至今
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-datebox" name="affLdhtkssj" data-options="label:'劳动合同签订年限',required:true,editable:false,validType:['minDate']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-datebox" name="affLdhtjssj" data-options="label:'劳动合同终止年限',required:true,editable:false">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-datebox" name="affSbjnsj" data-options="label:'社会保险缴纳情况',required:true,editable:false,validType:['minDate','annual']">至今
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-datebox" name="affGjjjnsj" data-options="label:'住房公积金缴纳情况',editable:false,validType:['minDate']">至今
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">工作单位信息</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.legelrep" data-options="label:'法定代表人',required:true,validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.bsls" data-options="label:'统一社会信用代码',required:true,validType:['xyCode','empty']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.entag" data-options="label:'委托代理人',validType:['empty','chinese','specialCharacter']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.tel"  data-options="label:'联系方式',validType:['phoneTel','empty'],required:true">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyUnit.address" data-options="label:'联系住址',required:true,validType:['empty']">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-content-padded" style="margin-top: 2rem;">
                            <button id='next3' class="mui-btn mui-btn-block mui-btn-primary">下一步</button>
                        </div>
                    </div>
                    <div id="wrap3" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">家庭收入情况</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="apJtrk" id="apJtrk" value="1"
                                               data-options="label:'家庭人口',readonly:true,required:true,validType:'numberZ'">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="apJtnsr" id="apJtnsr"
                                               data-options="label:'家庭年收入(元)',readonly:true,validType:['empty','number']">
                                    </div>
                                    <div class="readonlyRow commonRow">
                                        <input class="easyui-textbox" name="apRjysr" id="apRjysr"
                                               data-options="label:'人均月收入(元)',readonly:true,validType:['empty','number']">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">家庭现住房情况</div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group">
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhZl"
                                               data-options="label:'房屋地址',required:true,validType:['empty']">
                                    </div>
                                    <div class="commonRow">
                                        <input class="easyui-textbox" name="applyFamilyHouses[0].afhCqr"
                                               data-options="label:'产权人',required:true,validType:['empty','chinese','specialCharacter']">
                                        </dv>
                                        <div class="commonRow">
                                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhMj" id="applyFamilyHouses0afhMj" data-options="label:'房屋面积(m²)',required:true,validType:['number','empty'],events:{keyup:changeMj}">
                                        </div>
                                        <div class="readonlyRow commonRow">
                                            <input class="easyui-textbox" name="applyFamilyHouses[0].afhRjmj" id="applyFamilyHouses0afhRjmj" data-options="label:'人均建筑面积(m²)',required:true,validType:'number',readonly:true">
                                        </div>
                                        <div class="commonRow">
                                            <input class="easyui-combobox" name="applyFamilyHouses[0].afhZfxz" data-options="label:'现住房性质',required:true,valueField:'piItemcode',textField:'piItemname',url:'<%=basePath %>ParmItem/getOptions?parmSetcode=01',
		                                                 editable:false,panelHeight:'auto',panelMaxHeight:'280'">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mui-button-row">
                                <button type="button" class="mui-btn mui-btn-primary" id="prev2">上一步</button>
                                <button type="button" class="mui-btn mui-btn-success" id="next4">下一步</button>
                            </div>
                        </div>
                    </div>
                    <div id="wrap4" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header">同住家庭成员情况<span class="greenPan">新增</span><span
                                    class="redPan">删除</span></div>
                        </div>
                        <div class="card-container"></div>
                        <div class="mui-button-row">
                            <button type="button" class="mui-btn mui-btn-primary" id="prev3">上一步</button>
                            <button type="button" class="mui-btn mui-btn-success" id="next5">下一步</button>
                        </div>
                    </div>
                    <div id="wrap5" style="display: none;">
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">申请人和共同承租家庭成员身份证</span><span class="reqSpan">*(必填)</span></span><span id="type4" class="detail">详情</span>
                            </div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList0">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark0 filePicker" onclick="add(0)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">户口簿及其他居住证明</span><span class="reqSpan">*(必填)</span></span><span id="type10" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList1">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark1 filePicker" onclick="add(1)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">劳动合同或者聘用合同</span><span class="reqSpan">*(必填)</span></span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList2">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark2 filePicker" onclick="add(2)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">收入情况证明材料</span><span class="reqSpan">*(必填)</span></span><span id="type11" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList3">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark3 filePicker" onclick="add(3)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">住房状况证明材料</span><span class="reqSpan">*(必填)</span></span><span id="type6" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList4">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark4 filePicker" onclick="add(4)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">单位出具的公租房申请公示证明</span><span class="reqSpan">*(必填)</span></span><span id="type7" class="detail">详情</span>
                            </div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList5">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark5 filePicker" onclick="add(5)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">住房公积金缴存证明或者社会保险经办机构出具的社会保险缴存证明</span><span class="reqSpan">*(必填)</span></span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList6">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark6 filePicker" onclick="add(6)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">婚姻状况证明</span><span class="reqSpan">*(必填)</span></span><span id="type2" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList7">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark7 filePicker" onclick="add(7)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span><span class="name">营业执照</span><span class="reqSpan">*(必填)</span></span><span id="type9" class="detail">详情</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList8">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark8 filePicker" onclick="add(8)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-card">
                            <!--页眉，放置标题-->
                            <div class="mui-card-header"><span class="name">其他相关材料</span></div>
                            <!--内容区-->
                            <div class="mui-card-content">
                                <div class="mui-input-group uploader-list" id="fileList9">

                                </div>
                            </div>
                            <div class="mui-card-footer">
                                <div class="fileMark9 filePicker" onclick="add(9)">添加图片</div>
                            </div>
                        </div>
                        <div class="mui-button-row">
                            <button type="button" class="mui-btn mui-btn-primary" id="prev4">上一页</button>
                            <button type="button" class="mui-btn mui-btn-success" id="tijiao">提交申请</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="mui-off-canvas-backdrop"></div>
        <%--</div>--%>
    </div>
</div>
    <div id="picture" class="mui-popover mui-popover-action ">
    <ul class="mui-table-view"></ul>
    </div>
<style>
    .textbox-label {
        max-width: 46%;
        width: 11.2rem;
    }

    .commonRow .textbox, .commonRow .combo, .commonRow .datebox {
        min-width: 50% !important;
        max-width: 80% !important;
    }

    .commonRow .textbox-text, .commonRow .validatebox-text {
        width: 100% !important;
    }
    #signatureparent {
        color: darkblue;
        background-color: darkgrey;
    }
</style>
<script src="<%=basePath %>srcApp/js/public/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/mui/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/easyui/jquery.easyui.mobile.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/webuploader/webuploader.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/public/jSignature.min.noconflict.js" type="text/javascript"></script>
<script src="<%=basePath %>srcApp/js/public/public.js"></script>
<script>
    mui.init();
    var person = false;var i=1;//是否可添加同住家庭成员
    var $sigdiv = '';var paramSign="";//签字版
    var j = 0, currentIndex = 0, sucIndex = 0,codeArray = [], nameArray = [],idArray=[],sendArray=[];
    var isSave=true;//控制是否可以提交变量
    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.0006
    });
    var mask = mui.createMask();//创建遮罩层
    $('.filePicker').each(function (index) {
        nameArray[index] = $(this).parent().parent().find('.name').text();
    });
    uploader = WebUploader.create({
        auto: false,
        swf: '<%=basePath %>srcApply/js/Uploader.swf',
        server: '<%=basePath %>volel/uoloadVolel',
        pick: {
            id: '.filePicker',
            label: '添加附件'
        },
        fileVal: "volname1",
      //sendAsBinary:true,//设置为二进制
        threads: 1,//并发最大数量限制
        fileNumLimit: 100,//限制最大张数100
        disableGlobalDnd: true, //禁用浏览器的拖拽功能，否则图片会被浏览器打开
        fileSizeLimit: 1000 * 1024 * 1024, // 总共的最大限制1000M
        fileSingleSizeLimit: 10 * 1024 * 1024, // 单文件的最大限制4M
        accept: {
            title: 'Images',
            extensions: 'jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        duplicate: true, //同一文件是否可重复选择
        compress: {
            width: 1200,
            height: 1200,
            quality: 90,// 图片质量，只有type为`image/jpeg`的时候才有效。
            allowMagnify: false,// 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            crop: false,// 是否允许裁剪。
            preserveHeaders: true,// 是否保留头部meta信息。
            noCompressIfLarger: false,
            compressSize: 0 // 单位字节，如果图片大小小于此值，不会采用压缩。
        },
        resize: false//尺寸不改变
    });
    function add(index){
        j=index;
    }
    function del(obj) {
        var fileId=obj.parent().attr("id");
        var index=weixin.getArrayIndex(idArray,fileId);
        idArray.splice(index,1);
        codeArray.splice(index,1);
        sendArray.splice(index,1);
        obj.parents(".thumbnail").remove();
        uploader.removeFile(fileId,true);
        uploader.getFiles('inited');
    }
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            uploader.refresh();
            weixin.alert("请上传JPG、PNG、GIF、BMP格式图片文件");
        }else if (type == "F_DUPLICATE") {
            uploader.refresh();
            weixin.alert("请不要重复选择文件！");
        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
            uploader.refresh();
            weixin.alert("每次选择文件大小不超过10M");
        }else if(type=="F_EXCEED_SIZE"){
            uploader.refresh();
            weixin.alert("每次选择文件大小不超过10M")
        }else if(type=="Q_EXCEED_NUM_LIMIT"){
            uploader.refresh();
            weixin.alert("每次选择文件大小不超过10M")
        }
    });
    uploader.on('fileQueued', function(file) {
        idArray.push(file.id);//存入数组
        codeArray.push(Number(j));
        sendArray.push(Number(j))
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img>' +
            '<div class="info" onclick="del($(this))">' + "删除" + '</div>' +
            '</div>'
            ),
            $img = $li.find('img');
        $("#fileList" + j + "").append($li);
        uploader.makeThumb(file, function(error, src) {
            if(error) {
                $("#fileList" + j + "").find(".thumbnail:last").remove();
                codeArray.pop();
                idArray.pop();
                sendArray.pop();
                uploader.removeFile(file);
                weixin.alert('请选择正确图片格式');
            }else{
                $img.attr('src', src);
            }
        }, 100, 100);
    });
    function send(applyid) {
        var newArray=copyArray(sendArray);
        if(newArray.length>0){
            uploader.on('uploadBeforeSend', function (obj, data, headers) {
                var code = newArray[currentIndex];
                data.volname = nameArray[code];
                data.fileVal = "volname" + (Number(1 + Number(code)));
                data.applyid = applyid;
            });
            uploader.upload();
            //上传后才执行
            uploader.on( 'uploadError', function( file ) {
                currentIndex++;
                if(currentIndex==newArray.length) {
                    weixin.toast('上传失败');
                    mask.close();
                }
            });
            //上传后才执行
            uploader.on('uploadSuccess', function (file) {
                currentIndex++;
                sucIndex++;
                if (sucIndex == newArray.length) {
                    weixin.toast('上传成功');
                    setTimeout(function () {
                        mask.close();
                        weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
                    }, 1000);
                }
            });
        }
    }
    //处理数组
    function copyArray(codeArray){
        return codeArray
    }

    function solveArray(codeArray){
        return codeArray.sort(sequence);
    }
    function sequence(a,b){
        return a - b;
    }

    function judgeArray(newArray) {
        var judgeFlag = true;
        var bitianArray = [0, 1, 2, 3, 4, 5, 6, 7, 8];
        var numberarray=weixin.spliceNumber(newArray);
        if(numberarray.length>0){
            for(var x in bitianArray){
                if(typeof(weixin.getArrayIndex(numberarray,bitianArray[x]))=="boolean"){
                    judgeFlag=false;
                    return false;
                }
            }
        }else{
            judgeFlag=false;
        }
        return judgeFlag;
    }

    //提交
    function save() {
        if(isSave){
            var isValid = $('#form').form('validate');
            if(!isValid){
                isSave=true;
                weixin.toast('请检查是否有漏填或错填内容！');
                return false
            }else{
                isSave=false;
                checkUser()
            }
        }
    }
    //验证身份证信息是否合法
    function checkUser(){
        var userArray=[],sfzhArray=[];//所有身份证数组
        var checkIndex=$(".card-container .mui-card").length+2;
        for(var k=0;k<checkIndex;k++){
            var user=$.trim($("#applyFamilyMembers"+k+"afmXm").textbox('getValue'));
            if(user.length>0){
                userArray.push($("#applyFamilyMembers"+k+"afmXm").textbox('getValue'));
                sfzhArray.push($("#applyFamilyMembers"+k+"afmSfzh").textbox('getValue'));
            }
        }
        if(userArray.length>0){
            userArray.splice(0,1);
            sfzhArray.splice(0,1);
            var endUser=userArray;
            var endSfzh=sfzhArray;
            if(endUser.length==0){
                isSave=false;
                secondSave()
            }
            if(endUser.length>0){
                $.ajax({
                    url:'<%=basePath %>applyUserinfo/checkUsers',
                    type:'post',
                    async:true,//同步
                    data:{
                        usernames:endUser.toString(),
                        sfzhs:endSfzh.toString()
                    },
                    success:function(result){
                        if(result=="success"){
                            isSave=false;
                            secondSave();
                        }else{
                            isSave=true;
                            weixin.toast(result+"身份证和姓名不匹配");
                            return false
                        }
                    },error:function () {
                        isSave=true;
                        weixin.alert("数据库连接失败，请稍后再试");
                        return false
                    }
                });

            }
        }
    }
    function secondSave() {
        var judgeFlag=judgeArray(solveArray(codeArray));
        var flag=false;
        $('#form').form('submit', {
            url: '<%=basePath %>applyns/addApplyNsInfo',
            onSubmit: function(param){
                param.applyType = "4";
                param.applyid = "${applyid}";
                  param.apBaseimg=paramSign;
                var isValid = $(this).form('validate');
                if (!isValid) {
                    isSave=true;
                    flag=false;
                    weixin.toast('请检查是否有漏填或错填内容！');
                }else if(!judgeFlag){
                    flag=false;
                    isSave=true;
                    weixin.toast("附件所有必填项不能为空！")
                }else{
                    isSave=false;
                    flag=true;
                }
                return flag;
            },
            success: function(data){
                var data=JSON.parse(data);
                if(data.result == "success"){
                    $(".over").show();
                    var applyid=data.applyid;
                    currentIndex=0;
                    send(applyid)
                }else{
                    isSave=true;
                    weixin.toast(data.result)
                }
            },
            error:function (data) {
                isSave=true;
                weixin.alert("数据库连接失败，请稍后再试");
            }
        },'json');
    }
    //根据id查看详情
    mui('.mui-card').on('tap', '.detail', function() {
        var id=this.getAttribute("id");
        var type=Number(id.substring(4));
        mui('#picture').popover('toggle');
        weixin.typeDetail(type);
    });
    mui(document.body).on('tap', '.mui-btn', function (e) {
    var id = this.getAttribute("id");
    var code = id.substring(4);
    mui("#pullrefresh").scroll().scrollTo(0, 0, 0);
    if (id.indexOf("next") != -1) {
    if(id=="next1"){
    if($('#check').is(':checked')) {
    $('#wrap' + code + '').show().siblings().hide();
    //签字版
    $sigdiv = $("#signature").jSignature({
    'UndoButton': false,
    'height':360,
    'width':400
    })
    }else{
    weixin.toast("请详细阅读申请须知")
    }
    }else if(id=="next2"){
    var data=$sigdiv.jSignature('getData', 'svgbase64');//'image/svg+xml;base64';
     paramSign = 'data:' + data[0] + ',' + data[1];
    var compareSign = 'PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMCIgaGVpZ2h0PSIwIj48L3N2Zz4=';
    if(compareSign!=data[1]){
    //跳转
    $('#wrap' + code + '').show().siblings().hide();
    } else {
    weixin.alert("请输入本人姓名");
    }
    }else{
    $('#wrap' + code + '').show().siblings().hide();
    uploader.refresh();
    }
    }
    if(id=="clear"){
    $sigdiv.jSignature('reset');
    }
    if (id.indexOf("prev") != -1) {
    $('#wrap' + code + '').show().siblings().hide();
    uploader.refresh();
    }
    if (id == "tijiao") {
    save()
    }
    });
    mui(".mui-bar").on('tap', '.mui-tab-item', function (e) {
        var target = this.getAttribute("id");
        switch (target) {
            case 'apply':
                weixin.openWindow('applyList', '<%=basePath %>appPath/applyList');
                break;
            case 'audit':
                weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
                break;
            case 'userInfo':
                weixin.openWindow('userInfo', '<%=basePath %>appPath/userInfo');
                break;
            case 'updatePwd':
                weixin.openWindow('updatePwd', '<%=basePath %>appPath/updatePwd');
                break;
            default:
                break;
        }
    });
    //返回上一页
    mui(".mui-bar").on('tap','#back',function () {
        mui.back();
    });
    mui(".mui-table-view").on('tap', '.leftList', function (e) {
        var target = this.getAttribute("id");
        switch (target) {
            case 'applyAside':
                weixin.openWindow('applyList', '<%=basePath %>appPath/applyList');
                break;
            case 'audioAside':
                weixin.openWindow('audioList', '<%=basePath %>appPath/audioList');
                break;
            case 'userAside':
                weixin.openWindow('userInfo', '<%=basePath %>appPath/userInfo');
                break;
            case 'updateAside':
                weixin.openWindow('updatePwd', '<%=basePath %>appPath/updatePwd');
                break;
            case 'exitAside':
                window.location.href = "<%=basePath %>appLogin/appExit";
                break;
            default:
                break;
        }
    });
    //新增家庭成员
    mui(".mui-card").on('tap', '.greenPan', function (e) {
        var addIndex=Number($(".card-container .mui-card").length)+2;
        if (person) {
            i++;
            $("#apJtrk").textbox('setValue',i);
            var perString = $('<div class="mui-card">' +
                '<div class="mui-card-header">同住家庭成员情况</div>' +
                '    <div class="mui-card-content">' +
                '       <div class="mui-input-group">' +
                '            <div class="commonRow">' +
                '                <select class="easyui-combobox" name="applyFamilyMembers['+addIndex+'].afmYsqrgx"  data-options="label:\'关系\',valueField:\'piItemname\',textField:\'piItemname\',panelHeight:\'auto\',required:true,editable:false">' +
                '                   <option value="3">子女</option>'+
                '               </select>'+
                '            </div>' +
                '            <div class="commonRow">' +
                '                <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmXm" id="applyFamilyMembers' + addIndex+ 'afmXm"  data-options="label:\'姓名\',required:true,validType:[\'empty\',\'chinese\',\'specialCharacter\']">' +
                '            </div>' +
                '            <div class="commonRow readonlyRow">' +
                '                <input class="easyui-datebox" name="applyFamilyMembers[' + addIndex+ '].afmCsny" id="applyFamilyMembers' + addIndex+ 'afmCsny"  data-options="label:\'出生年月\',required:true,readonly:true,editable:false">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                <select  class="easyui-combobox" name="applyFamilyMembers['+addIndex+'].afmHyzk" id="applyFamilyMembers'+addIndex+'afmHyzk" style="width: 92%" data-options="label:\'婚姻状况\',valueField:\'piItemcode\',textField:\'piItemname\',readonly:true,editable:false">' +
                '                 <option value="1">未婚</option>'+
                '               </select>'+
                '            </div>' +
                '            <div class="commonRow">' +
                '                <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmGzdw"  data-options="label:\'工作单位\',prompt:\'请输入单位全称\'">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                 <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmSfzh" id="applyFamilyMembers' + addIndex + 'afmSfzh"   data-options="label:\'身份证号码\',required:true,validType:[\'sfz\',\'empty\'],events:{blur:setBirthday}">' +
                '            </div>' +
                '            <div class="commonRow">' +
                '                 <input class="easyui-textbox" name="applyFamilyMembers[' + addIndex + '].afmNsr" id="applyFamilyMembers' + addIndex + 'afmNsr"  data-options="label:\'个人年收入(元)\',required:true,validType:\'number\',events:{keyup:changeSr}">' +
                '            </div>' +
                '       </div>' +
                '     </div>' +
                '</div>').appendTo(".card-container");
            $.parser.parse(perString);
            changeMj(i);
            changeSr(i)
        } else {
            weixin.alert("婚姻状况未填或未婚状态，不能添加家庭成员")
        }
    });
    //删除家庭成员
    mui(".mui-card").on('tap', '.redPan', function (e) {
        var len= $(".card-container .mui-card").length;
        if(len>0){
            i--;
        }
        $("#apJtrk").textbox('setValue',i );
        $("#wrap4 .card-container").find(".mui-card:last").remove();
        changeMj(i);
        changeSr(i)
    });
    //删除同住人口
    //街区联动
    function selectSSQ(record) {
        $("#apJdbsc").combobox({
            url: '<%=basePath%>ParmItem/findAllJd',
            onBeforeLoad: function (param) {
                param.qid = record.piItemcode;
            }
        });
    }

    function changeMj(obj) {
        var allMj;
        if (typeof(obj) == "number") {
            allMj = $("#applyFamilyHouses0afhMj").textbox("getValue");
        } else {
            allMj = $(this).val();
        }
        var rjmj = Number(Number(allMj) / Number(i)).toFixed(2);
        $("#applyFamilyHouses0afhRjmj").textbox('setValue', rjmj);
    }
    function changeSr(obj) {
        var eachIndex= $(".card-container .mui-card").length+2;
        var sum = 0, average = 0, yuan = 0;
        for (var j = 0; j <eachIndex; j++) {
            if ($("#applyFamilyMembers" + j + "afmNsr").val() != undefined) {
                yuan = $("#applyFamilyMembers" + j + "afmNsr").textbox('getValue')
            } else {
                yuan = 0;
            }
            sum += Number(yuan);
        }
        if (typeof(obj) == "object") {
            var id = $(this).parent().siblings('input').attr("id");
            var oldValue = $('#' + id + '').textbox('getValue');
            sum = sum - Number(oldValue) + Number($(this).val());
        }
        average = Number(Number(sum) / (i) / 12).toFixed(2);
        //计算年收入，平均收入
        $("#apJtnsr").textbox('setValue', sum);
        $("#apRjysr").textbox('setValue', average);
    }


    //先输入了姓名后选择关系
    function judgeByAge(birthday, id) {
        if (new Date().getFullYear() - (birthday.substring(0, 4)) > 18) {
                $("#applyFamilyMembers" + id + "afmSfzh").textbox('setValue');
                $("#applyFamilyMembers" + id + "afmCsny").textbox('setValue', '');//清空
                weixin.alert('子女年龄大于18岁不能添加')
        }

    }

    //根据婚姻状况控制配偶信息
    function selecthyzk(record){
        var ulLen=$(".card-container .mui-card").length;//判断是否有家庭成员
        //已婚
        if(record.piItemcode=="2"){
            i=2+Number(ulLen);
            person=true;
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('reload');
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:true,readonly:false});
        }else if(record.piItemcode=="1"){//未婚
            i=1;
            person=false;
            $(".card-container").html("");
            $.parser.parse($(".card-container"));
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,readonly:true});
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('clear');
        }else{
            i=Number(ulLen)+1;
            person=true;
            $("#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw").textbox('setValue',"");
            $('#applyFamilyMembers1afmXm,#applyFamilyMembers1afmSfzh,#applyFamilyMembers1afmLxdh,#applyFamilyMembers1afmNsr,#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb,#applyFamilyMembers1afmGzdw').textbox({required:false,readonly:true});
            $('#applyFamilyMembers1afmYsqrgx,#applyFamilyMembers1afmXb').combobox('clear');
        }
        $("#apJtrk").textbox('setValue',i);
        changeMj(i);
        changeSr(i);
    }
    //回填出生日期
    function setBirthday(){
        var name=$(this).siblings().attr('name');
        var id=name.substring(name.indexOf('[')+1,name.indexOf(']'));
        if(name.indexOf('afmSfzh')>0){
            var  sfzh=$(this).val();
        }
        if(sfzh.length==0){
            $("#applyFamilyMembers"+id+"afmCsny").textbox('setValue','');
            return false
        }else{
            var birthday= weixin.setBirthday(sfzh);
            $("#applyFamilyMembers"+id+"afmCsny").textbox('setValue',birthday);
            judgeByAge(birthday,id);
        }
    }
    $(function () {
        $('#applyFamilyMembers0afmXb').combobox('setValue','${sessionScope.applyUserinfo.sex}');
    })

</script>
</body>
</html>

