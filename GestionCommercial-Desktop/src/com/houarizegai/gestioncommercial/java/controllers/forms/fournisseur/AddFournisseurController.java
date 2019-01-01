package com.houarizegai.gestioncommercial.java.controllers.forms.fournisseur;

import com.houarizegai.gestioncommercial.java.controllers.FournisseurController;
import com.houarizegai.gestioncommercial.java.database.dao.FournisseurDao;
import com.houarizegai.gestioncommercial.java.database.dao.MainDao;
import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.FournisseurBuilder;
import com.houarizegai.gestioncommercial.java.utils.regex.ClientRegex;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFournisseurController implements Initializable {

    @FXML // Parent of all fournisseur (root node)
    private VBox root;

    /* FournisseurRegex infos */
    @FXML
    private JFXTextField fieldNumero, fieldSociete, fieldNom, fieldPrenom, fieldTelephone, fieldMobile, fieldFax,
            fieldEmail, fieldAdresse, fieldCodePostal, fieldVille, fieldPays;
    @FXML
    private JFXComboBox<String> comboCivilite;
    @FXML // Error icons
    private FontAwesomeIconView iconSociete, iconNom, iconPrenom, iconTelephone, iconMobile, iconFax,
            iconEmail, iconAdresse, iconCodePostal, iconVille, iconPays;

    @FXML
    private JFXTextArea areaObservations;

    // For show error msg (like: Toast in android)
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize combo Civilite
        comboCivilite.getItems().addAll("Mr", "Mme", "Melle");

        toastMsg = new JFXSnackbar(root);
        initFieldListener();

        // Initialize Numero Fournisseur (get auto increment from db)
        int currentAutoIncrement = MainDao.getCurrentAutoIncrement("Fournisseur");
        fieldNumero.setText(String.valueOf(currentAutoIncrement));
    }

    private void initFieldListener() {
        fieldSociete.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldSociete, iconSociete, newValue, ClientRegex.SOCIETE));
        fieldNom.textProperty().addListener((observable, oldValue, newValue) -> setValidFontRequired(fieldNom, iconNom, newValue, ClientRegex.NOM));
        fieldPrenom.textProperty().addListener((observable, oldValue, newValue) -> setValidFontRequired(fieldPrenom, iconPrenom, newValue, ClientRegex.PRENOM));
        fieldTelephone.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldTelephone, iconTelephone, newValue, ClientRegex.TELEPHONE));
        fieldMobile.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldMobile, iconMobile, newValue, ClientRegex.MOBILE));
        fieldFax.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldFax, iconFax, newValue, ClientRegex.FAX));
        fieldEmail.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldEmail, iconEmail, newValue, ClientRegex.EMAIL));
        fieldAdresse.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldAdresse, iconAdresse, newValue, ClientRegex.ADRESSE));
        fieldCodePostal.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldCodePostal, iconCodePostal, newValue, ClientRegex.CODE_POSTAL));
        fieldVille.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldVille, iconVille, newValue, ClientRegex.VILLE));
        fieldPays.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldPays, iconPays, newValue, ClientRegex.PAYS));
    }

    private void setValidFont(JFXTextField field, FontAwesomeIconView errorIcon, String newValue, String regex) { // Change the font if not valid or reset color
        if(newValue != null && !newValue.isEmpty() && !newValue.trim().matches(regex)) {
            field.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            errorIcon.setVisible(true);
        } else {
            field.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
            errorIcon.setVisible(false);
        }
    }

    private void setValidFontRequired(JFXTextField field, FontAwesomeIconView errorIcon, String newValue, String regex) {
        if(newValue == null || !newValue.trim().matches(regex)) {
            field.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            errorIcon.setVisible(true);
        } else {
            field.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
            errorIcon.setVisible(false);
        }
    }

    @FXML
    private void onAdd() { // Add new fournisseur
        // Validate
        if(fieldNom.getText() == null || fieldNom.getText().trim().isEmpty()) {
            fieldNom.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            iconNom.setVisible(true);
        }
        if(fieldPrenom.getText() == null || fieldPrenom.getText().trim().isEmpty()) {
            fieldPrenom.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            iconPrenom.setVisible(true);
        }

        if(iconSociete.isVisible() || iconNom.isVisible() || iconPrenom.isVisible()
                || iconTelephone.isVisible() || iconMobile.isVisible() || iconFax.isVisible() || iconEmail.isVisible()
                || iconAdresse.isVisible() || iconCodePostal.isVisible() || iconVille.isVisible()
                || iconPays.isVisible()) {

            toastMsg.show("Svp, il ya des champs n'est pas bien formé", 2000);
            return;
        }

        // Using builder design pattern to make Fournisseur object
        Fournisseur Fournisseur = new FournisseurBuilder()
                .setSociete(fieldSociete.getText())
                .setCivilite(comboCivilite.getSelectionModel().getSelectedItem())
                .setNom(fieldNom.getText().trim())
                .setPrenom(fieldPrenom.getText().trim())
                .setTelephone(fieldTelephone.getText())
                .setMobile(fieldMobile.getText())
                .setFax(fieldFax.getText())
                .setEmail(fieldEmail.getText())
                .setAdresse(fieldAdresse.getText())
                .setCodePostal(fieldCodePostal.getText())
                .setVille(fieldVille.getText())
                .setPays(fieldPays.getText())
                .setObservations(areaObservations.getText())
                .build();

        int status = FournisseurDao.addFournisseur(Fournisseur);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 1500);
                break;
            case 0:
                toastMsg.show("Erreur dans l'ajoute de fournisseur !", 1500);
                break;
            default : {
                Notifications.create()
                        .title("Vous avez ajouter un fournisseur !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();

                onClear();
                // Initialize Numero Fournisseur (get auto increment from db)
                int currentAutoIncrement = MainDao.getCurrentAutoIncrement("Fournisseur");
                fieldNumero.setText(String.valueOf(currentAutoIncrement));
                break;
            }
        }
    }

    @FXML
    private void onClear() { // Clear everything in interface
        fieldSociete.setText(null);
        comboCivilite.getSelectionModel().clearSelection();
        fieldNom.setText(null);
        fieldPrenom.setText(null);
        fieldTelephone.setText(null);
        fieldMobile.setText(null);
        fieldFax.setText(null);
        fieldEmail.setText(null);
        fieldAdresse.setText(null);
        fieldCodePostal.setText(null);
        fieldVille.setText(null);
        fieldPays.setText(null);
        areaObservations.setText(null);

        fieldNom.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
        fieldPrenom.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");

        iconNom.setVisible(false);
        iconPrenom.setVisible(false);
    }

    @FXML
    private void onClose() {
        FournisseurController.dialogFournisseurAdd.close();
    }
}
