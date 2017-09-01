package com.snolf.common.exception;

/**
 * 
 * 参数错误异常
 * 
 * @Title: ParamMistakeException.java
 * @Description: <功能详细描述>
 * @author gaomingjie
 * @date 2017年5月2日下午6:37:38
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParamMistakeException extends Exception {

	private static final long serialVersionUID = 4024823458418290570L;

	public ParamMistakeException() {
		super();
	}

	public ParamMistakeException(String msg) {
		super(msg);
	}
}
