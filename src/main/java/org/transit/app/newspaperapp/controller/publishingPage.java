package org.transit.app.newspaperapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.transit.app.newspaperapp.model.Articles;
import org.transit.app.newspaperapp.logic.transactions;

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

    //TODO: pinagiisipan ko pa
    public void categoriesList() {
        ObservableList<String> categories = FXCollections.observableArrayList (
                "News", "Sports", "Entertainment", "Opinion", "Business", "Technology");
        categoryComboBox.setItems(categories);
    }

    public void getWrittenArticle() {
        String headline =   headlineTextfield.getText();
        String byline =  bylineTextfield.getText();
        String content =  contentTextArea.getText();
        String category = categoryComboBox.getValue();
        LocalDateTime publicationDate = LocalDateTime.now();
        String imageLink = imageLinkTextfield.getText();

        Articles articles = new Articles(headline, byline, content, category, publicationDate, imageLink);
        transactions transact = new transactions();

        try {
            transact.publishArticleQuery(articles);
            registrationController.clearTextsFields.clearFields(headlineTextfield, bylineTextfield, imageLinkTextfield);
            registrationController.clearTextsFields.clearFields(contentTextArea);
            notifyLabel.setText("Published!!");
        } catch (Exception e) {
            notifyLabel.setText("Publish Failed");
        }
    }
}

