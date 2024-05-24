package org.transit.app.newspaperapp.services;

import org.transit.app.newspaperapp.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentService implements org.transit.app.newspaperapp.services.CommentServiceInterface {
    private final List<Comment> comments = new ArrayList<>();

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
}
