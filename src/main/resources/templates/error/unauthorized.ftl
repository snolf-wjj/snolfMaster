<!DOCTYPE html>
<head>
<#include "../common/meta.ftl"/>
<title>unauthorized</title>
<meta text="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta text="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
</head>
<body>
<!--main content start-->
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row ">
        <div class="col-md-12">
            <h2>系统提示</h2>
            <hr>
            <p>被拒绝的请求，你没有权限。请重新登录或者联系管理员！</p>
        </div>
    </div><#--/row-->
    <p id="flash0" class="f-r"><script src="${base}/common/discDrop.js" charset="Shift_JIS"></script></p>
    <p id="flash1" class="f-r"><script src="${base}/common/newtonsCradle.js" charset="Shift_JIS"></script></p>
    <p id="flash2" class="f-r"><script src="${base}/common/pendulumclock.js" charset="Shift_JIS"></script></p>
</div>
<#include "../common/footer.ftl"/>
</body>
<script type="text/javascript">
    var url = "${base}/common/";
    var flashs = new Array();
    flashs[0] = "discDrop.js";
    flashs[1] = "newtonsCradle.js";
    flashs[2] = "pendulumclock.js";
    $(function(){
        var num = Math.floor(Math.random()*3);
//        var html = '<script src="' + url + flashs[i] + '" charset="Shift_JIS"/>';
//        $("#flash").prepend(html);
        for (var i = 0; i <= flashs.length; i++) {
            if (i != num) {
                $("#flash" + i).remove();
            }
        }
    });
</script>
</html>