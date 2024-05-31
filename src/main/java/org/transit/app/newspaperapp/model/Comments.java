package org.transit.app.newspaperapp.model;

import java.time.LocalDateTime;

public class Comments {
    private int commentId;
    private int articleId;
    private int userId;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private String timeAgo;

    public Comments(int commentId, int articleId, int userId, String username, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Comments(int commentId, int articleId, int userId, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Comments(int commentId, int articles, String username, String content, LocalDateTime createdAt, String timeAgo) {
        this.commentId = commentId;
        this.articleId = articles;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.timeAgo = timeAgo;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
