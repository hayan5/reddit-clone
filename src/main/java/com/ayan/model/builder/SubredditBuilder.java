//package com.ayan.model.builder;
//
//import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.GenerationType.SEQUENCE;
//
//import java.time.Instant;
//import java.util.List;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.NotBlank;
//
//import com.ayan.model.Post;
//import com.ayan.model.Subreddit;
//import com.ayan.model.User;
//
//public class SubredditBuilder {
//	
//	@Id
//	@GeneratedValue(strategy = SEQUENCE)
//	private Long id;
//	@NotBlank(message = "Community name is required")
//	private String name;
//	@NotBlank(message = "Description is required")
//	private String description;
//	@OneToMany(fetch = LAZY)
//	private List<Post> posts;
//	private Instant createdDate;
//	@ManyToOne(fetch = LAZY)
//	private User user;
//	
////	Constructor
//	public SubredditBuilder() {}
//	
//	public SubredditBuilder(Subreddit subreddit) {
//		this.id = subreddit.getId();
//		this.name = subreddit.getName();
//		this.description = subreddit.getDescription();
//		this.posts = subreddit.getPosts();
//		this.createdDate = subreddit.getCreatedDate();
//		this.user = subreddit.getUser();
//	}
//	
////	Getter Setter
//	public SubredditBuilder id(Long id) {
//		this.id = id;
//		return this;
//	}
//	
//	public SubredditBuilder name(String name) {
//		this.name = name;
//		return this;
//	}
//	
//	public SubredditBuilder description(String description) {
//		this.description = description;
//		return this;
//	}
//	
//	public SubredditBuilder posts(List<Post> posts) {
//		this.posts = posts;
//		return this;
//	}
//	
//	public SubredditBuilder createdDate(Instant createdDate) {
//		this.createdDate = createdDate;
//		return this;
//	}
//	
//	public SubredditBuilder user(User user) {
//		this.user = user;
//		return this;
//	}
//	
////	Builder
//	public Subreddit build() {
//		Subreddit subreddit = new Subreddit();
//		subreddit.setId(id);
//		subreddit.setName(name);
//		subreddit.setDescription(description);
//		subreddit.setPosts(posts);
//		subreddit.setCreatedDate(createdDate);
//		subreddit.setUser(user);
//		
//		return subreddit;
//	}
//}
