package com.rgt.training.session2basics.simpleatm;

public class UserAccount {
	int accNo;
	int pin;
	double balance;
	double[] transactions=new double[10];
	int transactionCount=0;

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

	

	public double[] getTransactions() {
		return transactions;
	}

	
	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactions(int index, double amount) {
        if (index >= 0 && index < transactions.length) {
            transactions[index] = amount;
        } else {
            System.out.println("Invalid transaction index.");
        }
    }

	public UserAccount() {

	}

}
