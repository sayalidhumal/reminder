package com.reminder.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserData {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
}
