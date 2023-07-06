package com.rgt.onlinebidding.factory;

import com.rgt.onlinebidding.entity.User;

public class Antique implements Item {
	private String name;
	private String description;
	private Double currentHighestBid;
	private String age;
	private String origin;
	private User highestBidder;

	public Antique(String name, String description, Double currentHighestBid, String age, String origin) {
		super();
		this.name = name;
		this.description = description;
		this.currentHighestBid = currentHighestBid;
		this.age = age;
		this.origin = origin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCurrentHighestBid() {
		return currentHighestBid;
	}

	public void setCurrentHighestBid(Double currentHighestBid) {
		this.currentHighestBid = currentHighestBid;
	}

	public User getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(User highestBidder) {
		this.highestBidder = highestBidder;
	}

	@Override
	public void placeBid(User user, double bidAmount) {

	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
