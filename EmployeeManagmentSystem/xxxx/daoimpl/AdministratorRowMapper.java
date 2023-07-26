package com.rgt.employeemanagmentSystem.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rgt.employeemanagmentSystem.entity.Administrator;

public class AdministratorRowMapper implements RowMapper<Administrator> {
    @Override
    public Administrator mapRow(ResultSet rs, int rowNum) throws SQLException {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getLong("id"));
        administrator.setUsername(rs.getString("username"));
        administrator.setPassword(rs.getString("password"));
        administrator.setName(rs.getString("name"));
        return administrator;
    }
}