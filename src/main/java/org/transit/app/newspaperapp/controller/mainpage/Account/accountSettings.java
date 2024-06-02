package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

    public class accountSettings {

        @FXML
        private VBox inputCredentialsField;

        @FXML
        private ToggleButton changeUsernameBtn;

        @FXML
        private TextField newUsernameField;

        @FXML
        private PasswordField currentPasswordField;

        @FXML
        private PasswordField newPasswordField;

        @FXML
        private PasswordField confirmPasswordField;

        @FXML
        private Button updateUsername;

        @FXML
        private Button updatePassword;


        public void initialize() {
            changeUsernameMenu();
//            updateUsername.setOnAction(event -> updateUsername());
//            updatePassword.setOnAction(event -> updatePassword());
        }

        private void changeUsernameMenu() {
            changeUsernameBtn.setOnAction(event -> {
                boolean isSelected = changeUsernameBtn.isSelected();

                double targetY = isSelected ? -562 : 0;
                double targetWidth = isSelected ? 0 : 562;

                Timeline timeline = new Timeline();

                KeyValue translateY = new KeyValue(inputCredentialsField.translateYProperty(), targetY, Interpolator.EASE_OUT);
                KeyValue resizeWidth = new KeyValue(inputCredentialsField.prefWidthProperty(), targetWidth, Interpolator.EASE_OUT);
                KeyValue slidePanelWidth = new KeyValue(inputCredentialsField.minWidthProperty(), targetWidth, Interpolator.EASE_OUT);
                KeyValue slidePanelMaxWidth = new KeyValue(inputCredentialsField.maxWidthProperty(), targetWidth, Interpolator.EASE_OUT);

                KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), translateY, resizeWidth, slidePanelWidth, slidePanelMaxWidth);

                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            });
        }

        @FXML
        private void updateUsername() {
            String newUsername = newUsernameField.getText().trim();

            if (newUsername.isEmpty()) {
                System.out.println("Username cannot be empty");
                return;
            }

            System.out.println("New Username: " + newUsername);
            newUsernameField.clear();
        }

        @FXML
        private void updatePassword() {
            String currentPassword = currentPasswordField.getText().trim();
            String newPassword = newPasswordField.getText().trim();
            String confirmPassword = confirmPasswordField.getText().trim();

            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                System.out.println("All password fields must be filled.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                System.out.println("Password does not match");
                return;
            }

            if (!currentPassword.equals("currentPassword")) {
                System.out.println("Current password is incorrect.");
                return;
            }
            System.out.println("Password updated to: " + newPassword);
            currentPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
        }
    }