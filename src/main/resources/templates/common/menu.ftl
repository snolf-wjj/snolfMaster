<aside class="Hui-aside wrap-menu">
	<#--<div class="menu_dropdown bk_2">
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 用户中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${base}/system/dept/list.html" data-title="部门管理" href="javascript:void(0)">部门管理</a></li>
					<li><a data-href="${base}/system/role/list.html" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<li><a data-href="${base}/system/project/list.html" data-title="项目管理" href="javascript:void(0)">项目管理</a></li>
					<li><a data-href="${base}/system/authority/list.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>
					<li><a data-href="${base}/system/user/list.html" data-title="用户管理" href="javascript:void(0)">用户管理</a></li>
				</ul>
			</dd>
		</dl>
	</div>-->
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>

<script type="text/javascript" src="${base}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base}/common/menu.js"></script>
<script type="application/javascript">
    $(function(){
        $.get("${base}/system/rest/getSystemMenu", function(result){
            var menuJson = result.data;
            new AccordionMenu({menuArrs: menuJson});
        });

    });
</script>