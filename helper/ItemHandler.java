package com.rgt.onlinebidding.helper;

import java.util.List;
import java.util.Scanner;

import com.rgt.onlinebidding.entity.Bid;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.factory.Item;
import com.rgt.onlinebidding.factory.ItemFactory;
import com.rgt.onlinebidding.services.ItemManagementService;
import com.rgt.onlinebidding.services.UserManagementService;
import com.rgt.onlinebidding.strategy.AutomaticBiddingStrategy;
import com.rgt.onlinebidding.strategy.BiddingStrategy;
import com.rgt.onlinebidding.strategy.IncrementalBiddingStrategy;

public class ItemHandler {
	public static Scanner scanner = new Scanner(System.in);

	static ItemManagementService itemManagementService = new ItemManagementService();

	static UserManagementService managementService = new UserManagementService();

	public static void addItems() {

		System.out.println("1.Piccasso Item");
		System.out.println("2.Antique Item");

		System.out.print("Choose the item: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			System.out.println("Enter Painting name");
			String name = scanner.nextLine();
			System.out.print("Enter item description: ");
			String description = scanner.nextLine();
			System.out.print("Enter starting bid amount: ");
			double startingBid = scanner.nextDouble();
			scanner.nextLine(); // Consume newline character
			Item item = ItemFactory.createItem(name, description, startingBid);
			itemManagementService.addItem(item);
			System.out.println("Item added successfully.");
			return;
		case 2:
			System.out.println("Enter item name");
			name = scanner.nextLine();
			System.out.print("Enter item description: ");
			description = scanner.nextLine();
			System.out.print("Enter starting bid amount: ");
			startingBid = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter the age of item");
			String age = scanner.nextLine();
			System.out.println("Enter its origin");
			String origin = scanner.nextLine();
			item = ItemFactory.createItem(name, description, startingBid, age, origin);
			itemManagementService.addItem(item);
			System.out.println("Item added successfully.");
			return;
		}
	}

	public static void searchItems() {
		System.out.print("Enter search keyword: ");
		String keyword = scanner.nextLine();
		List<Item> searchResults = itemManagementService.searchItems(keyword);
		System.out.println("Serach results: ");
		int count = 1;
		for (Item item : searchResults) {
			System.out.println(count + "." + item.getName() + "-" + item.getDescription() + "-Current Highest bid : Rs "
					+ item.getCurrentHighestBid());
			count++;
		}if(!searchResults.isEmpty()) {
		System.out.print("Enter an item index to place a bid, or '0' to go back: ");
		Integer selectedIndex = Integer.parseInt(scanner.next());
		if (selectedIndex >= 1 && selectedIndex <= searchResults.size()) {
			Item selectedItem = searchResults.get(selectedIndex - 1);
			System.out.print("Enter a bid amount: Rs ");
			double bidAmount = scanner.nextDouble();
			boolean enter = false;
			while (!enter) {
				if (selectedItem.getCurrentHighestBid() > bidAmount) {
					System.out.println("Amount Should be Greater than currentBid");
				} else {
					enter = true;
				}
			}
			System.out.println("1. Incremental Bidding");
			System.out.println("2. Automatic Bidding");
			System.out.print("Choose a bidding strategy: ");
			Integer choice = scanner.nextInt();
			BiddingStrategy biddingStrategy;
			switch (choice) {
			case 1:
				biddingStrategy = new IncrementalBiddingStrategy();
				break;
			case 2:
				biddingStrategy = new AutomaticBiddingStrategy();
				break;
			default:
				System.out.println("Invalid option. Placing bid using Incremental Bidding strategy.");
				return;
			}
		
			User currentUser = managementService.getCurrentUser();
			double newBidding = biddingStrategy.bid(selectedItem, currentUser, bidAmount);
			System.out.println("Bid placed succesfully. your bid amount :" + newBidding);}
		else {
			System.out.println("No Items Found");
			return;
		}

		}

	}

	public static void biddingHistory() {
		User currentUser = managementService.getCurrentUser();
		if (currentUser != null) {
			List<Bid> biddingHistory = currentUser.getBiddingHistory();
			if (biddingHistory.isEmpty()) {
				System.out.println("Your bidding history is empty");
			} else {
				System.out.println("Your bidding history:");
				for (Bid bid : biddingHistory) {
					Item item = bid.getItem();
					System.out.println(item.getName() + " - " + item.getDescription() + " - Bid amount: Rs "
							+ bid.getBidAmount() + " - Bidding Person: " + currentUser.getUserName());
				}
			}
		} else {
			System.out.println("You are not logged in. Please log in.");
		}
	}

	public static void closeBid() {
		scanner.nextLine();
		System.out.println("Enter item name :");
		String item=scanner.nextLine();
		itemManagementService.closeBid(item);		
	}
}
