package com.dig.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dig.blog.app.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
