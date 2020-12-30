package com.reminder.app.util;

import java.util.List;

public class CodeUtil {

	private CodeUtil() {
	}

	public static boolean isTrue(Boolean value) {
		return isNotNull(value) && Boolean.TRUE == value;
	}

	public static <T> boolean isNull(T value) {
		return value == null;
	}

	public static boolean not(boolean value) {
		return !value;
	}

	public static <T> boolean isNotNull(T value) {
		return not(isNull(value));
	}

	public static <T> boolean hasElemets(List<T> value) {
		return isNotNull(value) && not(value.isEmpty());
	}
}
