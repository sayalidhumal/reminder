package com.reminder.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.reminder.app.domain.UserDetailEntity;

public interface UserDetailRepository extends CrudRepository<UserDetailEntity, String>{

}
