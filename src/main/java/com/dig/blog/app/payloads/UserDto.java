package com.dig.blog.app.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	int id;
	String name;
	String email;
	String password;
	String about;
}
