package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
//import org.transit.app.busmonitoringapp.logic.registerUserInfo;
import org.transit.app.busmonitoringapp.logic.retrieveUserInfo;
import org.transit.app.busmonitoringapp.model.Signup;
import org.transit.app.busmonitoringapp.model.sceneSwitch;

import java.io.IOException;
import java.sql.SQLException;

public class registrationController {
    @FXML
    public Button registerStartButton;
    public Label notifyLabel;
    public VBox registrationScene;
    public CheckBox checkbox;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    public PasswordField confirmPasswordTextField;

    @FXML
    public void signup()  {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        String first_name = firstNameTextField.getText();
        String last_name = lastNameTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (!password.equals(confirmPassword)) {
            notifyLabel.setText("Passwords do not match");
            return;
        }

        Signup userData = new Signup(username, password, email, first_name, last_name);

        retrieveUserInfo registerUserInfoLogic = new retrieveUserInfo();
        try {
            if (registerUserInfoLogic.registerUser(userData)) {
                notifyLabel.setText("Registered successfully!");
                firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                usernameTextField.clear();
                passwordTextField.clear();
                confirmPasswordTextField.clear();
            } else {
                notifyLabel.setText("Registration failed. Please check username or password.");
            }
        } catch (SQLException e) {
            notifyLabel.setText("Registration failed.");
        }
    }

    public void enableRegisterButton() {
        registerStartButton.setDisable(!checkbox.isSelected());
    }

    public void backToLogin() throws IOException {
        new sceneSwitch(registrationScene, "loginPage.fxml");
    }
}
