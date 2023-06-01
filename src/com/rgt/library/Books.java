package com.rgt.library;

public class Books {
	public static final int MAX_BOOKS = 5;
	public static final int MAX_PATRONS = 5;
	public static Books[] totalBooks = new Books[MAX_BOOKS];

	
	String bookTitle;
	String author;
	int bookCount = 0;

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Books() {
		super();
	}
	
	public Books(String bookName, String author) {
		this.bookTitle = bookName;
		this.author = author;
	}
	
	public void addBook(String bookName, String author, int indexOfBook) {

		totalBooks[indexOfBook] = new Books(bookName, author);

	}

	public boolean borrowBook(String bookName, Patron person,int bookElemnts) {
		Books bx = new Books();
		int indexToRemove = -1;
		for (int i = 0; i < bookElemnts; i++) {
			bx = totalBooks[i];
			if (bx.getBookTitle().equals(bookName)) {
				person.setBooks(person.getBookCount(), bx);
				int val=person.getBookCount();
				person.setBookCount(val+1);
				indexToRemove=i;
			}
		}
		if (indexToRemove != -1) {
			if (indexToRemove < bookElemnts - 1) {
				System.arraycopy(totalBooks, indexToRemove + 1, totalBooks, indexToRemove,
						bookElemnts - indexToRemove - 1);
			}
			totalBooks[bookElemnts - 1] = null;
			return true;
		}
		return false;
	}
	public boolean returnBook(String bookName,Patron person,int j)
	{
		int count=0;
		Books book=null ;
		Books[] books=person.getBooks();

	for(Books k : books)
	{if(k!=null)
		if (k.getBookTitle().equals(bookName)) {
			book=k;
            count=10;
        }
	}if(count==10) {
        Books[] updatedBooks = new Books[books.length - 1];

        for (int i = 0; i < books.length; i++) {
        	if(books[i]!=null)
            if (!books[i].getBookTitle().equals(bookName)) {
                updatedBooks[j] = books[i];
            }
        }

        person.setBooks(updatedBooks);
        person.setBookCount(person.getBookCount() - 1);
        totalBooks[j]=book;
        return true;
	}
	return false;
	}        
	
}
