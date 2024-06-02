package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.User;
import org.transit.app.newspaperapp.model.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

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
        setUsernameLabel();
        setNameLabel();
        setEmailLabel();
        determineRoleSession();
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

    public void setUsernameLabel() {
        usernameLabel.setText("@" + UserSession.getInstance().getLoggedInUser().getUsername());
    }

    public void setNameLabel() {
        nameLabel.setText(UserSession.getInstance().getLoggedInUser().getFirstName() + " " + UserSession.getInstance().getLoggedInUser().getLastName());
    }

    public void setEmailLabel() {
        emailLabel.setText(UserSession.getInstance().getLoggedInUser().getEmail());
    }

    public void determineRoleSession() {
        UserSession session = UserSession.getInstance();
        Set<Integer> roles = session.getRoles();

        int roleIdToCheck = 1;

        if (!roles.contains(roleIdToCheck)) {
            roleLabel.setText("READER");
        } else {
            roleLabel.setText("AUTHOR");
        }
    }
}
