package com.houarizegai.gestioncommercial.java.controllers.forms.produit;

import com.houarizegai.gestioncommercial.java.controllers.ProduitController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProduitController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    private JFXTextField fieldReference, fieldCodeBarre, fieldLibProd, fieldPrixHT, fieldGenCode, fieldQteMin, fieldQteReappo;

    @FXML
    private FontAwesomeIconView iconReference, iconGenCode, iconCodeBarre, iconLibProd, iconPrixHT, iconQteReappro, iconQteMin;

    @FXML
    private JFXTextArea areaDesc;

    @FXML
    private JFXComboBox<String> comboTauxTva, comboFamille, comboPort, comboFournisseur;

    @FXML
    private JFXToggleButton tglPlusAuCatalogue;

    @FXML // image imported from PC (image chooser)
    private ImageView imageProduit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCombos();
    }

    private void initCombos() {

    }

    @FXML
    void onChooseImage() { // for choose image from pc

    }

    @FXML
    private void onAdd() { // Add produit to database

    }

    @FXML
    private void onClear() { // Add clear screen (remove the content of all nodes in this field)
        fieldReference.setText(null);
        fieldCodeBarre.setText(null);
        fieldLibProd.setText(null);
        fieldPrixHT.setText(null);
        fieldGenCode.setText(null);
        fieldQteMin.setText(null);
        fieldQteReappo.setText(null);

        areaDesc.setText(null);

        comboTauxTva.getSelectionModel().clearSelection();
        comboFournisseur.getSelectionModel().clearSelection();
        comboFamille.getSelectionModel().clearSelection();
        comboPort.getSelectionModel().clearSelection();

        tglPlusAuCatalogue.setSelected(false);

        imageProduit.setImage(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/add_product.png"));
    }

    @FXML
    private void onClose() {
        ProduitController.dialogProduitAdd.close();
    }

}
