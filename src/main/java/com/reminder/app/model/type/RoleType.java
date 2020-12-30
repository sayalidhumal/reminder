package com.reminder.app.model.type;

import static com.reminder.app.util.CodeUtil.areEqual;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
	ADMIN("Admin", true),
	USER("User", false),
	CONTROLLER("Controller", false);

	public static Optional<RoleType> byName(String value) {
		for (RoleType role : RoleType.values()) {
			if (areEqual(role.name, value)) {
				return Optional.of(role);
			}
		}

		return Optional.empty();
	}

	private String name;
	private boolean isSuperUser;
}
