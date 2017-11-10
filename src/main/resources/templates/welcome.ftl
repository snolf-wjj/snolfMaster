<#include "common/meta.ftl"/>
<title>我的桌面</title>
</head>
<body>
<div id="app" class="page-container">
    <#--<p class="f-r"><script src="http://morfans.cn/app/js/GAY.js" charset="Shift_JIS"></script></p>-->
    <p class="f-r"><script src="${base}/common/hamster.js" charset="Shift_JIS"></script></p>
    <p class="f-r"><script src="${base}/common/peopleClock.js" charset="Shift_JIS"></script></p>
    <p class="f-r"><script src="${base}/common/fish.js" charset="Shift_JIS"></script></p>
	<p class="f-20 text-success">欢迎使用SNOLF-MASTER <span class="f-14">v1.0</span>系统！</p>
	<p>上次登录IP：${lastLoginIp}  上次登录时间：${lastLoginTime}</p>
	<#--<table class="table table-border table-bordered table-bg">-->
		<#--<thead>-->
			<#--<tr>-->
				<#--<th colspan="7" scope="col">信息统计</th>-->
			<#--</tr>-->
			<#--<tr class="text-c">-->
				<#--<th>统计</th>-->
				<#--<th>资讯库</th>-->
				<#--<th>图片库</th>-->
				<#--<th>产品库</th>-->
				<#--<th>用户</th>-->
				<#--<th>管理员</th>-->
			<#--</tr>-->
		<#--</thead>-->
		<#--<tbody>-->
			<#--<tr class="text-c">-->
				<#--<td>总数</td>-->
				<#--<td>92</td>-->
				<#--<td>9</td>-->
				<#--<td>0</td>-->
				<#--<td>8</td>-->
				<#--<td>20</td>-->
			<#--</tr>-->
			<#--<tr class="text-c">-->
				<#--<td>今日</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
			<#--</tr>-->
			<#--<tr class="text-c">-->
				<#--<td>昨日</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
			<#--</tr>-->
			<#--<tr class="text-c">-->
				<#--<td>本周</td>-->
				<#--<td>2</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
			<#--</tr>-->
			<#--<tr class="text-c">-->
				<#--<td>本月</td>-->
				<#--<td>2</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
				<#--<td>0</td>-->
			<#--</tr>-->
		<#--</tbody>-->
	<#--</table>-->
	<table class="table table-border table-bordered table-bg mt-20">
		<thead>
			<tr>
				<th colspan="2" scope="col">服务器信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="30%">服务器计算机名</td>
				<td>{{systemInfo.os_name}}</td>
			</tr>
			<tr>
				<td>服务器IP地址</td>
				<td>{{systemInfo.os_ip}}</td>
			</tr>
			<tr>
				<td>服务器架构</td>
				<td>{{systemInfo.os_arch}}</td>
			</tr>
			<tr>
				<td>服务器版本号</td>
				<td>{{systemInfo.os_version}}</td>
			</tr>
			<tr>
				<td>服务器当前时间</td>
				<td>{{systemInfo.os_date}}</td>
			</tr>
			<tr>
				<td>系统CPU个数</td>
				<td>{{systemInfo.os_cpus}}</td>
			</tr>
			<tr>
				<td>系统用户名</td>
				<td>{{systemInfo.os_user_name}}</td>
			</tr>
			<tr>
				<td>Java的版本号</td>
				<td>{{systemInfo.java_version}}</td>
			</tr>
			<tr>
				<td>Java 平台</td>
				<td>{{systemInfo.sun_desktop}}</td>
			</tr>
			<tr>
				<td>服务器名</td>
				<td>{{systemInfo.server_name}}</td>
			</tr>
			<tr>
				<td>服务器端口</td>
				<td>{{systemInfo.server_port}}</td>
			</tr>
			<tr>
				<td>服务器地址</td>
				<td>{{systemInfo.server_addr}}</td>
			</tr>
			<tr>
				<td>客户端地址</td>
				<td>{{systemInfo.server_host}}</td>
			</tr>
            <tr>
                <td>服务协议</td>
                <td>{{systemInfo.server_protocol}}</td>
            </tr>
		</tbody>
	</table>
</div>
<footer class="footer mt-20">
	<div class="container">
		<p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>
			Copyright &copy;2015-2017 H-ui.admin v3.0 All Rights Reserved.<br>
			本后台系统由<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持</p>
	</div>
</footer>
<#include "../../common/footer.ftl"/>
<script type="text/javascript" src="${base}/lib/laypage/1.3/laypage.js"></script>
<!--此乃百度统计代码，请自行删除-->
<script>
    var app = new Vue({
        el: '#app',
        data: {
            systemInfo: {
                os_name: '',
                os_ip: '',
                os_arch: '',
                os_version: '',
                os_mac: '',
                os_date: '',
                os_cpus: '',
                os_user_name: '',
                java_version: '',
                sun_desktop: '',
                server_name: '',
                server_port: '',
                server_addr: '',
                server_host: '',
                server_protocol: ''
            }
        },
        mounted: function() {
			this.getSystemInfo();
        },
        methods: {
            getSystemInfo: function() {
                this.$http.get("${base}/system/rest/getSystemInfo").then(function(response) {
                    var data = response.data;

                    if ("0000" == data.code) {
                    	this.systemInfo = data.data;
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }

                });
            }
        }

    });

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