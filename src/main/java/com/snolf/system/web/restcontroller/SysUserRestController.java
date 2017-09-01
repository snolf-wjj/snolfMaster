package com.snolf.system.web.restcontroller;

import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.system.model.SysUser;
import com.snolf.system.service.SysUserService;
import com.snolf.util.common.ValidateUtil;
import com.snolf.util.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("system/rest/user")
public class SysUserRestController extends BaseController {
	public static Logger LOGGER = LoggerFactory.getLogger(SysUserRestController.class);
	
	@Resource
	private SysUserService sysUserService;

	/**
	 * 用户列表
	 * @author wangjunjie
	 * @date 2017/6/23 16:32
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<SysUser>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<SysUser> page = null;
		try {
			page = sysUserService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(SysUser paramEntity) {
		try {
			paramEntity.setCreateUser("未知");
			int result = sysUserService.insert(paramEntity);
			if (result == 1) {
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			if ("0".equals(id)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), "超级管理员无法删除");
			}
			int result = sysUserService.delete(id);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public ResponseResult<String> batchDelete(String ids) {
		try {
			ValidateUtil.paramRequired(ids, "参数不能为空");
			int result = sysUserService.batchDelete(ids);
			if (result > 0) {
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(SysUser paramEntity) {
		try {
			int result = sysUserService.update(paramEntity);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<SysUser> get(String id) {
		try {
			SysUser paramData = new SysUser();
			paramData.setId(id);
			SysUser resultData = sysUserService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	/**
	 * 修改会员状态
	 * @author wangjunjie
	 * @date 2017/8/28 15:54
	 */
	@RequestMapping("updateStatus")
	@ResponseBody
	public ResponseResult<String> updateStatus(String id, Integer userStatus) {
		try {
			SysUser paramData = new SysUser();
			paramData.setId(id);
			paramData.setUserStatus(userStatus);
			int result = sysUserService.updateStatus(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
}