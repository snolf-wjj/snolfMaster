<#include "../../common/meta.ftl"/>
<title>添加用户</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
    <form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="loginName" name="loginName" v-model="user.loginName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" value="" placeholder="" id="password" name="password" v-model="user.password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">昵称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="userName" name="userName" v-model="user.userName">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button class="btn btn-primary radius" type="button" @click="submit">提交</button>
            </div>
        </div>
    </form>
</article>
<#include "../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    var app = new Vue({
        el: '#app',
        data: {
            user: {
                loginName: '',
                password: '',
                userName: ''
            }
        },
        mounted: function() {

        },
        methods: {
            submit: function() {
                if (!$("#form-member-add").valid())
                    return;
                this.$http.post("${base}/system/rest/user/add", this.user, {emulateJSON:true}).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout("parentReload()", 500);
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }

                });
            }
        }

    });
    /**
     * 字段校验
     */
    $(function(){
        $("#form-member-add").validate({
            debug: true,
            rules:{
                loginName:{
                    required:true,
                    isRightfulString:true
                },
                password:{
                    required:true
                }

            },
            messages:{
                loginName:{
                    isRightfulString:"只能输入英文字母大小写、数字、下划线"
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

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>