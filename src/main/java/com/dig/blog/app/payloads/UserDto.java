package com.dig.blog.app.payloads;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.dig.blog.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	int id;
	@NotEmpty
	@Size(min = 4,message = "The size of name must be 4 char or more than it is !!!")
	String name;
	@NotEmpty
	@Email(message = "email is not valid !!!")
	String email;
	@NotEmpty
	@Size(min = 4,max = 10,message = "the password is not valid")
	//@Pattern(regexp = "")
	//@JsonIgnore
	String password;
	@NotEmpty
	String about;
	
	Set<RoleDto> roles = new HashSet<>();
}
