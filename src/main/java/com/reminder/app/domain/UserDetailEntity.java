package com.reminder.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "USER_DETAIL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailEntity  {

	@Column(name = "EMAIL")
	public String email;

	@Column(name = "FIRST_NAME")
	public String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Id
	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;
}
