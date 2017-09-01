package com.snolf.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/24
 * Time: 17:01
 */
@Controller
@RequestMapping("/system")
public class LoginController {

	/**
	 * 登录页
	 * @author wangjunjie
	 * @date 2017/6/24 17:02
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/**
	 * 登录成功后首页
	 * @author wangjunjie
	 * @date 2017/6/24 17:00
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	/**
	 * 登录成功后欢迎页
	 * @author wangjunjie
	 * @date 2017/6/24 17:00
	 */
	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}

}