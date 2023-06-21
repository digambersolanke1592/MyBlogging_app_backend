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
import org.springframework.web.bind.annotation.RestController;

import com.dig.blog.app.payloads.CategoryDto;
import com.dig.blog.app.service.CategoryService;
import com.mysql.fabric.Response;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catDto){
		CategoryDto catDto1 = categoryService.createCategory(catDto);
		return new ResponseEntity<>(catDto1,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto catDto,@PathVariable("id") Integer categoryId){
	CategoryDto catDtooo = categoryService.updateCategory(catDto, categoryId);
		return new ResponseEntity<>(catDtooo,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> catDtolist =  categoryService.getAllCategory();
		return new ResponseEntity<>(catDtolist,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
		CategoryDto catty = categoryService.getCategoryById(id);
		return new ResponseEntity<>(catty,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable("id") Integer categoryId) {
		categoryService.deleteCategoryById(categoryId);
		return "the category is deleted with id : "+ categoryId;
		
	}
	
	}


