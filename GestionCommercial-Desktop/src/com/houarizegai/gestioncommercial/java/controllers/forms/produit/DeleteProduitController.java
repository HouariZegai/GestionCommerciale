package com.houarizegai.gestioncommercial.java.controllers.forms.produit;

import com.houarizegai.gestioncommercial.java.controllers.ProduitController;
import com.houarizegai.gestioncommercial.java.database.dao.ProduitDao;
import com.houarizegai.gestioncommercial.java.database.models.Produit;
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

public class DeleteProduitController implements Initializable {

    @FXML
    private Label lblReference, lblCodeBarre, lblGenCode, lblLibProd, lblTauxTva, lblPrixHT, lblQteReappro, lblQteMin, lblSaisiLe,
            lblFournisseur, lblPlusAuCatalogue, lblSaisiPar, lblCodeFamille, lblCodePort;

    @FXML
    private Text txtDesc;

    @FXML
    private ImageView imgProduit;

    // Product u want to delete it
    public static Produit produit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Initialize the view by information of client u want to delete it */
        lblReference.setText(produit.getReference());
        lblGenCode.setText(produit.getGenCode());
        lblCodeBarre.setText(produit.getCodeBarre());
        lblLibProd.setText(produit.getLibProd());
        txtDesc.setText(produit.getDescription());
        lblPrixHT.setText(String.valueOf(produit.getPrixHt()));
        lblQteReappro.setText(String.valueOf(produit.getQteReappro()));
        lblQteMin.setText(String.valueOf(produit.getQteMini()));
        lblTauxTva.setText(String.valueOf(produit.getTauxTva()) + " %");
        lblFournisseur.setText(String.valueOf(produit.getNumFournisseur()));
        lblPlusAuCatalogue.setText(produit.isPlusAuCatalogue() ? "Oui" : "Non");
        lblSaisiPar.setText(produit.getSaisiPar());
        lblSaisiLe.setText(produit.getSaisiLe().toString());
        lblCodeFamille.setText(produit.getCodeFamille());
        lblCodePort.setText(produit.getCodePort());
    }

    @FXML
    private void onDelete() {
        int status = ProduitDao.deleteProduit(produit.getReference());

        if (status == -1) {
            System.out.println("Connection error (cannot delete Product)!");
        } else {
            Notifications.create()
                    .title("Vous avez supprimer le Produit !")
                    .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                    .hideAfter(Duration.millis(2000))
                    .position(Pos.BOTTOM_RIGHT)
                    .darkStyle()
                    .show();

        }

        ProduitController.dialogProduitDelete.close();
    }

    @FXML
    private void onCancel() {
        ProduitController.dialogProduitDelete.close();
    }
}
