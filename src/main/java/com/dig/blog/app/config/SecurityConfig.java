package com.dig.blog.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dig.blog.app.security.CustomeUserDetailService;
import com.dig.blog.app.security.JwtAuthenticationEntryPoint;
import com.dig.blog.app.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //for giving access of api to admin or public 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomeUserDetailService customeuserdetailservice;
	
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	
//		http
//		.csrf()
//		.disable()                                   //it is for basic authentication
//		.authorizeHttpRequests()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
//	}
	
	protected void configure(HttpSecurity http) throws Exception {
	
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/auth/**").permitAll()  //this url is now public no security for it
		.antMatchers(HttpMethod.GET).permitAll()  //all get api are public
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customeuserdetailservice).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	

}
