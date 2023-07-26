package com.rgt.employeemanagmentSystem.daoimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.daointerface.CustomerDAO;
import com.rgt.employeemanagmentSystem.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private JdbcTemplate jdbcTemplate;

    // Constructor or Autowire JdbcTemplate
    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
    

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

	@Override
    public Customer getCustomerById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        String sql = "SELECT * FROM customers WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new CustomerRowMapper());
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (username, password, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getUsername(), customer.getPassword(), customer.getName());
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET username = ?, password = ?, name = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getUsername(), customer.getPassword(), customer.getName(), customer.getId());
    }

    @Override
    public void deleteCustomer(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
