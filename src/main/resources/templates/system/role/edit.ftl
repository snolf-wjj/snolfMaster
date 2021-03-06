<#include "../../common/meta.ftl"/>
<title>添加部门</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="name" name="name" v-model="role.name">
			</div>
		</div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色标识：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="roleKey" name="roleKey" v-model="role.roleKey">
            </div>
        </div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea cols="" rows="" class="textarea" name="remark" v-model="role.remark" placeholder="说点什么..." onKeyUp="$(this).Huitextarealength()"></textarea>
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
        role: {
            id: '',
            name: '',
            roleKey: '',
            remark: ''
        }
	},
	mounted: function() {
		this.role.id = getQueryString("id");
		this.getRole();
	},
	methods: {
		getRole: function() {
            this.$http.get("${base}/system/rest/role/get?id="+getQueryString("id")).then(function(response) {
                var data = response.data;
                if ("0000" == data.code) {
                	this.role.name = data.data.name;
                	this.role.roleKey = data.data.roleKey;
                	this.role.remark = data.data.remark;
                } else {
                    layer.msg(data.msg, {icon: 5, time: 1000});
				}

            });
		},
		submit: function() {
			if (!$("#form-member-add").valid())
				return;
			this.$http.post("${base}/system/rest/role/edit", this.role, {emulateJSON:true}).then(function(response) {
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
			name:{
				required:true,
			},
            roleKey:{
				required:true,
                isRightfulString:true
			}
			
		},
        messages:{
            roleKey:{
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