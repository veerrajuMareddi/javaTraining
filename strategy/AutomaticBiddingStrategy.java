package com.rgt.onlinebidding.strategy;

import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.factory.Item;
import com.rgt.onlinebidding.services.UserManagementService;

public class AutomaticBiddingStrategy implements BiddingStrategy{
UserManagementService managementService=new UserManagementService();
    public Double bid(Item item, User user,Double bidAmount) {
		Double currentBid=item.getCurrentHighestBid();
		Double newBid=currentBid+10000;
		item.placeBid(user,newBid);
		managementService.notifyObserver(item);
		return newBid;
	}
}
