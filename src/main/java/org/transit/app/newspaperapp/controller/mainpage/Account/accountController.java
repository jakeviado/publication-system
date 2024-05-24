package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class accountController {

    @FXML
    public Button savedArticlesBtn;

    @FXML
    public Button accountSettingsBtn;

    @FXML
    public void initialize() {
        savedArticlesBtn.setOnAction(this::savedArticlesBtnAction);
        accountSettingsBtn.setOnAction(this::accountSettingsBtnAction);
    }

    public void savedArticlesBtnAction(ActionEvent event) {
        System.out.println("Articles Saved!");
    }

    public void accountSettingsBtnAction(ActionEvent event) {
        System.out.println("Account Settings");
    }
}
