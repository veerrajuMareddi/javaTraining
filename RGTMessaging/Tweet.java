package com.rgt.messaging;

import java.util.List;

public class Tweet {
private String id;
private String content;
private String author;
private String timestamp;
private int likes;
private int dislikes;
private List<String> comments;

public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
public List<String> getComments() {
	return comments;
}
public void setComments(List<String> comments) {
	this.comments = comments;
}
private static int count=100;

public Tweet(String content, String author, String timestamp) {
	super();
	this.id = "RGT_TWEET@"+count++;
	this.content = content;
	this.author = author;
	this.timestamp = timestamp;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}
public int getDislikes() {
	return dislikes;
}
public void setDislikes(int dislikes) {
	this.dislikes = dislikes;
} 


}
