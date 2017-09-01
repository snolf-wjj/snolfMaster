<#include "../../common/meta.ftl"/>
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 部门管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="app" class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endCreateTime\')||\'%y-%M-%d\'}' })" id="startCreateTime" class="input-text Wdate" style="width:120px;" v-model="startCreateTime" placeholder="开始时间">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'startCreateTime\')}',maxDate:'%y-%M-%d' })" id="endCreateTime" class="input-text Wdate" style="width:120px;" v-model="endCreateTime" placeholder="结束时间">
		<input type="text" class="input-text" style="width:250px" placeholder="输入角色名称" id="name" v-model="name">
		<button type="submit" class="btn btn-success radius" id="" v-on:click="loadData"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" @click="batchDel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a href="javascript:;" @click="add('添加','${base}/system/role/add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a>
		</span>
		<span class="r">共有数据：<strong>{{total}}</strong> 条</span> </div>
	<div class="mt-20">
		<table id="dataTable" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="check"  value=""></th>
					<th width="100">角色名称</th>
					<th width="40">标识</th>
					<th width="80">备注</th>
					<th width="130">添加时间</th>
					<th width="70">添加人</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<template v-if="dataList.length">
				<tr class="text-c" v-for="role in dataList">
					<td><input type="checkbox" v-bind:value="role.id" name="check" text="check"></td>
					<td>{{role.name }}</td>
					<td>{{role.roleKey}}</td>
					<td class="text-l">{{role.remark}}</td>
					<td>{{role.createTime|vTime}}</td>
					<td>{{role.createUser}}</td>
					<td class="td-manage"> <a title="编辑" href="javascript:;" v-on:click="edit('编辑','${base}/system/role/edit.html',role.id,'','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" v-on:click="del(this, role.id)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
				</template>
			</tbody>
		</table>
		<div id="pageList"></div>
	</div>
</div>
<#include "../../common/footer.ftl"/>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base}/lib/laypage/1.3/laypage.js"></script>
<script type="text/javascript">

var app = new Vue({
    el: '#app',
    data: {
        total: '',//数据总条数
        dataList: [],
		pageNum:1,
        name: '',
        startCreateTime: '',
        endCreateTime: ''
    },
    mounted: function() {
        this.loadData(1);
    },
    methods: {
        loadData: function(pageNum) {
        	this.startCreateTime = $("#startCreateTime").val();
        	this.endCreateTime = $("#endCreateTime").val();
            pageNum = isNaN(pageNum)?1:pageNum;
            this.pageNum = pageNum;
            layer.load();
            this.$http.get("${base}/system/rest/role/list",{params:{"pageNum": this.pageNum, "name": this.name,
			"startCreateTime":this.startCreateTime,"endCreateTime":this.endCreateTime}}).then(function (response) {
                layer.closeAll('loading');
            	var data = response.data.data;
            	if (response.data.code != "0000") {
                    layer.msg(response.data.msg, {icon: 5, time: 1000});
                    return;
                }
            	this.total = data.total;
                this.dataList = data.list;
                laypage({
                    cont: 'pageList', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                    pages: data.pages, //通过后台拿到的总页数
                    curr: data.pageNum, //初始化当前页
                    groups: 5,	//连续分页数
					skip: true,	//是否开启跳页
					//skin: 'default',
                    jump: function(obj, first){ //触发分页后的回调
						if (!first) {
							app.loadData(obj.curr)
                        }
                    }
                });
            },function (response) {
                layer.closeAll('loading');
                layer.msg("查询失败", {icon: 5, time: 1000});
            });

        },
		edit: function(title, url, id, w, h){
        	var param = "?id="+id;
			layer_show(title, url+param, w, h);
		},
        add: function(title, url, w, h){
            layer_show(title, url, w, h);
        },
		del: function(obj, id) {
            layer.confirm('确认要删除吗？',function() {
                Vue.http.post("${base}/system/rest/role/delete", {"id":id}, {emulateJSON:true}).then(function(response) {
                    var data = response.data;
                    if ("0000" == data.code) {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                        app.loadData(1);
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
					}
                },function(error){
                    layer.msg(data.msg, {icon: 5, time: 1000});
                });
            });
		},
        batchDel: function() {
            var id_array = new Array();
            $("input[text='check']:checked").each(function(){
                id_array.push($(this).val());//向数组中添加元素
            });
            if (id_array.length == 0) {
                layer.msg("请勾选要删除的列！", {icon: 7, time: 1000});
                return;
			}
            layer.confirm('确认要删除吗？',function() {
				var ids = id_array.join(',');
				Vue.http.post("${base}/system/rest/role/batchDelete", {"ids":ids}, {emulateJSON:true}).then(function(response) {
					var data = response.data;
					if ("0000" == data.code) {
						layer.msg(data.msg, {icon: 1, time: 1000});
						app.loadData(1);
                        $("input[text='check']").removeAttr("checked");
					} else {
						layer.msg(data.msg, {icon: 2, time: 1000});
					}
				},function(error){
					layer.msg(data.msg, {icon: 1, time: 1000});
				});
            });
        }
    },
    filters: {
    	vTime: function (value) {
            return formatDate(value, dateFormat.DATE_FORMAT_19H);
        }
    }
});

</script> 
</body>
</html>