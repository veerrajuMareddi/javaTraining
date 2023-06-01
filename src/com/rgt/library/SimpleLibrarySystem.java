package com.rgt.library;

import java.util.Scanner;

public class SimpleLibrarySystem {

	public static final int MAX_PATRONS = 5;
	public static final int MAX_BOOKS = 5;

	public static Patron[] patrons = new Patron[MAX_PATRONS];

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int choice;
		int numUsers = 0;
		int numOfBooks = 0;
		Books books = new Books();

		boolean flag = false;
		Patron loginUser = new Patron();
		while (true) {
			System.out.println("Library Management System");

			System.out.println("1. Create Account");

			System.out.println("2. Login");

			System.out.println("3. Exit");
			choice = scanner.nextInt();

			switch (choice) {

			case 1:
				Patron user = new Patron();

				System.out.println("Enter Patron Name :");
				String name = scanner.next();
				user.setName(name);

				System.out.println("Enter PIN: ");
				int pin = scanner.nextInt();
				user.setPin(pin);
				if (numUsers < MAX_PATRONS) {
					patrons[numUsers++] = user;
				} else {
					System.out.println("No of patrons execeded");
					break;
				}

				break;

			case 2:
				System.out.println("Enter UserName : ");
				String userName = scanner.next();

				System.out.println("Enter PIN: ");
				int pinNumber = scanner.nextInt();

				for (Patron current : patrons) {
					if (current != null && current.getName().equals(userName) && current.getPin() == pinNumber) {
						loginUser = current;
						flag = true;
					}
				}
				if (flag == false) {
					System.out.println("Please enter valid Credentials");
					break;
				}
				do {
					System.out.println("Choose an option: ");

					System.out.println("1. AddBook");

					System.out.println("2. Borrow Book");

					System.out.println("3. Return Book");

					System.out.print("Choose an option: ");

					choice = scanner.nextInt();
					switch (choice) {

					case 1:

						System.out.println("Enter book name: ");
						String bookname = scanner.next();

						System.out.println("Enter Author name: ");
						String author = scanner.next();				
						if (numOfBooks < MAX_BOOKS)
							books.addBook(bookname, author, numOfBooks);
						numOfBooks++;
						break;

					case 2:

						System.out.println("Enter book name: ");						
						String booktitle = scanner.next();
						boolean value = books.borrowBook(booktitle, loginUser, numOfBooks);
						if (value == true) {
							numOfBooks--;
						}else {
							System.out.println("The Book Named : "+booktitle +"Not Found");
						}

						break;

					case 3:
						System.out.println("Return book name:");
						String bookName = scanner.next();
						boolean val = books.returnBook(bookName, loginUser, numOfBooks);
						if (val) {
							numOfBooks++;
						}
						else {
							System.out.println("The Book Named : "+bookName +"Not Found");
						}
						break;
					default:
						System.out.println("Invalid option. Please try again.");
					}
				} while (numUsers >= 0); {
				break;
			}
			}

		}
	}
}
