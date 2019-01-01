package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.controllers.forms.produit.DeleteProduitController;
import com.houarizegai.gestioncommercial.java.controllers.forms.produit.EditProduitController;
import com.houarizegai.gestioncommercial.java.database.dao.ProduitDao;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    @FXML
    private JFXTreeTableView<TableProduit> tableProduit;

    private JFXTreeTableColumn<TableProduit, String> colReference, colGenCode, colCodeBarre, colLibProd, colPrixHt, colQteReappro, colQteMin, colTauxTva;

    // Dialog showing in (add/update) table
    public static JFXDialog dialogProduitAdd, dialogProduitEdit, dialogProduitDelete;

    private JFXSnackbar toastMsg;
    // data of table
    private List<Produit> produits;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSearchBy.getItems().addAll("Tout", "Reference", "Gen Code", "Code Barre", "Nom", "Prix Ht", "Qte Reappro",
                "Qte Min", "Taux TVA");

        comboSearchBy.getSelectionModel().selectFirst();

        toastMsg = new JFXSnackbar(root);

        initProduitTable();
        loadProduitTableData();
        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());
    }

    private void filterSearchTable() {
        tableProduit.setPredicate((TreeItem<TableProduit> Produit) -> {
            String reference = Produit.getValue().reference.getValue();
            String genCode = (Produit.getValue().genCode.getValue() == null) ? "" : Produit.getValue().genCode.getValue().toLowerCase();
            String codeBarre = (Produit.getValue().codeBarre.getValue() == null) ? "" : Produit.getValue().codeBarre.getValue().toLowerCase();
            String libProd = (Produit.getValue().libProd.getValue() == null) ? "" : Produit.getValue().libProd.getValue().toLowerCase();
            String prixHt = (Produit.getValue().prixHt.getValue() == null) ? "" : Produit.getValue().prixHt.getValue().toLowerCase();
            String qteReappro = (Produit.getValue().qteReappro.getValue() == null) ? "" : Produit.getValue().qteReappro.getValue().toLowerCase();
            String qteMin = (Produit.getValue().qteMin.getValue() == null) ? "" : Produit.getValue().qteMin.getValue().toLowerCase();
            String tauxTva = (Produit.getValue().tauxTva.getValue() == null) ? "" : Produit.getValue().tauxTva.getValue().toLowerCase();


            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 1:
                    return reference.contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return genCode.contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return codeBarre.contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return libProd.contains(fieldSearch.getText().toLowerCase());
                case 5:
                    return prixHt.contains(fieldSearch.getText().toLowerCase());
                case 6:
                    return qteReappro.contains(fieldSearch.getText().toLowerCase());
                case 7:
                    return qteMin.contains(fieldSearch.getText().toLowerCase());
                case 8:
                    return tauxTva.contains(fieldSearch.getText().toLowerCase());
                default:
                    return reference.contains(fieldSearch.getText().toLowerCase())
                            || libProd.contains(fieldSearch.getText().toLowerCase())
                            || prixHt.contains(fieldSearch.getText().toLowerCase())
                            || qteReappro.contains(fieldSearch.getText().toLowerCase())
                            || qteMin.contains(fieldSearch.getText().toLowerCase())
                            || tauxTva.contains(fieldSearch.getText().toLowerCase());
            }

        });
    }

    class TableProduit extends RecursiveTreeObject<TableProduit> {
        StringProperty reference;
        StringProperty genCode;
        StringProperty codeBarre;
        StringProperty libProd;
        StringProperty prixHt;
        StringProperty qteReappro;
        StringProperty qteMin;
        StringProperty tauxTva;

        public TableProduit(String reference, String genCode, String codeBarre, String libProd, double prixHt, int qteReappro, int qteMin, double tauxTva) {
            this.reference = new SimpleStringProperty(reference);
            this.genCode= new SimpleStringProperty(genCode);
            this.codeBarre = new SimpleStringProperty(codeBarre);
            this.libProd = new  SimpleStringProperty(libProd);
            this.prixHt = new  SimpleStringProperty(String.valueOf(prixHt));
            this.qteReappro = new  SimpleStringProperty(String.valueOf(qteReappro));
            this.qteMin = new  SimpleStringProperty(String.valueOf(qteMin));
            this.tauxTva = new  SimpleStringProperty(String.valueOf(tauxTva));
        }
    }

    private void initProduitTable() { // This function initialize the table by colunms
        colReference = new JFXTreeTableColumn<>("Reference");
        colReference.setPrefWidth(180);
        colReference.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().reference);

        colGenCode = new JFXTreeTableColumn<>("Gen Code");
        colGenCode.setPrefWidth(150);
        colGenCode.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().genCode);

        colCodeBarre = new JFXTreeTableColumn<>("Code Barre");
        colCodeBarre.setPrefWidth(150);
        colCodeBarre.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().codeBarre);

        colLibProd = new JFXTreeTableColumn<>("Nom");
        colLibProd.setPrefWidth(180);
        colLibProd.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().libProd);

        colPrixHt = new JFXTreeTableColumn<>("Prix HT");
        colPrixHt.setPrefWidth(150);
        colPrixHt.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().prixHt);

        colQteReappro = new JFXTreeTableColumn<>("Qte Reappro");
        colQteReappro.setPrefWidth(150);
        colQteReappro.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().qteReappro);

        colQteMin = new JFXTreeTableColumn<>("Qte Min");
        colQteMin.setPrefWidth(150);
        colQteMin.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().qteMin);

        colTauxTva = new JFXTreeTableColumn<>("Taux TVA");
        colTauxTva.setPrefWidth(150);
        colTauxTva.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().tauxTva);

        // Add columns to table
        tableProduit.getColumns().addAll(colReference, colGenCode, colCodeBarre, colLibProd, colPrixHt, colQteReappro, colQteMin, colTauxTva);
        tableProduit.setShowRoot(false);
    }

    private void loadProduitTableData() { // Fill table data from database

        ObservableList<TableProduit> listProduits = FXCollections.observableArrayList();

        this.produits = ProduitDao.getProduits(); // Get all Produits from database

        if (produits != null) {
            for (Produit Produit : produits) {
                TableProduit ProduitT = new TableProduit(Produit.getReference(),
                        Produit.getGenCode(),
                        Produit.getCodeBarre(),
                        Produit.getLibProd(),
                        Produit.getPrixHt(),
                        Produit.getQteReappro(),
                        Produit.getQteMini(),
                        Produit.getTauxTva());

                listProduits.add(ProduitT);
            }
        }

        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);
        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onAdd() {
        try {
            VBox paneAddProduit = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/produit/AddProduit.fxml"));
            dialogProduitAdd = getSpecialDialog(paneAddProduit);
            dialogProduitAdd.show();

            JFXTextField fieldReference = (JFXTextField) ((HBox) ((VBox) ((HBox) paneAddProduit.getChildren().get(1)).getChildren().get(0)).getChildren().get(0)).getChildren().get(0);

            // Focus to the first field when i show the dialog
            dialogProduitAdd.setOnDialogOpened(e -> fieldReference.requestFocus());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onEdit() {
        String referenceSelected = colReference.getCellData(tableProduit.getSelectionModel().getSelectedIndex());
        if (referenceSelected == null) {
            toastMsg.show("Svp, selectionné le Produit qui vous voulez Modifier !", 2000);
            return;
        }
        for (Produit produit : produits) {
            if (produit.getReference().equalsIgnoreCase(referenceSelected)) {
                EditProduitController.produitInfo= produit;
                break;
            }
        }

        try {
            VBox paneEditProduit = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/produit/EditProduit.fxml"));
            dialogProduitEdit = getSpecialDialog(paneEditProduit);
            dialogProduitEdit.show();

            JFXTextField fieldGenCode = (JFXTextField) ((HBox) ((VBox) ((HBox) paneEditProduit.getChildren().get(1)).getChildren().get(0)).getChildren().get(1)).getChildren().get(0);

            // Focus to the first field when i show the dialog
            dialogProduitEdit.setOnDialogOpened(e -> fieldGenCode.requestFocus());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onDelete() {
        // get selected produit from table
        String referenceSelected = colReference.getCellData(tableProduit.getSelectionModel().getSelectedIndex());
        if (referenceSelected == null) {
            toastMsg.show("Svp, selectionné le Produit qui vous voulez supprimer !", 2000);
            return;
        }

        for (Produit p : produits) {
            if (p.getReference().equalsIgnoreCase(referenceSelected)) {
                DeleteProduitController.produit = p;
                break;
            }
        }

        // Show confirm dialog
        try {
            VBox paneDeleteProduit = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/produit/DeleteProduit.fxml"));
            dialogProduitDelete = getSpecialDialog(paneDeleteProduit);
            dialogProduitDelete.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private JFXDialog getSpecialDialog(Region content) { // This function create dialog
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOnDialogClosed(e -> loadProduitTableData()); // if i close dialog: reload data to table
        return dialog;
    }

}
