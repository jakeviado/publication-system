package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.VBox;
import org.transit.app.busmonitoringapp.model.ArticlesDetails;

public class CardController {
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

    public VBox getView(ArticlesDetails articlesDetails) {
        VBox container = new VBox();

        if (articlesDetails != null) {
            articleTitleLabel = new Label(articlesDetails.title() != null ? articlesDetails.title() : "TEST");
            articleTitleLabel.setStyle("-fx-font-size: 20;");
            articleTitleLabel.setTextOverrun(OverrunStyle.CLIP);

            articleAuthorLabel = new Label(articlesDetails.author() != null ? articlesDetails.author() : "TEST");
            articleAuthorLabel.setTextOverrun(OverrunStyle.CLIP);

            articleDescriptionLabel = new Label(articlesDetails.description() != null ? articlesDetails.description() : "TEST");
            articleDescriptionLabel.setTextOverrun(OverrunStyle.CLIP);

            articlePublishedAtLabel = new Label(articlesDetails.publishedAt() != null ? articlesDetails.publishedAt() : "TEST");

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
