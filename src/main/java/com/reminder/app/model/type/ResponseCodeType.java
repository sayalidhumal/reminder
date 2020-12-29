package com.reminder.app.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeType {
	SUCCESS("00", "success"), 
	NO_DATA_FOUND("01", "No Data Found"),
	LOGIN_FAILED("02", "Invalid Username/Password");

	private final String responseCode;
	private final String responseMessage;
}
