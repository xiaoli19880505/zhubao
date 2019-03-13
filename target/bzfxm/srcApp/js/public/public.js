
(function($,app){
	/**
	 * 设置项目路径
	 **/
	app.basePath="http://localhost:8080/bzfxm/";
	/**
	 * alert提示框
	 **/
	app.alert=function(msg){
		 mui.alert(msg);
		 return false;
	};
	/**
	 * 输入提示框
	 **/
	app.confirm=function(msg){
		mui.confirm(msg, '提示',['是','否'], function(e) {
			e.index == 0 ? mui.toast('感谢您的支持!') : mui.toast('没有得到你的认可,继续加油!')
		})
	};
	
	/**
	 * promot提示框
	 **/
	app.prompt=function(msg){
		e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug
		mui.prompt(msg, '满意', '提示',['确定','取消'], function(e) {
			e.index == 1 && e.value != '' && mui.toast('谢谢您的评价: ' + e.value)			
		})
     
	};
	/**
	 * toast提示框
	 **/
	app.toast=function(msg){
		mui.toast(msg);
	};
	/**
	 * 获取应用本地配置
	 **/
	app.settings=function() {
		return {
            set: function (obj) {
                localStorage.setItem(obj.title, JSON.stringify(obj));
            },
            get: function (key) {
                var params=JSON.parse(localStorage.getItem(key));
                return params;
            },
            remove: function(key) {
                localStorage.removeItem(key)
            },
            clear: function() {
                localStorage.clear();
            }
        }
    };
	/**
	 * 打开窗口
	 **/
	app.openWindow=function(id,url){
		 mui.openWindow({
						id:id,
						url:url
				});
	};
	/**
	 * 关闭窗口
	 **/
	app.closeWindow=function(){
		var ws = plus.webview.currentWebview();
         plus.webview.close(ws);
	};
    /**
     * ajax表单提交
     **/
	app.ajaxForm=function(form,url,params,success){
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
                weixin.alert("数据库连接失败，请稍后再试！");
                return false;
            }
        },'json');
    };

    /**
     * ajax提交
     **/
    app.ajax=function(url,datas,success){
        mui.ajax(url,{
            data:datas,
            dataType:'json',//服务器返回json格式数据
            type:'post',//HTTP请求类型
            timeout:10000,//超时时间设置为10秒；
            headers:{'Content-Type':'application/json'},
            success:success,
            error:function(xhr,type,errorThrown){
                weixin.alert("数据库连接失败，请稍后再试！");
                return false;
            }
        });
    };

    /**
     * 获取生日
     **/
    app.setBirthday=function(idCard) {
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
    };

    /**
     * 获取数组标号
     **/
    app.getArrayIndex=function(array,item){
        var i = array.length;
        while (i--) {
            if (array[i] === item) {
                return i;
            }
        }
        return false;
    };
    /**
     * 数组去重
     **/
    app.arraySplice=function(array){
        array.sort();
        var temp = []; //一个新的临时数组
        for(var i = 0; i < array.length; i++){
            if(temp.indexOf(array[i]) == -1){
                temp.push(array[i]);
            }
        }
        var newarray=[];
        newarray= weixin.spliceNumber(temp);
        return newarray;
    };

    /**
     * 字符串变为数字
     **/

    app.spliceNumber=function(array){
        for(var i=0;i<array.length;i++){
            if(typeof (array[i])=="string"){
                array[i]=Number(array[i])
            }
        }
        return array;
    };
    /**
     * 数据格式验证
     **/
    app.typeDetail=function(type){
        switch (type){
            case 0:
                showString="<li class='mui-table-view-cell'>1）有工作单位的，出具单位开具的收入证明（加盖单位公章）以及半年以上的单位发放工资的银行流水单（发放银行盖章）；</li>" +
                            "<li class='mui-table-view-cell'>2）无工作单位的，由所属社区根据实际情况出具收入证明；</li>" +
                            "<li class='mui-table-view-cell'>3）已退休的超过6个月以上的提供6个月社保处发放退休金银行流水单（发放银行盖章），已退休未超过6个月的，提供社保处已发放退休金银行流水单（发放银行盖章）。</li>";
                break;
            case 1:
                showString="<li class='mui-table-view-cell'>1）没有房产的提供申请人所有家庭成员的无房证明[徐州市不动产（住户）查询信息；</li>" +
                            "<li class='mui-table-view-cell'>2）有房产的提供不动产证或土地证和房产证；</li>" +
                            "<li class='mui-table-view-cell'>3）未租公有住房证明。</li>";

                break;
            case 2:
                showString="<li class='mui-table-view-cell'>1）已婚的提供结婚证；</li>" +
                            "<li class='mui-table-view-cell'>2）离异的提供离婚证及离婚判决书；</li>" +
                            "<li class='mui-table-view-cell'>3）单身的提供单身具结书。</li>";
                break;
            case 3:
                showString="<li class='mui-table-view-cell'>1）低保家庭提供低保证（一年以上）；</li>" +
                            "<li class='mui-table-view-cell'>2）特困家庭提供特困证（一年以上）；</li>" +
                            "<li class='mui-table-view-cell'>3）二级以上下肢重度残疾者提供相应残疾证明。</li>";
                break;
            case 4:
                showString="<li class='mui-table-view-cell'>家庭成员不包括父母及已成年子女（年满18周岁）</li>";
                break;
            case 5:
                showString="<li class='mui-table-view-cell'>出具单位开具的收入证明（加盖单位公章）以及半年以上的单位发放工资的银行流水单（发放银行盖章）。</li>";
                break;
            case 6:
                showString="<li class='mui-table-view-cell'>1）没有房产的提供申请人所有家庭成员的无房证明[徐州市不动产（住户）查询信息]；</li>" +
                            "<li class='mui-table-view-cell'>2）未租公有住房证明。</li>";
                break;
            case 7:
                showString="<li class='mui-table-view-cell'>公示期为10天</li>";
                break;
            case 8:
                showString="<li class='mui-table-view-cell'>毕业时长不超过5年</li>";
                break;
            case 9:
                showString="<li class='mui-table-view-cell'>所属单位的营业执照（复印件加盖单位公章）</li>";
                break;
            case 10:
                showString="<li class='mui-table-view-cell'>户口簿包含首页，居住证明为暂住证</li>";
                break;
            case 11:
                showString="<li class='mui-table-view-cell'>出具单位开具的收入证明（加盖单位公章）以及半年以上的单位发放工资的银行流水单（发放银行盖章）。</li>";
                break;
            default :
                break;
        }
        $("#picture ul").html(showString);
    };

    /**
     * cookie使用
     **/
    app.cookExist=function(sfzh){
        var exist=false;
        var sfzh=weixin.getCookie(sfzh);
        if (sfzh!=null && sfzh!=""){
            exist=true;
            // 如果cookie值存在，自动赋值
        }else{
            if (sfzh!=null && sfzh!=""){
                exist=false;
                //如果cookie不存在，设置cookie
                /*weixin.setCookie('sfzh',sfzh,365)*/
            }
        }
        return exist;
    };
    app.setCookie=function(c_name,value,expireseconds){
        var d= new Date();
        d.setHours(d.getHours() + (24 * 30));//一个月过期
        document.cookie=c_name+ "=" +escape(value)+
            ((expireseconds==null) ? "" : ";expires="+d.toGMTString())
        //setCookie('name','zzyn',3600);
    };
    app.getCookie=function(sfzh){
        if (document.cookie.length>0){
            c_start=document.cookie.indexOf(sfzh + "=");
            if (c_start!=-1){
                c_start=c_start + sfzh.length+1;
                c_end=document.cookie.indexOf(";",c_start);
                if (c_end==-1){
                    c_end=document.cookie.length;
                }
                return unescape(document.cookie.substring(c_start,c_end));
            }
        }

        return "";
        //getCookie('name');
    };
	/**
	 * 数据格式验证
	 **/
	app.getNowDate=function(){
        var Y = new Date().getFullYear();
        var M = new Date().getMonth() + 1;
        var D = new Date().getDate();
        return Y + '-' + (M < 10 ? ('0' +M) : M) + '-' + (D< 10 ? ('0' + D) : D);
    };


	app.validType=function(){
            //验证正则
           var reg={
                idCard: /^\d{17}[\d|X|x]$|^\d{15}$/,
                email: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/,
                chinese: /^[\u4e00-\u9fa5]+$/i,
                xyCode: /^[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}$/,
                special: /^[\u4E00-\u9FA5A-Za-z0-9]+$/,
                phone: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
                numberZ: /^\+?[1-9][0-9]*$/,
                money: /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/
            };
            return {
	            	//验证手机
	            isPhone: function(txt) {
	                return reg.phone.test(txt);
	            },
	            //身份证验证
	            isIdCard: function(txt) {
	                return reg.idCard.test(txt);
	            },
	            //验证邮箱
	            isEmail: function(txt) {
	                return reg.email.test(txt);
	            },
	            //验证中文
	            isChinese: function(txt) {
	                return reg.chinese.test(txt);
	            },
	            //统一社会信用代码
	            isXyCode: function(txt) {
	                return reg.xyCode.test(txt);
	            },
	            //特殊字符
	            isSpecial: function(txt) {
	                return reg.special.test(txt);
	            },
	            //正整数
	             isNumberZ: function(txt) {
	                return reg.numberZ.test(txt);
	            },
	            //金额
	            isMoney: function(txt) {
	                return reg.money.test(txt);
	            }
            }
            
        }
}($,window.weixin = {}));
//textbox,验证格式
$.extend($.fn.textbox.defaults.rules, {
    empty:{
        validator : function(value, param) {
            return $.trim(value).length!=0;
        },
        message : "该项不能为空"
    },
    numberZ : {
        validator : function(value, param) {
            var reg = /^\+?[1-9][0-9]*$/;
            return reg.test($.trim(value));
        },
        message : "该项只能输入正整数"
    },

    number : {
        validator : function(value, param) {
            var reg = /^[0-9]+(.[0-9]{1,6})?$/;
            return reg.test($.trim(value));
        },
        message : "该项只能输入数字"
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
    allNumber:{
        validator:function (value,param) {
            var reg= /^.*[^\d].*$/;
            return reg.test($.trim(value));
        },
        message : "不能全为数字"
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
    xyCode:{
        validator: function(value, param){
            var reg = /^[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}$/;
            return reg.test($.trim(value));
        },
        message: '信用代码格式错误'
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
    checkHj:{
        validator: function (value, param) {
            var areaArr=["泉山","泉山区","云龙","云龙区","鼓楼","鼓楼区","经济技术开发区","经济开发区"];
            var city=($.trim(value).indexOf("徐州")==-1)?(false):(true);
            var area=false;
            for(var i=0;i<areaArr.length;i++){
                if($.trim(value).indexOf(areaArr[i])!=-1){
                    area=true;
                }
            }
            var flag=(!city||!area)?(true):(false);
            if(flag) {
                $.fn.textbox.defaults.rules.checkHj.message = '户籍信息必须包含徐州和户籍所在区';
                return false;
            }else{
                return true;
            }
        },
        message: ''
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
            if($.trim(value)<weixin.getNowDate()){
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
            if($.trim(value)>=weixin.getNowDate()){
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
            if(fiveAfter<=weixin.getNowDate()){
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
            if(fiveAfter>weixin.getNowDate()){
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
            if(fiveAfter>weixin.getNowDate()){
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
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    plateNumber:{
        validator : function(value) {
            /*    var reg=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;*/
            return /^[\u4E00-\u9FA5a-zA-Z][A-Z0-9]{6}$/.test(value);
        },
        message:'车牌号格式错误'
    }
})