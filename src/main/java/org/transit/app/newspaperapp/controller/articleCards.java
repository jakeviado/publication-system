package org.transit.app.newspaperapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.transit.app.newspaperapp.model.Comments;
import org.transit.app.newspaperapp.model.UserSession;
import org.transit.app.newspaperapp.services.ArticleTr;
import org.transit.app.newspaperapp.services.CommentService;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class articleCards extends articleLoader implements Initializable {
    @FXML
    public Hyperlink headlineLabel;

    @FXML
    public Label bylineLabel;

    @FXML
    public Label contentLabel;

    @FXML
    public Label dateLabel;

    @FXML
    public Label authorLabel;

    @FXML
    public BorderPane articleCard;

    @FXML
    public GridPane gridPane;

    @FXML
    public ImageView imageView;

    @FXML
    public Label ctgryLbl;

    @FXML
    public VBox commentsSection;

    @FXML
    public VBox commentsList;

    @FXML
    public Button commentsBtn;

    private List<Comments> comments;

    @FXML
    public TextField newCommentField;

    @FXML
    public Button saveArticleAction;

    private final CommentService commentService = new CommentService();

    private int articleId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setArticleImage(null);
        loadCommentsForArticle(articleId);
    }

    public void setArticleTexts(String headline, String byline, String content, String publishedDate, String category, String authorName, int articleId) {
        this.articleId = articleId;
        headlineLabel.setText(headline);
        bylineLabel.setText(String.valueOf(articleId)); // place holder ko muna ng article id ito orig line: bylineLabel.setText(byline);
        contentLabel.setText(content);
        dateLabel.setText(publishedDate);
        ctgryLbl.setText(category);
        authorLabel.setText("By: " + authorName);

    }

    public void setArticleImage(Image image) {
        imageView.setImage(image);
        Rectangle clipRect = new Rectangle(1000, 600);
        imageView.setClip(clipRect);
    }

    public void removeArticleImage() {
        HBox parentPane = (HBox) imageView.getParent();
        if (parentPane != null) {
            parentPane.getChildren().remove(imageView);
        }
    }

    private void loadCommentsForArticle(int articles) {
        loadComments(commentsList, articles);
    }

    @FXML
    private void handleSubmitComment() {
        String commentText = newCommentField.getText();
        if (!commentText.isEmpty()) {
            Comments comment = new Comments(0, articleId, UserSession.getInstance().getUserId(), commentText, LocalDateTime.now());
            commentService.addComment(comment);
            newCommentField.clear();
            loadCommentsForArticle(articleId);
        }
    }

    public void showCommentSection() {
        loadCommentsForArticle(articleId);
    }

    public void saveArticleAction(ActionEvent event) {
        saveArticle();
    }

    private void saveArticle() {
        try {
            ArticleTr articleTr = new ArticleTr();
            articleTr.saveArticle(UserSession.getInstance().getUserId(), articleId);
            System.out.println("Article saved successfully!");
        } catch (RuntimeException e) {
            System.err.println("Failed to save article: " + e.getMessage());
        }
    }
}
