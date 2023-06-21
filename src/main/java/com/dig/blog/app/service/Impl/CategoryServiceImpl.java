package com.dig.blog.app.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dig.blog.app.entities.Category;
import com.dig.blog.app.exceptions.ResourceNotFoundException;
import com.dig.blog.app.payloads.CategoryDto;
import com.dig.blog.app.repository.CategoryRepo;
import com.dig.blog.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		// modelmapper is used here first dto to category convert then vice versa
		
	Category cat = this.modelMapper.map(categorydto, Category.class);
	Category catsave = categoryRepo.save(cat);
	
	CategoryDto catDto = this.modelMapper.map(catsave,CategoryDto.class);
		
		return catDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId) {
		
		Category cat1 = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId) );
        cat1.setCategoryId(categorydto.getCategoryId());
        cat1.setCategoryTitle(categorydto.getCategoryTitle());
        cat1.setCategoryDescription(categorydto.getCategoryDescription());
        
      Category catt =  categoryRepo.save(cat1);
        
     CategoryDto catDtoo = this.modelMapper.map(catt, CategoryDto.class);
		
		return catDtoo;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId) );
		CategoryDto cat1 = this.modelMapper.map(cat, CategoryDto.class);
		
		return cat1;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> cats = categoryRepo.findAll();
		List<CategoryDto> catsss = cats.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return catsss;
	}

	@Override
	public void deleteCategoryById(Integer categoryId) {
		
		Category catt = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId) );
          
		categoryRepo.delete(catt);
		
	}

}
