package org.transit.app.busmonitoringapp.controller;

import javafx.scene.control.*;
import org.transit.app.busmonitoringapp.model.sceneSwitch;
import javafx.scene.layout.VBox;
import org.transit.app.busmonitoringapp.model.Login;
import org.transit.app.busmonitoringapp.logic.transactions;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    public Button signupButton;

    @FXML
    private Label notifyLabel;

    @FXML
    private Button closeAppButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private VBox loginScene;

    @FXML
    public void login() throws IOException {
        Login userData = new Login(usernameTextField.getText(), passwordTextField.getText());
        transactions retrieveUserInfoLogic = new transactions();

        try {
            if (retrieveUserInfoLogic.loginQuery(userData)) {
                new sceneSwitch(loginScene, "homeREADER.fxml");
            } else {
                notifyLabel.setText("Incorrect username or password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void signup() throws IOException {
        new sceneSwitch(loginScene, "registrationForm.fxml");
    }

    public void closeApplication() {
        Stage stage  = (Stage) closeAppButton.getScene().getWindow();
        stage.close();
    }
}