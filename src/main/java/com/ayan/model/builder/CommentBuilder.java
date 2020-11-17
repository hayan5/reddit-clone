package com.ayan.model.builder;

//import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.GenerationType.SEQUENCE;
//
//import java.time.Instant;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotEmpty;
//
//import com.ayan.model.Comment;
//import com.ayan.model.Post;
//import com.ayan.model.User;
//
//public class CommentBuilder {
//	
//	@Id
//	@GeneratedValue(strategy = SEQUENCE)
//	private Long id;
//	@NotEmpty
//	private String text;
//	@ManyToOne(fetch = LAZY)
//	@JoinColumn(name = "postId", referencedColumnName = "postId")
//	private Post post;
//	private Instant createdDate;
//	@ManyToOne(fetch = LAZY)
//	@JoinColumn(name = "userId", referencedColumnName = "userId")
//	private User user;
//	
////	Constructors
//	public CommentBuilder() {}
//	
//	public CommentBuilder(Comment comment) {
//		this.id = comment.getId();
//		this.text = comment.getText();
//		this.post = comment.getPost();
//		this.createdDate = comment.getCreatedDate();
//		this.user = comment.getUser();
//	}
//	
////	Getter Setter
//	public CommentBuilder id(Long id) {
//		this.id = id;
//		return this;
//	}
//	public CommentBuilder text(String text) {
//		this.text = text;
//		return this;
//	}
//	public CommentBuilder post(Post post) {
//		this.post = post;
//		return this;
//	}
//	public CommentBuilder createdDate(Instant createdDate) {
//		this.createdDate = createdDate;
//		return this;
//	}
//	public CommentBuilder user(User user) {
//		this.user = user;
//		return this;
//	}
//	
////	Builder
//	public Comment build() {
//		Comment comment = new Comment();
//		comment.setId(id);
//		comment.setText(text);
//		comment.setPost(post);
//		comment.setCreatedDate(createdDate);
//		comment.setUser(user);
//		
//		return comment;
//	}
//}
