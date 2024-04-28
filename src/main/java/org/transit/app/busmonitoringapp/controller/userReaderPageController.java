package org.transit.app.busmonitoringapp.controller;

import javafx.scene.control.ToggleButton;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.transit.app.busmonitoringapp.model.Signup;
import org.transit.app.busmonitoringapp.model.sceneSwitch;
import org.transit.app.busmonitoringapp.logic.retrieveUserInfo;

import java.io.IOException;


public class userReaderPageController {
    public VBox homepageScene;
    public VBox slidePanel;
    @FXML
    public Label welcomeNameLabel;
    @FXML
    public ToggleButton toggleButton;
    @FXML
    public BorderPane MainBorderPanel;
    @FXML
    public Button logoutButton;
    @FXML
    private Button exitButton;

    public void initialize(){
        Signup name = new Signup();
        welcomeNameLabel.setText(name.name());
    }

    public void toggle(ActionEvent mouseEvent) {
        toggleButton.setOnAction(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(slidePanel);

            double targetX;
            if (toggleButton.isSelected()) {
                targetX = -200;
                toggleButton.setText("MORE");
            } else {
                targetX = 200;
                toggleButton.setText("CLOSE");
            }
            slide.setToX(targetX);
            slide.play();
        });
    }

    public void exitApp() {
        Stage stage  = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    public void setLogoutButton() throws IOException {
        new sceneSwitch(homepageScene, "loginPage.fxml");
    }
}
