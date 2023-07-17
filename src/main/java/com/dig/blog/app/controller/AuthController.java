package com.dig.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dig.blog.app.payloads.JwtAuthRequest;
import com.dig.blog.app.payloads.JwtAuthResponse;
import com.dig.blog.app.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userdetailsservice;
    
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		this.autentication(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userdetailsservice.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void autentication(String username, String password) throws Exception {
		// TODO Auto-generated method stub

		UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(username,password);
		

		try {
			
			this.authenticationManager.authenticate(userPassAuthToken);

			
		} catch (BadCredentialsException ex) {
			// TODO: handle exception
			System.out.println("invalid details !!");
			throw new Exception("Invalid username or password !!!");
		}
	}
}
