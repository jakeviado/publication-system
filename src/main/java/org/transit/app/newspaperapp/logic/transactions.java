package org.transit.app.newspaperapp.logic;

import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.model.Login;
import org.transit.app.newspaperapp.model.Signup;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.transit.app.newspaperapp.utilities.DBConnection.getConnection;

public class transactions {

    //TODO NEED ICHECK KUNG SI ATTEMPTING USER AY AUTHOR OR SIMPLENG TAO LAMANG
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

//    public boolean loginQuery(Login userData, boolean isAuthor) throws SQLException {
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
////            return rs.next();
//
//            if (rs.next()) {
//                if (isAuthor) {
//                    String authorQuery = "SELECT * FROM Authors WHERE ROLE_ID = 1";
//                    PreparedStatement authorStmt = connection.prepareStatement(authorQuery);
//                    authorStmt.executeUpdate();
//                    authorStmt.close();
//                }
//                return true;
//            }
//
//        }
//        return false;
//    }


//
//    public boolean registerQuery(Signup userData) throws SQLException {
//        String first_name = userData.first_name();
//        String last_name = userData.last_name();
//        String email = userData.email();
//        String username = userData.username();
//        String password = userData.password();
//
//        try (Connection connection = getConnection()) {
//            String query = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?, ?)";
//            assert connection != null;
//            PreparedStatement stmt = connection.prepareStatement(query);
//
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            stmt.setString(3, email);
//            stmt.setString(4, first_name);
//            stmt.setString(5, last_name);
//
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected == 1;
//        }
//    }


    public boolean registerQuery(Signup userData, boolean isAuthor) throws SQLException {
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
//            return rowsAffected == 1;

            if (rowsAffected == 1) {
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String roleQuery = "INSERT INTO UserRoles (USER_ID, ROLE_ID) VALUES (?, ?)";
                    PreparedStatement roleStmt = connection.prepareStatement(roleQuery);
                    if (isAuthor) {
                        roleStmt.setInt(1, userId);
                        roleStmt.setInt(2, 1);
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
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    // Publish article query
    public boolean publishQuery(Articles articles) {
        String headline = articles.headline();
        String byline = articles.byline();
        String content = articles.content();
        String category = articles.category();
        LocalDateTime publicationDateTime = LocalDateTime.parse(articles.publicationDate());

        try (Connection connection = getConnection()) {
            String query = "INSERT INTO ARTICLESTEST (HEADLINE, BYLINE, PUBLICATION_DATE, CONTENT, CATEGORY_TYPE) VALUES (?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, headline);
            stmt.setString(2, byline);
            stmt.setObject(3, Timestamp.valueOf(publicationDateTime));
            stmt.setString(4, content);
            stmt.setString(5, category);

//            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public List<Articles> loadArticlesQuery() {
        List<Articles> articlesList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ARTICLESTEST";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String headline =  resultSet.getString("HEADLINE");
                String byline = resultSet.getString("BYLINE");
                LocalDateTime publicationDate = resultSet.getTimestamp("PUBLICATION_DATE").toLocalDateTime();
                String content = resultSet.getString("CONTENT");
                String category = resultSet.getString("CATEGORY_TYPE");

                Articles article = new Articles(headline, byline, content, category, publicationDate);
                articlesList.add(article);;
            }

//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articlesList;
    }

}

