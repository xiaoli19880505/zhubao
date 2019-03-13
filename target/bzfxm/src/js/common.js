﻿/**
 * BANK
 *
 */

$(window).load(function () {
    resizeAll();
    //清除localsotage
    bank.clearStorage();
});
$(window).resize(function () {
    resizeAll();
}).resize();

function resizeAll() {
    //顶部自适应
    $('#publicNorth').panel('resize',{height:'auto'});
}
(function (window, document, $) {
    window.bank = {
        clickMenu:function(basePath){
                $("#westTree").tree({
                    onClick: function (node) {
                       if (node.meUrl!=undefined) {
                            var title = node.text;
                            var href = node.meUrl;
                            if (!$('#AllTabs').tabs('exists', title)) {
                                $('#AllTabs').tabs('add', {
                                    title: title,
                                    content: '<iframe class="iframe" scrolling="auto" frameborder="0"  src='+basePath+href + '></iframe>',
                                    closable: true
                                });
                            } else {
                                $('#AllTabs').tabs('select', title);
                                var current_tab = $('#AllTabs').tabs('getSelected');
                                $('#AllTabs').tabs('update', {
                                    tab: current_tab,
                                    options: {
                                        content: '<iframe class="iframe" scrolling="auto" frameborder="0"  src='+basePath+ href + '></iframe>'
                                    }
                                });
                            }
                        }
                        else {
                            $(node.target).parent().siblings().find("ul").hide();
                            $(node.target).siblings().toggle();
                            $(node.target).parent().siblings().find("a").addClass("accordion-expand");
                            $(node.target).parent().siblings().find("a").removeClass("accordion-collapse");
                            if ($(node.target).find("a").attr("class") == "accordion-expand") {
                                $(node.target).find("a").removeClass("accordion-expand");
                                $(node.target).find("a").addClass("accordion-collapse");
                            } else {
                                $(node.target).find("a").addClass("accordion-expand");
                                $(node.target).find("a").removeClass("accordion-collapse");
                            }
                        }
                    }, onLoadSuccess: function (node,data) {
                        var childrens = $('#westTree').tree('getChildren');
                        $.each(childrens, function (k, node) {
                            if(node.children.length>0){
                                $('#'+node.domId+'').prepend('<a href="javascript:;" class="accordion-expand"></a>');
                            }
                        });
                    }
                });
        },
        closeTab: function (menu, type) {//tab右击操作
            var allTabs = $("#AllTabs").tabs('tabs');
            var allTabtitle = [];
            $.each(allTabs, function (i, n) {
                var opt = $(n).panel('options');
                if (opt.closable)
                    allTabtitle.push(opt.title);
            });
            var curTabTitle = $(menu).data("tabTitle");
            var curTabIndex = $("#AllTabs").tabs("getTabIndex", $("#AllTabs").tabs("getTab", curTabTitle));
            switch (type) {
                case 1://关闭当前
                    $("#AllTabs").tabs("close", curTabIndex);
                    return false;
                    break;
                case 2://全部关闭
                    for (var i = 0; i < allTabtitle.length; i++) {
                        $('#AllTabs').tabs('close', allTabtitle[i]);
                    }
                    break;
                case 3://除此之外全部关闭
                    for (var i = 0; i < allTabtitle.length; i++) {
                        if (curTabTitle != allTabtitle[i])
                            $('#AllTabs').tabs('close', allTabtitle[i]);
                    }
                    $('#AllTabs').tabs('select', curTabTitle);
                    break;
                case 4://当前侧面右边
                    for (var i = curTabIndex; i < allTabtitle.length; i++) {
                        $('#AllTabs').tabs('close', allTabtitle[i]);
                    }
                    $('#AllTabs').tabs('select', curTabTitle);
                    break;
                case 5: //当前侧面左边
                    for (var i = 0; i < curTabIndex - 1; i++) {
                        $('#AllTabs').tabs('close', allTabtitle[i]);
                    }
                    $('#AllTabs').tabs('select', curTabTitle);
                    break;
            }

        },
        openWindow: function (url, parameters) {//打开新窗口
            if (url) {
                window.open(url, "_blank");
            } else {
                var url = window.location.href;
                if (url.indexOf("#true") != -1) {
                    url = url.substring(0, url.length - 5);
                }
                window.open(url + '#true', "_blank", parameters);
            }
        },
        closeCurrent: function () {//关闭当前,关闭新窗口
            var hash = window.location.hash;
            if (hash) {
                window.opener = null;
                window.open('', '_self');
                $.messager.confirm('操作提示', '您确定关闭该窗口吗？', function (r) {
                    if (r) {
                        window.close();
                    }
                });
            } else {
                var current = parent.$('#AllTabs').tabs('getTabIndex', parent.$('#AllTabs').tabs('getSelected'));
                parent.$("#AllTabs").tabs("close", current);
            }
        },
        showWindow: function (url, title, width, height, shadow,reload) {//打开模态框
            var content = '<iframe src="' + url + '" class="iframe" scrolling="auto" frameborder="0" ></iframe>';
            var window = '<div class="bankWindow" title="' + title + '" data-attr="'+reload+'"></div>';
            $(document.body).append(window);
            var win = $('.bankWindow').dialog({
                content: content,
                width: width,
                height: height,
                modal: shadow,
                cache: true,
                title: title,
                onClose: function () {
                    var reload=$('.bankWindow').attr("data-attr");
                    if(reload=="reload"){
                        bank.tbReload('dataTable');
                    }
                    $(this).dialog('destroy');//后面可以关闭后的事件

                }
            });
            win.dialog('open');
        },
        hideWindow: function () {//关闭模态框
            var hash = window.location.hash;
            if (hash) {
                $.messager.confirm('操作提示', '您确定关闭该窗口吗？', function (r) {
                    if (r) {
                        window.opener = null;
                        window.open('', '_self');
                        window.close();
                    }
                });
            } else {
                $.messager.confirm('操作提示', '您确定关闭该窗口吗？', function (r) {
                    if (r) {
                        parent.$('.bankWindow').dialog("close");
                    }
                });
            }
        },
        tbReload: function (tableID) {//刷新表
            $('#' + tableID + '').datagrid('load',{});
        },
        ajaxMessage: function (content) {//提示框
            $.messager.show({
                showType: 'null', timeout: 1000, msg: content, title: '操作提示',
                style: {
                    left: document.documentElement.offsetWidth / 2 - 125,
                    top: document.documentElement.offsetTop + 100
                }
            });
        },
        alertMessage:function(content,icon){
            icon=(icon)?icon:"warning";
            $.messager.alert('操作提示',content,icon);
            return false;
        },
        ajaxForm:function(form,url,params,success){
            $(form).form('submit', {
                url:url,
                onSubmit: function(param){
                    if(params){
                        for(var key in params){
                            param[key]=params[key]
                        }
                    }
                    var isValid = $(this).form('validate');
                    if (!isValid){
                    }
                    return isValid;
                },
                success:success,
                error:function(){
                    bank.ajaxMessage("数据库连接失败，请稍后再试！");
                    return false;
                }
            },'json');
        },
        queryTable: function (tableID,fieldARR) {//根据字段查询，显示表单
                var data = {};
                $.each(fieldARR, function (index, val) {
                    data[val] = $.trim($('#' + val + '').val());
                });
                $('#' + tableID + '').datagrid('load',data);
        },
        serializeForm:function(form){
            var data = {};
            $.each(form.serializeArray(), function (index) {
                if (data[this['name']]) {
                    data[this['name']] = data[this['name']] + "," + this['value'];
                } else {
                    data[this['name']] = this['value'];
                }
            });
            return data;
        },
        clearForm: function (formID,type) {//清空搜索框
            var type=type?type:'clear';
            $('#' + formID + '').form(type);
        },
        fileExport: function (url,param) {//导出
            if(param){
                window.location.href=""+url+"?"+param+"="+param;
            }else{
                window.location.href=url
            }
        },
        fileDownload: function (url,param) {//文件下载
            if(param){
                window.location.href=""+url+"?"+param+"="+param;
            }else{
                window.location.href=url
            }
        },
        overHeight: function (ID, height) {
            while ($('#' + ID + '')[0].offsetHeight > 100) {//字段多行溢出
                var text = $('#' + ID + '')[0].innerText.replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "...");
                $('#' + ID + '')[0].html(text);
            }
        },
        biography: function () {
            return {
                setParams: function (obj) {
                    localStorage.setItem(obj.title, JSON.stringify(obj));
                },
                getParams: function (title) {
                    var params=JSON.parse(localStorage.getItem(title));
                    return params;
                }
            };
        },
        getParameter : function(key) {//获取url参数，并键值显示
            var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                var x = unescape(r[2]);
                return decodeURI(x);
            } else {
                return null;
            }
        },
        getArrayIndex:function(array,item){
                var i = array.length;
                while (i--) {
                    if (array[i] === item) {
                        return i;
                    }
                }
                return false;
        },
        arraySplice:function(array){
            array.sort();
            var temp = []; //一个新的临时数组
            for(var i = 0; i < array.length; i++){
                if(temp.indexOf(array[i]) == -1){
                    temp.push(array[i]);
                }
            }
            return temp;
        },
        nodeLevel:function(node,tree){
            var length = 0;
            while (node != null) {
                node = $(tree).tree('getParent', node.target);
                length++;
            }
            return length;
        },
        treeLevel:function(data) {
            if(typeof(data)=="object"){
                data=JSON.stringify(data)
            }
            var count=0,length=0,array = {'{': 1, '}': -1};
            for ( var i=0;i<data.length;i++ ) {
                var result = array[data.charAt(i)];
                if (!result) continue;
                count += result;
                if (count > length) {
                    length = count;
                }
            }
            if (count != 0) {
                return false
            }
            return length;
    },
        timeFormat:function (date) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(date)) date = date.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(date)) date = date.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return date;
        },
        getNowDate:function(){
            var Y = new Date().getFullYear();
            var M = new Date().getMonth() + 1;
            var D = new Date().getDate();
            return Y + '-' + (M < 10 ? ('0' +M) : M) + '-' + (D< 10 ? ('0' + D) : D);
        },
        getVersion:function(){
            var Sys = {};
            var ua = navigator.userAgent.toLowerCase();
            var re =/(msie ([\d.]+)|firefox\/([\d.]+)|chrome\/([\d.]+)|opera.([\d.]+)|version\/([\d.]+).*safari)/;
            var m = ua.match(re);
            if(m==null){
                Sys.browser ="msie 11.0";
            }else{
                Sys.browser =(m[1])? m[1]:0;
            }
            return Sys;
        },
        clearStorage:function(){
            if(!window.localStorage){
                return false;
            }else{
                var size = 0;
                for(item in window.localStorage) {
                    if(window.localStorage.hasOwnProperty(item)) {
                        size += window.localStorage.getItem(item).length;
                    }
                }
                var maxSize=(size/1024).toFixed(2);
                //判断浏览器类型
                if(bank.getVersion().browser.indexOf("msie")>-1){//ie浏览器
                    if(bank.getVersion().browser.indexOf(10)){
                        if(maxSize>=1630){//ie10以上
                            localStorage.clear();
                        }
                    }else{
                        if(maxSize>=1320){//ie10以下
                            localStorage.clear();
                        }
                    }
                }else{
                    if(maxSize>=5120){//谷歌，火狐
                        localStorage.clear();
                    }
                }
            }
        },
        loading:function(targetID,msg){
            var targetID=targetID?('#'+targetID+''):('body');
            var msg=msg?msg:"正在处理，请稍候....";
            $("<div class=\"load-mask\"></div>").css({
                display:"block",
                width:"100%",
                height:$(window).height()
            }).appendTo(targetID);
            $("<div class=\"load-mask-msg\"></div>").html(msg).appendTo(targetID).css({
                display:"block",
                left:($(targetID).outerWidth(true) - 190) / 2,
                top:($(targetID).height()-50)/2
            });
        },
        loaded:function(){
            $(".load-mask").remove();
            $(".load-mask-msg").remove();
        },
        supportTransition:function(){
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                    'WebkitTransition' in s ||
                    'MozTransition' in s ||
                    'msTransition' in s ||
                    'OTransition' in s;
            s = null;
            return r;
        },
        setBirthday:function(idCard) {
            var birthday = "";
            if(idCard != null && idCard != ""){
                if(idCard.length == 15){
                    birthday = "19"+idCard.substr(6,6);
                } else if(idCard.length == 18){
                    birthday = idCard.substr(6,8);
                }
                birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
            }
            return birthday;
        },
        viewAttach:function(type,url, title, width, height, shadow,reload){
            bank.biography().setParams({row:type,title:"attachType"});
            bank.showWindow(url, title, width, height, shadow,reload);
        }
    };
})(window, document, $);

//textbox,验证格式
$.extend($.fn.textbox.defaults.rules, {
    empty:{
        validator : function(value, param) {
            return $.trim(value).length!=0;
        },
        message : "该项不能为空"
    },
    number : {
        validator : function(value, param) {
            var reg = /^\+?[1-9][0-9]*$/;
            return reg.test($.trim(value));
        },
        message : "该项只能输入正整数"
    },
    allNumber:{
        validator:function (value,param) {
            var reg= /^.*[^\d].*$/;
            return reg.test($.trim(value));
        },
        message : "不能全为数字"
    },
    canNumber:{
        validator:function (value,param) {
            var reg= /^(0|[1-9][0-9]*)$/  ;
            var reg1= /^(0[1-9][0-9]*)$/;
            return reg.test($.trim(value))||reg1.test($.trim(value));
        },
        message : "只能输入数字"
    },
    money:{
        validator:function(value,param){
            var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;//金额验证
            return  reg.test($.trim(value));
        },
        message :"金额必须大于0，且最多含有2位小数!"
    },
    email:{
        validator:function (value,param){
            var reg=/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
            return  reg.test($.trim(value));
        },
        message :"邮箱格式错误！"
    },
    phone:{
        validator:function (value,param) {
            var reg=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            return  reg.test($.trim(value))
        },
        message :"手机号格式错误！"
    },
    tel:{
        validator:function (value,param) {
            var reg= /^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;//固话验证
            return  reg.test($.trim(value));
        },
        message:"固话格式格式错误！"
    },
    phoneTel:{
        validator:function (value,param) {
            var reg1= /^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;//固话验证*/
            var reg2=/^(\\+\\d{2}-)?0\\d{2,3}-\\d{7,8}$/;//国际
            var reg3=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;//手机号验证
            var reg4= /^\d{3,4}-?\d{7,9}$/;
            if(reg1.test($.trim(value))||reg2.test($.trim(value))||reg3.test($.trim(value))||reg4.test($.trim(value))){
                return true
            }else{
                return false;
            }
        },
        message:"请输入正确的带区号固话或手机号"
    },
    specialCharacter: {
        validator: function(value, param){
            var reg = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
            return reg.test($.trim(value));
        },
        message: '该项不能含有特殊字符'
    },
    checkLength: {
        validator: function (value, param) {
            if (($.trim(value)).length < param[0] || ($.trim(value)).length > param[1]) {
                $.fn.textbox.defaults.rules.checkLength.message = '该字段长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            }else{
                return true;
            }
        },
        message: ''
    },
    sfz : {
        validator : function(value, param) {
            var reg = /(^\d{15}$)|(^\d{17}(\d|X|x)$)/;
            return reg.test($.trim(value));
        },
        message : "身份证格式不正确"
    },
    limitHJ:{
        validator: function (value, param) {
            if (($.trim(value))<=param[0]) {
                $.fn.textbox.defaults.rules.limitHJ.message = '该字段值不能小于' + param[0]+'';
                return false;
            }else{
                return true;
            }
        },
        message: ''
    },
    sfzh:{
        validator: function (value, param) {
            if (($.trim(value)).length!=param[0]&&($.trim(value)).length!=param[1]) {
                $.fn.textbox.defaults.rules.sfzh.message = '该字段长度只能为' + param[0] + '或' + param[1] + '';
                return false;
            }else{
                return true;
            }
        },
        message: ''
    },
    xyCode:{
        validator: function(value, param){
            var reg = /^[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}$/;
            return reg.test($.trim(value));
        },
        message: '信用代码格式错误'
    },
    equalTo: {
        validator:function(value,param){
            return $.trim($(param[0]).val()) == $.trim(value);
        },
        message:'两次密码不一致!'
    },
    chinese : {
        validator : function(value, param) {
            var reg = /^[\u4e00-\u9fa5]+$/i;
            return reg.test($.trim(value));
        },
        message : "请输入中文"
    },
    englishLowerCase  : {// 验证英语小写
        validator : function(value) {
            var reg=/^[a-z]+$/;
            return reg.test($.trim(value));
        },
        message : '请输入小写字母'
    },
    isDateBox:{
        validator:function () {
            var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
            return reg.test($.trim(value));
        },
        message:"日期格式错误"
    },
    overDate:{
        validator:function (value,param) {
            if($.trim(value)<bank.getNowDate()){
                $.fn.textbox.defaults.rules.overDate.message = '选择的时间不能小于当前时间';
                return false;
            }else{
                return true
            }
        },
        message:''
    },
    minDate:{
        validator:function (value,param) {
            if($.trim(value)>=bank.getNowDate()){
                $.fn.textbox.defaults.rules.minDate.message = '选择的时间不能大于当前时间';
                return false;
            }else{
                return true
            }
        },
        message:''
    },
    fiveAfter:{
        validator:function (value,param) {
            var nian=Number(value.substring(0,4))+5;
            var yue=value.substring(4);
            var fiveAfter=nian+yue;
            if(fiveAfter<=bank.getNowDate()){
                $.fn.textbox.defaults.rules.fiveAfter.message = '毕业年限超过5年不得申请';
                return false;
            }else{
                return true
            }
        },
        message:''
    },
    annual:{
        validator:function (value,param) {
            var nian=Number(value.substring(0,4))+1;
            var yue=value.substring(4);
            var fiveAfter=nian+yue;
            if(fiveAfter>bank.getNowDate()){
                $.fn.textbox.defaults.rules.annual.message = '缴纳社保或公积金必须满一年';
                return false;
            }else{
                return true
            }
        },
        message:''
    },
    dbAnnual:{
        validator:function (value,param) {
            var nian=Number(value.substring(0,4))+1;
            var yue=value.substring(4);
            var fiveAfter=nian+yue;
            if(fiveAfter>bank.getNowDate()){
                $.fn.textbox.defaults.rules.dbAnnual.message = '享受低保特困必须满一年';
                return false;
            }else{
                return true
            }
        },
        message:''
    },
    ZIP: {
        validator: function (value, param) {
            return /^[1-9][0-9]{5}$/.test(value);
        },
        message: '邮政编码不存在'
    },
    LXDH: {
        validator: function (value, param) {
            return /^\d{6,12}$/.test(value);
        },
        message: '联系电话不正确'
    },
    plateNumber:{
        validator : function(value) {
            /*    var reg=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;*/
            return /^[\u4E00-\u9FA5a-zA-Z][A-Z0-9]{6}$/.test(value);
        },
        message:'车牌号格式错误'
    }
});
$(function(){
    //tabs右击事件
    $('#AllTabs').tabs({
        onContextMenu: function (e, title, index) {
            e.preventDefault();
            if (index > 0) {
                $('#menu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        }
    });
    $("#menu").menu({
        onClick: function (item) {
            bank.closeTab(this, item.name);
        }
    });
});


