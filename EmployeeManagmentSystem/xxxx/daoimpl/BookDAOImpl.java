package com.rgt.employeemanagmentSystem.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgt.employeemanagmentSystem.daointerface.BookDAO;
import com.rgt.employeemanagmentSystem.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Book getBookById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookRowMapper());
    }

//    @Override
//    public void addBook(Book book) throws DataAccessException{
//        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPrice());
//    }
        
        @Override
        public void addBook(Book book) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                // Establish a connection to the H2 in-memory database
                connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "root", "root");
                
                // Prepare the SQL statement for inserting a new book
                String sql = "INSERT INTO books(title, author, price) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql);
                
                // Set the values for the parameters in the SQL statement
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setDouble(3, book.getPrice());
                
                // Execute the SQL statement to insert the new book
                statement.executeUpdate();
                
                // Optional: Commit the transaction if needed (e.g., if using transactions)
                // connection.commit();
                
            } catch (SQLException e) {
                // Handle any exceptions that occurred during database operations
                e.printStackTrace();
            } finally {
                // Close the statement and connection in the finally block to release resources
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    
    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getId());
    }

    @Override
    public void deleteBook(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}