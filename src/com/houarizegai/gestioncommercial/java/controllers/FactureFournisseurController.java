package com.houarizegai.gestioncommercial.java.controllers;

import com.houarizegai.gestioncommercial.java.database.models.Fournisseur;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FactureFournisseurController implements Initializable {
    // Root Node (Parent of all elements (nodes))
    @FXML
    private StackPane root;

    // Fournisseur Infos
    @FXML
    private JFXTextField fieldNumFournisseur, fieldNomFournisseur, fieldPrenomFournisseur;

    /* Start Facture Infos */

    @FXML
    private JFXTextField fieldNumFacture, fieldNumFactureFournisseur;

    @FXML
    private JFXDatePicker pickerDate;

    /* End Facture Infos */

    // Mode Payement Number
    @FXML
    private JFXTextField fieldNumModePayement;

    // Total Montant Infos
    @FXML
    private JFXTextField fieldTotalHT, fieldTotalTVA, fieldTotalTTC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // For Select The Fournisseur
    @FXML
    private void onChooseFournisseur() {

    }

    // For Select Payment Mode
    @FXML
    private void onChoosePaymentMode() {

    }

    /* Start Product operations */

    @FXML
    private void onAdd() {

    }

    @FXML
    private void onDelete() {

    }

    @FXML
    private void onEdit() {

    }

    @FXML
    private void onPrint() {

    }

    /* End Product operations */

    /* Start Change Fournisseur (Bascule between fournissuer) */

    @FXML
    private void onMoveToFirst() {

    }

    @FXML
    private void onMoveToNext() {

    }

    @FXML
    private void onMoveToEnd() {

    }

    @FXML
    private void onMoveToPrevious() {

    }

    /* End Change Fournisseur (Bascule between fournissuer) */

    @FXML
    private void onSave() {

    }
}
