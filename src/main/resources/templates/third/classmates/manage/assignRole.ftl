<#include "../../../common/meta.ftl"/>
<link rel="stylesheet" type="text/css" href="${base}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
<title>角色分配</title>
<meta text="keywords" content="">
<meta text="description" content="">
<style>
    #tree {
        border: 1px solid rgb(209, 219, 229);
        width: 300px;
    }
</style>
</head>
<body>
<article class="page-container" id="app">
    <form action="" method="post" class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">用户名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span v-cloak>{{user.userName}} </span>
            </div>
        </div>
        <div id="treeBox" class="row cl">
            <label class="form-label col-xs-4 col-sm-3">角色分配：</label>
            <div class="hideDiv">
                <div id="tree" class="formControls col-xs-8 col-sm-9 ztree">
                    <#-- 角色树 -->
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
<#include "../../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
    /**
     * 下拉树
     */
    var setting = {
        check: {
            enable: true,
            chkStyle: "radio"
        },
        view: {
            dblClickExpand: false,
            showIcon: false,
            showLine: false
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
            user: {
            	id: '',
                userName: '',
                loginName: ''
            },
            userRole: []
        },
        mounted: function() {
            this.user.id = getQueryString("userId");
            this.getRoleAll();
            this.getUser();
        },
        methods: {
            getRoleAll: function () {
                this.$http.get("${base}/system/rest/role/getRoleAll?userId=" + this.user.id).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        $.fn.zTree.init($("#tree"), setting, data.data);
                        treeObj = $.fn.zTree.getZTreeObj("tree");
                    } else {
                        $.Huimodalalert(data.msg,2000);
                    }

                });
            },
            getUser: function () {
                this.$http.get("${base}/system/rest/user/get?id=" + this.user.id).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        this.user.userName = data.data.userName;
                        this.user.loginName = data.data.loginName;
                    } else {
                        $.Huimodalalert(data.msg,2000);
                    }

                });
            },
            submit: function() {
                this.userRole = [];
                var nodes = treeObj.getCheckedNodes(true);
                for (var i = 0; i < nodes.length; i++) {
                    var obj = new Object();
                    obj.roleId = nodes[i].id;
                    obj.roleName = nodes[i].name;
                    obj.roleKey = nodes[i].attributes.roleKey;
                    obj.userId = this.user.id;
                    obj.userName = this.user.userName;
                    this.userRole.push(obj);
                }
                this.$http.post("${base}/system/rest/user/assignUserRole", {"dataParam": jsonToStr(this.userRole),"userId": this.user.id}, {emulateJSON:true}).then(function(response) {
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

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>