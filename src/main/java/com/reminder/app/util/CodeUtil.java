package com.reminder.app.util;

public class CodeUtil {

	private CodeUtil() {
	}

	public static boolean isTrue(Boolean value) {
		return isNull(value) || not(value) ? false : true;
	}

	public static <T> boolean isNull(T value) {
		return value == null;
	}

	public static boolean not(boolean value) {
		return !value;
	}
}
