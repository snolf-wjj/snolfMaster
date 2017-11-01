package com.snolf.base;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 重写BasicErrorController,主要负责系统的异常页面的处理以及错误信息的显示
 * @see org.springframework.boot.autoconfigure.web.BasicErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 *
 * @author Jonathan
 * @version 2016/5/31 11:22
 * @since JDK 7.0+
 */
@Controller
@RequestMapping(value = "error/")
public class ExceptionController implements ErrorController {

	@RequestMapping(value = "404.html")
	public String errorHtml404() {
		return "error/404";
	}

	/**
	 * 无权限返回页面
	 * @return
	 */
	@RequestMapping(value = "unauthorized.html")
	public String errorHtmlUnauthorized() {
		return "error/unauthorized";
	}


	/**
	 * 实现错误路径,暂时无用
	 * @return
	 */
	@Override
	public String getErrorPath() {
		return null;
	}
}