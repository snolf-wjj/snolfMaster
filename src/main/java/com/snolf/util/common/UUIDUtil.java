package com.snolf.util.common;

import java.util.UUID;

public class UUIDUtil {

	public static UUID getUUID() {
		return UUID.randomUUID();
	}

	public static String getUUID36Str() {
		return getUUID().toString();
	}

	public static String getUUID32Str() {
		return getUUID().toString().replaceAll("-", "");
	}
}
