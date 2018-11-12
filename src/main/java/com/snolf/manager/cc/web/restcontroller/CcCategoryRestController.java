package com.snolf.manager.cc.web.restcontroller;

import com.alibaba.fastjson.JSONArray;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.model.CcCategory;
import com.snolf.manager.cc.service.CcCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/manager/cc/rest/category")
public class CcCategoryRestController extends BaseController {

	@Resource
	private CcCategoryService ccCategoryService;

	@RequiresPermissions("/manager/cc/rest/category/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<CcCategory>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<CcCategory> page = null;
		try {
			page = ccCategoryService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 获取消费商详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions("/manager/cc/rest/category/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<CcCategory> get(@RequestParam(required=true) String id) {
		try {
			CcCategory paramData = new CcCategory();
			paramData.setId(Integer.valueOf(id));
			CcCategory resultData = ccCategoryService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	
	@RequiresPermissions("/manager/cc/rest/category/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(CcCategory paramData) {
		try {
			paramData.setCreateUser(getLoginUserName());
			int result = ccCategoryService.insert(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/category/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(CcCategory paramData) {
		try {
			int result = ccCategoryService.update(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/category/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			if ("0".equals(id)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
			int result = ccCategoryService.delete(id);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/category/tree")
	@RequestMapping(value = "/tree")
	@ResponseBody
	public ResponseResult<JSONArray> getAuthTree(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		try {
			JSONArray treeJson = ccCategoryService.queryListAll(paramsMap);
			return ResponseUtil.success(treeJson);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}



}
