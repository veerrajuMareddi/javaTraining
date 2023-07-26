package com.rgt.employeemanagmentSystem.daointerface;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.entity.Order;
@Repository
public interface OrderDAO {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Long id);
}