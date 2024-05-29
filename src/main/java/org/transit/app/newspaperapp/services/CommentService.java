package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.model.UserSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.transit.app.newspaperapp.utilities.DBConnection.getConnection;

public class CommentService {
    private final List<Articles> comments = new ArrayList<>();

    public void addComment(Articles comment) {
        addCommentToDB(comment);
        comments.add(comment);
    }

    public List<Articles> getComments() {
        return getCommentsFromDB();
    }

    public void updateComment(Articles comment) {
        updateCommentInDB(comment);
    }

    public void deleteComment(int commentId) {
        deleteCommentFromDB(commentId);
    }

    private void addCommentToDB(Articles articles) {
        String query = "INSERT INTO Comments (article_id, user_id, content, created_at) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, Articles.getArticleId());
                stmt.setInt(2, UserSession.getInstance().getUserId());
                stmt.setString(3, articles.getContent());
                stmt.setTimestamp(4, Timestamp.valueOf(articles.getCommentCreatedAt()));

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Comment added successfully");
                } else {
                    System.out.println("Failed to add comment");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding the comment: " + e.getMessage(), e);
        }
    }

    private List<Articles> getCommentsFromDB() {
        List<Articles> commentList = new ArrayList<>();
        String query = "SELECT * FROM Comments";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int commentId = resultSet.getInt("comment_id");
                    int articleId = resultSet.getInt("article_id");
                    int userId = resultSet.getInt("user_id");
                    String content = resultSet.getString("content");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");

                    Articles comment = new Articles(commentId, articleId, userId, content, createdAt.toLocalDateTime());
                    commentList.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the comments", e);
        }
        return commentList;
    }

    private void updateCommentInDB(Articles comment) {
        String query = "UPDATE Comments SET article_id = ?, user_id = ?, content = ?, created_at = ? WHERE comment_id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, Articles.getArticleId());
                stmt.setInt(2, comment.getUserId());
                stmt.setString(3, comment.getContent());
                stmt.setTimestamp(4, Timestamp.valueOf(comment.getCommentCreatedAt()));
                stmt.setInt(5, comment.getCommentId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the comment", e);
        }
    }

    private void deleteCommentFromDB(int commentId) {
        String query = "DELETE FROM Comments WHERE comment_id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, commentId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the comment", e);
        }
    }
}
