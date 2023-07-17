package com.dig.blog.app.exceptions;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {


	public ApiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	public ApiException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
