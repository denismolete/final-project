package com.example.finalproject.service;

import com.example.finalproject.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class SQLStorageEngine {

    @Autowired
    private DataSource dataSource;

    public void checkConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                System.out.println("SQLStorageEngine: Database connection is valid.");
            } else {
                throw new StorageException("SQLStorageEngine: Database connection is invalid.");
            }
        } catch (SQLException e) {
            throw new StorageException("SQLStorageEngine: Failed to connect to database.", e);
        }
    }

    public int countUsersDirectly() {
        String sql = "SELECT COUNT(*) FROM users";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new StorageException("SQLStorageEngine: Failed to execute count query.", e);
        }
    }
}
