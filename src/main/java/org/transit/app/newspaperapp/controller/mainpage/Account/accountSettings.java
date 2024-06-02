package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
//import javafx.scene.control.Button;

public class accountSettings {

        @FXML
        private VBox inputCredentialsField;

        @FXML
        private ToggleButton changeUsernameBtn;

    @FXML
    private VBox inputPasswordField;

    @FXML
    private ToggleButton changePasswordBtn;

//    @FXML
//    private Button deleteAccountBtn;

    @FXML
    private TextField confirmNewPwTextfield;

    @FXML
    private TextField confirmNewUnTextfield;

    @FXML
    private TextField newPasswordTextfield;

    @FXML
    private TextField newUsernameTextfield;

    @FXML
    private TextField oldPasswordTextfield;

    @FXML
    private TextField oldUsernameTextfield;

        public void initialize() {
            initializeChangePasswordPanel();
            initializeChangeUsernamePanel();
        }

        private void initializeChangePasswordPanel(){
            inputPasswordField.setTranslateY(-562);
            inputPasswordField.setMinWidth(0);
            inputPasswordField.setMaxWidth(0);
//            toggleButton.setText("MENU");
            changePasswordBtn.setSelected(false);

            changePasswordMenu();
        }

        private void initializeChangeUsernamePanel(){
            inputCredentialsField.setTranslateY(-562);
            inputCredentialsField.setMinWidth(0);
            inputCredentialsField.setMaxWidth(0);
            changeUsernameBtn.setSelected(false);

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

    public void changePasswordMenu(){
        changePasswordBtn.setOnAction(event -> {
            boolean isSelected = changePasswordBtn.isSelected();

            double targetY = isSelected ? -562 : 0;
            double targetWidth = isSelected ? 0 : 562;
//            String buttonText = isSelected ? "MORE" : "CLOSE";

            Timeline timeline = new Timeline();

            KeyValue translateY = new KeyValue(inputPasswordField.translateYProperty(), targetY, Interpolator.EASE_OUT);
            KeyValue resizeWidth = new KeyValue(inputPasswordField.prefWidthProperty(), targetWidth, Interpolator.EASE_OUT);

            KeyValue slidePanelWidth = new KeyValue(inputPasswordField.minWidthProperty(), targetWidth, Interpolator.EASE_OUT);
            KeyValue slidePanelMaxWidth = new KeyValue(inputPasswordField.maxWidthProperty(), targetWidth, Interpolator.EASE_OUT);

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), translateY, resizeWidth, slidePanelWidth, slidePanelMaxWidth);

            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

//            changeUsernameBtn.setText(buttonText);
        });
    }

//    public void changeOldUsername(){
//        String username = oldUsernameTextfield.getText();
//    }
}