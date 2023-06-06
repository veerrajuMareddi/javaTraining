package com.rgt.training.session2basics.simpleatm;

import java.util.Scanner;

public class SimpleATM_WithArrays {
	public static void main(String[] args) {
		ATMWithArrays atm = new ATMWithArrays();
		Scanner scanner = new Scanner(System.in);
		int choice;
		int i = 0;
		boolean flag = false;
		boolean loop=true;
		UserAccount[] userlist = new UserAccount[5];
		UserAccount loginUser = new UserAccount();
		while (loop) {
				System.out.println("Welcome to the ATM");

				System.out.println("1. Create Account");

				System.out.println("2. Login");

				System.out.println("3. Exit");

				System.out.print("Choose an option: ");

				choice = scanner.nextInt();

				switch (choice) {

				case 1:
					UserAccount user = new UserAccount();

					System.out.print("Enter account number: ");
					int accountNumber = scanner.nextInt();
					System.out.println("Account Number :" + accountNumber);
					user.setAccNo(accountNumber);
					
					System.out.print("Enter PIN: ");
					int pin = scanner.nextInt();
					user.setPin(pin);
					
					System.out.println("Enter Minimum  balance:");
					double balance = scanner.nextDouble();
					user.setBalance(balance);
					userlist[i++] = user;

					break;

				case 2:
					System.out.print("Enter Account number: ");
					int accountnumber = scanner.nextInt();
					
					System.out.println("Enter PIN: ");
					int pinNumber = scanner.nextInt();
					
					for (UserAccount acc : userlist) {
						if (acc != null && acc.getAccNo() == accountnumber && acc.getPin() == pinNumber) {		
									loginUser = acc;
									flag = true;		
						}
					}
					if (flag==false) {
						System.out.println("Please enter valid Credentials");
						break;
					}
					System.out.println("Create PIN:" + pinNumber);

					do {
						System.out.println("Welcome to the ATM Simulator");
						System.out.println("AccoutNumber :"+ loginUser.getAccNo());
						System.out.println("1. Check Balance");
						System.out.println("2. Deposit");
						System.out.println("3. Withdraw");
						System.out.println("4. View Transaction History");
						System.out.println("5. Logout");
						System.out.print("Choose an option: ");
						choice = scanner.nextInt();

						switch (choice) {
						case 1:
							System.out.println("Current balance: $" + atm.checkBalance(loginUser.getBalance()));
							break;
						case 2:
							System.out.print("Enter deposit amount: $");
							double depositAmount = scanner.nextDouble();
							atm.deposit(depositAmount, loginUser);
							break;
						case 3:
							System.out.print("Enter withdrawal amount: $");
							double withdrawalAmount = scanner.nextDouble();
							atm.withdraw(withdrawalAmount, loginUser);
							break;
						case 4:
							atm.showTransactionHistory(loginUser);
							break;
						case 5:
							System.out.println("Goodbye!");
							break;
						default:
							System.out.println("Invalid option. Please try again.");
						}

						System.out.println();

					} while (choice != 5);
					{
						break;
					}
				case 3:
					loop=false;
					System.out.println("----Closed----");
					break;
				}
		}
	}
}

class ATMWithArrays {
double balance;
	public double checkBalance(double balance) {
		return balance;
	}

	public void deposit(double amount, UserAccount acc) {
		if (amount > 0) {
			 balance = acc.getBalance();
			int val = acc.getTransactionCount();
			balance += amount;
			acc.setBalance(balance);
			acc.setTransactions(val, amount);
			acc.setTransactionCount(++val);
			System.out.println("Deposit successful!");
		} else {
			System.out.println("Invalid deposit amount.");
		}
	}

	public void withdraw(double amount, UserAccount acc) {
		if (amount > acc.getBalance()) {
			System.out.println("Insufficient balance.");
		} else if (amount <= 0) {
			System.out.println("Invalid withdrawal amount.");
		} else {
			 balance = acc.getBalance();
			balance -= amount;
			int val = acc.getTransactionCount();
			acc.setTransactions(val, -amount);
			acc.setTransactionCount(++val);
			acc.setBalance(balance);
			System.out.println("Withdrawal successful!");
		}
	}

	public void showTransactionHistory(UserAccount acc) {
		System.out.println("Transaction History:");
		for (double transactAmount : acc.getTransactions()) {
			if (transactAmount > 0) {
				System.out.println("Deposit: $" + transactAmount);
			}
			if (transactAmount < 0) {
				System.out.println("Withdraw: $" + transactAmount);
			}
		}
	}
}
