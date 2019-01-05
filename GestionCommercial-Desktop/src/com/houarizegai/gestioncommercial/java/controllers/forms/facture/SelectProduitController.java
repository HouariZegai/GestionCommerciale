package com.houarizegai.gestioncommercial.java.controllers.forms.facture;

import com.houarizegai.gestioncommercial.java.controllers.FactureController;
import com.houarizegai.gestioncommercial.java.database.dao.ProduitDao;
import com.houarizegai.gestioncommercial.java.database.models.Produit;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectProduitController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    // Table produit
    @FXML
    private JFXTreeTableView<TableProduit> tableProduit;

    // Columns of table produit
    private JFXTreeTableColumn<TableProduit, String>  colRef, colLibProd, colPrixHt, colTauxTva;

    private List<Produit> produits;

    private JFXSnackbar toastMsg;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FactureController.selectedProduit = null;

        // Init Toast Message
        toastMsg = new JFXSnackbar(root);

        // Initialize combo Produit (Search by)
        comboSearchBy.getItems().addAll("Tout", "Reference", "Désignation", "Prix HT", "TVA");
        comboSearchBy.getSelectionModel().select(0);

        initTableProduit();
        loadProduitTable();

        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());

    }

    /* Start Table */

    class TableProduit extends RecursiveTreeObject<TableProduit> {
        StringProperty ref;
        StringProperty libProd;
        StringProperty prixHt;
        StringProperty tauxTva;

        public TableProduit() {

        }

        TableProduit(String ref, String libProd, double prixHt, double tauxTva) {
            this.ref = new SimpleStringProperty(ref);
            this.libProd = new SimpleStringProperty(libProd);
            this.prixHt = new SimpleStringProperty(String.valueOf(prixHt));
            this.tauxTva = new SimpleStringProperty(String.valueOf(tauxTva));
        }
    }

    private void initTableProduit() {
        colRef = new JFXTreeTableColumn<>("Reference");
        colRef.setPrefWidth(100);
        colRef.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().ref);

        colLibProd = new JFXTreeTableColumn<>("Dégination");
        colLibProd.setPrefWidth(100);
        colLibProd.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().libProd);

        colPrixHt = new JFXTreeTableColumn<>("Prix HT");
        colPrixHt.setPrefWidth(140);
        colPrixHt .setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().prixHt);

        colTauxTva = new JFXTreeTableColumn<>("TVA");
        colTauxTva.setPrefWidth(120);
        colTauxTva.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableProduit, String> param) -> param.getValue().getValue().tauxTva);

        tableProduit.getColumns().addAll(colRef, colLibProd, colPrixHt, colTauxTva);
        tableProduit.setShowRoot(false);
    }

    private void loadProduitTable() {
        produits = ProduitDao.getProduits();

        ObservableList<TableProduit> listProduits = FXCollections.observableArrayList();
        if(produits != null) {
            //boolean isAleadyAdded;
            foreachProduct:
            for(Produit f : produits) {
                for(int i = 0; i < FactureController.listProduits.size(); i++) {
                    String ref = FactureController.listProduits.get(i).ref.getValue();
                    if(ref.equalsIgnoreCase(f.getReference())) {
                        continue foreachProduct;
                    }
                }
                listProduits.add(new TableProduit(f.getReference(), f.getLibProd(), f.getPrixHt(), f.getTauxTva()));
            }
        }

        final TreeItem<TableProduit> treeItem = new RecursiveTreeItem<>(listProduits, RecursiveTreeObject::getChildren);

        try {
            tableProduit.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    private void filterSearchTable() {
        tableProduit.setPredicate((TreeItem<TableProduit> produit) -> {
            String reference = produit.getValue().ref.getValue();
            String libProd = (produit.getValue().libProd.getValue() == null) ? "" : produit.getValue().libProd.getValue().toLowerCase();
            String prixHT = (produit.getValue().prixHt.getValue() == null) ? "" : produit.getValue().prixHt.getValue().toLowerCase();
            String tauxTva = (produit.getValue().tauxTva.getValue() == null) ? "" : produit.getValue().tauxTva.getValue().toLowerCase();


            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 0:
                    return reference.toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || libProd.contains(fieldSearch.getText().toLowerCase())
                            || prixHT.contains(fieldSearch.getText().toLowerCase())
                            || tauxTva.contains(fieldSearch.getText().toLowerCase());
                case 1:
                    return reference.toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return libProd.contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return prixHT.contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return tauxTva.contains(fieldSearch.getText().toLowerCase());
                default:
                    return true;
            }

        });
    }

    /* End Table */

    @FXML
    private void onAdd() {

    }

    @FXML
    private void onSelect() {
        String refProduitSelected = colRef.getCellData(tableProduit.getSelectionModel().getSelectedIndex());
        if (refProduitSelected == null) {
            toastMsg.show("Svp, selectionné le produit !", 2000);
            return;
        }
        for (Produit produit : produits) {
            if (produit.getReference().equalsIgnoreCase(refProduitSelected)) {
                FactureController.selectedProduit = produit;
                break;
            }
        }

        FactureController.dialogSelectProduit.close();
    }

    @FXML
    private void onClose() {
        FactureController.dialogSelectProduit.close();
    }
}
