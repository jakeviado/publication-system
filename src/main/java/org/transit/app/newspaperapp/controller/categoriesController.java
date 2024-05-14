package org.transit.app.newspaperapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.transit.app.newspaperapp.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class categoriesController  implements Initializable {

    public Button newsBtn;

    public Button sportsBtn;

    public Button businessBtn;

    public Button opinionBtn;

    public Button technologyBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("category-News.fxml"));

        newsBtn.setOnAction(this::newsButton);
        sportsBtn.setOnAction(this::sportsButton);
        businessBtn.setOnAction(this::businessButton);
        opinionBtn.setOnAction(this::opinionButton);
        //technologyBtn.setOnAction(this::technologyButton);
    }
    public void newsButton(ActionEvent event) {
        categoryFXML("category-News.fxml");
    }

    public void businessButton(ActionEvent event) {
        categoryFXML("category-Business.fxml");
    }

    public void sportsButton(ActionEvent event) {
        categoryFXML("category-Sports.fxml");
    }

    public void opinionButton(ActionEvent event) {
        categoryFXML("category-Opinion.fxml");
    }

    //public void technologyButton(ActionEvent event){
      //categoryFXML("category-Technology.fxml");
    //}

    public void categoryFXML(String categories) {
        try {
            BorderPane categoryPane = FXMLLoader.load(getClass().getResource(categories));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}