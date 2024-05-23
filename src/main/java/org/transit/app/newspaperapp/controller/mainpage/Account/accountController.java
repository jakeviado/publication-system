package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.transit.app.newspaperapp.model.User;

public class accountController {

    @FXML
    public Button changePassBtn;

    @FXML
    public Label usernameLabel;

    @FXML
    public Label nameLabel;

    public void initialize() {
        setNameLabel();
        setUsernameLabel();
    }

    public void setNameLabel() {
        usernameLabel.setText(User.getLoggedInUser().getFirstName() + " " + User.getLoggedInUser().getLastName());
    }

    public void setUsernameLabel() {
        nameLabel.setText("@" + User.getLoggedInUser().getUsername());
    }
}
