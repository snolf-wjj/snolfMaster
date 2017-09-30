package com.snolf.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/authority")
public class SysAuthorityController {

	@RequestMapping(value = "/list")
	public String queryListPage() {
		return "system/authority/list";
	}

	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/authority/add";
	}

	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/authority/edit";
	}
}