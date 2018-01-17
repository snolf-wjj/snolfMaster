<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="${base}/system/index.html">SNOLF-MASTER</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> <span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe72b;</i> 项目列表 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow" id="projectList">
						</ul>
					</li>
				</ul>
			</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<#--<li>超级管理员</li>-->
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${userName} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:void(0);" onclick="myselfInfo()">个人信息</a></li>
							<li><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal">修改密码</a></li>
							<li><a href="javascript:void(0);" onclick="logout('${base}');">退出</a></li>
						</ul>
					</li>
					<li id="Hui-msg" style="display: none;"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form form-horizontal" id="form-member-updatePassword">
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-3">旧密码：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="password" class="input-text" placeholder="" id="oldPassword" name="oldPassword">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-3">新密码：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="password" class="input-text" placeholder="" id="newPassword" name="newPassword">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-3">确认密码：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="password" class="input-text" placeholder="" id="newPassword1" name="newPassword1">
						</div>
					</div>
				</form>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updatePassword()">修改</button>
            </div>
        </div>
    </div>
</div>