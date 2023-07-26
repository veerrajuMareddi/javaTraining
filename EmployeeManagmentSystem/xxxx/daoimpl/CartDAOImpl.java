package com.rgt.employeemanagmentSystem.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.daointerface.CartDAO;
import com.rgt.employeemanagmentSystem.entity.Book;

@Repository
public class CartDAOImpl implements CartDAO {
    private JdbcTemplate jdbcTemplate;

    // Constructor or Autowire JdbcTemplate
    public CartDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
    

    @Override
    public void addToCart(Long customerId, Long bookId, int quantity) {
        String sql = "INSERT INTO cart (customer_id, book_id, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customerId, bookId, quantity);
    }

	@Override
    public void removeFromCart(Long customerId, Long bookId) {
        String sql = "DELETE FROM cart WHERE customer_id = ? AND book_id = ?";
        jdbcTemplate.update(sql, customerId, bookId);
    }

    @Override
    public void updateCartQuantity(Long customerId, Long bookId, int quantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE customer_id = ? AND book_id = ?";
        jdbcTemplate.update(sql, quantity, customerId, bookId);
    }

    @Override
    public void clearCart(Long customerId) {
        String sql = "DELETE FROM cart WHERE customer_id = ?";
        jdbcTemplate.update(sql, customerId);
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<Book> getCartItems(Long customerId) {
        String sql = "SELECT b.* FROM cart c JOIN books b ON c.book_id = b.id WHERE c.customer_id = ?";
        return jdbcTemplate.query(sql, new Object[]{customerId}, new BookRowMapper());
    }

    @Override
    public void checkout(Long customerId) {
    	String selectCartItems = "SELECT book_id FROM cart WHERE customer_id = ?";
        List<Long> bookIds = jdbcTemplate.queryForList(selectCartItems, Long.class, customerId);
        String sql4 = "DELETE FROM cart";
        jdbcTemplate.update(sql4);
//        String sql = "INSERT INTO orders (customer_name) VALUES (?) RETURNING id";
//        Long orderId = jdbcTemplate.queryForObject(sql, Long.class,customerId );
        String sql = "INSERT INTO orders (customer_name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, customerId+"");
            return ps;
        }, keyHolder);

        Long orderId = keyHolder.getKey().longValue();
        
        String insertOrderBooks = "INSERT INTO Orders_Books (order_id, books_id) VALUES (?, ?)";
        for (Long bookId : bookIds) {
            jdbcTemplate.update(insertOrderBooks, orderId, bookId);
        }
    }
    
   
}

