package org.transit.app.busmonitoringapp.controller;

import javafx.scene.layout.VBox;
import org.transit.app.busmonitoringapp.model.sceneSwitch;

import java.io.IOException;

public class publishingPage {

    public VBox pageVbox;

    public void back() throws IOException {
        new sceneSwitch(pageVbox, "homeREADER.fxml");
    }
}

