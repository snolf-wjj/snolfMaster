package com.snolf.system.web.restcontroller;

import com.alibaba.fastjson.JSON;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.model.SysUser;
import com.snolf.system.model.SysUserRole;
import com.snolf.system.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
	@RequiresPermissions("/system/rest/user/list")
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

	@RequiresPermissions("/system/rest/user/add")
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

	@RequiresPermissions("/system/rest/user/delete")
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

	@RequiresPermissions("/system/rest/user/batchDelete")
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

	@RequiresPermissions("/system/rest/user/edit")
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
	@RequiresPermissions("/system/rest/user/updateStatus")
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

	@RequiresPermissions("/system/rest/user/assignUserRole")
	@RequestMapping(value = "/assignUserRole")
	@ResponseBody
	public ResponseResult<String> assignUserRole(String dataParam, String userId) {
		try {
			List<SysUserRole> dataParams = JSON.parseArray(dataParam, SysUserRole.class);
			sysUserService.assignUserRole(dataParams, userId);
			return ResponseUtil.success("操作成功");
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
}