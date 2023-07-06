package com.rgt.onlinebidding;

import com.rgt.onlinebidding.helper.InputHandler;
import com.rgt.onlinebidding.helper.ItemHandler;

public class OnlineBiddingSystem {
	public static void main(String[] args) {
		InputHandler inputHandler = new InputHandler();
		boolean login = false;
		while (!login) {
			boolean retry = false;
			while (!retry) {
				retry = inputHandler.startProcess();
			}
			login = true;
			while (login) {
				System.out.println("1. Search Items");
				System.out.println("2. View Bidding History");
				System.out.println("3. Logout");
				switch (inputHandler.handleInput()) {
				case 1:
					ItemHandler.searchItems();
					break;
				case 2:
					ItemHandler.biddingHistory();
					break;
				case 3:
					login = inputHandler.logout();
					break;
				}
			}

		}
	}
}
