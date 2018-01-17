<#include "../../common/meta.ftl"/>
<title>修改权限</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
    <input type="hidden" id="oldType">
    <form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="authName" name="authName" v-model="auth.authName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属项目：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="input-text" size="1" name="proId" v-model="auth.proId" @change="selectProject()" v-cloak>
                    <option v-for="project in projectList" v-bind:value="project.id">
                        {{ project.name }} -> {{ project.url }}
                    </option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限类型：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="type" v-model="auth.type" value="1" type="radio" id="type-1">
                    <label for="type-1">目录</label>
                </div>
                <div class="radio-box">
                    <input name="type" v-model="auth.type" value="2" type="radio" id="type-3">
                    <label for="type-2">菜单</label>
                </div>
                <div class="radio-box">
                    <input name="type" v-model="auth.type" value="3" type="radio" id="type-3">
                    <label for="type-2">按钮</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上级权限：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="input-text" size="1" name="parentId" id="parentId">

                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">权限地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="url" name="url" v-model="auth.url">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限标识：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="" id="authKey" name="authKey" v-model="auth.authKey">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限级别：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="权限级别" id="level" name="level" v-model="auth.level">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">是否展示：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="isShow" v-model="auth.isShow" value="0" type="radio" id="isShow-1" checked >
                    <label for="isShow-1">是</label>
                </div>
                <div class="radio-box">
                    <input name="isShow" v-model="auth.isShow" value="1" type="radio" id="isShow-2">
                    <label for="isShow-2">否</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">排序：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="排序" id="sort" name="sort" v-model="auth.sort">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="remark" cols="" rows="" class="textarea" v-model="auth.remark" placeholder="说点什么..." onkeyup="$(this).Huitextarealength()"></textarea>
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
        projectList: [],
        auth: {
        	id: '',
            authName: '',
            proId: '',
            type: '',
            parentId: '',
            url: '',
            authKey: '',
            level: '',
            isShow: '',
            sort: '',
            remark: ''
        }
	},
	mounted: function() {
		this.auth.id = getQueryString("id");
        this.checkedType();
		this.getProject();
	},
	methods: {
        getProject: function() {
            this.$http.get("${base}/system/rest/project/listAll").then(function (response) {
                this.projectList = response.data.data;
                this.getAuthority();
            },function (response) {
            });
        },
        selectProject: function() {//根据项目和类型获取权限列表
            $('input[name=type]').iCheck('enable');
            $('input[name=type]').iCheck('uncheck');
            $("#parentId").empty(); //清空上级权限
        },
        checkedType: function () {
            $('input[name=type]').on('ifChecked', function(event){
                $("#parentId").empty();//清空上级权限option
//                var proId = $("[name=proId]").val();
                var proId = app.auth.proId;
                var type = $("input[name=type]:checked").val();
                var optionStr = "";
                if (type == 1) {//如果类型是目录
                    optionStr = "<option value='0000'>" + $("[name=proId]").find("option:selected").text() + "</option>"
                    $("#parentId").append(optionStr);
                } else if(type == 2 || type == 3){//权限类型不是目录
                    var paramData = "?proId="+proId+"&type="+type;
                    if ($("#oldType").val() == 2 && type == 3) {
                        paramData += "&id=" + app.auth.id;
                    }
                    $.get("${base}/system/rest/authority/listSelect" + paramData, function(data, status) {
                        if (data.data.length == 0) {
                            layer.msg("没有上级权限，请先添加上级权限", {icon: 5, time: 1000});
                            $('input[name=type]').iCheck('uncheck');
                        } else {
                            for(var i = 0; i < data.data.length; i++) {
                                optionStr += "<option value='" + data.data[i].id + "'>" + data.data[i].authName + "</option>"
                            }
                            $("#parentId").append(optionStr);
                            $("#parentId").find("option[value='" + app.auth.parentId + "']").attr("selected",true);
                        }
                    });
                }
            });
        },
        getAuthority: function() {
            this.$http.get("${base}/system/rest/authority/get?id="+getQueryString("id")).then(function (response) {
            	var data = response.data;
                if ("0000" == data.code) {
                    this.auth.id = data.data.id;
                    this.auth.authName = data.data.authName;
                    this.auth.proId = data.data.proId;
                    this.auth.type = data.data.type;
                    this.auth.parentId = data.data.parentId;
                    this.auth.url = data.data.url;
                    this.auth.authKey = data.data.authKey;
                    this.auth.level = data.data.level;
                    this.auth.isShow = data.data.isShow;
                    this.auth.sort = data.data.sort;
                    this.auth.remark = data.data.remark;

                    $("#oldType").val(data.data.type);

                    $("input:radio[name=type][value="+this.auth.type+"]").iCheck('check');
                    $("input:radio[name=isShow][value="+this.auth.isShow+"]").iCheck('check');
                    var optionStr = "";
                    $("#parentId").empty();
                    if (this.auth.type == 1) {//如果类型是目录
                        setTimeout('optionStr = "<option value=0000 selected>" + $("[name=proId]").find("option:selected").text() + "</option>";$("#parentId").append(optionStr)', 10);
                    }
                    // 如果该权限下有子权限，则项目、类型、父权限不可编辑
                    if (data.data.childrenAuth) {
                        $("[name=proId]").attr("disabled",true);
                        $("[name=type]").iCheck('disable');
                        $("[name=parentId]").attr("disabled",true);
                    }

                } else {
                    $.Huimodalalert(data.msg,2000);
                }
            },function (response) {
            });
        },
		submit: function() {
			if (!$("#form-member-add").valid())
				return;
            this.auth.parentId = $("[name=parentId]").val();
            this.auth.type = $("input[name=type]:checked").val();
			this.$http.post("${base}/system/rest/authority/edit", this.auth, {emulateJSON:true}).then(function(response) {
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
			authName:{
				required:true,
			},
            authKey:{
				required:true,
                isRightfulString:true
			}
			
		},
        messages:{
            authKey:{
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