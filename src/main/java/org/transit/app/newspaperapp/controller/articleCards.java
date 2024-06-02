package org.transit.app.newspaperapp.controller;

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
    public ImageView imageView;

    @FXML
    public Label ctgryLbl;

    @FXML
    public VBox commentsSection;

    @FXML
    public VBox commentsList;

    @FXML
    public VBox commentsListContainer;

    @FXML
    public TextField newCommentField;

    @FXML
    public Button saveArticleAction;

    private final CommentService commentService = new CommentService();

    @FXML
    public Label numberOfCommentsLbl;

    private int articleId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setArticleTexts(String headline, String byline, String content, String publishedDate, String category, String authorName, int articleId) {
        this.articleId = articleId;
        headlineLabel.setText(headline);
        bylineLabel.setText("/ " + byline);
        contentLabel.setText(content);
        dateLabel.setText(publishedDate);
        ctgryLbl.setText(category);
        authorLabel.setText("By: " + authorName);

        loadNumberOfComments(articleId);
        updateSaveButtonStatus();
    }

    public void setArticleImage(Image image) {
        imageView.setImage(image);

        double fixedWidth = 300;
        double fixedHeight = 900;
        imageView.setFitWidth(fixedWidth);
        imageView.setFitHeight(fixedHeight);
        imageView.setPreserveRatio(true);

        double aspectRatio = image.getWidth() / image.getHeight();

        double clipWidth = fixedWidth;
        double clipHeight = fixedHeight;

        if (image.getWidth() < fixedWidth && image.getHeight() < fixedHeight) {
            if (aspectRatio > fixedWidth / fixedHeight) {
                clipHeight = fixedWidth / aspectRatio;
            } else {
                clipWidth = fixedHeight * aspectRatio;
            }
        }

        Rectangle clipRect = new Rectangle(clipWidth, clipHeight);
        imageView.setClip(clipRect);

        clipRect.widthProperty().bind(imageView.fitWidthProperty());
        clipRect.heightProperty().bind(imageView.fitHeightProperty());
    }

    public void removeArticleImage() {
        HBox parentPane = (HBox) imageView.getParent();
        if (parentPane != null) {
            parentPane.getChildren().remove(imageView);
        }
    }

    private void loadCommentsForArticle(int articleId) {
        loadComments(commentsList, articleId);
    }

    private void loadNumberOfComments(int articleId) {
        List<Comments> comments = commentService.getComments(articleId);
        numberOfCommentsLbl.setText("Comments (" + comments.size() + ")");
        numberOfCommentsLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
    }

    @FXML
    private void handleSubmitComment() {
        String commentText = newCommentField.getText().trim();
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

    private void updateSaveButtonStatus() {
        int userId = UserSession.getInstance().getUserId();
        isArticleSaved = ArticleTr.isArticleSavedByUser(userId, articleId);
        if (isArticleSaved) {
            saveArticleAction.setText("Saved");
            saveArticleAction.setStyle("-fx-background-color: #000000; -fx-text-fill: #f4f4f4;");
        } else {
            saveArticleAction.setText("Save");
            saveArticleAction.setStyle("-fx-background-color: #f4f4f4; -fx-text-fill: #000000;");
        }
    }

    private boolean isArticleSaved = false;

    @FXML
    public void saveArticleAction() {
        if (isArticleSaved) {
            unsaveArticle();
        } else {
            saveArticle();
        }
        updateSaveButtonStatus();
    }


    private void saveArticle() {
        try {
            ArticleTr.saveArticle(UserSession.getInstance().getUserId(), articleId);
            System.out.println("article saved");
        } catch (RuntimeException e) {
            System.err.println("failed to save: " + e.getMessage());
        }
    }

    private void unsaveArticle() {
        try {
            ArticleTr.unsaveArticle(UserSession.getInstance().getUserId(), articleId);
            System.out.println("article unsaved");
        } catch (RuntimeException e) {
            System.err.println("failed to unsave: " + e.getMessage());
        }
    }
}
