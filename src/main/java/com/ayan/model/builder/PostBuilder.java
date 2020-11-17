//package com.ayan.model.builder;
//
//import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.GenerationType.SEQUENCE;
//
//import java.time.Instant;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotBlank;
//
//import org.springframework.lang.Nullable;
//
//import com.ayan.model.Post;
//import com.ayan.model.Subreddit;
//import com.ayan.model.User;
//
//public class PostBuilder {
//	
//	@Id
//	@GeneratedValue(strategy = SEQUENCE)
//	private Long postId;
//	@NotBlank(message = "Post Name cannot be empty or Null")
//	private String postName;
//	@Nullable
//	private String url;
//	@Nullable
//	@Lob
//	private String description;
//	private Integer voteCount;
//	@ManyToOne(fetch = LAZY)
//	@JoinColumn(name = "userId", referencedColumnName = "userId")
//	private User user;
//	private Instant createdDate;
//	@ManyToOne(fetch = LAZY)
//	@JoinColumn(name = "id", referencedColumnName = "id")
//	private Subreddit subreddit;
//	
////	Constructors
//	public PostBuilder() {}
//	
//	public PostBuilder(Post post) {
//		this.postId = post.getPostId();
//		this.postName = post.getPostName();
//		this.url = post.getUrl();
//		this.description = post.getDescription();
//		this.voteCount = post.getVoteCount();
//		this.user = post.getUser();
//		this.createdDate = post.getCreatedDate();
//		this.subreddit = post.getSubreddit();
//	}
//	
////	Getter Setter
//	
//	public PostBuilder postId(Long postId) {
//		this.postId = postId;
//		return this;
//	}
//	public PostBuilder postName(String postName) {
//		this.postName = postName;
//		return this;
//	}
//	public PostBuilder url(String url) {
//		this.url = url;
//		return this;
//	}
//	public PostBuilder description(String description) {
//		this.description = description;
//		return this;
//	}
//	public PostBuilder voteCount(Integer voteCount) {
//		this.voteCount = voteCount;
//		return this;
//	}
//	public PostBuilder user(User user) {
//		this.user = user;
//		return this;
//	}
//	public PostBuilder createdDate(Instant createdDate) {
//		this.createdDate = createdDate;
//		return this;
//	}
//	public PostBuilder subreddit(Subreddit subreddit) {
//		this.subreddit = subreddit;
//		return this;
//	}
//	
////	Builder
//	public Post build() {
//		Post post = new Post();
//		post.setPostId(postId);
//		post.setPostName(postName);
//		post.setUrl(url);
//		post.setDescription(description);
//		post.setVoteCount(voteCount);
//		post.setUser(user);
//		post.setCreatedDate(createdDate);
//		post.setSubreddit(subreddit);
//		
//		return post;
//	}
//	
//	
//}
