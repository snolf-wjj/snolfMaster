<#include "../../common/meta.ftl"/>
<title>添加部门</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="项目名称" id="name" name="name" v-model="project.name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目标识：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="项目标识" id="proKey" name="proKey" v-model="project.proKey">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目链接：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="项目链接(例：www.baidu.com)" id="url" name="url" v-model="project.url">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">项目状态：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="status" v-model="project.status" value="0" type="radio" id="status-1">
                    <label for="isShow-1">正常</label>
                </div>
                <div class="radio-box">
                    <input name="status" v-model="project.status" value="1" type="radio" id="status-2">
                    <label for="status-2">停用</label>
                </div>
                <div class="radio-box">
                    <input name="status" v-model="project.status" value="2" type="radio" id="status-3">
                    <label for="status-3">维护</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="remark" cols="" rows="" class="textarea" v-model="project.remark" placeholder="说点什么..." onkeyup="$(this).Huitextarealength()"></textarea>
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
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
var app = new Vue({
	el: '#app',
	data: {
        project: {
            name: '',
            proKey: '',
            url: '',
            status: '',
            remark: ''
        }
	},
	mounted: function() {
		this.project.id = getQueryString("id");
		this.getProject();
	},
	methods: {
		getProject: function() {
            this.$http.get("${base}/system/rest/project/get?id="+getQueryString("id")).then(function(response) {
                var data = response.data;
                if ("0000" == data.code) {
                	this.project.name = data.data.name;
                	this.project.proKey = data.data.proKey;
                	this.project.url = data.data.url;
                	this.project.status = data.data.status;
                	this.project.remark = data.data.remark;
                    $("input:radio[value='"+data.data.status+"']").iCheck('check');
                } else {
                    layer.msg(data.msg, {icon: 5, time: 1000});
				}

            });
		},
		submit: function() {
			if (!$("#form-member-add").valid())
				return;
            this.project.status = $("input[name=status]:checked").val();
			this.$http.post("${base}/system/rest/project/edit", this.project, {emulateJSON:true}).then(function(response) {
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
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
	$("#form-member-add").validate({
        debug: true,
		rules:{
			name:{
				required:true,
			},
            proKey:{
				required:true,
                isRightfulString:true
			},
            url:{
                required:true,
                isURL: true,
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