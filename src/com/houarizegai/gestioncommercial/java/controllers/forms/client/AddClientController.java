package com.houarizegai.gestioncommercial.java.controllers.forms.client;

import com.houarizegai.gestioncommercial.java.controllers.ClientController;
import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
import com.houarizegai.gestioncommercial.java.utils.regex.ClientRegex;
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

    /* ClientRegex infos */
    @FXML
    private JFXTextField fieldSociete, fieldCivilite, fieldNom, fieldPrenom, fieldTelephone, fieldMobile, fieldFax,
            fieldEmail, fieldType, fieldAdresse, fieldCodePostal, fieldVille, fieldPays;

    @FXML
    private JFXToggleButton tglBtnLivreMemeAdresse, tglBtnFactureMemeAdresse, tglBtnExemptTva;

    @FXML
    private JFXTextArea areaObservations;

    // For show error msg (like: Toast in android)
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);
        initFieldListener();
    }

    private void initFieldListener() {
        fieldSociete.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldSociete, newValue, ClientRegex.SOCIETE));
        fieldCivilite.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldCivilite, newValue, ClientRegex.CIVILITE));
        fieldNom.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldNom, newValue, ClientRegex.NOM));
        fieldPrenom.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldPrenom, newValue, ClientRegex.PRENOM));
        fieldTelephone.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldTelephone, newValue, ClientRegex.TELEPHONE));
        fieldMobile.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldMobile, newValue, ClientRegex.MOBILE));
        fieldFax.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldFax, newValue, ClientRegex.FAX));
        fieldEmail.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldEmail, newValue, ClientRegex.EMAIL));
        fieldType.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldType, newValue, ClientRegex.TYPE));
        fieldAdresse.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldAdresse, newValue, ClientRegex.ADRESSE));
        fieldCodePostal.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldCodePostal, newValue, ClientRegex.CODE_POSTAL));
        fieldVille.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldVille, newValue, ClientRegex.VILLE));
        fieldPays.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldPays, newValue, ClientRegex.PAYS));
    }

    private void setValidFont(JFXTextField field, String newValue, String regex) { // Change the font if not valid or reset color
        if(!newValue.isEmpty() && !newValue.trim().toLowerCase().matches(regex)) {
            field.setStyle("-jfx-focus-color: #800; -fx-text-fill: #800;");
        } else {
            field.setStyle("-jfx-focus-color: #0f9d58; -fx-text-fill: #000;");
        }
    }

    @FXML
    private void onAdd() { // Add new ClientRegex

        // validate

        // Using builder design pattern to make client object
        Client client = new ClientBuilder()
                .setSociete(fieldSociete.getText().trim().toLowerCase())
                .setCivilite(fieldCivilite.getText().trim().toLowerCase())
                .setNomClient(fieldNom.getText().trim().toLowerCase())
                .setPrenom(fieldPrenom.getText().trim().toLowerCase())
                .setTelephone(fieldTelephone.getText().trim().toLowerCase())
                .setMobile(fieldMobile.getText().trim())
                .setFax(fieldFax.getText().trim())
                .setEmail(fieldEmail.getText().trim().toLowerCase())
                .setType((fieldType.getText().trim().isEmpty()) ? 0 : Integer.parseInt(fieldType.getText().trim()))
                .setAdresse(fieldAdresse.getText().trim())
                .setCodePostal(fieldCodePostal.getText())
                .setVille(fieldVille.getText().trim().toLowerCase())
                .setPays(fieldPays.getText().trim().toLowerCase())
                .setLivreMemeAdresse(tglBtnLivreMemeAdresse.isSelected())
                .setFactureMemeAdresse(tglBtnFactureMemeAdresse.isSelected())
                .setExemptTva(tglBtnExemptTva.isSelected())
                .setSaisiPar(DBConnection.user)
                .setSaisiLe(new java.util.Date())
                .setAuteurModif(DBConnection.user)
                .setDateModif(new java.util.Date())
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

                onClear();
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

        tglBtnLivreMemeAdresse.setSelected(false);
        tglBtnFactureMemeAdresse.setSelected(false);
        tglBtnExemptTva.setSelected(false);

        areaObservations.setText(null);
    }

    @FXML
    private void onClose() {
        ClientController.dialogClientAdd.close();
    }
}
