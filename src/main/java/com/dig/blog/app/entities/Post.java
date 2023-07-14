package com.dig.blog.app.entities;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dig.blog.app.payloads.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name  = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(name = "post_title",length = 100,nullable = false)
	private String title;
	@Column(length = 10000)
	private String content;
	private String imageName;
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name = "category_id")  //many post for one category
	private Category category;
	@ManyToOne
	private User user;       //many post for single user         
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)        //single post can have many comments  so we take set/list of comment
	private Set<Comment> comment = new HashSet<>(); //if we use comment then it give looping of post problem because comment has post but commentDto dont have post
	
	// mapping are depends on our requirements 
	

}
