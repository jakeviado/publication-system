package org.transit.app.newspaperapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.transit.app.newspaperapp.model.Categories;
import org.transit.app.newspaperapp.services.ArticleTr;
import org.transit.app.newspaperapp.model.Articles;

import java.time.LocalDateTime;

public class publishingPage {

    @FXML
    public TextField headlineTextfield;

    @FXML
    public TextField bylineTextfield;

    @FXML
    public TextArea contentTextArea;

    @FXML
    public TextField imageLinkTextfield;

    @FXML
    public Label notifyLabel;

    @FXML
    public ComboBox<String> categoryComboBox;

    public void initialize() {
        categoriesList();
    }

    public void categoriesList() {
        ObservableList<String> categories = FXCollections.observableArrayList(
                Categories.NEWS, Categories.SPORTS, Categories.ENTERTAINMENT,
                Categories.OPINION, Categories.BUSINESS, Categories.TECHNOLOGY);
        categoryComboBox.setItems(categories);
    }

    @FXML
    public void getWrittenArticle() {
        String headline = headlineTextfield.getText();
        String byline =  bylineTextfield.getText();
        String content =  contentTextArea.getText();
        String category = categoryComboBox.getValue();
        LocalDateTime publicationDate = LocalDateTime.now();
        String imageLink = imageLinkTextfield.getText();

        Articles articles = new Articles(headline, byline, content, category, publicationDate, imageLink);
        ArticleTr publish = new ArticleTr();

        try {
            validateInput();

            publish.publishArticleQuery(articles);
            registrationController.clearTextsFields.clearFields(headlineTextfield, bylineTextfield, imageLinkTextfield);
            registrationController.clearTextsFields.clearFields(contentTextArea);
            notifyLabel.setText("Published!!");
        } catch (IllegalArgumentException e) {
            notifyLabel.setText(e.getMessage());
        }
    }

    private void validateInput() {
        String headline = headlineTextfield.getText();
        String byline =  bylineTextfield.getText();
        String content =  contentTextArea.getText();
        String category = categoryComboBox.getValue();
        String imageLink = imageLinkTextfield.getText();

        if (headline.isEmpty() || byline.isEmpty() || content.isEmpty() || category.isEmpty() || imageLink.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
    }
}

