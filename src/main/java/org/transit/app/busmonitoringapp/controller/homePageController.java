package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class homePageController {
    public VBox homepageScene;
    @FXML
    private Button logout;

    public void logoutButton() {
        Stage stage  = (Stage) logout.getScene().getWindow();
        stage.close();
    }
}
