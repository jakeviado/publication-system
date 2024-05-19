module org.transit.app.busmonitoringapp {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires com.google.gson;

    opens org.transit.app.newspaperapp to javafx.fxml;
    exports org.transit.app.newspaperapp;
    exports org.transit.app.newspaperapp.controller;
    opens org.transit.app.newspaperapp.controller to javafx.fxml;
    exports org.transit.app.newspaperapp.model;
    opens org.transit.app.newspaperapp.model to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage.categories.categoryComponents;
    opens org.transit.app.newspaperapp.controller.mainpage.categories.categoryComponents to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage.author;
    opens org.transit.app.newspaperapp.controller.mainpage.author to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage;
    opens org.transit.app.newspaperapp.controller.mainpage to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage.categories;
    opens org.transit.app.newspaperapp.controller.mainpage.categories to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage.newsfeed;
    opens org.transit.app.newspaperapp.controller.mainpage.newsfeed to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage.account;
    opens org.transit.app.newspaperapp.controller.mainpage.account to javafx.fxml;
}