package com.snolf.manager.cc.web.restcontroller;

import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.model.CcMember;
import com.snolf.manager.cc.service.CcMemberService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/manager/cc/rest/member")
public class CcMemberRestController extends BaseController {

	@Resource
	private CcMemberService ccMemberService;

	@RequiresPermissions("/manager/cc/rest/member/list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<CcMember>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<CcMember> page = null;
		try {
			page = ccMemberService.queryList(paramsMap);
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
	@RequiresPermissions("/manager/cc/rest/member/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<CcMember> get(@RequestParam(required=true) String id) {
		try {
			CcMember paramData = new CcMember();
			paramData.setId(id);
			CcMember resultData = ccMemberService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
	
	@RequiresPermissions("/manager/cc/rest/member/add")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseResult<String> add(CcMember paramData) {
		try {
			paramData.setCreateUser(getLoginUserName());
			int result = ccMemberService.insert(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/member/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(CcMember paramData) {
		try {
			int result = ccMemberService.update(paramData);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/manager/cc/rest/member/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(String id) {
		try {
			ValidateUtil.paramRequired(id, "id不能为空");
			if ("0".equals(id)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
			int result = ccMemberService.delete(id);
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
