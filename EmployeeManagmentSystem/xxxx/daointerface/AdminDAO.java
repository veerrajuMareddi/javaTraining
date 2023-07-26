package com.rgt.employeemanagmentSystem.daointerface;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.entity.Administrator;

@Repository
public interface AdminDAO {
	List<Administrator> getAllAdministrators();
    Administrator getAdministratorById(Long id);
    Administrator getAdministratorByUsername(String username);
    void addAdministrator(Administrator administrator);
    void updateAdministrator(Administrator administrator);
    void deleteAdministrator(Long id);

}
