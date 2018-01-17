<#include "../../common/meta.ftl"/>
<link rel="stylesheet" type="text/css" href="${base}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
<title>添加部门</title>
<meta text="keywords" content="">
<meta text="description" content="">
</head>
<body>
<article class="page-container" id="app">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>部门名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="deptName" name="deptName" v-model="dept.deptName">
			</div>
		</div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系人：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="contactPerson" name="contactPerson" v-model="dept.contactPerson">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">联系电话：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="contactPhone" name="contactPhone" v-model="dept.contactPhone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">部门级别：</label>
            <div class="formControls col-xs-8 col-sm-9">
				<select class="input-text" v-model="dept.deptLevel" size="1" name="deptLevel">
					<option value="" selected>请选择级别</option>
					<option value="1">一级</option>
					<option value="2">二级</option>
					<option value="3">三级</option>
					<option value="4">四级</option>
					<option value="5">五级</option>
					<option value="6">六级</option>
					<option value="7">七级</option>
				</select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上级部门：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" readonly onclick="showMenu();return false;" value="" placeholder="" id="parentName" name="parentName" v-model="dept.parentName">
                <input type="hidden" class="input-text" value="" placeholder="" id="parentId" name="parentId" v-model="dept.parentId">
            </div>
        </div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea cols="" rows="" class="textarea" name="remark" v-model="dept.remark" placeholder="说点什么..." onKeyUp="$(this).Huitextarealength()"></textarea>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button class="btn btn-primary radius" type="button" @click="submit">提交</button>
			</div>
		</div>
	</form>
    <div id="menuContent" class="menuContent" style="position: absolute; left: 52px; top: 112px;display:none;">
        <ul id="treeDemo" class="ztree" style="margin-top:0; width: 180px; height: 170px; -moz-user-select: none;"></ul>
    </div>
</article>
<#include "../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="${base}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${base}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${base}/lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
var app = new Vue({
	el: '#app',
	data: {
        dept: {
            id: '',
            deptName: '',
            contactPerson: '',
            parentId: '',
            parentName: '',
            contactPhone: '',
            deptLevel: '',
            remark: ''
        }
	},
	mounted: function() {
		this.dept.id = getQueryString("id");
		this.getDept();
		this.getDeptTree();
	},
	methods: {
		getDept: function() {
            this.$http.get("${base}/system/rest/dept/get?id="+getQueryString("id")).then(function(response) {
                var data = response.data;
                if ("0000" == data.code) {
                	this.dept.deptName = data.data.deptName;
                	this.dept.contactPerson = data.data.contactPerson;
                	this.dept.parentId = data.data.parentId;
                	this.dept.parentName = data.data.parentName;
                	this.dept.contactPhone = data.data.contactPhone;
                	this.dept.deptLevel = data.data.deptLevel;
                	this.dept.remark = data.data.remark;
                } else {
                    $.Huimodalalert(data.msg,2000);
				}

            });
		},
		getDeptTree: function() {
			var id = getQueryString("id");
            this.$http.get("${base}/system/rest/dept/tree?id="+id).then(function(response) {
                var data = response.data;
                if ("0000" == data.code) {
                    $.fn.zTree.init($("#treeDemo"), setting, data.data);
                } else {
                    $.Huimodalalert(data.msg,2000);
                }

            });
		},
		submit: function() {
			if (!$("#form-member-add").valid())
				return;
			this.dept.parentName = $("#parentName").val()
			this.dept.parentId = $("#parentId").val()
			this.$http.post("${base}/system/rest/dept/edit", this.dept, {emulateJSON:true}).then(function(response) {
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
			deptName:{
				required:true,
			},
            contactPerson:{
				required:true,
			},
            contactPhone:{
				isMobile:true,
			},
            deptLevel:{
				required:true,
			},
            parentName:{
				required:false,
			},
			
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

/**
 * 下拉树
 */
var setting = {
    check: {
        enable: false
    },
    view: {
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClick,
        onDblClick: onDblClick,
        onAsyncSuccess: function () {
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getNodes();
            for (var i=0, l=nodes.length; i<l; i++) {
                if (nodes[i].id == $("#parentId").val()) {
                    treeObj.selectNode(nodes[i]);
                }
            }
        }
    }
};
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes();
    menuName = "";
    menuId = "";
    nodes.sort(function compare(a,b){return a.id-b.id;});//数字排序
    var isParent = nodes[0].getParentNode();
    var isChildren = nodes[0].children;
//        if((!isParent && !isChildren) || (isParent)){
    for (var i=0, l=nodes.length; i<l; i++) {
        menuName += nodes[i].name + ",";
        menuId += nodes[i].id + ",";
    }
    if (menuName.length > 0 ) menuName = menuName.substring(0, menuName.length-1);
    if (menuId.length > 0 ) menuId = menuId.substring(0, menuId.length-1);
    $("#parentName").val(menuName);
    $("#parentId").val(menuId);

//        }
}
function onDblClick() {
    $("#parentName").val("");
    $("#parentId").val("");
}

function showMenu() {
    var cityObj = $("#parentName");
    var cityOffset = cityObj.offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast")
    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>