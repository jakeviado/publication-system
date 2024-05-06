package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.transit.app.newspaperapp.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class categoriesController  implements Initializable {

    public Button newsBtn;

    public Button sportsBtn;

    public Button businessBtn;

    public Button opinionBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("category-News.fxml"));
    }
}
