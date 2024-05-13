package org.transit.app.newspaperapp.logic;

import org.transit.app.newspaperapp.model.Login;
import org.transit.app.newspaperapp.model.Signup;

import java.sql.*;

import static org.transit.app.newspaperapp.utilities.DBConnection.getConnection;

public class UserTr {

//    //TODO: NEED ICHECK KUNG SI ATTEMPTING USER AY AUTHOR OR SIMPLENG TAO LAMANG
//    public boolean loginQuery(Login userData) throws SQLException {
//        String username = userData.username();
//        String password = userData.password();
//
//        try (Connection connection = getConnection()) {
//            String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
//            assert connection != null;
//
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//
//            ResultSet rs = stmt.executeQuery();
//            return rs.next();
//
//        }
//    }

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


    private boolean retrieveUserRole(Connection connection, int userId, int roleId) throws SQLException {
        String checkUserRoles = "SELECT * FROM UserRoles WHERE user_id = ? AND role_id = ?";
        PreparedStatement roleStmt = connection.prepareStatement(checkUserRoles);
        roleStmt.setInt(1, userId);
        roleStmt.setInt(2, roleId);
        ResultSet rs = roleStmt.executeQuery();
        return rs.next();
    }


    public boolean registerQuery(Signup userData, boolean isAuthor, boolean isReader) throws SQLException {
        String first_name = userData.first_name();
        String last_name = userData.last_name();
        String email = userData.email();
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String userQuery = "INSERT INTO Users (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?, ?)";
            assert connection != null;

            // CHECKING sa unang column ng relation na USER_ID kasi iyon ay auto increment sa db
            PreparedStatement userStmt = connection.prepareStatement(userQuery, new int[]{1});

            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, email);
            userStmt.setString(4, first_name);
            userStmt.setString(5, last_name);

            int rowsAffected = userStmt.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String roleQuery = "INSERT INTO UserRoles (USER_ID, ROLE_ID) VALUES (?, ?)";
                    PreparedStatement roleStmt = connection.prepareStatement(roleQuery);
                    if (isAuthor) {
                        roleStmt.setInt(1, userId);
                        roleStmt.setInt(2, 1); // This is Author ROLE_ID
                    } else {
                        roleStmt.setInt(1, userId);
                        roleStmt.setInt(2, 2); // This is Reader ROLE_ID
                    }

                    roleStmt.executeUpdate();
                    roleStmt.close();

                    if (isAuthor) {
                        String authorQuery = "INSERT INTO Authors (name, user_id, role_id) VALUES (?, ?, ?)";
                        PreparedStatement authorStmt = connection.prepareStatement(authorQuery);
                        authorStmt.setString(1, first_name + " " + last_name);
                        authorStmt.setInt(2, userId);
                        authorStmt.setInt(3, 1);
                        authorStmt.executeUpdate();
                        authorStmt.close();
                    }
                    if (isReader) {
                        String authorQuery = "INSERT INTO Readers (name, user_id, role_id) VALUES (?, ?, ?)";
                        PreparedStatement authorStmt = connection.prepareStatement(authorQuery);
                        authorStmt.setString(1, first_name + " " + last_name);
                        authorStmt.setInt(2, userId);
                        authorStmt.setInt(3, 2);
                        authorStmt.executeUpdate();
                        authorStmt.close();
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error registering", e);
        }
        return false;
    }
}

