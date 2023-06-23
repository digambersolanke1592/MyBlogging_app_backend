package com.dig.blog.app.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dig.blog.app.entities.Category;
import com.dig.blog.app.entities.Post;
import com.dig.blog.app.entities.User;
import com.dig.blog.app.exceptions.ResourceNotFoundException;
import com.dig.blog.app.payloads.PostDto;
import com.dig.blog.app.payloads.PostResponse;
import com.dig.blog.app.payloads.UserDto;
import com.dig.blog.app.repository.CategoryRepo;
import com.dig.blog.app.repository.PostRepo;
import com.dig.blog.app.repository.UserRepo;
import com.dig.blog.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelMapperr;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPostDto(PostDto postDto,Integer userId,Integer categoryId) {
		
		//user and category comes from below code
	    User user =	this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
	    Category category =	this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        
		// postdto properties map to Post like title,content from PostDto class
		Post post = this.modelMapperr.map(postDto,Post.class);
		
		//set other properties to post from here as realtime like date
	    post.setAddedDate(new Date());
	    post.setImageName("default.png");
	    post.setCategory(category);
	    post.setUser(user);
		
	    Post newPost = this.postrepo.save(post);//post saved in repo
	    
	    return this.modelMapperr.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePostDto(PostDto postDto, Integer postId) {
	    Post post =	this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());
        
        Post post1 = postrepo.save(post);
        PostDto postdto = this.modelMapperr.map(post1,PostDto.class);
       
	    return postdto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	    Post post =	this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
	    return modelMapperr.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> getAllPost() {
	List<Post> allpost = postrepo.findAll();
    List<PostDto> postDtos = allpost.stream().map(post -> this.modelMapperr.map(post,PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}
	
	//getAll post with pagination concept
	public List<PostDto> getAllPostWithPagination(Integer pageNumber,Integer pageSize) {
		
	    Pageable p = PageRequest.of(pageNumber,pageSize);
		Page<Post> pagePost = postrepo.findAll(p);
		List<Post> allpost = pagePost.getContent();
 	    List<PostDto> postDtos = allpost.stream().map(post -> this.modelMapperr.map(post,PostDto.class)).collect(Collectors.toList());

		return postDtos;
		}

	@Override
	public void DeletePost(Integer postId) {
	    Post post =	this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        postrepo.deleteById(postId);
		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		Category catt =	this.categoryRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
        List<Post> postss =  this.postrepo.findByCategory(catt);
        List<PostDto> postdtoss =   postss.stream().map((post) -> this.modelMapperr.map(post,PostDto.class)).collect(Collectors.toList());
	    
		return postdtoss;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
	    Category cat =	this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts =  this.postrepo.findByCategory(cat);
        List<PostDto> postdtos =   posts.stream().map((post) -> this.modelMapperr.map(post,PostDto.class)).collect(Collectors.toList());
	    return postdtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
	List<Post> posts = this.postrepo.findByTitleContaining(keyword);
    List<PostDto> postDto =	posts.stream().map((post)->this.modelMapperr.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public PostResponse getAllPostWithPaginationAndDetails(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		
//		Sort sort = null;
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		}
//		else {
//			sort = Sort.by(sortBy).descending();
//		}
		
		//by using ternary operator we reduce the code see below
	    Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
	        
		    Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		//  Pageable p = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));//for descending order "sort.by(sortBy).descendenging()" we used
			Page<Post> pagePost = postrepo.findAll(p);                    //or we can make it dynamic already we did it as 'sortDir'
			List<Post> allpost = pagePost.getContent();
	 	    List<PostDto> postDtos = allpost.stream().map(post -> this.modelMapperr.map(post,PostDto.class)).collect(Collectors.toList());
            
	 	    PostResponse postResponse = new PostResponse();
	 	    postResponse.setContent(postDtos);
	 	    postResponse.setPageNumber(pagePost.getNumber());
	 	    postResponse.setPageSize(pagePost.getSize());
	 	    postResponse.setTotleElements((int) pagePost.getTotalElements());
	 	    postResponse.setTotlePages(pagePost.getTotalPages());
	 	    postResponse.setLastPage(pagePost.isLast());
			
	 	    return postResponse;
	}
	
	

}
