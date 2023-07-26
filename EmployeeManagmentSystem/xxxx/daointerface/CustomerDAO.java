package com.rgt.employeemanagmentSystem.daointerface;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.rgt.employeemanagmentSystem.entity.Customer;


@Repository
public interface CustomerDAO {
	List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer getCustomerByUsername(String username);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Long id);
}
