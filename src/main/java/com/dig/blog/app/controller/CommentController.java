package com.dig.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dig.blog.app.payloads.ApiResponse;
import com.dig.blog.app.payloads.CommentDto;
import com.dig.blog.app.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentService commentservice;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentdto,@PathVariable("postId") Integer postId ){
		
		CommentDto createdcommentdto = commentservice.createComment(commentdto, postId);
		return new ResponseEntity<CommentDto>(createdcommentdto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		commentservice.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfuly!!!", true),HttpStatus.OK);
	}
}
