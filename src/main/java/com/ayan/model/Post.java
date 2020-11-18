package com.ayan.model;

import java.time.Instant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@NotBlank
	private String postName;
	@Nullable
	@Lob
	private String url;
	private String description;
	private Integer voteCount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	private Instant createdDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subredditId", referencedColumnName = "id")
	private Subreddit subreddit;
	
	public Post() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Instant getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
	public Subreddit getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(Subreddit subreddit) {
		this.subreddit = subreddit;
	}
	
	
	
	
}
