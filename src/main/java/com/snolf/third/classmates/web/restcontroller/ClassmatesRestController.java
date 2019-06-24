package com.snolf.third.classmates.web.restcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.page.PageInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.config.shiro.token.TokenManager;
import com.snolf.third.classmates.model.ClassmatesInfo;
import com.snolf.third.classmates.model.ClassmatesUser;
import com.snolf.third.classmates.service.ClassmatesInfoService;
import com.snolf.third.classmates.service.ClassmatesUserService;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/third/rest/classmates")
public class ClassmatesRestController extends BaseController {
	public static Logger LOGGER = LoggerFactory.getLogger(ClassmatesRestController.class);

	@Resource
	private ClassmatesUserService classmatesUserService;
	@Resource
	private ClassmatesInfoService classmatesInfoService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Map<String, Object>> login(String loginName, String password, HttpServletRequest request) {
		try {
			ValidateUtil.paramRequired(loginName, "登录名不能为空");
			ValidateUtil.paramRequired(password, "密码不能为空");
			ClassmatesUser checkUser = classmatesUserService.login(loginName, password);
			ValidateUtil.paramRequired(checkUser, "账号密码错误");

			ClassmatesInfo paramData = new ClassmatesInfo();
			paramData.setInnerCode(checkUser.getInnerCode());
			ClassmatesInfo infoData = classmatesInfoService.query(paramData);
			String url;
			UsernamePasswordToken token;
			if (null != infoData) {
				// 已填写过信息的账户登录
				url = "/third/classmates/info/list.html";
				token = new UsernamePasswordToken("classmatesInfo", "classmatesInfo");
			} else {
				// 未填写过信息的账户登录
				url = "/third/classmates/form.html";
				token = new UsernamePasswordToken("tongxueluzhanghao", "tongxuelumima");
			}

			token.setRememberMe(Boolean.TRUE);
			SecurityUtils.getSubject().login(token);

			Session session = TokenManager.getSession();
			session.setAttribute("innerCode", checkUser.getInnerCode());
			session.setAttribute("userName", checkUser.getLoginName());
			session.setAttribute("roleType", 1);

			resultMap.put("url", url);
			resultMap.put("message", "登录成功");
			return ResponseUtil.success(resultMap);
		} catch (AccountException e) {
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), e.getMessage());
		}catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}



	/**
	 * 用户列表
	 * @author wangjunjie
	 * @date 2017/6/23 16:32
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseResult<PageInfo<ClassmatesUser>> list(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		PageInfo<ClassmatesUser> page = null;
		try {
			page = classmatesUserService.queryList(paramsMap);
			return ResponseUtil.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequestMapping(value = "/register")
	@ResponseBody
	public ResponseResult<String> register(ClassmatesUser paramEntity) {
		try {
			ClassmatesUser checkData = classmatesUserService.query(paramEntity);
			ValidateUtil.paramRequired(null != checkData , "该账号已存在，请重新输入");

			paramEntity.setCreateUser("自己注册");
			int result = classmatesUserService.insert(paramEntity);
			if (result == 1) {
				return ResponseUtil.success("恭喜您，注册成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/third/rest/classmates/user/delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseResult<String> delete(Long innerCode) {
		try {
			ValidateUtil.paramRequired(innerCode, "id不能为空");
			if ("0".equals(innerCode)) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), "超级管理员无法删除");
			}
			int result = classmatesUserService.delete(innerCode);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/third/rest/classmates/user/batchDelete")
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public ResponseResult<String> batchDelete(String ids) {
		try {
			ValidateUtil.paramRequired(ids, "参数不能为空");
//			int result = classmatesUserService.batchDelete(ids);
//			if (result > 0) {
//				return ResponseUtil.success("操作成功");
//			} else {
//				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
//			}
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), "暂不支持批量删除");
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/third/rest/classmates/user/edit")
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseResult<String> edit(ClassmatesUser paramEntity) {
		try {
			int result = classmatesUserService.update(paramEntity);
			if (result == 1){
				return ResponseUtil.success("操作成功");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	@RequiresPermissions("/third/rest/classmates/user/get")
	@RequestMapping("get")
	@ResponseBody
	public ResponseResult<ClassmatesUser> get(Integer id) {
		try {
			ClassmatesUser paramData = new ClassmatesUser();
			paramData.setId(id);
			ClassmatesUser resultData = classmatesUserService.query(paramData);
			return ResponseUtil.success(resultData);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

//	@RequiresPermissions("/third/rest/classmatesUser/assignUserRole")
//	@RequestMapping(value = "/assignUserRole")
//	@ResponseBody
//	public ResponseResult<String> assignUserRole(String dataParam, String userId) {
//		try {
//			List<SysUserRole> dataParams = JSON.parseArray(dataParam, SysUserRole.class);
//			classmatesUserService.assignUserRole(dataParams, userId);
//			return ResponseUtil.success("操作成功");
//		} catch (Exception e) {
//			return ResponseExceptionUtil.handleException(e);
//		}
//	}

	/**
	 * 添加同学信息信息
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value = "/info/add")
	@ResponseBody
	public ResponseResult<String> addInfo(ClassmatesInfo paramData) {
		try {
			Session session = TokenManager.getSession();
			paramData.setInnerCode((Long) session.getAttribute("innerCode"));
			paramData.setCreateUser((String) session.getAttribute("userName"));


			ClassmatesInfo checkParam = new ClassmatesInfo();
			checkParam.setInnerCode(paramData.getInnerCode());
			ClassmatesInfo checkData = classmatesInfoService.query(checkParam);
			if (null != checkData) {
				classmatesInfoService.delete(checkData.getId());
			}

			int result = classmatesInfoService.insert(paramData);
			if (result == 1) {
				// 账号授权
				UsernamePasswordToken token = new UsernamePasswordToken("classmatesInfo", "classmatesInfo");
				token.setRememberMe(Boolean.TRUE);
				SecurityUtils.getSubject().login(token);

				return ResponseUtil.success("/third/classmates/info/list.html");
			} else {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), SystemStatusCode.sysMsg.L_0001.getMsg());
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 用户信息列表
	 * @author wangjunjie
	 * @date 2017/6/23 16:32
	 */
	@RequestMapping(value = "/info/list")
	@ResponseBody
	public ResponseResult<JSONObject> infoList(HttpServletRequest request) {
		Map<String,Object> paramsMap = getParameters(request);
		JSONObject resultJS = new JSONObject();
		PageInfo<ClassmatesInfo> page = null;
		try {
			page = classmatesInfoService.queryList(paramsMap);
			resultJS.put("data", page);
			Session session = TokenManager.getSession();
			resultJS.put("roleType", session.getAttribute("roleType"));
			return ResponseUtil.success(resultJS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseExceptionUtil.handleException(e);
		}
	}

}