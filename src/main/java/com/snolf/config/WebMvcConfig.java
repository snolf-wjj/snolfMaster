package com.snolf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/20
 * Time: 10:21
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	/*
	 * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
	 * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
	 * @Title:
	 * @author wangjunjie
	 * @date 2017/6/20 10:35
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/toLogin").setViewName("login");
		super.addViewControllers(registry);
	}

	/*
	 * 拦截器
	 * @Title:
	 * @author wangjunjie
	 * @date 2017/6/20 10:29
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
//		registry.addInterceptor(new MyInterceptor()).addPathPatterns(" *")
//				.excludePathPatterns("/system *");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("*")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/**");
	}

}
