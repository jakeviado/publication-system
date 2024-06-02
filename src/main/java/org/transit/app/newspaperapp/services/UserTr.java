package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Signup;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static org.transit.app.newspaperapp.utilities.DBConnection.getConnection;

public class UserTr {

    public Signup authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    return new Signup(userId, rs.getString("username"), rs.getString("password"), rs.getString("email"),
                            rs.getString("first_name"), rs.getString("last_name"));
                }
            }
        }
        return null;
    }


    public Set<Integer> getUserRoles(int userId) throws SQLException {
        Set<Integer> roles = new HashSet<>();
        String query = "SELECT role_id FROM USERROLES WHERE user_id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    roles.add(rs.getInt("role_id"));
                }
            }
        }
        return roles;
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
            throw new IllegalArgumentException("Error registering", e);
        }
        return false;
    }


    public static boolean changeUsername(int userId, String newUsername) {
        String query = "UPDATE Users SET username = ? WHERE user_id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);

                pstmt.setString(1, newUsername);
                pstmt.setInt(2, userId);

                int rowsAffected = pstmt.executeUpdate();
                connection.commit();

                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changePassword(int userId, String newPassword) {
        String query = "UPDATE Users SET password = ? WHERE user_id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);

                pstmt.setString(1, newPassword);
                pstmt.setInt(2, userId);

                int rowsAffected = pstmt.executeUpdate();
                connection.commit();

                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAccount(int userId) {
        String deleteCommentsQuery = "DELETE FROM Comments WHERE user_id = ?";
        String deleteSavedArticlesQuery = "DELETE FROM SavedArticles WHERE user_id = ?";
        String deleteArticlesQuery = "DELETE FROM Articles WHERE author_id = ?";
        String deleteRolesQuery = "DELETE FROM UserRoles WHERE user_id = ?";
        String deleteUserQuery = "DELETE FROM Users WHERE user_id = ?";

        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement deleteCommentsStmt = connection.prepareStatement(deleteCommentsQuery);
                 PreparedStatement deleteSavedArticlesStmt = connection.prepareStatement(deleteSavedArticlesQuery);
                 PreparedStatement deleteArticlesStmt = connection.prepareStatement(deleteArticlesQuery);
                 PreparedStatement deleteRolesStmt = connection.prepareStatement(deleteRolesQuery);
                 PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery)) {

                connection.setAutoCommit(false);

                // comments
                deleteCommentsStmt.setInt(1, userId);
                deleteCommentsStmt.executeUpdate();

                // saved articles
                deleteSavedArticlesStmt.setInt(1, userId);
                deleteSavedArticlesStmt.executeUpdate();

                // articles publishes by the user
                deleteArticlesStmt.setInt(1, userId);
                deleteArticlesStmt.executeUpdate();

                // Delete on UserRoles
                deleteRolesStmt.setInt(1, userId);
                deleteRolesStmt.executeUpdate();

                // users
                deleteUserStmt.setInt(1, userId);

                int rowsAffected = deleteUserStmt.executeUpdate();

                connection.commit();

                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

