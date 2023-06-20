package com.dig.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBloggingAppApplication.class, args);
		
		System.out.println("MY BLOGGING APP IS RUNNING !!!!");
	}

}
