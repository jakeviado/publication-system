package org.transit.app.newspaperapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class publishingPage {
    @FXML
    public VBox pageVbox;
    @FXML
    public ComboBox categoryComboBox;

    public TextField headlineTextfield;
    public TextField bylineTextfield;
    public TextArea contentTextArea;
}

