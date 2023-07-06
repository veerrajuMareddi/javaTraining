package com.rgt.onlinebidding.services;

import java.util.ArrayList;
import java.util.List;

import com.rgt.onlinebidding.entity.Bid;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.factory.Item;
import com.rgt.onlinebidding.factory.PicassoPainting;
import com.rgt.onlinebidding.observer.OberverService;
import com.rgt.onlinebidding.observer.Observer;

public class UserManagementService implements OberverService {
	private static User currentUser;
	private static List<User> users = new ArrayList<User>();
	public static List<Observer> observers = new ArrayList<>();

	public UserManagementService() {

	}

	public void createUser(String username, String password) {
		User user = new User(username, password);
		users.add(user);
		observers.add(user);
	}

	public User authenticateUser(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;
				return user;
			} else {
				System.out.println("Authentication failure..");
			}
		}
		return null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public List<User> getALl() {
		return users;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserver(Item item) {
		for (Observer observer : observers) {
			observer.update(item, observer);
		}
	}

	public void getUser(User highestBidder, Item item) {

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getUserName().equals(highestBidder.getUserName())) {
				for (Bid bid : user.getBiddingHistory()) {
					if (bid.getItem().getName().equals(item.getName())) {
						bid.setWinningBid(true);

						// Replace the bid in the bidding history
						List<Bid> biddingHistory = user.getBiddingHistory();
						int index = biddingHistory.indexOf(bid);
						if (index >= 0) {
							biddingHistory.set(index, bid);
						}

						// Update the bidding history for the user
						user.setBiddingHistory(biddingHistory);

						// Replace the user in the users collection
						users.set(i, user);
					}
				}
			}
		}

	}

	public boolean checkItemAvailbility(Item item) {

		User user = item.getHighestBidder();
		if (user != null) {
			for (Bid bid : user.getBiddingHistory()) {
				if (bid.getItem().getName().equals(item.getName()) && bid.isWinningBid()) {
					return false;
				}
			}
		} else {
			return true;
		}
		return true;
	}
}
