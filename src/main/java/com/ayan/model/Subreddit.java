package com.ayan.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Subreddit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotBlank(message = "Subreddit name is required")
	private String name;
	@NotBlank(message = "Description is required")
	private String description;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Post> posts;
	private Instant createdDate;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	public Subreddit(Long id, @NotBlank(message = "Subreddit name is required") String name,
			@NotBlank(message = "Description is required") String description, List<Post> posts, Instant createdDate,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.posts = posts;
		this.createdDate = createdDate;
		this.user = user;
	}
	public Subreddit() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public Instant getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
