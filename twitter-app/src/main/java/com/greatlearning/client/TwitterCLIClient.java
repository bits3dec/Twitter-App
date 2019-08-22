package com.greatlearning.client;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.greatlearning.controller.UserController;
import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;

public class TwitterCLIClient {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		UserController controller = context.getBean(UserController.class);
		
		run(controller);
	}
	
	private static void run(UserController controller) {
//		User user1 = new User("preetom93", "Preetom", "Bhowmik", "preetom@gmail.com", "Pass1", LocalDate.now());
//		Tweet tweet1 = new Tweet(user1, "First tweet", LocalDate.of(2019, 8, 16));
//		controller.saveUser(user1);
//		controller.postTweet(tweet1);
//		
//		Tweet tweet2 = new Tweet(user1, "Second tweet", LocalDate.of(2019, 8, 16));
//		controller.postTweet(tweet2);
//		
//		User user2 = new User("rahul93", "Rahul", "Sharma", "rahul@gmail.com", "PassRahul", LocalDate.now());
//		controller.saveUser(user2);
//		User user3 = new User("virat93", "Virat", "Kohli", "viratl@gmail.com", "PassVirat", LocalDate.now());
//		controller.saveUser(user3);
//		User user4 = new User("rishabhl93", "Rishabh", "Pant", "rishabh@gmail.com", "PassPant", LocalDate.now());
//		controller.saveUser(user4);
//		User user5 = new User("dhoni93", "MS", "Dhoni", "dhoni@gmail.com", "PassDhoni", LocalDate.now());
//		controller.saveUser(user5);
//		User user6 = new User("shreyas93", "Shreyas", "Iyer", "shreyas@gmail.com", "PassShreyas", LocalDate.now());
//		controller.saveUser(user6);
//		
//		controller.follow(user1.getUserId(), user2.getUserId());
//		controller.follow(user1.getUserId(), user3.getUserId());
//		controller.follow(user1.getUserId(), user4.getUserId());
//		controller.follow(user1.getUserId(), user5.getUserId());
//		controller.follow(user1.getUserId(), user6.getUserId());
//		controller.follow(user2.getUserId(), user4.getUserId());
//		controller.follow(user2.getUserId(), user5.getUserId());
//		controller.follow(user3.getUserId(), user5.getUserId());
//		controller.follow(user4.getUserId(), user5.getUserId());
//		controller.follow(user4.getUserId(), user3.getUserId());
//		controller.follow(user5.getUserId(), user3.getUserId());
//		controller.follow(user6.getUserId(), user2.getUserId());
//		controller.follow(user6.getUserId(), user3.getUserId());
//		controller.follow(user6.getUserId(), user4.getUserId());
//		controller.follow(user6.getUserId(), user5.getUserId());
//		
//		Tweet tweet3 = new Tweet(user2, "Second tweet", LocalDate.now());
//		Tweet tweet4 = new Tweet(user2, "Third tweet", LocalDate.now());
//		Tweet tweet5 = new Tweet(user3, "Forth tweet", LocalDate.now());
//		Tweet tweet6 = new Tweet(user4, "Fifth tweet", LocalDate.now());
//		Tweet tweet7 = new Tweet(user4, "Sixth tweet", LocalDate.now());
//		Tweet tweet8 = new Tweet(user5, "Seventh tweet", LocalDate.now());
//		Tweet tweet9 = new Tweet(user5, "Eighth tweet", LocalDate.now());
//		Tweet tweet10 = new Tweet(user6, "Nineth tweet", LocalDate.now());
//		controller.postTweet(tweet3);
//		controller.postTweet(tweet4);
//		controller.postTweet(tweet5);
//		controller.postTweet(tweet6);
//		controller.postTweet(tweet7);
//		controller.postTweet(tweet8);
//		controller.postTweet(tweet9);
//		controller.postTweet(tweet10);
//		
//		controller.unfollow(user1.getUserId(), user2.getUserId());
//		
//		//Added User: Test (To test for deleting user from database)		
//		User testUser = new User("test93", "Test", "Test", "test@gmail.com", "PassTest", LocalDate.now());
//		controller.saveUser(testUser);	
//		controller.follow(17, 1);
//		controller.unfollow(17, 1);
//		controller.deleteUser(17);
//		Tweet testTweet = new Tweet(testUser, "Test tweet", LocalDate.now());
//		controller.postTweet(testTweet);
//		controller.removeTweet(18, 19);
		
//		Tweet searchResult = controller.getTweetByTweetId(10);
//		System.out.println("********* Search Result Starts **********");
//		System.out.println(searchResult);
//		System.out.println("********* Search Result Ends **********");
		
		Set<Tweet> tweets = controller.getAllTweetsByUser(4);
		System.out.println(tweets);		
		
	}
}
