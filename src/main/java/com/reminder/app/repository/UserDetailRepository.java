package com.reminder.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reminder.app.domain.UserDetailEntity;

public interface UserDetailRepository extends CrudRepository<UserDetailEntity, String> {
	public List<UserDetailEntity> findByEmail(String email);
}
