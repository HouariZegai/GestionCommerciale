package com.houarizegai.gestioncommercial.java.controllers.forms.client;

import com.houarizegai.gestioncommercial.java.controllers.ClientController;
import com.houarizegai.gestioncommercial.java.database.DBConnection;
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
import java.time.ZoneId;
import java.util.ResourceBundle;

public class EditClientController implements Initializable {

    @FXML // Parent of all client (root node)
    private VBox root;

    /* Client infos */
    @FXML
    private JFXTextField fieldSociete, fieldCivilite, fieldNom, fieldPrenom, fieldTelephone, fieldMobile, fieldFax,
            fieldEmail, fieldType, fieldAdresse, fieldCodePostal, fieldVille, fieldPays;

    @FXML
    private JFXToggleButton tglBtnLivreMemeAdresse, tglBtnFactureMemeAdresse, tglBtnExemptTva;

    @FXML
    private JFXTextArea areaObservations;

    // For show error msg (like: Toast in android)
    private JFXSnackbar toastMsg;
    // client infos
    public static Client clientInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);
        onReset();
    }

    @FXML
    private void onEdit() { // Add new ClientRegex
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
            toastMsg.show("Le champ Nom ne pas bien formé !", 2000);
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
                .setLivreMemeAdresse(tglBtnLivreMemeAdresse.isSelected())
                .setFactureMemeAdresse(tglBtnFactureMemeAdresse.isSelected())
                .setExemptTva(tglBtnExemptTva.isSelected())
                .setAuteurModif(DBConnection.user)
                .setDateModif(new java.util.Date())
                .setObservations(areaObservations.getText().trim())
                .build();

        int status = new ClientDao().setClient(clientInfo.getNumClient(), client);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 1500);
                break;
            case 0:
                toastMsg.show("Erreur dans la modification de client !", 1500);
                break;
            default : {
                Notifications.create()
                        .title("Vous avez modifier le client !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();

                ClientController.dialogClientEdit.close();
                break;
            }
        }
    }

    @FXML
    private void onReset() { // Clear everything in interface
        fieldSociete.setText(clientInfo.getSociete());
        fieldCivilite.setText(clientInfo.getCivilite());
        fieldNom.setText(clientInfo.getNomClient());
        fieldPrenom.setText(clientInfo.getPrenom());
        fieldTelephone.setText(clientInfo.getTelephone());
        fieldMobile.setText(clientInfo.getMobile());
        fieldFax.setText(clientInfo.getFax());
        fieldEmail.setText(clientInfo.getEmail());
        fieldType.setText(String.valueOf(clientInfo.getType()));
        fieldAdresse.setText(clientInfo.getAdresse());
        fieldCodePostal.setText(clientInfo.getCodePostal());
        fieldVille.setText(clientInfo.getVille());
        fieldPays.setText(clientInfo.getPays());
        tglBtnLivreMemeAdresse.setSelected(clientInfo.isLivreMemeAdresse());
        tglBtnFactureMemeAdresse.setSelected(clientInfo.isFactureMemeAdresse());
        tglBtnExemptTva.setSelected(clientInfo.isExemptTva());

        areaObservations.setText(clientInfo.getObservations());
    }

    @FXML
    private void onClose() {
        ClientController.dialogClientEdit.close();
    }
}
