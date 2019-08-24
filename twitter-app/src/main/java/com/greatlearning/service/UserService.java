package com.greatlearning.service;

import java.util.List;
import java.util.Set;

import com.greatlearning.exception.InvalidUserException;
import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	void deleteUser(long userId);
	
	User updateUser(long userId, User user);
	
	public List<User> getAllUsers();
	
	void postTweet(long userId, Tweet tweet);
	
	void removeTweet(long userId, long tweetId);
	
	void likeTweet(long userId, long tweetId);
	
	void follow(long userId, long followerId);
	
	void unfollow(long userId, long followingId);
	
	Tweet getTweetById(long tweetId);
	
	Set<Tweet> getAllTweetsByUser(long userId);
	
	Set<Tweet> getAllTweetsByfollower(long userId, long followerId);
	
	User authenticateUser(String username, String password) throws InvalidUserException;
}
