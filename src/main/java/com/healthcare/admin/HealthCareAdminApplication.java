package com.healthcare.admin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.healthcare.admin.domain.User;
import com.healthcare.admin.domain.security.Role;
import com.healthcare.admin.domain.security.UserRole;
import com.healthcare.admin.service.UserService;
import com.healthcare.admin.utility.SecurityUtility;

@SpringBootApplication
public class HealthCareAdminApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(HealthCareAdminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		User user1 = new User();
		user1.setUsername("aa");
		user1.setEmail("aa@gmail.com");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("aa"));
		
		Set<UserRole> userRoles = new HashSet<>();
		
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_ADMIN");
		
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
	}

}
