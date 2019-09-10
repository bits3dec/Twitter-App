package com.greatlearning.twitterapp.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User implements Comparable<User>, Serializable {
	private static final long serialVersionUID = 3744877144956191823L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userHandle;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String emailAddress;
	
	private int age;
	
	private String profilePic;
	
	private String coverPic;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Tweet> tweets = new HashSet<Tweet>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_followings",
				joinColumns = @JoinColumn(name = "user"),
				inverseJoinColumns = @JoinColumn(name = "following"))
	private Set<User> followings = new HashSet<User>();
	
	@ManyToMany(mappedBy = "followings", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> followers = new HashSet<User>();
	
	private User() {}
	
	public User(String firstName, String lastName, String userHandle, String password, String emailAddress, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userHandle = userHandle;
		this.password = password;
		this.emailAddress = emailAddress;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUserHandle() {
		return userHandle;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}
	public void postTweet(Tweet tweet) {
		this.tweets.add(tweet);
	}
	public void removeTweet(Tweet tweet) {
		this.tweets.remove(tweet);
	}
	public void clearTweets() {
		this.tweets.clear();
	}
	
	@JsonIgnore
	public Set<User> getFollowings() {
		return followings;
	}
	public void addFollowings(User following) {
		this.followings.add(following);
		following.addFollowers(this);
	}
	public void removeFollowings(User following) {
		this.followings.remove(following);
		following.removeFollowers(this);
	}
	
	@JsonIgnore
	public Set<User> getFollowers() {
		return followers;
	}
	private void addFollowers(User follower) {
		this.followers.add(follower);
	}
	private void removeFollowers(User follower) {
		this.followers.remove(follower);
	}
	
	public int compareTo(User other) {
		return this.getUserHandle().compareTo(other.getUserHandle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userHandle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(userHandle, other.userHandle);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userHandle=" + userHandle + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", profilePic=" + profilePic + ", coverPic=" + coverPic + "]";
	}	
}
