package org.transit.app.newspaperapp.controller.categoryComponents;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.controller.articleLoader;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.net.URL;
import java.util.ResourceBundle;

public class ctgEntertainmentController extends articleLoader implements Initializable {
    @FXML
    public VBox ctgEntertainmentContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ArticleTr load  = new ArticleTr();
//        loadEntertainmentArticles(load, ctgEntertainmentContainer);
        ArticleTr articleService = new ArticleTr();
        loadArticlesByCategory(articleService, ctgEntertainmentContainer, "Entertainment");
    }
}
