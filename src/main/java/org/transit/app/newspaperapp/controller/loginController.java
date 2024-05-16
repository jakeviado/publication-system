package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.transit.app.newspaperapp.Main;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.model.User;
import org.transit.app.newspaperapp.services.UserTr;
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
    private Button closeAppButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private VBox loginScene;

    public void login() throws IOException {
        User userData = new User(usernameTextField.getText(), passwordTextField.getText());
        UserTr transact = new UserTr();

        try {
            if (transact.loginQuery(userData)) {
                //hindi ko pa alam ito
                User user  = new User();
                user.setUsername(user.username());

                sceneSwitch("mainpage.fxml");
            } else {
                notifyLabel.setText("Incorrect username or password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void signup() throws IOException {
        sceneSwitch("registrationForm.fxml");
    }

    public void closeApplication() {
        Stage stage  = (Stage) closeAppButton.getScene().getWindow();
        stage.close();
    }

    public void sceneSwitch(String fxml) throws IOException {
        VBox nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        loginScene.getChildren().setAll(nextVbox);
    }
}