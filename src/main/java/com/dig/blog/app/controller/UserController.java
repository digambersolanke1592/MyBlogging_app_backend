package com.dig.blog.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dig.blog.app.payloads.ApiResponse;
import com.dig.blog.app.payloads.UserDto;
import com.dig.blog.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
	    UserDto userdto = userService.createUser(userDto);
		return new ResponseEntity<>(userdto,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity <List<UserDto>> getAllUsers(){
		List<UserDto> userDto =  userService.getAllUsers();
		return new ResponseEntity<>(userDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
	    UserDto userdto = userService.getUserById(id);
		return new ResponseEntity<>(userdto,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")   //below this @valid annotation is used to enable the annotations used for validation purpose
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable Integer id){
		
	    UserDto userdto = userService.updateuser(userDto, id);
	    return new ResponseEntity<>(userdto,HttpStatus.OK);
		
	}
	
//	@DeleteMapping("/{id}")//here no need to write @pathvariable because we used "id" in mapping same as in parameter   
//	public String deleteUserById(@PathVariable Integer id){
//		userService.deleteUser(id);
//		return "the user is deleted with id :  "+id;
//	}
	
	@PreAuthorize("hasRole('ADMIN')")   //give the access to only admin for this api
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfuly",true),HttpStatus.OK);
	}
}
