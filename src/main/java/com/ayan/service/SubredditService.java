package com.ayan.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayan.dto.SubredditDto;
import com.ayan.exception.SubredditNotFoundException;
import com.ayan.model.Subreddit;
import com.ayan.repository.SubredditRepository;

import static java.util.stream.Collectors.toList;

import java.time.Instant;
@Service
public class SubredditService {
	
	@Autowired
	private SubredditRepository subredditRepository;
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subredditRepository.findAll()
				.stream()
				.map(this::mapToDto)
				.collect(toList());
	}
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit subreddit = subredditRepository.save(mapToSubreddit(subredditDto));
		subredditDto.setId(subreddit.getId());
		return subredditDto;
	}
	
	@Transactional(readOnly = true)
	public SubredditDto getSubreddit(Long id) throws SubredditNotFoundException {
		Subreddit subreddit = subredditRepository.findById(id)
				.orElseThrow(() -> new SubredditNotFoundException("Subreddit not found with id: " + id));
		
		return mapToDto(subreddit);
	}
	
	private SubredditDto mapToDto(Subreddit subreddit) {
		SubredditDto dto = new SubredditDto();
		dto.setName(subreddit.getName());
		dto.setId(subreddit.getId());
		dto.setDescription(subreddit.getDescription());
		dto.setNumberOfPosts(subreddit.getPosts().size());
		
		return dto;
	}
	
	private Subreddit mapToSubreddit(SubredditDto subredditDto) {
		Subreddit subreddit = new Subreddit();
		subreddit.setName("/r/" + subredditDto.getName());
		subreddit.setDescription(subredditDto.getDescription());
		subreddit.setUser(authService.getCurrentUser());
		subreddit.setCreatedDate(Instant.now());
		
		return subreddit;
	}
	

}
