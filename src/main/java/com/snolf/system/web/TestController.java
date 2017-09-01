package com.snolf.system.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/19
 * Time: 11:31
 */
@RestController
@RequestMapping("rest")
public class TestController {

	@RequestMapping("/hello")
	public String index() {
		return "Hello Word";
	}
}
