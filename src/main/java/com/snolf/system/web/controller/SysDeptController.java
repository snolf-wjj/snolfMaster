package com.snolf.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/dept")
public class SysDeptController {

	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/dept/list";
	}

	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/dept/add";
	}

	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/dept/edit";
	}
}