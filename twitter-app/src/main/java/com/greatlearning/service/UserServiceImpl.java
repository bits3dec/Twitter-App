package com.greatlearning.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.greatlearning.exception.InvalidUserException;
import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;
import com.greatlearning.repository.TweetRepository;
import com.greatlearning.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TweetRepository tweetRepository;
	
	@Override
	public User saveUser(User user) {
		this.userRepository.save(user);	
		return user;
	}

	@Override
	public void deleteUser(long userId) {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			this.userRepository.delete(user);
		}
		return;
	}
	
	@Override
	public User updateUser(long userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

	@Override
	public void postTweet(long userId, Tweet tweet) {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.addTweet(tweet);
		}
		return;
	}

	@Override
	public void removeTweet(long userId, long tweetId) {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		Optional<Tweet> optionalTweet = this.tweetRepository.findById(tweetId);
		if(optionalUser.isPresent() && optionalTweet.isPresent()) {
			User user = optionalUser.get();
			Tweet tweet = optionalTweet.get();
			this.tweetRepository.delete(tweet);
		}
		return;
	}
	
	@Override
	public void follow(long userId, long followingId) {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		Optional<User> optionalfollower = this.userRepository.findById(followingId);
		if(optionalUser.isPresent() && optionalfollower.isPresent()) {
			User user = optionalUser.get();
			User following = optionalfollower.get();
			user.addFollowing(following);
		}
		return;
	}

	@Override
	public void unfollow(long userId, long followingId) {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		Optional<User> optionalfollower = this.userRepository.findById(followingId);
		if(optionalUser.isPresent() && optionalfollower.isPresent()) {
			User user = optionalUser.get();
			User following = optionalfollower.get();
			user.removeFollowing(following);
		}
		return;
	}

	@Override
	public void likeTweet(long userId, long tweetId) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public Tweet getTweetById(long tweetId) {
		Optional<Tweet> optionalTweet = this.tweetRepository.findById(tweetId);
		if(optionalTweet.isPresent()) {
			Tweet tweet = optionalTweet.get();
			return tweet;
		}
		return null;
	}

	@Override
	public Set<Tweet> getAllTweetsByUser(long userId) {
		Optional<User> optionalUser = this.userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			Set<Tweet> tweets = user.getTweets();
			return tweets;
		}
		return null;
	}

	@Override
	public Set<Tweet> getAllTweetsByfollower(long userId, long followerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User authenticateUser(String username, String password) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}
}
