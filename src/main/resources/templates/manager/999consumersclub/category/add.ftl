<#include "../../../common/meta.ftl"/>
<link rel="stylesheet" type="text/css" href="${base}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
<title>添加消费商</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
    <form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品类目名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name" v-model="category.name">
            </div>
        </div>
        <div id="treeBox" class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上级类目：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" readonly value="" @focus="showMenu()" placeholder="" id="parentName" name="parentName">
                <input type="hidden" class="input-text" placeholder="" id="parentId" name="parentId" v-model="category.parentId">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="status" v-model="category.status" value="0" type="radio" id="type-1">
                    <label for="type-1">启用</label>
                </div>
                <div class="radio-box">
                    <input name="status" v-model="category.status" value="1" type="radio" id="type-2">
                    <label for="type-2">禁用</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button class="btn btn-primary radius" type="button" @click="submit">提交</button>
            </div>
        </div>
    </form>
</article>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="tree" class="ztree" style="margin-top:0; width:160px;height: 100px;"></ul>
</div>
<#include "../../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
    /**
     * 下拉树
     */
    var setting = {
        check: {
            enable: false,
            chkStyle: "radio",
            radioType: "all"
        },
        view: {
            dblClickExpand: false,
            showIcon: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
                nodes = zTree.getSelectedNodes(),
                v = "";
        var dataObj = $("#parentName");
        dataObj.attr("value", nodes[0].name);
        app.category.parentId = nodes[0].id;
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

//----------------------------------------------------------------------------------------
    var app = new Vue({
        el: '#app',
        data: {
        	treeData: [],
            category: {
                name: '',
                parentId: '',
                status: 0,
                sort: ''
            }
        },
        mounted: function() {
            this.getCategoryAll();
        },
        methods: {
            getCategoryAll: function () {
                this.$http.get("${base}/manager/cc/rest/category/tree").then(function(response) {
                    var data = response.data;
                    this.treeData = data.data;
                    if ("0000" == data.code) {
                        $.fn.zTree.init($("#tree"), setting, data.data);
                        treeObj = $.fn.zTree.getZTreeObj("tree");
                    } else {
                        $.Huimodalalert(data.msg,2000);
                    }

                });
            },
            submit: function() {
                if (!$("#form-member-add").valid())
                    return;
                this.$http.post("${base}/manager/cc/rest/category/add", this.category, {emulateJSON:true}).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout("parentReload()", 500);
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }
                });
            },
            showMenu: function() {
                var dataObj = $("#parentName");
                var dataOffset = $("#parentName").offset();
                $("#menuContent").css({left:dataOffset.left + "px", top:dataOffset.top + dataObj.outerHeight() + "px"}).slideDown("fast");
                var heigth = this.getTreeDataLength(this.treeData);
                $(".ztree").height(heigth*20);
                $("body").bind("mousedown", onBodyDown);
            },
            getTreeDataLength: function(data) {
            	var length = 0;
            	for (var i = 0; i < data.length; i++) {
                    if (data[i].children.length > 0) {
                    	length +=this.getTreeDataLength(data[i].children);
                    }
                    length++;
                }
                return length;
            }
        }

    });
// ------------- iCheck
    $(function() {
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
                parentName:{
                    required:true,
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
                    $(element).attr("title", $(error).text()).tooltip("show");
                }
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>