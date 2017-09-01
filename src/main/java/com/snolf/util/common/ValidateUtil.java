package com.snolf.util.common;

import com.snolf.common.exception.BusinessException;
import com.snolf.common.exception.ParamDefectException;
import com.snolf.common.exception.ParamMistakeException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/8/15
 * Time: 20:06
 */
public class ValidateUtil {
	/**
	 * 校验条件是否为空，如果符合则抛出参数缺失异常
	 * @Title: paramRequired
	 * @Description: <功能详细描述>
	 * @author gaomingjie
	 * @date 2017年5月2日 下午6:42:08
	 * @param value
	 * @param message
	 * @throws ParamDefectException
	 * @see [类、类#方法、类#成员]
	 */
	public static void paramRequired(Object value, String message)
			throws ParamDefectException {
		if (value == null) {
			throw new ParamDefectException(message);
		} else {
			if (value instanceof String
					&& StringUtils.isBlank(String.valueOf(value))) {
				throw new ParamDefectException(message);
			}
		}
	}

	/**
	 * 校验条件是否符合，如果符合则抛出参数错误异常
	 *
	 * @Title: paramValidate
	 * @Description: <功能详细描述>
	 * @author gaomingjie
	 * @date 2017年5月2日 下午6:42:40
	 * @param checked
	 * @param message
	 * @throws ParamMistakeException
	 * @see [类、类#方法、类#成员]
	 */
	public static void paramValidate(boolean checked, String message)
			throws ParamMistakeException {
		if (checked) {
			throw new ParamMistakeException(message);
		}
	}

	/**
	 * 校验条件是否符合，如果符合则抛出业务异常
	 *
	 * @Title: businessValidate
	 * @Description: <功能详细描述>
	 * @author gaomingjie
	 * @date 2017年5月2日 下午6:42:53
	 * @param checked
	 * @param message
	 * @throws BusinessException
	 * @see [类、类#方法、类#成员]
	 */
	public static void businessValidate(boolean checked, String message)
			throws BusinessException {
		if (checked) {
			throw new BusinessException(message);
		}
	}
}
