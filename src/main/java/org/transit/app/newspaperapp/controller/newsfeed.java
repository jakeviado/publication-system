package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.logic.transactions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class newsfeed implements Initializable {

    @FXML
    public VBox articleVboxContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadArticlesList();
    }


    private void loadArticlesList() {
        transactions loadArticlesQuery = new transactions();
        List<Articles> articlesList = loadArticlesQuery.loadArticlesQuery();

        for (Articles article : articlesList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cardArticleContainer.fxml"));
                BorderPane card = fxmlLoader.load();

                articleCards controller = fxmlLoader.getController();
                controller.setArticleTexts(article.getHeadline(), article.getByline(), article.getContent(), article.getPublicationDate());

//                URL imageUrl = new URL(article.getImageLink());

                Image image = loadImageFromURL(article.getImageLink());
                controller.setArticleImage(image);

                articleVboxContainer.getChildren().add(card);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    private Image loadImageFromURL(String imageURL) {
//        try {
//            return new Image(imageURL);
//        } catch (Exception e) {
//            System.err.println("error loading image from URL: " + imageURL);
//            throw new RuntimeException(e);
//        }
//    }


    // kuha lang sa internet
    private Image loadImageFromURL(String imageURL) {
        try {
            URI uri = new URI(imageURL);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();
            try (InputStream inputStream = connection.getInputStream()) {
                return new Image(inputStream);
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Error loading image: " + imageURL);
            throw new RuntimeException(e);
        }
    }
}
