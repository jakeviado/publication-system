package org.transit.app.newspaperapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.sceneSwitch;

import java.io.IOException;
import java.util.Objects;


public class homepage {
    @FXML
    public VBox homepageScene;

    @FXML
    public VBox slidePanel;

    @FXML
    public ToggleButton toggleButton;

    @FXML
    public BorderPane MainBorderPanel;

    @FXML
    public Button logoutButton;

    @FXML
    public Button writeArticleButton;

    @FXML
    public BorderPane articleContainerBorderPane;

    @FXML
    public Button homeBtn;

    @FXML
    public Button categoriesBtn;

    @FXML
    public Button aboutBtn;

    @FXML
    public Button accountBtn;

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

    public void setCategoriesBtn(ActionEvent event) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("categories.fxml")));
        articleContainerBorderPane.setCenter(nextVbox);
    }

    public void publishArticlePage(ActionEvent event) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("publishingPage.fxml")));
        articleContainerBorderPane.setCenter(nextVbox);
    }

    public void setAboutBtn(ActionEvent event) throws IOException {
        BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("about.fxml")));
        articleContainerBorderPane.setCenter(about);
    }

    public void setAccountBtn(ActionEvent event) throws IOException {
        BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("account.fxml")));
        articleContainerBorderPane.setCenter(about);
    }
}
