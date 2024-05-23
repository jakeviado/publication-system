package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.User;
import org.transit.app.newspaperapp.model.Signup;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

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

//    public boolean loginQuery(User userData) throws SQLException {
//        String username = userData.getUsername();
//        String password = userData.getPassword();
//
//        try (Connection connection = getConnection()) {
//
//            String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
//            assert connection != null;
//
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//
//            ResultSet rs = stmt.executeQuery();
//            return rs.next();
//        }
//        catch (SQLException e) {
//            throw new IllegalArgumentException("fail to login in db hehe", e);
//        }
//    }

    public User getUserByUsername(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM USERS WHERE USERNAME = ?";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("PASSWORD");
                return new User(username, password);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Failed", e);
        }
    }

//    public boolean loginQuery(User userData) throws SQLException {
//        User dbUser = getUserByUsername(userData.getUsername());
//        return dbUser != null && dbUser.checkPassword(userData.getPassword());
//    }
//
//    private static Signup authenticate(Connection conn, String username, String password) throws SQLException {
//        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setString(1, username);
//            pstmt.setString(2, password);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return new Signup(rs.getString("username"), rs.getString("password"), rs.getString("email"),
//                        rs.getString("first_name"), rs.getString("last_name"));
//            }
//        }
//        return null;
//    }

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

    public boolean loginQuery(User userData) throws SQLException {
        Signup dbUser = authenticate(userData.getUsername(), userData.getPassword());
        return dbUser != null && dbUser.getPassword().equals(userData.getPassword());
    }

    private boolean retrieveUserRole(Connection connection, int userId, int roleId) throws SQLException {
        String checkUserRoles = "SELECT * FROM UserRoles WHERE user_id = ? AND role_id = ?";
        try (PreparedStatement roleStmt = connection.prepareStatement(checkUserRoles)) {
            roleStmt.setInt(1, userId);
            roleStmt.setInt(2, roleId);
            ResultSet rs = roleStmt.executeQuery();
            return rs.next();
        }
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


}

