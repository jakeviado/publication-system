package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.User;
import org.transit.app.newspaperapp.model.UserSession;
import org.transit.app.newspaperapp.services.UserTr;

import java.io.IOException;
import java.util.Objects;

public class accountSettings {

    public Button deleteAccountBtn;

    @FXML
    public Label alertLbl;

    @FXML
    public TextField confirmNewPasswordTextfield;

    @FXML
    public TextField confirmNewUnTextfield;

    @FXML
    private TextField oldUsernameTextfield;

    @FXML
    private TextField newUsernameTextfield;

    @FXML
    private TextField oldPasswordTextfield;

    @FXML
    private TextField newPasswordTextfield;

    @FXML
    private Button changeUsernameBtn;

    @FXML
    private Button changePasswordBtn;

    @FXML
    public void handleChangeUsername() throws IOException {
        String oldUsername = oldUsernameTextfield.getText().trim();
        String newUsername = newUsernameTextfield.getText().trim();
        String confirmNewUsername = confirmNewUnTextfield.getText().trim();

        if (!oldUsername.isEmpty() && !newUsername.isEmpty() && !confirmNewUsername.isEmpty()) {
            if (!newUsername.equals(confirmNewUsername)) {
                alertLbl.setText("New usernames don't match");
                return;
            }

            if (!newUsername.matches("\\S+")) {
                alertLbl.setText("Username cannot contain white spaces");
                return;
            }

            UserSession loggedInUserSession = UserSession.getInstance();
            int userId = loggedInUserSession.getUserId();

            if (loggedInUserSession.getLoggedInUser() != null && loggedInUserSession.getLoggedInUser().getUsername().equals(oldUsername)) {
                boolean success = UserTr.changeUsername(userId, newUsername);
                if (success) {
                    alertLbl.setText("Username changed successfully");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Username changed successfully");
                    alert.setHeaderText(null);
                    alert.setContentText("Need to re-log-in");
                    alert.show();

                    switchToLoginForm();
                    clearSession(UserSession.getInstance().getLoggedInUser());
                } else {
                    alertLbl.setText("Failed to change username");
                }
            } else {
                alertLbl.setText("Incorrect old username");
            }
        } else {
            alertLbl.setText("Old, new, or confirmation username cannot be empty");
        }
    }

    @FXML
    public void handleChangePassword() throws IOException {
        String oldPassword = oldPasswordTextfield.getText().trim();
        String newPassword = newPasswordTextfield.getText().trim();
        String confirmNewPassword = confirmNewPasswordTextfield.getText().trim();

        if (!oldPassword.isEmpty() && !newPassword.isEmpty() && !confirmNewPassword.isEmpty()) {
            if (!newPassword.equals(confirmNewPassword)) {
                alertLbl.setText("New passwords don't match");
                return;
            }

            UserSession loggedInUserSession = UserSession.getInstance();
            int userId = loggedInUserSession.getUserId();

            if (loggedInUserSession.getLoggedInUser() != null && loggedInUserSession.getLoggedInUser().checkPassword(oldPassword)) {
                boolean success = UserTr.changePassword(userId, newPassword);
                if (success) {
                    alertLbl.setText("Password changed successfully");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password changed successfully");
                    alert.setHeaderText(null);
                    alert.setContentText("Need to re-log-in");
                    alert.show();

                    switchToLoginForm();
                    clearSession(UserSession.getInstance().getLoggedInUser());
                } else {
                    alertLbl.setText("Failed to change password");
                }
            } else {
                alertLbl.setText("Incorrect old password");
            }
        } else {
            alertLbl.setText("Old, new, or confirmation password cannot be empty");
        }
    }

    @FXML
    public void handleDeleteAccount() {
        UserSession loggedInUserSession = UserSession.getInstance();
        int userId = loggedInUserSession.getUserId();

        if (loggedInUserSession.getLoggedInUser() != null) {
            boolean success = UserTr.deleteAccount(userId);
            if (success) {
                System.out.println("Account deleted successfully");
                try {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Account deleted successfully");
                    alert.setHeaderText(null);
                    alert.setContentText("thank you");
                    alert.show();

                    switchToLoginForm();
                    clearSession(UserSession.getInstance().getLoggedInUser());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed to delete account");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred");
                alert.showAndWait();

                alertLbl.setText("Failed to delete account");
            }
        } else {
            System.out.println("User not logged in");
        }
    }

    public void clearSession(User user) {
        user.setUsername(null);
        user.setPassword(null);
        UserSession.getInstance().clearSession();
    }

    private void switchToLoginForm() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/LoginPage/loginForm.fxml")));
        Stage stage = (Stage) deleteAccountBtn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

}
