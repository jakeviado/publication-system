package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.logic.transactions;
import org.transit.app.newspaperapp.model.Signup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

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
                clearTextsFields.clearFields(usernameTextField, passwordTextField, emailTextField, firstNameTextField, lastNameTextField, passwordTextField);
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
                clearTextsFields.clearFields(usernameTextField, passwordTextField, emailTextField, firstNameTextField, lastNameTextField, passwordTextField);
            } else {
                notifyLabel.setText("Author registration failed. Please check username or password.");
            }
        } catch (SQLException e) {
            notifyLabel.setText("Author registration failed.");
        }
    }

    public static class clearTextsFields {
        public static void clearFields(TextField... textFields) {
            for (TextField textField : textFields) {
                textField.clear();
            }
        }
        public static void clearFields(TextArea... textAreas) {
            for (TextArea textArea : textAreas) {
                textArea.clear();
            }
        }
    }

    public void enableRegisterButton() {
        registerAuthorButton.setDisable(!checkbox.isSelected());
        registerStartButton.setDisable(!checkbox.isSelected());
    }

    public void backToLogin() throws IOException {
        sceneSwitch(registrationScene, "loginForm.fxml");
    }

    public void sceneSwitch(VBox loginScene, String fxml) throws IOException {
        VBox nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        loginScene.getChildren().removeAll();
        loginScene.getChildren().setAll(nextVbox);
    }

}
