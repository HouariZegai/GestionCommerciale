package com.houarizegai.gestioncommercial.java.controllers.forms.facture;

import com.houarizegai.gestioncommercial.java.controllers.FactureController;
import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
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

public class SelectClientController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    // Table client
    @FXML
    private JFXTreeTableView<TableClient> tableClient;

    // Columns of table client
    private JFXTreeTableColumn<TableClient, String>  colNumClient, colNom, colPrenom, colTelephone;

    private List<Client> clients;

    private JFXSnackbar toastMsg;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Init Toast Message
        toastMsg = new JFXSnackbar(root);

        // Initialize combo Client (Search by)
        comboSearchBy.getItems().addAll("Tout", "N° Client", "Nom", "Prenom", "Telephone");
        comboSearchBy.getSelectionModel().selectFirst();

        initTableClient();
        loadClientTable();

        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());

    }

    /* Start Table */

    class TableClient extends RecursiveTreeObject<TableClient> {
        StringProperty numClient;
        StringProperty nom;
        StringProperty prenom;
        StringProperty telephone;

        public TableClient() {

        }

        TableClient(int numClient, String nom, String prenom, String telephone) {
            this.numClient = new SimpleStringProperty(String.valueOf(numClient));
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.telephone = new SimpleStringProperty(telephone);
        }
    }

    private void initTableClient() {
        colNumClient = new JFXTreeTableColumn<>("N°");
        colNumClient.setPrefWidth(100);
        colNumClient.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().numClient);

        colNom = new JFXTreeTableColumn<>("Nom");
        colNom.setPrefWidth(100);
        colNom.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().nom);

        colPrenom = new JFXTreeTableColumn<>("Prenom");
        colPrenom .setPrefWidth(140);
        colPrenom .setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().prenom);

        colTelephone = new JFXTreeTableColumn<>("Telephone");
        colTelephone.setPrefWidth(120);
        colTelephone.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableClient, String> param) -> param.getValue().getValue().telephone);

        tableClient.getColumns().addAll(colNumClient, colNom, colPrenom, colTelephone);
        tableClient.setShowRoot(false);
    }

    private void loadClientTable() {
        clients = ClientDao.getClients();

        ObservableList<TableClient> listClients = FXCollections.observableArrayList();
        if(clients != null) {
            for(Client f : clients) {
                listClients.add(new TableClient(f.getNumClient(), f.getNomClient(), f.getPrenom(), f.getTelephone()));
            }
        }

        final TreeItem<TableClient> treeItem = new RecursiveTreeItem<>(listClients, RecursiveTreeObject::getChildren);

        try {
            tableClient.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    private void filterSearchTable() {
        tableClient.setPredicate((TreeItem<TableClient> client) -> {
            String numClient = client.getValue().numClient.getValue();
            String nom = client.getValue().nom.getValue().toLowerCase();
            String prenom = client.getValue().prenom.getValue().toLowerCase();
            String telephone = (client.getValue().telephone.getValue() == null) ? "" : client.getValue().telephone.getValue().toLowerCase();


            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 0:
                    return numClient.contains(fieldSearch.getText().toLowerCase())
                            || nom.contains(fieldSearch.getText().toLowerCase())
                            || prenom.contains(fieldSearch.getText().toLowerCase())
                            || telephone.contains(fieldSearch.getText().toLowerCase());
                case 1:
                    return numClient.contains(fieldSearch.getText().toLowerCase());
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
        String numClientSelected = colNumClient.getCellData(tableClient.getSelectionModel().getSelectedIndex());
        if (numClientSelected == null) {
            toastMsg.show("Svp, selectionné le client !", 2000);
            return;
        }
        for (Client client : clients) {
            if (client.getNumClient() == Integer.parseInt(numClientSelected)) {
                FactureController.selectedClient = client;
                break;
            }
        }

        onClose();
    }

    @FXML
    private void onClose() {
        FactureController.dialogSelectClient.close();
    }
}
