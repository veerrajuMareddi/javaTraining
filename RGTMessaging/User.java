package com.rgt.messaging;

import java.util.List;

/**
 * @author VeerrajuMareddi
 *
 */
public class User {
	
	private String username;
	private String password;
	private String name;
	private String bio;
	private List<String> followings;
	private List<String> followers;
	private String tweets;
	
	
	public User() {
	}
	public User(String username, String password, String name, String bio) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.bio = bio;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getTweets() {
		return tweets;
	}
	public void setTweets(String tweets) {
		this.tweets = tweets;
	}
	public List<String> getFollowings() {
		return followings;
	}
	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
}
