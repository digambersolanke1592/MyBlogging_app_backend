package com.dig.blog.app.service;

import java.util.List;

import com.dig.blog.app.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
    UserDto	updateuser(UserDto userdto,Integer userId);
    
    UserDto getUserById(Integer userId);
    
    List<UserDto> getAllUsers();
    
    void deleteUser(Integer userId);
}
