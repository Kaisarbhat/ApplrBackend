package com.applr.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Post {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String caption;
	private String image;
	private String video;
	@ManyToOne
	private User user;
	@OneToMany
	private List<User> likedPost = new ArrayList<>();
	@OneToMany
	private List<Comment> comments = new ArrayList<>();
	private LocalDateTime createdAt;
	
	
	public Post() {
	
	}
	
	public Post(int id, String caption, String image, String video, User user, List<User> likedPost,
			LocalDateTime createdAt, List<Comment> comments) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.likedPost = likedPost;
		this.createdAt = createdAt;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<User> getLikedPost() {
		return likedPost;
	}

	public void setLikedPost(List<User> likedPost) {
		this.likedPost = likedPost;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
		
}
