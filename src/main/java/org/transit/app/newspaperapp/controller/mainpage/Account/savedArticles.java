package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.io.IOException;
import java.util.List;

public class savedArticles {

    @FXML
    private VBox savedArticleContainer;

    public void initialize() {
        loadSavedArticles();
    }

    private void loadSavedArticles() {
        ArticleTr articleService = new ArticleTr();
        List<Articles> savedArticles = articleService.getSavedArticles();

        for (Articles article : savedArticles) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/components/savedArticleCard.fxml"));
                BorderPane articleCard = loader.load();

                savedArticlesLoader controller = loader.getController();
                controller.setArticleData(article);

                savedArticleContainer.getChildren().add(articleCard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
