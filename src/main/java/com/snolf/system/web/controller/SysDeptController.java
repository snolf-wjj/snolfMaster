package com.snolf.system.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/dept")
public class SysDeptController {

	@RequiresPermissions("/system/dept/list.html")
	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/dept/list";
	}

	@RequiresPermissions("/system/dept/add.html")
	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/dept/add";
	}

	@RequiresPermissions("/system/dept/edit.html")
	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/dept/edit";
	}
}