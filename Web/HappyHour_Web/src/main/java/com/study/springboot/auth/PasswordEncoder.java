package com.study.springboot.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static PasswordEncoder getInstance() { return new PasswordEncoder(); }
	
	public PasswordEncoder() { }
	
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
