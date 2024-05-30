package org.transit.app.newspaperapp.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.services.ArticleTr;
import org.transit.app.newspaperapp.model.Articles;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class articleLoader implements Initializable {

    protected void loadArticlesByCategory(ArticleTr articleService, VBox container, String category) {
        List<Articles> articlesList = switch (category) {
            case "All" -> articleService.loadArticlesQuery();
            case "Sports" -> articleService.loadSportsArticle();
            case "Business" -> articleService.loadBusinessArticle();
            case "Entertainment" -> articleService.loadEntertainmentArticle();
            case "Opinion" -> articleService.loadOpinionArticle();
            case "Technology" -> articleService.loadTechnologyArticle();
            default -> throw new IllegalArgumentException("Invalid category: " + category);
        };

        if (articlesList != null) {
            for (Articles article : articlesList) {
                BorderPane card = loadArticleCard(article);
                if (card != null) {
                    container.getChildren().add(card);
                }
            }
        }
    }


    protected BorderPane loadArticleCard(Articles article) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/components/cardArticleContainer.fxml"));
            BorderPane card = fxmlLoader.load();

            articleCards controller = fxmlLoader.getController();
            setArticleController(controller, article);

            return card;
        } catch (IOException e) {
            handleLoadError("Error loading article: " + e.getMessage());
            return null;
        }
    }

    protected void setArticleController(articleCards controller, Articles article) {
        controller.setArticleTexts(article.getHeadline(), article.getByline(), article.getContent(), article.getPublicationDate(), article.getCategory(), article.getAuthor_name());

        try {
            if (article.getImageLink() == null || article.getImageLink().isEmpty()) {
                controller.removeArticleImage();
            } else {
                Consumer<Image> setImageCallback = controller::setArticleImage;
                loadImageFromURL(article.getImageLink(), setImageCallback);
            }
        } catch (IllegalArgumentException e) {
            handleLoadError("Error loading image: " + e.getMessage());
            controller.removeArticleImage();
        }
    }

    private final Map<String, Image> imageCache = new HashMap<>();

    // optimizations for lazy load image
    protected void loadImageAsync(String imageURL, Consumer<Image> callback) {
        new Thread(() -> {
            try {
                URI uri = new URI(imageURL);
                URL url = uri.toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.connect();
                try (InputStream inputStream = connection.getInputStream()) {
                    Image image = new Image(inputStream);

                    imageCache.put(imageURL, image);
                    Platform.runLater(() -> callback.accept(image));
                }
            } catch (IOException | URISyntaxException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }).start();
    }

    protected void loadImageFromURL(String imageURL, Consumer<Image> callback) {
        if (imageCache.containsKey(imageURL)) {
            callback.accept(imageCache.get(imageURL));
        } else {
            loadImageAsync(imageURL, callback);
        }
    }

    protected void handleLoadError (String errorMessage){
        System.err.println(errorMessage);
    }
}
