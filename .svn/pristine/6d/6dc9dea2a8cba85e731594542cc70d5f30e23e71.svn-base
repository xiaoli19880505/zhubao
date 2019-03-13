
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout" style="background:#d5e1f2">
<div class="over" style="display: none;">
    <div id="loading"><img src="<%=basePath %>srcApply/img/public/loading.gif" ></div>
</div>
<div data-options="region:'north',border:'false'" id="publicNorth">
    <div class="search-group" style="float: right;width:180px;margin-right: 50px">
        <li class="query-btn" style="float: right"><a class="icon iconfont icon-icon- " onclick="save()"><i>保存</i></a></li>
    </div>
</div>
<div data-options="region:'center',border:false">
    <form class='easyui-form' id='form' method='post'>
        <div class="contract newcontract">
            <div class="conPage">
                <div class="conCode">合同编号：${contractTemplateFillingDataPojo.contractNo }</div>
                <div class="titleContainer">
                    <div class="conTitle">徐州市市区</div>
                    <div class="conTitle"> 公共租赁住房租赁合同</div>
                </div>
                <div class="conPrint">徐州市住房保障服务中心印制</div>
                <div class="xiaoTitle">徐州市市区公共租赁住房租赁合同</div>


                <div class="preColumn">出租方（以下简称甲方）：<span class="conUnder" style="width: 510px">徐州市住房保障服务中心</span></div>
                <div class="preColumn">承租方（以下简称乙方）：<span class="conUnder" style="width: 510px">${contractTemplateFillingDataPojo.userName }</span></div>
                <div class="preColumn">身份证号码：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.userCardId }</span></div>
                <div class="preColumn">固 定 电话：<span class="conUnder" style="width: 240px">${contractTemplateFillingDataPojo.fixedTelephone }</span>
                                        手  &nbsp;&nbsp;&nbsp; 机 ：<span class="conUnder" style="width: 240px">${contractTemplateFillingDataPojo.mobilePhone}</span>
                </div>
                <div class="preColumn">工 作 单位：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.employer }</span></div>
                <div class="preColumn">联 系 住址：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.contactAddress }</span></div>
                <div class="preColumn">邮 政 编码：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.postalCode }</span></div>

                <div class="preColumn">委托代理人姓名：<span class="conUnder" style="width: 592px">${contractTemplateFillingDataPojo.principadName }</span></div>
                <div class="preColumn">身份证号码：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.principadCardId }</span></div>
                <div class="preColumn">住   &nbsp;&nbsp;&nbsp;&nbsp; 址：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.principadAddress}</span></div>
                <div class="preColumn">邮 政 编码：<span class="conUnder" style="width: 635px">${contractTemplateFillingDataPojo.principadPostalCode}</span></div>
                <div class="preColumn">固 定 电话：<span class="conUnder" style="width: 240px">${contractTemplateFillingDataPojo.principadTelephone }</span>
                    手  &nbsp;&nbsp;&nbsp; 机 ：<span class="conUnder" style="width: 240px">${contractTemplateFillingDataPojo.principadMobilePhone }</span>
                </div>
                <div class="preColumn">工 作 单位：<span class="conUnder" style="width:635px">${contractTemplateFillingDataPojo.principadEmployer }</span></div>



                <div class="conContent">
                    根据《徐州市市区公共租赁住房管理办法》（徐政规﹝2012﹞8号），遵循平等、公平和诚实信用等原则，经甲乙双方协商一致，就公共租赁住房的租赁事宜签订本合同:
                </div>
                <div class="conContent">
                    <span class="conColumn">一、</span>乙方符合市区公共租赁住房的承租条件（低保、特困家庭□，低收入无房家庭□，中等偏下收入住房困难家庭 □），
                    甲方将坐落于<span class="conUnder">${contractTemplateFillingDataPojo.street }</span>路（街、巷）
                                <span class="conUnder">${contractTemplateFillingDataPojo.community }</span>小区
                                <span class="conUnder">${contractTemplateFillingDataPojo.building}</span>栋
                                <span class="conUnder">${contractTemplateFillingDataPojo.unit }</span>单元
                                <span class="conUnder">${contractTemplateFillingDataPojo.room }</span>室的公共租赁住房(以下简称：该房屋)出租给乙方及共同承租人居住使用，
                    房屋建筑面积为 <span class="conUnder">${contractTemplateFillingDataPojo.constructionArea}</span>㎡。
                </div>
                <div class="conContent">
                    <span class="conColumn">二、</span>该房屋租赁期限自
                    <span class="conUnder">${contractTemplateFillingDataPojo.yearSta }</span>年
                    <span class="conUnder">${contractTemplateFillingDataPojo.monthSta }</span>月
                    <span class="conUnder">${contractTemplateFillingDataPojo.daySta}</span>日起至
                    <span class="conUnder">${contractTemplateFillingDataPojo.yearEnd }</span>年
                    <span class="conUnder">${contractTemplateFillingDataPojo.monthEnd }</span>月
                    <span class="conUnder">${contractTemplateFillingDataPojo.dayEnd }</span>日止。
                </div>
                <div class="conContent">
                    <span class="conColumn">三、</span>该房屋按建筑面积计算租金，租金标准为
                    <input class="easyui-textbox" name="rentUnitPrice" data-options="required:true,validType:['empty']"/>元/平方米·月，
                    月租金为人民币 <input class="easyui-textbox" name="rent" id="rent" data-options="required:true,validType:['empty'],events:{keyup:change}"/>元（大写：<span class="yearUnder" id="yearUnder" style="width: auto!important;min-width: 60px"></span>）。
                    租赁期内，按照徐州市公共租赁住房管理相关规定调整租金标准的，乙方承诺按照调整后的标准缴纳租金。
                </div>
                <div class="conContent">
                    <span class="conColumn">四、</span>按照先交租金后使用的原则，租金按季度缴纳，乙方应提前10 日缴纳下季度的房屋租金，逾期未缴每天加收月租金 2 %的滞纳金。退租时，按实际使用天数计算租金。
                </div>
                <div class="conContent">
                    <span class="conColumn">五、</span>合同签订之日，乙方按1000元/户的标准缴纳房屋租赁保证金。合同期满或终止，乙方无违约事项或其他损害事由的，且乙方已经结清房租及水、电、燃气、物业等相关费用后7个工作日内退还房屋租赁保证金本金。
                </div>
                <div class="conContent">
                    <span class="conColumn">六、</span>房屋租赁期间，发生的与使用该房屋相关的费用，包括水电燃气费、物业服务费、电视电话网络费等，由乙方承担。如迟交或不交给甲方造成损失的由乙方承担责任。
                </div>
                <div class="conContent">
                    <span class="conColumn">七、</span>乙方不得利用承租的房屋进行非法活动，如因非法活动给甲方造成损失的须承担相应的法律和赔偿责任。
                </div>
                <div class="conContent">
                    <span class="conColumn">八、</span>乙方严格遵守小区物业管理的规定，缴纳物业服务费用。
                </div>
                <div class="conContent">
                    <span class="conColumn">九、</span>房屋维修规定
                    <br>（一）租赁期间，甲方应定期检查房屋，确保该房屋及其附属设施处于安全状态。
                    <br>（二）乙方应当合理使用并爱护房屋及其附属设施设备，不得对房屋进行装修。
                    <br>（三）室内日常使用的设施设备等由乙方自行负责维修。
                    <br>（四）因乙方使用不当造成房屋及其附属设施设备损坏以及造成甲方或第三人财产损失和人身损害的，乙方承担维修责任和赔偿责任。
                    <br>（五）甲方如需要对房屋及附属设施进行检查或维修时，乙方应积极配合，如因乙方原因导致房屋及附属设施不能及时维修而发生安全事故的，乙方承担全部责任。
                </div>
                <div class="conContent">
                    <span class="conColumn">十、</span>乙方有下列行为之一的，甲方有权解除合同，收回公共租赁住房，并将有关情况记入乙方的住房保障个人诚信档案。
                    <br>（一）租赁期届满，承租人未再续租的;
                    <br>（二）承租人不再符合公共租赁住房保障条件的；
                    <br>（三）承租人擅自改变公共租赁住房用途的；
                    <br>（四）破坏或者擅自装修所承租公共租赁住房，拒不恢复原状的；
                    <br>（五）承租人无正当理由连续3个月以上未在承租的公共租赁住房居住的；
                    <br>（六）承租人将公共租赁住房出借、转租或者擅自调换的；
                    <br>（七）承租人无正当理由累计3个月以上未缴纳公共租赁住房租金的；
                    <br>（八）在公共租赁住房中从事违法活动的；
                    <br>（九）其它违反租赁合同约定和相关规定情形的。
                </div>
                <div class="conContent"><span class="conColumn">十一、</span>乙方在本合同租赁期限届满后，经市住房保障服务中心审核符合公共租赁住房承租条件的，甲方与乙方续签租赁合同。
                    房屋租赁期限内，乙方去世或者外迁的，甲方有权解除本合同，收回房屋。乙方同户籍家庭共同居住人需继续承租的，应重新提出书面申请，办理相关手续。
                </div>
                <div class="conContent"><span class="conColumn">十二、</span>公共租赁住房实行年度复审制度，乙方在租赁期限届满前一个月将家庭收入、人口及住房面积情况向市住房保障服务中心申报复审，对符合条件的，在办理相关手续后可继续承租公共租赁住房，对不符合条件的，应及时退出公共租赁住房。
                </div>
                <div class="conContent"><span class="conColumn">十三、</span>房屋腾退规定
                    <br>（一）乙方应自合同解除或终止之日腾退该房屋, 并结清租金、物管费、水、电、气等相关费用，将房屋及附属设施交还甲方。
                    <br>（二）乙方在租赁合同期满或终止后，不符合租住条件但暂时无法退房的，可以给予3个月的过渡期，签订《短期租赁合同》，过渡期内按市场租金的80%标准计收租金。
                    <br>（三）乙方不再符合租住条件，拒不腾退住房的，按同地段市场租金标准计收租金。甲方有权提起诉讼，申请人民法院强制执行。
                </div>
                <div class="conContent"><span class="conColumn">十四、</span>租赁期间，该房屋因不可抗力导致毁损或造成损失的，甲、乙双方互不承担责任。
                </div>
                <div class="conContent"><span class="conColumn">十五、</span>其他约定事项
                    乙方承诺：本合同中填写的名称、联系方式、证件信息准确无误，为与甲方联系的有效方式。联系方式如有变更，乙方应书面通知甲方。甲方依据上述联系方式向乙方发送的各项通知，发出即视为送达。
                </div>
                <div class="conContent"><span class="conColumn">十六、</span> 本合同未尽事宜按照《徐州市市区公共租赁住房管理办法》和《徐州市市区公共租赁住房运营管理实施意见》的相关规定执行。
                </div>
                <div class="conContent"><span class="conColumn">十七、</span>本合同履行中发生的争议，双方协商解决，协商不成的，可选择<select class="easyui-combobox" name="disputeResolution" style="width: 100px" data-options="required:true,editable:false,panelHeight:'auto',valueField:'id',textField:'text'">
                    <option value="（一）">（一）</option>
                    <option value="（二）">（二）</option>
                </select>方式解决：
                    <br>（一）向徐州市仲裁委员会申请仲裁；
                    <br>（二）向房屋所在地人民法院提起诉讼。
                </div>
                <div class="conContent">
                    <span class="conColumn">十八、</span>本合同经甲乙双方签字盖章后生效。本合同一式贰份，甲方执壹份，乙方执壹份，具有同等法律效力。
                </div>


                <table>
                    <tr>
                        <td>甲方（签章）： </td>
                        <td> 乙方（签字）：</td>
                    </tr>
                    <tr>
                        <td> 经办人： </td>
                        <td> 联系电话：</td>
                    </tr>
                    <tr>
                        <td>联系电话：  </td>
                        <td>联系地址：</td>
                    </tr>
                    <tr>
                        <td>联系地址：</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><span class="yearUnder"></span>年<span class="yearUnder"></span>月<span class="yearUnder"></span>日  </td>
                        <td> <span class="yearUnder"></span>年<span class="yearUnder"></span>月<span class="yearUnder"></span>日</td>
                    </tr>
                </table>
                <input type="hidden" name="ctCode" value="${contractTemplateFillingDataPojo.ctCode}"/>
                <input type="hidden" name="contractNo" value="${contractTemplateFillingDataPojo.contractNo}"/>
                <input type="hidden" name="userName" value="${contractTemplateFillingDataPojo.userName }"/>
                <input type="hidden" name="userCardId" value="${contractTemplateFillingDataPojo.userCardId }"/>
                <input type="hidden" name="postalCode" value="${contractTemplateFillingDataPojo.postalCode}"/>
                <input type="hidden" name="fixedTelephone" value="${contractTemplateFillingDataPojo.fixedTelephone }"/>
                <input type="hidden" name="mobilePhone" value="${contractTemplateFillingDataPojo.mobilePhone }"/>
                <input type="hidden" name="contactAddress" value="${contractTemplateFillingDataPojo.contactAddress }"/>
                <input type="hidden" name="employer" value="${contractTemplateFillingDataPojo.employer}"/>

                <input type="hidden" name="principadName" value="${contractTemplateFillingDataPojo.principadName}"/>
                <input type="hidden" name="principadCardId" value="${contractTemplateFillingDataPojo.principadCardId  }"/>
                <input type="hidden" name="principadPostalCode" value="${contractTemplateFillingDataPojo.principadPostalCode }"/>
                <input type="hidden" name="principadTelephone" value="${contractTemplateFillingDataPojo.principadTelephone  }"/>
                <input type="hidden" name="principadMobilePhone" value="${contractTemplateFillingDataPojo.principadMobilePhone}"/>
                <input type="hidden" name="principadAddress" value="${contractTemplateFillingDataPojo.principadAddress}"/>
                <input type="hidden" name="principadEmployer " value="${contractTemplateFillingDataPojo.principadEmployer}"/>

                <input type="hidden" name="street" value="${contractTemplateFillingDataPojo.street}"/>
                <input type="hidden" name="community" value="${contractTemplateFillingDataPojo.community }"/>
                <input type="hidden" name="building" value="${contractTemplateFillingDataPojo.building }"/>
                <input type="hidden" name="unit" value="${contractTemplateFillingDataPojo.unit}"/>
                <input type="hidden" name="room" value="${contractTemplateFillingDataPojo.room}"/>
                <input type="hidden" name="constructionArea" value="${contractTemplateFillingDataPojo.constructionArea }"/>
                <%--<input type="hidden" name="rentName" value="${contractTemplateFillingDataPojo.rentName }"/>--%>

                <input type="hidden" name="yearSta" value="${contractTemplateFillingDataPojo.yearSta}"/>
                <input type="hidden" name="monthSta" value="${contractTemplateFillingDataPojo.monthSta}"/>
                <input type="hidden" name="daySta" value="${contractTemplateFillingDataPojo.daySta}"/>
                <input type="hidden" name="yearEnd" value="${contractTemplateFillingDataPojo.yearEnd}"/>
                <input type="hidden" name="monthEnd" value="${contractTemplateFillingDataPojo.monthEnd}"/>
                <input type="hidden" name="dayEnd" value="${contractTemplateFillingDataPojo.dayEnd}"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script>
    function save(){
        $(".over").show();
        var data =bank.biography().getParams('idData');
        var objId = data.row.objId;
        var type = "${contractTemplateFillingDataPojo.apSqlb}";
        var rentName = $("#yearUnder").text();
        var cType = "";
        if(data.row.contractDetail == undefined){
            cType = 1;
        }else if(data.row.contractDetail != undefined && data.row.contractDetail.cType == ""){
            cType = 1;
        }else if(data.row.contractDetail != undefined && data.row.contractDetail.cType != ""){
            cType = data.row.contractDetail.cType;
        }
        $('#form').form('submit', {
            url:'<%=basePath%>contractTemplate/getContractTemplateFile',
            onSubmit: function(param){
                param.objId = objId;
                param.apSqlb = type;
                param.rentName = rentName;
                param.cType = cType;
                var isValid = $(this).form('validate');
                if (!isValid){
                    $(".over").hide();
                    bank.ajaxMessage('存在漏填项！');
                }
                return isValid;
            },
            success: function(data){
                data =JSON.parse(data);
                $(".over").hide();
                if(data.flag == true){
                    //提交表单
                    var a = $('<a href="<%=basePath %>'+data.msg+'" target="_blank"></a>')[0];
                    var e = document.createEvent('MouseEvents');
                    e.initEvent('click', true, true);
                    a.dispatchEvent(e);
                } else {
                    bank.ajaxMessage(data.msg);
                }
            }
        })
    }
    function change() {
        var rent = $(this).val();
        $.ajax({
            type:"post",
            url:"<%=basePath%>contractTemplate/getMoneyZH",
            async:true,
            data:{
                rentalSubsidiesTP:rent
            },
            success:function (data) {
                var data = JSON.parse(data);
                if(data.flag == true){
                    $("#yearUnder").html( data.msg);
                }else{
                    bank.ajaxMessage(data.msg);
                    $("#yearUnder").html( "");
                }
            }
        });
    }
</script>


