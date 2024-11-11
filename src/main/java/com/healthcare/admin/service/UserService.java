package com.healthcare.admin.service;

import java.util.Set;

import com.healthcare.admin.domain.User;
import com.healthcare.admin.domain.security.UserRole;

public interface UserService {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User saveUser(User user);
	
	User createUser(User user, Set<UserRole> userRoles);
	
	User findById(Long userId);
}
