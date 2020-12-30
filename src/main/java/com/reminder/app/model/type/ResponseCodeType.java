package com.reminder.app.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeType {
	SUCCESS("00", "success"),
	NO_DATA_FOUND("01", "No Data Found"),
	LOGIN_FAILED("02", "Invalid Username/Password"),
	USERNAME_ALREADY_EXISTS("03", "Username already registered"),
	EMAIL_ALREADY_EXISTS("03", "Email already registered"),
	;

	private final String responseCode;
	private final String responseMessage;
}
