package com.snolf.manager.cc.web.restcontroller;

import com.snolf.base.BaseController;
import com.snolf.common.ImageUtil;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.UUIDUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Title: ImageRestController </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author Wjj
 * @CreateDate 2018/7/30 18:46
 */
@Controller
@RequestMapping("/manager/cc/rest/image")
public class ImageRestController extends BaseController{

	@Value("${img.path}")
	private String imgPath;

	@RequiresPermissions("/manager/cc/rest/image/upload")
	@RequestMapping(value = "/upload")
	@ResponseBody
	public ResponseResult<String> uploadGoodImg(MultipartFile file) {

		if (null != file) {
			String fileName = file.getOriginalFilename();
			String newFileName = UUIDUtil.getUUID32Str() + "." +  fileName.substring(fileName.lastIndexOf(".")+1);
			boolean b = ImageUtil.upload(file, null, imgPath, newFileName);
			if (b) {
				return ResponseUtil.success(newFileName);
			}
		}
		return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
	}

	@RequiresPermissions("/manager/cc/rest/image/getImg")
	@RequestMapping(value = "/getImg")
	public void getImg(@RequestParam(required=true)String fileName, HttpServletResponse response) {
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(imgPath + fileName);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fis.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
