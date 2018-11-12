package com.snolf.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片地址上传工具类
 * 
 * @Title: ImageUtil.java
 * @Description:
 * @author Hanzhonghua
 * @date 2016年10月19日下午2:01:32
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ImageUtil {

	/**
	 * 上传图片
	 * @Title: uploadImg
	 * @Description: <功能详细描述>
	 * @author wangjunjie
	 * @date 2017年6月4日 下午7:29:46
	 * @param file 上传文件
	 * @param type 文件类型 1：出租房屋 2：头像
	 * @param fileName 文件名称
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Boolean upload(MultipartFile file, String type, String filePath, String fileName) {
		if(null == file) {
			return false;
		}
		// 创建文件目录
		File savedir = new File(filePath);
		// 如果目录不存在就创建
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 将上传的文件信息保存到相应的文件目录里
		try {
			file.transferTo(new File(filePath + fileName));
			return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}