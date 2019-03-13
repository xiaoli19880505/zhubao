//导航效果
function mainmenuScroll() {
	//alert("执行了啦");
	//return;
	try {
		var b = 0;
		var t1 = 58;
		if($("#menu_container_id").length < 1) {
			return;
		}
		var c = $("#menu_container_id").offset();
		var bodyH = $("body").height();
		//alert(t);
		//alert("c.top="+c.top);
		$(window).scroll(function() {
			var a = $(window).scrollTop();
			if((a > c.top - b)) {
				$("#menu_container_id").addClass("menu_fixed");
				$("#menu_container_id").css("top", "0px");
			} else {
				$("#menu_container_id").css("top", "0px");
				$("#menu_container_id").removeClass("menu_fixed");
			}

		});
	} catch(e) {
		alert(e.message);
	}
}

//搜索框
function JTrim(s) {
	return s.replace(/(^\s*)|(\s*$)/g, "");
}

function enterSearch(event) {
	if(event.keyCode == 13) {
		searchPro();
		return false;
	}
}

function searchPro() {
	var keys = JTrim(document.getElementById("keywords").value);
	if(keys == undefined || keys == "" || keys == null || keys == "请输入关键字") {
		alert("请输入关键字！");
		return false;
	} else {
		location.href = "news.html?keywords=" + keys + "";
		window.event.returnValue = false;
	}
}
//返回顶部
$(function() {
	mainmenuScroll();
})