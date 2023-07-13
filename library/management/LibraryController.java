package com.rgt.library.management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryController {
    private List<Book> books;
    private String filePath;

    public LibraryController(String filePath) {
        this.filePath = filePath;
        this.books = loadBooksFromFile();
    }

    public LibraryController() {
		super();
	}
    
	public boolean addBook(Book book) {
        if (book != null) {
            books.add(book);
            saveBooksToFile();
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(updatedBook.getTitle())) {
                books.set(i, updatedBook);
                saveBooksToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(Book book) {
        boolean removed = books.remove(book);
        if (removed) {
            saveBooksToFile();
            return true;
        }
        return false;
    }

    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    private List<Book> loadBooksFromFile() {
        List<Book> loadedBooks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0];
                    String author = parts[1];
                    String isbn = parts[2];
                    Book book = new Book(title, author, isbn);
                    loadedBooks.add(book);
                }
            }
        } catch (FileNotFoundException e) {
            // Ignore FileNotFoundException when the file doesn't exist yet
        } catch (IOException e) {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
        return loadedBooks;
    }
}
