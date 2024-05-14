package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Articles;

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

        try (Connection connection = getConnection()) {
            String query = "INSERT INTO ARTICLES (HEADLINE, BYLINE, PUBLICATION_DATE, CONTENT, CATEGORY_TYPE, IMAGELINK) VALUES (?, ?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, headline);
            stmt.setString(2, byline);
            stmt.setObject(3, Timestamp.valueOf(publicationDateTime));
            stmt.setString(4, content);
            stmt.setString(5, category);
            stmt.setString(6, imageLink);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("successful");
            } else {
                System.out.println("failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error publishing articles", e);
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

    //TODO: pwede pa maimprove antok lang ako haha
    public List<Articles> loadArticlesQuery() {
        List<Articles> articlesList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ARTICLES ORDER BY PUBLICATION_DATE DESC";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String headline =  resultSet.getString("HEADLINE");
                String byline = resultSet.getString("BYLINE");
                LocalDateTime publicationDate = resultSet.getTimestamp("PUBLICATION_DATE").toLocalDateTime();
                String content = resultSet.getString("CONTENT");
                String category = resultSet.getString("CATEGORY_TYPE");
                String imageLink = resultSet.getString("IMAGELINK");

                Articles article = new Articles(headline, byline, content, category, publicationDate, imageLink);
                articlesList.add(article);;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error loading the articles", e);
        }
        return articlesList;
    }

    public List<Articles> loadSportsArticle() {
        List<Articles> articlesList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ARTICLES WHERE CATEGORY_TYPE LIKE '%Sports%'";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String headline =  resultSet.getString("HEADLINE");
                String byline = resultSet.getString("BYLINE");
                LocalDateTime publicationDate = resultSet.getTimestamp("PUBLICATION_DATE").toLocalDateTime();
                String content = resultSet.getString("CONTENT");
                String category = resultSet.getString("CATEGORY_TYPE");
                String imageLink = resultSet.getString("IMAGELINK");

                Articles article = new Articles(headline, byline, content, category, publicationDate, imageLink);
                articlesList.add(article);;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error loading the articles", e);
        }
        return articlesList;
    }

    //TODO: implementation
    public void searchQuery(Articles articles) {
        String headline = articles.headline();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM Articles WHERE HEADLINE LIKE ?";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + headline + "%");

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("result: " + resultSet.getString("HEADLINE"));
                } else {
                    System.out.println("not found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }

    //TODO: implementation
    public void saveArticle(int userId, int articleId) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO SavedArticles (USER_ID, ARTICLE_ID, TIMESTAMP) VALUES (?, ?, ?)";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, userId);
            stmt.setInt(2, articleId);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

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
