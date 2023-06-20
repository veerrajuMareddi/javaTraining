package com.rgt.messaging;

import java.util.Scanner;

/**
 * @author VeerrajuMareddi
 *
 */
public class RGTMain {
	public static RGTMesssageService messsageService = new RGTMesssageService();
	static boolean login;
	static boolean exit = false;

	public static void main(String[] args) {
		while (!exit) {
			if (login == false) {
				System.out.println("RGT Messaging:");

				System.out.println("1. Register");

				System.out.println("2. Login");

				System.out.println("3. Exit");

				System.out.print("Choose an option: ");
				Scanner scanner = new Scanner(System.in);

				int choice = scanner.nextInt();

				switch (choice) {

				case 1:
					addUser(scanner);
					break;
				case 2:
					loginUser(scanner);
					break;
				case 3:
					System.out.println("Shutting Down");
					exit = true;
					break;
				default:
					System.out.println("Enter valid input");
				}
			} else {
				System.out.println("1. PostTweet:");

				System.out.println("2. view your timeline");

				System.out.println("3. search for users");

				System.out.println("4. view your profile");

				System.out.println("5. Browse Tweets");

				System.out.println("6. Browse Users");
				
				System.out.println("7. Like or dislike Tweet");

				System.out.println("8. logout");
				Scanner scanner = new Scanner(System.in);

				int choice = scanner.nextInt();

				switch (choice) {

				case 1:
					postTweet(scanner);
					break;
				case 2:
					viewTimeLine();
					break;
				case 3:
					serachUser(scanner);
					break;
				case 4:
					viewProfile();
					break;
				case 5:
					browseTweets(scanner);
					break;
				case 6:
					viewUsers();
					break;
				case 7:
					like_dislike(scanner);
					break;
				case 8:
					login = false;
					break;
				}
			}
		}

	}
/**
 * 
 * @param scanner
 */
	private static void like_dislike(Scanner scanner) {
		System.out.println("Enter the tweet name :");
		String conetent=scanner.next();
		messsageService.like_dislike(conetent,scanner);
	}

	private static void viewUsers() {
		messsageService.viewUsers();
	}

// if credentials are not matched then login set to false
	private static void loginUser(Scanner scanner) {
		System.out.println("Enter your userName");
		String name = scanner.next();
		System.out.print("Enter your password: ");
		String password = scanner.next();
		login = messsageService.login(name, password);
	}

//registering a new user to RGTMessaging
	private static void addUser(Scanner scanner) {
		System.out.println("Enter Your Full Name");
		String name = scanner.next();
		System.out.println("create User Name: ");
		String userName = scanner.next();
		System.out.println("Enter Password :");
		String password = scanner.next();
		System.out.println("Update the Bio");
		String bio = scanner.next();
		User user = new User(userName, password, name, bio);
		messsageService.addUser(user);
	}

//Browses entire Tweets of all users
	private static void browseTweets(Scanner scanner) {
		messsageService.browseTweets();
	}

//Shows logged in user Profile
	private static void viewProfile() {
		messsageService.viewProfile();
	}

//Searches User by name and gives the option to follow if user Exists
	private static void serachUser(Scanner scanner) {
		System.out.println("Enter userName :");
		String user = scanner.next();
		messsageService.searchUser(user, scanner);
	}

//Shows user recent activity of tweets 
	private static void viewTimeLine() {
		messsageService.viewTimeLine();
	}

	private static void postTweet(Scanner scanner) {
		messsageService.postTweet();

	}
}
