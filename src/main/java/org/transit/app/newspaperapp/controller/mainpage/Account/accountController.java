package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import org.transit.app.newspaperapp.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class accountController implements Initializable {

    @FXML
    private BorderPane accountContainerBorderPane;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label roleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User currentUser = UserSession.getCurrentUser();

        usernameLabel.setText(currentUser.username());
        nameLabel.setText(currentUser.name());
        emailLabel.setText(currentUser.email());
        roleLabel.setText(currentUser.role());
    }

    @FXML
    public void savedArticlesBtnAction(ActionEvent event) {
        loadSavedArticlesScene(event);
    }

    @FXML
    public void accountSettingsBtnAction(ActionEvent event) {
        loadAccountSettingsScene(event);
    }

    private void loadSavedArticlesScene(ActionEvent event) {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Mainpage/Account/savedArticles.fxml")));
            accountContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAccountSettingsScene(ActionEvent event) {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Mainpage/Account/accountSettings.fxml")));
            accountContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public record User(String username, String name, String email, String role) {
    }

    public static class UserSession {
        public static User getCurrentUser() {
            return new User("Username", "Name", "test@gmail.com", "User");
        }
    }
}
