package org.transit.app.busmonitoringapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import org.transit.app.busmonitoringapp.Main;
import org.transit.app.busmonitoringapp.model.Login;
import org.transit.app.busmonitoringapp.logic.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    @FXML
    private Label notifyLabel;
    @FXML
    private Button closeAppButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;


    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        Login userData = new Login(usernameTextField.getText(), passwordTextField.getText());
        login loginLogic = new login();

        if (loginLogic.loginQuery(userData)) {
            notifyLabel.setText("Successfully Login!");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } else {
            notifyLabel.setText("Incorrect username or password");
        }
    }


    public void closeApplication(ActionEvent e) {
        Stage stage  = (Stage) closeAppButton.getScene().getWindow();
        stage.close();
    }
}