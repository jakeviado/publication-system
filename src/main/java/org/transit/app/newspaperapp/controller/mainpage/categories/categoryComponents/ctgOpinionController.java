package org.transit.app.newspaperapp.controller.mainpage.categories.categoryComponents;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.controller.articleLoader;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.net.URL;
import java.util.ResourceBundle;

public class ctgOpinionController extends articleLoader implements Initializable {
    @FXML
    public VBox ctgOpinionContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ArticleTr load  = new ArticleTr();
//        loadOpinionArticles(load, ctgOpinionContainer);
        ArticleTr articleService = new ArticleTr();
        loadArticlesByCategory(articleService, ctgOpinionContainer, "Opinion");
    }
}
