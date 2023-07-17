package com.dig.blog.app.service;

import java.util.List;

import com.dig.blog.app.payloads.UserDto;

public interface UserService {
	
	//for registration of new user
	UserDto registerNewuser(UserDto user);

	UserDto createUser(UserDto user);
	
    UserDto	updateuser(UserDto userdto,Integer userId);
    
    UserDto getUserById(Integer userId);
    
    List<UserDto> getAllUsers();
    
    void deleteUser(Integer userId);
}
