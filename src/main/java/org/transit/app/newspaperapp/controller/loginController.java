package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.transit.app.newspaperapp.Main;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.model.Login;
import org.transit.app.newspaperapp.logic.transactions;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class loginController {
    public Button signupButton;

    @FXML
    private Label notifyLabel;

    @FXML
    private static Button closeAppButton;

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
                sceneSwitch(loginScene, "mainpage.fxml");
            } else  {
                notifyLabel.setText("Incorrect username or password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void signup() throws IOException {
        sceneSwitch(loginScene, "registrationForm.fxml");
    }

    public void closeApplication() {
        Stage stage  = (Stage) closeAppButton.getScene().getWindow();
        stage.close();
    }

    public void sceneSwitch(VBox loginScene, String fxml) throws IOException {
        VBox nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        loginScene.getChildren().removeAll();
        loginScene.getChildren().setAll(nextVbox);
    }
}