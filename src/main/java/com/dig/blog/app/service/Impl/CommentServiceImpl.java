package com.dig.blog.app.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dig.blog.app.entities.Comment;
import com.dig.blog.app.entities.Post;
import com.dig.blog.app.exceptions.ResourceNotFoundException;
import com.dig.blog.app.payloads.CommentDto;
import com.dig.blog.app.repository.CommentRepo;
import com.dig.blog.app.repository.PostRepo;
import com.dig.blog.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostRepo postrepo;
	
	@Autowired
	CommentRepo commentrepo;
	
	@Autowired
	ModelMapper modelmapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		Comment comment = modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedcomment = commentrepo.save(comment);
		
		return modelmapper.map(savedcomment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comm = commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "comment id", commentId));
		commentrepo.delete(comm);
	}

}
