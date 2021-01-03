package com.reminder.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.reminder.app.domain.UserDetailEntity;

public interface UserDetailRepository extends CrudRepository<UserDetailEntity, String> {
	public List<UserDetailEntity> findByEmail(String email);

	@Query(value = "select * from user_detail e where e.first_name like %:searchText% or e.last_name like %:searchText%", nativeQuery = true)
	public List<UserDetailEntity> findAllUsersByKeyword(
	        @Param("searchText") String searchText);
}
