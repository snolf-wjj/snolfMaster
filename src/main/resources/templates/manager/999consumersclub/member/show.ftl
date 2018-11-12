<#include "../../../common/meta.ftl"/>
<title>个人信息</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<div id="app">
<div class="cl pd-20" style=" background-color:#5bacb6">
    <img class="avatar size-XL l" src="${base}/h-ui/images/ucnter/avatar-default.jpg">
    <dl style="margin-left:80px; color:#fff">
        <dt>
            <span class="f-18" v-cloak>{{user.loginName}}</span>

        </dt>
        <dd class="pt-10 f-12" style="margin-left:0"><span class="pl-10 f-12" v-cloak>昵称：{{user.userName}}</span></dd>
    </dl>
</div>
<div class="pd-20">
    <table class="table">
        <tbody>
        <tr>
            <th class="text-r" width="100">角色：</th>
            <td v-cloak>{{user.roleName}}</td>
        </tr>
        <tr>
            <th class="text-r">本次登录时间：</th>
            <td v-cloak>{{user.loginTime|vTime}}</td>
        </tr>
        <tr>
            <th class="text-r">本次登录IP：</th>
            <td v-cloak>{{user.loginIp}}</td>
        </tr>
        <tr>
            <th class="text-r">上次登录时间：</th>
            <td v-cloak>{{user.lastLoginTime|vTime}}</td>
        </tr>
        <tr>
            <th class="text-r">上次登录IP：</th>
            <td v-cloak>{{user.lastLoginIp}}</td>
        </tr>
        </tbody>
    </table>
</div>
</div>
<#include "../../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    var app = new Vue({
        el: '#app',
        data: {
            user: {
                loginName: '',
                userName: '',
                roleName:'',
                loginTime: '',
                loginIp: '',
                lastLoginTime: '',
                lastLoginIp: ''
            }
        },
        mounted: function() {
            this.queryUser();
        },
        methods: {
            queryUser: function() {
                this.$http.get("${base}/system/rest/user/get?id=${userId}").then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        this.user = data.data;
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }

                });
            }
        },
        filters: {
            vTime: function (value) {
                return formatDate(value, dateFormat.DATE_FORMAT_19H);
            }
        }

    });

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>