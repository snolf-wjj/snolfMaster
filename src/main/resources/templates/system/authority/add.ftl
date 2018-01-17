<#include "../../common/meta.ftl"/>
<title>添加权限</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
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
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    var app = new Vue({
        el: '#app',
        data: {
        	projectList: [],
            auth: {
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
            this.getProject();
            this.checkedType();
            $('input[name=type]').iCheck('disable');
            $("#isShow-1").iCheck('check');
        },
        methods: {
        	getProject: function() {
                this.$http.get("${base}/system/rest/project/listAll").then(function (response) {
                    this.projectList = response.data.data;
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
                    var proId = $("[name=proId]").val();
                    var type = $("input[name=type]:checked").val();
                    app.auth.type = type;
                    var optionStr = "";
                    if (type == 1) {//如果类型是目录
                        optionStr = "<option value='0000'>" + $("[name=proId]").find("option:selected").text() + "</option>"
                        $("#parentId").append(optionStr);
                    } else if(type == 2 || type == 3){//权限类型不是目录
                        $.get("${base}/system/rest/authority/listSelect?proId="+proId+"&type="+type, function(data, status) {
                            if (data.data.length == 0) {
                                layer.msg("没有上级权限，请先添加上级权限", {icon: 5, time: 1000});
                                $('input[name=type]').iCheck('uncheck');
                            } else {
                                for(var i = 0; i < data.data.length; i++) {
                                    optionStr += "<option value='" + data.data[i].id + "'>" + data.data[i].authName + "</option>"
                                }
                                $("#parentId").append(optionStr);
                            }
                        });
                    }
                });
            },
            submit: function() {
                if (!$("#form-member-add").valid())
                    return;
                this.auth.parentId = $("[name=parentId]").val();
                this.$http.post("${base}/system/rest/authority/add", this.auth, {emulateJSON:true}).then(function(response) {
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
                proId:{
                    required:true,
                },
                type:{
                    required:true,
                },
                parentId:{
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
    //字段校验结束
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>