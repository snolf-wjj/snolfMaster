package com.snolf.common.exception;

/**
 * 
 * 参数缺失异常
 * 
 * @Title: ParamDefectException.java
 * @Description: <功能详细描述>
 * @author gaomingjie
 * @date 2017年5月2日下午6:37:21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParamDefectException extends Exception {

	private static final long serialVersionUID = 4024823458418290570L;

	public ParamDefectException() {
		super();
	}

	public ParamDefectException(String msg) {
		super(msg);
	}
}
