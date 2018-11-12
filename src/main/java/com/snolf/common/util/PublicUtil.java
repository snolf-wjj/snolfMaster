package com.snolf.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: PublicUtil </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author Wjj
 * @CreateDate 2018/5/15 17:15
 */
public class PublicUtil {
	public PublicUtil() {
	}

	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		} else if (pObj == "") {
			return true;
		} else {
			if (pObj instanceof String) {
				if (((String)pObj).length() == 0) {
					return true;
				}
			} else if (pObj instanceof Collection) {
				if (((Collection)pObj).size() == 0) {
					return true;
				}
			} else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
				return true;
			}

			return false;
		}
	}

	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		} else if (pObj == "") {
			return false;
		} else {
			if (pObj instanceof String) {
				if (((String)pObj).length() == 0) {
					return false;
				}
			} else if (pObj instanceof Collection) {
				if (((Collection)pObj).size() == 0) {
					return false;
				}
			} else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
				return false;
			}

			return true;
		}
	}

	public static <T> List<List<T>> separateList(List<T> list) {
		List<List<T>> lists = new ArrayList();
		int size = list.size();
		int sum = 100;
		int count = size / sum;
		int yu = size % sum;
		if (count == 0) {
			lists.add(list);
		} else {
			if (size % sum != 0) {
				++count;
			}

			for(int i = 0; i < count; ++i) {
				List subList;
				if (sum * (i + 1) <= size) {
					subList = list.subList(sum * i, sum * (i + 1));
				} else {
					subList = list.subList(sum * i, sum * i + yu);
				}

				lists.add(subList);
			}
		}

		return lists;
	}
}

