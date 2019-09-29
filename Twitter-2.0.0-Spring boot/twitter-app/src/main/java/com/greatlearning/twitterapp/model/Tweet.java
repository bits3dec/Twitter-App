package com.greatlearning.twitterapp.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tweets")
public class Tweet implements Comparable<Tweet>, Serializable {
	private static final long serialVersionUID = 1421404139600587423L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    @JsonBackReference
	private User user;
	
	private int likes;
	
	private int comments;
	
	private Tweet() {}
	
	public Tweet(String message, User user) {
		this.message = message;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public int getLikes() {
		return likes;
	}
	public void addLike() {
		this.likes++;
	}

	public int getComments() {
		return comments;
	}
	public void addComment() {
		this.comments++;
	}

	@Override
	public int compareTo(Tweet other) {
		return this.getId().compareTo(other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user);
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
		return Objects.equals(id, other.id) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		if(user == null) {
			return "Tweet [id=" + id + ", message=" + message + ", likes=" + likes + ", comments="
					+ comments + "]";
		}
		return "Tweet [id=" + id + ", message=" + message + ", userHandle=" + user.getUserHandle() + ", likes=" + likes + ", comments="
				+ comments + "]";
	}
}
