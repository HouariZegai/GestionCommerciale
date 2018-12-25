package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.dao.ProduitDao;
import com.houarizegai.gestioncommercial.java.database.dao.TVADao;
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

    // Data of Table
    public static ObservableList<TableProduit> listProduits;

    /* End Product Table */

    /* Start Product Form infos */

    @FXML
    private JFXTextField fieldQte;

    public static Produit selectedProduit;

    public static JFXDialog dialogSelectProduit;

    private VBox selectProduitView;

    /* End Product Form infos */

    // Payment Mode Number
    @FXML
    private JFXTextField fieldNumModePayement;

    // Total Montant Infos
    @FXML
    private JFXTextField fieldTotalHT, fieldTotalTVA, fieldTotalTTC;

    // Error message showing in time limited (like toast in android)
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);

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
        initDataOfProduitTable();

        // on Change Select
        tableProduit.setOnMouseClicked((e -> {
            fieldQte.setText(colQte.getCellData(tableProduit.getSelectionModel().getSelectedIndex()));
        }));
    }

    /* Start Table Produit */

    public class TableProduit extends RecursiveTreeObject<TableProduit> {
        public StringProperty ref;
        public StringProperty designation;
        public StringProperty qte;
        public StringProperty pu;
        public StringProperty mht;
        public StringProperty tva;
        public StringProperty mttc;

        public TableProduit(String ref, String designation, int qte, double pu, double mht, double tva, double mttc) {
            this.ref = new SimpleStringProperty(ref);
            this.designation = new SimpleStringProperty(designation);
            this.qte = new SimpleStringProperty(String.valueOf(qte));
            this.pu = new SimpleStringProperty(String.valueOf(pu));
            this.mht = new SimpleStringProperty(String.valueOf(mht));
            this.tva = new SimpleStringProperty(String.valueOf(tva));
            this.mttc = new SimpleStringProperty(String.valueOf(mttc));
        }

        public int getQte() {
            return Integer.parseInt(qte.getValue());
        }

        public void setQte(int qte) {
            this.qte = new SimpleStringProperty(String.valueOf(qte));

            this.setMht(this.getPu() * this.getQte());
            this.setMttc(this.getMht() * this.getTva() / 100 + this.getMht());
        }

        public double getPu() {
            return Double.parseDouble(pu.getValue());
        }

        public void setPu(double pu) {
            this.pu = new SimpleStringProperty(String.valueOf(pu));
        }

        public double getTva() {
            return Double.parseDouble(tva.getValue());
        }

        public void setTva(double tva) {
            this.tva = new SimpleStringProperty(String.valueOf(tva));
        }

        public double getMht() {
            return Double.parseDouble(this.mht.getValue());
        }

        public void setMht(double mht) {
            this.mht = new SimpleStringProperty(String.valueOf(mht));
        }

        public double getMttc() {
            return Double.parseDouble(this.mttc.getValue());
        }

        public void setMttc(double mttc) {
            this.mttc = new SimpleStringProperty(String.valueOf(mttc));
        }
    }

    private void initProduitTable() { // This function initialize the table by colunms
        colRef = new JFXTreeTableColumn<>("Reference");
        colRef.setPrefWidth(80);
        colRef.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().ref);

        colDesignation = new JFXTreeTableColumn<>("Désignation");
        colDesignation.setPrefWidth(120);
        colDesignation.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().designation);


        colQte = new JFXTreeTableColumn<>("Qte");
        colQte.setPrefWidth(70);
        colQte.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().qte);

        colPU = new JFXTreeTableColumn<>("P.U");
        colPU.setPrefWidth(100);
        colPU.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().pu);

        colMHT = new JFXTreeTableColumn<>("MHT");
        colMHT.setPrefWidth(100);
        colMHT.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().mht);

        colTVA = new JFXTreeTableColumn<>("TVA %");
        colTVA.setPrefWidth(80);
        colTVA.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().tva);

        colMTTC = new JFXTreeTableColumn<>("MTTC");
        colMTTC.setPrefWidth(100);
        colMTTC.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().mttc);

        // Add columns to table
        tableProduit.getColumns().addAll(colRef, colDesignation, colQte, colPU, colMHT, colTVA, colMTTC);
        tableProduit.setShowRoot(false);
    }

    private void initDataOfProduitTable() {
        listProduits = FXCollections.observableArrayList();
        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
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
        // Load Select Produit View
        try {
            selectProduitView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/facture_fournisseur/SelectProduit.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        dialogSelectProduit = new JFXDialog(root, selectProduitView, JFXDialog.DialogTransition.CENTER);
        dialogSelectProduit.setOnDialogClosed(e -> { // if i close dialog: make the fournisseur selected
            if(selectedProduit == null)
                return;
            listProduits.add(new TableProduit(selectedProduit.getReference(),
                    selectedProduit.getLibProd(),
                    0,
                    selectedProduit.getPrixHt(),
                    0.0,
                    selectedProduit.getTauxTva(),
                    0.0));
        });
        dialogSelectProduit.show();
    }

    @FXML
    private void onDelete() { // delete product
        if(tableProduit.getSelectionModel().getSelectedItem() == null) {
            toastMsg.show("Svp, selectionnée le produit qui vous voulez supprimer !", 2000);
            return;
        }

        //tableProduit.getRoot().getChildren().remove(tableProduit.getSelectionModel().getSelectedIndex());
        listProduits.remove(tableProduit.getSelectionModel().getSelectedIndex());

        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
        countTotal();
    }

    @FXML
    private void onEdit() { // Edit Qte of product
        if(fieldQte.getText() == null || !fieldQte.getText().trim().matches("[0-9]{1,3}")) {
            toastMsg.show("Svp, taper le quantité dans ce form: nombre entre 0 et 999", 2000);
            return;
        }
        if(tableProduit.getSelectionModel().getSelectedItem() == null) {
            toastMsg.show("Svp, selectionnée le produit qui vous voulez modifier !", 2000);
            return;
        }

        int index = tableProduit.getSelectionModel().getSelectedIndex();
        TableProduit produit = listProduits.get(index);
        produit.setQte(Integer.parseInt(fieldQte.getText().trim()));

        listProduits.set(index, produit);
        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }

        countTotal();
        fieldQte.setText(null);
    }

    @FXML
    private void onPrint() {

    }

    /* End Product operations */

    /* Start */
    private void countTotal() {
        double totalHT = 0,
                totalTVA = 0,
                totalTTC = 0;
        for(TableProduit produit : listProduits) {
            totalHT += produit.getMht();
            totalTVA += produit.getMht() * produit.getTva() / 100;
            totalTTC += produit.getMttc();
        }

        fieldTotalHT.setText(String.valueOf(totalHT));
        fieldTotalTVA.setText(String.valueOf(totalTVA));
        fieldTotalTTC.setText(String.valueOf(totalTTC));
    }

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
