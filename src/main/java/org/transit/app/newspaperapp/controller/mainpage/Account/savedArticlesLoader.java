package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import org.transit.app.newspaperapp.model.Articles;

public class savedArticlesLoader {

    @FXML
    private Label ctgryLbl;
    @FXML
    private Hyperlink headlineLabel;
    @FXML
    private Label bylineLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label contentLabel;

    public void setArticleData(String headline, String byline, String content, String publishedDate, String category, String authorName) {
        ctgryLbl.setText(category);
        headlineLabel.setText(headline);
        bylineLabel.setText("/ " + byline);
        authorLabel.setText("Author: " + authorName);
        dateLabel.setText(publishedDate);
        contentLabel.setText(content);
    }
}
