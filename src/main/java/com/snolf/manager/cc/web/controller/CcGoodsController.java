package com.snolf.manager.cc.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/cc/goods")
public class CcGoodsController {

	@RequiresPermissions("/manager/cc/goods/list.html")
	@RequestMapping(value = "/list.html")
	public String queryListPage() {
		return "/manager/999consumersclub/goods/list";
	}

	@RequiresPermissions("/manager/cc/goods/add.html")
	@RequestMapping("/add.html")
	public String addPage() {
		return "/manager/999consumersclub/goods/add";
	}

	@RequiresPermissions("/manager/cc/goods/edit.html")
	@RequestMapping(value = "/edit.html")
	public String editPage() {
		return "/manager/999consumersclub/goods/edit";
	}
}
