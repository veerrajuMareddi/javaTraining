package com.rgt.messaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author VeerrajuMareddi
 *
 */
public class RGTMesssageService {
	DataStore dataStore = new DataStore();
	Scanner scanner = new Scanner(System.in);
	List<User> users = new ArrayList<>();

	public void addUser(User user) {
		if (!dataStore.isUserExist(user)) {
			dataStore.addUser(user);
			System.out.println("Succesfully user Registered");
		} else {
			System.out.println("User already Exists");
		}
	}

	public void postTweet() {
		System.out.println("Enter Content");
		String content = scanner.next();
		dataStore.postTweet(content);
	}

	public boolean login(String name, String password) {
		users = dataStore.getUsers();
		boolean login = false;
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name) && user.getPassword().equals(password)) {
				dataStore.loginUser(user);
				login = true;
				System.out.println("Welocme to RGTMessaging :" + user.getUsername());
				break;
			}
		}
		return login;
	}

	public void browseTweets() {

		for (Tweet tweet : dataStore.getAllTweets()) {
			System.out.println();
			System.out.println("======================================");
			System.out.println("Tweet Id :" + tweet.getId());
			System.out.println("message  :" + tweet.getContent());
			System.out.println("Author   :" + tweet.getAuthor());
			System.out.println("posted   :" + tweet.getTimestamp());
			System.out.println("Likes    :" + tweet.getLikes());
			System.out.println("DisLikes :" + tweet.getDislikes());

			System.out.println("======================================");
			System.out.println();

		}
	}

	public void viewProfile() {
		User user = dataStore.getLoginUser();
		System.out.println("============================================");
		System.out.println("FullName :" + user.getName());
		System.out.println("UserName :" + user.getUsername());
		System.out.println("Password :" + user.getPassword());
		System.out.println("Bio      :" + user.getBio());
		System.out.println("=============================================");

	}

	public void viewUsers() {
		for (User user : users) {
			System.out.println("userName :" + user.getUsername());
		}
	}

	/**
	 * searches the users existence and provides the 
	 * option to follow him
	 *  
	 * @param user
	 * @param scanner
	 */
	public void searchUser(String user, Scanner scanner) {
		boolean flag = false;
		for (User use : users) {
			if (use.getUsername().equalsIgnoreCase(user)) {
				System.out.println("============================================");
				System.out.println("FullName :" + use.getName());
				System.out.println("UserName :" + use.getUsername());
				System.out.println("Password :" + use.getPassword());
				System.out.println("Bio      :" + use.getBio());
				System.out.println("=============================================");
				System.out.println();

				System.out.println("Press 1 to follow,0 to Home");
				int result = scanner.nextInt();
				if (result == 1) {
					dataStore.follow(use);
					flag = true;
				}
				break;
			}
		}
		if (!flag)
			System.out.println("No user Found");
	}

	public void viewTimeLine() {
		List<Tweet> tweets = dataStore.getTweetsOfloginUser();

		for (Tweet tr : tweets) {
			System.out.println();
			System.out.println("======================================");
			System.out.println("Tweet Id :" + tr.getId());
			System.out.println("message  :" + tr.getContent());
			System.out.println("Author   :" + tr.getAuthor());
			System.out.println("posted   :" + tr.getTimestamp());
			System.out.println("Likes    :" + tr.getLikes());
			System.out.println("DisLikes :" + tr.getDislikes());

			System.out.println("======================================");
			System.out.println();
		}
		System.out.println("***************************************************************");
		System.out.println("***************************************************************");
		System.out.println("***************************************************************");
		System.out.println();

		User user = dataStore.getLoginUser();
		if (Optional.ofNullable(user.getFollowings()).orElse(null) != null) {
			for (String name : user.getFollowings()) {
				System.out.println("following :" + name);
				System.out.println();

			}
		}

	}

	public void like_dislike(String conetent, Scanner scanner) {
		boolean like = false;
		for (Tweet tweet : dataStore.getAllTweets()) {
			if (tweet.getContent().equalsIgnoreCase(conetent)) {
				like = true;
				System.out.println("Enter 1 for like or 0 for Dislike");
				int i = scanner.nextInt();
				if (i == 1) {
					int likes = tweet.getLikes();
					tweet.setLikes(++likes);
				} else {
					int disLikes = tweet.getDislikes();
					tweet.setDislikes(++disLikes);
				}
			}

		}
		if (!like) {
			System.out.println("No tweet is there with content :" + conetent);
		}
	}

}
