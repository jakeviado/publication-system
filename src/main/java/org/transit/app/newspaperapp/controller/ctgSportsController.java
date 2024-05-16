package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.net.URL;
import java.util.ResourceBundle;

public class ctgSportsController extends articleLoader implements Initializable {
    @FXML
    public VBox ctgSportsContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArticleTr load  = new ArticleTr();
        loadSportsArticles(load, ctgSportsContainer);
    }
}
