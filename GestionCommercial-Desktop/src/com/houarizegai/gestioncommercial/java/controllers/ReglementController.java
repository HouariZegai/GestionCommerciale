package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.controllers.forms.reglement.DeleteReglementController;
import com.houarizegai.gestioncommercial.java.controllers.forms.reglement.EditReglementController;
import com.houarizegai.gestioncommercial.java.database.dao.ReglementDao;
import com.houarizegai.gestioncommercial.java.database.models.Reglement;
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
import java.util.List;
import java.util.ResourceBundle;

public class ReglementController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    @FXML
    private JFXTreeTableView<TableReglement> tableReg;

    private JFXTreeTableColumn<TableReglement, String> colIdReg, colDateReg, colnumClient, colModeReg, colMontant, colObs;

    // Dialog showing in (add/update) table
    public static JFXDialog dialogReglementAdd, dialogReglementEdit, dialogReglementDelete;

    private JFXSnackbar toastMsg;
    // data of table (all reglement)
    private List<Reglement> reglements;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSearchBy.getItems().addAll("Tout", "ID", "Date", "N° Facture", "Mode reglement", "Montant", "Observations");
        comboSearchBy.getSelectionModel().selectFirst();

        toastMsg = new JFXSnackbar(root);

        initReglementTable();
        loadReglementTableData();

        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());
    }

    private void filterSearchTable() {
        tableReg.setPredicate((TreeItem<TableReglement> Reglement) -> {
            String idReg = Reglement.getValue().idReg.getValue();
            String dateReg = (Reglement.getValue().dateReg.getValue() == null) ? "" : Reglement.getValue().dateReg.getValue().toLowerCase();
            String numClient = (Reglement.getValue().numClient.getValue() == null) ? "" : Reglement.getValue().numClient.getValue().toLowerCase();
            String modeReg = (Reglement.getValue().modeReg.getValue() == null) ? "" : Reglement.getValue().modeReg.getValue().toLowerCase();
            String montant = (Reglement.getValue().montant.getValue() == null) ? "" : Reglement.getValue().montant.getValue().toLowerCase();
            String obs = (Reglement.getValue().obs.getValue() == null) ? "" : Reglement.getValue().obs.getValue().toLowerCase();

            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 1:
                    return idReg.contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return dateReg.contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return numClient.contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return modeReg.contains(fieldSearch.getText().toLowerCase());
                case 5:
                    return montant.contains(fieldSearch.getText().toLowerCase());
                case 6:
                    return obs.contains(fieldSearch.getText().toLowerCase());
                default:
                    return idReg.contains(fieldSearch.getText().toLowerCase())
                            || dateReg.contains(fieldSearch.getText().toLowerCase())
                            || numClient.contains(fieldSearch.getText().toLowerCase())
                            || modeReg.contains(fieldSearch.getText().toLowerCase())
                            || montant.contains(fieldSearch.getText().toLowerCase())
                            || obs.contains(fieldSearch.getText().toLowerCase());
            }

        });
    }

    class TableReglement extends RecursiveTreeObject<TableReglement> {
        StringProperty idReg;
        StringProperty dateReg;
        StringProperty numClient;
        StringProperty modeReg;
        StringProperty montant;
        StringProperty obs;

        public TableReglement() {}

        public TableReglement(int idReg, String dateReg, int numClient, String modeReg, double montant, String obs) {
            this.idReg = new SimpleStringProperty(String.valueOf(idReg));
            this.dateReg = new SimpleStringProperty(dateReg);
            this.numClient = new SimpleStringProperty(String.valueOf(numClient));
            this.modeReg = new SimpleStringProperty(modeReg);
            this.montant = new SimpleStringProperty(String.valueOf(montant));
            this.obs = new SimpleStringProperty(obs);
        }
    }

    private void initReglementTable() { // This function initialize the table by colunms
        colIdReg = new JFXTreeTableColumn<>("ID");
        colIdReg.setPrefWidth(180);
        colIdReg.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableReglement, String> param) -> param.getValue().getValue().idReg);

        colDateReg = new JFXTreeTableColumn<>("DATE");
        colDateReg.setPrefWidth(150);
        colDateReg.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableReglement, String> param) -> param.getValue().getValue().dateReg);

        colnumClient = new JFXTreeTableColumn<>("N° CLIENT");
        colnumClient.setPrefWidth(150);
        colnumClient.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableReglement, String> param) -> param.getValue().getValue().numClient);

        colModeReg = new JFXTreeTableColumn<>("MODE REGLEMENT");
        colModeReg.setPrefWidth(180);
        colModeReg.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableReglement, String> param) -> param.getValue().getValue().modeReg);

        colMontant = new JFXTreeTableColumn<>("MONTANT");
        colMontant.setPrefWidth(150);
        colMontant.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableReglement, String> param) -> param.getValue().getValue().montant);

        colObs = new JFXTreeTableColumn<>("OBSERVATIONS");
        colObs.setPrefWidth(450);
        colObs.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableReglement, String> param) -> param.getValue().getValue().obs);

        // Add columns to table
        tableReg.getColumns().addAll(colIdReg, colDateReg, colnumClient, colModeReg, colMontant, colObs);
        tableReg.setShowRoot(false);
    }

    private void loadReglementTableData() { // Fill table data from database

        ObservableList<TableReglement> listReglements = FXCollections.observableArrayList();

        this.reglements = ReglementDao.getReglements(); // Get all Reglements from database

        if (reglements != null) {
            for (Reglement reglement : reglements) {
                TableReglement regT = new TableReglement(reglement.getIdReglement(),
                        reglement.getDateReglement().toString(),
                        reglement.getNumClient(),
                        ReglementDao.getLibModeReglement(reglement.getIdModeReglement()),
                        reglement.getMontant(),
                        reglement.getObservations());

                listReglements.add(regT);
            }
        }

        final TreeItem<TableReglement> treeItem = new RecursiveTreeItem<>(listReglements, RecursiveTreeObject::getChildren);
        try {
            tableReg.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onAdd() {
        try {
            VBox paneAddReglement = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/reglement/AddReglement.fxml"));
            dialogReglementAdd = getSpecialDialog(paneAddReglement);
            dialogReglementAdd.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onEdit() {
        String idRegSelected = colIdReg.getCellData(tableReg.getSelectionModel().getSelectedIndex());
        if (idRegSelected == null) {
            toastMsg.show("Svp, selectionné le Reglement qui vous voulez Modifier !", 2000);
            return;
        }
        for (Reglement reglement : reglements) {
            if (reglement.getIdReglement() == Integer.parseInt(idRegSelected)) {
                EditReglementController.reglementInfo = reglement;
                break;
            }
        }

        try {
            VBox paneEditReglement = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/reglement/EditReglement.fxml"));
            dialogReglementEdit = getSpecialDialog(paneEditReglement);
            dialogReglementEdit.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onDelete() {
        // get selected reglement from table
        String idRegSelected = colIdReg.getCellData(tableReg.getSelectionModel().getSelectedIndex());
        if (idRegSelected == null) {
            toastMsg.show("Svp, selectionné le Reglement qui vous voulez supprimer !", 2000);
            return;
        }

        for (Reglement reglement : reglements) {
            if (reglement.getIdReglement() == Integer.parseInt(idRegSelected)) {
                DeleteReglementController.reglement = reglement;
                break;
            }
        }

        // Show confirm dialog
        try {
            VBox paneDeleteReglement = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/reglement/DeleteReglement.fxml"));
            dialogReglementDelete = getSpecialDialog(paneDeleteReglement);
            dialogReglementDelete.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private JFXDialog getSpecialDialog(Region content) { // This function create dialog
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOnDialogClosed(e -> loadReglementTableData()); // if i close dialog: reload data to table
        return dialog;
    }

}
