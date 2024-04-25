package org.transit.app.busmonitoringapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class homePageController {
    @FXML
    private Button logout;

    public void logoutButton(ActionEvent e) {
        Stage stage  = (Stage) logout.getScene().getWindow();
        stage.close();
    }
}
