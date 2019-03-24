package com.houarizegai.gestioncommerciale.java.controllers.forms.client;

import com.houarizegai.gestioncommerciale.java.controllers.ClientController;
import com.houarizegai.gestioncommerciale.java.database.dao.ClientDao;
import com.houarizegai.gestioncommerciale.java.database.models.Client;
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

public class DeleteClientController implements Initializable {

    @FXML
    private Label lblNumero, lblNom, lblPrenom, lblCivilite, lblEmail, lblType, lblSociete, lblAdresse,
            lblCodePostal, lblVille, lblTelephone, lblMobile, lblFax, lblPays, lblLivreMemeAdresse,
            lblFactureMemeAdresse, lblExemptTva;
    @FXML
    private Text txtObservations;

    // client u want to delete it
    public static Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Initialize the view by information of client u want to delete it */
        lblNumero.setText(String.valueOf(client.getNumClient()));
        lblNom.setText(client.getNomClient());
        lblPrenom.setText(client.getPrenom());
        lblCivilite.setText(client.getCivilite());
        lblEmail.setText(client.getEmail());
        lblType.setText(String.valueOf(client.getType()));
        lblSociete.setText(client.getSociete());
        lblAdresse.setText(client.getAdresse());
        lblCodePostal.setText(client.getCodePostal());
        lblVille.setText(client.getVille());
        lblTelephone.setText(client.getTelephone());
        lblMobile.setText(client.getMobile());
        lblFax.setText(client.getFax());
        lblPays.setText(client.getPays());
        lblLivreMemeAdresse.setText(client.isLivreMemeAdresse() ? "Oui" : "Non");
        lblFactureMemeAdresse.setText(client.isFactureMemeAdresse() ? "Oui" : "Non");
        lblExemptTva.setText(client.isExemptTva() ? "Oui" : "Non");
        txtObservations.setText(client.getObservations());
    }

    @FXML
    private void onDelete() {
        int status = ClientDao.deleteClient(client.getNumClient());

        switch (status) {
            case -1:
                System.out.println("Connection error (cannot delete client)!");
                break;
            case -2:
                System.out.println("Client have facture not paid (cannot delete client)!");
                Notifications notification = Notifications.create()
                        .title("Client have facture not paid !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommerciale/resources/images/icons/error.png")))
                        .hideAfter(Duration.millis(4000))
                        .position(Pos.BOTTOM_RIGHT);
                notification.darkStyle();
                notification.show();
                break;
            default:
                Notifications notification2 = Notifications.create()
                        .title("Vous avez supprimer le client !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommerciale/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(4000))
                        .position(Pos.BOTTOM_RIGHT);
                notification2.darkStyle();
                notification2.show();
                break;
        }

        ClientController.dialogClientDelete.close();
    }

    @FXML
    private void onCancel() {
        ClientController.dialogClientDelete.close();
    }
}
