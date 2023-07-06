package com.rgt.onlinebidding.services;

import java.util.ArrayList;
import java.util.List;

import com.rgt.onlinebidding.entity.Auction;
import com.rgt.onlinebidding.factory.Item;
import com.rgt.onlinebidding.factory.ItemFactory;
import com.rgt.onlinebidding.factory.PicassoPainting;

public class ItemManagementService {
	UserManagementService managementService=new UserManagementService();

	private Auction auction;

	public ItemManagementService() {
		this.auction=Auction.getInstance();
	}

	public void addItem(Item item) {
		auction.addItem(item);
	}
	public List<Item> searchItems(String keyword){
		List<Item>searchResults=new ArrayList<Item>();
		for(Item item: auction.getItems()) {
			if(item.getName().toLowerCase().contains(keyword.toLowerCase())) {
				if(managementService.checkItemAvailbility(item)) {
					searchResults.add(item);
				}
			}
		}
		return searchResults;
	}
	public void closeBid(String itemName) {
		for(Item item: auction.getItems()) {
			if(item.getName().toLowerCase().equals(itemName)) {
				managementService.getUser(item.getHighestBidder(),item);
			}
		}
	}
	
}
