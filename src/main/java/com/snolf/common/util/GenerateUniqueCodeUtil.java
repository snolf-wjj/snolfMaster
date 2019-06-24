package com.snolf.common.util;

import com.snolf.common.exception.BusinessException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成唯一编码
 *
 * @author wangjunjie
 * @date 2019/6/17 16:36
 */
public class GenerateUniqueCodeUtil {
	private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z'};

	private static String nums = "0123456789";

	private static Map<Long, Set<String>> millisMap;

	private static Set<String> millisSet;

	private static Logger logger = LoggerFactory.getLogger(GenerateUniqueCodeUtil.class);

	/**
	 * 生成8位短编码，有可能会重复
	 * @return
	 */
	public static String shortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	/**
	 * 时间戳加随机8位短编码，几乎无重复可能性
	 *
	 * @return
	 */
	public static String uniqueShortUuid() {
		String shortId = shortUuid();
		return System.currentTimeMillis() + shortId;
	}

	/**
	 * 获取纯数字的唯一编码
	 * @param len 在13位时间戳后面再加多少位
	 * @return
	 * @throws BusinessException
	 */
	public static synchronized String uniqueNumId(int len) throws BusinessException {
		long millis = System.currentTimeMillis();
		if (millisMap == null || millisMap.isEmpty()) {
			millisMap = new HashMap<Long, Set<String>>();
			millisSet = new HashSet<String>();
			millisMap.put(millis, millisSet);
		} else if (!millisMap.containsKey(millis)) {
			millisMap.clear();
			millisSet.clear();
			millisMap.put(millis, millisSet);
		}
		StringBuffer sb = new StringBuffer(String.valueOf(millis));
		Random r = new Random();
		int nlen = nums.length();
		boolean iscircle = false;
		StringBuffer randomstr = new StringBuffer("");
		int count = 0;
		do {
			randomstr.delete(0, randomstr.length());
			for (int i = 0; i < len; i++) {
				randomstr.append(nums.charAt(r.nextInt(nlen)));
			}
			String rstr = randomstr.toString();
			if (!millisSet.isEmpty() && millisSet.contains(rstr)) {
				iscircle = true;
				if (count > 99) {
					throw new BusinessException("没有唯一性编码了！");
				}
				count++;
			} else {
				millisSet.add(rstr);
				iscircle = false;
			}
		} while (iscircle);
		sb.append(randomstr);
		return sb.toString();
	}

	/**
	 * 获取纯数字随机码
	 * @param count
	 * @return
	 */
	public static String getRandomNumStr(int count) {
		if (count < 1) {
			return null;
		}
		Random r = new Random();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < count; i++) {
			sb.append(r.nextInt(10));
		}
		return sb.toString();
	}

	/**
	 * 产生指定位数的随机数
	 * @param count 随机码位数
	 * @param range 随机数产生范围
	 * @return
	 */
	public static String getRandom(int count, int range) {
		if (count <= 0) {
			return null;
		}
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(nums.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 获取18位的纯数字编码
	 *
	 * @return
	 */
	public static long unique18NumId() {
		String ucode = null;
		try {
			ucode = uniqueNumId(5);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			ucode = "0";
		}
		return Long.parseLong(ucode);
	}

	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}