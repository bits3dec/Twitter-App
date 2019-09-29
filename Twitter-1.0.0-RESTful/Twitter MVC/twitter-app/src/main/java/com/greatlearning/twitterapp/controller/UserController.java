package com.greatlearning.twitterapp.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.greatlearning.twitterapp.model.*;
import com.greatlearning.twitterapp.service.*;

@Component
public class UserController {
	@Autowired
	UserService userService;
	
	public User createUser(User user) {
		user = this.userService.create(user);
		return user;
	}
	
	public User find(Long userId) {
		User user = this.userService.find(userId);
		return user;
	}
	
	public User update(Long userId, User updateUser) {
		User user = this.userService.update(userId, updateUser);
		return user;
	}
	
	public void delete(Long userId) {
		this.userService.delete(userId);
	}
	
	public Tweet postTweet(Long userId, Tweet tweet) {
		tweet = this.userService.postTweet(userId, tweet);
		return tweet;
	}
	
	public void deleteTweet(Long userId, Long tweetId) {
		this.userService.deleteTweet(userId, tweetId);
	}
	
	public Set<Tweet> getAllTweets(Long userId) {
		Set<Tweet> tweets = this.userService.getAllTweetsByUser(userId);
		return tweets;
	}
	
	public void deleteAllTweetsByUser(Long userId) {
		this.userService.deleteAllTweetsByUser(userId);
	}
	
	public User follow(Long userId, Long followingId) {
		return this.userService.follow(userId, followingId);
	}
	
	public User unfollow(Long userId, Long followingId) {
		return this.userService.unfollow(userId, followingId);
	}
	
	public Set<User> getAllFollowingsByUser(Long userId) {
		return this.userService.getAllFollowingsByUser(userId);
	}
	
	public Set<User> getAllFollowersByUser(Long userId) {
		return this.userService.getAllFollowersByUser(userId);
	}
}
