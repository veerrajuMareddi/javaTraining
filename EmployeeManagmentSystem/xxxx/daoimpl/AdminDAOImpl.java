package com.rgt.employeemanagmentSystem.daoimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rgt.employeemanagmentSystem.daointerface.AdminDAO;
import com.rgt.employeemanagmentSystem.entity.Administrator;

@Repository
public class AdminDAOImpl implements AdminDAO {
    private JdbcTemplate jdbcTemplate;

    // Constructor or Autowire JdbcTemplate
    public AdminDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        String sql = "SELECT * FROM admins";
        return jdbcTemplate.query(sql, new AdministratorRowMapper());
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        String sql = "SELECT * FROM admins WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AdministratorRowMapper());
    }

    @Override
    public Administrator getAdministratorByUsername(String username) {
        String sql = "SELECT * FROM admins WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new AdministratorRowMapper());
    }

    @Override
    public void addAdministrator(Administrator administrator) {
        String sql = "INSERT INTO admins (username, password, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, administrator.getUsername(), administrator.getPassword(), administrator.getName());
    }

    @Override
    public void updateAdministrator(Administrator administrator) {
        String sql = "UPDATE administrators SET username = ?, password = ?, name = ? WHERE id = ?";
        jdbcTemplate.update(sql, administrator.getUsername(), administrator.getPassword(), administrator.getName(), administrator.getId());
    }

    @Override
    public void deleteAdministrator(Long id) {
        String sql = "DELETE FROM administrators WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
