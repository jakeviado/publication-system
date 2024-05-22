package org.transit.app.newspaperapp.controller.mainpage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.controller.articleLoader;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.net.URL;
import java.util.ResourceBundle;

public class newsfeed extends articleLoader implements Initializable {
    @FXML
    public VBox articleVboxContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ArticleTr load  = new ArticleTr();
//        loadArticlesList(load, articleVboxContainer);
        ArticleTr articleService = new ArticleTr();
        loadArticlesByCategory(articleService, articleVboxContainer, "All");
    }
}
