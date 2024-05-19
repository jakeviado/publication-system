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

//    public void login() throws IOException {
//        User userData = new User(usernameTextField.getText(), passwordTextField.getText());
//        UserTr transact = new UserTr();
//
//        try {
//            if (transact.loginQuery(userData)) {
//                //hindi ko pa alam ito
//                User user = new User();
//                user.setUsername(user.username());
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Login Successful");
//                alert.setHeaderText(null);
//                alert.setContentText("Welcome, " + User.getUsername() + "!");
//                alert.showAndWait();
//
//                sceneSwitch("mainpage.fxml");
//            } else {
//                notifyLabel.setText("Incorrect username or password");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    // ensure that the user is properly logged in not just switching scenes.
    public void login() throws IOException {
        User userData = new User(usernameTextField.getText(), passwordTextField.getText());
        UserTr transact = new UserTr();

        try {
            if (transact.loginQuery(userData)) {
                User.setLoggedInUser(userData);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, " + User.getLoggedInUser().getUsername() + "!");
                alert.showAndWait();

                sceneSwitch("views/Mainpage/mainpage.fxml");
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password, Try Again");
                alert.showAndWait();
                notifyLabel.setText("Incorrect username or password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void registerPage() throws IOException {
        sceneSwitch("views/RegistrationForm/registrationForm.fxml");
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