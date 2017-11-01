package com.snolf.system.web.restcontroller;

import com.alibaba.fastjson.JSONArray;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.model.SysAuthority;
import com.snolf.system.service.SysAuthorityService;
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
@RequestMapping("system/rest/authority")
public class SysAuthorityRestController extends BaseController{
	public static Logger LOGGER = LoggerFactory.getLogger(SysAuthorityRestController.class);
	
	@Resource
	private SysAuthorityService sysAuthorityService;
	
	/**
	 * 权限资源列表
	 * @author wangjunjie
	 * @date 2017/9/4 15:03
	 */
	@RequiresPermissions("/system/rest/authority/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<SysAuthority>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<SysAuthority> page = null;
		try {
			page = sysAuthorityService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}
	/**
	 * 查询权限资源详情
	 * @author wangjunjie
	 * @date 2017/9/4 15:04
	 */
	@RequiresPermissions("/system/rest/authority/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<SysAuthority> get(@RequestParam(required=true) Integer id) {
		try {
			SysAuthority paramData = new SysAuthority();
			paramData.setId(id);
			SysAuthority resultData = sysAuthorityService.query(paramData);
			if (null != resultData) {
				return ResponseUtil.success(resultData);
			} else {
				return  ResponseUtil.error(SystemStatusCode.sysMsg.L_0002.getCode(), "不存在该权限");
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 添加权限资源
	 * @author wangjunjie
	 * @date 2017/9/4 15:04
	 */
	@RequiresPermissions("/system/rest/authority/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(SysAuthority dept) {
		try {
			dept.setCreateUser("未知");
			int result = sysAuthorityService.insert(dept);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/authority/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			int result = sysAuthorityService.delete(id);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/authority/batchDelete")
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public ResponseResult<String> batchDelete(String ids) {
		try {
			ValidateUtil.paramRequired(ids, "参数不能为空");
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), "权限数据过于重要，请逐个操作！");
//			int result = sysAuthorityService.batchDelete(ids);
//			if (result > 0) {
//				return ResponseUtil.success("操作成功");
//			} else {
//				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
//			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/authority/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(SysAuthority dept) {
		try {
			int result = sysAuthorityService.update(dept);
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
	 * 选择上级权限时用
	 * @param request
	 * @return
	 */
	@RequiresPermissions("/system/rest/authority/listSelect")
	@RequestMapping(value = "/listSelect")
	@ResponseBody
	public ResponseResult<List<SysAuthority>> listSelect(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		List<SysAuthority> dataList = null;
		try {
			dataList = sysAuthorityService.queryListByProject(paramsMap);
			return ResponseUtil.success(dataList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/authority/tree")
	@RequestMapping(value = "/tree")
	@ResponseBody
	public ResponseResult<JSONArray> getAuthTree(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		JSONArray treeJson = null;
		try {
			treeJson = sysAuthorityService.queryListAll(paramsMap);
			return ResponseUtil.success(treeJson);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
}