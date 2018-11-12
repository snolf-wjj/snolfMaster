<#include "../../../common/meta.ftl"/>
<title>添加消费商</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="phone" name="phone" v-model="member.phone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name" v-model="member.name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="sex" v-model="member.sex" value="1" type="radio" id="type-1">
                    <label for="type-1">男</label>
                </div>
                <div class="radio-box">
                    <input name="sex" v-model="member.sex" value="0" type="radio" id="type-3">
                    <label for="type-2">女</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>年龄：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="age" name="age" v-model="member.age">
            </div>
        </div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button class="btn btn-primary radius" type="button" @click="submit">提交</button>
			</div>
		</div>
	</form>
</article>
<#include "../../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="${base}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
var app = new Vue({
	el: '#app',
	data: {
        member: {
        	id:'',
            phone: '',
            name: '',
            sex: '',
            age: '',
            memberLevel: '',
            starLevel: '',
            inviteCode: '',
            recommendCode: '',
            status: ''
        }
	},
	mounted: function() {
		this.member.id = getQueryString("id");
		this.getMember();
	},
	methods: {
        getMember: function() {
            this.$http.get("${base}/manager/cc/rest/member/get?id="+getQueryString("id")).then(function(response) {
                var data = response.data;
                if ("0000" == data.code) {
                	this.member.id = data.data.id;
                	this.member.phone = data.data.phone;
                	this.member.name = data.data.name;
                	this.member.sex = data.data.sex;
                	this.member.age = data.data.age;
                	this.member.status = data.data.status;
                } else {
                    layer.msg(data.msg, {icon: 5, time: 1000});
				}

            });
		},
		submit: function() {
			if (!$("#form-member-add").valid())
				return;
			this.$http.post("${base}/manager/cc/rest/member/edit", this.member, {emulateJSON:true}).then(function(response) {
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

$(function(){
	$("#form-member-add").validate({
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

</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>