package org.transit.app.newspaperapp.controller.mainpage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class categoriesController implements Initializable {

    @FXML
    public BorderPane categoriesPane;
    @FXML
    public VBox categoriesContainer;
    @FXML
    public Button latestBtn;
    @FXML
    public Button sportsBtn;
    @FXML
    public Button entertainmentBtn;
    @FXML
    public Button opinionBtn;
    @FXML
    public Button businessBtn;
    @FXML
    public Button technologyBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFirst() throws IOException {
        setNewsBtn();
    }

    @FXML
    public void setNewsBtn() throws IOException {
        switchScene("views/categoryComponents/category-News.fxml", latestBtn);
    }

    @FXML
    public void setSportsBtn() throws IOException {
        switchScene("views/categoryComponents/category-Sports.fxml", sportsBtn);
    }

    @FXML
    public void setBusinessBtn() throws IOException {
        switchScene("views/categoryComponents/category-Business.fxml", businessBtn);
    }

    @FXML
    public void setOpinionBtn() throws IOException {
        switchScene("views/categoryComponents/category-Opinion.fxml", opinionBtn);
    }

    @FXML
    public void setTechnologyBtn() throws IOException {
        switchScene("views/categoryComponents/category-Technology.fxml", technologyBtn);
    }

    @FXML
    public void setEntertainmentBtn() throws IOException {
        switchScene("views/categoryComponents/category-Entertainment.fxml", entertainmentBtn);
    }

    private void switchScene(String fxmlFile, Button button) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFile)));
        categoriesPane.setCenter(nextVbox);
        button.requestFocus();
    }
}
