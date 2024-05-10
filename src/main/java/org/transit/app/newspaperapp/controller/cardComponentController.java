package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;


public class cardComponentController {
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

    public void setArticleTexts(String headline, String byline, String content, String publishedDate) {
        headlineLabel.setText(headline);
        bylineLabel.setText(byline);
        contentLabel.setText(content);
        dateLabel.setText(publishedDate);
    }

    public void setArticleImage(Image image) {
        imageView.setImage(image);

        Rectangle clipRect = new Rectangle(700, 400);
        clipRect.setArcWidth(2);
        clipRect.setArcHeight(2);

        imageView.setClip(clipRect);
    }
}
