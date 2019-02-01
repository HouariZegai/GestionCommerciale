package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.dao.FactureDao;
import com.houarizegai.gestioncommercial.java.database.dao.ReglementDao;
import com.houarizegai.gestioncommercial.java.database.models.Client;
import com.houarizegai.gestioncommercial.java.database.models.Facture;
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
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FicheClientController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField fieldNumClient, fieldNomClient, fieldPrenomClient;

    @FXML
    private JFXTreeTableView tableFicheClient;

    // Columns of table fiche client
    private JFXTreeTableColumn<TableFicheClient, String> colDate, colDebit, colCredit, colSoldeDebit, colSoldeCredit, colPiece;

    // Data of Table
    public static ObservableList<TableFicheClient> listFicheClients;

    // View for choose client
    private VBox selectClientView;

    // Dialog for choose client
    public static JFXDialog dialogSelectClient;

    // data of selected client
    public static Client selectedClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load Select client View
        try {
            selectClientView = FXMLLoader.load(getClass().getResource("/com/houarizegai/gestioncommercial/resources/views/forms/fiche_client/SelectClient.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        initTable();
    }

    @FXML // For Select Client
    private void onChooseClient() {
        dialogSelectClient = new JFXDialog(root, selectClientView, JFXDialog.DialogTransition.CENTER);
        dialogSelectClient.setOnDialogClosed(e -> { // if i close dialog: make the client selected
            if (selectedClient != null) {
                fieldNumClient.setText(String.valueOf(selectedClient.getNumClient()));
                fieldNomClient.setText(String.valueOf(selectedClient.getNomClient()));
                fieldPrenomClient.setText(String.valueOf(selectedClient.getPrenom()));
                loadDataToTable();
            }
        });
        dialogSelectClient.show();
    }

    /* Start table fiche client */

    public class TableFicheClient extends RecursiveTreeObject<TableFicheClient> implements Comparable<TableFicheClient> {
        private StringProperty date, debit, credit, soldeDebit, soldeCredit, piece;

        public TableFicheClient(Date date, double debit, double credit, double soldeDebit, double soldeCredit, String piece) {
            this.date = new SimpleStringProperty(date.toString());
            this.debit = new SimpleStringProperty(String.valueOf(debit));
            this.credit = new SimpleStringProperty(String.valueOf(credit));
            this.soldeDebit = new SimpleStringProperty(String.valueOf(soldeDebit));
            this.soldeCredit = new SimpleStringProperty(String.valueOf(soldeCredit));
            this.piece = new SimpleStringProperty(piece);
        }

        public TableFicheClient() {
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(Date date) {
            this.date = new SimpleStringProperty(date.toString());
        }

        public double getDebit() {
            if (debit == null)
                return 0d;
            return Double.valueOf(debit.get());
        }

        public void setDebit(double debit) {
            this.debit = new SimpleStringProperty(String.valueOf(debit));
        }

        public double getCredit() {
            if (credit == null)
                return 0d;
            return Double.parseDouble(credit.get());
        }

        public void setCredit(double credit) {
            this.credit = new SimpleStringProperty(String.valueOf(credit));
        }

        public double getSoldeDebit() {
            if (soldeDebit == null || soldeDebit.get() == null)
                return 0d;
            return Double.parseDouble(soldeDebit.get());
        }

        public void setSoldeDebit(double soldeDebit) {
            if(soldeDebit == 0d)
                this.soldeDebit = new SimpleStringProperty(null);
            else
                this.soldeDebit = new SimpleStringProperty(String.valueOf(soldeDebit));
        }

       public double getSoldeCredit() {
            if (soldeCredit == null || soldeCredit.get() == null)
                return 0d;
            return Double.parseDouble(soldeCredit.get());
        }

        public void setSoldeCredit(double soldeCredit) {
            if(soldeCredit == 0d)
                this.soldeCredit = new SimpleStringProperty(null);
            else
                this.soldeCredit = new SimpleStringProperty(String.valueOf(soldeCredit));

        }

        public String getPiece() {
            if (piece == null)
                return null;
            return piece.get();
        }

        public void setPiece(String piece) {
            this.piece = new SimpleStringProperty(piece);
        }

        @Override
        public int compareTo(TableFicheClient o) {
            try {
                Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(this.getDate());
                Date date2 = (new SimpleDateFormat("yyyy-MM-dd")).parse(o.getDate());
                return date.compareTo(date2);
            } catch (Exception e) {
                return 0;
            }

        }
    }

    private void initTable() {
        colDate = new JFXTreeTableColumn<>("DATE");
        colDate.setPrefWidth(150d);
        colDate.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFicheClient, String> param) -> param.getValue().getValue().date);

        colDebit = new JFXTreeTableColumn<>("DEBIT");
        colDebit.setPrefWidth(150d);
        colDebit.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFicheClient, String> param) -> param.getValue().getValue().debit);

        colCredit = new JFXTreeTableColumn<>("CREDIT");
        colCredit.setPrefWidth(150d);
        colCredit.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFicheClient, String> param) -> param.getValue().getValue().credit);

        colSoldeDebit = new JFXTreeTableColumn<>("SOLDE DEBIT");
        colSoldeDebit.setPrefWidth(150d);
        colSoldeDebit.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFicheClient, String> param) -> param.getValue().getValue().soldeDebit);

        colSoldeCredit = new JFXTreeTableColumn<>("SOLDE CREDIT");
        colSoldeCredit.setPrefWidth(150d);
        colSoldeCredit.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFicheClient, String> param) -> param.getValue().getValue().soldeCredit);

        colPiece = new JFXTreeTableColumn<>("PIECE");
        colPiece.setPrefWidth(190d);
        colPiece.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFicheClient, String> param) -> param.getValue().getValue().piece);

        // Add columns to table
        tableFicheClient.getColumns().addAll(colDate, colDebit, colCredit, colSoldeDebit, colSoldeCredit, colPiece);
        tableFicheClient.setShowRoot(false);

    }

    private void loadDataToTable() {
        /* Get data of selected client */
        List<Facture> facturesClient = FactureDao.getFacturesByClient(Integer.parseInt(fieldNumClient.getText()));
        List<Reglement> reglementsClient = ReglementDao.getReglementsByClient(Integer.parseInt(fieldNumClient.getText()));

        listFicheClients = FXCollections.observableArrayList();

        if (facturesClient != null) {
            for (Facture fac : facturesClient) {
                TableFicheClient ficheClient = new TableFicheClient();
                ficheClient.setDate(fac.getDateFacture());
                ficheClient.setDebit(fac.getTotalTTC());
                ficheClient.setPiece("F" + fac.getNumFacture());
                listFicheClients.add(ficheClient);
            }
        }
        if (reglementsClient != null) {
            for (Reglement reg : reglementsClient) {
                TableFicheClient ficheClient = new TableFicheClient();
                ficheClient.setDate(reg.getDateReglement());
                ficheClient.setCredit(reg.getMontant());
                listFicheClients.add(ficheClient);
            }
        }

        // Sort list
        Collections.sort(listFicheClients);

        if (listFicheClients != null && listFicheClients.size() > 0) {
            Collections.sort(listFicheClients); // Filter data of table by date

            if (listFicheClients.get(0).getPiece() != null) {
                listFicheClients.get(0).setSoldeDebit(listFicheClients.get(0).getDebit());
            } else {
                listFicheClients.get(0).setSoldeCredit(listFicheClients.get(0).getCredit());
            }

            for (int i = 1; i < listFicheClients.size(); i++) {
                if (listFicheClients.get(i).getPiece() != null) { // Debit
                    double soldeCredit = listFicheClients.get(i - 1).getSoldeCredit();
                    double debit = listFicheClients.get(i).getDebit();

                    if (soldeCredit > 0.0) {
                        if (debit > soldeCredit) {
                            listFicheClients.get(i).setSoldeCredit(0.0);
                            listFicheClients.get(i).setSoldeDebit(debit - soldeCredit);
                        } else {
                            listFicheClients.get(i).setSoldeCredit(soldeCredit - debit);
                        }
                    } else {
                        listFicheClients.get(i).setSoldeDebit(listFicheClients.get(i - 1).getSoldeDebit() + debit);
                    }

                } else { // Credit
                    double soldeDebit = listFicheClients.get(i - 1).getSoldeDebit();
                    double credit = listFicheClients.get(i).getCredit();
                    if(soldeDebit > 0.0) {
                        if (credit > soldeDebit) {
                            listFicheClients.get(i).setSoldeDebit(0.0);
                            listFicheClients.get(i).setSoldeCredit(credit - soldeDebit);
                        } else {
                            listFicheClients.get(i).setSoldeDebit(soldeDebit - credit);
                        }
                    } else
                        listFicheClients.get(i).setSoldeCredit(listFicheClients.get(i - 1).getSoldeCredit() + credit);

                }
            }
        }


        final TreeItem<TableFicheClient> treeItem = new RecursiveTreeItem<>(listFicheClients, RecursiveTreeObject::getChildren);
        try {
            tableFicheClient.setRoot(treeItem);
        } catch (Exception e) {
            System.out.println("Error catched !");
        }
    }

    /* End table fiche client */

    @FXML
    private void onPrint() {

    }
}
