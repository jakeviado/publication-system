package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.transit.app.newspaperapp.Main;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.model.Signup;
import org.transit.app.newspaperapp.model.User;
import org.transit.app.newspaperapp.model.UserSession;
import org.transit.app.newspaperapp.services.UserTr;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

public class loginController {
    public Button signupButton;

    @FXML
    public Button showUser;

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

    // ensure that the user is properly logged in not just switching scenes.
    public void login() throws IOException {
        User userData = new User(usernameTextField.getText(), passwordTextField.getText());
        UserTr transact = new UserTr();

        try {
            Signup dbUser = transact.authenticate(userData.getUsername(), userData.getPassword());
            if (dbUser != null) {
                UserSession session = UserSession.getInstance();
                session.setLoggedInUser(new User(dbUser.getUsername(), dbUser.getPassword(), dbUser.getEmail(),
                        dbUser.getFirstName(), dbUser.getLastName()));

                Set<Integer> userRoles = transact.getUserRoles(dbUser.getUserId());
                session.setRoles(userRoles);

                int userId = dbUser.getUserId();
                session.setUserId(userId);

                System.out.println("User ID: " + userId);
                System.out.println("User Roles: " + userRoles);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, " + UserSession.getInstance().getLoggedInUser().getUsername() + "!");
                alert.show();

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        Stage stage = (Stage) loginScene.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}