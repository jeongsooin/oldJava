package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/list").permitAll()
		    .antMatchers("/upload/**").permitAll()
		    .antMatchers("/FileUpload/**").permitAll()
		    .antMatchers("/views/**").permitAll()
			.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
			.antMatchers("/modal/**").permitAll();
		
//		http.authorizeRequests()
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.anyRequest().authenticated();
		
		http.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/j_spring_security_check")
		.failureUrl("/loginError")
		.failureHandler(authenticationFailureHandler)
		.usernameParameter("j_username")
		.passwordParameter("j_password")
		.permitAll();
	
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/main")
			.permitAll();
	
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/signIn", "/signUp", "/androidDB", "/app", "/getDeviceId",
				 "/getReservationOptions", "/image", "/insertReservationData",
				 "/mainPage", "/sendFCM", "/updateDeviceId", "/updateInfo", "/updatePassword",
				 "/CeoClause", "/hwClause", "/signUp_Ceo", "/signUp_User", "/signUpMain");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select name as userName, password, enabled"
				            + " from user_list where name = ?")
		.authoritiesByUsernameQuery("select name as userName, authority "
				              + " from user_list where name = ?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}
