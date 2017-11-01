package com.snolf.system.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/project")
public class SysProjectController {

	@RequiresPermissions("/system/project/list.html")
	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/project/list";
	}

	@RequiresPermissions("/system/project/add.html")
	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/project/add";
	}

	@RequiresPermissions("/system/project/edit.html")
	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/project/edit";
	}
}