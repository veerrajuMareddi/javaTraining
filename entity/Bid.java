package com.rgt.onlinebidding.entity;

import com.rgt.onlinebidding.factory.Item;

public class Bid {
	
	    private Item item;
	    private Double bidAmount;
	    private boolean winningBid;

	    public void setWinningBid(boolean winningBid) {
			this.winningBid = winningBid;
		}

		public Bid(Item item, Double bidAmount, boolean winningBid) {
	        this.item = item;
	        this.bidAmount = bidAmount;
	        this.winningBid = winningBid;
	    }

	    public Bid(Item item, Double bidAmount) {
	    	this.item = item;
	        this.bidAmount = bidAmount;
		}

		public Item getItem() {
	        return item;
	    }

	    public Double getBidAmount() {
	        return bidAmount;
	    }

	    public boolean isWinningBid() {
	        return winningBid;
	    }
	}
