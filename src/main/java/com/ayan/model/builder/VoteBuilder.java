//package com.ayan.model.builder;
//
//import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.GenerationType.SEQUENCE;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotNull;
//
//import com.ayan.model.Post;
//import com.ayan.model.User;
//import com.ayan.model.Vote;
//import com.ayan.model.VoteType;
//
//public class VoteBuilder {
//	
//	@Id
//	@GeneratedValue(strategy = SEQUENCE)
//	private Long voteId;
//	private VoteType voteType;
//	@NotNull
//	@ManyToOne(fetch = LAZY)
//	@JoinColumn(name = "postId", referencedColumnName = "postId")
//	private Post post;
//	@ManyToOne(fetch = LAZY)
//	@JoinColumn(name = "userId", referencedColumnName = "userId")
//	private User user;
//	
////	Constructors
//	public VoteBuilder() {}
//	
//	public VoteBuilder(Vote vote) {
//		this.voteId = vote.getVoteId();
//		this.voteType = vote.getVoteType();
//		this.post = vote.getPost();
//		this.user = vote.getUser();
//	}
//	
////	Getter Setter
//	public VoteBuilder voteId(Long voteId) {
//		this.voteId = voteId;
//		return this;
//	}
//	public VoteBuilder voteType(VoteType voteType) {
//		this.voteType = voteType;
//		return this;
//	}
//	public VoteBuilder post(Post post) {
//		this.post = post;
//		return this;
//	}
//	public VoteBuilder user(User user) {
//		this.user = user;
//		return this;
//	}
//	
////	Builder
//	public Vote build() {
//		Vote vote = new Vote();
//		vote.setVoteId(voteId);
//		vote.setVoteType(voteType);
//		vote.setPost(post);
//		vote.setUser(user);
//		
//		return vote;
//	}
//}
