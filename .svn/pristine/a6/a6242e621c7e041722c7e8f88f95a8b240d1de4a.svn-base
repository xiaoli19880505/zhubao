<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/index/header.jsp" flush="true"></jsp:include>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="overflow: hidden" id="publicNorth">
    <div class="easyui-panel" style="width: 100%;overflow: hidden">
        <div class="houseType">
            <div class="smallType type1" data-id="5">
                <div class="typeCount"></div>
                <div class="typeName">公共租赁住房（新就业人员）</div>
            </div>
            <div class="smallType type2" data-id="4">
                <div class="typeCount"></div>
                <div class="typeName">公共租赁住房（外来务工人员）</div>
            </div>
            <div class="smallType type3" data-id="3">
                <div class="typeCount"></div>
                <div class="typeName">公共租赁住房（低保、特困家庭）</div>
            </div>
            <div class="smallType type3" data-id="2">
                <div class="typeCount"></div>
                <div class="typeName">公共租赁住房租赁补贴</div>
            </div>
            <%--<div class="smallType type5" data-id="1">
                <div class="typeCount"></div>
                <div class="typeName" >经济适用房</div>
            </div>--%>
        </div>
        <form id="queryForm">
            <ul class="search-group">
                <li><input class="easyui-textbox" name="username" id="username" style="width: 90%" data-options="label:'申请人:'"></li>
                <li><input class="easyui-textbox" name="sfzh" id="sfzh" style="width: 92%" data-options="label:'身份证号:'"></li>
                <li class="readonly ssq">
                    <input class="easyui-combobox" name="ssq" id="ssq" style="width: 92%" data-options="label:'所属县区:',panelHeight:'auto',panelMaxHeight:'280',
					editable:false,valueField:'piItemcode',textField:'piItemname',onSelect:select">
                </li>
                <li>
                    <select class="easyui-combobox" name="ssjd" id="ssjd" style="width: 92%" data-options="label:'所属街道:',panelHeight:'auto',panelMaxHeight:'280',
					editable:false,valueField:'piItemcode',textField:'piItemname'"></select>
                </li>
                <li>
                    <select class="easyui-combobox" name="sendMes" id="sendMes" style="width: 92%" data-options="label:'短信状态:',panelHeight:'auto',panelMaxHeight:'280',
					editable:false,valueField:'piItemcode',textField:'piItemname'"></select>
                </li>
                <li class="query-btn">
                    <a class="icon iconfont icon-sousuo" onclick="query()"><i>查&nbsp;询</i></a>
                </li>
                <li class="query-btn reset">
                    <a class="icon iconfont" onclick="sendAllMessByAll()"><i>群发短信(多条)</i></a>
                </li>
                <li class="query-btn reset">
                    <a class="icon iconfont" onclick="sendAllMessByOne()"><i>群发短信(单条)</i></a>
                </li>
            </ul>
           <%-- <ul class="search-group">
                <span style="float: left;margin-right: 45px">操作：</span>
                <li class="query-btn reset">
                    <a class="icon iconfont" onclick="sendAllMessByAll()"><i>群发短信(多条)</i></a>
                </li>
                <li class="query-btn reset">
                    <a class="icon iconfont" onclick="sendAllMessByOne()"><i>群发短信(单条)</i></a>
                </li>
            </ul>--%>
        </form>
    </div>
</div>
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <table id="dataTable" class="easyui-datagrid resTable" style="height: 100%;min-height: 300px"
               data-options="url:'<%=basePath %>nstask/listInform?oldData=yes',singleSelect:false,rownumbers:true,pagination:true,border:true,striped:true,
                       pagePosition:'bottom',nowrap:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'cb',checkbox:true,align:'center'"></th>
                <th data-options="field:'APPTYPE',width:180,align:'center',halign:'center',formatter:convenrtApplyType">申请类型</th>
                <th data-options="field:'APLBH',width:100,align:'center'">申请编号</th>
                <th data-options="field:'USERNAME',width:150,align:'center'">申请人姓名</th>
                <th data-options="field:'APLDATE',width:100,align:'center'">录入日期</th>
                <th data-options="field:'QYSJ',width:100,align:'center'">签约日期</th>
                <th data-options="field:'LINKTEL',width:100,align:'center'">手机号</th>
                <th data-options="field:'SFZH',width:200,align:'center'">身份证号</th>
                <th data-options="field:'SSQ',width:100,align:'center'">所属县区</th>
                <th data-options="field:'SSJD',width:100,align:'center'">所属街道</th>
                <th data-options="field:'APPZT',width:100,align:'center',formatter:convertState">状态</th>
                <th data-options="field:'MSEXS',width:100,align:'center'">短信是否发送</th>
                <th data-options="field:'MSTIME',width:150,align:'center'">发送时间</th>
                <th data-options="field:'essetNature',width:150,align:'center',formatter:operate">操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="easyui-dialog" style="width:500px;height:400px;display: none" id="info"
         data-options="title:'通知内容',buttons:'#bb',modal:true,closed: true">
        <div style="width:100%;height:90%;" >
            <div class="message" style="margin: 20px auto 0;width: 80%;overflow: hidden">

            </div>
            <ul class="search-group" style="margin-top: 100px">
                <li class="query-btn" style="margin-left: 190px" onclick="submit()">
                    <a class="icon iconfont icon-icon-" ><i>发&nbsp;送</i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
<style>
    .query-btn {
        width: 120px !important;
    }
</style>
<script>
    var reg=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
    var showArray=[];
    var sendArray=[];
    var sendJson={};
     sendJson.usernames=[];
    sendJson.rooms=[];
    sendJson.htqxs=[];
    sendJson.linktels=[];
    sendJson.sqbhs=[];
    sendJson.sfzhs=[];
    sendJson.applyids=[];
    sendJson.updateLinkTelFlags=[];
    sendJson.updateContraFlags=[];
    //为街道和街区默认赋值
    $(function () {
        var ssqId = "${sessionScope.user.parmItemssq.piItemcode}";
        var ssqname = "${sessionScope.user.parmItemssq.piItemname}";
        var ssjId = "${sessionScope.user.ssj}";
        var ssjText = "${sessionScope.user.parmItemssjd.piItemname}";
        if(ssqId=="0"){//市区，去掉市区，改为下拉
            $("#ssq").combobox({
                url:'<%=basePath %>ParmItem/getOptions?parmSetcode=05',
                onLoadSuccess:function(){
                    var datas=$("#ssq").combobox('getData');
                    if(datas[0].piItemcode=="0"){
                        datas.splice(0,1);
                        $("#ssq").combobox('loadData',datas);
                        $(".ssq").removeClass('readonly');
                        $("#ssq").combobox('setValue',datas[0]);
                    }
                }
            });
        }else{//非市区，readonly
            $("#ssq").combobox("setValue",{piItemcode:ssqId,piItemname:ssqname});
            $("#ssq").combobox("readonly",true);
            if($.trim(ssjId).length==0){
                select($("#ssq").combobox("getValue"));
            }
        }
        if($.trim(ssjId).length!=0){
            $("#ssjd").combobox('setValue',{piItemcode:ssjId,piItemname:ssjText})
        }
        /*设置短信发送状态查询*/
        $("#sendMes").combobox("loadData",[{piItemcode:0,piItemname:'全部'}
            ,{piItemcode:1,piItemname:'未发送'},{piItemcode:2,piItemname:'已发送'}]);

    });
    function select(record){
        var qid = record.piItemcode;
        if(qid==undefined){
            qid=record;
        }
        $("#ssjd").combobox({
            url:'<%=basePath%>ParmItem/findAllJd',
            onBeforeLoad:function(param){
                param.qid=qid;
            }
        });
        clearMsg();
    }
    //点击类型查询
    $('.houseType .smallType').click(function () {
        $(this).addClass('selectType').siblings().removeClass('selectType');
        var applytype=$.trim($(this).attr("data-id"));
        $("#dataTable").datagrid('load',{
            applyType:applytype,
            applyUsername:$.trim($("#username").textbox('getValue')),
            applyUsercard:$.trim($("#sfzh").textbox('getValue')),
            applyUserSsq:$.trim($("#ssq").combobox('getValue')),
            applyUserJd:$.trim($("#ssjd").combobox('getValue')),
            mesState:$.trim($("#sendMes").combobox('getValue'))
        })
        clearMsg();
    });

    //清空请求
    function clearMsg(){
        showArray=[];
        sendArray=[];
        sendJson.usernames=[];
        sendJson.rooms=[];
        sendJson.htqxs=[];
        sendJson.linktels=[];
        sendJson.sqbhs=[];
        sendJson.sfzhs=[];
        sendJson.applyids=[];
        sendJson.updateLinkTelFlags=[];
        sendJson.updateContraFlags=[];
    }

    /*转换业务类别*/
    function convenrtApplyType(value,row,index){
        var apptype = ""+value;
        switch (apptype){
            case "1":
                return "经济适应住房";
            case "2":
                return "公共租赁住房租赁补贴";
            case "3":
                return "公共租赁住房（低保、特困家庭）";
            case "4":
                return "公共租赁住房（外来务工人员）";
            case "5":
                return "公共租赁住房（新就业人员）";
        }
    }
    //根据条件查询
    function query(){
        var applyType=$.trim($('.selectType').attr("data-id"));
        $("#dataTable").datagrid('load',{
            applyType:applyType,
            applyUsername:$.trim($("#username").val()),
            applyUsercard:$.trim($("#sfzh").val()),
            applyUserSsq:$.trim($("#ssq").combobox('getValue')),
            applyUserJd:$.trim($("#ssjd").combobox('getValue')),
            mesState:$.trim($("#sendMes").combobox('getValue'))
        });
        clearMsg();
    }
    function operate(value,row,index) {
       var apptype = row.APPTYPE;
       var qysj = row.QYSJ;
        var appid = row.APPID;
        if(qysj!=null){
            addConFlag="";
        }else{
            addConFlag="toAdd";
        }

        if(reg.test(row.LINKTEL)){
            updateTelFlag="";
        }else{
            updateTelFlag="toUpdate";
        }

       //var aplid = row.APPID;
       var sqbh = row.APLBH;
       var sfzh = row.SFZH;
        return '<a class="agreen" onclick="inform(\''+apptype+'\',\''+row.USERNAME +'\',\''+row.LINKTEL +'\',\''+row.ROOM +'\',\''+sqbh+'\',\''+sfzh+'\',\''+appid+'\',\''+addConFlag+'\',\''+updateTelFlag+'\',\''+qysj+'\')">通知年审</a>' +
               '<a class="ablue" onclick="view(\''+apptype+'\',\''+appid +'\')">查看</a>';
    }
    //查看
    function view(apptype,aplid){
        var a = $('<a href="<%=basePath %>task/toApprove?applyType='+apptype+'&applyId='+aplid+'&chengxin=chengxin#true" target="_blank"></a>')[0];
        var e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        a.dispatchEvent(e);
    }
   //通知
    function inform(apptype,username,linktel,room,sqbh,sfzh,appid,addConFlag,updateTelFlag,qysj){
        bank.biography().setParams({row:{apptype:apptype,username:username,linktel:linktel,room:room,sqbh:sqbh,sfzh:sfzh,appid:appid,addConFlag:addConFlag,updateTelFlag:updateTelFlag,qysj:qysj},title:"mesInform"});
        bank.showWindow('<%=basePath %>nstask/toMesInform',"通知内容", 500, 400, true);
    }
    /*转换状态*/
    function convertState(value) {
        //0已录入1待保障2异议3取消资格4已保障5保障结束
        switch (value){
            case 0:
                return "已录入";
            case 1:
                return "待保障";
            case 2:
                return "异议";
            case 3:
                return "取消资格";
            case 4:
                return "已保障";
            case 5:
                return "保障结束";
        }
    }

    /*群发短信*/
    function sendAllMessByAll() {

        //return  reg.test($.trim(value))
        var id;
        var content = '';
        $(".message").html('');
        if($('.houseType .smallType').hasClass('selectType') == false){
            bank.ajaxMessage("请选择业务类型");
        }else if(showArray.length == 0){
            bank.ajaxMessage("请选择群发人员");
        }else{
           id = $(' .selectType').attr('data-id');
           if (id == 2) {
                for (var i = 0; i<sendArray.length;i++){
                    if(reg.test(sendArray[i].LINKTEL)){
                        content += '<span class="before" >' + '同志，您好！您所享受的公共租赁住房租赁补贴，合同期限将于' +
                            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
                            '<input class="easyui-datebox sendTime" name="sendTime" style="width: 120px" data-options="label:\'\',required:true,editable:false,validType:[\'overTime\']">' +
                            '<span class="after">' +
                            '到期，请您收到提示短信后，请及时登录徐州市住保系统，办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+'，' +
                            '徐州市住房保障服务中心' +
                             '</span><br/><br/>';
                    }else{
                        content += '<span class="before" >' + '同志，您好！您所享受的公共租赁住房租赁补贴，合同期限将于' +
                            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
                            '<input class="easyui-datebox sendTime" name="sendTime" style="width: 120px" data-options="label:\'\',required:true,editable:false,validType:[\'overTime\']">' +
                            '<span class="after">' +
                            '到期，请您收到提示短信后，请及时登录徐州市住保系统，办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+'，' +
                            '徐州市住房保障服务中心;补充手机号'+  '<input class="easyui-textbox" name="sendTel" style="width: 120px" data-options="label:\'\',required:true,editable:true,validType:[\'phone\']">'  +
                            '</span><br/><br/>';
                    }
                }
               $.parser.parse($(".message").append(content));
               for (var i = 0; i<sendArray.length;i++){
                   if(sendArray[i].QYSJ!=null){
                       $(".sendTime").eq(i).textbox("setValue",sendArray[i].QYSJ.substr(0,10));
                       $(".sendTime").eq(i).textbox().textbox("readonly",true);
                   }
               }
               $("#info").dialog('open');
           }else{
               for (var i = 0; i<sendArray.length;i++){

                       if(reg.test(sendArray[i].LINKTEL)){
                         content += '<span class="before" >' + sendArray[i].USERNAME +
                               '同志，您好！您所承租的公租房'+ sendArray[i].ROOM +
                               '合同期限将于' +
                               '<input class="easyui-datebox sendTime" name="sendTime" style="width: 120px" data-options="label:\'\',required:true,editable:false,validType:[\'overTime\']">' +
                               '<span class="after">' +
                                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
                               '到期，请您收到提示短信后，请及时登录徐州市住保系统，' +
                               '办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+',徐州市住房保障服务中心' +
                                '</span><br/><br/>';
                        }else{
                           content += '<span class="before" >' + sendArray[i].USERNAME +
                               '同志，您好！您所承租的公租房'+ sendArray[i].ROOM +
                               '合同期限将于' +
                               '<input class="easyui-datebox sendTime" name="sendTime" style="width: 120px" data-options="label:\'\',required:true,editable:false,validType:[\'overTime\']">'  +
                               '<span class="after">' +
                               '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
                               '到期，请您收到提示短信后，请及时登录徐州市住保系统，' +
                               '办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+',徐州市住房保障服务中心' +
                               ';补充手机号<input class="easyui-textbox" name="sendTel" style="width: 120px" data-options="label:\'\',required:true,editable:true,validType:[\'phone\']">'+
                               '</span><br/><br/>';
                       }
               }
               $.parser.parse($(".message").append(content));
               for (var i = 0; i<sendArray.length;i++){
                   if(sendArray[i].QYSJ!=null){
                       $(".sendTime").eq(i).textbox("setValue",sendArray[i].QYSJ.substr(0,10));
                       $(".sendTime").eq(i).textbox().textbox("readonly",true);
                   }
               }
               $("#info").dialog('open')
           }
            /*判断是否存在签约时间*/
            for (var i = 0; i<sendArray.length;i++){
                if(sendArray[i].QYSJ!=null){
                    $("#info").find(".before").eq(i).find("input[name='sendTime']").textbox("setValue",sendArray[i].QYSJ.substr(0,10));
                    $("#info").find(".before").eq(i).find("input[name='sendTime']").textbox().textbox("readonly",true);
                }
            }
            flag=true;
        }
    }

    var flag = true;//标记是单个群发还是多个群发
    /*群发短信*/
    function sendAllMessByOne() {
        var id;
       var content = '';
        $(".message").html('');
        if($('.houseType .smallType').hasClass('selectType') == false){
            bank.ajaxMessage("请选择业务类型");
        }else if(showArray.length == 0){
            bank.ajaxMessage("请选择群发人员");
        }else{
            id = $('.selectType').attr('data-id');
            if (id == 2) {
             //   for (var i = 0; i<sendArray.length;i++){
                    content += '<span class="before" >' + '同志，您好！您所享受的公共租赁住房租赁补贴，合同期限将于' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
                        '<input class="easyui-datebox" name="sendTime" style="width: 120px" data-options="label:\'\',required:true,editable:false,validType:[\'overTime\']">' +
                        '<span class="after">' +
                        '到期，请您收到提示短信后，请及时登录徐州市住保系统，办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+'，' +
                        '徐州市住房保障服务中心' +
                        '</span><br/><br/>';
               // }
                $.parser.parse($(".message").append(content));
                $("#info").dialog('open')
            }else{
               // for (var i = 0; i<sendArray.length;i++){
                    content += '<span class="before" >' +
                        '同志，您好！您所承租的公租房' +
                        '合同期限将于' +
                        '<input class="easyui-datebox" name="sendTime" style="width: 120px" data-options="label:\'\',required:true,editable:false,validType:[\'overTime\']">' +
                        '<span class="after">' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
                        '到期，请您收到提示短信后，请及时登录徐州市住保系统，' +
                        '办理年度审核申请业务。谢谢！联系电话:'+"${sessionScope.user.linktel},审核人:"+"${sessionScope.user.username}"+',徐州市住房保障服务中心' +
                        '</span><br/><br/>';
              //  }
                $.parser.parse($(".message").append(content));
                $("#info").dialog('open')
            }
            flag=false;
           // $(".query-btn").attr("onclick","submitByOne");
        }
    }

    function solveMes(){
        var date="";
        for(var i=0;i<sendArray.length;i++){
            if(flag){
                date= $("#info").find(".before").eq(i).find("input[name='sendTime']").val();
            }else{
                date= $("#info").find(".before").eq(0).find("input[name='sendTime']").val();
            }
            (sendJson.usernames)[i]=sendArray[i].USERNAME;
            (sendJson.rooms)[i]=sendArray[i].ROOM;
            (sendJson.linktels)[i]=sendArray[i].LINKTEL;
            sendJson.applyids[i]=sendArray[i].APPID;
            /*如果不存在签约日期，则需要带上申请单id到后台，用于新增合同到期时间到合同表中*/
            if(sendArray[i].QYSJ==null || sendArray[i].QYSJ==''){
                sendJson.updateContraFlags[i]="toAdd";
            }else{
                sendJson.updateContraFlags[i]="no";
            }
            /*如果手机号符号符合规则，则不需更新；否则需要将最新的手机号更新到用户的家庭成员表中*/
            if(flag){
                if(reg.test(sendArray[i].LINKTEL)){
                    (sendJson.updateLinkTelFlags)[i]="no";
                }else{
                    (sendJson.linktels)[i]=$("#info").find(".before").eq(i).find("input[name='sendTel']").val();
                    (sendJson.updateLinkTelFlags)[i]="toUpdate";
                }
            }else{
                (sendJson.updateLinkTelFlags)[i]="no";
            }

            (sendJson.htqxs)[i]=date;
            (sendJson.sqbhs)[i]=sendArray[i].APLBH;
            (sendJson.sfzhs)[i]=sendArray[i].SFZH;

        }
    }
    //回显已选中数据
    function multiShow(data){
        if(showArray.length>0){
            $('#dataTable').datagrid('clearSelections');
            var datas=$("#dataTable").datagrid('getRows');
            for(var i=0;i<datas.length;i++){
                for(var j=0;j<showArray.length;j++){
                    if(datas[i].APPID==showArray[j]){
                        $('#dataTable').datagrid('selectRow',i);
                    }
                }
            }
        }
    }
    //选择失信人员
    $("#dataTable").datagrid({
        onSelect:function(rowIndex, rowData){
            if($.inArray(rowData.APPID,showArray)==-1){
                showArray.push(rowData.APPID);
                sendArray.push(rowData)
            }
        },
        onUnselect:function(rowIndex, rowData){
            $.each(showArray,function(index,value){
                if(rowData.APPID==value){
                    showArray.splice(index, 1);
                    sendArray.splice(index,1);
                }
            });
        },
        onLoadSuccess:function(data){
            //回填选中
            multiShow(data)
        }
    });
    //模态框
    /*批量提交短信数据*/
    function submit(){
        solveMes();
        var applyType=$.trim($('.selectType').attr('data-id'));
        $.post("<%=basePath%>message/sendMessage",
            {   temType:"batch7",
                usernames:sendJson.usernames.toString(),
                htqxs:sendJson.htqxs.toString(),
                linktels:sendJson.linktels.toString(),
                sqbhs:sendJson.sqbhs.toString(),
                rooms:sendJson.rooms.toString(),
                sfzhs:sendJson.sfzhs.toString(),
                updateContraFlags:sendJson.updateContraFlags.toString(),
                applyids:sendJson.applyids.toString(),
                updateLinkTelFlags:sendJson.updateLinkTelFlags.toString(),
                applytype:applyType},
            function (data) {
                bank.ajaxMessage(data);
                if(data.indexOf("成功!=-1")){
                    $("#info").dialog('close');
                    query();
                }
            },"json");
    }


</script>
<style>
    .webuploader-pick {
        background:none !important;
        position: relative;
        top:-10px;
    }
    .datagrid-header-check input{
        display: none;
    }
</style>