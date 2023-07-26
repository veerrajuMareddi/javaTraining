package com.rgt.employeemanagmentSystem.daoimpl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.daointerface.OrderDAO;
import com.rgt.employeemanagmentSystem.entity.Book;
import com.rgt.employeemanagmentSystem.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private JdbcTemplate jdbcTemplate;

    // Constructor or Autowire JdbcTemplate
    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

	@SuppressWarnings("deprecation")
	@Override
    public Order getOrderById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
      Order order=  jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
        String sql2 = "SELECT books_id FROM Orders_Books WHERE order_id = ?";
        List<Long>booksIds= jdbcTemplate.queryForList(sql2, Long.class, id);
        
        
        
        List<Book> booklist=new ArrayList<>();
        for(Long bookId :booksIds) {
        	String sql3 = "SELECT * FROM books WHERE id = ?";
           Book book=  jdbcTemplate.queryForObject(sql3, new Object[]{bookId},  new BookRowMapper());
           booklist.add(book);
        }
        order.setBooks(booklist);
		return order;      
    }

    @Override
    public void addOrder(Order order) {
        // Implement the logic to add an order and its associated books
    }

    @Override
    public void updateOrder(Order order) {
        // Implement the logic to update an order and its associated books
    }

    @Override
    public void deleteOrder(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
