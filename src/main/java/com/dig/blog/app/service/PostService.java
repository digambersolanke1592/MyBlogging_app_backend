package com.dig.blog.app.service;

import java.util.List;
import com.dig.blog.app.payloads.PostDto;
import com.dig.blog.app.payloads.PostResponse;


public interface PostService {

	PostDto createPostDto(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePostDto(PostDto postDto, Integer postId);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto>getAllPost();
	
	List<PostDto> getAllPostWithPagination(Integer pageNumber,Integer pageSize);
	//with all details
	PostResponse getAllPostWithPaginationAndDetails(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	void DeletePost(Integer postId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto>  getPostByCategory(Integer categoryId);
	
	//if this api is not working more than one time then it is hibernate version problem it is not work hibernate-core 5.6.5-final above version
	//and also there is springboot jpa version problem
	List<PostDto> searchPosts(String keyword);
	
	
	
	
	
	
}
