package com.snolf.system.web.restcontroller;

import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.model.SysProject;
import com.snolf.system.service.SysProjectService;
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
@RequestMapping("system/rest/project")
public class SysProjectRestController extends BaseController{
	public static Logger LOGGER = LoggerFactory.getLogger(SysProjectRestController.class);
	
	@Resource
	private SysProjectService sysProjectService;
	
	/**
	 * 项目列表
	 * @author wangjunjie
	 * @date 2017/9/4 15:03
	 */
	@RequiresPermissions("/system/rest/project/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<SysProject>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<SysProject> page = null;
		try {
			page = sysProjectService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/project/listAll")
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public ResponseResult<List<SysProject>> listAll(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		List<SysProject> dataList = null;
		try {
			dataList = sysProjectService.queryListAll(paramsMap);
			return ResponseUtil.success(dataList);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	/**
	 * 查询项目详情
	 * @author wangjunjie
	 * @date 2017/9/4 15:04
	 */
	@RequiresPermissions("/system/rest/project/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<SysProject> get(@RequestParam(required=true) Integer id) {
		try {
			SysProject paramData = new SysProject();
			paramData.setId(id);
			SysProject resultData = sysProjectService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 添加权限资源
	 * @author wangjunjie
	 * @date 2017/9/4 15:04
	 */
	@RequiresPermissions("/system/rest/project/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(SysProject dept) {
		try {
			dept.setCreateUser("未知");
			int result = sysProjectService.insert(dept);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/project/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			int result = sysProjectService.delete(id);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/project/batchDelete")
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public ResponseResult<String> batchDelete(String ids) {
		try {
			ValidateUtil.paramRequired(ids, "参数不能为空");
			int result = sysProjectService.batchDelete(ids);
			if (result > 0) {
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/system/rest/project/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(SysProject dept) {
		try {
			int result = sysProjectService.update(dept);
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