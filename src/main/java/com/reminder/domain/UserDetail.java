package com.reminder.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;

@Entity(name = "USER_DETAIL")
@Builder
public class UserDetail implements Serializable {

	private static final long serialVersionUID = -451088116994619500L;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Id
	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;
}
