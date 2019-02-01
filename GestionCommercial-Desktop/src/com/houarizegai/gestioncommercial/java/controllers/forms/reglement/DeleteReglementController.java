package com.houarizegai.gestioncommercial.java.controllers.forms.reglement;

import com.houarizegai.gestioncommercial.java.controllers.ReglementController;
import com.houarizegai.gestioncommercial.java.database.dao.ReglementDao;
import com.houarizegai.gestioncommercial.java.database.models.Reglement;
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

public class DeleteReglementController implements Initializable {

    @FXML
    private Label lblIdReg, lblDate, lblNumClient, lblModReg, lblMontant, lblSaisiPar, lblSaisiLe;

    @FXML
    private Text txtObs;

    // Product u want to delete it
    public static Reglement reglement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Initialize the view by information of reglement u want to delete it */
        lblIdReg.setText(String.valueOf(reglement.getIdReglement()));
        lblDate.setText(reglement.getDateReglement().toString());
        lblNumClient.setText(String.valueOf(reglement.getNumClient()));
        lblModReg.setText(ReglementDao.getLibModeReglement(reglement.getIdModeReglement()));
        lblMontant.setText(String.valueOf(reglement.getMontant()));
        lblSaisiPar.setText(reglement.getSaisiPar() == null ? "Inconnu" : reglement.getSaisiPar());
        lblSaisiLe.setText(reglement.getSaisiLe().toString());
        txtObs.setText(reglement.getObservations());
    }

    @FXML
    private void onDelete() {
        int status = ReglementDao.deleteReglement(reglement.getIdReglement());

        if (status == -1) {
            System.out.println("Connection error (cannot delete Reglement)!");
        } else {
            Notifications.create()
                    .title("Vous avez supprimer le Reglement !")
                    .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                    .hideAfter(Duration.millis(2000))
                    .position(Pos.BOTTOM_RIGHT)
                    .darkStyle()
                    .show();
        }

        ReglementController.dialogReglementDelete.close();
    }

    @FXML
    private void onCancel() {
        ReglementController.dialogReglementDelete.close();
    }
}
