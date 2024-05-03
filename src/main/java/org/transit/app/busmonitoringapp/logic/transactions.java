package org.transit.app.busmonitoringapp.logic;

import org.transit.app.busmonitoringapp.model.Login;
import org.transit.app.busmonitoringapp.model.Signup;

import java.sql.*;

import static org.transit.app.busmonitoringapp.utilities.DBConnection.getConnection;

public class transactions {

    public boolean loginQuery(Login userData) throws SQLException {
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
            assert connection != null;

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    //TODO IF THE ADMIN LOGS IN, THE ADMIN MUST HAVE ACCESS TO THE ADMIN PAGE
    public boolean adminLogin(Login userData) throws SQLException {
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String query = "SELECT * from USERROLES where USER_ID = 2 AND ROLE_ID = 2";
            assert connection != null;

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean registerQuery(Signup userData, boolean isAuthor) throws SQLException {
        String first_name = userData.first_name();
        String last_name = userData.last_name();
        String email = userData.email();
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String userQuery = "INSERT INTO Users (username, password, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement userStmt = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, email);
            userStmt.setString(4, first_name);
            userStmt.setString(5, last_name);

            int rowsAffected = userStmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long userId = generatedKeys.getLong(1);

                    String roleQuery = "INSERT INTO UserRoles (user_id, role_id) VALUES (?, ?)";
                    PreparedStatement roleStmt = connection.prepareStatement(roleQuery);
                    if (isAuthor) {
                        roleStmt.setLong(1, userId);
                        roleStmt.setInt(2, 1);
                    }

                    roleStmt.executeUpdate();
                    roleStmt.close();

                    if (isAuthor) {
                        String authorQuery = "INSERT INTO Authors (author_id, name, role_id) VALUES (?, ?, 1)";
                        PreparedStatement authorStmt = connection.prepareStatement(authorQuery);
                        authorStmt.setLong(1, userId);
                        authorStmt.setString(2, first_name + " " + last_name);
                        authorStmt.executeUpdate();
                        authorStmt.close();
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

