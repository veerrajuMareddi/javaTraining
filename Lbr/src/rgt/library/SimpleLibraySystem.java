package rgt.library;

import java.util.Scanner;

public class SimpleLibraySystem {
	public static final int MAX_PATRONS = 5;
	public static final int MAX_BOOKS = 5;

	public static String[] bookTitles = new String[MAX_BOOKS];
	public static String[] patrons = new String[MAX_PATRONS];
	public static String[] libraryRecords = new String[MAX_BOOKS];

	public static int bookCount = 0;
	public static int patronCount = 0;
	public static int recordsCount = 0;
	public static boolean exit = false;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String message;
		int choice = 0;
		while (!exit) {
			System.out.println("Library Management System");

			System.out.println("1. AddBook");

			System.out.println("2. Add Patron");

			System.out.println("3. Borrow Book");

			System.out.println("4. Return Book");
			
			System.out.println("5. BooksAvailble");
			
			System.out.println("6. Patrons List");

			System.out.println("7. Exit");
			
			boolean input=false;
			while(!input) {
			try {
				choice = Integer.parseInt( scanner.next());
				input=true;
			} catch (NumberFormatException e) {
				System.out.println("Please Enter valid Input :");
			}}

			switch (choice) {

			case 1:
				System.out.println("Enter Book Name :");
				String title = scanner.next();
				System.out.println("Enter AuthorName: ");
				scanner.next();
				 message = addBook(title);
				System.out.println(message);
				break;

			case 2:
				System.out.println("Enter Patron Name :");
				String patron = scanner.next();
				 message = addPatron(patron);
				System.out.println(message);
				break;
			case 3:
				System.out.println("Enter Patron Name :");
				String patronName = scanner.next();
				System.out.println("Enter BookTitle :");
				String titleName = scanner.next();
				 message = borrowBook(patronName, titleName);
				System.out.println(message);
				break;
			case 4:
				System.out.println("Enter Patron Name :");
				String patronName1 = scanner.next();
				System.out.println("Enter Patron Name :");
				String title2 = scanner.next();
				returnBook(patronName1, title2);
				break;
			case 5:
				System.out.println("Availble Books in Library are :");
				booksAvailable();
				break;
			case 6:
				System.out.print("Patrons Available :");
				patronList();
				break;
			case 7:
				exit = true;
				System.out.println("---Library closed----");
				break;
			default:
				System.out.println("Please choose a valid Option");
				System.out.println();
			}

		}
	}

	private static void booksAvailable() {
		if(bookCount>0)
		{
			for (int i = 0; i < bookCount; i++) {
				if (bookTitles[i] != null) {
					System.out.println(bookTitles[i]);
				}
			}
		}
		else {
			System.out.println("Currently No Books are Avilable");
			System.out.println();
		}
	}
	private static void patronList() {
		if(patronCount>0)
		{
			for (int i = 0; i < patronCount; i++) {
				if (patrons[i] != null) {
					System.out.println(patrons[i]);
				}
			}
		}
		else {
			System.out.println("None");
		}
	}

	private static String addBook(String title) {
		if (bookCount < MAX_BOOKS) {
			boolean add = false;
			for (int i = 0; i <= bookCount; i++) {
				if (bookTitles[i] != null && bookTitles[i].equals(title)) {
					add = true;
				}
			}
			if (add) {
				return ("Book already presnt In Librarys");
			}
			bookTitles[bookCount] = title;
			bookCount++;
			return "successfully Book added";
		} else {
			return "Max book count Reached";
		}
	}

	private static String addPatron(String patron) {
		if (patronCount < MAX_PATRONS) {
			boolean add = false;
			for (int i = 0; i <= patronCount; i++) {
				if (patrons[i] != null && patrons[i].equals(patron)) {
					add = true;
				}
			}
			if (add) {
				return "Patron already presnt In Librarys";
			}
			patrons[patronCount] = patron;
			patronCount++;
			return "Patron added successfully";
		} else {
			return "Max patron count Reached";
		}
	}

	private static void returnBook(String patronName1, String title2) {
		int indexToRemove = -1;
		for (int i = 0; i < recordsCount; i++) {
			String[] val = libraryRecords[i].split(":");
			if (val[0].equals(patronName1) && val[1].equals(title2)) {
				bookTitles[bookCount] = val[1];
				System.out.println("Book Returned to Library successfully");
				bookCount++;
				indexToRemove = i;
				break;
			}
		}
		if (indexToRemove != -1) {
			for (int i = indexToRemove + 1; i < recordsCount; i++) {
				libraryRecords[i - 1] = libraryRecords[i];
			}
			libraryRecords[recordsCount - 1] = null;
			recordsCount--;
		} else {
			System.out.println("Book not Found in borrowed List");
		}
	}

	private static String borrowBook(String patronName, String titleName) {
		int checkPatron = -1;
		for (int i = 0; i < patronCount; i++) {
			if (patrons[i] != null && (patrons[i].equals(patronName))) {
				checkPatron = 1;
			}
		}
		if (checkPatron != 1) {
			return "Patron not found in the library";
		}
		int indexToRemove = -1;
		for (int i = 0; i < bookCount; i++) {
			if (bookTitles[i].equals(titleName)) {
				indexToRemove = i;
			}
		}
		if (indexToRemove != -1) {
			for (int i = indexToRemove + 1; i < bookCount; i++) {
				bookTitles[i - 1] = bookTitles[i];
			}
			bookTitles[bookCount - 1] = null;
			bookCount--;
			libraryRecords[recordsCount] = patronName + ":" + titleName;
			recordsCount++;
			return "Book borrowed Succesfully";
		} else {
			return "No matching book found.";
		}

	}

}
