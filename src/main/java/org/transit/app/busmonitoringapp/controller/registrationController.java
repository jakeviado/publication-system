package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
//import org.transit.app.busmonitoringapp.logic.registerUserInfo;
import org.transit.app.busmonitoringapp.logic.transactions;
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
    public Button registerAuthorButton;

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
        transactions registerUserInfoLogic = new transactions();

        try {
            if (registerUserInfoLogic.registerQuery(userData)) {
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
        registerAuthorButton.setDisable(!checkbox.isSelected());
        registerStartButton.setDisable(!checkbox.isSelected());
    }

    public void backToLogin() throws IOException {
        new sceneSwitch(registrationScene, "loginForm.fxml");
    }
}
