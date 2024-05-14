package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.services.UserTr;
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


    private void register(boolean isAuthor, boolean isReader, String success, String fail) {
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
        UserTr transact = new UserTr();

        try {
            if (transact.registerQuery(userData, isAuthor, isReader)) {
                notifyLabel.setText(success);
                clearTextsFields.clearFields(usernameTextField, passwordTextField, emailTextField, firstNameTextField, lastNameTextField, passwordTextField);
            } else {
                notifyLabel.setText(fail);
            }
        } catch (SQLException e) {
            notifyLabel.setText(fail);
        }
    }

    @FXML
    public void registerAsReader() {
        try {
            validateInput();
            register(false, true, "Registered successfully!", "Registration failed.");
        } catch (IllegalArgumentException e) {
            notifyLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void registerAsAuthor() {
        try {
            validateInput();
            register(true, false, "Registered as author successfully!", "Author registration failed. Please check username or password.");
        } catch (IllegalArgumentException e) {
            notifyLabel.setText(e.getMessage());
        }
    }

    private void validateInput() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
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

    @FXML
    public void enableRegisterButton() {
        registerAuthorButton.setDisable(!checkbox.isSelected());
        registerStartButton.setDisable(!checkbox.isSelected());
    }

    public void backToLogin() throws IOException {
        SceneSwitch(registrationScene, "loginForm.fxml");
    }

    public void SceneSwitch(VBox loginScene, String fxml) throws IOException {
        VBox nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        loginScene.getChildren().removeAll();
        loginScene.getChildren().setAll(nextVbox);
    }

}
