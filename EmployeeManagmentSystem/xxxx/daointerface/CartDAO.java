package com.rgt.employeemanagmentSystem.daointerface;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.entity.Book;
@Repository
public interface CartDAO {
    void addToCart(Long customerId, Long bookId, int quantity);
    void removeFromCart(Long customerId, Long bookId);
    void updateCartQuantity(Long customerId, Long bookId, int quantity);
    void clearCart(Long customerId);
    List<Book> getCartItems(Long customerId);
    void checkout(Long customerId);
}
