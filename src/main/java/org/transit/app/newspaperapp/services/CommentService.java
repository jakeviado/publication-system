package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Comments;
import org.transit.app.newspaperapp.model.UserSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.transit.app.newspaperapp.utilities.DBConnection.getConnection;

public class CommentService {

    private final List<Comments> comments = new ArrayList<>();

    public void addComment(Comments comment) {
        addCommentToDB(comment);
        comments.add(comment);
    }

    public List<Comments> getComments(int articles) {
        return loadCommentsByArticleId(articles);
    }

    public void updateComment(Comments comment) {
        updateCommentInDB(comment);
    }

    public void deleteComment(int commentId) {
        deleteCommentFromDB(commentId);
    }

    private void addCommentToDB(Comments comment) {
        String query = "INSERT INTO Comments (article_id, user_id, content, created_at) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, comment.getArticleId());
                stmt.setInt(2, UserSession.getInstance().getUserId());
                stmt.setString(3, comment.getContent());
                stmt.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt()));

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


    public List<Comments> loadCommentsByArticleId(int articles) {
        List<Comments> commentList = new ArrayList<>();
        String query = "SELECT c.comment_id, c.content, c.created_at, u.username " +
                "FROM Comments c " +
                "INNER JOIN Users u ON c.user_id = u.user_id " +
                "WHERE article_id = ? " +
                "ORDER BY c.created_at DESC";

        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.out.println("Failed to establish database connection");
                return Collections.emptyList();
            }

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, articles);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int commentId = resultSet.getInt("comment_id");
                String content = resultSet.getString("content");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                String username = resultSet.getString("username");

                Comments comment = new Comments(commentId, articles, username, content, createdAt.toLocalDateTime());
                commentList.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("Error loading the comments: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }

        return commentList;
    }


    private void updateCommentInDB(Comments comment) {
        String query = "UPDATE Comments SET article_id = ?, user_id = ?, content = ?, created_at = ? WHERE comment_id = ?";
        try (Connection connection = getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, comment.getArticleId());
                stmt.setInt(2, comment.getUserId());
                stmt.setString(3, comment.getContent());
                stmt.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt()));
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
