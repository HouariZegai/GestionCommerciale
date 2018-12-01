package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.controllers.forms.client.EditClientController;
import com.houarizegai.gestioncommercial.java.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.designpatterns.builder.ClientBuilder;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    public static JFXDialog dialogClientAdd, dialogClientEdit;

    private JFXSnackbar toastMsg;
    // data of table
    private List<Client> clients;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSearchBy.getItems().addAll("N° Client", "Societe", "Civilite", "Nom", "Prenom", "Adresse", "Ville", "Pays", "Email");
        toastMsg = new JFXSnackbar(root);

        initClientTable();
        loadClientTableData();
        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());
    }

    public void filterSearchTable() {
        tableClient.setPredicate((TreeItem<TableClient> client) -> {
            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 0:
                    return client.getValue().numClient.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 1:
                    return client.getValue().societe.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return client.getValue().civilite.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return client.getValue().nomClient.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return client.getValue().prenom.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 5:
                    return client.getValue().adresse.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 6:
                    return client.getValue().ville.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 7:
                    return client.getValue().pays.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                case 8:
                    return client.getValue().email.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
                default:
                    return client.getValue().numClient.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().societe.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().civilite.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().nomClient.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().prenom.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().adresse.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().ville.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().pays.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase())
                            || client.getValue().email.getValue().toLowerCase().contains(fieldSearch.getText().toLowerCase());
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

        this.clients = new ClientDao().getClients(); // Get Client from database
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
        //listClients.add(new TableClient(1, "TiaretSoft", "Civavo", "ZEGAI", "Houari", "Cité sidi khaled N 94 Tiaret", "Tiaret", "Algerie", "HouariZegai14@gmail.com"));

        final TreeItem<TableClient> treeItem = new RecursiveTreeItem<>(listClients, RecursiveTreeObject::getChildren);
        try {
            tableClient.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onAdd() { // On Add Client
        VBox paneAddClient = null;
        try {
            paneAddClient = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/client/AddClient.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dialogClientAdd = getSpecialDialog(paneAddClient);
        dialogClientAdd.show();
    }

    @FXML
    private void onEdit() {
        String numClientSelected = colNumClient.getCellData(tableClient.getSelectionModel().getSelectedIndex());
        if (numClientSelected == null) {
            toastMsg.show("Svp, selectionné le client qui vous voulez Modifier !", 2000);
            return;
        }
        for(Client client : clients) {
            if(client.getNumClient() == Integer.parseInt(numClientSelected)) {
                EditClientController.clientInfo = client;
                break;
            }
        }

        VBox paneEditClient = null;
        try {
            paneEditClient = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/client/EditClient.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dialogClientEdit = getSpecialDialog(paneEditClient);
        dialogClientEdit.show();
    }

    @FXML
    private void onDelete() {
        String numClientSelected = colNumClient.getCellData(tableClient.getSelectionModel().getSelectedIndex());
        if (numClientSelected == null) {
            toastMsg.show("Svp, selectionné le client qui vous voulez supprimer !", 2000);
            return;
        }

        JFXDialogLayout content = new JFXDialogLayout();
        Text headerText = new Text("Confirmation");
        Text contentText = new Text("Tu a sur pour supprimer cette Client ?");
        headerText.setStyle("-fx-font-size: 19px");
        contentText.setStyle("-fx-font-size: 18px");

        content.setHeading(headerText);
        content.setBody(contentText);

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

        JFXButton btnOk = new JFXButton("Oui");
        btnOk.setOnAction(e -> {
            int status = new ClientDao().deleteClient(Integer.parseInt(numClientSelected));

            System.out.println("status : " + status);
            if (status == -1) {
                toastMsg.show("Erreur de Connexion !", 2000);
            } else {
                Notifications notification = Notifications.create()
                        .title("Vous avez supprimer le client !")
                        .graphic(new ImageView(new Image("/com/houarizegai/gestioncommercial/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT);
                notification.darkStyle();
                notification.show();
                loadClientTableData();
            }
            dialog.close();
        });

        JFXButton btnNo = new JFXButton("Non");
        btnOk.setPrefSize(120, 40);
        btnNo.setPrefSize(120, 40);
        btnOk.getStyleClass().addAll("btn", "btn-delete");
        btnNo.getStyleClass().addAll("btn");
        btnNo.setStyle("-fx-background-color: #DDD;-fx-text-fill: #333");

        content.setActions(btnOk, btnNo);
        StackPane stackpane = new StackPane();

        dialog.getStylesheets().add("/com/houarizegai/gestioncommercial/resources/css/client.css");
        btnNo.setOnAction(e -> {
            dialog.close();
        });
        dialog.show();
    }

    private JFXDialog getSpecialDialog(Region content) { // This function create dialog
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOnDialogClosed(e -> loadClientTableData()); // if i close dialog: reload data to table
        return dialog;
    }

}
