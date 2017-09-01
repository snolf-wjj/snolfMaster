package com.snolf.common.contact;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/22
 * Time: 16:01
 */
public class SystemStatusCode {
	public static final String CODE = "code";

	public static final String MSG = "msg";

	public enum sysMsg {
		L_0000("0000", "操作成功"), L_0001("0001", "操作失败"), L_0002("0002",
				"参数错误"), L_0003("0003", "服务器请求异常"), L_0004("0004", "参数缺失");

		private String code;
		private String msg;

		sysMsg(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}