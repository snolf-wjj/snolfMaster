package com.snolf.manager.cc.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/cc/category")
public class CcCategoryController {

	@RequiresPermissions("/manager/cc/category/list.html")
	@RequestMapping(value = "/list.html")
	public String queryListPage() {
		return "/manager/999consumersclub/category/list";
	}

	@RequiresPermissions("/manager/cc/category/add.html")
	@RequestMapping("/add.html")
	public String addPage() {
		return "/manager/999consumersclub/category/add";
	}

	@RequiresPermissions("/manager/cc/category/edit.html")
	@RequestMapping(value = "/edit.html")
	public String editPage() {
		return "/manager/999consumersclub/category/edit";
	}
}
