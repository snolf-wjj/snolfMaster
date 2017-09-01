package com.snolf.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/role")
public class SysRoleController {

	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/role/list";
	}

	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/role/add";
	}

	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/role/edit";
	}
}