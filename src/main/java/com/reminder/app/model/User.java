package com.reminder.app.model;

import com.reminder.app.model.type.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;

	@Builder.Default
	private RoleType role = RoleType.USER;
}
