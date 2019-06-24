package com.snolf.third.classmates.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/third/classmates")
public class ClassmatesController {

	@RequestMapping(value = "/index.html", method = {RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return "third/classmates/index";
	}

	@RequestMapping(value = "/register.html")
	public String register() {
		return "third/classmates/register";
	}

	@RequestMapping(value = "/form.html")
	public String form() {
		return "third/classmates/form";
	}

	@RequiresPermissions("/third/classmates/info/list.html")
	@RequestMapping(value = "/info/list.html")
	public String infoList() {
		return "third/classmates/manage/infoList";
	}

	@RequiresPermissions("/third/classmates/user/list.html")
	@RequestMapping(value = "/user/list.html")
	public String userList() {
		return "third/classmates/manage/userList";
	}
}