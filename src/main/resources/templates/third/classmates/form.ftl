<#include "../../common/base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>填写个人信息</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!--图标样式-->
	<link rel="stylesheet" type="text/css"
		  href="${base}/third/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>

	<!--布局框架-->
	<link rel="stylesheet" type="text/css" href="${base}/third/css/util.css"/>

	<!--主要样式-->
	<link rel="stylesheet" type="text/css" href="${base}/third/css/main.css"/>
</head>

<body>

<div class="limiter">
	<div class="container-login100" style="background-image: url('static/third/images/img-01.jpg');">
		<div class="wrap-login100 p-t-190 p-b-30">
			<form id="form" class="login100-form validate-form">
				<div class="login100-form-avatar">
					<img src="${base}/third/images/avatar-01.jpg" alt="AVATAR">
				</div>

				<span class="login100-form-title p-t-20 p-b-45">填写个人信息</span>

				<div class="wrap-input100 validate-input m-b-10" data-validate="姓名">
					<input class="input100" type="text" name="userName" placeholder="姓名" autocomplete="off">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-user"></i>
                        </span>
				</div>

				<div class="wrap-input100 validate-input m-b-10" data-validate="联系电话">
					<input class="input100" type="text" name="phone" placeholder="联系电话">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-mobile-phone"></i>
                        </span>
				</div>
				<div class="wrap-input100 validate-input m-b-10" data-validate="所在地区">
					<input class="input100" type="text" name="address" placeholder="所在地区">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-location-arrow"></i>
                        </span>
				</div>
				<div class="wrap-input100 validate-input m-b-10" data-validate="公司名称">
					<input class="input100" type="text" name="companyName" placeholder="公司名称">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-copyright"></i>
                        </span>
				</div>
				<div class="wrap-input100 validate-input m-b-10" data-validate="从事行业">
					<input class="input100" type="text" name="industryType" placeholder="从事行业">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
                            <i class="fa fa-file-text-o"></i>
                        </span>
				</div>
				<div class="wrap-input100 validate-input m-b-10" data-validate="QQ号">
					<input class="input100" type="text" name="qqNumber" placeholder="QQ号">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-qq"></i>
					</span>
				</div>
				<div class="wrap-input100 validate-input m-b-10" data-validate="微信号">
					<input class="input100" type="text" name="weixinNumber" placeholder="微信号">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-weixin"></i>
					</span>
				</div>

				<div class="container-login100-form-btn p-t-10">
					<button id="formData" type="button" class="login100-form-btn">提交</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${base}/third/js/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${base}/third/js/main.js"></script>
<script type="application/javascript">

</script>
<script type="text/javascript" src="${base}/lib/layer/2.4/layer.js"></script>
</html>
