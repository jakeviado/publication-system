package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Comment;

import java.util.List;

public interface CommentServiceInterface {
    void addComment(Comment comment);
    List<Comment> getComments();
}
