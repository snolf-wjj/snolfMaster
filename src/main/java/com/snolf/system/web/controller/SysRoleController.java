package com.snolf.system.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/role")
public class SysRoleController {

	@RequiresPermissions("/system/role/list.html")
	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/role/list";
	}

	@RequiresPermissions("/system/role/add.html")
	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/role/add";
	}

	@RequiresPermissions("/system/role/edit.html")
	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/role/edit";
	}

	@RequiresPermissions("/system/role/assignAuth.html")
	@RequestMapping(value = "/assignAuth")
	public String assignAuthorityPage() {
		return "system/role/assignAuthority";
	}
}