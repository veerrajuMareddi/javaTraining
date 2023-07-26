package com.rgt.employeemanagmentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreInterface  {
	
	public BookstoreInterface() {
			}

	public static void main(String[] args) {
	    System.setProperty("java.awt.headless", "false");

		SpringApplication.run(BookstoreInterface.class, args);

	}

}
