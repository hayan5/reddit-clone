package com.ayan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.dto.SubredditDto;
import com.ayan.exception.SubredditNotFoundException;
import com.ayan.service.SubredditService;

@RestController
@RequestMapping("/api/subreddit")
public class SubredditController {
	
	@Autowired
	private SubredditService subredditService;
	
	@GetMapping
	public List<SubredditDto> getAllSubreddits() {
		return subredditService.getAll();
	}
	
	@GetMapping("/{id}")
	public SubredditDto getSubreddit(@PathVariable Long id) throws SubredditNotFoundException {
		return subredditService.getSubreddit(id);
	}
	
	@PostMapping
	public SubredditDto create(@RequestBody @Valid SubredditDto subredditDto) {
		return subredditService.save(subredditDto);
	}

}
