<#include "../../common/base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>同学录登录页</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!--图标样式-->
	<link rel="stylesheet" type="text/css" href="${base}/third/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />

	<!--布局框架-->
	<link rel="stylesheet" type="text/css" href="${base}/third/css/util.css" />

	<!--主要样式-->
	<link rel="stylesheet" type="text/css" href="${base}/third/css/main.css"/>

</head>

<body>

<div class="limiter">
	<div class="container-login100" style="background-image: url('${base}/third/images/img-01.jpg');">
		<div class="wrap-login100 p-t-190 p-b-30">
			<form id="form" class="login100-form validate-form">
				<div class="login100-form-avatar">
					<img src="${base}/third/images/group.png" alt="AVATAR">
				</div>

				<span class="login100-form-title p-t-20 p-b-45">Hello</span>

				<div class="wrap-input100 validate-input m-b-10" data-validate="请输入用户名">
					<input class="input100" type="text" name="loginName" placeholder="用户名" autocomplete="off">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-user"></i>
                        </span>
				</div>

				<div class="wrap-input100 validate-input m-b-10" data-validate="请输入密码">
					<input class="input100" type="password" name="password" placeholder="密码">
					<span classsubmit="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-lock"></i>
                        </span>
				</div>

				<div class="container-login100-form-btn p-t-10">
					<button id="login" type="button" class="login100-form-btn">登 录</button>
				</div>

				<#--<div class="text-center w-full p-t-25 p-b-230">
					<a href="#" class="txt1">忘记密码？</a>
				</div>-->

				<div class="text-center w-full">
					</br>
					<a class="txt1" href="register.html">
						立即注册
						<i class="fa fa-long-arrow-right"></i>
					</a>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="${base}/third/js/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${base}/third/js/main.js"></script>
<script type="application/javascript">
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "https://hm.baidu.com/hm.js?b09dd5f206837aaed854870cd963b34b";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
</body>
<script type="text/javascript" src="${base}/lib/layer/2.4/layer.js"></script>
</html>
