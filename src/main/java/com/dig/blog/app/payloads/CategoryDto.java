package com.dig.blog.app.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	int categoryId;
	@NotBlank
	@Size(min = 4,message = "the title size should be 4 characters !!!")
	String categoryTitle;
	@NotBlank
	@Size(min = 10,message = "the description size should be 10 characters !!1")
	String categoryDescription;
	
}
