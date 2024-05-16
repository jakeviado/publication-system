package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.services.ArticleTr;
import org.transit.app.newspaperapp.model.Articles;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public abstract class articleLoader implements Initializable {

    protected void loadArticlesList(ArticleTr articleService, VBox container) {
        List<Articles> articlesList = articleService.loadArticlesQuery();

        for (Articles article : articlesList) {
            BorderPane card = loadArticleCard(article);
            if (card != null) {
                container.getChildren().add(card);
            }
        }
    }

    protected void loadSportsArticles(ArticleTr articleService, VBox container) {
        List<Articles> articlesList = articleService.loadSportsArticle();

        for (Articles article : articlesList) {
            BorderPane card = loadArticleCard(article);
            if (card != null) {
                container.getChildren().add(card);
            }
        }
    }

    protected BorderPane loadArticleCard(Articles article) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cardArticleContainer.fxml"));
            BorderPane card = fxmlLoader.load();

            articleCards controller = fxmlLoader.getController();
            setArticleController(controller, article);

            return card;
        } catch (IOException e) {
            System.err.println("Error loading article: " + e.getMessage());
            return null;
        }
    }

    protected void setArticleController(articleCards controller, Articles article) {
        controller.setArticleTexts(article.getHeadline(), article.getByline(), article.getContent(), article.getPublicationDate());

        try {
            Image image = loadImageFromURL(article.getImageLink());
            controller.setArticleImage(image);
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    protected Image loadImageFromURL(String imageURL) {
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
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
