package com.snolf.common.exception;

/**
 * 
 * 业务异常类
 * 
 * @Title: BusinessException.java
 * @Description: <功能详细描述>
 * @author gaomingjie
 * @date 2017年5月2日下午6:36:59
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 4024823458418211570L;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super(msg);
	}
}
