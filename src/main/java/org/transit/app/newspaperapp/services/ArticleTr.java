package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.model.UserSession;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.transit.app.newspaperapp.utilities.DBConnection.getConnection;

public class ArticleTr {

    public void publishArticleQuery(Articles articles) {
        String headline = articles.headline();
        String byline = articles.byline();
        String content = articles.content();
        String category = articles.category();
        LocalDateTime publicationDateTime = LocalDateTime.parse(articles.publicationDate());
        String imageLink = articles.imageLink();
        int userId = UserSession.getInstance().getUserId();

        try (Connection connection = getConnection()) {
            String authorQuery = "SELECT author_id FROM Authors WHERE user_id = ?";
            assert connection != null;
            PreparedStatement authorStmt = connection.prepareStatement(authorQuery);
            authorStmt.setInt(1, userId);
            ResultSet authorRs = authorStmt.executeQuery();

            int authorId = -1;
            if (authorRs.next()) {
                authorId = authorRs.getInt("author_id");
            } else {
                throw new RuntimeException("author not found for the current user.");
            }

            String query = "INSERT INTO ARTICLES (HEADLINE, BYLINE, PUBLICATION_DATE, CONTENT, CATEGORY_TYPE, IMAGELINK, AUTHOR_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, headline);
            stmt.setString(2, byline);
            stmt.setObject(3, Timestamp.valueOf(publicationDateTime));
            stmt.setString(4, content);
            stmt.setString(5, category);
            stmt.setString(6, imageLink);
            stmt.setInt(7, authorId);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Article published successfully.");
            } else {
                System.out.println("Article publishing failed.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error publishing article", e);
        }
    }

    //TODO: implementation
    public void unpublishArticleQuery(Articles articles) {
        String headline = articles.headline();

        try (Connection connection = getConnection()) {
            String query = "DELETE FROM Articles WHERE HEADLINE = ?";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, headline);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("article unpublished");
            } else {
                System.out.println("article not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }


    public List<Articles> loadArticlesQuery() {
        return loadArticlesByCategory(null);
    }

    public List<Articles> loadSportsArticle() {
        return loadArticlesByCategory("Sports");
    }

    public List<Articles> loadTechnologyArticle() {
        return loadArticlesByCategory("Technology");
    }

    public List<Articles> loadBusinessArticle() {
        return loadArticlesByCategory("Business");
    }

    public List<Articles> loadOpinionArticle() {
        return loadArticlesByCategory("Opinion");
    }

    public List<Articles> loadEntertainmentArticle() {
        return loadArticlesByCategory("Entertainment");
    }

    private List<Articles> loadArticlesByCategory(String category) {
        List<Articles> articlesList = new ArrayList<>();

        String query = "SELECT a.*, au.name AS author_name " +
                "FROM ARTICLES a " +
                "INNER JOIN Authors au ON a.author_id = au.author_id";
        if (category != null && !category.isEmpty()) {
            query += " WHERE a.CATEGORY_TYPE LIKE ?";
        }
        query += " ORDER BY a.PUBLICATION_DATE DESC";

        try (Connection connection = getConnection()) {
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            if (category != null && !category.isEmpty()) {
                stmt.setString(1, "%" + category + "%");
            }
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String headline = resultSet.getString("HEADLINE");
                String byline = resultSet.getString("BYLINE");
                LocalDateTime publicationDate = resultSet.getTimestamp("PUBLICATION_DATE").toLocalDateTime();
                String content = resultSet.getString("CONTENT");
                String categoryType = resultSet.getString("CATEGORY_TYPE");
                String imageLink = resultSet.getString("IMAGELINK");
                int article_id = resultSet.getInt("ARTICLE_ID");
                String author_id = resultSet.getString("AUTHOR_NAME");

                Articles article = new Articles(headline, byline, content, categoryType, publicationDate, imageLink, article_id, author_id);
                articlesList.add(article);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error loading the articles", e);
        }
        return articlesList;
    }


    public void saveArticle(int userId, int articleId) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO SavedArticles (USER_ID, ARTICLE_ID) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, userId);
            stmt.setInt(2, articleId);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("article saved");
            } else {
                System.out.println("failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }
}