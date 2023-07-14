package com.dig.blog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dig.blog.app.entities.User;
import com.dig.blog.app.exceptions.ResourceNotFoundException;
import com.dig.blog.app.repository.UserRepo;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	User user =	userrepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user", "email :"+username, 0));
		return user;
	}

}
