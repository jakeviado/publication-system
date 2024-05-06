module org.transit.app.busmonitoringapp {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires com.google.gson;
    requires java.desktop;

    opens org.transit.app.newspaperapp to javafx.fxml;
    exports org.transit.app.newspaperapp;
    exports org.transit.app.newspaperapp.controller;
    opens org.transit.app.newspaperapp.controller to javafx.fxml;
    exports org.transit.app.newspaperapp.model;
    opens org.transit.app.newspaperapp.model to javafx.fxml;
}