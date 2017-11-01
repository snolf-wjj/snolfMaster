package com.snolf.system.web.restcontroller;

import com.alibaba.fastjson.JSONArray;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.model.SysDept;
import com.snolf.system.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("system/rest/dept")
public class SysDeptRestController extends BaseController{
	public static Logger LOGGER = LoggerFactory.getLogger(SysDeptRestController.class);
	
	@Resource
	private SysDeptService sysDeptService;
	
	/**
	 * 部门列表
	 * @author wangjunjie
	 * @date 2017/6/23 16:32
	 */
	@RequiresPermissions("/system/rest/dept/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<SysDept>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<SysDept> page = null;
		try {
			page = sysDeptService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}
	/**
	 * 查询部门详情
	 * @author wangjunjie
	 * @date 2017/7/25 16:41
	 */
	@RequiresPermissions("/system/rest/dept/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<SysDept> get(@RequestParam(required=true) String id) {
		try {
			SysDept paramData = new SysDept();
			paramData.setId(id);
			SysDept resultData = sysDeptService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 添加部门
	 * @author wangjunjie
	 * @date 2017/7/25 14:56
	 */
	@RequiresPermissions("/system/rest/dept/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(SysDept dept) {
		try {
			dept.setCreateUser("未知");
			int result = sysDeptService.insert(dept);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	@RequiresPermissions("/system/rest/dept/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			if ("0".equals(id)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
			int result = sysDeptService.delete(id);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/dept/batchDelete")
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public ResponseResult<String> batchDelete(String ids) {
		try {
			ValidateUtil.paramRequired(ids, "参数不能为空");
			int result = sysDeptService.batchDelete(ids);
			if (result > 0) {
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/dept/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(SysDept dept) {
		try {
			if ("0".equals(dept.getId())) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg() + ",超级账户无法修改！");
			}
			SysDept checkDept = new SysDept();
			checkDept.setId(dept.getId());

			int result = sysDeptService.update(dept);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	@RequiresPermissions("/system/rest/dept/tree")
	@RequestMapping(value = "/tree")
	@ResponseBody
	public ResponseResult<JSONArray> tree(String id) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			JSONArray treeJson = sysDeptService.queryListAll(map);
			return ResponseUtil.success(treeJson);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
}