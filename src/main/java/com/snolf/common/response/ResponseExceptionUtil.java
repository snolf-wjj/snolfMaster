package com.snolf.common.response;

import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.exception.BusinessException;
import com.snolf.common.exception.ParamDefectException;
import com.snolf.common.exception.ParamMistakeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理异常工具类
 * 
 * @Title: ResponseExceptionUtil.java
 * @Description: <功能详细描述>
 * @author gaomingjie
 * @date 2017年5月2日下午6:40:28
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseExceptionUtil {
	public static Logger logger = LoggerFactory
	        .getLogger(ResponseExceptionUtil.class);

	/**
	 * 
	 * 根据异常类型返回对应信息
	 * 
	 * @Title: handleException
	 * @Description: <功能详细描述>
	 * @author gaomingjie
	 * @date 2017年5月2日 下午6:40:53
	 * @param e
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> ResponseResult<T> handleException(Exception e) {
		return handleException("", e);
	}

	public static <T> ResponseResult<T> handleException(String exceptionKey,
	        Exception e) {
		if (e instanceof ParamDefectException) {
			// 参数缺失异常
			logger.error("业务返回异常Key:[{}][参数缺失],异常信息:{}", exceptionKey,
			        e.getMessage(), e);
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0004.getCode(),
			        e.getMessage());
		} else if (e instanceof ParamMistakeException) {
			// 参数错误异常
			logger.error("业务返回异常Key:[{}][参数错误],异常信息:{}", exceptionKey,
			        e.getMessage(), e);
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0002.getCode(),
			        e.getMessage());
		} else if (e instanceof BusinessException) {
			// 业务异常
			logger.warn("业务返回异常Key:[{}][数据错误],异常信息:{}", exceptionKey,
			        e.getMessage(), e);
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(),
			        e.getMessage());
		} else {
			// 其他异常
			logger.error("业务返回异常Key:[{}],异常信息:{}", exceptionKey, e.getMessage(),
			        e);
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0003.getCode(),
			        SystemStatusCode.sysMsg.L_0003.getMsg());
		}
	}

	/**
	 * 
	 * <记录异常信息>
	 * 
	 * @Title: takeException
	 * @Description: <功能详细描述>
	 * @author gaomingjie
	 * @date 2017年6月27日 上午11:38:35
	 * @param e
	 * @see [类、类#方法、类#成员]
	 */
	public static void takeException(String exceptionKey, Exception e) {
		if (e instanceof ParamDefectException) {
			// 参数缺失异常
			logger.error("业务返回异常Key:[{}][参数缺失],异常信息:{}", exceptionKey,
			        e.getMessage(), e);
		} else if (e instanceof ParamMistakeException) {
			// 参数错误异常
			logger.error("业务返回异常Key:[{}][参数错误],异常信息:{}", exceptionKey,
			        e.getMessage(), e);
		} else if (e instanceof BusinessException) {
			// 业务异常
			logger.warn("业务返回异常Key:[{}][数据错误],异常信息:{}", exceptionKey,
			        e.getMessage(), e);
		} else {
			// 其他异常
			logger.error("业务返回异常Key:[{}],异常信息:{}", exceptionKey, e.getMessage(),
			        e);
		}
	}
}
