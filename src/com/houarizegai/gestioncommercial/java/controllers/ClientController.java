package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    @FXML
    private JFXTreeTableView<TableClient> tableClient;

    private JFXTreeTableColumn<TableClient, String> colNumClient, colSociete, colCivilite, colNomClient, colPrenom,
            colAdresse, colVille, colPays, colEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSearchBy.getItems().addAll("");

        initClientTable();
        loadClientTableData();
    }

    class TableClient extends RecursiveTreeObject<TableClient> {
        StringProperty numClient;
        StringProperty societe;
        StringProperty civilite;
        StringProperty nomClient;
        StringProperty prenom;
        StringProperty adresse;
        StringProperty ville;
        StringProperty pays;
        StringProperty email;

        public TableClient() {

        }

        public TableClient(int numClient, String societe, String civilite, String nomClient, String prenom,
                           String adresse, String ville, String pays, String email) {
            this.numClient = new SimpleStringProperty(String.valueOf(numClient));
            this.societe = new SimpleStringProperty(societe);
            this.civilite = new SimpleStringProperty(civilite);
            this.nomClient = new SimpleStringProperty(nomClient);
            this.prenom = new SimpleStringProperty(prenom);
            this.adresse = new SimpleStringProperty(adresse);
            this.ville = new SimpleStringProperty(ville);
            this.pays = new SimpleStringProperty(pays);
            this.email = new SimpleStringProperty(email);
        }
    }

    private void initClientTable() { // This function initialize the table by colunms
        colNumClient = new JFXTreeTableColumn<>("N°");
        colNumClient.setPrefWidth(100);
        colNumClient.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().numClient);

        colSociete = new JFXTreeTableColumn<>("Societe");
        colSociete.setPrefWidth(100);
        colSociete.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().societe);

        colCivilite = new JFXTreeTableColumn<>("Civilite");
        colCivilite.setPrefWidth(100);
        colCivilite.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().civilite);

        colNomClient = new JFXTreeTableColumn<>("Nom");
        colNomClient.setPrefWidth(100);
        colNomClient.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().nomClient);

        colPrenom = new JFXTreeTableColumn<>("Prenom");
        colPrenom.setPrefWidth(150);
        colPrenom.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().prenom);

        colAdresse = new JFXTreeTableColumn<>("Adresse");
        colAdresse.setPrefWidth(250);
        colAdresse.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().adresse);

        colVille = new JFXTreeTableColumn<>("Ville");
        colVille.setPrefWidth(150);
        colVille.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().ville);

        colPays = new JFXTreeTableColumn<>("Pays");
        colPays.setPrefWidth(120);
        colPays.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().pays);

        colEmail = new JFXTreeTableColumn<>("Email");
        colEmail.setPrefWidth(200);
        colEmail.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().email);

        // Add columns to table
        tableClient.getColumns().addAll(colNumClient, colSociete, colCivilite, colNomClient, colPrenom, colAdresse, colVille, colPays, colEmail);
        tableClient.setPrefWidth(1260d);
        tableClient.setShowRoot(false);
    }

    private void loadClientTableData() { // Fill table data from database

        ObservableList<TableClient> listClients = FXCollections.observableArrayList();

        List<Client> clients = new ClientDao().getClients(); // Get Client from database
        if(clients != null) {
            for(Client client : clients) {
                TableClient clientT = new TableClient(client.getNumClient(),
                        client.getSociete(),
                        client.getCivilite(),
                        client.getNomClient(),
                        client.getPrenom(),
                        client.getAdresse(),
                        client.getVille(),
                        client.getPays(),
                        client.getEmail());

                listClients.add(clientT);
            }
        }

        // This line below just for testing
        listClients.add(new TableClient(1, "TiaretSoft", "Civavo", "ZEGAI", "Houari", "Cité sidi khaled N 94 Tiaret", "Tiaret", "Algerie", "HouariZegai14@gmail.com"));

        final TreeItem<TableClient> treeItem = new RecursiveTreeItem<>(listClients, RecursiveTreeObject::getChildren);
        try {
            tableClient.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onAjouter() { // On Add Client

    }

}
