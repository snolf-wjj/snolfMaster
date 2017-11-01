package com.snolf.system.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/authority")
public class SysAuthorityController {

	@RequiresPermissions("/system/authority/list.html")
	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/authority/list";
	}

	@RequiresPermissions("/system/authority/add.html")
	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/authority/add";
	}

	@RequiresPermissions("/system/authority/edit.html")
	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/authority/edit";
	}
}