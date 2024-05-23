package org.transit.app.newspaperapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import javafx.geometry.NodeOrientation;
import javafx.stage.Stage;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.controller.mainpage.mainpage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;


public class articleCards implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomOrientation();
        setArticleImage(null);
    }

    public void setArticleTexts(String headline, String byline, String content, String publishedDate, String category, String author_name) {
        headlineLabel.setText(headline);
        bylineLabel.setText(byline);
        contentLabel.setText(content);
        dateLabel.setText(publishedDate);
        ctgryLbl.setText(category);
        authorLabel.setText("By: " + author_name);
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

    public void articleHyperlinkClick(ActionEvent event) throws IOException {
//        mainpage.setArticleContent(headlineLabel.getText(), bylineLabel.getText(), contentLabel.getText(), dateLabel.getText(), ctgryLbl.getText(), authorLabel.getText());
    }

    public void removeArticleImage() {
        HBox parentPane = (HBox) imageView.getParent();
        if (parentPane != null) {
            parentPane.getChildren().remove(imageView);
        }
    }

    public void closeModal(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

//    private void saveArticle(User userId, Articles articleId) {
//        try {
//            ArticleTr articleTr = new ArticleTr();
//            articleTr.saveArticle();
//            System.out.println("Article saved successfully!");
//        } catch (RuntimeException e) {
//            System.err.println("Failed to save article: " + e.getMessage());
//        }
//    }
//
//    public void saveArticleAction(ActionEvent event) {
//        saveArticle();
//    }
}
