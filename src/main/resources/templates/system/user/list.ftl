﻿<#include "../../common/meta.ftl"/>
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="app" class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endCreateTime\')||\'%y-%M-%d\'}' })" id="startCreateTime" class="input-text Wdate" style="width:120px;" v-model="startCreateTime" placeholder="开始时间">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'startCreateTime\')}',maxDate:'%y-%M-%d' })" id="endCreateTime" class="input-text Wdate" style="width:120px;" v-model="endCreateTime" placeholder="结束时间">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称" id="userName" v-model="userName">
		<button type="submit" class="btn btn-success radius" id="" v-on:click="loadData"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" @click="batchDel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a href="javascript:;" @click="add('添加','${base}/system/user/add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
		</span>
		<span class="r">共有数据：<strong v-cloak>{{total}}</strong> 条</span> </div>
	<div class="mt-20">
		<table id="dataTable" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="check"  value=""></th>
					<th width="100">账号</th>
					<th width="80">昵称</th>
					<th width="100">所属角色</th>
					<th width="130">最后登录时间</th>
					<th width="130">最后登录IP</th>
					<th width="130">添加时间</th>
					<th width="130">状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<template v-if="dataList.length">
				<tr class="text-c" v-for="user in dataList">
					<td><input type="checkbox" v-bind:value="user.id" name="check" text="check"></td>
					<td>{{user.loginName }}</td>
					<td>{{user.userName}}</td>
					<td>{{user.roleName}}</td>
					<td>{{user.lastLoginTime|vTime}}</td>
					<td>{{user.lastLoginIp}}</td>
					<td>{{user.createTime|vTime}}</td>
					<td>
						<span class="label label-success radius" v-if="user.userStatus == 0">已启用</span>
						<span class="label label-danger radius" v-else-if="user.userStatus == 1">已禁用</span>
					</td>
					<td class="td-manage">
                        <a title="停用" href="javascript:;" v-if="user.userStatus == 0" v-on:click="updateStatus(user.id, 1)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>
                        <a title="启用" href="javascript:;" v-else-if="user.userStatus == 1" v-on:click="updateStatus(user.id, 0)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>

						<a title="分配角色" href="javascript:;" v-on:click="assignRole('分配角色','${base}/system/user/assignRole.html',user.id, user.userStatus, '','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe611;</i></a>
						<a title="编辑" href="javascript:;" v-on:click="edit('编辑','${base}/system/user/edit.html',user.id,'','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
						<a title="删除" href="javascript:;" v-on:click="del(this, user.id)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</td>
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
        userName: '',
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
            this.$http.get("${base}/system/rest/user/list",{params:{"pageNum": this.pageNum, "userName": this.userName,
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
        assignRole: function(title, url, id, userStatus, w, h){
        	if (userStatus == 1) {
                layer.msg("该用户已被禁用，请先解禁！", {icon: 5, time: 1100});
                return;
            }
            var param = "?userId=" + id;
            layer_show(title, url+param, w, h);
        },
		updateStatus: function (id, status) {
        	var str = "确认要冻结吗？";
        	if ("0" == status)
        		str = "确认要解冻吗？";
            layer.confirm(str, function() {
                Vue.http.post("${base}/system/rest/user/updateStatus", {"id":id, "userStatus":status}, {emulateJSON:true}).then(function(response) {
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
		del: function(obj, id) {
            layer.confirm('确认要删除吗？',function() {
                Vue.http.post("${base}/system/rest/user/delete", {"id":id}, {emulateJSON:true}).then(function(response) {
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
				Vue.http.post("${base}/system/rest/user/batchDelete", {"ids":ids}, {emulateJSON:true}).then(function(response) {
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