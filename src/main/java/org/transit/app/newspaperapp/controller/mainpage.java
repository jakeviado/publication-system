package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.transit.app.newspaperapp.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class mainpage implements Initializable {
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
    public Label notifyLabel;

    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loadNewsFeed()) {
            notifyLabel.setText("Today's Front Page");
        }

    }

    public boolean loadNewsFeed() {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("newsfeed.fxml")));
            articleContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void publishArticlePage() throws IOException {
        switchScene("publishingPage.fxml");
        notifyLabel.setText("Author Exclusive");
    }

    public void toggleMenu() {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.2));
        slide.setNode(slidePanel);

        toggleButton.setOnAction(event -> {
            double targetX = toggleButton.isSelected() ? -200 : 200;
            String buttonText = toggleButton.isSelected() ? "MORE" : "CLOSE";

            slide.setToX(targetX);
            toggleButton.setText(buttonText);
            slide.play();
        });
    }

    public void exitApp() {
        Stage stage  = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void switchScene(String fxmlFile) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFile)));
        articleContainerBorderPane.setCenter(nextVbox);
    }

    public void setLogoutButton() throws IOException {
        switchScene("loginForm.fxml");
    }

    public void setHomeBtn() throws IOException {
        switchScene("newsfeed.fxml");
        notifyLabel.setText("Today's Front Page");
    }

    public void setCategoriesBtn() throws IOException {
        switchScene("categories.fxml");
        notifyLabel.setText("Categories");
    }

    public void setAboutBtn() throws IOException {
        switchScene("about.fxml");
        notifyLabel.setText("About Us");
    }

    public void setAccountBtn() throws IOException {
        switchScene("account.fxml");
        notifyLabel.setText("Account");
    }
}
