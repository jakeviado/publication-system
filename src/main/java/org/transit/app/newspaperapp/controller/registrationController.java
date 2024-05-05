package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import org.transit.app.newspaperapp.logic.transactions;
import org.transit.app.newspaperapp.model.Signup;
import org.transit.app.newspaperapp.model.sceneSwitch;

import java.io.IOException;
import java.sql.SQLException;

public class registrationController {
    @FXML
    public Button registerStartButton;

    @FXML
    public Label notifyLabel;

    @FXML
    public VBox registrationScene;

    @FXML
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
    public void registerAsReader() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        String first_name = firstNameTextField.getText();
        String last_name = lastNameTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (!password.equals(confirmPassword)) {
            notifyLabel.setText("Passwords do not match");
            return;
        } else {
            registerAuthorButton.setDisable(false);
        }

        Signup userData = new Signup(username, password, email, first_name, last_name);
        transactions transact = new transactions();

        try {
            if (transact.registerQuery(userData , false)) {
                notifyLabel.setText("Registered successfully!");
                clearFields();
            }
        } catch (SQLException e) {
            notifyLabel.setText("Registration failed.");
        }
    }

    @FXML
    public void registerAsAuthor() {
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
        transactions transact = new transactions();

        try {
            if (transact.registerQuery(userData, true)) {
                notifyLabel.setText("Registered as author successfully!");
                clearFields();
            } else {
                notifyLabel.setText("Author registration failed. Please check username or password.");
            }
        } catch (SQLException e) {
            notifyLabel.setText("Author registration failed.");
        }
    }

    private void clearFields() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailTextField.clear();
        usernameTextField.clear();
        passwordTextField.clear();
        confirmPasswordTextField.clear();
    }

    public void enableRegisterButton() {
        registerAuthorButton.setDisable(!checkbox.isSelected());
        registerStartButton.setDisable(!checkbox.isSelected());
    }

    public void backToLogin() throws IOException {
        new sceneSwitch(registrationScene, "loginForm.fxml");
    }
}
