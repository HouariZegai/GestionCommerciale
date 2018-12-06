package com.houarizegai.gestioncommercial.java.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class FournisseurController implements Initializable {
    @FXML // Parent of all nodes (root node)
    private StackPane root;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    @FXML // Table fournisseur
    private JFXTreeTableView<TableFournisseur> tableFournisseur;
    // Columns of table fournisseur
    private JFXTreeTableColumn<TableFournisseur, String>  colNumFournisseur, colSociete, colCivilite, colNom, colPrenom,
            colAdresse, colCodePostal, colVille, colPays, colTelephone, colMobile, colFax, colEmail, colObservations;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableFournisseur();
    }

    /* Start Table */

    class TableFournisseur extends RecursiveTreeObject<TableFournisseur> {
        StringProperty numFournisseur;
        StringProperty societe;
        StringProperty civilite;
        StringProperty nom;
        StringProperty prenom;
        StringProperty adresse;
        StringProperty codePostal;
        StringProperty ville;
        StringProperty pays;
        StringProperty telephone;
        StringProperty mobile;
        StringProperty fax;
        StringProperty email;
        StringProperty observations;

        public TableFournisseur(int numFounisseur, String societe, String civilite, String nom, String prenom,
                                String adresse, String codePostal, String ville, String pays, String telephone, String mobile,
                                String fax, String email, String observations
        ) {
            this.numFournisseur = new SimpleStringProperty(String.valueOf(numFounisseur));
            this.societe = new SimpleStringProperty(societe);
            this.civilite = new SimpleStringProperty(civilite);
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.adresse = new SimpleStringProperty(adresse);
            this.codePostal = new SimpleStringProperty(codePostal);
            this.ville = new SimpleStringProperty(ville);
            this.pays = new SimpleStringProperty(pays);
            this.telephone = new SimpleStringProperty(telephone);
            this.mobile = new SimpleStringProperty(mobile);
            this.fax = new SimpleStringProperty(fax);
            this.email = new SimpleStringProperty(email);
            this.observations = new SimpleStringProperty(observations);
        }
    }
    
    private void initTableFournisseur() {
        colNumFournisseur = new JFXTreeTableColumn<>("N°");
        colNumFournisseur.setPrefWidth(80);
        colNumFournisseur.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().numFournisseur);

        colSociete = new JFXTreeTableColumn<>("Societe");
        colSociete.setPrefWidth(100);
        colSociete.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().societe);
        
        colCivilite = new JFXTreeTableColumn<>("Civilite");
        colCivilite.setPrefWidth(80);
        colCivilite.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().civilite);

        colNom = new JFXTreeTableColumn<>("Nom");
        colNom.setPrefWidth(100);
        colNom.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().nom);

        colPrenom = new JFXTreeTableColumn<>("Prenom");
        colPrenom .setPrefWidth(120);
        colPrenom .setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().prenom);
        
        colAdresse = new JFXTreeTableColumn<>("Adresse");
        colAdresse .setPrefWidth(150);
        colAdresse .setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().adresse);

        colCodePostal = new JFXTreeTableColumn<>("Code Postal");
        colCodePostal.setPrefWidth(100);
        colCodePostal.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().codePostal);

        colVille = new JFXTreeTableColumn<>("Ville");
        colVille.setPrefWidth(100);
        colVille.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().ville);

        colPays = new JFXTreeTableColumn<>("Pays");
        colPays.setPrefWidth(100);
        colPays.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().pays);

        colTelephone = new JFXTreeTableColumn<>("Telephone");
        colTelephone.setPrefWidth(120);
        colTelephone.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().telephone);

        colMobile = new JFXTreeTableColumn<>("Mobile");
        colMobile.setPrefWidth(120);
        colMobile.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().mobile);

        colFax = new JFXTreeTableColumn<>("Fax");
        colFax.setPrefWidth(120);
        colFax.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().fax);

        colEmail = new JFXTreeTableColumn<>("Email");
        colEmail.setPrefWidth(150);
        colEmail.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().email);

        colObservations = new JFXTreeTableColumn<>("Observations");
        colObservations.setPrefWidth(1610d);
        colObservations.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFournisseur, String> param) -> param.getValue().getValue().observations);

        tableFournisseur.getColumns().addAll(colNumFournisseur, colSociete, colCivilite, colNom, colPrenom, colAdresse, colCodePostal, colVille, colPays, colTelephone, colMobile, colFax, colEmail, colObservations);
    }
    
    /* End Table */

    @FXML
    private void onAdd() {

    }

    @FXML
    private void onDelete() {

    }


    @FXML
    private void onEdit() {

    }
}
