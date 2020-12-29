package com.reminder.app.exception;

import com.reminder.app.model.type.ResponseCodeType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainException extends RuntimeException {

	private static final long serialVersionUID = -5040066216715701491L;

	private final ResponseCodeType responseCodeType;
	private final String viewMessage;

}
