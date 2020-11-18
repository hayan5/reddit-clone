package com.ayan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayan.model.Post;
import com.ayan.model.Subreddit;
import com.ayan.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllBySubreddit(Subreddit subreddit);
	
	List<Post> findByUser(User user);
}
