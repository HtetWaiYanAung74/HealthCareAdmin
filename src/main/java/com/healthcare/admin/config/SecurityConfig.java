/**
 * @author Thiha
 */

package com.healthcare.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.healthcare.admin.service.impl.UserSecurityService;
import com.healthcare.admin.utility.SecurityUtility;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private BCryptPasswordEncoder passwordEncoder() {		
		return SecurityUtility.passwordEncoder();
	}
	
	private static final String[] ADMIN_MATCHERS = {
		"/",
		"/index",
		"/patient",
		"/patient/**",
		"/doctor/**",
		"/room/**",
		"/appointment/**"
	};
	
	private static final String[] PUBLIC_MATCHERS = {
		
		"/login",
		"/assets/**",
		"/assets/webfonts/**"
		
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(ADMIN_MATCHERS).hasRole("ADMIN")
		.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated()
		.and()
		.exceptionHandling().accessDeniedPage("/error-403");
		http
		.csrf().disable().cors().disable()
		.formLogin()
		.usernameParameter("email")
		.failureUrl("/login?error")
		.defaultSuccessUrl("/")
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
		.and()
		.rememberMe();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
