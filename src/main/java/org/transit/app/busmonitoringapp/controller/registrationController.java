package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.transit.app.busmonitoringapp.logic.signup;
import org.transit.app.busmonitoringapp.model.Signup;
import org.transit.app.busmonitoringapp.model.sceneSwitch;

import java.io.IOException;
import java.sql.SQLException;

public class registrationController {
    @FXML public Button registerStartButton;
    public Label notifyLabel;
    public VBox registrationScene;

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    public TextField nameTextField;
    @FXML public PasswordField confirmPasswordTextField;


    @FXML
    public void signup() throws SQLException {
        Signup userData = new Signup(nameTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
        signup signupLogic = new signup();

        if (signupLogic.registerUser(userData)) {
            notifyLabel.setText("Registered");
        } else {
            notifyLabel.setText("Incorrect username or password");
        }
    }

    public void backToLogin() throws IOException {
        new sceneSwitch(registrationScene, "loginPage.fxml");
    }
}
