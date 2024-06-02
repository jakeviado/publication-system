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

    public void setArticleData(Articles article) {
        ctgryLbl.setText(article.getCategory());
        headlineLabel.setText(article.getHeadline());
        bylineLabel.setText("/ " + article.getByline());
        authorLabel.setText("Author: " + article.getAuthor_name());
        dateLabel.setText(article.getPublicationDate());
        contentLabel.setText(article.getContent());
    }
}
