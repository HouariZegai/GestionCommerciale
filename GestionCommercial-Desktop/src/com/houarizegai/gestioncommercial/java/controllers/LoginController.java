package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.Launcher;
import com.houarizegai.gestioncommercial.java.database.dao.LoginDao;
import com.houarizegai.gestioncommercial.java.database.models.Login;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

        root.setOnKeyPressed(e -> { // Execute login action if i click Enter button from Keyboard
            if(e.getCode().equals(KeyCode.ENTER)) {
                onLogin();
            }
        });
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

        Login login = new Login(fieldUser.getText().trim().toLowerCase(), fieldPass.getText());

        int status = LoginDao.checkLogin(login);
        switch (status) {
            case -1 :
                toastMsg.show("Connection failed !", 2000);
                break;
            case 0 :
                toastMsg.show("Nom Utilisateur et/ou le mot de passe faux !", 2000);
                break;
            case 1 : // Login to the system (show system gui)
                Parent systemView = null;
                try {
                    systemView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/System.fxml"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                Stage stage = ((Stage) fieldUser.getScene().getWindow());
                stage.setScene(new Scene(Objects.requireNonNull(systemView)));
                Launcher.centerOnScreen(); // make stage in the center
                break;
        }

    }
}
