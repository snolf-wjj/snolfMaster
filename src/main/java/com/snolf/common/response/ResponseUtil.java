package com.snolf.common.response;

import com.alibaba.fastjson.JSON;
import com.snolf.common.contact.SystemStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 返回数据封装工具
 * @Title: ResponseUtil.java
 * @author  wangjunjie
 * @date 2017/6/22 16:00
 */
public class ResponseUtil<T> {
	public static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
	
	/**
	 * 返回成功数据
	 * @Title:
	 * @author wangjunjie
	 * @date 2017/6/22 16:02
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> ResponseResult<T> success(T o) {
		ResponseResult<T> rr = new ResponseResult<T>();
		rr.setCode(SystemStatusCode.sysMsg.L_0000.getCode());
		rr.setMsg(SystemStatusCode.sysMsg.L_0000.getMsg());
		rr.setData(o);
		return rr;
	}

	/**
	 * 返回错误数据
	 * @Title: ResponseUtil.java
	 * @author  wangjunjie
	 * @date 2017/6/22 16:06
	 */
	public static <T> ResponseResult<T> error(String code, String msg) {
		ResponseResult<T> rr = new ResponseResult<T>();
		rr.setCode(code);
		rr.setMsg(msg);
		return rr;
	}

	public static void info(HttpServletResponse response, ResponseResult<?> result) {
		PrintWriter out = null;
		try {
			if (response != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=utf-8");
				out = response.getWriter();
				out.print(JSON.toJSONString(result));
			}
		} catch (Exception e) {
			logger.error("封装JSON异常,异常信息:{}", e.getMessage(), e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}