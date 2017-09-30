package com.snolf.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/project")
public class SysProjectController {

	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/project/list";
	}

	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/project/add";
	}

	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/project/edit";
	}
}