<#include "../../common/meta.ftl"/>
<link rel="stylesheet" type="text/css" href="${base}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
<title>权限分配</title>
<meta text="keywords" content="">
<meta text="description" content="">
<style>
    div#treeBox {
        height:320px;
    }
    .hideDiv{
        overflow: hidden;
    }
    div#tree {
        border: 1px solid rgb(167, 183, 200);
        max-height: 320px;
        height: 320px;
        overflow: auto;
        scroll: none;
    }

    .line {
        border-top: solid 0px #eeeeee;
    }
</style>
</head>
<body>
<article class="page-container" id="app">
    <form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">角色名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span v-cloak>{{role.name}} </span>
            </div>
        </div>
        <div id="treeBox" class="row cl">
            <label class="form-label col-xs-4 col-sm-3">权限分配：</label>
            <div class="hideDiv">
                <div id="tree" class="formControls col-xs-8 col-sm-9 ztree">
                    <#-- 权限树 -->
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
<#include "../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
    /**
     * 下拉树
     */
    var setting = {
        check: {
            enable: true
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
            onAsyncSuccess: function () {
                var treeObj = $.fn.zTree.getZTreeObj("tree");
                var nodes = treeObj.getNodes();
                for (var i=0, l=nodes.length; i<l; i++) {
                    if (nodes[i].id == $("#parentId").val()) {
                    }
                }
            }
        }
    };


//----------------------------------------------------------------------------------------
    var treeObj;
    var app = new Vue({
        el: '#app',
        data: {
            role: {
            	roleId: '',
                name: '',
                roleKey: '',
                roleAuth: []
            }
        },
        mounted: function() {
            this.role.roleId = getQueryString("roleId");
            this.role.name = getQueryString("name");
            this.role.roleKey = getQueryString("roleKey");
            this.getAuthAll();
        },
        methods: {
            getAuthAll: function () {
                this.$http.get("${base}/system/rest/authority/tree?roleId=" + this.role.roleId).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        $.fn.zTree.init($("#tree"), setting, data.data);
                        treeObj = $.fn.zTree.getZTreeObj("tree");
                    } else {
                        $.Huimodalalert(data.msg,2000);
                    }

                });
            },
            submit: function() {
                this.role.roleAuth = [];
                var nodes = treeObj.getCheckedNodes(true);
                for (var i = 0; i < nodes.length; i++) {
                    var obj = new Object();
                    obj.roleId = this.role.roleId;
                    obj.roleKey = this.role.roleKey;
                    obj.authId = nodes[i].id;
                    obj.authKey = nodes[i].attributes.authKey;
                    this.role.roleAuth.push(obj);
                }
                this.$http.post("${base}/system/rest/role/assignRoleAuth", {"dataParam": jsonToStr(this.role.roleAuth),"roleId": this.role.roleId}, {emulateJSON:true}).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout("layer_close()", 500);
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }
                });
            }
        }

    });

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>