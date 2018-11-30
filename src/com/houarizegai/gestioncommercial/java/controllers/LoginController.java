package com.houarizegai.gestioncommercial.java.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML // Parent node of all child (root node)
    private VBox root;

    @FXML
    private JFXTextField fieldUser;
    @FXML
    private JFXPasswordField fieldPass;

    private JFXSnackbar toastMsg; // For showing msg (like toast in Android)

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);
    }

    @FXML
    private void onLogin() {
        if(fieldUser.getText() == null || !fieldUser.getText().trim().toLowerCase().matches("[a-z0-9_]{4,}")) {
            toastMsg.show("Nom utilisateur faux !", 2000);
            return;
        }
        if(fieldPass.getText() == null || fieldPass.getText().length() < 4) {
            toastMsg.show("Mot de passe faux !", 2000);
            return;
        }

    }
}
