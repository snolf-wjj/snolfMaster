package com.snolf.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/system/user")
public class SysUserController {

	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String queryUserAll() {
		return "system/user/list";
	}

	@RequestMapping(value = "/add")
	public String addPage() {
		return "system/user/add";
	}

	@RequestMapping(value = "/edit")
	public String editPage() {
		return "system/user/edit";
	}
}