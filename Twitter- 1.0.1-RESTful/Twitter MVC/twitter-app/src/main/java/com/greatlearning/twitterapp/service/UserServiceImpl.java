package com.greatlearning.twitterapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.greatlearning.twitterapp.exception.InvalidUserException;
import com.greatlearning.twitterapp.model.Tweet;
import com.greatlearning.twitterapp.model.User;
import com.greatlearning.twitterapp.repository.TweetRepository;
import com.greatlearning.twitterapp.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;		
	@Autowired
	TweetRepository tweetRepository;

	@Override
	public User create(User user) {
		this.userRepository.save(user);
		return user;
	}

	@Override
	public void delete(Long userId) {
		try {
			User user = validateUser(userId);
			this.userRepository.delete(user);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User update(Long userId, User updateUser) {
		User user = null;
		try {
			user = validateUser(userId);
			user.setFirstName(updateUser.getFirstName());
			user.setLastName(updateUser.getLastName());
			user.setAge(updateUser.getAge());
			user.setEmailAddress(updateUser.getEmailAddress());
			user.setCoverPic(updateUser.getCoverPic());
			user.setPassword(updateUser.getPassword());
			user.setProfilePic(updateUser.getProfilePic());
			user = this.userRepository.save(user);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User find(Long userId) {
		User user = null;
		try {
			user = validateUser(userId);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public Tweet postTweet(Long userId, Tweet tweet) {
		User user = null;
		try {
			user = validateUser(userId);
			tweet.setUser(user);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		tweet = this.tweetRepository.save(tweet);
		return tweet;
	}

	
	@Override
	public void deleteTweet(Long userId, Long tweetId) {
		Optional<Tweet> optionalTweet = this.tweetRepository.findById(tweetId);
		if(optionalTweet.isPresent()) {
			Tweet tweet = optionalTweet.get();
			User user = optionalTweet.get().getUser();
			if(tweet.getUser().getId() == userId) {
				user.removeTweet(tweet);
				/*
				 * DOUBT- 
				 * Why saving the user with updated User's Set<Tweet> tweets (i.e. this.userRepository.save(user)
				 * is not updating the tweets table even after setting "cascade = CascadeType.ALL" in User's Set<Tweet>?
				 * Why calling "tweetRepository.delete(tweet)" is needed explicitly?
				 */
				this.userRepository.save(user);
				this.tweetRepository.delete(tweet);//Why this?
			}
		}
		System.out.println("**** Deleted tweet ****" + optionalTweet.get());
		System.out.println("of User:" + optionalTweet.get().getUser().getTweets());
	}
	
	@Override
	public void deleteAllTweetsByUser(Long userId) {
		try {
			/*
			 * DOUBT- 
			 * Why saving the user with updated User's Set<Tweet> tweets (i.e. this.userRepository.save(user)
			 * is not updating the tweets table even after setting "cascade = CascadeType.ALL" in User's Set<Tweet>?
			 * Why calling "tweetRepository.delete(tweet)" is needed explicitly?
			 */
			User user = validateUser(userId);
			Set<Tweet> tweets = new HashSet<Tweet>(user.getTweets());
			user.clearTweets();
			this.userRepository.save(user);
			tweets.forEach(tweet -> this.tweetRepository.delete(tweet));
		} catch (InvalidUserException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Set<Tweet> getAllTweetsByUser(Long userId) {
		User user;
		Set<Tweet> tweets = new HashSet<Tweet>();
		try {
			user = validateUser(userId);
			tweets.addAll(user.getTweets());
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return tweets;
	}

	@Override
	public User follow(Long userId, Long followingId) {
		User user = null;
		User following = null;
		try {
			user = validateUser(userId);
			following = validateUser(followingId);
			user.addFollowings(following);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User unfollow(Long userId, Long followingId) {
		User user = null;
		User following = null;
		try {
			user = validateUser(userId);
			following = validateUser(followingId);
			user.removeFollowings(following);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Set<User> getAllFollowingsByUser(Long userId) {
		User user = null;
		Set<User> followings = new HashSet<>();
		try {
			user = validateUser(userId);
			followings.addAll(user.getFollowings());
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return followings;
	}

	@Override
	public Set<User> getAllFollowersByUser(Long userId) {
		User user = null;
		Set<User> followers = new HashSet<>();
		try {
			user = validateUser(userId);
			followers.addAll(user.getFollowers());
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		return followers;
	}
	
	private User validateUser(Long userId) throws InvalidUserException {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new InvalidUserException();
		}
	}
}