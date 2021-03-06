package com.snolf.manager.cc.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/cc/orderGoods")
public class CcOrderGoodsController {

	@RequiresPermissions("/manager/cc/orderGoods/list.html")
	@RequestMapping(value = "/list.html")
	public String queryListPage() {
		return "/manager/999consumersclub/orderGoods/list";
	}

	@RequiresPermissions("/manager/cc/orderGoods/add.html")
	@RequestMapping("/add.html")
	public String addPage() {
		return "/manager/999consumersclub/orderGoods/add";
	}

	@RequiresPermissions("/manager/cc/orderGoods/edit.html")
	@RequestMapping(value = "/edit.html")
	public String editPage() {
		return "/manager/999consumersclub/orderGoods/edit";
	}
}
