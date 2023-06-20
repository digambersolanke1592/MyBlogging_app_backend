package com.dig.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dig.blog.app.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	
}
