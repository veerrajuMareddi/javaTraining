package com.rgt.onlinebidding.entity;

import java.util.ArrayList;
import java.util.List;

import com.rgt.onlinebidding.factory.Item;
import com.rgt.onlinebidding.observer.Observer;
import com.rgt.onlinebidding.services.UserManagementService;

public class User implements Observer {
	UserManagementService serv = new UserManagementService();

	private String userName;
	private String password;
	private List<Bid> biddingHistory;

	public void addBid(Bid bid) {
		biddingHistory.add(bid);
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.biddingHistory = new ArrayList<Bid>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Bid> getBiddingHistory() {
		return biddingHistory;
	}

	public void setBiddingHistory(List<Bid> biddingHistory) {
		this.biddingHistory = biddingHistory;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", biddingHistory=" + biddingHistory + "]";
	}

	@Override
	public void update(Item item, Observer user) {

		for (Bid item2 : ((User) user).getBiddingHistory()) {
			if (item.getName().equals(item2.getItem().getName())
					&& item.getCurrentHighestBid() > item2.getBidAmount()) {
				System.out.println("Your OutBidded for Item " + item.getName() + " By amount "
						+ (item.getCurrentHighestBid() - item2.getBidAmount()));
			}
		}
	}



}
