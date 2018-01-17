/**
 * Created by Administrator on 2017/7/12.
 */
function log_info(info) {
	console.info(info);
}
var dateFormat = {
	DATE_FORMAT_10H: "yyyy-MM-dd",
	DATE_FORMAT_16H: "yyyy-MM-dd HH:mm",
	DATE_FORMAT_19H: "yyyy-MM-dd HH:mm:ss",
	DATE_FORMAT_10X: "yyyy/MM/dd",
	DATE_FORMAT_16X: "yyyy/MM/dd HH:mm",
	DATE_FORMAT_19X: "yyyy/MM/dd HH:mm:ss"
}
function formatDate (value, fmt) {
	if(value == null){
		return "";
	}
	var date = new Date(value);
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
	}
	var o = {
		'M+': date.getMonth() + 1,
		'd+': date.getDate(),
		'H+': date.getHours(),
		'm+': date.getMinutes(),
		's+': date.getSeconds()
	}
	for (var k in o) {
		if (new RegExp('('+k+')').test(fmt)) {
			var str = o[k] + '';
			fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : getzf(str));
		}
	}
	return fmt;
}
//补0操作,当时间数据小于10的时候，给该数据前面加一个0
function getzf(num){
	if(parseInt(num) < 10){
		num = '0'+num;
	}
	return num;
}
/**
 * json转字符串
 * @param jsonData
 */
function jsonToStr(jsonData) {
	return JSON.stringify(jsonData)
}
/**
 * 字符串转json
 * @param strData
 */
function strToJson(strData) {
	return JSON.parse(strData)
}
/**
 * 获取URL的参数
 * @param name
 * @returns {*}
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(r[2]);
	return null;
}

/**
 * 关闭layer,重载父页面
 */
function parentReload() {
	parent.location.reload();
	var index = parent.layer.getFrameIndex(window.text);
	parent.layer.close(index);
}
/**
 * 退出登录
 */
function logout(url){
	$.get(url + "/system/rest/logout", function(result){
		if(result.code == "0000"){
			layer.msg('退出成功！');
			window.location.reload(true);
			return !1;
		}else{
			layer.msg('退出失败，重试！');
		}
	});
}

(function() {
	var OriginTitile = document.title, titleTime;
	document.addEventListener('visibilitychange', function() {
		if (document.hidden) {
			document.title = '死鬼去哪里了！';
			clearTimeout(titleTime);
		} else {
			// document.title = '(つェ⊂)咦!又好了!';
			document.title = '主人，你回来啦!（*^﹏^*）';
			titleTime = setTimeout(function() {
				document.title = OriginTitile;
			},2000);
		}
	});
})();

(function ($) {
	$("form .field-validation-valid,form .field-validation-error")
		.each(function () {
			var tip = $(this);
			var fname = tip.attr("data-valmsg-for");
			var input = $("#" + fname);
			var vgName = "vg" + fname;
			$("<span class='vg' id='" + vgName + "'></div>").insertBefore(input);
			input.appendTo("#" + vgName);
			tip.appendTo("#" + vgName);

		});
})(jQuery);