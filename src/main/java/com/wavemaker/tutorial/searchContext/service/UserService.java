package com.wavemaker.tutorial.searchContext.service;

import com.wavemaker.tutorial.searchContext.domain.User;
import com.wavemaker.tutorial.searchContext.util.DbConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by srujant on 19/7/16.
 */
public class UserService {

    public static User getUser(String emailId) {
        String query = "select *from users where  emailId=? ;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, emailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            } else {
                User user = new User(resultSet.getString("emailId"), resultSet.getString("password"), resultSet.getString("username"));
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database access error", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to close StatementObject", e);
                }
            }
        }
    }

    public static int inserUser(User user) {
        try {
            String query = "insert into users(username,password,emailId) values(?,?,?)";
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmailId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database access error", e);
        }

    }

    private static PreparedStatement getPreparedStatement(String query) throws SQLException {
        PreparedStatement preparedStatement = DbConnectionUtil.getConnection().prepareStatement(query);
        return preparedStatement;
    }

}