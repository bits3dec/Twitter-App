package com.greatlearning.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;

@Entity
@Table(name = "tweet")
public class Tweet implements Comparable<Tweet>, Serializable {
	private static final long serialVersionUID = -6548479253812770763L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tweetId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "tweet_message")
	private String tweetMsg;

	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "likes")
	private int likes;

	public Tweet() {}
	
	public Tweet(User user, String tweetMsg, LocalDate createdDate) {
		this.user = user;
		this.tweetMsg = tweetMsg;
		this.createdDate = createdDate;
	}

	public long getTweetId() {
		return tweetId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getTweetMsg() {
		return tweetMsg;
	}
	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes() {
		this.likes++;
	}

	public int compareTo(Tweet other) {
		return (int) this.tweetMsg.compareTo(other.getTweetMsg());
	}

	@Override
	public int hashCode() {
		return Objects.hash(tweetMsg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		return Objects.equals(tweetMsg, other.getTweetMsg());
	}

	@Override
	public String toString() {
		return "Tweet [tweetId=" + tweetId + ", user=" + user + ", tweetMsg=" + tweetMsg + ", createdDate="
				+ createdDate + ", likes=" + likes + "]\n";
	}
}