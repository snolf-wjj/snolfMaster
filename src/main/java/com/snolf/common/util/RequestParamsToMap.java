package com.snolf.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/8/15
 * Time: 15:16
 */
public class RequestParamsToMap {
	/**
	 * 获取请求参数，并将参数转为Map<String, Object>
	 * @author wangjunjie
	 * @date 2017/8/15 15:18
	 */
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, String[]> properties = request.getParameterMap();//把请求参数封装到Map<String, String[]>中
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Map.Entry<String, String[]>> iter = properties.entrySet().iterator();
		String name = "";
		String value = "";
		while (iter.hasNext()) {
			Map.Entry<String, String[]> entry = iter.next();
			name = entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();//用于请求参数中请求参数名唯一
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
	/**
	 * 获取请求参数，并将参数转为Map<String, String>
	 * @author wangjunjie
	 * @date 2017/8/15 15:19
	 */
	public static Map<String, String> getParameterStringMap(HttpServletRequest request) {
		Map<String, String[]> properties = request.getParameterMap();//把请求参数封装到Map<String, String[]>中
		Map<String, String> returnMap = new HashMap<String, String>();
		String name = "";
		String value = "";
		for (Map.Entry<String, String[]> entry : properties.entrySet()) {
			name = entry.getKey();
			String[] values = entry.getValue();
			if (null == values) {
				value = "";
			} else if (values.length>1) {
				for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = values[0];//用于请求参数中请求参数名唯一
			}
			returnMap.put(name, value);

		}
		return returnMap;
	}

}