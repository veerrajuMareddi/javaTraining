package com.rgt.messaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class DataStore {
	public List<User> userList = new ArrayList<>();
	public List<Tweet> tweets = new ArrayList<>();
	public Map<String, List<Tweet>> userTweets = new HashMap<String, List<Tweet>>();
	public User loginUser;

	public void addUser(User user) {
		userList.add(user);
	}

	public boolean isUserExist(User user) {
		return userList.contains(user) ? true : false;
	}

	public void postTweet(String content) {
		long sec = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = dateFormat.format(sec);
		Tweet tweet = new Tweet(content, loginUser.getUsername(), dateString);
		tweets.add(tweet);
		boolean val = false;
		for (Entry<String, List<Tweet>> key : userTweets.entrySet()) {
			if(key.getKey().equals(loginUser.getUsername()))
			{
				List<Tweet> tweet1 = new ArrayList<Tweet>();
				tweet1.addAll(key.getValue());
				tweet1.addAll(Arrays.asList(tweet));
				userTweets.put(loginUser.getUsername(), tweet1);
				System.out.println();
				System.out.println();
				val = true;
			}
			break;
		}
		if (!val) {
			userTweets.put(loginUser.getUsername(), Arrays.asList(tweet));
		}
	}

	public List<User> getUsers() {
		return userList;
	}

	public void loginUser(User user) {
		loginUser = user;
	}

	public List<Tweet> getAllTweets() {
		return tweets;
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void follow(User use) {
		boolean folow = false;
		
		@SuppressWarnings("unchecked")
		List<String> followings = Optional.ofNullable(loginUser.getFollowings()).orElse(new ArrayList());
		if (followings != null) {
			for (String following : followings) {
				if (following.equalsIgnoreCase(use.getUsername())) {
					folow = true;
					System.out.println("Alreday Following This User");
				}
			}
		}
		if (!folow) {
			followings.add(use.getUsername());
			loginUser.setFollowings(followings);
			List<String> followers = Optional.ofNullable(use.getFollowings()).orElse(new ArrayList());
            followers.add(loginUser.getUsername());
			use.setFollowers(followers);
			System.out.println();
			System.out.println("Successfully started following " + use.getUsername());

		}
	}

	public List<Tweet> getTweetsOfloginUser() {
		List<Tweet> tweet1 = new ArrayList<Tweet>();

		for (Entry<String, List<Tweet>> key : userTweets.entrySet()) {
			if (key.getKey().equals(loginUser.getUsername())) {
				tweet1.addAll(key.getValue());
			}
		}
		return tweet1;
	}
}