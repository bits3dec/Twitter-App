package com.greatlearning.twitterapp.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.greatlearning.twitterapp.model.Tweet;
import com.greatlearning.twitterapp.model.User;
import com.greatlearning.twitterapp.service.UserService;

@RestController
@RequestMapping("/api/v1/users/")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		user = this.userService.create(user);
		return user;
	}
	
	@GetMapping("/{id}/find")
	public User find(@PathVariable("id") Long userId) {
		User user = this.userService.find(userId);
		return user;
	}
	
	@PutMapping("/{id}/update")
	public User update(@PathVariable("id") Long userId, @RequestBody User updateUser) {
		User user = this.userService.update(userId, updateUser);
		return user;
	}
	
	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable("id") Long userId) {
		this.userService.delete(userId);
	}
	
	@GetMapping("/")
	public List<User> getAll() {
		return this.userService.getAllUsers();
	}
	
	@PostMapping("/{id}/tweets/post")
	public Tweet postTweet(@PathVariable("id") Long userId, @RequestBody Tweet tweet) {
		tweet = this.userService.postTweet(userId, tweet);
		return tweet;
	}
	
	@DeleteMapping("/{userId}/tweets/{tweetId}/delete")
	public void deleteTweet(@PathVariable("userId") Long userId, @PathVariable("tweetId") Long tweetId) {
		this.userService.deleteTweet(userId, tweetId);
	}
	
	@GetMapping("/{id}/tweets")
	public Set<Tweet> getAllTweetsByUser(@PathVariable("id") Long userId) {
		Set<Tweet> tweets = this.userService.getAllTweetsByUser(userId);
		return tweets;
	}
	
	@DeleteMapping("/{id}/tweets")
	public void deleteAllTweetsByUser(@PathVariable("id") Long userId) {
		this.userService.deleteAllTweetsByUser(userId);
	}
	
	@PostMapping("/{userId}/follow/{followingId}")
	public User follow(@PathVariable("userId") Long userId, @PathVariable("followingId") Long followingId) {
		return this.userService.follow(userId, followingId);
	}
	
	@DeleteMapping("/{userId}/unfollow/{followingId}")
	public User unfollow(@PathVariable("userId") Long userId, @PathVariable("followingId") Long followingId) {
		return this.userService.unfollow(userId, followingId);
	}
	
	@GetMapping("/{id}/followings")
	public Set<User> getAllFollowingsByUser(@PathVariable("id") Long userId) {
		return this.userService.getAllFollowingsByUser(userId);
	}
	
	@GetMapping("/{id}/followers")
	public Set<User> getAllFollowersByUser(@PathVariable("id") Long userId) {
		return this.userService.getAllFollowersByUser(userId);
	}	
}
