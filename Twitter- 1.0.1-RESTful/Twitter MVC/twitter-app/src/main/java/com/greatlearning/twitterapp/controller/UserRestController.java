package com.greatlearning.twitterapp.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/{id}")
	public User findUser(@PathVariable("id") Long userId) {
		User user = this.userService.find(userId);
		return user;
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		System.out.println("*** Came Inside REST GetAllUsers ***");
		List<User> users = this.userService.getAllUsers();
		for(User user : users) {
			System.out.println(user);
			System.out.println(user.getTweets());
		}
		return users;
	}
	
	@PostMapping("/{id}/tweets")
	public Tweet postTweet(@PathVariable("id") Long userId, @RequestBody Tweet tweet) {
		System.out.println("******** Came Inside PostTweet ******");
		System.out.println("Before Service layer -> Tweet: " + tweet);
		tweet = this.userService.postTweet(userId, tweet);
		System.out.println("After Service layer -> Tweet: " + tweet);
		return tweet;
	}
	
	@GetMapping("{id}/tweets")
	public Set<Tweet> getAllTweets(@PathVariable Long userId) {
		Set<Tweet> tweets = this.userService.getAllTweetsByUser(userId);
		return tweets;
	}
}
