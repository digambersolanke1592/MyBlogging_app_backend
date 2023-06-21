package com.dig.blog.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dig.blog.app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
	
		 String message = ex.getMessage();
		 ApiResponse apiresponse = new ApiResponse(message,false);
		 return new ResponseEntity<>(apiresponse,HttpStatus.NOT_FOUND);
	 }
}
