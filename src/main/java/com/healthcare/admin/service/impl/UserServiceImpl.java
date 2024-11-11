package com.healthcare.admin.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.RoleDAO;
import com.healthcare.admin.dao.UserDAO;
import com.healthcare.admin.domain.User;
import com.healthcare.admin.domain.security.UserRole;
import com.healthcare.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userDAO.findByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.save(user);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		// TODO Auto-generated method stub
		User localUser = userDAO.findByEmail(user.getEmail());
		if(localUser!=null) {
			LOG.info("user {} already exists. Nothing will be done.",user.getEmail());
		}else {
			
			for(UserRole ur : userRoles) {
				
				roleDAO.save(ur.getRole());
				
			}
			//One to Many Connect
			user.getUserRoles().addAll(userRoles);
			localUser = userDAO.save(user);
			
		}
		return localUser;
	}

	@Override
	public User findById(Long userId) {
		// TODO Auto-generated method stub
		return userDAO.findById(userId).get();
	}

	
	
}
