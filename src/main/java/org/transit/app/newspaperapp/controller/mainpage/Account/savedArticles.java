package org.transit.app.newspaperapp.controller.mainpage.Account;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class savedArticles {

    @FXML
    private ListView<String> savedArticlesListView;

    public void initialize() {      // initialize controller
        loadSavedArticles();        // load from db
    }

    private void loadSavedArticles() {      // nakuha ng saved articles sa db
        savedArticlesListView.getItems().addAll(
                "Article 1", "Article 2", "Article 3"
        );
    }
}
