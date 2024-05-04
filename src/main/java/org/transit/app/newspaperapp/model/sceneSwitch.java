package org.transit.app.newspaperapp.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import org.transit.app.newspaperapp.Main;

import java.io.IOException;
import java.util.Objects;

public class  sceneSwitch {
    public sceneSwitch(VBox loginScene, String fxml) throws IOException {
        VBox nextVbox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        loginScene.getChildren().removeAll();
        loginScene.getChildren().setAll(nextVbox);
    }
}