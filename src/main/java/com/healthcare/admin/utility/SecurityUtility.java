package com.healthcare.admin.utility;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
	
	private static final String SALT = "health-care-spring";
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(17, new SecureRandom(SALT.getBytes()));
	}
	
}
