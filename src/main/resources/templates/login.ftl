<#include "common/meta.ftl"/>
<link href="${base}/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    input[type=reset] {
        margin-left: 10px;
    }

</style>
<title>后台登录  SNOLF-MASTER v1.0</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<input type="hidden" id="TenantId" text="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form id="form" class="form form-horizontal">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="loginName" name="loginName" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <#--<div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div>-->
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" text="online" id="online" value="">
            使我保持登录状态</label>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="button" onclick="login()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 逐梦 by SNOLF-MASTER v1.0</div>
<#include "common/footer.ftl"/>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<!--此乃百度统计代码，请自行删除-->
<script type="application/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script type="application/javascript">
    function login() {
        if (!$("#form").valid())
            return;
        $.post("${base}/user/login", $("#form").serializeArray(), function (data, status) {
        	if ("0000" == data.code) {
                layer.msg("登录成功", {icon: 6, time: 1000});
                window.location.href = "index";
            } else {
                layer.msg(data.msg, {icon: 5, time: 1000});
            }
        });
    }

    $(function(){
        $("#form").validate({
            debug: true,
            rules:{
                loginName:{
                    required:true,
                    isRightfulString:true
                },
                password:{
                    required:true,
                }

            },
            messages:{
                loginName:{
                    isRightfulString:"只能输入英文字母大小写、数字、下划线"
                }
            },
            onkeyup:false,
            success:"valid"
        });
    });


</script>
<!-- 此乃百度统计代码，请自行删除 -->
</body>
</html>