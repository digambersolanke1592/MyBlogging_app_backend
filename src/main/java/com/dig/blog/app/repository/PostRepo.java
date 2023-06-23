package com.dig.blog.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dig.blog.app.entities.Category;
import com.dig.blog.app.entities.Post;
import com.dig.blog.app.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	//here for creating customise method for search we have some rule like findBy then title (what we want to search)then containing
	List<Post> findByTitleContaining(String title);
}
