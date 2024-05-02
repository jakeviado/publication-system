package org.transit.app.busmonitoringapp.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.transit.app.busmonitoringapp.model.ArticlesDetails;
import org.transit.app.busmonitoringapp.model.sceneSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.transit.app.busmonitoringapp.utilities.APIConnection.fetchArticles;



public class readerPageController implements Initializable {
    @FXML
    public VBox homepageScene;

    @FXML
    public VBox slidePanel;

    @FXML
    public Label welcomeNameLabel;

    @FXML
    public ToggleButton toggleButton;

    @FXML
    public BorderPane MainBorderPanel;

    @FXML
    public Button logoutButton;

    @FXML
    public Label articleDescription;

    @FXML
    public GridPane articleGrid;

    @FXML
    public VBox object;

    @FXML
    private Button exitButton;

    @FXML
    private List<ArticlesDetails> fetchedArticles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchedArticles = new ArrayList<>(latestNews());

        for (ArticlesDetails articles : fetchedArticles) {
            cardController c = new cardController();
            VBox articleCard = c.getView(articles);
            articleGrid.add(articleCard, 2, articleGrid.getRowCount());
            GridPane.setMargin(articleCard, new Insets(50));
        }
    }

    private List<ArticlesDetails> latestNews() {
        List<ArticlesDetails> al = new ArrayList<>();

        JsonObject articlesData = (JsonObject) fetchArticles();

        assert articlesData != null;
        if (articlesData.has("articles") && articlesData.get("articles").isJsonArray()) {
            JsonArray articlesJsonArray = articlesData.getAsJsonArray("articles");

            for (int i = 0; i < articlesJsonArray.size(); i++) {
                JsonObject articleJsonObject = articlesJsonArray.get(i).getAsJsonObject();

                String title = articleJsonObject.get("title").getAsString();
                String author = articleJsonObject.get("author").isJsonNull() ? "" : articleJsonObject.get("author").getAsString();
                String description = articleJsonObject.get("description").isJsonNull() ? "" : articleJsonObject.get("description").getAsString();
                String publishedAt = articleJsonObject.get("publishedAt").isJsonNull() ? "" : articleJsonObject.get("publishedAt").getAsString();

                ArticlesDetails currentArticle = new ArticlesDetails(title, description, author, publishedAt);

                al.add(currentArticle);
            }
        } else {
            articleDescription.setText("Error: Invalid data format");
        }
        return al;
    }

    public void toggle() {
        toggleButton.setOnAction(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(slidePanel);

            double targetX;
            if (toggleButton.isSelected()) {
                targetX = -200;
                toggleButton.setText("MORE");
            } else {
                targetX = 200;
                toggleButton.setText("CLOSE");
            }
            slide.setToX(targetX);
            slide.play();
        });
    }

    public void exitApp() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void setLogoutButton() throws IOException {
        new sceneSwitch(homepageScene, "loginPage.fxml");
    }
}
