package com.rgt.onlinebidding.factory;

import com.rgt.onlinebidding.entity.User;

public interface Item {
	
	public String getName();
	public String getDescription();
	public User getHighestBidder() ;
	public Double getCurrentHighestBid() ;

	public void setName(String name);
	public void setDescription(String description) ;
	public void setCurrentHighestBid(Double currentHighestBid);
	public void setHighestBidder(User highestBidder) ;
	public void placeBid(User user, double bidAmount);

}
