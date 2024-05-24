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
    exports org.transit.app.newspaperapp.controller.categoryComponents;
    opens org.transit.app.newspaperapp.controller.categoryComponents to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage;
    opens org.transit.app.newspaperapp.controller.mainpage to javafx.fxml;
    exports org.transit.app.newspaperapp.controller.mainpage.Account;
    opens org.transit.app.newspaperapp.controller.mainpage.Account to javafx.fxml;
    exports org.transit.app.newspaperapp.services;
    opens org.transit.app.newspaperapp.services to javafx.fxml;
}