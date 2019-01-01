package com.houarizegai.gestioncommercial.java.controllers.forms.fournisseur;

import com.houarizegai.gestioncommercial.java.controllers.FournisseurController;
import com.houarizegai.gestioncommercial.java.database.dao.FournisseurDao;
import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteFournisseurController implements Initializable {

    @FXML
    private Label lblNumero, lblNom, lblPrenom, lblCivilite, lblEmail, lblType, lblSociete, lblAdresse,
            lblCodePostal, lblVille, lblTelephone, lblMobile, lblFax, lblPays;
    @FXML
    private Text txtObservations;

    // Fournisseur u want to delete it
    public static Fournisseur fournisseur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Initialize the view by information of client u want to delete it */
        lblNumero.setText(String.valueOf(fournisseur.getNumFournisseur()));
        lblNom.setText(fournisseur.getNom());
        lblPrenom.setText(fournisseur.getPrenom());
        lblCivilite.setText(fournisseur.getCivilite());
        lblEmail.setText(fournisseur.getEmail());
        lblSociete.setText(fournisseur.getSociete());
        lblAdresse.setText(fournisseur.getAdresse());
        lblCodePostal.setText(fournisseur.getCodePostal());
        lblVille.setText(fournisseur.getVille());
        lblTelephone.setText(fournisseur.getTelephone());
        lblMobile.setText(fournisseur.getMobile());
        lblFax.setText(fournisseur.getFax());
        lblPays.setText(fournisseur.getPays());
        txtObservations.setText(fournisseur.getObservations());
    }

    @FXML
    private void onDelete() {
        int status = FournisseurDao.deleteFournisseur(fournisseur.getNumFournisseur());

        if (status == -1) {
            System.out.println("Connection error (cannot delete Fournisseur)!");
        } else {
            Notifications notification = Notifications.create()
                    .title("Vous avez supprimer le Fournisseur !")
                    .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                    .hideAfter(Duration.millis(2000))
                    .position(Pos.BOTTOM_RIGHT);
            notification.darkStyle();
            notification.show();
        }

        FournisseurController.dialogFournisseurDelete.close();
    }

    @FXML
    private void onCancel() {
        FournisseurController.dialogFournisseurDelete.close();
    }
}
