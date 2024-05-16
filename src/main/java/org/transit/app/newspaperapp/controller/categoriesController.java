package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.services.ArticleTr;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class categoriesController implements Initializable {

    @FXML
    public BorderPane categoriesPane;

    @FXML
    public VBox categoriesContainer;

    //public Button technologyBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFirst();
    }

    public void loadFirst() {
        BorderPane nextVbox = null;
        try {
            nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("category-News.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        categoriesContainer.getChildren().add(nextVbox);
    }

    public void setNewsBtn() throws IOException {
        switchScene("category-News.fxml");
    }

    public void setSportsBtn() throws IOException {
        switchScene("category-Sports.fxml");
    }

    public void setBusinessBtn() throws IOException {
        switchScene("category-Business.fxml");
    }

    public void setOpinionBtn() throws IOException {
        switchScene("category-Opinion.fxml");
    }

    public void setTechnologyBtn() throws IOException {
        switchScene("category-Technology.fxml");
    }

    public void setEntertainmentBtn() throws IOException{
        switchScene("category-Entertainment.fxml");
    }

    private void switchScene(String fxmlFile) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFile)));
        categoriesPane.setCenter(nextVbox);
    }
}