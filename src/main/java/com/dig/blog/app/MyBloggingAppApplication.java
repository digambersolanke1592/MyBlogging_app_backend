package com.dig.blog.app;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MyBloggingAppApplication implements CommandLineRunner{//we implement commandlinearunner for getting encoded password of previous password

	public static void main(String[] args) {
		SpringApplication.run(MyBloggingAppApplication.class, args);
		
		System.out.println("MY BLOGGING APP IS RUNNING !!!!");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override             //password for user 'ram' is 12345678 which is encoded below for basic authetication
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("encoded password for previously store password   "+passwordEncoder.encode("12345678"));
	}

}
