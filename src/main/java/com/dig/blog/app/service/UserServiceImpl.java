package com.dig.blog.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dig.blog.app.entities.User;
import com.dig.blog.app.exceptions.ResourceNotFoundException;
import com.dig.blog.app.payloads.UserDto;
import com.dig.blog.app.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser( userDto);
		User saveUser = userRepo.save(user);
	//	UserDto userdto = this.userToDto(saveUser);
    //  return userdto;
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateuser(UserDto userdto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user1 = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId) );
	    user1.setId(userdto.getId());
	    user1.setName(userdto.getName());
	    user1.setEmail(userdto.getEmail());
	    user1.setPassword(userdto.getPassword());
	    user1.setAbout(userdto.getAbout());
	    
	    User updatedUser =  userRepo.save(user1);
	    UserDto userdto1 =  this.userToDto(updatedUser);
		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
	   User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId) );
	   UserDto userdto =  this.userToDto(user);
	   return userdto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
	List<User> users = userRepo.findAll();
	
    List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId) );
	userRepo.delete(user);
	}
	 
	//converting dto to user because we used UserDto as parameter not User so it necessary
	public User dtoToUser (UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class) ;  //by using modelmapper
		return user;
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
		
	}
	//converting user to dto 
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		return userDto; 
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		return userDto;
		
	}
}
