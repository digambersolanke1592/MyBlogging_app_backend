package com.dig.blog.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dig.blog.app.config.AppConstant;
import com.dig.blog.app.entities.Role;
import com.dig.blog.app.repository.RoleRepo;

@SpringBootApplication
public class MyBloggingAppApplication implements CommandLineRunner {// we implement commandlinearunner for getting
																	// encoded password of previous password

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(MyBloggingAppApplication.class, args);

		System.out.println("MY BLOGGING APP IS RUNNING !!!!");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override // password for user 'ram' is 12345678 which is encoded below for basic
				// authetication
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("encoded password for previously store password   " + passwordEncoder.encode("12345678"));

		// for new app user we need to add role in database so we do it below

		Role role = new Role();
		role.setId(AppConstant.ADMIN_USER);
		role.setRname("ADMIN_USER");

		Role role1 = new Role();
		role1.setId(AppConstant.NORMAL_USER);
		role1.setRname("NORMAL_USER");

		List<Role> roles = List.of(role, role1);
		List<Role> roleresult = roleRepo.saveAll(roles);
		
		//fir iterating roles on console
		
		roleresult.forEach((ro)-> System.out.println("Data adding before start   "+ro));
	}

}
