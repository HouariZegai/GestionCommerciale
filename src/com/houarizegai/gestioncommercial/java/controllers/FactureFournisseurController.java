package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.dao.ProduitDao;
import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
import com.houarizegai.gestioncommercial.java.database.models.Produit;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FactureFournisseurController implements Initializable {
    // Root Node (Parent of all elements (nodes))
    @FXML
    private StackPane root;

    /* Start Fournisseur */

    @FXML
    private JFXTextField fieldNumFournisseur, fieldNomFournisseur, fieldPrenomFournisseur;

    public static Fournisseur selectedFournisseur;

    public static JFXDialog dialogSelectForunisseur;

    private VBox selectFournisseurView;

    /* End Fournisseur */

    /* Start Facture Infos */

    @FXML
    private JFXTextField fieldNumFacture, fieldNumFactureFournisseur;

    @FXML
    private JFXDatePicker pickerDate;

    /* End Facture Infos */

    /* Start Product Table */

    @FXML
    private JFXTreeTableView tableProduit;

    private JFXTreeTableColumn<TableProduit, String> colRef, colDesignation, colQte, colPU, colMHT, colTVA, colMTTC;

    /* End Product Table */

    // Payment Mode Number
    @FXML
    private JFXTextField fieldNumModePayement;

    // Total Montant Infos
    @FXML
    private JFXTextField fieldTotalHT, fieldTotalTVA, fieldTotalTTC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init Date of facture
        pickerDate.setValue(LocalDate.now());

        // Load Select Fournisseur View
        try {
            selectFournisseurView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/facture_fournisseur/SelectFournisseur.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Initialize table product
        initProduitTable();
    }

    /* Start Table Produit */

    class TableProduit extends RecursiveTreeObject<TableProduit> {
        StringProperty ref;
        StringProperty designation;
        StringProperty qte;
        StringProperty pu;
        StringProperty mht;
        StringProperty tva;
        StringProperty mttc;

        public TableProduit(String ref, String designation, int qte, double pu, double mht, double tva, double mttc) {
            this.ref = new SimpleStringProperty(ref);
            this.designation = new SimpleStringProperty(designation);
            this.qte = new SimpleStringProperty(String.valueOf(qte));
            this.pu = new SimpleStringProperty(String.valueOf(pu));
            this.mht = new SimpleStringProperty(String.valueOf(mht));
            this.tva = new SimpleStringProperty(String.valueOf(tva));
            this.mttc = new SimpleStringProperty(String.valueOf(mttc));
        }
    }

    private void initProduitTable() { // This function initialize the table by colunms
        colRef = new JFXTreeTableColumn<>("Reference");
        colRef.setPrefWidth(180);
        colRef.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().ref);

        colDesignation = new JFXTreeTableColumn<>("Désignation");
        colDesignation.setPrefWidth(250);
        colDesignation.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().designation);


        colQte = new JFXTreeTableColumn<>("Qte");
        colQte.setPrefWidth(150);
        colQte.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().qte);

        colPU = new JFXTreeTableColumn<>("Prix Unitaire");
        colPU.setPrefWidth(150);
        colPU.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().pu);

        colMHT = new JFXTreeTableColumn<>("Montant HT");
        colMHT.setPrefWidth(150);
        colMHT.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().mht);

        colTVA = new JFXTreeTableColumn<>("TVA %");
        colTVA.setPrefWidth(100);
        colTVA.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().tva);

        colMTTC = new JFXTreeTableColumn<>("Montant TTC");
        colMTTC.setPrefWidth(160);
        colMTTC.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().mttc);

        // Add columns to table
        tableProduit.getColumns().addAll(colRef, colDesignation, colQte, colPU, colMHT, colTVA, colMTTC);
        tableProduit.setShowRoot(false);
    }

    /* End Table Produit */

    // For Select The Fournisseur
    @FXML
    private void onChooseFournisseur() {
        dialogSelectForunisseur = new JFXDialog(root, selectFournisseurView, JFXDialog.DialogTransition.CENTER);
        dialogSelectForunisseur.setOnDialogClosed(e -> { // if i close dialog: make the fournisseur selected
            if(selectedFournisseur == null)
                return;
            fieldNumFournisseur.setText(String.valueOf(selectedFournisseur.getNumFournisseur()));
            fieldNomFournisseur.setText(String.valueOf(selectedFournisseur.getNom()));
            fieldPrenomFournisseur.setText(String.valueOf(selectedFournisseur.getPrenom()));
        });
        dialogSelectForunisseur.show();
    }

    // For Select Payment Mode
    @FXML
    private void onChoosePaymentMode() {

    }

    /* Start Product operations */

    @FXML
    private void onAdd() {

    }

    @FXML
    private void onDelete() {

    }

    @FXML
    private void onEdit() {

    }

    @FXML
    private void onPrint() {

    }

    /* End Product operations */

    /* Start Change Fournisseur (Bascule between fournissuer) */

    @FXML
    private void onMoveToFirst() {

    }

    @FXML
    private void onMoveToNext() {

    }

    @FXML
    private void onMoveToEnd() {

    }

    @FXML
    private void onMoveToPrevious() {

    }

    /* End Change Fournisseur (Bascule between fournissuer) */

    @FXML
    private void onSave() {

    }
}
