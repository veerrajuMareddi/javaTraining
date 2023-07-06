package com.rgt.onlinebidding.factory;

public class ItemFactory {

	public static Item createItem(String name,String description,Double startingBid) {
		return new PicassoPainting(name, description, startingBid);
	}
	public static Item createItem(String name,String description,Double startingBid,String age,String origin) {
		return new Antique(name, description, startingBid,age,origin);
	}
}
