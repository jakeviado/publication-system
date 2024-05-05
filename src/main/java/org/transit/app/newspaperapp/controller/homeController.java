package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.logic.transactions;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML
    public VBox articleVboxContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transactions loadArticlesQuery = new transactions();

        List<Articles> articlesList = loadArticlesQuery.loadArticlesQuery();

        for (Articles article : articlesList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cardArticleContainer.fxml"));
                BorderPane card = fxmlLoader.load();



                cardComponentController controller = fxmlLoader.getController();
                controller.setArticleTexts(article.getHeadline(), article.getByline(), article.getContent(), article.getPublicationDate().toString());
                articleVboxContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private List<Articles> loadArticlesQuery() {
////        transactions loadArticlesQuery = new transactions();
//        return List.of();
//    }

}
