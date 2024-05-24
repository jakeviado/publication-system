package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class accountSettings {

    @FXML
    private VBox inputCredentialsField;

    @FXML
    private ToggleButton changeUsernameBtn;

    public void initialize() {
        changeUsernameMenu();
    }

    public void changeUsernameMenu(){
        changeUsernameBtn.setOnAction(event -> {
            boolean isSelected = changeUsernameBtn.isSelected();

            double targetY = isSelected ? -562 : 0;
            double targetWidth = isSelected ? 0 : 562;
//            String buttonText = isSelected ? "MORE" : "CLOSE";

            Timeline timeline = new Timeline();

            KeyValue translateY = new KeyValue(inputCredentialsField.translateYProperty(), targetY, Interpolator.EASE_OUT);
            KeyValue resizeWidth = new KeyValue(inputCredentialsField.prefWidthProperty(), targetWidth, Interpolator.EASE_OUT);

            KeyValue slidePanelWidth = new KeyValue(inputCredentialsField.minWidthProperty(), targetWidth, Interpolator.EASE_OUT);
            KeyValue slidePanelMaxWidth = new KeyValue(inputCredentialsField.maxWidthProperty(), targetWidth, Interpolator.EASE_OUT);

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), translateY, resizeWidth, slidePanelWidth, slidePanelMaxWidth);

            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

//            changeUsernameBtn.setText(buttonText);
        });
    }
}
