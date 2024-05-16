package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class newsfeed extends articleLoader implements Initializable {
    @FXML
    public VBox articleVboxContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArticleTr load  = new ArticleTr();
        loadArticlesList(load, articleVboxContainer);
    }
}
