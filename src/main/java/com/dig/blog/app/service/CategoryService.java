package com.dig.blog.app.service;

import java.util.List;

import com.dig.blog.app.payloads.CategoryDto;

public interface CategoryService {

	//create category
     CategoryDto createCategory (CategoryDto categorydto);	
   //udate category
     CategoryDto updateCategory (CategoryDto categorydto,Integer categoryId);	
   //getbyId category
     CategoryDto getCategoryById (Integer categoryId);
   //getall category
     List<CategoryDto> getAllCategory ();
   //delete category
     void deleteCategoryById (Integer categoryId);
}

