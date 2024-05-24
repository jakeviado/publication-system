package org.transit.app.newspaperapp.interfaces;

import org.transit.app.newspaperapp.model.Comment;

import java.util.List;

public interface CommentServiceInterface {
    void addComment();
    List<Comment> getComments();
}
