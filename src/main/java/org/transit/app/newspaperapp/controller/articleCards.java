package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import javafx.geometry.NodeOrientation;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class articleCards implements Initializable {
    @FXML
    public Label headlineLabel;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomOrientation();
    }

    public void setArticleTexts(String headline, String byline, String content, String publishedDate, String category) {
        headlineLabel.setText(headline);
        bylineLabel.setText(byline);
        contentLabel.setText(content);
        dateLabel.setText(publishedDate);
        ctgryLbl.setText(category);
    }

    public void setArticleImage(Image image) {
        imageView.setImage(image);
        Rectangle clipRect = new Rectangle(1000, 600);
        imageView.setClip(clipRect);
    }

    public void randomOrientation() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);

        if (randomNumber == 0) {
            articleCard.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        } else {
            articleCard.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    public void removeArticleImage() {
//        imageView.setImage(null);
        HBox parentPane = (HBox) imageView.getParent();
        if (parentPane != null) {
            parentPane.getChildren().remove(imageView);
        }
    }

    private void saveArticle(int userId, int articleId) {
        try {
            ArticleTr articleTr =  new ArticleTr();
            articleTr.saveArticle(userId, articleId);
            System.out.println("Article saved successfully!");
        } catch (Exception e) {
            System.err.println("Failed to save article: " + e.getMessage());
        }
    }
}
