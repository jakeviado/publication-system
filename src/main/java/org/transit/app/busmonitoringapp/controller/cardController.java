package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.VBox;
import org.transit.app.busmonitoringapp.model.apiArticles;

public class cardController {
    @FXML
    public Label articleAuthorLabel;
    @FXML
    public Label articleDescriptionLabel;
    @FXML
    public Label articlePublishedAtLabel;
    @FXML
    public Label articleTitleLabel;
    @FXML
    VBox container;

    public VBox getView(apiArticles apiArticles) {
        VBox container = new VBox();

        if (apiArticles != null) {
            articleTitleLabel = new Label(apiArticles.title() != null ? apiArticles.title() : "TEST");
            articleTitleLabel.setStyle("-fx-font-size: 20;");
            articleTitleLabel.setTextOverrun(OverrunStyle.CLIP);

            articleAuthorLabel = new Label(apiArticles.author() != null ? apiArticles.author() : "TEST");
            articleAuthorLabel.setTextOverrun(OverrunStyle.CLIP);

            articleDescriptionLabel = new Label(apiArticles.description() != null ? apiArticles.description() : "TEST");
            articleDescriptionLabel.setTextOverrun(OverrunStyle.CLIP);

            articlePublishedAtLabel = new Label(apiArticles.publishedAt() != null ? apiArticles.publishedAt() : "TEST");

        } else {
            articleTitleLabel = new Label("");
            articleAuthorLabel = new Label("");
            articleDescriptionLabel = new Label("");
            articlePublishedAtLabel = new Label("");

        }
        container.getChildren().addAll(articleTitleLabel, articleAuthorLabel, articleDescriptionLabel, articlePublishedAtLabel);
        return container;
    }
}
