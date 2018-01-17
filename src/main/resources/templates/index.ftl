<#include "common/meta.ftl"/>
<title>SNOLF-MASTER</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<#include "common/header.ftl">
<#include "common/menu.ftl">
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="system/welcome.html">我的桌面</span>
					<em></em>
				</li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
		</div>
	</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
	</ul>
</div>
<#include "common/footer.ftl">
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
    $.get("${base}/system/rest/project/listAll?status=0", function(data){
		var proHtml = '';
        $(".project").remove();
        if (data.code == "0000") {
            var proList = data.data;
        	for(var i in proList) {
        		proHtml += '<li class="project"><a href="javascript:;" onclick="checkProject(\'' + proList[i].proKey + '\')"><i class="Hui-iconfont">&#xe67a;</i> ' + proList[i].name + '</a></li>';
			}
			$("#projectList").append(proHtml);
        } else {

        }
    });

	$("body").Huitab({
		tabBar:".navbar-wrapper .navbar-levelone",
		tabCon:".Hui-aside .menu_dropdown",
		className:"current",
		index:0,
	});
});
/*个人信息*/
function myselfInfo(){
    layer_show('查看信息', '${base}/system/user/show.html', '360', '400');
}

function updatePassword() {
    if (!$("#form-member-updatePassword").valid())
        return;
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
    $.post("${base}/system/rest/user/updatePassword", {"oldPassword": oldPassword, "newPassword": newPassword}, function(data){
    	if (data.code == "0000") {
            layer.msg(data.msg, {icon: 6, time: 1000});
            $("#myModal").modal('hide');
		} else {
            layer.msg(data.msg, {icon: 5, time: 1000});
		}
    });
}

function checkProject(proKey) {
    $.get("${base}/system/rest/getSystemMenu?proKey=" + proKey, function(result){
    	if (result.code == "0000") {
    		$(".menu_dropdown").remove();
            var menuJson = result.data;
            new AccordionMenu({menuArrs: menuJson});
        }
    });
}
/**
 * 字段校验
 */
$(function(){
    $("#form-member-updatePassword").validate({
        debug: true,
        rules:{
            oldPassword:{
                required:true,
                isRightfulString:true
            },
            newPassword:{
                required:true,
                isRightfulString:true
            },
            newPassword1:{
                required:true,
                equalTo: "#newPassword"
            }

        },
        messages:{
            loginName:{
                isRightfulString:"只能输入英文字母大小写、数字、下划线"
            },
            newPassword1:{
                equalTo: "两次密码输入不一致"
            }
        },
        onkeyup:false,
        success:"valid",
        unhighlight: function (element, errorClass, validClass) { //验证通过
            $(element).tooltip('destroy').removeClass(errorClass);
        },
        errorPlacement: function (error, element) {
            if ($(element).next("div").hasClass("tooltip")) {
                $(element).attr("data-original-title", $(error).text()).tooltip("show");
            } else {
                $(element).attr("title",
                        $(error).text()).tooltip("show");
            }
        }
    });
});
//字段校验结束

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 

<!--此乃百度统计代码，请自行删除-->
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
<!--/此乃百度统计代码，请自行删除-->
</body>
</html>