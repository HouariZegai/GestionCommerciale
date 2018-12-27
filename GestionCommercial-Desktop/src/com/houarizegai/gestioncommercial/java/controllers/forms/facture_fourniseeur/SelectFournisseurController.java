package com.houarizegai.gestioncommercial.java.controllers.forms.facture_fourniseeur;

import com.houarizegai.gestioncommercial.java.controllers.FactureFournisseurController;
import com.houarizegai.gestioncommercial.java.controllers.forms.fournisseur.EditFournisseurController;
import com.houarizegai.gestioncommercial.java.database.dao.FournisseurDao;
import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
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

public class SelectFournisseurController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    // Table fournisseur
    @FXML
    private JFXTreeTableView<TableFournisseur> tableFournisseur;

    // Columns of table fournisseur
    private JFXTreeTableColumn<TableFournisseur, String>  colNumFournisseur, colNom, colPrenom, colTelephone;

    List<Fournisseur> fournisseurs;

    private JFXSnackbar toastMsg;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Init Toast Message
        toastMsg = new JFXSnackbar(root);

        // Initialize combo Fournisseur (Search by)
        comboSearchBy.getItems().addAll("Tout", "N° Fournisseur", "Societe", "Nom", "Prenom", "Telephone");
        comboSearchBy.getSelectionModel().select(0);

        initTableFournisseur();
        loadFournisseurTable();

        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());

    }

    /* Start Table */

    class TableFournisseur extends RecursiveTreeObject<TableFournisseur> {
        StringProperty numFournisseur;
        StringProperty nom;
        StringProperty prenom;
        StringProperty telephone;

        public TableFournisseur() {

        }

        public TableFournisseur(int numFournisseur, String nom, String prenom, String telephone) {
            this.numFournisseur = new SimpleStringProperty(String.valueOf(numFournisseur));
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.telephone = new SimpleStringProperty(telephone);
        }
    }

    private void initTableFournisseur() {
        colNumFournisseur = new JFXTreeTableColumn<>("N°");
        colNumFournisseur.setPrefWidth(100);
        colNumFournisseur.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().numFournisseur);
        colNom = new JFXTreeTableColumn<>("Nom");
        colNom.setPrefWidth(100);
        colNom.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().nom);

        colPrenom = new JFXTreeTableColumn<>("Prenom");
        colPrenom .setPrefWidth(140);
        colPrenom .setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().prenom);

        colTelephone = new JFXTreeTableColumn<>("Telephone");
        colTelephone.setPrefWidth(120);
        colTelephone.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().telephone);

        tableFournisseur.getColumns().addAll(colNumFournisseur, colNom, colPrenom, colTelephone);
        tableFournisseur.setShowRoot(false);
    }

    private void loadFournisseurTable() {
        fournisseurs = FournisseurDao.getFournisseur();

        ObservableList<TableFournisseur> listFournisseurs = FXCollections.observableArrayList();
        if(fournisseurs != null) {
            for(Fournisseur f : fournisseurs) {
                listFournisseurs.add(new TableFournisseur(f.getNumFournisseur(), f.getNom(), f.getPrenom(), f.getTelephone()));
            }
        }

        final TreeItem<TableFournisseur> treeItem = new RecursiveTreeItem<>(listFournisseurs, RecursiveTreeObject::getChildren);

        try {
            tableFournisseur.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    public void filterSearchTable() {
        tableFournisseur.setPredicate((TreeItem<TableFournisseur> fournisseur) -> {
            String numFournisseur = fournisseur.getValue().numFournisseur.getValue();
            String nom = (fournisseur.getValue().nom.getValue() == null) ? "" : fournisseur.getValue().nom.getValue().toLowerCase();
            String prenom = (fournisseur.getValue().prenom.getValue() == null) ? "" : fournisseur.getValue().prenom.getValue().toLowerCase();
            String telephone = (fournisseur.getValue().telephone.getValue() == null) ? "" : fournisseur.getValue().telephone.getValue().toLowerCase();


            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 0:
                    return numFournisseur.contains(fieldSearch.getText().toLowerCase())
                            || nom.contains(fieldSearch.getText().toLowerCase())
                            || prenom.contains(fieldSearch.getText().toLowerCase())
                            || telephone.contains(fieldSearch.getText().toLowerCase());
                case 1:
                    return numFournisseur.contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return nom.contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return prenom.contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return telephone.contains(fieldSearch.getText().toLowerCase());
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
        String numFournisseurSelected = colNumFournisseur.getCellData(tableFournisseur.getSelectionModel().getSelectedIndex());
        if (numFournisseurSelected == null) {
            toastMsg.show("Svp, selectionné le fournisseur !", 2000);
            return;
        }
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.getNumFournisseur() == Integer.parseInt(numFournisseurSelected)) {
                FactureFournisseurController.selectedFournisseur = fournisseur;
                break;
            }
        }

        FactureFournisseurController.dialogSelectForunisseur.close();
    }

    @FXML
    private void onClose() {
        FactureFournisseurController.dialogSelectForunisseur.close();
    }
}
