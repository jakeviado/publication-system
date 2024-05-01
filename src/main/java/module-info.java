module org.transit.app.busmonitoringapp {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires com.google.gson;

    opens org.transit.app.busmonitoringapp to javafx.fxml;
    exports org.transit.app.busmonitoringapp;
    exports org.transit.app.busmonitoringapp.controller;
    opens org.transit.app.busmonitoringapp.controller to javafx.fxml;
    exports org.transit.app.busmonitoringapp.model;
    opens org.transit.app.busmonitoringapp.model to javafx.fxml;
}