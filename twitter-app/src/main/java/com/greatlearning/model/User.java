package com.greatlearning.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;

@Entity
@Table(name = "user")
public class User implements Comparable<User>, Serializable {
	static final long serialVersionUID = 1115533237396131226L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;

	@Column(name = "user_handle")
	private String userHandle;

	@Column(name = "first_name")
	@NotBlank(message = "Name cannot be empty")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "age")
	@Range(min = 18, max = 60, message = "Age should be greater than 18")
	private int age;

	@Column(name = "password")
	private String password;

	@Column(name = "email_Address")
	private String emailAddress;

	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "profile_pic")
	private String profilePic;
	
	@Column(name = "cover_image")
	private String coverImage;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Tweet> tweets = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_following",	
				joinColumns = @JoinColumn(name = "user"),
				inverseJoinColumns = @JoinColumn(name = "following"))
	private Set<User> followings = new HashSet<User>();
	
	@ManyToMany(mappedBy = "followings", cascade = CascadeType.ALL)
	private Set<User> followers = new HashSet<User>();

	public User() {}

	public User(String userHandle, String firstName, String lastName, int age, String emailAddress, String password, LocalDate createdDate) {
		this.userHandle = userHandle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailAddress = emailAddress;
		this.password = password;
		this.createdDate = createdDate;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserHandle() {
		return userHandle;
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

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getProfilePic() {
		return profilePic; 
    } 
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic; 
	}
	 
	public String getCoverImage() {
		return coverImage; 
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage; 
	}
	 
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public Set<Tweet> getTweets() {
		return tweets; 
	} 

	public Set<User> getFollowings() {
		return followings;
	}

	public Set<User> getFollowers() {
		return followers;
	}
	
	public void addTweet(Tweet tweet) {
		this.tweets.add(tweet);
		tweet.setUser(this);
	}

	public void addFollowing(User following) {
		this.followings.add(following);
		following.getFollowers().add(this);
	}
	
 	public void removeFollowing(User following) {
 		this.followings.remove(following);
		following.getFollowers().remove(this);
 	}
	
	@Override
	public int compareTo(User other) {
		return (int) (this.emailAddress.compareTo(other.getEmailAddress()) + this.userHandle.compareTo(other.getUserHandle()));
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailAddress, userHandle);
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
		return Objects.equals(emailAddress, other.emailAddress)
				&& Objects.equals(userHandle, other.userHandle);
	}

	@Override
	public String toString() {
		StringBuilder userInString = new StringBuilder();
		userInString.append("User [userId=");
		userInString.append(userId);
		userInString.append("\n, userHandle=");
		userInString.append(userHandle);
		userInString.append("\n, firstName=");
		userInString.append(firstName);
		userInString.append("\n, lastName=");
		userInString.append(lastName);
		userInString.append("\n, age=");
		userInString.append(age);
		userInString.append("\n, password=");
		userInString.append(password);
		userInString.append("\n, emailAddress=");
		userInString.append(emailAddress);
		userInString.append("\n, createdDate=");
		userInString.append(createdDate);
		userInString.append("\n, profilePic=");
		userInString.append(profilePic);
		userInString.append("\n, coverImage=");
		userInString.append(coverImage);
		userInString.append("\n]");

		return userInString.toString();
	}
}
