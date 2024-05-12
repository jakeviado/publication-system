package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.transit.app.newspaperapp.Main;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.model.Login;
import org.transit.app.newspaperapp.logic.transactions;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.Objects;

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


    // Hindi pa sure kung nagana talaga
    @FXML
    public void login() throws IOException {
        auth(true, true);
    }

    public void auth(boolean isAuthor, boolean isReader) throws IOException {
        Login userData = new Login(usernameTextField.getText(), passwordTextField.getText());
        transactions transact = new transactions();

        try {
            if (transact.loginQuery(userData, isAuthor, isReader)) {
                sceneSwitch(loginScene, "mainpage.fxml");
            } else {
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