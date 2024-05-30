package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import org.transit.app.newspaperapp.Main;

import java.io.IOException;
import java.util.Objects;

public class accountController {

    @FXML
    public Button savedArticlesBtn;

    @FXML
    public Button accountSettingsBtn;

    @FXML
    private BorderPane accountContainerBorderPane;

//    @FXML
//    public void initialize() {
//        savedArticlesBtn.setOnAction(this::savedArticlesBtnAction);
////        accountSettingsBtn.setOnAction(this::accountSettingsBtnAction);
//    }

//    public void savedArticlesBtnAction(ActionEvent event) {
//        loadSavedArticlesScene(event);
//    }
//
////    public void accountSettingsBtnAction(ActionEvent event) {
////        loadAccountSettingsScene(event);
////    }
//
//    private void loadSavedArticlesScene(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("savedArticle.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            System.out.println("Articles Saved!");
//        }
//    }

    public boolean loadAccountSettingsScene() {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Mainpage/Account/accountSettings.fxml")));
            accountContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


//    private void loadAccountSettingsScene(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("accountSettings.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//        } catch (IOException e) {
//            System.out.println("Account Settings");
//        }
//    }
}
