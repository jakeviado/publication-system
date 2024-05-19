package org.transit.app.newspaperapp.controller.mainpage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    public void setNewsBtn() throws IOException {
        switchScene("views/Mainpage/Categories/categoryComponents/category-News.fxml");
    }

    public void setSportsBtn() throws IOException {
        switchScene("views/Mainpage/Categories/categoryComponents/category-Sports.fxml");
    }

    public void setBusinessBtn() throws IOException {
        switchScene("views/Mainpage/Categories/categoryComponents/category-Business.fxml");
    }

    public void setOpinionBtn() throws IOException {
        switchScene("views/Mainpage/Categories/categoryComponents/category-Opinion.fxml");
    }

    public void setTechnologyBtn() throws IOException {
        switchScene("views/Mainpage/Categories/categoryComponents/category-Technology.fxml");
    }

    public void setEntertainmentBtn() throws IOException{
        switchScene("views/Mainpage/Categories/categoryComponents/category-Entertainment.fxml");
    }

    private void switchScene(String fxmlFile) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFile)));
        categoriesPane.setCenter(nextVbox);
    }
}