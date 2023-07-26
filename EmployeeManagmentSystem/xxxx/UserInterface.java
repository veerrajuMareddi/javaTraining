package com.rgt.employeemanagmentSystem;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.rgt.employeemanagmentSystem.daoimpl.BookDAOImpl;
import com.rgt.employeemanagmentSystem.daointerface.BookDAO;
import com.rgt.employeemanagmentSystem.daointerface.CartDAO;
import com.rgt.employeemanagmentSystem.daointerface.CustomerDAO;
import com.rgt.employeemanagmentSystem.daointerface.OrderDAO;
import com.rgt.employeemanagmentSystem.entity.Book;
import com.rgt.employeemanagmentSystem.entity.Customer;
import com.rgt.employeemanagmentSystem.entity.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class UserInterface {
	private JFrame frame;
	private JButton viewBooksButton;
	private JButton viewDetailsButton;
	private JButton addToCartButton;
	private JButton removeFromCartButton;
	private JButton updateQuantityButton;
	private JButton checkoutButton;
	private JButton addBookButton;
	private JButton updateBookButton;
	private JButton removeBookButton;
	private JButton viewOrdersButton;
	private JButton viewOrderDetailsButton;
	private JButton addCustomer;
	private JButton viewCart;
	private boolean isAdmin;

	private static int view = 0;

	@Autowired
	private BookDAOImpl bookDAO = new BookDAOImpl();

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private CustomerDAO customerDAO;

	public UserInterface(@Value("${admin.mode:false}") boolean isAdmin) {
		this.isAdmin = isAdmin;
		if (view < 2) {
			initialize();
			this.isAdmin = true;
		}
		view++;
	}

	@PostConstruct
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());

		if (!isAdmin) {

			addCustomer = new JButton("addCustomer");
			addCustomer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String UserName = JOptionPane.showInputDialog(frame, "Enter customer UserName:");
					String customerName = JOptionPane.showInputDialog(frame, "Enter customerName :");
					String password = JOptionPane.showInputDialog(frame, "Enter password:");
					Customer customer = new Customer();
					customer.setName(customerName);
					customer.setUsername(UserName);
					customer.setPassword(password);
					customerDAO.addCustomer(customer);
				}
			});
			frame.getContentPane().add(addCustomer);

			viewBooksButton = new JButton("View Books");
			viewBooksButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Logic to handle view books action
					List<Book> books = bookDAO.getAllBooks();

					// Display the list of books in the UI or perform any other desired action
					StringBuilder bookList = new StringBuilder();
					for (Book book : books) {
						bookList.append("Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor())
								.append(", Price: ").append(book.getPrice()).append(System.lineSeparator());
					}
					JOptionPane.showMessageDialog(frame, bookList.toString(), "Book List",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});

			frame.getContentPane().add(viewBooksButton);

			viewDetailsButton = new JButton("View Details");
			viewDetailsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Prompt the user to enter a book ID or select a book from a list
					String bookIdString = JOptionPane.showInputDialog(frame, "Enter book ID:");

					// Validate and parse the book ID as a Long
					Long bookId = null;
					try {
						bookId = Long.parseLong(bookIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid book ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Retrieve the book details using bookDAO.getBookById(bookId)
					Book book = bookDAO.getBookById(bookId);

					// Check if the book exists
					if (book == null) {
						JOptionPane.showMessageDialog(frame, "Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Display the book details in the UI or perform any other desired action
					StringBuilder bookDetails = new StringBuilder();
					bookDetails.append("Title: ").append(book.getTitle()).append("\nAuthor: ").append(book.getAuthor())
							.append("\nPrice: ").append(book.getPrice());

					JOptionPane.showMessageDialog(frame, bookDetails.toString(), "Book Details",
							JOptionPane.INFORMATION_MESSAGE);

					// Optionally, you can update the book details view or perform any other desired
					// action
				}
			});
			frame.getContentPane().add(viewDetailsButton);

			viewCart = new JButton("viewCart");
			viewCart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String customerID = JOptionPane.showInputDialog(frame, "Enter customer UserName:");

					List<Book> books = cartDAO.getCartItems(Long.parseLong(customerID));
					StringBuilder bookList = new StringBuilder();
					for (Book book : books) {
						bookList.append("Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor())
								.append(", Price: ").append(book.getPrice()).append(System.lineSeparator());
					}
					JOptionPane.showMessageDialog(frame, bookList.toString(), "Book List",
							JOptionPane.INFORMATION_MESSAGE);

				}
			});
			frame.getContentPane().add(viewCart);

			addToCartButton = new JButton("Add to Cart");
			addToCartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Prompt the user to enter the customer ID, book ID, and quantity to add to the
					// cart
					String customerIdString = JOptionPane.showInputDialog(frame, "Enter customer ID:");
					String bookIdString = JOptionPane.showInputDialog(frame, "Enter book ID:");
					String quantityString = JOptionPane.showInputDialog(frame, "Enter quantity:");

					// Validate and parse the customer ID as a Long
					Long customerId = null;
					try {
						customerId = Long.parseLong(customerIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid customer ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validate and parse the book ID as a Long
					Long bookId = null;
					try {
						bookId = Long.parseLong(bookIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid book ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validate and parse the quantity as an integer
					int quantity = 0;
					try {
						quantity = Integer.parseInt(quantityString);
						if (quantity <= 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid quantity entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Call cartDAO.addToCart(customerId, bookId, quantity) to add the book to the
					// cart
					cartDAO.addToCart(customerId, bookId, quantity);

					// Show a success message or update the cart view in the UI
					JOptionPane.showMessageDialog(frame, "Book added to cart successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					// Optionally, you can update the cart view or perform any other desired action
				}
			});
			frame.getContentPane().add(addToCartButton);

			removeFromCartButton = new JButton("Remove from Cart");
			removeFromCartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Prompt the user to enter the customer ID and book ID to remove from the cart
					String customerIdString = JOptionPane.showInputDialog(frame, "Enter customer ID:");
					String bookIdString = JOptionPane.showInputDialog(frame, "Enter book ID:");

					// Validate and parse the customer ID as a Long
					Long customerId = null;
					try {
						customerId = Long.parseLong(customerIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid customer ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validate and parse the book ID as a Long
					Long bookId = null;
					try {
						bookId = Long.parseLong(bookIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid book ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Call cartDAO.removeFromCart(customerId, bookId) to remove the book from the
					// cart
					cartDAO.removeFromCart(customerId, bookId);

					// Show a success message or update the cart view in the UI
					JOptionPane.showMessageDialog(frame, "Book removed from cart successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					// Optionally, you can update the cart view or perform any other desired action
				}
			});
			frame.getContentPane().add(removeFromCartButton);

			updateQuantityButton = new JButton("Update Quantity");
			updateQuantityButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Prompt the user to enter the customer ID, book ID, and new quantity
					String customerIdString = JOptionPane.showInputDialog(frame, "Enter customer ID:");
					String bookIdString = JOptionPane.showInputDialog(frame, "Enter book ID:");
					String quantityString = JOptionPane.showInputDialog(frame, "Enter new quantity:");

					// Validate and parse the customer ID as a Long
					Long customerId = null;
					try {
						customerId = Long.parseLong(customerIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid customer ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validate and parse the book ID as a Long
					Long bookId = null;
					try {
						bookId = Long.parseLong(bookIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid book ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validate and parse the quantity as an integer
					int quantity = 0;
					try {
						quantity = Integer.parseInt(quantityString);
						if (quantity <= 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid quantity entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Call cartDAO.updateCartQuantity(customerId, bookId, quantity) to update the
					// quantity in the cart
					cartDAO.updateCartQuantity(customerId, bookId, quantity);

					// Show a success message or update the cart view in the UI
					JOptionPane.showMessageDialog(frame, "Quantity updated successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					// Optionally, you can update the cart view or perform any other desired action
				}
			});
			frame.getContentPane().add(updateQuantityButton);

			checkoutButton = new JButton("Checkout");
			checkoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Prompt the user to enter the customer ID
					String customerIdString = JOptionPane.showInputDialog(frame, "Enter customer ID:");

					// Validate and parse the customer ID as a Long
					Long customerId = null;
					try {
						customerId = Long.parseLong(customerIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid customer ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Call cartDAO.checkout(customerId) to handle the checkout process
					cartDAO.checkout(customerId);

					// Show a success message or update the UI to reflect the completed checkout
					JOptionPane.showMessageDialog(frame, "Checkout completed successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					// Optionally, you can update the UI to reflect the completed checkout or
					// perform any other desired action
				}
			});
			frame.getContentPane().add(checkoutButton);
		} else {
			addBookButton = new JButton("Add Book");
			addBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Logic to handle add book action
					String title = JOptionPane.showInputDialog(frame, "Enter book title:");
					String author = JOptionPane.showInputDialog(frame, "Enter book author:");
					String priceString = JOptionPane.showInputDialog(frame, "Enter book price:");

					double price = 0.0;
					try {
						price = Double.parseDouble(priceString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid price entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Book newBook = new Book();
					newBook.setTitle(title);
					newBook.setAuthor(author);
					newBook.setPrice(price);

					bookDAO.addBook(newBook);

					JOptionPane.showMessageDialog(frame, "Book added successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			frame.getContentPane().add(addBookButton);

			updateBookButton = new JButton("Update Book");
			updateBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String bookIdString = JOptionPane.showInputDialog(frame, "Enter book ID:");

					Long bookId = null;
					try {
						bookId = Long.parseLong(bookIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid book ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					String title = JOptionPane.showInputDialog(frame, "Enter updated title:");
					String author = JOptionPane.showInputDialog(frame, "Enter updated author:");
					String priceString = JOptionPane.showInputDialog(frame, "Enter updated price:");

					double price = 0.0;
					try {
						price = Double.parseDouble(priceString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid price entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Book existingBook = bookDAO.getBookById(bookId);

					if (existingBook == null) {
						JOptionPane.showMessageDialog(frame, "Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					existingBook.setTitle(title);
					existingBook.setAuthor(author);
					existingBook.setPrice(price);

					bookDAO.updateBook(existingBook);

					JOptionPane.showMessageDialog(frame, "Book updated successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			frame.getContentPane().add(updateBookButton);

			removeBookButton = new JButton("Remove Book");
			removeBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String bookIdString = JOptionPane.showInputDialog(frame,
							"Enter the book ID or select a book from the list:");

					Long bookId = null;
					try {
						bookId = Long.parseLong(bookIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid book ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					bookDAO.deleteBook(bookId);

					JOptionPane.showMessageDialog(null, "Book successfully removed from the database.");
				}
			});
			frame.getContentPane().add(removeBookButton);

			viewOrdersButton = new JButton("View Orders");
			viewOrdersButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Order> orders = orderDAO.getAllOrders();

					if (orders.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No orders found.");
					} else {
						StringBuilder message = new StringBuilder("List of Orders:\n");
						for (Order order : orders) {
							message.append(order.getId()).append(": ").append(order.getCustomerName()).append("\n");
						}
						JOptionPane.showMessageDialog(null, message.toString());
					}
				}
			});
			frame.getContentPane().add(viewOrdersButton);

			viewOrderDetailsButton = new JButton("View Order Details");
			viewOrderDetailsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String orderIdString = JOptionPane.showInputDialog(frame, "Enter order ID:");

					Long orderId = null;
					try {
						orderId = Long.parseLong(orderIdString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid order ID entered!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Order order = orderDAO.getOrderById(orderId);

					if (order == null) {
						JOptionPane.showMessageDialog(frame, "Order not found!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					StringBuilder orderDetails = new StringBuilder();
					orderDetails.append("Order ID: ").append(order.getId()).append("\n");
					orderDetails.append("Customer: ").append(order.getCustomerName()).append("\n");
					orderDetails.append("Books: ").append("\n");
					for (Book book : order.getBooks()) {
						orderDetails.append("- ").append(book.getTitle()).append(" by ").append(book.getAuthor())
								.append("\n");
					}

					JOptionPane.showMessageDialog(frame, orderDetails.toString(), "Order Details",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			frame.getContentPane().add(viewOrderDetailsButton);
		}

		frame.setVisible(true);
	}

}
