package com.healthcare.admin.dao;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.security.Role;

public interface RoleDAO extends CrudRepository<Role, Integer>{
	
	Role findByName(String name);
	
}
