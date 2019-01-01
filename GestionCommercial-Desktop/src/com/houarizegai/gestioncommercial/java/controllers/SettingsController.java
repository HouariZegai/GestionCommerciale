package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.dao.LoginDao;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsController implements Initializable {

    @FXML
    private VBox changeUsernamePane, changePasswordPane, changeLanguagePane, changeThemePane;
    @FXML
    private HBox usernameOption, passwordOption, languageOption, themeOption;
    @FXML
    private JFXComboBox<String> comboTheme, comboLanguage;

    @FXML // This label showing in top of each settings
    private Label headerLabel, contentLabel;

    // Change username part
    @FXML
    private TextField newUsernameUserPart;
    @FXML
    private PasswordField currentPasswordUserPart;

    // Change password part
    @FXML
    private PasswordField currentPasswordPassPart, newPasswordPassPart, verifyPasswordPassPart;
    @FXML
    private FontAwesomeIconView iconValid;

    @FXML
    private HBox boxError;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTheme.getItems().addAll("Bleu", "Rouge", "Vert", "Noire");
        comboTheme.getSelectionModel().select(0);

        comboLanguage.getItems().addAll("Français", "English", "العربية");
        comboLanguage.getSelectionModel().select(0);

        addListenerOption();
        
        // This code below check the max value length of password field
        
        // max length of username
        newUsernameUserPart.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (newUsernameUserPart.getText().length() > 25) {
                newUsernameUserPart.setText(newUsernameUserPart.getText().substring(0, 25));
                newUsernameUserPart.positionCaret(newUsernameUserPart.getText().length());
            }
        });
        
        // max length for password
        currentPasswordUserPart.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (currentPasswordUserPart.getText().length() > 25) {
                currentPasswordUserPart.setText(currentPasswordUserPart.getText().substring(0, 25));
                currentPasswordUserPart.positionCaret(currentPasswordUserPart.getText().length());
            }
        });
        
        currentPasswordPassPart.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (currentPasswordPassPart.getText().length() > 50) {
                currentPasswordPassPart.setText(currentPasswordPassPart.getText().substring(0, 50));
                currentPasswordPassPart.positionCaret(currentPasswordPassPart.getText().length());
            }
        });
        
        newPasswordPassPart.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (newPasswordPassPart.getText().length() > 50) {
                newPasswordPassPart.setText(newPasswordPassPart.getText().substring(0, 50));
                newPasswordPassPart.positionCaret(newPasswordPassPart.getText().length());
            }
            showIconPassPart();
        });
        
        verifyPasswordPassPart.setOnKeyReleased(event -> { // this event for check max length of the answer area
            if (verifyPasswordPassPart.getText().length() > 50) {
                verifyPasswordPassPart.setText(verifyPasswordPassPart.getText().substring(0, 50));
                verifyPasswordPassPart.positionCaret(verifyPasswordPassPart.getText().length());
            }
            showIconPassPart();
        });
        
    }

    private void addListenerOption() {
        usernameOption.setOnMouseClicked(e -> {
            // Show the content of selected option
            changeUsernamePane.setVisible(true);
            changePasswordPane.setVisible(false);
            changeLanguagePane.setVisible(false);
            changeThemePane.setVisible(false);

            // Make label option selected bold and reset other option to normal
            usernameOption.getChildren().get(0).setStyle("-fx-font-weight: bold");
            passwordOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            languageOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            themeOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            btnCloseErrorMsg();
            changePasswordPane.setPrefHeight(0);

            // Change the text in top selected box
            headerLabel.setText("Nom utilisateur");
            contentLabel.setText("Changer votre nom d'utilisateur");
            
            // Remove value from field
            newUsernameUserPart.setText("");
            currentPasswordUserPart.setText("");
        });
        passwordOption.setOnMouseClicked(e -> {
            changeUsernamePane.setVisible(false);
            changePasswordPane.setVisible(true);
            changeLanguagePane.setVisible(false);
            changeThemePane.setVisible(false);
            usernameOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            passwordOption.getChildren().get(0).setStyle("-fx-font-weight: bold");
            languageOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            themeOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            btnCloseErrorMsg();
            changePasswordPane.setPrefHeight(252);

            // Change the text in top selected box
            headerLabel.setText("Mot de passe");
            contentLabel.setText("Changer votre mot de passe");
            
            // Remove value from field
            newPasswordPassPart.setText("");
            currentPasswordPassPart.setText("");
            verifyPasswordPassPart.setText("");
        });
        languageOption.setOnMouseClicked(e -> {
            changeUsernamePane.setVisible(false);
            changePasswordPane.setVisible(false);
            changeLanguagePane.setVisible(true);
            changeThemePane.setVisible(false);
            usernameOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            passwordOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            languageOption.getChildren().get(0).setStyle("-fx-font-weight: bold");
            themeOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            btnCloseErrorMsg();
            changePasswordPane.setPrefHeight(0);

            // Change the text in top selected box
            headerLabel.setText("Langage");
            contentLabel.setText("Changer le langage de ce logiciel");
            
            comboLanguage.getSelectionModel().select(0);
        });
        themeOption.setOnMouseClicked(e -> {
            changeUsernamePane.setVisible(false);
            changePasswordPane.setVisible(false);
            changeLanguagePane.setVisible(false);
            changeThemePane.setVisible(true);
            usernameOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            passwordOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            languageOption.getChildren().get(0).setStyle("-fx-font-weight: normal");
            themeOption.getChildren().get(0).setStyle("-fx-font-weight: bold");
            btnCloseErrorMsg();
            changePasswordPane.setPrefHeight(0);

            // Change the text in top selected box
            headerLabel.setText("Thème");
            contentLabel.setText("Changer le thème de ce logiciel");
            
            comboTheme.getSelectionModel().select(0);
        });

    }

    @FXML
    private void btnSave() {
        if (changeUsernamePane.isVisible()) {
            saveUsername();
        }else if (changePasswordPane.isVisible()) {
            savePassword();
        } else if (changeLanguagePane.isVisible()) {
            saveLanguage();
        } else if (changeThemePane.isVisible()) {
            saveTheme();
        }
    }

    private void saveUsername() {
        if (newUsernameUserPart.getText() == null || !newUsernameUserPart.getText().trim().toLowerCase().matches("[a-z0-9_-]{4,}")) {
            showErrorMsg("Nouvelle nom utilisateur error !");
            return;
        }
        if (currentPasswordUserPart.getText().length() < 4) {
            showErrorMsg("Mot de passe petite !");
            return;
        }

        // Change Username
        int status = LoginDao.setUsername(newUsernameUserPart.getText(), currentPasswordUserPart.getText());

        switch (status) {
            case -3:
                showErrorMsg("C'est le nom utilisateur actuel !");
                newUsernameUserPart.setText("");
                currentPasswordUserPart.setText("");
                break;
            case -2:
                showErrorMsg("Nom utilisateur déja existe !");
                break;
            case -1:
                showErrorMsg("Erreur de connaction !");
                break;
            case 0:
                showErrorMsg("Mot de passe faux !");
                break;
            case 1:
                showErrorMsg("Vous avez change le nom utilisateur !");
                newUsernameUserPart.setText("");
                currentPasswordUserPart.setText("");
                break;
        }
    }

    private void savePassword() {
        if (currentPasswordPassPart.getText().length() < 4) {
            showErrorMsg("Mot de passe actuel petite !");
            return;
        }
        if (newPasswordPassPart.getText().length() < 4) {
            showErrorMsg("Nouveau mot de passe petite !");
            return;
        }

        if (verifyPasswordPassPart.getText().length() < 4 || !newPasswordPassPart.getText().equals(verifyPasswordPassPart.getText())) {
            showErrorMsg("Retaper mot de passe faux !");
            return;
        }

        // Change Username
        int status = LoginDao.setPassword(currentPasswordPassPart.getText(), newPasswordPassPart.getText());

        switch (status) {
            case -1:
                showErrorMsg("Erreur connection !");
                break;
            case 0:
                showErrorMsg("Mot de passe faux !");
                break;
            case 1: {
                showErrorMsg("Vous avez change le mot de passe !");
                currentPasswordPassPart.setText("");
                newPasswordPassPart.setText("");
                verifyPasswordPassPart.setText("");
                iconValid.setVisible(false);
                break;
            }
        }
    }

    private void saveLanguage() {
        showErrorMsg("Coming soon !");
    }

    private void saveTheme() {
        showErrorMsg("Coming soon !");
    }
    
    private void showIconPassPart() {
        // This method show / hide password
        if (newPasswordPassPart.getText().isEmpty() || verifyPasswordPassPart.getText().isEmpty()) {
            iconValid.setVisible(false);
            return;
        }
        if (newPasswordPassPart.getText().equals(verifyPasswordPassPart.getText())) {
            iconValid.setVisible(true);
        } else {
            iconValid.setVisible(false);
        }
    }

    private void showErrorMsg(String msg) {
        errorLabel.setText(msg);
        boxError.setPrefHeight(48);
        boxError.setVisible(true);
    }

    @FXML
    private void btnCloseErrorMsg() {
        boxError.setPrefHeight(0);
        boxError.setVisible(false);
    }

    @FXML
    private void onClose() {
        SystemController.dialogSettings.close();
    }

}
