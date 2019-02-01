package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.database.dao.*;
import com.houarizegai.gestioncommercial.java.database.models.*;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.FactureBuilder;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.LigneFactureBuilder;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ReglementBuilder;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.StockBuilder;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;
import com.houarizegai.gestioncommercial.java.utils.regex.ClientRegex;
import com.houarizegai.gestioncommercial.java.utils.regex.ProduitRegex;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.javafx.scene.control.SelectedCellsMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class FactureController implements Initializable {
    // Root Node (Parent of all elements (nodes))
    @FXML
    private StackPane root;

    /* Start Client */

    @FXML
    private JFXTextField fieldNumClient, fieldNomClient, fieldPrenomClient;

    public static Client selectedClient;

    public static JFXDialog dialogSelectClient;

    private VBox selectClientView;

    /* End Client */

    @FXML
    private JFXTextArea areaObservations;

    /* Start Facture Infos */

    @FXML
    private JFXTextField fieldNumFacture;

    @FXML
    private JFXDatePicker pickerDate;

    /* End Facture Infos */

    /* Start Product Table */

    @FXML
    private JFXTreeTableView tableProduit;

    private JFXTreeTableColumn<TableProduit, String> colRef, colDesignation, colQte, colPU, colRemise, colMHT, colTVA, colMTTC;

    // Data of Table
    public static ObservableList<TableProduit> listProduits;

    /* End Product Table */

    /* Start Product Form infos */

    @FXML
    private JFXTextField fieldRemise, fieldQte;

    public static Produit selectedProduit;

    public static JFXDialog dialogSelectProduit;

    private VBox selectProduitView;

    /* End Product Form infos */

    /* Start Mode Reglement */

    @FXML
    private JFXTextField fieldMontantModePay;

    @FXML
    private JFXComboBox<String> comboModeReg;

    // Get it from database
    private List<ModeReglement> modeReglements;

    /* End Mode Reglement */

    // Total Montant Infos
    @FXML
    private JFXTextField fieldTotalHT, fieldTotalTVA, fieldTotalTTC;

    // Buttons
    @FXML
    private JFXButton btnMoveToFirst, btnMoveToPrevious, btnMoveToNext, btnMoveToEnd;

    // Error message showing in time limited (like toast in android)
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);

        // Get Auto increment facture number from db
        fieldNumFacture.setText(String.valueOf(MainDao.getCurrentAutoIncrement("Facture")));

        // init Date of facture
        pickerDate.setValue(LocalDate.now());

        // Get Mode reglement from db
        modeReglements = ReglementDao.getModeReglements();

        // init combo Mode reglement
        for (ModeReglement modeReglement : modeReglements)
            comboModeReg.getItems().addAll(modeReglement.getLibModeReglement());
        comboModeReg.getSelectionModel().select(0);

        // Load Select client View
        try {
            selectClientView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/facture/SelectClient.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Initialize table product
        initProduitTable();
        initDataOfProduitTable();

        // on Change Select in product table
        tableProduit.setOnMouseClicked((e -> {
            if (tableProduit.getSelectionModel().getSelectedItem() == null)
                return;

            fieldQte.setText(colQte.getCellData(tableProduit.getSelectionModel().getSelectedIndex()));
            fieldRemise.setText(colRemise.getCellData(tableProduit.getSelectionModel().getSelectedIndex()));
            fieldQte.requestFocus(); // UX: Make cursor in qte field when i select row in table
        }));

        /* UX: when i click enter in (qte or remise) field execute Edit method */
        fieldQte.setOnKeyReleased(e-> {
            if(e.getCode().equals(KeyCode.ENTER))
                onEdit();
        });
        fieldRemise.setOnKeyReleased(e-> {
            if(e.getCode().equals(KeyCode.ENTER))
                onEdit();
        });

        // Init action in Buttons
        initActionButtons();
    }

    private void initActionButtons() {
        btnMoveToFirst.setOnAction(e -> {
            loadFactureToView(FactureDao.getFirstFacture());
            btnMoveToFirst.setDisable(true);
            btnMoveToEnd.setDisable(false);
            btnMoveToPrevious.setDisable(true);
            btnMoveToNext.setDisable(false);
        });
        btnMoveToEnd.setOnAction(e -> {
            onReset();
            btnMoveToFirst.setDisable(false);
            btnMoveToEnd.setDisable(true);
            btnMoveToPrevious.setDisable(false);
            btnMoveToNext.setDisable(true);
        });
        btnMoveToPrevious.setOnAction(e -> {
            loadFactureToView(FactureDao.getPreviousFacture(Integer.parseInt(fieldNumFacture.getText())));
            btnMoveToEnd.setDisable(false);
            btnMoveToNext.setDisable(false);
        });
        btnMoveToNext.setOnAction(e -> {
            Facture facture = FactureDao.getNextFacture(Integer.parseInt(fieldNumFacture.getText()));
            loadFactureToView(facture);
            if(facture == null) { // Show new Facture
                onReset();
            }
            btnMoveToFirst.setDisable(false);
            btnMoveToPrevious.setDisable(false);
        });
    }

    /* Start Table Produit */

    public class TableProduit extends RecursiveTreeObject<TableProduit> {
        public StringProperty ref;
        public StringProperty designation;
        public StringProperty qte;
        public StringProperty pu;
        public StringProperty remise;
        public StringProperty mht;
        public StringProperty tva;
        public StringProperty mttc;

        public TableProduit(String ref, String designation, int qte, double pu, double remise, double mht, double tva, double mttc) {
            this.ref = new SimpleStringProperty(ref);
            this.designation = new SimpleStringProperty(designation);
            this.qte = new SimpleStringProperty(String.valueOf(qte));
            this.pu = new SimpleStringProperty(String.valueOf(pu));
            this.remise = new SimpleStringProperty(String.valueOf(remise));
            this.mht = new SimpleStringProperty(String.valueOf(mht));
            this.tva = new SimpleStringProperty(String.valueOf(tva));
            this.mttc = new SimpleStringProperty(String.valueOf(mttc));
        }

        public String getRef() {
            return this.ref.getValue();
        }

        public String getDesignation() {
            return designation.getValue();
        }

        public StringProperty designationProperty() {
            return designation;
        }

        public int getQte() {
            return Integer.parseInt(qte.getValue());
        }

        public void setQte(int qte) {
            this.qte = new SimpleStringProperty(String.valueOf(qte));
        }

        public double getPu() {
            return Double.parseDouble(pu.getValue());
        }

        public double getRemise() {
            return Double.parseDouble(this.remise.getValue());
        }

        public void setRemise(double remise) {
            this.remise = new SimpleStringProperty(String.valueOf(remise));
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

        colRemise = new JFXTreeTableColumn<>("Rem %");
        colRemise.setPrefWidth(80);
        colRemise.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().remise);

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
        tableProduit.getColumns().addAll(colRef, colDesignation, colQte, colPU, colRemise, colMHT, colTVA, colMTTC);
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

    // For Select Client
    @FXML
    private void onChooseClient() {
        dialogSelectClient = new JFXDialog(root, selectClientView, JFXDialog.DialogTransition.CENTER);
        dialogSelectClient.setOnDialogClosed(e -> { // if i close dialog: make the client selected
            if (selectedClient == null)
                return;
            fieldNumClient.setText(String.valueOf(selectedClient.getNumClient()));
            fieldNomClient.setText(String.valueOf(selectedClient.getNomClient()));
            fieldPrenomClient.setText(String.valueOf(selectedClient.getPrenom()));
        });
        dialogSelectClient.show();
    }

    /* Start Product operations */

    @FXML
    private void onAdd() {
        // Load Select Produit View
        try {
            selectProduitView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/facture/SelectProduit.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        dialogSelectProduit = new JFXDialog(root, selectProduitView, JFXDialog.DialogTransition.CENTER);
        dialogSelectProduit.setOnDialogClosed(e -> { // if i close dialog: make the product selected
            if (selectedProduit == null)
                return;
            listProduits.add(new TableProduit(selectedProduit.getReference(),
                    selectedProduit.getLibProd(),
                    0,
                    selectedProduit.getPrixHt(),
                    0.0,
                    0.0,
                    selectedProduit.getTauxTva(),
                    0.0));
        });
        dialogSelectProduit.show();
    }

    @FXML
    private void onDelete() { // delete product
        if (tableProduit.getSelectionModel().getSelectedItem() == null) {
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
        if (tableProduit.getSelectionModel().getSelectedItem() == null) {
            toastMsg.show("Svp, selectionnée le produit qui vous voulez modifier !", 2000);
            return;
        }

        String qte = fieldQte.getText() == null ? "0" : fieldQte.getText().trim();
        String remise = fieldRemise.getText() == null ? "0" : fieldRemise.getText().trim();

        if (!qte.matches("[0-9]{1,3}")) {
            toastMsg.show("Svp, taper le quantité dans ce form: nombre entre 0 et 999", 2000);
            return;
        }
        if (!remise.trim().matches(ProduitRegex.PRIX_HT) || Double.parseDouble(remise) > 100d) {
            toastMsg.show("Svp, taper le remise dans ce form: nombre entre 0 et 100", 2000);
            return;
        }

        // Get index of selected product
        int index = tableProduit.getSelectionModel().getSelectedIndex();
        TableProduit produit = listProduits.get(index);

        /* Update Qte & Remise */
        produit.setQte(Integer.parseInt(qte));
        produit.setRemise(Double.parseDouble(remise));

        // Update mht & mttc
        produit.setMht((produit.getPu() - (produit.getPu() / 100 * produit.getRemise())) * produit.getQte());
        produit.setMttc(produit.getMht() * produit.getTva() / 100 + produit.getMht());

        listProduits.set(index, produit);
        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }

        countTotal();
        fieldQte.setText(null);
        fieldRemise.setText(null);
    }

    @FXML
    private void onPrint() {

    }

    /* End Product operations */

    // Count total price
    private void countTotal() {
        double totalHT = 0,
                totalTVA = 0,
                totalTTC = 0;
        for (TableProduit produit : listProduits) {
            totalHT += produit.getMht();
            totalTVA += produit.getMht() * produit.getTva() / 100;
            totalTTC += produit.getMttc();
        }

        fieldTotalHT.setText(String.valueOf(totalHT));
        fieldTotalTVA.setText(String.valueOf(totalTVA));
        fieldTotalTTC.setText(String.valueOf(totalTTC));
    }

    /* Start Change Facture (Bascule between facture) */

    private void loadFactureToView(Facture facture) {
        if(facture == null)
            return;

        // Facture pricipale info
        fieldNumFacture.setText(String.valueOf(facture.getNumFacture()));
        pickerDate.setValue(UsefulMethods.getSQLDate(facture.getDateFacture()).toLocalDate());

        // Client
        Client client = ClientDao.getClient(facture.getNumClient());
        fieldNumClient.setText(String.valueOf(client.getNumClient()));
        fieldNomClient.setText(client.getNomClient());
        fieldPrenomClient.setText(client.getPrenom());

        areaObservations.setText(facture.getObservations());

        // Mode Reglement
        String libModeReglement = ReglementDao.getLibModeReglement(facture.getIdModeReglement());
        comboModeReg.getSelectionModel().select(libModeReglement);

        loadDataToProductTable(facture.getLigneFactures());

        countTotal();
        fieldQte.setText(null);
        fieldRemise.setText(null);

    }

    private void loadDataToProductTable(List<LigneFacture> ligneFactures) { // Set data to table of ligne Facture
        listProduits.clear();

        for(LigneFacture ligneFacture : ligneFactures) {
            TableProduit produit = new TableProduit(ligneFacture.getReference(),
                    ligneFacture.getLibProd(),
                    ligneFacture.getQuantite(),
                    ligneFacture.getPrixVente(),
                    ligneFacture.getRemise(),
                    0.0,
                    ligneFacture.getTauxTva(),
                    0.0);
            // Update mht & mttc
            produit.setMht((produit.getPu() - (produit.getPu() / 100 * produit.getRemise())) * produit.getQte());
            produit.setMttc(produit.getMht() * produit.getTva() / 100 + produit.getMht());

            listProduits.add(produit);
        }

        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    /* End Change Facture (Bascule between factures) */

    @FXML
    private void onSave() {
        if (fieldNumClient.getText() == null || fieldNumClient.getText().isEmpty()) {
            toastMsg.show("Svp, selectionné le client", 2000);
            return;
        }
        if(fieldMontantModePay.getText() != null && !fieldMontantModePay.getText().trim().matches(ProduitRegex.PRIX_HT)) {
            toastMsg.show("Svp, taper un nombre dans le numero de mode reglement !", 2000);
            return;
        }

        int selectedIdModeReg = 0;
        for (ModeReglement modeReglement : modeReglements)
            if (modeReglement.getLibModeReglement().equalsIgnoreCase(comboModeReg.getSelectionModel().getSelectedItem())) {
                selectedIdModeReg = modeReglement.getIdModeReglement();
                break;
            }

        // Prepare facture object
        Facture facture = new FactureBuilder()
                //.setDateFacture(new Date())
                .setDateFacture(java.sql.Date.valueOf(pickerDate.getValue()))
                .setNumClient(Integer.parseInt(fieldNumClient.getText()))
                .setIdAdresseFacturation(1)
                .setIdModeReglement(selectedIdModeReg)
                .setTotalHT(Double.parseDouble(fieldTotalHT.getText()))
                .setTotalTVA(Double.parseDouble(fieldTotalHT.getText()))
                .setTotalFraisPort(0d)
                .setTotalTTC(Double.parseDouble(fieldTotalTTC.getText()))
                .setSaisiPar(DBConnection.user)
                .setSaisiLe(new Date())
                .setObservations(areaObservations.getText())
                .setLigneFactures(getLigneFacturesFromTable())
                .build();

        // Save facture in database
        int status = FactureDao.addFacture(facture);

        switch (status) {
            case -1:
                toastMsg.show("Erreur de connexion !", 2000);
                break;
            case 0:
                toastMsg.show("Erreur dans la sauvgarde de facture !", 2000);
                break;
            default:
                toastMsg.show("Vous avez ajouter une nouvelle facture !", 2000);

                // Update Stock in database
                //updateStockAfterFactureAdded();

                // Save Reglement if exist
                if(fieldMontantModePay.getText() != null && !fieldMontantModePay.getText().trim().isEmpty()) {
                    Reglement reglement = new ReglementBuilder()
                            .setDateReglement(java.sql.Date.valueOf(pickerDate.getValue()))
                            .setIdModeReglement(selectedIdModeReg)
                            .setNumClient(Integer.parseInt(fieldNumClient.getText()))
                            .setSaisiPar(DBConnection.user)
                            .setSaisiLe(new Date())
                            .setObservations(areaObservations.getText())
                            .setMontant(Double.valueOf(fieldMontantModePay.getText() == null ? "0.0" : fieldMontantModePay.getText()))
                            .build();

                    status = ReglementDao.addReglement(reglement);
                    switch (status) {
                        case -1:
                            toastMsg.show("Erreur de connexion !", 2000);
                            return;
                        case 0:
                            toastMsg.show("Erreur dans la sauvgarde de reglement !", 2000);
                            break;
                    }
                }
                onReset();
                break;
        }
    }

    private List<LigneFacture> getLigneFacturesFromTable() {
        List<LigneFacture> ligneFactures = new LinkedList<>();

        int numeroFact = Integer.parseInt(fieldNumFacture.getText());

        for (TableProduit produit : listProduits) {
            LigneFacture ligneFacture = new LigneFactureBuilder()
                    .setNumFacture(numeroFact)
                    .setReference(produit.getRef())
                    .setLibProd(produit.getDesignation())
                    .setQuantite(produit.getQte())
                    .setPrixVente(produit.getPu())
                    .setRemise(produit.getRemise())
                    .setTauxTva(produit.getTva())
                    .build();
            ligneFactures.add(ligneFacture);
        }

        return ligneFactures;
    }

    private void onReset() { // Clear screen
        // Get Auto increment facture number from db
        fieldNumFacture.setText(String.valueOf(MainDao.getCurrentAutoIncrement("Facture")));

        fieldNumClient.setText(null);
        fieldNomClient.setText(null);
        fieldPrenomClient.setText(null);

        areaObservations.setText(null);

        comboModeReg.getSelectionModel().select(0);
        fieldMontantModePay.setText(null);

        // Delete content of table
        listProduits.clear();
        fieldQte.setText(null);
        fieldRemise.setText(null);

        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }

        fieldTotalHT.setText("0.0");
        fieldTotalTVA.setText("0.0");
        fieldTotalTTC.setText("0.0");
    }

    private void updateStockAfterFactureAdded() { // Not yet used
        for (TableProduit produit : listProduits) {
            Stock stock = new StockBuilder()
                    .setReference(produit.getRef())
                    .setDateModif(new Date())
                    .setAuteurModif(DBConnection.user)
                    .setQteEnStock(produit.getQte())
                    .build();
            StockDao.setStockQte(stock);
        }
    }
}
