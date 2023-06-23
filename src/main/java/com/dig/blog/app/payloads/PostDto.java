package com.dig.blog.app.payloads;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private int postId;
	private String title;
	private String content;

	private String imageName;
	private Date addedDate;

	private CategoryDto category;     //if inplace of categoryDto/userDto we used only category then method recursion occurs infinite no of times
	private UserDto user;      //same here
}
