package com.reminder.repository;

import org.springframework.data.repository.CrudRepository;

import com.reminder.domain.UserDetail;

public interface UserDetailRepository extends CrudRepository<UserDetail, String>{

}
