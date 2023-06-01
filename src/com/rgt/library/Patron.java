package com.rgt.library;

public class Patron {
	
	String name;
	Books books[]=new Books[5];
	int bookCount=0;
	
	int pin;
	
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Books[] getBooks() {
		return books;
	}
	public void setBooks(int index, Books book) {
        if (index >= 0 && index < books.length) {
        	books[index] =book ;
        } else {
            System.out.println("Invalid transaction index.");
        }
    }
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public void setBooks(Books[] books2) {
		// TODO Auto-generated method stub
		
	}
	

}
