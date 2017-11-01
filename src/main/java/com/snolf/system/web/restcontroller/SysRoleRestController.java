package com.snolf.system.web.restcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.model.SysRole;
import com.snolf.system.model.SysRoleAuthority;
import com.snolf.system.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system/rest/role")
public class SysRoleRestController extends BaseController{
	public static Logger LOGGER = LoggerFactory.getLogger(SysRoleRestController.class);
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 角色列表
	 * @author wangjunjie
	 * @date 2017/6/23 16:32
	 */
	@RequiresPermissions("/system/rest/role/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<SysRole>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<SysRole> page = null;
		try {
			page = sysRoleService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}
	/**
	 * 查询角色详情
	 * @author wangjunjie
	 * @date 2017/7/25 16:41
	 */
	@RequiresPermissions("/system/rest/role/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<SysRole> get(@RequestParam(required=true) String id) {
		try {
			SysRole paramData = new SysRole();
			paramData.setId(id);
			SysRole resultData = sysRoleService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 添加角色
	 * @author wangjunjie
	 * @date 2017/7/25 14:56
	 */
	@RequiresPermissions("/system/rest/role/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(SysRole paramEntity) {
		try {
			paramEntity.setCreateUser("未知");
			int result = sysRoleService.insert(paramEntity);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/role/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			if ("0".equals(id)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
			int result = sysRoleService.delete(id);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/role/batchDelete")
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public ResponseResult<String> batchDelete(String ids) {
		try {
			ValidateUtil.paramRequired(ids, "参数不能为空");
			int result = sysRoleService.batchDelete(ids);
			if (result > 0) {
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/role/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(SysRole paramEntity) {
		try {
			int result = sysRoleService.update(paramEntity);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 根据角色id获取权限列表
	 * @param id
	 * @return
	 */
	@RequiresPermissions("/system/rest/role/getRoleAuth")
	@RequestMapping(value = "/getRoleAuth")
	@ResponseBody
	public ResponseResult<List<SysRoleAuthority>> getRoleAuth(String id) {
		try {
			List<SysRoleAuthority> roleAuthList = sysRoleService.queryRoleAuthList(id);
			return ResponseUtil.success(roleAuthList);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 分配角色权限
	 * @param dataParam
	 * @return
	 */
	@RequiresPermissions("/system/rest/role/assignRoleAuth")
	@RequestMapping(value = "/assignRoleAuth")
	@ResponseBody
	public ResponseResult<String> assignRoleAuth(String dataParam, String roleId) {
		try {
			List<SysRoleAuthority> dataParams = JSON.parseArray(dataParam, SysRoleAuthority.class);
			sysRoleService.assignRoleAuth(dataParams, roleId);
			return ResponseUtil.success("操作成功");
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/role/getRoleAll")
	@RequestMapping(value = "/getRoleAll")
	@ResponseBody
	public ResponseResult<JSONArray> getRoleAll(HttpServletRequest request) {
		try {
			Map<String,Object> paramsMap = getParameters(request);
			JSONArray treeJson = null;
			treeJson = sysRoleService.queryListAll(paramsMap);
			return ResponseUtil.success(treeJson);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

}