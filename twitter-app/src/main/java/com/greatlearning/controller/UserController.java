package com.greatlearning.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greatlearning.exception.InvalidUserException;
import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;
import com.greatlearning.service.UserService;

@Component
public class UserController {

	@Autowired
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	public User saveUser(User user) {
		return this.service.saveUser(user);
	}
	
	public void deleteUser(long userId) {
		this.service.deleteUser(userId);
	}
	
	//TODO:
	public User updateUser(long userId, User user) {
		return null;
	}
	
	public void postTweet(Tweet tweet) {
		this.service.postTweet(tweet.getUser().getUserId(), tweet);
	}
	
	//TODO:
	public void removeTweet(long userId, long tweetId) {
		this.service.removeTweet(userId, tweetId);
	}
	
	public void follow(long userId, long followerId) {
		this.service.follow(userId, followerId);
	}
	
	public void unfollow(long userId, long followingId) {
		this.service.unfollow(userId, followingId);
	}
	
	//TODO:
	public void likeTweet(long userId, long tweetId) {
		return;
	}
	
	//TODO:
	public User authenticateUser(String username, String password) throws InvalidUserException {
		return null;
	}
	
	public Tweet getTweetById(long tweetId) {
		return this.service.getTweetById(tweetId);
	}
	
	public Set<Tweet> getAllTweetsByUser(long userId) {
		return this.service.getAllTweetsByUser(userId);
	}
	
	public Set<Tweet> getAllTweetsByFollower(long userId, long followerId) {
		return null;
	}
}
