package org.transit.app.busmonitoringapp.controller;

import javafx.scene.control.ToggleButton;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.transit.app.busmonitoringapp.model.sceneSwitch;

import java.io.IOException;


public class homepage {
    @FXML
    public VBox homepageScene;

    @FXML
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
    public GridPane articleGrid;

    @FXML
    public VBox object;

    @FXML
    public Button writeArticleButton;

    @FXML
    private Button exitButton;

    public void toggle() {
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
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void setLogoutButton() throws IOException {
        new sceneSwitch(homepageScene, "loginForm.fxml");
    }

    public void publishArticlePage() throws IOException {
        new sceneSwitch(homepageScene, "authorPublishingPage.fxml");
    }
}
