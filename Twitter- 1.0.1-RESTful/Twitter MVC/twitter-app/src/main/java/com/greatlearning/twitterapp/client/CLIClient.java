package com.greatlearning.twitterapp.client;

import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.greatlearning.twitterapp.controller.UserController;
import com.greatlearning.twitterapp.model.Tweet;
import com.greatlearning.twitterapp.model.User;

public class CLIClient {
	private static UserController controller;
	private static Scanner sc1 = new Scanner(System.in);
	private static Scanner sc2 = new Scanner(System.in);
	
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
//		controller = applicationContext.getBean(UserController.class);
		
//		createUsers();
//		postTweets();
//		getTweetsByUser();
//		follow();
//		unfollow();
//		updateUser();
//	    deleteUser();
//	    deleteTweet();
//		deleteAllTweetsByUser();
//		getAllFollowingsByUser();
//		getAllFollowersByUser();
	}
	
	private static void createUsers() {
		List<User> users = new ArrayList<>();
		User user1 = new User("first_1", "last_1", "userhandle_1", "emailAddress_1", "password_1", 25);
		User user2 = new User("first_2", "last_2", "userhandle_2", "emailAddress_2", "password_2", 25);
		User user3 = new User("first_3", "last_3", "userhandle_3", "emailAddress_3", "password_3", 25);
		User user4 = new User("first_4", "last_4", "userhandle_4", "emailAddress_4", "password_4", 25);
		User user5 = new User("first_5", "last_5", "userhandle_5", "emailAddress_5", "password_5", 25);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		
		for(User user : users) {
			controller.createUser(user);
		}	
	}
	
	private static void postTweets() {
		System.out.println("************** POST TWEETS *************");
		
		System.out.println("***** (1) Enter userId *****");
		Long userId1 = sc1.nextLong();
		User user1 = controller.find(userId1);
		if(user1 != null) {
			System.out.println("***** Post a tweet  *****");
			String tweetMessage = sc2.nextLine();
			Tweet tweet1 = new Tweet(tweetMessage, user1);
			controller.postTweet(userId1, tweet1);
			Tweet mockTweet2 = new Tweet("MockTweet1", user1);
			controller.postTweet(userId1, mockTweet2);
			Tweet mockTweet3 = new Tweet("MockTweet2", user1);
			controller.postTweet(userId1, mockTweet3);
		}
		
		System.out.println("***** (2) Enter your userId *****");
		Long userId2 = sc1.nextLong();
		User user2 = controller.find(userId2);
		if(user1 != null) {
			System.out.println("***** Post a tweet  *****");
			String tweetMessage = sc2.nextLine();
			Tweet tweet1 = new Tweet(tweetMessage, user2);
			controller.postTweet(userId2, tweet1);
		}
	}
	
	private static void getTweetsByUser() {
		System.out.println("************** SHOW TWEETS BY USER *************");
		
		System.out.println("***** (1) Enter userId *****");
		Long userId1 = sc1.nextLong();
		Set<Tweet> tweetsByUser1 = new HashSet<>();
		tweetsByUser1.addAll(controller.getAllTweets(userId1));
		System.out.println("****** Tweets ******");
		if(tweetsByUser1.size() == 0) {
			System.out.println("No tweets found !!!");
		} else {
			for(Tweet tweet : tweetsByUser1) {
				System.out.println(tweet);
			}
		}
		
		System.out.println("***** (2) Enter userId  *****");
		Long userId2 = sc1.nextLong();
		Set<Tweet> tweetsByUser2 = new HashSet<>();
		tweetsByUser2.addAll(controller.getAllTweets(userId2));
		System.out.println("****** Tweets ******");
		if(tweetsByUser2.size() == 0) {
			System.out.println("No tweets found !!!");
		} else {
			for(Tweet tweet : tweetsByUser2) {
				System.out.println(tweet);
			}
		}
	}
	
	private static void follow() {	
		controller.follow(1L, 2L);
		controller.follow(1L, 3L);
		controller.follow(1L, 4L);
		controller.follow(2L, 1L);
		controller.follow(2L, 3L);
		controller.follow(4L, 5L);
		controller.follow(5L, 3L);
	}
	
	private static void unfollow() {	
		controller.unfollow(1L, 2L);
		controller.unfollow(1L, 3L);
		controller.unfollow(2L, 1L);
	}
	
	private static void updateUser() {
		User updateUser = new User("updatedfirst_1", "updatedlast_1", "userhandle_1", "emailAddress_1", "password_1", 25);
		updateUser.setProfilePic("New Profile Pic");
		controller.update(1L, updateUser);
	}
	
	private static void deleteUser() {
		controller.delete(5L);
	}
	
	private static void deleteTweet() {
		controller.deleteTweet(1L, 7L);
	}
	
	private static void deleteAllTweetsByUser() {
		System.out.println("******** DELETE ALL TWEETS BY USER *********");
		System.out.println("***** Enter userId  *****");
		Long userId = sc2.nextLong();
		controller.deleteAllTweetsByUser(userId);
	}
	
	private static void getAllFollowingsByUser() {
		System.out.println("******* SHOW FOLLOWINGS ******");
		System.out.println("**** Enter userId: *****");
		Long userId = sc1.nextLong();
		Set<User> followings = controller.getAllFollowingsByUser(userId);
		if(followings.size() == 0) {
			System.out.println("No followings !!!");
		} else {
			for(User following : followings) {
				System.out.println(following);
			}
		}	
	}
	
	private static void getAllFollowersByUser() {
		System.out.println("******* SHOW FOLLOWERS ******");
		System.out.println("**** Enter userId: *****");
		Long userId = sc1.nextLong();
		Set<User> followers = controller.getAllFollowersByUser(userId);
		if(followers.size() == 0) {
			System.out.println("No followers !!!");
		} else {
			for(User follower : followers) {
				System.out.println(follower);
			}
		}
	}
}
