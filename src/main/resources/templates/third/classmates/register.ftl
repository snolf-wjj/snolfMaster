<#include "../../common/base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>同学录用户注册</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!--图标样式-->
	<link rel="stylesheet" type="text/css" href="${base}/third/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

	<!--布局框架-->
	<link rel="stylesheet" type="text/css" href="${base}/third/css/util.css">

	<!--主要样式-->
	<link rel="stylesheet" type="text/css" href="${base}/third/css/main.css">

</head>

<body>
	<div class="limiter">
		<div class="container-login100" style="background-image: url('${base}/third/images/img-01.jpg');">
			<div class="wrap-login100 p-t-190 p-b-30">
				<form id="form" class="form login100-form validate-form">
					<div class="login100-form-avatar">
						<img src="${base}/third/images/group.png" alt="AVATAR">
					</div>

					<span class="login100-form-title p-t-20 p-b-45">Register</span>

					<div class="wrap-input100 validate-input m-b-10" data-validate="请输入用户名，只能是字母和数字">
						<input class="input100" type="text" name="loginName" placeholder="用户名" autocomplete="off">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
								<i class="fa fa-user"></i>
							</span>
					</div>

					<div class="wrap-input100 validate-input m-b-10" data-validate="请输入密码">
						<input class="input100" type="password" name="password" placeholder="密码">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
								<i class="fa fa-lock"></i>
							</span>
					</div>
					<div class="wrap-input100 validate-input m-b-10" data-validate="请输入姓名">
						<input class="input100" type="text" name="nickName" placeholder="姓名">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
								<i class="fa fa-file-text-o"></i>
							</span>
					</div>
					<div class="wrap-input100 validate-input m-b-10" data-validate="请输入手机号">
						<input class="input100" type="phone" name="phone" placeholder="联系方式">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
								<i class="fa fa-file-text-o"></i>
							</span>
					</div>

					<div class="container-login100-form-btn p-t-10">
						<button id="register" type="button" class="login100-form-btn">注册</button>
					</div>

					<div class="text-center w-full">
						<a class="txt1" href="index.html">
							立即登录
							<i class="fa fa-long-arrow-right"></i>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${base}/third/js/jquery/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${base}/third/js/main.js"></script>
	<script type="text/javascript" src="${base}/lib/layer/2.4/layer.js"></script>
</body>
</html>
