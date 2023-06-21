package com.dig.blog.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBloggingAppApplication.class, args);
		
		System.out.println("MY BLOGGING APP IS RUNNING !!!!");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
