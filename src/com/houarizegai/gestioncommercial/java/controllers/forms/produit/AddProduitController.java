package com.houarizegai.gestioncommercial.java.controllers.forms.produit;

import com.houarizegai.gestioncommercial.java.Launcher;
import com.houarizegai.gestioncommercial.java.controllers.ProduitController;
import com.houarizegai.gestioncommercial.java.database.dao.TVADao;
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
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        List<Double> tauxTva = TVADao.getTauxTva(); // get Tauc TVA from database
        if(!tauxTva.isEmpty())
            for(double taux : tauxTva)
            comboTauxTva.getItems().add(String.valueOf(taux));
    }

    @FXML
    void onChooseImage() { // for choose image from pc
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(Launcher.stage);

        if(file != null) {
            imageProduit.setImage(new Image(file.toURI().toString()));
        }
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
