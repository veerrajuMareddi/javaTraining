package com.rgt.employeemanagmentSystem.daoimpl;

import org.springframework.jdbc.core.RowMapper;

import com.rgt.employeemanagmentSystem.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPrice(rs.getDouble("price"));
        // Set other book attributes if available in the ResultSet
        return book;
    }
}
