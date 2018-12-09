package com.houarizegai.gestioncommercial.java.controllers.forms.client;

import com.houarizegai.gestioncommercial.java.controllers.ClientController;
import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
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

public class EditClientController implements Initializable {

    @FXML // Parent of all client (root node)
    private VBox root;

    /* Client infos */
    @FXML
    private JFXTextField fieldNumClient, fieldSociete, fieldNom, fieldPrenom, fieldTelephone, fieldMobile, fieldFax,
            fieldEmail, fieldType, fieldAdresse, fieldCodePostal, fieldVille, fieldPays;
    @FXML
    private JFXComboBox<String> comboCivilite;
    @FXML // Error icons
    private FontAwesomeIconView iconSociete, iconNom, iconPrenom, iconTelephone, iconMobile, iconFax,
            iconEmail, iconType, iconAdresse, iconCodePostal, iconVille, iconPays;

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
        // Initialize combo Civilite
        comboCivilite.getItems().addAll("Mr", "Mme", "Melle");

        toastMsg = new JFXSnackbar(root);

        fieldNumClient.setText(String.valueOf(clientInfo.getNumClient()));
        onReset();
        initFieldListener();
    }

    private void initFieldListener() {
        fieldSociete.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldSociete, iconSociete, newValue, ClientRegex.SOCIETE));
        fieldNom.textProperty().addListener((observable, oldValue, newValue) -> setValidFontNomPrenom(fieldNom, iconNom, newValue, ClientRegex.NOM));
        fieldPrenom.textProperty().addListener((observable, oldValue, newValue) -> setValidFontNomPrenom(fieldPrenom, iconPrenom, newValue, ClientRegex.PRENOM));
        fieldTelephone.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldTelephone, iconTelephone, newValue, ClientRegex.TELEPHONE));
        fieldMobile.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldMobile, iconMobile, newValue, ClientRegex.MOBILE));
        fieldFax.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldFax, iconFax, newValue, ClientRegex.FAX));
        fieldEmail.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldEmail, iconEmail, newValue, ClientRegex.EMAIL));
        fieldType.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldType, iconType, newValue, ClientRegex.TYPE));
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

    private void setValidFontNomPrenom(JFXTextField field, FontAwesomeIconView errorIcon, String newValue, String regex) {
        if(newValue == null || !newValue.trim().matches(regex)) {
            field.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            errorIcon.setVisible(true);
        } else {
            field.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
            errorIcon.setVisible(false);
        }
    }

    @FXML
    private void onSave() { // Add new ClientRegex
        if(fieldNom.getText().isEmpty()) {
            fieldNom.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            iconNom.setVisible(true);
        }
        if(fieldPrenom.getText().isEmpty()) {
            fieldPrenom.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            iconPrenom.setVisible(true);
        }

        if(iconSociete.isVisible() || iconNom.isVisible() || iconPrenom.isVisible()
                || iconTelephone.isVisible() || iconMobile.isVisible() || iconFax.isVisible() || iconEmail.isVisible()
                || iconType.isVisible() || iconAdresse.isVisible() || iconCodePostal.isVisible() || iconVille.isVisible()
                || iconPays.isVisible()) {

            toastMsg.show("Svp, il ya des champs n'est pas bien formé", 2000);
            return;
        }

        // Using builder design pattern to make client object
        Client client = new ClientBuilder()
                .setNumClient(Integer.parseInt(fieldNumClient.getText()))
                .setSociete(fieldSociete.getText())
                .setCivilite(comboCivilite.getSelectionModel().getSelectedItem())
                .setNomClient(fieldNom.getText())
                .setPrenom(fieldPrenom.getText())
                .setTelephone(fieldTelephone.getText())
                .setMobile(fieldMobile.getText())
                .setFax(fieldFax.getText())
                .setEmail(fieldEmail.getText())
                .setType((fieldType.getText() == null || fieldType.getText().trim().isEmpty()) ? 0 : Integer.parseInt(fieldType.getText()))
                .setAdresse(fieldAdresse.getText())
                .setCodePostal(fieldCodePostal.getText())
                .setVille(fieldVille.getText())
                .setPays(fieldPays.getText())
                .setLivreMemeAdresse(tglBtnLivreMemeAdresse.isSelected())
                .setFactureMemeAdresse(tglBtnFactureMemeAdresse.isSelected())
                .setExemptTva(tglBtnExemptTva.isSelected())
                .setSaisiPar(DBConnection.user)
                .setSaisiLe(new java.util.Date())
                .setAuteurModif(DBConnection.user)
                .setDateModif(new java.util.Date())
                .setObservations(areaObservations.getText())
                .build();

        int status = ClientDao.setClient(client);

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
        comboCivilite.getSelectionModel().select(clientInfo.getCivilite());
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
