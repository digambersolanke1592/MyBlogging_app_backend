package com.dig.blog.app.entities;

import java.security.Identity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)  //we are making id constant
	private int id;
	
	private String rname;
}
