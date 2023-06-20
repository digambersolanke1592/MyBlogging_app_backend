package com.dig.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dig.blog.app.payloads.UserDto;
import com.dig.blog.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
	UserDto userdto = userService.createUser(userDto);
		return new ResponseEntity<>(userdto,HttpStatus.OK);
		
	}
}
