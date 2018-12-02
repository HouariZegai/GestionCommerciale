package com.houarizegai.gestioncommercial.java.controllers.forms.client;

import com.houarizegai.gestioncommercial.java.controllers.ClientController;
import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AddClientController implements Initializable {

    @FXML // Parent of all client (root node)
    private VBox root;

    /* Client infos */
    @FXML
    private JFXTextField fieldSociete, fieldCivilite, fieldNom, fieldPrenom, fieldTelephone, fieldMobile, fieldFax,
            fieldEmail, fieldType, fieldAdresse, fieldCodePostal, fieldVille, fieldPays, fieldSaisiPar,
            fieldAuteurModif;

    @FXML
    private JFXCheckBox checkLivreMemeAdresse, checkFactureMemeAdresse, checkExemptTva;

    @FXML
    private JFXDatePicker pickerSaisiLe, pickerDateModif;

    @FXML
    private JFXTextArea areaObservations;

    // For show error msg (like: Toast in android)
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);
    }

    @FXML
    private void onAdd() { // Add new Client
        if(fieldSociete.getText() == null || !fieldSociete.getText().trim().toLowerCase().matches("[a-z0-9]{4,}")) {
            toastMsg.show("Le champ Societe ne pas bien formé !", 2000);
            return;
        }
        if(fieldCivilite.getText() == null || !fieldCivilite.getText().trim().toLowerCase().matches("[a-z0-9]{1,5}")) {
            toastMsg.show("Le champ Civilite ne pas bien formé !", 2000);
            return;
        }
        if(fieldNom.getText() == null || !fieldNom.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            toastMsg.show("Le champ Nom ne pas bien formé !", 2000);
            return;
        }
        if(fieldPrenom.getText() == null || !fieldPrenom.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            toastMsg.show("Le champ Prenom ne pas bien formé !", 2000);
            return;
        }
        if(fieldTelephone.getText() == null || !fieldTelephone.getText().trim().matches("[0-9]{8,}")) {
            toastMsg.show("Le champ Telephone ne pas bien formé !", 2000);
            return;
        }
        if(fieldMobile.getText() == null || !fieldMobile.getText().trim().matches("[0-9]{8,}")) {
            toastMsg.show("Le champ Mobile ne pas bien formé !", 2000);
            return;
        }
        if(fieldFax.getText() == null || !fieldFax.getText().trim().matches("[0-9]{8,}")) {
            toastMsg.show("Le champ Fax ne pas bien formé !", 2000);
            return;
        }
        if(fieldEmail.getText() == null || !fieldEmail.getText().trim().matches("[a-zA-Z_][\\w]*[-]{0,4}[\\w]+@[a-zA-Z0-9]+.[a-zA-Z]{2,6}")) {
            toastMsg.show("Le champ Email ne pas bien formé !", 2000);
            return;
        }
        if(fieldType.getText() != null && !fieldType.getText().trim().matches("[0-9]{0,2}")) {
            toastMsg.show("Le champ Type ne pas bien formé !", 2000);
            return;
        }
        if(fieldAdresse.getText() == null || fieldAdresse.getText().trim().length() < 3) {
            toastMsg.show("Le champ Adresse ne pas bien formé !", 2000);
            return;
        }
        if(fieldCodePostal.getText() == null || !fieldCodePostal.getText().trim().matches("[0-9]{5}")) {
            toastMsg.show("Le champ Code Postal ne pas bien formé !", 2000);
            return;
        }
        if(fieldVille.getText() == null || !fieldVille.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            toastMsg.show("Le champ Ville ne pas bien formé !", 2000);
            return;
        }
        if(fieldPays.getText() == null || !fieldPays.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            toastMsg.show("Le champ Pays ne pas bien formé !", 2000);
            return;
        }
        if(fieldSaisiPar.getText() == null || !fieldSaisiPar.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            toastMsg.show("Le champ Saisi Par ne pas bien formé !", 2000);
            return;
        }
        if(pickerSaisiLe.getValue() == null) {
            toastMsg.show("Le champ Saisi Le ne pas bien formé !", 2000);
            return;
        }
        if(fieldAuteurModif.getText() == null || !fieldAuteurModif.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            toastMsg.show("Le champ Auteur Modif Par ne pas bien formé !", 2000);
            return;
        }
        if(pickerDateModif.getValue() == null) {
            toastMsg.show("Le champ Date Modif ne pas bien formé !", 2000);
            return;
        }

        Client client = new ClientBuilder()
                .setSociete(fieldSociete.getText().trim().toLowerCase())
                .setCivilite(fieldCivilite.getText().trim().toLowerCase())
                .setNomClient(fieldNom.getText().trim().toLowerCase())
                .setPrenom(fieldPrenom.getText().trim().toLowerCase())
                .setTelephone(fieldTelephone.getText().trim().toLowerCase())
                .setMobile(fieldMobile.getText().trim())
                .setFax(fieldFax.getText().trim())
                .setEmail(fieldEmail.getText().trim().toLowerCase())
                .setType(Integer.parseInt(fieldType.getText().trim()))
                .setAdresse(fieldAdresse.getText().trim())
                .setCodePostal(fieldCodePostal.getText())
                .setVille(fieldVille.getText().trim().toLowerCase())
                .setPays(fieldPays.getText().trim().toLowerCase())
                .setLivreMemeAdresse(checkLivreMemeAdresse.isSelected())
                .setFactureMemeAdresse(checkFactureMemeAdresse.isSelected())
                .setExemptTva(checkExemptTva.isSelected())
                .setSaisiPar(fieldSaisiPar.getText().trim().toLowerCase())
                .setSaisiLe(Date.from(Instant.from(pickerSaisiLe.getValue().atStartOfDay(ZoneId.systemDefault()))))
                .setAuteurModif(fieldAuteurModif.getText().trim().toLowerCase())
                .setDateModif(Date.from(Instant.from(pickerDateModif.getValue().atStartOfDay(ZoneId.systemDefault()))))
                .setObservations(areaObservations.getText().trim())
                .build();

        int status = new ClientDao().addclient(client);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 1500);
                break;
            case 0:
                toastMsg.show("Erreur dans l'ajoute de client !", 1500);
                break;
            default : {
                Notifications.create()
                        .title("Vous avez ajouter un client !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();

                ClientController.dialogClientAdd.close();
                break;
            }
        }
    }

    @FXML
    private void onClear() { // Clear everything in interface
        fieldSociete.setText(null);
        fieldCivilite.setText(null);
        fieldNom.setText(null);
        fieldPrenom.setText(null);
        fieldTelephone.setText(null);
        fieldMobile.setText(null);
        fieldFax.setText(null);
        fieldEmail.setText(null);
        fieldType.setText(null);
        fieldAdresse.setText(null);
        fieldCodePostal.setText(null);
        fieldVille.setText(null);
        fieldPays.setText(null);
        fieldSaisiPar.setText(null);
        fieldAuteurModif.setText(null);

        checkLivreMemeAdresse.setSelected(false);
        checkFactureMemeAdresse.setSelected(false);
        checkExemptTva.setSelected(false);

        pickerSaisiLe.setValue(null);
        pickerDateModif.setValue(null);

        areaObservations.setText(null);
    }

    @FXML
    private void onClose() {
        ClientController.dialogClientAdd.close();
    }
}
