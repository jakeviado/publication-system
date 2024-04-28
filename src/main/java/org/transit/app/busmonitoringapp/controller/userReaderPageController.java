package org.transit.app.busmonitoringapp.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class userReaderPageController {
    public VBox homepageScene;
    public Button closePaneButton;
    public Button openPaneButton;
    public VBox slidePanel;
    public BorderPane mainPanel;
    @FXML
    private Button exitButton;

    public void initialize(){
        slidePanel.setTranslateX(-190);
    }

    public void close(MouseEvent mouseEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slidePanel);


        slide.setToX(-190);
        slide.play();

        slidePanel.setTranslateX(0);

        slide.setOnFinished( (ActionEvent e) -> {
            openPaneButton.setVisible(true);
            closePaneButton.setVisible(false);
        });

    }

    public void open(MouseEvent mouseEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slidePanel);

        slide.setToX(0);
        slide.play();

        slidePanel.setTranslateX(190);

        slide.setOnFinished( (ActionEvent e) -> {
            openPaneButton.setVisible(false);
            closePaneButton.setVisible(true);
        });
    }



    public void exitApp() {
        Stage stage  = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
