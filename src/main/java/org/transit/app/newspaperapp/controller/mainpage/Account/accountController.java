package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import org.transit.app.newspaperapp.Main;

import java.io.IOException;
import java.util.Objects;

public class accountController {

    @FXML
    private BorderPane accountContainerBorderPane;

    @FXML
    public void savedArticlesBtnAction(ActionEvent event) {
        loadSavedArticlesScene(event);
    }

    public void accountSettingsBtnAction(ActionEvent event) {
        loadAccountSettingsScene(event);
    }

    public void loadSavedArticlesScene(ActionEvent event) {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Mainpage/Account/savedArticles.fxml")));
            accountContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAccountSettingsScene(ActionEvent event) {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Mainpage/Account/accountSettings.fxml")));
            accountContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
