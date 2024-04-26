package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.transit.app.busmonitoringapp.logic.signup;
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
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public PasswordField confirmPasswordTextField;



    @FXML
    public void signup()  {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (!password.equals(confirmPassword)) {
            notifyLabel.setText("Passwords do not match. Please try again.");
            return;
        }

        Signup userData = new Signup(name, username, password);

        signup signupLogic = new signup();
        try {
            if (signupLogic.registerUser(userData)) {
                notifyLabel.setText("Registered successfully!");
                nameTextField.clear();
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
