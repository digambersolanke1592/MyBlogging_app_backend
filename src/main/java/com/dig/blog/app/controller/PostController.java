package com.dig.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dig.blog.app.config.AppConstant;
import com.dig.blog.app.entities.Post;
import com.dig.blog.app.entities.User;
import com.dig.blog.app.payloads.ApiResponse;
import com.dig.blog.app.payloads.PostDto;
import com.dig.blog.app.payloads.PostResponse;
import com.dig.blog.app.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")//for number of parameter increase use another pattern
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postdto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
	
		PostDto postdtooo = postService.createPostDto(postdto, userId, categoryId);
		return new ResponseEntity<>(postdtooo,HttpStatus.CREATED);
	}
	
	@GetMapping("/post/user/{id}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable ("id") Integer userId){
		
	List<PostDto> postdtos = postService.getPostByUser(userId);
		return new ResponseEntity<>(postdtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/category/{id}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable ("id") Integer categoryId){
		
	List<PostDto> postDtos = postService.getPostByUser(categoryId);
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
		}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		
	List<PostDto> postDtos = postService.getAllPost();
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
		}
	
	@GetMapping("/page")
	public ResponseEntity<List<PostDto>> getAllPostWithPagination(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
			){
		
	    List<PostDto> postDtos = postService.getAllPostWithPagination(pageNumber,pageSize);
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
		}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer postId){
	PostDto postDto1 = postService.getPostById(postId);
	return ResponseEntity.ok(postDto1);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable("id") Integer postId){
		postService.DeletePost(postId);
		return new ResponseEntity<>(new ApiResponse("This post is deleted successfully", true),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable("id") Integer postId){
		PostDto postDto1 =  this.postService.updatePostDto(postdto, postId);
		return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
		
	}
	//get all post with pagination in details 
	@GetMapping("/detailsPost")
	public ResponseEntity<PostResponse> getAllPostWithPaginationInDetails(//here 'required' field is used for optional data 
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
			){//above fields are common when we used in category or user if we make them constant then we used them and reduce the code 
		 PostResponse postResp = postService.getAllPostWithPaginationAndDetails(pageNumber,pageSize, sortBy,sortDir);
		 return new ResponseEntity<>(postResp,HttpStatus.OK);
	}
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
	List<PostDto> posts = postService.searchPosts(keyword);
	return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
//	@PostMapping("/")
//	public String createList(@RequestBody List<User> user) {
//		
//		//System.out.println(user);
//		
//		User user1 = new User();
//		for(User l:user) {
//			
//			
//		//	System.out.println(l.toString());
//			int id = l.getId();
//			user1.setId(id);
//		}
//		
//		System.out.println(user1.getId()); 
//		
//		return "printing List";
//	}
	
}
