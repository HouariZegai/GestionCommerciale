package com.houarizegai.gestioncommercial.java.controllers.forms.produit;

import com.houarizegai.gestioncommercial.java.Launcher;
import com.houarizegai.gestioncommercial.java.controllers.ProduitController;
import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.dao.*;
import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
import com.houarizegai.gestioncommercial.java.database.models.FraisPort;
import com.houarizegai.gestioncommercial.java.database.models.Produit;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ProduitBuilder;
import com.houarizegai.gestioncommercial.java.utils.regex.ProduitRegex;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProduitController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    private JFXTextField fieldReference, fieldCodeBarre, fieldLibProd, fieldPrixHT, fieldGenCode, fieldQteMin, fieldQteReappro;

    @FXML
    private FontAwesomeIconView iconReference, iconGenCode, iconCodeBarre, iconLibProd, iconPrixHT, iconQteReappro, iconQteMin;

    @FXML
    private JFXTextArea areaDesc;

    @FXML
    private JFXComboBox<String> comboTauxTva, comboFamille, comboPort, comboFournisseur;

    @FXML
    private JFXToggleButton tglPlusAuCatalogue;

    @FXML // image of produit, imported from PC (image chooser)
    private ImageView imageProduit;

    private JFXSnackbar toastMsg;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);
        initCombos();
        initFieldListener();
    }

    private void initCombos() {
        for(double taux : Objects.requireNonNull(TVADao.getTauxTva())) // get Taux TVA from db
            comboTauxTva.getItems().add(String.valueOf(taux));

        for(FraisPort port : Objects.requireNonNull(FraisPortDao.getFraisPorts())) // get Frais port from db
            comboPort.getItems().add(port.getLibFraisPort());

        for(String[] f : Objects.requireNonNull(FamilleDao.getFamilles())) // get Familles from db
            comboFamille.getItems().add(String.valueOf(f[1]));

        for(Fournisseur f : Objects.requireNonNull(FournisseurDao.getFournisseur()))
            comboFournisseur.getItems().add(f.getNumFournisseur() + " " + f.getNom() + " " + f.getPrenom());
    }

    private void initFieldListener() {
        fieldReference.textProperty().addListener((observable, oldValue, newValue) -> setValidFontRequired(fieldReference, iconReference, newValue, ProduitRegex.REFERENCE));
        fieldGenCode.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldGenCode, iconGenCode, newValue, ProduitRegex.GEN_CODE));
        fieldCodeBarre.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldCodeBarre, iconCodeBarre, newValue, ProduitRegex.CODE_BARRE));
        fieldLibProd.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldLibProd, iconLibProd, newValue, ProduitRegex.LIB_PROD));
        fieldPrixHT.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldPrixHT, iconPrixHT, newValue, ProduitRegex.PRIX_HT));
        fieldQteReappro.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldQteReappro, iconQteReappro, newValue, ProduitRegex.QTE_REAPPRO));
        fieldQteMin.textProperty().addListener((observable, oldValue, newValue) -> setValidFont(fieldQteMin, iconQteMin, newValue, ProduitRegex.QTE_MIN));
    }

    private void setValidFont(JFXTextField field, FontAwesomeIconView errorIcon, String newValue, String regex) { // Change the font if not valid or reset color
        if(newValue != null && !newValue.trim().isEmpty() && !newValue.trim().matches(regex)) {
            field.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            errorIcon.setVisible(true);
        } else {
            field.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
            errorIcon.setVisible(false);
        }
    }

    private void setValidFontRequired(JFXTextField field, FontAwesomeIconView errorIcon, String newValue, String regex) {
        if(newValue == null || !newValue.trim().matches(regex)) {
            field.setStyle("-jfx-un-focus-color: #E00; -jfx-focus-color: #D00;");
            errorIcon.setVisible(true);
        } else {
            field.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
            errorIcon.setVisible(false);
        }
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
        setValidFontRequired(fieldReference, iconReference, fieldReference.getText(), ProduitRegex.REFERENCE);

        if(iconReference.isVisible() || iconGenCode.isVisible() || iconCodeBarre.isVisible() || iconLibProd.isVisible() || iconPrixHT.isVisible()
                || iconQteReappro.isVisible() || iconQteMin.isVisible()) {
            toastMsg.show("Svp, il ya des champs n'est pas bien formé", 2000);
            return;
        }

        String fournisseurNomPrenom = comboFournisseur.getSelectionModel().getSelectedItem();
        int numFournisseur = fournisseurNomPrenom == null ? 1 : Integer.parseInt(fournisseurNomPrenom.split(" ")[0]);
        String codeFamille = FamilleDao.getCodeFamille(comboFamille.getSelectionModel().getSelectedItem());
        String codePort = comboPort.getSelectionModel().getSelectedItem() == null ? null : FraisPortDao.getCodePort(comboPort.getSelectionModel().getSelectedItem());

        // Using builder design pattern to make produit object
        Produit produit = new ProduitBuilder()
                .setReference(fieldReference.getText())
                .setGenCode(fieldGenCode.getText())
                .setCodeBarre(fieldCodeBarre.getText())
                .setLibProd(fieldLibProd.getText())
                .setDescription(areaDesc.getText())
                .setPrixHt((fieldPrixHT.getText() == null || fieldPrixHT.getText().trim().isEmpty()) ? 0d : Double.parseDouble(fieldPrixHT.getText()))
                .setQteReappro((fieldQteReappro.getText() == null || fieldQteReappro.getText().trim().isEmpty()) ? 0 : Integer.parseInt(fieldQteReappro.getText()))
                .setQteMini((fieldQteMin.getText() == null || fieldQteMin.getText().trim().isEmpty()) ? 0 : Integer.parseInt(fieldQteMin.getText()))
                .setTauxTva((comboTauxTva.getSelectionModel().getSelectedItem() == null)? 17d : Double.parseDouble(comboTauxTva.getSelectionModel().getSelectedItem()))
                //.setPhoto(imageProduit.getImage())
                .setNumFournisseur(numFournisseur)
                .setPlusAuCatalogue(tglPlusAuCatalogue.isSelected())
                .setSaisiPar(DBConnection.user)
                .setSaisiLe(new Date())
                .setCodeFamille(codeFamille)
                .setCodePort(codePort)
                .build();


        int status = ProduitDao.addProduit(produit);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 1500);
                break;
            case 0:
                toastMsg.show("Erreur dans l'ajoute de Produit !", 1500);
                break;
            default : {
                Notifications.create()
                        .title("Vous avez ajouter un Produit !")
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
    private void onClear() { // Add clear screen (remove the content of all nodes in this field)
        fieldReference.setText(null);
        fieldCodeBarre.setText(null);
        fieldLibProd.setText(null);
        fieldPrixHT.setText(null);
        fieldGenCode.setText(null);
        fieldQteMin.setText(null);
        fieldQteReappro.setText(null);

        areaDesc.setText(null);

        comboTauxTva.getSelectionModel().clearSelection();
        comboFournisseur.getSelectionModel().clearSelection();
        comboFamille.getSelectionModel().clearSelection();
        comboPort.getSelectionModel().clearSelection();

        tglPlusAuCatalogue.setSelected(false);

        imageProduit.setImage(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/add_product.png"));

        fieldReference.setStyle("-jfx-un-focus-color: #777; -jfx-focus-color: #0f9d58;");
        iconReference.setVisible(false);
    }

    @FXML
    private void onClose() {
        ProduitController.dialogProduitAdd.close();
    }

}
