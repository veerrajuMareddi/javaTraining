package com.rgt.employeemanagmentSystem.daoimpl;

import org.springframework.jdbc.core.RowMapper;

import com.rgt.employeemanagmentSystem.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setCustomerName(rs.getString("customer_name"));
        // Set other order attributes if available in the ResultSet
        return order;
    }
}
