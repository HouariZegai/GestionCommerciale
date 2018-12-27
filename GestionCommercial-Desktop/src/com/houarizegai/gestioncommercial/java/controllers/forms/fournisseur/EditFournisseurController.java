package com.houarizegai.gestioncommercial.java.controllers.forms.fournisseur;

import com.houarizegai.gestioncommercial.java.controllers.FournisseurController;
import com.houarizegai.gestioncommercial.java.database.dao.FournisseurDao;
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

public class EditFournisseurController implements Initializable {

    @FXML // Parent of all fournisseur (root node)
    private VBox root;

    /* Fournisseur infos */
    @FXML
    private JFXTextField fieldNumFournisseur, fieldSociete, fieldNom, fieldPrenom, fieldTelephone, fieldMobile, fieldFax,
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
    // fournisseur infos
    public static Fournisseur fournisseurInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize combo Civilite
        comboCivilite.getItems().addAll("Mr", "Mme", "Melle");

        toastMsg = new JFXSnackbar(root);

        fieldNumFournisseur.setText(String.valueOf(fournisseurInfo.getNumFournisseur()));
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
    private void onSave() { // Add new FournisseurRegex
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
                || iconAdresse.isVisible() || iconCodePostal.isVisible() || iconVille.isVisible()
                || iconPays.isVisible()) {

            toastMsg.show("Svp, il ya des champs n'est pas bien formé", 2000);
            return;
        }

        // Using builder design pattern to make fournisseur object
        Fournisseur fournisseur = new FournisseurBuilder()
                .setNumFournisseur(Integer.parseInt(fieldNumFournisseur.getText()))
                .setSociete(fieldSociete.getText())
                .setCivilite(comboCivilite.getSelectionModel().getSelectedItem())
                .setNom(fieldNom.getText())
                .setPrenom(fieldPrenom.getText())
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

        int status = FournisseurDao.setFournisseur(fournisseur);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 1500);
                break;
            case 0:
                toastMsg.show("Erreur dans la modification de fournisseur !", 1500);
                break;
            default : {
                Notifications.create()
                        .title("Vous avez modifier le fournisseur !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();

                FournisseurController.dialogFournisseurEdit.close();
                break;
            }
        }
    }

    @FXML
    private void onReset() { // Clear everything in interface
        fieldSociete.setText(fournisseurInfo.getSociete());
        comboCivilite.getSelectionModel().select(fournisseurInfo.getCivilite());
        fieldNom.setText(fournisseurInfo.getNom());
        fieldPrenom.setText(fournisseurInfo.getPrenom());
        fieldTelephone.setText(fournisseurInfo.getTelephone());
        fieldMobile.setText(fournisseurInfo.getMobile());
        fieldFax.setText(fournisseurInfo.getFax());
        fieldEmail.setText(fournisseurInfo.getEmail());
        fieldAdresse.setText(fournisseurInfo.getAdresse());
        fieldCodePostal.setText(fournisseurInfo.getCodePostal());
        fieldVille.setText(fournisseurInfo.getVille());
        fieldPays.setText(fournisseurInfo.getPays());

        areaObservations.setText(fournisseurInfo.getObservations());
    }

    @FXML
    private void onClose() {
        FournisseurController.dialogFournisseurEdit.close();
    }
}
