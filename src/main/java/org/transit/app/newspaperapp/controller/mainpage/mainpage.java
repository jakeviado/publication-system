package org.transit.app.newspaperapp.controller.mainpage;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.transit.app.newspaperapp.Main;
import org.transit.app.newspaperapp.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainpage implements Initializable {
    @FXML
    public VBox homepageScene;

    @FXML
    public VBox slidePanel;

    @FXML
    public ToggleButton toggleButton;

    @FXML
    public BorderPane MainBorderPanel;

    @FXML
    public Button logoutButton;

    @FXML
    public Button writeArticleButton;

    @FXML
    public BorderPane articleContainerBorderPane;

    @FXML
    public Button homeBtn;

    @FXML
    public Button categoriesBtn;

    @FXML
    public Button aboutBtn;

    @FXML
    public Button accountBtn;

    @FXML
    public Label notifyLabel;

    @FXML
    public Label welcomeUserLbl;

    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loadNewsFeed()) {
            notifyLabel.setText("~ Today's Front Page ~");
        }
        setWelcomeUserLbl();
        roleAuth();
        toggleMenu();
    }

    public boolean loadNewsFeed() {
        try {
            BorderPane about = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Mainpage/Newsfeed/newsfeed.fxml")));
            articleContainerBorderPane.setCenter(about);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void setWelcomeUserLbl() {
        welcomeUserLbl.setText("Welcome " + "@" + User.getLoggedInUser().getUsername() + "!");

//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.seconds(10),
//                event -> welcomeUserLbl.setVisible(false)
//        ));
//        timeline.setCycleCount(1);
//        timeline.play();
    }


    public void toggleMenu() {
        toggleButton.setOnAction(event -> {
            boolean isSelected = toggleButton.isSelected();

            double targetX = isSelected ? -240 : 0;
            double targetWidth = isSelected ? 0 : 240;
            String buttonText = isSelected ? "MORE" : "CLOSE";

            Timeline timeline = new Timeline();

            KeyValue translateX = new KeyValue(slidePanel.translateXProperty(), targetX, Interpolator.EASE_OUT);
            KeyValue resizeWidth = new KeyValue(slidePanel.prefWidthProperty(), targetWidth, Interpolator.EASE_OUT);

            KeyValue slidePanelWidth = new KeyValue(slidePanel.minWidthProperty(), targetWidth, Interpolator.EASE_OUT);
            KeyValue slidePanelMaxWidth = new KeyValue(slidePanel.maxWidthProperty(), targetWidth, Interpolator.EASE_OUT);

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), translateX, resizeWidth, slidePanelWidth, slidePanelMaxWidth);

            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

            toggleButton.setText(buttonText);
        });
    }

    public void publishArticlePage() throws IOException {
        switchScene("views/Mainpage/PublishArticle/publishingPage.fxml");
        notifyLabel.setText("Author Exclusive");
    }

    public void setHomeBtn() throws IOException {
        switchScene("views/Mainpage/Newsfeed/newsfeed.fxml");
        notifyLabel.setText("~ Today's Front Page ~");
    }

    public void setCategoriesBtn() throws IOException {
        switchScene("views/Mainpage/Categories/categoriesPage.fxml");
        notifyLabel.setText("~ Categories ~");
    }

    public void setAboutBtn() throws IOException {
        switchScene("views/Mainpage/About/about.fxml");
        notifyLabel.setText("~ About Us ~");
    }

    public void setAccountBtn() throws IOException {
        switchScene("views/Mainpage/Account/account.fxml");
        notifyLabel.setText("~ Account ~");
    }

    public void exitApp() throws IOException {
        exitSession(User.getLoggedInUser());
    }

    public void exitSession(User user) throws IOException {
        Stage stage  = (Stage) exitButton.getScene().getWindow();
        stage.close();
        user.setUsername(null);
        user.setPassword(null);
    }

    public void setLogoutButton() throws IOException {
        logoutSession(User.getLoggedInUser());
    }

    public void logoutSession(User user) throws IOException {
        logoutScene("views/LoginPage/loginForm.fxml");
        user.setUsername(null);
        user.setPassword(null);
    }

    public void roleAuth() {
        writeArticleButton.setDisable(false);
    }

    private void switchScene(String fxmlFile) throws IOException {
        BorderPane nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFile)));
        articleContainerBorderPane.setCenter(nextVbox);
    }

    public void logoutScene(String fxml) throws IOException {
//        VBox nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
//        homepageScene.getChildren().removeAll();
//        homepageScene.getChildren().setAll(nextVbox);

        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        Stage stage = (Stage) homepageScene.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}
