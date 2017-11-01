package com.snolf.system.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/system/user")
public class SysUserController {

	@RequiresPermissions("/system/user/list.html")
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String queryListPage() {
		return "system/user/list";
	}

	@RequiresPermissions("/system/user/add.html")
	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/user/add";
	}

	@RequiresPermissions("/system/user/edit.html")
	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/user/edit";
	}

	@RequiresPermissions("/system/user/assignRole.html")
	@RequestMapping(value = "/assignRole")
	public String assignRolePage() {
		return "system/user/assignRole";
	}
}