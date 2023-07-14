package com.dig.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dig.blog.app.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
