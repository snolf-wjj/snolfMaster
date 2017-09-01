package com.snolf.common;

public class Contents {
	
	public enum imgType {
		IMG_HOUSE("1", "出租屋图片类型"),
		IMG_PHOTO("2", "人员头像")
		;
		
		private String code;
		private String value;
		private imgType(String code, String value) {
			this.code=code;
			this.value=value;
		}
		public String getCode() {
			return code;
		}
		public String getValue() {
			return value;
		}
	}
}
