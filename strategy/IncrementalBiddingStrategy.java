package com.rgt.onlinebidding.strategy;

import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.factory.Item;
import com.rgt.onlinebidding.factory.PicassoPainting;
import com.rgt.onlinebidding.services.UserManagementService;

public class IncrementalBiddingStrategy implements BiddingStrategy{
	UserManagementService managementService=new UserManagementService();

    public Double bid(Item item, User user,Double bidAmount) {
        double newBid =  bidAmount+item.getCurrentHighestBid();
        item.placeBid(user, newBid);
		managementService.notifyObserver(item);

        return newBid;
    }

}
