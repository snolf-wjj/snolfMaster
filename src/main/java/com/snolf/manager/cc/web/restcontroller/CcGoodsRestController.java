package com.snolf.manager.cc.web.restcontroller;

import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.model.CcGoods;
import com.snolf.manager.cc.service.CcGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/manager/cc/rest/goods")
public class CcGoodsRestController extends BaseController {

	@Resource
	private CcGoodsService ccGoodsService;

	@RequiresPermissions("/manager/cc/rest/goods/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<CcGoods>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<CcGoods> page = null;
		try {
			page = ccGoodsService.queryList(paramsMap);
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
	@RequiresPermissions("/manager/cc/rest/goods/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<CcGoods> get(@RequestParam(required=true) int id) {
		try {
			CcGoods paramData = new CcGoods();
			paramData.setId(id);
			CcGoods resultData = ccGoodsService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	
	@RequiresPermissions("/manager/cc/rest/goods/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(CcGoods paramData) {
		try {
			paramData.setCreateUser(getLoginUserName());
			int result = ccGoodsService.insert(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/goods/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(CcGoods paramData) {
		try {
			int result = ccGoodsService.update(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/goods/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			if ("0".equals(id)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
			int result = ccGoodsService.delete(id);
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
