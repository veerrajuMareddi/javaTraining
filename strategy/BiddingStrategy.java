package com.rgt.onlinebidding.strategy;

import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.factory.Item;

public interface BiddingStrategy {
	
	public Double bid(Item item,User user,Double bidAmount);
}
