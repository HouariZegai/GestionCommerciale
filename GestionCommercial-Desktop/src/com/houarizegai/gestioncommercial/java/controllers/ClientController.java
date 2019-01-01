package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.controllers.forms.client.DeleteClientController;
import com.houarizegai.gestioncommercial.java.controllers.forms.client.EditClientController;
import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
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

    // Dialog showing in (add/update) table
    public static JFXDialog dialogClientAdd, dialogClientEdit, dialogClientDelete;

    private JFXSnackbar toastMsg;
    // data of table
    private List<Client> clients;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSearchBy.getItems().addAll("Tout", "N° Client", "Societe", "Civilite", "Nom", "Prenom", "Adresse", "Ville", "Pays", "Email");
        comboSearchBy.getSelectionModel().select(0);

        toastMsg = new JFXSnackbar(root);

        initClientTable();
        loadClientTableData();
        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());
    }

    private void filterSearchTable() {
        tableClient.setPredicate((TreeItem<TableClient> client) -> {
            String numClient = client.getValue().numClient.getValue();
            String societe = (client.getValue().societe.getValue() == null) ? "" : client.getValue().societe.getValue().toLowerCase();
            String civilite = (client.getValue().civilite.getValue() == null) ? "" : client.getValue().civilite.getValue().toLowerCase();
            String nomClient = (client.getValue().nomClient.getValue() == null) ? "" : client.getValue().nomClient.getValue().toLowerCase();
            String prenom = (client.getValue().prenom.getValue() == null) ? "" : client.getValue().prenom.getValue().toLowerCase();
            String adresse = (client.getValue().adresse.getValue() == null) ? "" : client.getValue().adresse.getValue().toLowerCase();
            String ville = (client.getValue().ville.getValue() == null) ? "" : client.getValue().ville.getValue().toLowerCase();
            String pays = (client.getValue().pays.getValue() == null) ? "" : client.getValue().pays.getValue().toLowerCase();
            String email = (client.getValue().email.getValue() == null) ? "" : client.getValue().email.getValue().toLowerCase();


            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 0:
                    return numClient.contains(fieldSearch.getText().toLowerCase())
                            || societe.contains(fieldSearch.getText().toLowerCase())
                            || civilite.contains(fieldSearch.getText().toLowerCase())
                            || nomClient.contains(fieldSearch.getText().toLowerCase())
                            || prenom.contains(fieldSearch.getText().toLowerCase())
                            || adresse.contains(fieldSearch.getText().toLowerCase())
                            || ville.contains(fieldSearch.getText().toLowerCase())
                            || pays.contains(fieldSearch.getText().toLowerCase())
                            || email.contains(fieldSearch.getText().toLowerCase());
                case 1:
                    return numClient.contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return societe.contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return civilite.contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return nomClient.contains(fieldSearch.getText().toLowerCase());
                case 5:
                    return prenom.contains(fieldSearch.getText().toLowerCase());
                case 6:
                    return adresse.contains(fieldSearch.getText().toLowerCase());
                case 7:
                    return ville.contains(fieldSearch.getText().toLowerCase());
                case 8:
                    return pays.contains(fieldSearch.getText().toLowerCase());
                case 9:
                    return email.contains(fieldSearch.getText().toLowerCase());
                default: return true;
            }

        });
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

        TableClient(int numClient, String societe, String civilite, String nomClient, String prenom,
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

        this.clients = ClientDao.getClients(); // Get ClientRegex from database
        if (clients != null) {
            for (Client client : clients) {
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

        final TreeItem<TableClient> treeItem = new RecursiveTreeItem<>(listClients, RecursiveTreeObject::getChildren);
        try {
            tableClient.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onAdd() { // On Add ClientRegex
        try {
            VBox paneAddClient = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/client/AddClient.fxml"));
            dialogClientAdd = getSpecialDialog(paneAddClient);
            dialogClientAdd.show();

            JFXTextField fieldSociete = (JFXTextField) ((HBox) ((VBox) ((HBox) paneAddClient.getChildren().get(1)).getChildren().get(0)).getChildren().get(1)).getChildren().get(0);

            // Focus to the first field when i show the dialog
            dialogClientAdd.setOnDialogOpened(e -> fieldSociete.requestFocus());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onEdit() {
        String numClientSelected = colNumClient.getCellData(tableClient.getSelectionModel().getSelectedIndex());
        if (numClientSelected == null) {
            toastMsg.show("Svp, selectionné le client qui vous voulez Modifier !", 2000);
            return;
        }
        for (Client client : clients) {
            if (client.getNumClient() == Integer.parseInt(numClientSelected)) {
                EditClientController.clientInfo = client;
                break;
            }
        }

        try {
            VBox paneEditClient = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/client/EditClient.fxml"));
            dialogClientEdit = getSpecialDialog(paneEditClient);
            dialogClientEdit.show();

            JFXTextField fieldSociete = (JFXTextField) ((HBox) ((VBox) ((HBox) paneEditClient.getChildren().get(1)).getChildren().get(0)).getChildren().get(1)).getChildren().get(0);

            // Focus to the first field when i show the dialog
            dialogClientEdit.setOnDialogOpened(e -> fieldSociete.requestFocus());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onDelete() {
        // get selected client from table
        String numClientSelected = colNumClient.getCellData(tableClient.getSelectionModel().getSelectedIndex());
        if (numClientSelected == null) {
            toastMsg.show("Svp, selectionné le client qui vous voulez supprimer !", 2000);
            return;
        }

        for (Client c : clients) {
            if (c.getNumClient() == Integer.parseInt(numClientSelected)) {
                DeleteClientController.client = new ClientBuilder()
                        .setNumClient(c.getNumClient())
                        .setSociete(c.getSociete())
                        .setCivilite(c.getCivilite())
                        .setNomClient(c.getNomClient())
                        .setPrenom(c.getPrenom())
                        .setTelephone(c.getTelephone())
                        .setMobile(c.getMobile())
                        .setFax(c.getFax())
                        .setEmail(c.getEmail())
                        .setType(c.getType())
                        .setAdresse(c.getAdresse())
                        .setCodePostal(c.getCodePostal())
                        .setVille(c.getVille())
                        .setPays(c.getPays())
                        .setLivreMemeAdresse(c.isLivreMemeAdresse())
                        .setFactureMemeAdresse(c.isFactureMemeAdresse())
                        .setExemptTva(c.isExemptTva())
                        .setObservations(c.getObservations())
                        .build();
                break;
            }
        }

        // Show confirm dialog
        try {
            VBox paneDeleteClient = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/client/DeleteClient.fxml"));
            dialogClientDelete = getSpecialDialog(paneDeleteClient);
            dialogClientDelete.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private JFXDialog getSpecialDialog(Region content) { // This function create dialog
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOnDialogClosed(e -> loadClientTableData()); // if i close dialog: reload data to table
        return dialog;
    }

}
