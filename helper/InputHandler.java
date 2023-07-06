package com.rgt.onlinebidding.helper;

import java.util.Scanner;

import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.services.ItemManagementService;
import com.rgt.onlinebidding.services.UserManagementService;

public class InputHandler {
	public static User loginUser;
	public static Scanner scanner = new Scanner(System.in);

	public static final String USERNAME = "admin";
	public static final String PASSWORD = "password";
	UserManagementService managementService = new UserManagementService();
	ItemManagementService itemManagementService = new ItemManagementService();
	boolean userLog=false;

	public boolean startProcess() {
		System.out.println("Welcome to the Online Bidding System!");
		System.out.println("1. Login");
		System.out.println("2. Create Account");
		System.out.println("3. AdminPanel");
		System.out.println("4. Exit");
		System.out.println("Choose Your Option :");
		switch (handleInput()) {
		case 1:
			login();
			break;
		case 2:
			createAccount();
			break;
		case 3:
			adminPanel();
			break;
		case 4:
			exit();
			break;
		}
		return userLog;
	}

	private void adminPanel() {
		scanner.nextLine();
		System.out.println("Enter Admin ID:");
		String name = scanner.nextLine();
		System.out.println("Enter Admin Password:");
		String password = scanner.nextLine();
		if (name.equals(USERNAME) && password.equals(PASSWORD)) {
			userLog=false;
			boolean login = true;
			while (login) {
				System.out.println("1. AddItems");
				System.out.println("2. CloseBid");
				System.out.println("3. Logout");
				switch (handleInput()) {
				case 1:
					ItemHandler.addItems();
					break;
				case 2:
					ItemHandler.closeBid();
					break;
				case 3:
					login = false;
					break;
				}}
		} else {
			System.out.println("Access denied");
		}
	}

	private void exit() {
		System.exit(0);
	}

	private void createAccount() {
		scanner.nextLine();
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String pwd = scanner.nextLine();
		managementService.createUser(username, pwd);
		System.out.println("Account created successfully..");
		startProcess();
	}

	private void login() {
		scanner.nextLine();
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String pwd = scanner.nextLine();

		loginUser = managementService.authenticateUser(username, pwd);
		if (loginUser != null) {
			userLog=true;
			System.out.println("Login Sucessful. Welcome, " + loginUser.getUserName());
		} else {
			System.out.println("Invalid username or password. Please try again..");
			startProcess();
		}
	}

	public int handleInput() {
		boolean success = false;
		int choice = 0;
		while (!success) {
			try {
				choice = Integer.parseInt(scanner.next());
				success = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter Valid input");
			}

		}
		return choice;
	}

	public boolean logout() {
		System.out.println("Logged out successfully");
		return false;
	}

}
