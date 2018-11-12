package com.snolf.manager.cc.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/cc/member")
public class CcMemberController {

	@RequiresPermissions("/manager/cc/member/list.html")
	@RequestMapping(value = "/list.html")
	public String queryListPage() {
		return "/manager/999consumersclub/member/list";
	}

	@RequiresPermissions("/manager/cc/member/add.html")
	@RequestMapping("/add.html")
	public String addPage() {
		return "/manager/999consumersclub/member/add";
	}

	@RequiresPermissions("/manager/cc/member/edit.html")
	@RequestMapping(value = "/edit.html")
	public String editPage() {
		return "/manager/999consumersclub/member/edit";
	}
}
