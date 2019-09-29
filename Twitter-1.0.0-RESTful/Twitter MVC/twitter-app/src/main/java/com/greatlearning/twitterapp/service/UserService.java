package com.greatlearning.twitterapp.service;

import java.util.List;
import java.util.Set;
import com.greatlearning.twitterapp.model.*;

public interface UserService {
	User create(User user);
	void delete(Long userId);
	User update(Long userId, User updateUser);
	User find(Long userId);
	List<User> getAllUsers();
	Tweet postTweet(Long userId, Tweet tweet);
	void deleteTweet(Long userId, Long tweetId);
	void deleteAllTweetsByUser(Long userId);
	Set<Tweet> getAllTweetsByUser(Long userId);
	User follow(Long userId, Long followingId);
	User unfollow(Long userId, Long followingId);
	Set<User> getAllFollowingsByUser(Long userId);
	Set<User> getAllFollowersByUser(Long userId);
}
